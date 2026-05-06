
package Server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import Clases.PedidoClass;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;


public class ServerView extends javax.swing.JFrame {
    // --------------------ACA DECLARO LAS ESTRUCTURAS-------------------------
    public static Queue<PedidoClass> colaPedidos = new LinkedList<>(); //Pendientes por hacer
    public static List<PedidoClass> listaPedidos = new LinkedList<>(); //Para el historial
    public static Queue<PedidoClass> colaRestaurante = new LinkedList<>(); //Pendientes del restaurante
    public static Queue<PedidoClass> colaDelivery = new LinkedList<>(); //Pendientes del delivery

    public ServerView() {
        initComponents();
        etiquetas();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPendientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        PEDIDOS = new javax.swing.JLabel();
        lblPedidos = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PEDIDOS1 = new javax.swing.JLabel();
        lblDelivery = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        PEDIDOS2 = new javax.swing.JLabel();
        lblRestaurante = new javax.swing.JLabel();
        lblPcPedidos = new javax.swing.JLabel();
        lblPcDelivery = new javax.swing.JLabel();
        lblPcRestaurante = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PEDIDOS PENDIENTES");

        tblPendientes.setBackground(new java.awt.Color(204, 204, 255));
        tblPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TABLA PENDIENTES"
            }
        ));
        tblPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPendientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPendientes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(87, 87, 87))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 440, 390));

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        tblHistorial.setBackground(new java.awt.Color(204, 204, 255));
        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TABLA HISTORIAL"
            }
        ));
        tblHistorial.setGridColor(new java.awt.Color(204, 204, 255));
        tblHistorial.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tblHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHistorialMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblHistorial);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HISTORIAL");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel2)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel5.setBackground(new java.awt.Color(0, 0, 102));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PEDIDOS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PEDIDOS.setForeground(new java.awt.Color(255, 255, 255));
        PEDIDOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PEDIDOS.setText("PEDIDOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(PEDIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(lblPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PEDIDOS))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 90, 90));

        jPanel6.setBackground(new java.awt.Color(0, 0, 102));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PEDIDOS1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PEDIDOS1.setForeground(new java.awt.Color(255, 255, 255));
        PEDIDOS1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PEDIDOS1.setText("DELIVERY");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(PEDIDOS1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDelivery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PEDIDOS1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 90, 90));

        jPanel7.setBackground(new java.awt.Color(0, 0, 102));
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PEDIDOS2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PEDIDOS2.setForeground(new java.awt.Color(255, 255, 255));
        PEDIDOS2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PEDIDOS2.setText("RESTAURANTE");

        lblRestaurante.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(PEDIDOS2)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRestaurante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lblRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PEDIDOS2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 90, 90));

        lblPcPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redCircle.png"))); // NOI18N
        jPanel1.add(lblPcPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 20, 20));

        lblPcDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redCircle.png"))); // NOI18N
        jPanel1.add(lblPcDelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 20, 20));

        lblPcRestaurante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redCircle.png"))); // NOI18N
        jPanel1.add(lblPcRestaurante, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 20, 20));

        jPanel8.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 10, 540));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 860, 540));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SERVIDOR GENERAL");

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Chat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHistorialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistorialMousePressed
    int fila = tblHistorial.getSelectedRow(); //la fila que selecciono

        if (fila != -1 && listaPedidos != null) { //verifica que si se haya seleccionado una fila y que la lista no sea nula

            PedidoClass pedidoSeleccionado = listaPedidos.get(fila);//sacamos el pedido de la lista

            // --- CONSTRUIR EL MENSAJE DE LA COMIDA ---
            StringBuilder detalleComida = new StringBuilder();
            detalleComida.append("======= DETALLE DEL PEDIDO =======\n");
            detalleComida.append("Cliente: ").append(pedidoSeleccionado.getCliente()).append("\n");
            detalleComida.append("Tipo:    ").append(pedidoSeleccionado.getTipo()).append("\n");

            if(pedidoSeleccionado.isEstado()==false){
                detalleComida.append("Estado:  ").append("EN PROCESO").append("\n"); 
            } else {
                detalleComida.append("Estado:  ").append("FINALIZADO").append("\n"); 
            }

            detalleComida.append("------------------------------------------\n");
            detalleComida.append("PRODUCTOS:\n");
            
            // Ya muestra Nombre + Precio gracias al cambio en la clase
            detalleComida.append(pedidoSeleccionado.getListaComidas()); 

            detalleComida.append("------------------------------------------\n");
            // AGREGAMOS EL TOTAL AL FINAL
            detalleComida.append("TOTAL PAGADO: Q").append(pedidoSeleccionado.getTotalPedido()).append("\n");
            detalleComida.append("==================================");

            javax.swing.JOptionPane.showMessageDialog(this, // --- MOSTRAR EL AVISO (JOptionPane) ---
                    detalleComida.toString(), 
                    "Detalles del Pedido #" + pedidoSeleccionado.getId(), 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }else {
            javax.swing.JOptionPane.showMessageDialog(this, "Mano, no seleccionaste nada jaja. \n Pilas pues");
        }
    }//GEN-LAST:event_tblHistorialMousePressed

    private void tblPendientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendientesMousePressed
        int fila = tblPendientes.getSelectedRow(); //la fila que selecciono

            if (fila != -1 && colaPedidos != null) { //verifica que si se haya seleccionado una fila y que la cola no sea nula

                // --- LA SOLUCIÓN PARA LA COLA ---
                // Convertimos la cola a un arreglo para poder usar el índice de la fila
                PedidoClass[] temporal = colaPedidos.toArray(new PedidoClass[0]);
                PedidoClass pedidoSeleccionado = temporal[fila]; // Ahora sí sacamos el pedido según la fila

                // --- CONSTRUIR EL MENSAJE DE LA COMIDA ---
                StringBuilder detalleComida = new StringBuilder();
                detalleComida.append("======= DETALLE DEL PEDIDO =======\n");
                detalleComida.append("Cliente: ").append(pedidoSeleccionado.getCliente()).append("\n");
                detalleComida.append("Tipo:    ").append(pedidoSeleccionado.getTipo()).append("\n");

                if (pedidoSeleccionado.isEstado() == false) {
                    detalleComida.append("Estado:  ").append("PENDIENTE").append("\n"); 
                } else {
                    detalleComida.append("Estado:  ").append("FINALIZADO").append("\n"); 
                }

                detalleComida.append("------------------------------------------\n");
                detalleComida.append("PRODUCTOS:\n");

                // Usamos el método que ya actualizamos en la clase PedidoClass 
                // que ahora imprime Nombre + Precio de cada Orden
                detalleComida.append(pedidoSeleccionado.getListaComidas()); 

                detalleComida.append("------------------------------------------\n");
                // AGREGAMOS EL TOTAL AL FINAL DEL DETALLE
                detalleComida.append("TOTAL A PAGAR: Q").append(pedidoSeleccionado.getTotalPedido()).append("\n");
                detalleComida.append("==================================");

                // --- MOSTRAR EL AVISO (JOptionPane) ---
                javax.swing.JOptionPane.showMessageDialog(this, 
                        detalleComida.toString(), 
                        "Detalles del Pedido #" + pedidoSeleccionado.getId(), 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Mano, no seleccionaste nada jaja. \n Pilas pues");
            }
    }//GEN-LAST:event_tblPendientesMousePressed

    // Para llamar al chat
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chat_server chat = new chat_server();
        
        chat.setVisible(true);
        chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jButton1ActionPerformed


    public static void main(String args[]) {
        ServerView vista = new ServerView(); // se "Instancia" la ventana

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vista.setVisible(true);
            }
        });

        // --- ESTO ES EL HILO PARA QUE EL SERVIDOR NO CONGELE LA VENTANA ---
        new Thread(() -> {
            // Creamos el socket de forma que permita reutilizar el puerto inmediatamente si reiniciamos
            try {
                ServerSocket servidor = new ServerSocket();
                servidor.setReuseAddress(true);
                servidor.bind(new java.net.InetSocketAddress(8080)); // ACA ESTA LA ONDA DEL PUERTOOOOOO ------------------

                System.out.println("Servidor escuchando en puerto 5003...");

                while (true) {
                    Socket cliente = servidor.accept(); // El servidor se queda esperando aquí a que el PC 1 se conecte

                    // Si el código llega aquí, es que el servidor se conectó con éxito
                    vista.actualizarEstadoServidor(vista.lblPcPedidos, true); // Encendemos la luz en VERDE 
                    System.out.println("PC 1 conectado.");

                    // Usamos un segundo try para manejar la conexión continua con este cliente específico
                    try (ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream())) {
                        while (true) {
                            // Se queda escuchando permanentemente los objetos que mande el PC 1
                            PedidoClass p = (PedidoClass) entrada.readObject(); // Leemos el objeto que mandó el cliente

                            // ---------------- ALMACENAR LAS VAINAS EN LAS ESTRUCTURAS ----------------
                            listaPedidos.add(p); // todo se almacena en el historial

                            vista.mostrarNotificacion("¡Pedido recibido! Cliente: " + p.getCliente()); // ESTP ES LO DE LA NOTIFICACION ALEEEEEX

                            if (!p.isEstado()) { // Solo revisamos los pendientes
                                colaPedidos.add(p); // Cola de pendientes

                                if (p.getTipo().equalsIgnoreCase("Restaurante")) { // Pendientes del restaurante
                                    colaRestaurante.add(p);
                                } else if (p.getTipo().equalsIgnoreCase("Delivery")) { // Pendientes del delivery
                                    colaDelivery.add(p);

                                    try {
                                        // La IP de la PC 3 (Delivery) y un puerto nuevo, ej: 5004
                                        Socket socketPC3 = new Socket("localhost", 5004); // IP DEL PC 3 (DELIVERY)
                                        ObjectOutputStream salidaPC3 = new ObjectOutputStream(socketPC3.getOutputStream());

                                        salidaPC3.writeObject(p); // Le mandamos el pedido completo
                                        salidaPC3.flush();
                                        socketPC3.close(); // Cerramos después de enviar
                                    } catch (Exception e) {
                                        System.out.println("PC 3 no está conectada o disponible");
                                    }
                                }
                            }

                            // --- PARA VER QUE TAL VA JAJA ---
                            imprimirEstadoEstructuras();

                            vista.actualizarTablaHistorial(); // Llamamos a actualizar la tabla usando la instancia 'vista'
                            vista.actualizarTablaPendientes();
                        }
                    } catch (java.io.EOFException | java.net.SocketException e) {
                        // Detecta cuando el PC 1 cierra el programa o la conexión
                        System.out.println("La Pc de los pedidos se ha desconectado.");
                    } catch (Exception e) {
                        System.err.println("Error recibiendo datos: " + e.getMessage());
                    } finally {
                        vista.actualizarEstadoServidor(vista.lblPcPedidos, false); // Apagamos el LED
                        if (!cliente.isClosed()) {
                            try { cliente.close(); } catch (Exception ex) {}
                        }
                    }
                }
            } catch (Exception e) {
                vista.actualizarEstadoServidor(vista.lblPcPedidos, false); // Si el servidor falla, luz en ROJO
                System.err.println("Error en el servidor: " + e.getMessage());
            }
        }).start(); // Hasta acá es donde arrancamos el hilo

        // --- HILO PARA ESCUCHAR RESPUESTAS DE PC 3 (DELIVERY) ---
        new Thread(() -> {
            try {
                ServerSocket serverRespuestas = new ServerSocket(5005);
                serverRespuestas.setReuseAddress(true);

                while (true) {
                    // Se queda esperando a que la PC 3 se conecte
                    Socket pc3 = serverRespuestas.accept();

                    // ¡PC 3 CONECTADA! Prendemos el LED
                    vista.actualizarEstadoServidor(vista.lblPcDelivery, true);
                    System.out.println("PC Delivery (PC 3) conectada.");

                    try (ObjectInputStream entrada = new ObjectInputStream(pc3.getInputStream())) {
                        while (true) {
                            // Escucha permanentemente los "Listo" sin cerrar la conexión
                            PedidoClass pedidoListo = (PedidoClass) entrada.readObject();

                            // 1. Notificación
                            vista.mostrarNotificacion("¡Pedido #" + pedidoListo.getId() + " en camino!");

                            // 2. Actualizar estructuras
                            colaPedidos.removeIf(p -> p.getId().equals(pedidoListo.getId()));
                            colaDelivery.removeIf(p -> p.getId().equals(pedidoListo.getId()));

                            // 3. Actualizar historial
                            for (PedidoClass p : listaPedidos) {
                                if (p.getId().equals(pedidoListo.getId())) {
                                    p.setEstado(true);
                                    break;
                                }
                            }

                            // 4. Refrescar tablas
                            vista.actualizarTablaPendientes();
                            vista.actualizarTablaHistorial();
                        }
                    } catch (Exception e) {
                        // Si la PC 3 se cierra, el socket se rompe y entra aquí
                        System.out.println("PC Delivery desconectada.");
                    } finally {
                        // Se apagará el LED solo cuando la conexión se pierda
                        vista.actualizarEstadoServidor(vista.lblPcDelivery, false);
                        if (!pc3.isClosed()) {
                            try { pc3.close(); } catch (Exception ex) {}
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error en puerto 5005: " + e.getMessage());
            }
        }).start();   
    }

    private static void imprimirEstadoEstructuras() { //Solo pa ver si todo nice o no está nice
        System.out.println("\n======= ESTADO ACTUAL DEL SISTEMA =======");
        System.out.println("Historial Total: " + listaPedidos.size());
        System.out.println("Cola Pendientes (Total): " + colaPedidos.size());
        System.out.println("Cola Restaurante: " + colaRestaurante.size());
        System.out.println("Cola Delivery: " + colaDelivery.size());
        System.out.println("==========================================");
    }

    public void actualizarTablaHistorial() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblHistorial.getModel(); // reinicia la tabla y saca modelo
        modelo.setRowCount(0);

        modelo.setColumnIdentifiers(new Object[]{"Id", "Cliente", "Tipo", "Total"}); // columnas - AGREGADO TOTAL

        // --- CONFIGURACIÓN DE DISEÑO ---
        tblHistorial.setRowHeight(30);
        tblHistorial.getColumnModel().getColumn(0).setMaxWidth(80);

        if (listaPedidos != null) { // recorrer la lista el for de abajo jaja
            for (PedidoClass p : listaPedidos) {
                // Extraemos los datos del objeto PedidoClass para llenar las columnas e incluimos el Total calculado
                modelo.addRow(new Object[]{p.getId(), p.getCliente(), p.getTipo(), "Q" + p.getTotalPedido()});
            }
        }
    }

    public void actualizarTablaPendientes() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblPendientes.getModel(); // reinicia la tabla y saca modelo
        modelo.setRowCount(0);

        modelo.setColumnIdentifiers(new Object[]{"Id", "Cliente", "Tipo", "Total"}); // columnas - AGREGADO TOTAL

        // --- CONFIGURACIÓN DE DISEÑO ---
        tblPendientes.setRowHeight(30);
        tblPendientes.getColumnModel().getColumn(0).setMaxWidth(80);

        if (colaPedidos != null) { // recorrer la lista el for de abajo jaja
            for (PedidoClass p : colaPedidos) {
                // Extraemos los datos del objeto PedidoClass para llenar las columnas e incluimos el Total calculado
                modelo.addRow(new Object[]{p.getId(), p.getCliente(), p.getTipo(), "Q" + p.getTotalPedido()});
            }
        }
    }

    public void actualizarEstadoServidor(javax.swing.JLabel etiqueta, boolean encendido) {
        // Si encendido es true usa greenCircle, si es false usa redCircle
        String imagen = encendido ? "/greenCircle.png" : "/redCircle.png";
        java.net.URL ruta = getClass().getResource(imagen);

        if (ruta != null) {
            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(ruta);
            java.awt.Image img = icono.getImage().getScaledInstance(
                    etiqueta.getWidth(), etiqueta.getHeight(), java.awt.Image.SCALE_SMOOTH);
            etiqueta.setIcon(new javax.swing.ImageIcon(img));
        } else {
            System.err.println("No se encontró la imagen: " + imagen);
        }
    }

    public void etiquetas() {
        setImagenLabel(lblPedidos, "/pedidos.png");
        setImagenLabel(lblDelivery, "/delivery.png");
        setImagenLabel(lblRestaurante, "/restaurante.png");
    }

    public void setImagenLabel(javax.swing.JLabel etiqueta, String nombreImagen) { // para ajustar las imagenes a los lbl
        java.net.URL ruta = getClass().getResource(nombreImagen);

        if (ruta != null) {
            javax.swing.ImageIcon iconoOriginal = new javax.swing.ImageIcon(ruta);

            java.awt.Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                    etiqueta.getWidth(),
                    etiqueta.getHeight(),
                    java.awt.Image.SCALE_SMOOTH
            );

            etiqueta.setIcon(new javax.swing.ImageIcon(imagenEscalada));
        } else {
            System.err.println("No se pudo encontrar la imagen en: " + nombreImagen);
        }
    }

    public void mostrarNotificacion(String mensaje) {
        final javax.swing.JOptionPane optionPane = new javax.swing.JOptionPane(
                mensaje,
                javax.swing.JOptionPane.INFORMATION_MESSAGE,
                javax.swing.JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, null // Esto quita los botones para que no sea intrusivo así bien feo xd
        );

        final javax.swing.JDialog dialog = optionPane.createDialog(this, "Nuevo Pedido");
        dialog.setModal(false); // Permite que sigas usando el server mientras se ve la nota

        // Timer para cerrar la notificación solita después de 2.5 segundos
        javax.swing.Timer timer = new javax.swing.Timer(2500, e -> {
            dialog.setVisible(false);
            dialog.dispose();
        });

        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PEDIDOS;
    private javax.swing.JLabel PEDIDOS1;
    private javax.swing.JLabel PEDIDOS2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDelivery;
    private javax.swing.JLabel lblPcDelivery;
    private javax.swing.JLabel lblPcPedidos;
    private javax.swing.JLabel lblPcRestaurante;
    private javax.swing.JLabel lblPedidos;
    private javax.swing.JLabel lblRestaurante;
    private javax.swing.JTable tblHistorial;
    private javax.swing.JTable tblPendientes;
    // End of variables declaration//GEN-END:variables
}
