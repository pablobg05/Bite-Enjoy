
package Clases;

import java.io.Serializable;


public class Orden implements Serializable{
   private String nombreComida;
   private double precio;
   
   public Orden (String n, double p){
       this.nombreComida=n;
       this.precio = p;
   }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
   
   @Override
    public String toString() {
        return nombreComida + " (Q" + precio + ")";
    }
}
