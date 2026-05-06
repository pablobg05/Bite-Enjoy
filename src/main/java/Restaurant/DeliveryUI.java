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

public class DeliveryUI extends JFrame {
    
    private JPanel orders;
    private ObjectOutputStream salidaServer;
    private Socket socketAlServer;
    
    // --- NUEVA ESTRUCTURA DE COLA ---
    private Queue<PedidoClass> colaLocal = new LinkedList<>();
    
    private final String SERVER_IP = "localhost"; // IP del Servidor (PC 2) para devolver los datos ---------------------------------------------------------

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
        
        // --- BOTÓN PARA DESPACHAR EL SIGUIENTE EN LA COLA ---
        JButton btnDespachar = new JButton("Pedido Listo");
        btnDespachar.addActionListener(e -> despacharSiguiente());
        
        header.add(title, BorderLayout.WEST);
        header.add(btnDespachar, BorderLayout.EAST);
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
            try {
                socketAlServer = new Socket(SERVER_IP, 5005);
                salidaServer = new ObjectOutputStream(socketAlServer.getOutputStream());
                System.out.println("Conexión persistente con el servidor establecida.");
            } catch (Exception e) {
                System.err.println("Error conectando al servidor: " + e.getMessage());
            }
        }).start();
    }
    
    private void iniciarServidorInterno() { 
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

    private void actualizarInterfaz() { //va actualizando los cambios 
        SwingUtilities.invokeLater(() -> {
            orders.removeAll();
            for (PedidoClass p : colaLocal) {
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
        SwingUtilities.invokeLater(() -> new DeliveryUI());
    }
}