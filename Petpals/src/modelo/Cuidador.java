package modelo;
import java.util.ArrayList;
import java.util.List;

public class Cuidador extends Empleado {
    private List<Mascota> mascotasCuidadas;

    public Cuidador(String nombre, String apellido, String cargo) {
        super(nombre, apellido, cargo);
        this.mascotasCuidadas = new ArrayList<>();
    }

    public void agregarMascotaCuidada(Mascota mascota) {
        mascotasCuidadas.add(mascota);
    }

    public void eliminarMascotaCuidada(String nombreMascota) {
        mascotasCuidadas.removeIf(m -> m.getNombre().equals(nombreMascota));
    }

    public List<Mascota> getMascotasCuidadas() {
        return mascotasCuidadas;
    }
}

