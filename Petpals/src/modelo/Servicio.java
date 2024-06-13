package modelo;
public class Servicio {
    private String nombre;
    private double costo;

    public Servicio(String nombre, double costo) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío.");
        }
        if (costo >= 0) {
            this.costo = costo;
        } else {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío.");
        }
    }

    public double getCosto() {
    return costo;
            }

            public void setCosto(double costo) {
                if (costo >= 0) {
                    this.costo = costo;
                } else {
                    throw new IllegalArgumentException("El costo no puede ser negativo.");
                }
            }

			public void setPrecio(double parseDouble) {
				
				
			}

			public Object getPrecio() {
				
				return null;
			}
        }

