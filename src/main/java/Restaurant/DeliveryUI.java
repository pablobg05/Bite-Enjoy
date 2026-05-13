package Restaurant;

import Clases.PedidoClass;
import Clases.Orden;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;
import Restaurant.chat_delivery;

public class DeliveryUI extends JFrame {
    
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
    
//    private final String SERVER_IP = "localhost"; // --------------------------------------------------------------------------------------------------------->IP DEL SERVER

    public DeliveryUI(String ipServerRecibida){
        this.ipServer= ipServerRecibida;
        setTitle("Pantalla de Delivery");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        addWindowListener(new java.awt.event.WindowAdapter() { //Igual para que se detenga la conección al cerrar la ventana
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
        
        JLabel title = new JLabel("Pedidos de Delivery");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        
      // --- BOTÓN PARA DESPACHAR EL SIGUIENTE EN LA COLA ---
        JButton btnDespachar = new JButton("Pedido Listo");
        btnDespachar.addActionListener(e -> despacharSiguiente());
        
        header.add(title, BorderLayout.WEST);
        header.add(btnDespachar, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        
        // --- BOTÓN CHAT ---
        JButton btnChat = new JButton("Chat");

        btnChat.addActionListener(e -> {

        chat_delivery chat = new chat_delivery(ipServer);

         chat.setVisible(true);

         chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        });
        // PANEL DE BOTONES 
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(45, 45, 45));

        panelBotones.add(btnChat);
        panelBotones.add(btnDespachar);

        // AGREGAR AL HEADER
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
    
    private void conectarAlServidorRespuesta() {//se conecta como cliente para avisar que ya se envió el pedido
        new Thread(() -> {
            int esperaMs = 3000;//tiempo entre intento e intento

            while (corriendo) {
                try {
                    socketAlServer = new Socket(ipServer, 5005);
                    salidaServer   = new ObjectOutputStream(socketAlServer.getOutputStream());
                    salidaServer.writeObject("DELIVERY"); // Estp es un identificador para el serverView
                    salidaServer.flush();                 
                    System.out.println("Conexión con el servidor establecida.");
                    
                    while (corriendo && !socketAlServer.isClosed()) {// Mantener vivo el hilo mientras la conexión esté activa
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {
                    System.err.println("Sin conexión al servidor, reintentando en 3s...");
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
    
    
    
    private void iniciarServidorInterno() { //recibe los pedidos entrantes, escucha en el puerto asignado
        new Thread(() -> {
            try {
                ServerSocket servidorDelivery = new ServerSocket(5004);
                while (true) {
                    Socket cliente = servidorDelivery.accept();
                    ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                    PedidoClass pedido = (PedidoClass) entrada.readObject();
                    
                    colaLocal.offer(pedido); //Agrega a la cola
                    actualizarInterfaz();
                    
                    cliente.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void despacharSiguiente() { //Saca de la cola
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

            // Convertimos a lista y ordenamos para que el VIP salga arriba en la pantalla
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
    
    private JPanel buildSimpleRow(PedidoClass pedido){
        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        StringBuilder platosText = new StringBuilder("<html><b>ID: #" + pedido.getId() + " - " + pedido.getCliente() + "</b><br>");
        for (Orden o : pedido.getComidas()) {
            platosText.append("- ").append(o.getNombreComida()).append(" ");
        }
        platosText.append("</html>");
        
        row.add(new JLabel(platosText.toString()), BorderLayout.CENTER);
        return row;
    }

    private void enviarEstadoAlServer(PedidoClass pedido) { //esta onda es para que se prenda el led del server, no me la vayan a mover xd
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
        SwingUtilities.invokeLater(() -> new DeliveryUI("localhost"));
    }
}