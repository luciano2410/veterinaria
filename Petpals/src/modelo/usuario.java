package modelo;
public class usuario {
    private String usuario;
    private String contrase�a;
    private String rol;

    public usuario(String usuario, String contrase�a, String rol) {
        this.usuario = usuario;
        this.contrase�a = contrase�a;
        this.rol = rol;
    }

    // Getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrase�a() {
        return contrase�a;
    }

    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

