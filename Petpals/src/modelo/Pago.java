package modelo;
import javax.swing.JOptionPane;

public class Pago {
    private int pagoID;
    private double monto;
    private String fechaHora;
    private String estadoPago;

    public Pago(int pagoID, double monto, String fechaHora, String estadoPago) {
        if (pagoID > 0) {
            this.pagoID = pagoID;
        } else {
            throw new IllegalArgumentException("El ID del pago debe ser mayor que 0.");
        }
        if (monto >= 0) {
            this.monto = monto;
        } else {
            throw new IllegalArgumentException("El monto del pago no puede ser negativo.");
        }
        if (fechaHora != null && !fechaHora.trim().isEmpty()) {
            this.fechaHora = fechaHora;
        } else {
            throw new IllegalArgumentException("La fecha y hora del pago no pueden estar vacías.");
        }
        if (estadoPago != null && !estadoPago.trim().isEmpty()) {
            this.estadoPago = estadoPago;
        } else {
            throw new IllegalArgumentException("El estado del pago no puede estar vacío.");
        }
    }

    public void mostrarInformacion() {
        String mensaje = "Pago ID: " + pagoID + "\n" +
                         "Monto: $" + monto + "\n" +
                         "Fecha y Hora: " + fechaHora + "\n" +
                         "Estado del Pago: " + estadoPago;
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
