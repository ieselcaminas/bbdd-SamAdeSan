import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        // secciones
        while (opcion != -1) {
            System.out.println("___________________________________________________________________________________________________");
            System.out.println(" 1 - Iniciar Sesión (Loguearse) | 2 - Crear nuevo usuario (Registrarse) || 4 - Salir de Usuarios  |");
            System.out.println("---------------------------------------------------------------------------------------------------");
            opcion = sc.nextInt();
            if (opcion == 1) {
                String usuario  = existeUsuario();
                if (!usuario.isEmpty()) {
                    System.out.println("Usuario logueado con éxito!");
                    MainRS.usuario = usuario;
                    break;
                }
            }
            else if (opcion == 2) {
                System.out.println("Usuario en la cuenta: " + insertarUsuario());
            }
            else if (opcion == 4) {
                MainRS.main(null);
            }
            else {
                System.out.println("Usuario no encontrado");
            }
        }
    }
    public static String existeUsuario() throws SQLException {
        // Creamos la conexión al host.
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu usuario: ");
        String usuario = sc.nextLine();
        System.out.println("Introduce tu contraseña: ");
        String contrasena = sc.nextLine();

        // Hacer una consulta ya preparada
        PreparedStatement ps = con.prepareStatement(
                "SELECT * " +
                     "FROM usuarios " +
                     "WHERE nombre = ? AND contrasenya = ?"
        );
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            MainRS.id_usuario = rs.getInt(1);
            return usuario;
        }
        return "";
    }
    public static String insertarUsuario() throws SQLException {
        // Insertamos un usuario con los datos que queremos poner a demás de conectarnos al host
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce tu apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasena = sc.nextLine();
        // consulta
        PreparedStatement ps = con.prepareStatement(
                "Insert into usuarios (nombre, apellidos, contrasenya) " +
                        "VALUES (?, ?, ?)"
        );
        //forma de los resultados
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, contrasena);
        ps.executeUpdate();
        return "";
    }
}
