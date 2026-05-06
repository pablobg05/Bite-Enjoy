
package Clases;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

public class PedidoClass implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String cliente;
    private String tipo;
    private List<Orden> comidas; 
    private boolean estado;
    private String origen;
    private Date fecha;
    private boolean VIP;

    
    public PedidoClass(String id, String cliente, String tipo, List lista) {
        this.id = id;
        this.cliente = cliente;
        this.tipo = tipo;
        this.comidas = lista;
        this.estado = false;
        this.fecha = new Date();
        this.VIP = false;
    }
    
    public double getTotalPedido() {
        double total = 0;
        for (Orden o : comidas) {
            total += o.getPrecio();
        }
        return total;
    }
    
    public String getListaComidas() {
        String listado = "";
        for (int i = 0; i < comidas.size(); i++) {
            listado += (i + 1 + ". " + comidas.get(i).getNombreComida() + " - Q" + comidas.get(i).getPrecio() + "\n");
        }
        return listado;
    }
    
    public String getFechaConFormato() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        return sdf.format(fecha);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Orden> getComidas() {
        return comidas;
    }

    public void setComidas(List<Orden> comidas) {
        this.comidas = comidas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isVIP() {
        return VIP;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

}
