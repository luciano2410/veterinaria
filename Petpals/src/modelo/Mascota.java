package modelo;
public class Mascota {
    private String nombre;
    private String especie;

    public Mascota(String nombre, String especie) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vac�o.");
        }
        if (especie != null && !especie.trim().isEmpty()) {
            this.especie = especie;
        } else {
            throw new IllegalArgumentException("La especie de la mascota no puede estar vac�a.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vac�o.");
        }
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        if (especie != null && !especie.trim().isEmpty()) {
            this.especie = especie;
        } else {
            throw new IllegalArgumentException("La especie de la mascota no puede estar vac�a.");
        }
    }
}
