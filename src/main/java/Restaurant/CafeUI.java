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
// Importamos el chat específico de café
import Restaurant.chat_cafe;

public class CafeUI extends JFrame {
    
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
    
    public CafeUI(String ipServerRecibida){
        this.ipServer = ipServerRecibida;
        setTitle("Pantalla de Café y Postres");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        addWindowListener(new java.awt.event.WindowAdapter() { 
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                corriendo = false;
                try { if (socketAlServer != null) socketAlServer.close(); } catch (Exception ex) {}
            }
        });
        
        // --- HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 45, 45)); 
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        
        JLabel title = new JLabel("Cafetería");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        
        // --- BOTÓN PARA DESPACHAR EL SIGUIENTE EN LA COLA ---
        JButton btnDespachar = new JButton("Pedido Listo");
        btnDespachar.addActionListener(e -> despacharSiguiente());
        
        // --- BOTÓN CHAT ---
        JButton btnChat = new JButton("Chat");
        btnChat.addActionListener(e -> {
            chat_cafe chat = new chat_cafe(ipServer);
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
        add(header, BorderLayout.NORTH);
        
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
                    // IDENTIFICADOR PARA EL SERVER
                    salidaServer.writeObject("CAFE"); 
                    salidaServer.flush();                 
                    System.out.println("Café conectado al servidor.");
                    
                    while (corriendo && !socketAlServer.isClosed()) {
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.err.println("Sin conexión al servidor, reintentando en 3s...");
                    salidaServer   = null;
                    socketAlServer = null;
                    try { Thread.sleep(esperaMs); } catch (InterruptedException ie) { return; }
                }
            }
        }).start();
    }
    
    private void iniciarServidorInterno() { 
        new Thread(() -> {
            try {
                // PUERTO 5007 para no chocar con el 5004 o 5006
                ServerSocket servidorCafe = new ServerSocket(5007);
                while (true) {
                    Socket cliente = servidorCafe.accept();
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
            System.out.println("Pedido de café " + pedido.getId() + " despachado.");
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
        SwingUtilities.invokeLater(() -> new CafeUI("localhost"));
    }
}