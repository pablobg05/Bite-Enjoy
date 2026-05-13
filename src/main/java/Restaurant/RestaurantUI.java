package Restaurant;

import Clases.PedidoClass;
import Clases.Orden;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import javax.swing.*;
import Restaurant.chat_restaurante;

public class RestaurantUI extends JFrame {

    private JPanel orders;
    private ObjectOutputStream salidaServer;
    private Socket socketAlServer;
    private String ipServer;

    private volatile boolean corriendo = true;

    private Queue<PedidoClass> colaLocal = new java.util.PriorityQueue<>((p1, p2) -> {
        if (p1.isVIP() && !p2.isVIP()) return -1;
        if (!p1.isVIP() && p2.isVIP()) return 1;
        return 0;
    });

    //private final String SERVER_IP = "localhost"; //-------------------------------------------------------------------------------------------.-----------> IP DEL SERVER

    public RestaurantUI(String ipServerRecibida) {
        this.ipServer = ipServerRecibida;
        
        setTitle("Pantalla de Restaurante");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                corriendo = false;
                try { if (socketAlServer != null) socketAlServer.close(); } catch (Exception ex) {}
                System.exit(0); 
            }
        });

        // --- HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 45, 45));
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JLabel title = new JLabel("Pedidos de Restaurante");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnDespachar = new JButton("Pedido Listo");
        btnDespachar.addActionListener(e -> despacharSiguiente());
        
        // BOTÓN PARA ABRIR CHAT
        JButton btnChat = new JButton("Chat");

        btnChat.addActionListener(e -> {
        chat_restaurante chat = new chat_restaurante(ipServer);
        chat.setVisible(true);
        
        chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        });

        header.add(title, BorderLayout.WEST);
        header.add(btnDespachar, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        
        // PANEL PARA LOS BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(new Color(45, 45, 45));

        panelBotones.add(btnChat);
        panelBotones.add(btnDespachar);

        header.add(title, BorderLayout.WEST);
        header.add(panelBotones, BorderLayout.EAST);

        // --- PANEL DE ÓRDENES ---
        orders = new JPanel();
        orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
        orders.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(orders);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
        conectarAlServidorRespuesta();
        iniciarServidorInterno();
    }

    private void conectarAlServidorRespuesta() {
        new Thread(() -> {
            int esperaMs = 3000;
            
            while (corriendo) {
                try {
                    
                    socketAlServer = new Socket(ipServer, 5005);
                    salidaServer   = new ObjectOutputStream(socketAlServer.getOutputStream());
                    salidaServer.writeObject("RESTAURANTE"); // identificador
                    salidaServer.flush();
                    System.out.println("Conexión con el servidor establecida.");

                    while (corriendo && !socketAlServer.isClosed()) {
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {
                    System.err.println("Sin conexión al servidor, reintentando en 3s... LA IP ASIGNADA");
                    salidaServer   = null;
                    socketAlServer = null;

                    try {
                        Thread.sleep(esperaMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }).start();
    }

    private void iniciarServidorInterno() {
        new Thread(() -> {
            try {
                ServerSocket servidorRestaurante = new ServerSocket(5006); // puerto del restaurante
                while (true) {
                    Socket cliente = servidorRestaurante.accept();
                    ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                    PedidoClass pedido = (PedidoClass) entrada.readObject();

                    colaLocal.offer(pedido);
                    actualizarInterfaz();

                    cliente.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void despacharSiguiente() {
        PedidoClass pedido = colaLocal.poll();
        if (pedido != null) {
            pedido.setEstado(true);
            enviarEstadoAlServer(pedido);
            actualizarInterfaz();
            System.out.println("Pedido " + pedido.getId() + " despachado.");
        } else {
            JOptionPane.showMessageDialog(this, "No hay pedidos pendientes.");
        }
    }

    private void actualizarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            orders.removeAll();

            java.util.List<PedidoClass> listaVisual = new java.util.ArrayList<>(colaLocal);
            listaVisual.sort((p1, p2) -> {
                if (p1.isVIP() && !p2.isVIP()) return -1;
                if (!p1.isVIP() && p2.isVIP()) return 1;
                return 0;
            });

            for (PedidoClass p : listaVisual) {
                orders.add(buildSimpleRow(p));
            }

            orders.revalidate();
            orders.repaint();
        });
    }

    private JPanel buildSimpleRow(PedidoClass pedido) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        row.setBackground(pedido.isVIP() ? new Color(255, 250, 230) : Color.WHITE); // fondo dorado para VIP
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        String prefijo = pedido.isVIP() ? "⭐ VIP - " : "";
        StringBuilder platosText = new StringBuilder("<html><b>" + prefijo + "ID: #" + pedido.getId() + " - " + pedido.getCliente() + "</b><br>");
        for (Orden o : pedido.getComidas()) {
            platosText.append("- ").append(o.getNombreComida()).append(" ");
        }
        platosText.append("</html>");

        row.add(new JLabel(platosText.toString()), BorderLayout.CENTER);
        
        return row;
    }

    private void enviarEstadoAlServer(PedidoClass pedido) {
        try {
            if (salidaServer != null) {
                salidaServer.writeObject(pedido);
                salidaServer.flush();
                salidaServer.reset();
            }
        } catch (Exception ex) {
            System.err.println("No se pudo conectar al servidor para avisar.");
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RestaurantUI("localhost"));
    }
}