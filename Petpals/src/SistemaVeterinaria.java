import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Mascota;
import modelo.Producto;
import modelo.Servicio;
import modelo.conexion;
import modelo.usuario;

public class SistemaVeterinaria {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<Servicio> servicios = new ArrayList<>();
    private static usuario usuarioActual = null;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/petpalsdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        while (true) {
            if (usuarioActual == null) {
                String opcionStr = JOptionPane.showInputDialog(null,
                        "1. Iniciar sesión\n" +
                        "2. Registrarse\n" +
                        "Seleccione una opción:");
                if (opcionStr == null) break;

                int opcion;
                try {
                    opcion = Integer.parseInt(opcionStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        iniciarSesion();
                        break;
                    case 2:
                        registrarse();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                        continue;
                }

                if (usuarioActual == null) continue; 
            }

            String opcionStr = JOptionPane.showInputDialog(null,
                    "1. Cargar cliente\n" +
                            "2. Editar cliente\n" +
                            "3. Eliminar cliente\n" +
                            "4. Mostrar clientes\n" +
                            "5. Cargar producto\n" +
                            "6. Editar producto\n" +
                            "7. Eliminar producto\n" +
                            "8. Mostrar productos\n" +
                            "9. Cargar servicio\n" +
                            "10. Editar servicio\n" +
                            "11. Eliminar servicio\n" +
                            "12. Mostrar servicios\n" +
                            "19. Cerrar sesión\n" +
                            "Seleccione una opción:");

            if (opcionStr == null) break;

            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida");
                continue;
            }

            switch (opcion) {
                case 1:
                    cargarCliente();
                    break;
                case 2:
                    editarCliente();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                case 4:
                    mostrarClientes();
                    break;
                case 5:
                    cargarProducto();
                    break;
                case 6:
                    editarProducto();
                    break;
                case 7:
                    eliminarProducto();
                    break;
                case 8:
                    mostrarProductos();
                    break;
                case 9:
                    cargarServicio();
                    break;
                case 10:
                    editarServicio();
                    break;
                case 11:
                    eliminarServicio();
                    break;
                case 12:
                    mostrarServicios();
                    break;
                case 19:
                    usuarioActual = null;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                    break;
            }
        }
    }

    private static void iniciarSesion() {
        String usuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM usuario WHERE usuario = ? AND contraseña = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, usuario);
                statement.setString(2, contraseña);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String rol = rs.getString("rol");
                        usuarioActual = new usuario(usuario, contraseña, rol);
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
        }
    }

    private static void registrarse() {
        String nuevoUsuario = JOptionPane.showInputDialog("Ingrese un nuevo nombre de usuario:");
        String nuevaContraseña = JOptionPane.showInputDialog("Ingrese una nueva contraseña:");
        String rol = JOptionPane.showInputDialog("Ingrese el rol (admin, empleado, cliente):");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO usuario (usuario, contraseña, rol) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, nuevoUsuario);
                statement.setString(2, nuevaContraseña);
                statement.setString(3, rol);
                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e.getMessage());
        }
    }

    private static void cargarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
        String nombreMascota = JOptionPane.showInputDialog("Ingrese el nombre de la mascota:");
        String especieMascota = JOptionPane.showInputDialog("Ingrese la especie de la mascota:");
        int edadMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad de la mascota:"));
        String razaMascota = JOptionPane.showInputDialog("Ingrese la raza de la mascota:");

        Connection connection = null;
        PreparedStatement psCliente = null;
        PreparedStatement psMascota = null;

        try {
            
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sqlCliente = "INSERT INTO cliente (nombre, apellido) VALUES (?, ?)";
            psCliente = connection.prepareStatement(sqlCliente, PreparedStatement.RETURN_GENERATED_KEYS);
            psCliente.setString(1, nombre);
            psCliente.setString(2, apellido);
            psCliente.executeUpdate();

            int idCliente = -1;
            var rs = psCliente.getGeneratedKeys();
            if (rs.next()) {
                idCliente = rs.getInt(1);
            }

            String sqlMascota = "INSERT INTO mascota (nombre, especie, edad, raza, id_cliente) VALUES (?, ?, ?, ?, ?)";
            psMascota = connection.prepareStatement(sqlMascota);
            psMascota.setString(1, nombreMascota);
            psMascota.setString(2, especieMascota);
            psMascota.setInt(3, edadMascota);
            psMascota.setString(4, razaMascota);
            psMascota.setInt(5, idCliente);
            psMascota.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente y mascota cargados correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        } finally {
            try {
                if (psCliente != null) psCliente.close();
                if (psMascota != null) psMascota.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private static void editarCliente() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente a editar:");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
     
            String queryBuscarCliente = "SELECT id_cliente FROM cliente WHERE nombre = ?";
            try (PreparedStatement psBuscarCliente = con.prepareStatement(queryBuscarCliente)) {
                psBuscarCliente.setString(1, nombreCliente);
                try (ResultSet rs = psBuscarCliente.executeQuery()) {
                    if (rs.next()) {
                        int idCliente = rs.getInt("id_cliente");

                        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:");
                        String nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido del cliente:");
                        String nuevoNombreMascota = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la mascota:");
                        String nuevaEspecieMascota = JOptionPane.showInputDialog("Ingrese la nueva especie de la mascota:");
                        int nuevaEdadMascota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad de la mascota:"));
                        String nuevaRazaMascota = JOptionPane.showInputDialog("Ingrese la nueva raza de la mascota:");

                        String queryActualizarCliente = "UPDATE cliente SET nombre = ?, apellido = ? WHERE id_cliente = ?";
                        try (PreparedStatement psActualizarCliente = con.prepareStatement(queryActualizarCliente)) {
                            psActualizarCliente.setString(1, nuevoNombre);
                            psActualizarCliente.setString(2, nuevoApellido);
                            psActualizarCliente.setInt(3, idCliente);
                            psActualizarCliente.executeUpdate();
                        }

                        String queryActualizarMascota = "UPDATE mascota SET nombre = ?, especie = ?, edad = ?, raza = ? WHERE id_cliente = ?";
                        try (PreparedStatement psActualizarMascota = con.prepareStatement(queryActualizarMascota)) {
                            psActualizarMascota.setString(1, nuevoNombreMascota);
                            psActualizarMascota.setString(2, nuevaEspecieMascota);
                            psActualizarMascota.setInt(3, nuevaEdadMascota);
                            psActualizarMascota.setString(4, nuevaRazaMascota);
                            psActualizarMascota.setInt(5, idCliente);
                            psActualizarMascota.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(null, "Cliente y mascota editados correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar el cliente: " + e.getMessage());
        }
    }


    private static void eliminarCliente() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente a eliminar:");

        if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Verificar si el cliente existe
                String checkSql = "SELECT id_cliente FROM cliente WHERE nombre = ?";
                try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                    checkPs.setString(1, nombreCliente);
                    try (ResultSet rs = checkPs.executeQuery()) {
                        if (!rs.next()) {
                            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con ese nombre.");
                            return;
                        }
                    }
                }

                // Eliminar la mascota asociada
                String deleteMascotaSql = "DELETE FROM mascota WHERE id_cliente = (SELECT id_cliente FROM cliente WHERE nombre = ?)";
                try (PreparedStatement deleteMascotaPs = con.prepareStatement(deleteMascotaSql)) {
                    deleteMascotaPs.setString(1, nombreCliente);
                    deleteMascotaPs.executeUpdate();
                }

                // Eliminar el cliente
                String sql = "DELETE FROM cliente WHERE nombre = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nombreCliente);
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con ese nombre.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "El nombre del cliente no puede estar vacío.");
        }
    }


   

    private static void mostrarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes:\n");

        String query = "SELECT c.nombre AS cliente_nombre, c.apellido, m.nombre AS mascota_nombre, m.especie, m.edad, m.raza " +
                       "FROM cliente c " +
                       "JOIN mascota m ON c.id_cliente = m.id_cliente";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                sb.append("Nombre: ").append(rs.getString("cliente_nombre")).append("\n");
                sb.append("Apellido: ").append(rs.getString("apellido")).append("\n");
                sb.append("Nombre de la mascota: ").append(rs.getString("mascota_nombre")).append("\n");
                sb.append("Especie de la mascota: ").append(rs.getString("especie")).append("\n");
                sb.append("Edad de la mascota: ").append(rs.getInt("edad")).append("\n");
                sb.append("Raza de la mascota: ").append(rs.getString("raza")).append("\n");
                sb.append("--------------------\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los clientes: " + e.getMessage());
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }


    private static void cargarProducto() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        String descripcionProducto = JOptionPane.showInputDialog("Ingrese la descripción del producto:");
        double precioProducto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));

        String insertQuery = "INSERT INTO producto (nombre, descripcion, precio) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

            pstmt.setString(1, nombreProducto);
            pstmt.setString(2, descripcionProducto);
            pstmt.setDouble(3, precioProducto);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Producto cargado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al cargar el producto.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el producto: " + e.getMessage());
        }
    }

    private static void editarProducto() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto a editar:");

        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto:");
                String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción del producto:");
                double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del producto:"));

                producto.setNombre(nuevoNombre);
                producto.setDescripcion(nuevaDescripcion);
                producto.setPrecio(nuevoPrecio);

                Connection conn = null;
                PreparedStatement stmt = null;
                try {
                    conn = conexion.getConnection();
                    String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ? WHERE nombre = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, nuevoNombre);
                    stmt.setString(2, nuevaDescripcion);
                    stmt.setDouble(3, nuevoPrecio);
                    stmt.setString(4, nombreProducto);

                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Producto editado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al editar el producto en la base de datos.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                } finally {
                    try {
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
    }

    private static void eliminarProducto() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto a eliminar:");

        productos.removeIf(p -> p.getNombre().equals(nombreProducto));
        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
    }

    private static void mostrarProductos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de productos:\n");
        for (Producto producto : productos) {
            sb.append("Nombre: ").append(producto.getNombre()).append("\n");
            sb.append("Precio: ").append(producto.getPrecio()).append("\n");
            sb.append("Stock: ").append(producto.getStock()).append("\n");
            sb.append("--------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void cargarServicio() {
        String nombreServicio = JOptionPane.showInputDialog("Ingrese el nombre del servicio:");
        double precioServicio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del servicio:"));

        Servicio servicio = new Servicio(nombreServicio, precioServicio);
        servicios.add(servicio);

        JOptionPane.showMessageDialog(null, "Servicio cargado correctamente.");
    }

    private static void editarServicio() {
        String nombreServicio = JOptionPane.showInputDialog("Ingrese el nombre del servicio a editar:");

        for (Servicio servicio : servicios) {
            if (servicio.getNombre().equals(nombreServicio)) {
                servicio.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre del servicio:"));
                servicio.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del servicio:")));

                JOptionPane.showMessageDialog(null, "Servicio editado correctamente.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Servicio no encontrado.");
    }

    private static void eliminarServicio() {
        String nombreServicio = JOptionPane.showInputDialog("Ingrese el nombre del servicio a eliminar:");

        servicios.removeIf(s -> s.getNombre().equals(nombreServicio));
        JOptionPane.showMessageDialog(null, "Servicio eliminado correctamente.");
    }

    private static void mostrarServicios() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de servicios:\n");
        for (Servicio servicio : servicios) {
            sb.append("Nombre: ").append(servicio.getNombre()).append("\n");
            sb.append("Precio: ").append(servicio.getPrecio()).append("\n");
            sb.append("--------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}




