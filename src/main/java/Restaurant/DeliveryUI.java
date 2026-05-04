package Restaurant;

import Clases.PedidoClass;
import Clases.Orden;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class DeliveryUI extends JFrame {
    
    private JPanel orders;
    // IP del Servidor (PC 2) para devolver los datos
    private final String SERVER_IP = "localhost"; 

    public DeliveryUI(){
        setTitle("Pantalla de Delivery");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // --- HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 45, 45));
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        
        JLabel title = new JLabel("Pedidos de Delivery");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel status = new JLabel("● Servidor Escuchando");
        status.setForeground(new Color(100,220,100));
        
        header.add(title, BorderLayout.WEST);
        header.add(status, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        
        // --- PANEL DE ÓRDENES ---
        orders = new JPanel();
        orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
        orders.setBackground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(orders);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
        
        setVisible(true);
        iniciarServidorInterno();
    }
    
    private void iniciarServidorInterno() {
        new Thread(() -> {
            try {
                ServerSocket servidorDelivery = new ServerSocket(5004);
                while (true) {
                    Socket cliente = servidorDelivery.accept();
                    ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                    PedidoClass pedido = (PedidoClass) entrada.readObject();
                    addOrder(pedido);
                    cliente.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private JPanel buildRow(PedidoClass pedido){
        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        row.setBackground(Color.WHITE);
        
        // Ajustamos el tamaño máximo para que no se deforme la viñeta
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        // --- CONSTRUIR LISTADO DE PLATOS ---
        StringBuilder platosText = new StringBuilder("<html><b>ID: #" + pedido.getId() + " - " + pedido.getCliente() + "</b><br>");
        platosText.append("<font color='#555555'>");
        for (Orden o : pedido.getComidas()) {
            platosText.append("- ").append(o.getNombreComida()).append("<br>");
        }
        platosText.append("</font></html>");
        
        JLabel info = new JLabel(platosText.toString());
        
        // --- TU LÓGICA ORIGINAL DEL BOTÓN ---
        JButton btn = new JButton("Listo");
        
        JLabel badge = new JLabel("• Listo");
        badge.setForeground(new Color(60,160,80));
        
        btn.addActionListener(e -> {
            // 1. Cambiar boton por label verde (Tu lógica original)
            row.remove(btn);
            row.add(badge, BorderLayout.EAST);
            
            // 2. Siempre agregar cuando se hacen cambios así
            row.revalidate();
            row.repaint();
            
            // 3. Mandar el mensaje al server (Lo nuevo que agregamos)
            pedido.setEstado(true);
            enviarEstadoAlServer(pedido);
            
            System.out.println("Pedido " + pedido.getId() + " marcado como LISTO");
        });
        
        row.add(info, BorderLayout.WEST);
        row.add(btn, BorderLayout.EAST);
        return row;
    }

    private void enviarEstadoAlServer(PedidoClass pedido) {
        new Thread(() -> {
            try (Socket socket = new Socket(SERVER_IP, 5003)) {
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                salida.writeObject(pedido);
                salida.flush();
            } catch (Exception ex) {
                System.err.println("No se pudo conectar al servidor para avisar.");
            }
        }).start();
    }
    
    public void addOrder(PedidoClass pedido) {
        SwingUtilities.invokeLater(() -> {
          JPanel row = buildRow(pedido);
          orders.add(row, 0); 
          orders.revalidate();
          orders.repaint();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeliveryUI());
    }
}