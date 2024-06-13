package modelo;
import javax.swing.*;

public class Cliente {
    private String nombre;
    private String apellido;
    private Mascota mascota;
    private int edad;
    private String raza;

    public Cliente(String nombre, String apellido, Mascota mascota, int edad, String raza) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido != null && !apellido.trim().isEmpty()) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (mascota != null) {
            this.mascota = mascota;
        } else {
            throw new IllegalArgumentException("La mascota no puede ser nula.");
        }
        if (edad >= 0) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        if (raza != null && !raza.trim().isEmpty()) {
            this.raza = raza;
        } else {
            throw new IllegalArgumentException("La raza no puede estar vacía.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido != null && !apellido.trim().isEmpty()) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        if (mascota != null) {
            this.mascota = mascota;
        } else {
            throw new IllegalArgumentException("La mascota no puede ser nula.");
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        if (raza != null && !raza.trim().isEmpty()) {
            this.raza = raza;
        } else {
            throw new IllegalArgumentException("La raza no puede estar vacía.");
        }
    }

    public void mostrarInformacion() {
        String mensaje = "Nombre: " + nombre + "\n" +
                         "Apellido: " + apellido + "\n" +
                         "Mascota: " + mascota.getNombre() + "\n" +
                         "Edad: " + edad + "\n" +
                         "Raza: " + raza;
        JOptionPane.showMessageDialog(null, mensaje);
    }
}

