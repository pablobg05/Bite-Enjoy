
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class chat_server extends javax.swing.JFrame {

    public static ServerSocket ss;
    public static Socket s;
    public static DataInputStream dis;
    public static DataOutputStream dout;

 
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
    }
    
     private void iniciarServidor() {
    new Thread(() -> {
        try {
            ss = new ServerSocket();
            ss.setReuseAddress(true);
            ss.bind(new java.net.InetSocketAddress(1201));

            msg_area.append("\nEsperando cliente...");

            while (true) { 
                s = ss.accept();

                msg_area.append("\nCliente conectado");

                dis = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                new Thread(() -> {
                    try {
                        String msgin = "";
                        while (!msgin.equals("exit")) {
                            msgin = dis.readUTF();
                            msg_area.append("\nCliente: " + msgin);
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
            if (dout == null) {
                msg_area.append("\n[ERROR] No hay cliente conectado");
                return;
            }

            String msg = msg_text.getText();
            dout.writeUTF(msg);
            msg_area.append("\nYo: " + msg);
            msg_text.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
         
        
    }//GEN-LAST:event_msg_sendActionPerformed

    
    public static void cerrarConexion() {
    try {
        if (dis != null) dis.close();
        if (dout != null) dout.close();
        if (s != null) s.close();
        if (ss != null) ss.close(); // solo en server
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
