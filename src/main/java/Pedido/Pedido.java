package Pedido;

import Clases.Orden;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import Clases.PedidoClass;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Pedido extends javax.swing.JFrame {
    private Socket sc;
    private ObjectOutputStream salida;
    List <Orden> listaPedido = new ArrayList<>();
    private volatile boolean corriendo = true; // para mantener trabajando la conección con el servidor continuamente
    String ipServer;
    
//    public Pedido() {
//        initComponents();
//        conectarAlServidor();
//        cargarComboBox();
//    }
    public Pedido(String ipServerRecibida) {
        this.ipServer = ipServerRecibida;
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() { //esta "addwindowlistener" detecta cuando el usuario cierre la ventana, ahí apaga el "corriendo" para cerrar el socket
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                corriendo = false;
                try { if (sc != null) sc.close(); } catch (Exception ex) {}
            }
        });

        conectarAlServidor();
        cargarComboBox();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        cmbComida = new javax.swing.JComboBox<>();
        cmbTipo = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar1 = new javax.swing.JButton();
        btnChat = new javax.swing.JButton();
        BtnVip = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregar.setBackground(new java.awt.Color(0, 102, 102));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        cmbComida.setBackground(new java.awt.Color(248, 234, 234));
        cmbComida.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbComida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hamburguesa", "Papas", "Fresas xd" }));
        jPanel1.add(cmbComida, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 230, 30));

        cmbTipo.setBackground(new java.awt.Color(248, 234, 234));
        cmbTipo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Delivery", "Restaurante", "Cafeteria" }));
        jPanel1.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 150, 30));

        txtNombre.setBackground(new java.awt.Color(248, 234, 234));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 210, 30));

        txtId.setBackground(new java.awt.Color(248, 234, 234));
        txtId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 210, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 90, -1));

        tblPedido.setBackground(new java.awt.Color(248, 234, 234));
        tblPedido.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Comida"
            }
        ));
        jScrollPane1.setViewportView(tblPedido);
        if (tblPedido.getColumnModel().getColumnCount() > 0) {
            tblPedido.getColumnModel().getColumn(0).setMinWidth(50);
            tblPedido.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblPedido.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 300, 210));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Id");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 80, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Comida:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Tipo:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 80, -1));

        btnEnviar.setBackground(new java.awt.Color(0, 102, 51));
        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(102, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        btnAgregar1.setBackground(new java.awt.Color(0, 102, 102));
        btnAgregar1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAgregar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setText("Agregar");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        btnChat.setBackground(new java.awt.Color(204, 204, 0));
        btnChat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChat.setForeground(new java.awt.Color(255, 255, 255));
        btnChat.setText("Chat");
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });
        jPanel1.add(btnChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 100, 30));

        BtnVip.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BtnVip.setText("VIP");
        jPanel1.add(BtnVip, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 680, 290));

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CENTRO DE ORDENES");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 280, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        listaPedido.add((Orden) cmbComida.getSelectedItem());
        actualizarTablaPedido();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try {
            // Revisamos que la conexión exista y no esté cerrada
            if (sc != null && !sc.isClosed()) {
                if(txtId.getText().isEmpty()){
                    javax.swing.JOptionPane.showMessageDialog(this, "Falta el Id");
                    return;
                } else if (!txtId.getText().matches("\\d+")){ 
                    javax.swing.JOptionPane.showMessageDialog(this, "Solo ingrese números en el Id");
                    return;
                }
                
                if(txtNombre.getText().isEmpty()){
                    javax.swing.JOptionPane.showMessageDialog(this, "falta el nombre");
                    return;
                }else if (!txtNombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
                    javax.swing.JOptionPane.showMessageDialog(this, "Solo debe de ingresar letras en el nombre");
                    return;                   
                }
                
                if(listaPedido.isEmpty()){
                    javax.swing.JOptionPane.showMessageDialog(this, "No has añadido ningún producto");
                    return;
                }
                
                
                
                
                // Creamos el objeto con los datos de los campos de texto
                PedidoClass p = new PedidoClass(txtId.getText(), txtNombre.getText(), cmbTipo.getSelectedItem().toString(), listaPedido);
                
                if(BtnVip.isSelected()){ //para "marcarlo" para la cola prioritaria
                    p.setVIP(true);
                } 

                p.setOrigen("PCPEDIDOS");
                
                // Lo enviamos por el "cable"
                salida.writeObject(p);
                salida.flush(); // Asegura que el objeto salga por el cable de una vez
                salida.reset(); // Limpia la memoria del stream para que el siguiente pedido no se traslape

            } else {
                // Si por algún motivo se desconectó, avisamos
                javax.swing.JOptionPane.showMessageDialog(this, "No hay conexión con el servidor.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Ocurrió un error inesperado: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        limpiar();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblPedido.getSelectedRow();

        if (filaSeleccionada != -1) {
            listaPedido.remove(filaSeleccionada);
            actualizarTablaPedido();

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Mano, no seleccionaste nada jaja. \n Pilas pues");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed
    chat_pedido chat = new chat_pedido();
    chat.setVisible(true);
    chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnChatActionPerformed

    public void actualizarTablaPedido() {
            javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblPedido.getModel(); //reinicia la tabla y saca modelo
            modelo.setRowCount(0); 

            modelo.setColumnIdentifiers(new Object[]{"No.", "Pedido", "Precio"});//columnas

            // --- CONFIGURACIÓN DE DISEÑO ---
            tblPedido.setRowHeight(30);
            tblPedido.getColumnModel().getColumn(0).setMaxWidth(40);
            // Opcional: darle un ancho fijo a la columna de precio
            tblPedido.getColumnModel().getColumn(2).setPreferredWidth(80);

            int contador = 1;
            if (listaPedido != null) { 
                // CAMBIO CLAVE: Usamos 'Orden' en lugar de 'Object' para acceder a sus métodos
                for (Orden orden : listaPedido) { 
                    // Agregamos los datos: el contador, el nombre de la comida y el precio con formato
                    modelo.addRow(new Object[]{
                        contador, 
                        orden.getNombreComida(), 
                        "Q" + orden.getPrecio()
                    });
                    contador++;
                }
            }
        }
    
    private void conectarAlServidor() {
        new Thread(() -> {
            int esperaMs = 3000; //tiempo entre intento e intento 

            while (corriendo) {  //este while es para que siempre que esté la ventana siga intentando conectarse
                try {
                    sc     = new Socket(ipServer, 8080); //--------------------------------------------------------------------------------------------------------------->IP DEL SERVER
                    salida = new ObjectOutputStream(sc.getOutputStream());
                    System.out.println("Conectado al servidor con éxito.");//hace la conección

                    
                    while (corriendo && !sc.isClosed()) {// Espera aquí mientras la conexión esté viva
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {// Si falla, limpia y vuelve a intentar
                    
                    salida = null;
                    sc     = null;
                    try {
                        Thread.sleep(esperaMs);   // espera 3 segundos
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }).start();
    }
    
    public List<Orden> obtenerMenuDefault() {
        List<Orden> menu = new ArrayList<>();

        // Agregamos los platos con sus precios reales
        menu.add(new Orden("Pizza Familiar", 75.00));
        menu.add(new Orden("Hamburguesa Doble", 45.00));
        menu.add(new Orden("Tacos (Orden de 3)", 30.00));
        menu.add(new Orden("Lasaña de Carne", 55.00));
        menu.add(new Orden("Gaseosa 1.5L", 15.00));
        menu.add(new Orden("Papas Fritas", 20.00));

        return menu;
    }
    
    public void cargarComboBox() {
        // 1. Limpiamos el combo por si acaso
        cmbComida.removeAllItems();

        // 2. Obtenemos la lista
        List<Orden> menu = obtenerMenuDefault();

        // 3. Llenamos el combo con los OBJETOS, no con Strings
        for (Orden item : menu) {
            ((javax.swing.DefaultComboBoxModel)cmbComida.getModel()).addElement(item);
        }
    }
    
    public void limpiar(){
        txtNombre.setText("");
        txtId.setText("");
        
        listaPedido.clear(); 
        
        actualizarTablaPedido();

        txtId.requestFocus();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pedido("localhost").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BtnVip;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox<String> cmbComida;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
