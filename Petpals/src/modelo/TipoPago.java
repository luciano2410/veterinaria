package modelo;
import javax.swing.JOptionPane;

public class TipoPago {
	
    private int tipoPagoID;
    private String nombre;
    private String descripcion;
    

    public TipoPago(int tipoPagoID, String nombre, String descripcion) {
        if (tipoPagoID > 0) {
            this.tipoPagoID = tipoPagoID;
        } else {
            throw new IllegalArgumentException("El ID del tipo de pago debe ser mayor que 0.");
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre del tipo de pago no puede estar vac�o.");
        }
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
        } else {
            throw new IllegalArgumentException("La descripci�n del tipo de pago no puede estar vac�a.");
        }
    }

    public int getTipoPagoID() {
        return tipoPagoID;
    }

    public void setTipoPagoID(int tipoPagoID) {
        if (tipoPagoID > 0) {
            this.tipoPagoID = tipoPagoID;
        } else {
            throw new IllegalArgumentException("El ID del tipo de pago debe ser mayor que 0.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre del tipo de pago no puede estar vac�o.");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
        } else {
            throw new IllegalArgumentException("La descripci�n del tipo de pago no puede estar vac�a.");
        }
    }

    public void mostrarInformacion() {
        String mensaje = "Tipo de Pago ID: " + tipoPagoID + "\n" +
                         "Nombre: " + nombre + "\n" +
                         "Descripci�n: " + descripcion;
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
