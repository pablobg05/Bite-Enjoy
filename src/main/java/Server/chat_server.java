
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class chat_server extends javax.swing.JFrame {
    //variables pedido
    public static ServerSocket ss;
    public static Socket s;
    public static DataInputStream dis;
    public static DataOutputStream dout;
    
    //variables delivery
    
    public static ServerSocket ssDelivery;
    public static Socket sDelivery;
    public static DataInputStream disDelivery;
    public static DataOutputStream doutDelivery;

    //variables del restaurante 
    
    public static ServerSocket ssRestaurante;
    public static Socket sRestaurante;
    public static DataInputStream disRestaurante;
    public static DataOutputStream doutRestaurante;
 
    public chat_server() {
        initComponents();
        
            // Cerrar conexión al cerrar ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                cerrarConexion();
            }
        });
        
        iniciarServidor();
        iniciarServidorDelivery();
        iniciarServidorRestaurante();
    }
    //conexión del chat de pedido
     private void iniciarServidor() {
    new Thread(() -> {
        try {
            ss = new ServerSocket();
            ss.setReuseAddress(true);
            ss.bind(new java.net.InetSocketAddress(1201));

            msg_area.append("\nEsperando conexión pedido...");

            while (true) { 
                s = ss.accept();

                msg_area.append("\nPedido conectado");

                dis = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                new Thread(() -> {
                    try {
                        String msgin = "";
                        while (!msgin.equals("exit")) {
                            msgin = dis.readUTF();
                            msg_area.append("\nPedido: " + msgin);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
}
     //conexión del chat del servidor 
         private void iniciarServidorDelivery() {

        new Thread(() -> {

            try {

                ssDelivery = new ServerSocket();
                ssDelivery.setReuseAddress(true);
                ssDelivery.bind(new java.net.InetSocketAddress(3000));

                msg_area.append("\nEsperando conexión de Delivery ...");

                while (true) {

                    sDelivery = ssDelivery.accept();

                    msg_area.append("\nDelivery conectado");

                    disDelivery = new DataInputStream(sDelivery.getInputStream());
                    doutDelivery = new DataOutputStream(sDelivery.getOutputStream());

                    new Thread(() -> {

                        try {

                            String msgin = "";

                            while (!msgin.equals("exit")) {

                                msgin = disDelivery.readUTF();

                                msg_area.append("\nDelivery: " + msgin);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }).start();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        msg_send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Server");

        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_send.setBackground(new java.awt.Color(102, 102, 255));
        msg_send.setForeground(new java.awt.Color(255, 255, 255));
        msg_send.setText("Enviar");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(msg_send)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msg_text)
                    .addComponent(msg_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed
       
           try {

        String msg = msg_text.getText();

        if (msg.trim().isEmpty()) {
            return;
        }

        boolean enviado = false;

        // PEDIDO
        if (dout != null) {
            dout.writeUTF(msg);
            enviado = true;
        }

        // DELIVERY
        if (doutDelivery != null) {
            doutDelivery.writeUTF(msg);
            enviado = true;
        }


        // RESTAURANTE
        if (doutRestaurante != null) {
            doutRestaurante.writeUTF(msg);
            enviado = true;
        }

        if (!enviado) {
            msg_area.append("\n[ERROR] No hay clientes conectados");
            return;
        }

        msg_area.append("\nYo: " + msg);

        msg_text.setText("");

    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }//GEN-LAST:event_msg_sendActionPerformed

    private void iniciarServidorRestaurante() {

    new Thread(() -> {

        try {

            ssRestaurante = new ServerSocket();
            ssRestaurante.setReuseAddress(true);
            ssRestaurante.bind(new java.net.InetSocketAddress(5000));

            msg_area.append("\nEsperando Conexión de Restaurante...");

            while (true) {

                sRestaurante = ssRestaurante.accept();

                msg_area.append("\nRestaurante conectado");

                disRestaurante = new DataInputStream(sRestaurante.getInputStream());
                doutRestaurante = new DataOutputStream(sRestaurante.getOutputStream());

                new Thread(() -> {

                    try {

                        String msgin = "";

                        while (!msgin.equals("exit")) {

                            msgin = disRestaurante.readUTF();

                            msg_area.append("\nRestaurante: " + msgin);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }).start();
}
    
    public static void cerrarConexion() {
    try {

        // PEDIDO
        if (dis != null) dis.close();
        if (dout != null) dout.close();
        if (s != null) s.close();
        if (ss != null) ss.close();

        // DELIVERY
        if (disDelivery != null) disDelivery.close();
        if (doutDelivery != null) doutDelivery.close();
        if (sDelivery != null) sDelivery.close();
        if (ssDelivery != null) ssDelivery.close();

        // RESTAURANTE
        if (disRestaurante != null) disRestaurante.close();
        if (doutRestaurante != null) doutRestaurante.close();
        if (sRestaurante != null) sRestaurante.close();
        if (ssRestaurante != null) ssRestaurante.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public static void main(String args[]) {
 
           java.awt.EventQueue.invokeLater(() -> {
            new chat_server().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    // End of variables declaration//GEN-END:variables
}
