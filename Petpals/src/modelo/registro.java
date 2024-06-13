package modelo;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registro {

    public boolean registrarUsuario(String username, String password, String role) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = conexion.conectar();
            String query = "INSERT INTO usuarios (usuario, contrase�a, rol) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Registro exitoso.");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexi�n: " + ex.getMessage());
            }
        }

        return false;
    }

   
    public usuario iniciarSesion(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexion.conectar();
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrase�a = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("rol");
                JOptionPane.showMessageDialog(null, "Inicio de sesi�n exitoso.");
                return new usuario(username, password, role);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contrase�a incorrectos.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesi�n: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexi�n: " + ex.getMessage());
            }
        }

        return null;
    }
}
