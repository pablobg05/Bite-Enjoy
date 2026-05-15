package Main;

import Clases.ArbolBPlus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Restaurant.DeliveryUI;
import Restaurant.RestaurantUI;
import Restaurant.CafeUI; // IMPORTANTE: Agregué la importación del Cafe
import Server.ServerView;
import Pedido.Pedido;   

public class ProyectoRestaurante extends JFrame{
    
    // Variable para guardar la IP que el usuario ingrese en el main
    private static String ipServer = "localhost";
    private static String ipDelivery = "localhost";
    private static String ipRestaurante = "localhost";
    private static String ipCafe = "localhost";
    
    public ProyectoRestaurante(){
        //-- Titulo (lo que se mira en el borde) y tamano --
        setTitle("Bite & Enjoy");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //-- El layout del JFrame es usando los bordes
        setLayout(new BorderLayout());
        
        //-- Header (La parte de arribita) --
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 45, 45));
        header.setBorder(
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
        );
        
        JLabel title = new JLabel("Bite & Enjoy - IP: " + ipServer); // Ahora muestra la IP arriba
        title.setForeground(Color.WHITE);
        
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        
        //-- Todo lo importante --
        JPanel main = new JPanel(new BorderLayout());
        
        //-- El layout es en grid, osea que se ponen en una tipo matriz --
        JPanel grid = new JPanel();
        grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS)); // apila las dos filas verticalmente
        grid.setBackground(Color.WHITE);

        // -- Fila de arriba: 2 botones grandes (Pedidos y Server) --
        JPanel filaArriba = new JPanel(new GridLayout(1, 2, 20, 0));
        filaArriba.setBackground(Color.WHITE);

        // -- Fila de abajo: 3 botones mas chicos (Restaurante, Cafeteria, Delivery) --
        JPanel filaAbajo = new JPanel(new GridLayout(1, 3, 20, 0));
        filaAbajo.setBackground(Color.WHITE);
        
        // ---------- Boton restaurante ----------
        JLabel res = new JLabel("Restaurante"); // Label
        res.setHorizontalAlignment(SwingConstants.CENTER); /* Hace que se quede en el centro siempre */
        // -- Panel para poner la label --
        JPanel topR = new JPanel(new BorderLayout());
        topR.setBackground(new Color(150, 150, 150));
        topR.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topR.add(res, BorderLayout.CENTER); // se agrega la label al panel
        
        // Imagen (más pequeña porque va en la fila de abajo)
        ImageIcon rawRes = new ImageIcon(getClass().getResource("/restaurante.png"));
        Image scaledRes = rawRes.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        JLabel lblIRes = new JLabel(new ImageIcon(scaledRes));
        lblIRes.setHorizontalAlignment(SwingConstants.CENTER);
        
        // -- Panel que servira como boton --
        JPanel btnRes = new JPanel(new BorderLayout());
        btnRes.setBackground(new Color(150, 150, 150));
        btnRes.add(topR, BorderLayout.NORTH); // se agrega el panel de la label
        btnRes.add(lblIRes, BorderLayout.CENTER); // se agrega el panel de la imagen
        // -- Logica para que funcione como boton
        btnRes.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){ // al poner el cursor
                    btnRes.setBackground(new Color(98, 98, 98));
                    btnRes.repaint();
                    topR.setBackground(new Color(98, 98, 98));
                    topR.repaint();
                    lblIRes.setBackground(new Color(98, 98, 98));
                    lblIRes.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){ // al quitar el cursor
                    btnRes.setBackground(new Color(150, 150, 150));
                    btnRes.repaint();
                    topR.setBackground(new Color(150, 150, 150));
                    topR.repaint();
                    lblIRes.setBackground(new Color(150, 150, 150));
                    lblIRes.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){ // al dar click
                    // Pasamos la IP al constructor para que sepa a donde conectarse
                    RestaurantUI res = new RestaurantUI(ipServer); 
                    res.setVisible(true);
                }
        });
        
        // ---------- Boton Delivery ----------
        // -- Lo mismo que para el de restaurante --
        JLabel del = new JLabel("Delivery");
        del.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topD = new JPanel(new BorderLayout());
        topD.setBackground(new Color(150, 150, 150));
        topD.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topD.add(del, BorderLayout.CENTER);
        
        ImageIcon rawDel = new ImageIcon(getClass().getResource("/delivery.png"));
        
        // Imagen más pequeña porque va en la fila de abajo
        Image scaledDel = rawDel.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        JLabel lblIDel = new JLabel(new ImageIcon(scaledDel));
        lblIDel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnDel = new JPanel(new BorderLayout());
        btnDel.setBackground(new Color(150, 150, 150));
        btnDel.add(topD, BorderLayout.NORTH);
        btnDel.add(lblIDel, BorderLayout.CENTER);
        btnDel.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnDel.setBackground(new Color(98, 98, 98));
                    btnDel.repaint();
                    topD.setBackground(new Color(98, 98, 98));
                    topD.repaint();
                    lblIDel.setBackground(new Color(98, 98, 98));
                    lblIDel.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnDel.setBackground(new Color(150, 150, 150));
                    btnDel.repaint();
                    topD.setBackground(new Color(150, 150, 150));
                    topD.repaint();
                    lblIDel.setBackground(new Color(150, 150, 150));
                    lblIDel.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    DeliveryUI del = new DeliveryUI(ipServer);
                    del.setVisible(true);
                }
        });
        
        // ---------- Boton Server ----------
        JLabel ser = new JLabel("Server");
        ser.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topS = new JPanel(new BorderLayout());
        topS.setBackground(new Color(150, 150, 150));
        topS.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topS.add(ser, BorderLayout.CENTER);
        
        ImageIcon rawSer = new ImageIcon(getClass().getResource("/server.png"));
        
        Image scaledSer = rawSer.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        JLabel lblISer = new JLabel(new ImageIcon(scaledSer));
        lblISer.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnSer = new JPanel(new BorderLayout());
        btnSer.setBackground(new Color(150, 150, 150));
        btnSer.add(topS, BorderLayout.NORTH);
        btnSer.add(lblISer, BorderLayout.CENTER);
        btnSer.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnSer.setBackground(new Color(98, 98, 98));
                    btnSer.repaint();
                    topS.setBackground(new Color(98, 98, 98));
                    topS.repaint();
                    lblISer.setBackground(new Color(98, 98, 98));
                    lblISer.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnSer.setBackground(new Color(150, 150, 150));
                    btnSer.repaint();
                    topS.setBackground(new Color(150, 150, 150));
                    topS.repaint();
                    lblISer.setBackground(new Color(150, 150, 150));
                    lblISer.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    ServerView ser = new ServerView(ipDelivery, ipRestaurante, ipCafe);
                    ser.setVisible(true);
                }
        });
        
        // ---------- Boton Pedidos ----------
        JLabel ped = new JLabel("Pedidos");
        ped.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topP = new JPanel(new BorderLayout());
        topP.setBackground(new Color(150, 150, 150));
        topP.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topP.add(ped, BorderLayout.CENTER);
        
        ImageIcon rawPed = new ImageIcon(getClass().getResource("/pedidos.png"));
        
        Image scaledPed = rawPed.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        JLabel lblIPed = new JLabel(new ImageIcon(scaledPed));
        lblIPed.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnPed = new JPanel(new BorderLayout());
        btnPed.setBackground(new Color(150, 150, 150));
        btnPed.add(topP, BorderLayout.NORTH);
        btnPed.add(lblIPed, BorderLayout.CENTER);
        btnPed.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnPed.setBackground(new Color(98, 98, 98));
                    btnPed.repaint();
                    topP.setBackground(new Color(98, 98, 98));
                    topP.repaint();
                    lblIPed.setBackground(new Color(98, 98, 98));
                    lblIPed.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnPed.setBackground(new Color(150, 150, 150));
                    btnPed.repaint();
                    topP.setBackground(new Color(150, 150, 150));
                    topP.repaint();
                    lblIPed.setBackground(new Color(150, 150, 150));
                    lblIPed.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    Pedido ped = new Pedido(ipServer);
                    ped.setVisible(true);
                }
        });

        // ---------- Boton Cafeteria ----------
        JLabel caf = new JLabel("Cafetería");
        caf.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topC = new JPanel(new BorderLayout());
        topC.setBackground(new Color(150, 150, 150));
        topC.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topC.add(caf, BorderLayout.CENTER);

        // Imagen más pequeña porque va en la fila de abajo
        ImageIcon rawCaf = new ImageIcon(getClass().getResource("/cafeteria.png")); // Cambia el recurso cuando tengas el icono de cafe
        Image scaledCaf = rawCaf.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        JLabel lblICaf = new JLabel(new ImageIcon(scaledCaf));
        lblICaf.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel btnCaf = new JPanel(new BorderLayout());
        btnCaf.setBackground(new Color(150, 150, 150));
        btnCaf.add(topC, BorderLayout.NORTH);
        btnCaf.add(lblICaf, BorderLayout.CENTER);
        btnCaf.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnCaf.setBackground(new Color(98, 98, 98));
                    btnCaf.repaint();
                    topC.setBackground(new Color(98, 98, 98));
                    topC.repaint();
                    lblICaf.setBackground(new Color(98, 98, 98));
                    lblICaf.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e){
                    btnCaf.setBackground(new Color(150, 150, 150));
                    btnCaf.repaint();
                    topC.setBackground(new Color(150, 150, 150));
                    topC.repaint();
                    lblICaf.setBackground(new Color(150, 150, 150));
                    lblICaf.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    CafeUI caf = new CafeUI(ipServer);
                    caf.setVisible(true);
                }
        });

        // -- Se agregan todos los botones al grid
        // Fila de arriba: Pedidos y Server
        filaArriba.add(btnPed);
        filaArriba.add(btnSer);
        // Fila de abajo: Restaurante, Cafeteria y Delivery
        filaAbajo.add(btnRes);
        filaAbajo.add(btnCaf);
        filaAbajo.add(btnDel);

        grid.add(filaArriba);
        grid.add(Box.createVerticalStrut(20)); // el gap de 20px entre filas
        grid.add(filaAbajo);

        // -- Se agrega el grid al main layout
        main.add(grid, BorderLayout.CENTER);
        
        // -- Agregue esto para que quede separado de los izquierda, tal vez lo arregle despues si se puede
        JPanel west = new JPanel(new BorderLayout());
        west.setBackground(Color.WHITE);
        west.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(west, BorderLayout.WEST);
        
        JPanel east = new JPanel(new BorderLayout());
        east.setBackground(Color.WHITE);
        east.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(east, BorderLayout.EAST);
        
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(Color.WHITE);
        north.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(north, BorderLayout.NORTH);
        
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(Color.WHITE);
        south.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(south, BorderLayout.SOUTH);
        
        // se agrega todo al JFrame
        add(main, BorderLayout.CENTER);
        
        // Se pone visible
        setVisible(true);
    }
    
    
    
private static java.util.Properties cargarConfig() { // oara cargar el config que esta en resources xd
    java.util.Properties props = new java.util.Properties();
    try (java.io.InputStream in = ProyectoRestaurante.class
            .getResourceAsStream("/config.properties")) {
        if (in != null) {
            props.load(in);
        } else {
            System.out.println("config.properties no encontrado, usando defaults.");
        }
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
    return props;
}

    
    
    
    
private static void mostrarConfiguracionRed() {
        JDialog dialog = new JDialog((Frame)null, "Configuración de Red", true);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(240, 240, 240));

        // --- Panel del Título ---
        JPanel pnlTitulo = new JPanel();
        pnlTitulo.setBackground(new Color(45, 45, 45));
        JLabel lblTitulo = new JLabel("Configuración de Red");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlTitulo.add(lblTitulo);

        // --- Panel de Campos (Formulario) ---
        JPanel pnlCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlCampos.setBackground(Color.WHITE);

        JTextField txtServer = new JTextField(15);
        JTextField txtDelivery = new JTextField(15);
        JTextField txtRest = new JTextField(15);
        JTextField txtCafe = new JTextField(15);

        pnlCampos.add(new JLabel("IP Server:")); pnlCampos.add(txtServer);
        pnlCampos.add(new JLabel("IP Delivery:")); pnlCampos.add(txtDelivery);
        pnlCampos.add(new JLabel("IP Restaurante:")); pnlCampos.add(txtRest);
        pnlCampos.add(new JLabel("IP Café:")); pnlCampos.add(txtCafe);

        // --- Panel de Botones ---
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnAceptar = new JButton("ACEPTAR");
        JButton btnLocal = new JButton("LOCALHOST");
        JButton btnDefault = new JButton("DEFAULT");

        // Estilo de botones
        btnAceptar.setBackground(new Color(60, 179, 113)); // Verde
        btnAceptar.setForeground(Color.WHITE);
        btnLocal.setBackground(new Color(70, 130, 180)); // Azul
        btnLocal.setForeground(Color.WHITE);
        btnDefault.setBackground(new Color(105, 105, 105)); // Gris oscuro
        btnDefault.setForeground(Color.WHITE);

        // Lógica ACEPTAR
        btnAceptar.addActionListener(e -> {
            if (txtServer.getText().isEmpty() || txtDelivery.getText().isEmpty() || 
                txtRest.getText().isEmpty() || txtCafe.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Debes llenar todas las IPs para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ipServer = txtServer.getText().trim();
                ipDelivery = txtDelivery.getText().trim();
                ipRestaurante = txtRest.getText().trim();
                ipCafe = txtCafe.getText().trim();
                dialog.dispose();
            }
        });

        // Lógica LOCALHOST
        btnLocal.addActionListener(e -> {
            ipServer = "localhost";
            ipDelivery = "localhost";
            ipRestaurante = "localhost";
            ipCafe = "localhost";
            dialog.dispose();
        });

        // Lógica DEFAULT
        btnDefault.addActionListener(e -> {
            java.util.Properties cfg = cargarConfig();
            ipServer      = cfg.getProperty("ip.server",      "localhost");
            ipRestaurante = cfg.getProperty("ip.restaurante",  "localhost");
            ipCafe        = cfg.getProperty("ip.cafe",         "localhost");
            ipDelivery    = cfg.getProperty("ip.delivery",     "localhost");
            dialog.dispose();
        });
        pnlBotones.add(btnAceptar);
        pnlBotones.add(btnLocal);
        pnlBotones.add(btnDefault);

        // Agregar todo al diálogo
        dialog.add(pnlTitulo, BorderLayout.NORTH);
        dialog.add(pnlCampos, BorderLayout.CENTER);
        dialog.add(pnlBotones, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centrar en pantalla
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        mostrarConfiguracionRed();

        SwingUtilities.invokeLater(() -> {
            ProyectoRestaurante ui = new ProyectoRestaurante();
        });
    }
}