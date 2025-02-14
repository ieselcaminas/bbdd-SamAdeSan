import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != -1) {
            System.out.println("1 - Loguearse");
            System.out.println("2 - Nuevo usuario");
            opcion = sc.nextInt();
            if (opcion == -1) {
                GestionUsuarios.gestionMenu();
            }
            if (opcion == 1) {
                boolean logueado = existeUsuario();
                if (logueado) {
                    System.out.println("Usuario logueado!");
                }
            }
            else if (opcion == 2) {
                System.out.println("Usuario en la cuenta: " + insertarUsuario());
            }
        }
    }
    public static boolean existeUsuario() throws SQLException {
        // Creamos la conexión al host.
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce tu contraseña: ");
        String contrasena = sc.nextLine();

        // Hacer una consulta ya preparada
        PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE usuarios = ? AND contrasenya = ?");
        ps.setString(1, nombre);
        ps.setString(2, contrasena);
        return false;
    }
    public static String insertarUsuario() throws SQLException {
        //
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nuevo nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce una nueva contraseña");
        String contrasena = sc.nextLine();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios");
        return nombre;
    }
}
