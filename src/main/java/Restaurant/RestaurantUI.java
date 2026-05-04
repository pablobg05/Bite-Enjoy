package Restaurant;

import Clases.PedidoClass;
import Clases.Orden;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;

public class RestaurantUI extends JFrame {
    
    private JPanel orders;
    // Server IP
    private final String SERVER_IP = "localhost";
    public RestaurantUI(){
        setTitle("Restaurante");
        setSize(700,500);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        
        setLayout(new BorderLayout());
        setVisible(true);
        
        JPanel header = new JPanel(
          new BorderLayout()
        );
        header.setBackground(new Color(45, 45, 45));
        header.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 16, 12, 16
                )
        );
        
        JLabel title = new JLabel(
                "Restaurante"
        );
        title.setForeground(Color.WHITE);
        
        JLabel status = new JLabel("● Connected");
        status.setForeground(new Color(100,220,100));
        
        header.add(title, BorderLayout.WEST);
        header.add(status, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        
        orders = new JPanel();
        orders.setLayout(
                new BoxLayout(
                        orders, BoxLayout.Y_AXIS
                )
        );
        orders.setBackground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(
                orders
        );
        scroll.setBorder(null);
        
        add(scroll, BorderLayout.CENTER);
    }
    
    private JPanel buildRow(PedidoClass pedido){
        JPanel row = new JPanel(
                new BorderLayout()
        );
        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10, 14, 10, 14)
                )
        );
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(
                Integer.MAX_VALUE, 64
        ));
        
        StringBuilder platosText = new StringBuilder("<html><b>ID: #" + pedido.getId() + " - " + pedido.getCliente() + "</b><br>");
        platosText.append("<font color='#555555'>");
        for (Orden o : pedido.getComidas()) {
            platosText.append("- ").append(o.getNombreComida()).append("<br>");
        }
        platosText.append("</font></html>");
        
        JLabel info = new JLabel(platosText.toString());
        
        JButton btn = new JButton("Listo");
        
        JLabel badge = new JLabel("• Listo");
        badge.setForeground(new Color(60,160,80));
        
        btn.addActionListener(e -> {
            // Cambiar boton por label verde
            row.remove(btn);
            row.add(badge, BorderLayout.EAST);
            
            // Siempre agregar cuando se hacen cambios así
            row.revalidate();
            row.repaint();
            
            // Aqui se va a mandar el mensaje al server después
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
            try (Socket socket = new Socket(SERVER_IP, 5004)) {
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

      // launch the window — always use
      // invokeLater here too
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantUI ui = new RestaurantUI();
        });
    }
}