package modelo;
public class Empleado {
    private String nombre;
    private String apellido;
    private String cargo;

    public Empleado(String nombre, String apellido, String cargo) {
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
        if (cargo != null && !cargo.trim().isEmpty()) {
            this.cargo = cargo;
        } else {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }
    }

    // Getters y setters
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo != null && !cargo.trim().isEmpty()) {
            this.cargo = cargo;
        } else {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }
    }
}

