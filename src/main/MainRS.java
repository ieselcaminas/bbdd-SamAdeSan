import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainRS {
    static java.sql.Connection connection;
    static String usuario = "";
    static int id_usuario = -1;
    // Conexión a la Base de Datos de SQLite
    public static java.sql.Connection getConnection(){
        String host = "jdbc:sqlite:src/main/resources/network.sqlite";
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(host);
            }
            catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }
    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        // secciones
        while (opcion != 4) {

            System.out.println("*-_Red Social_-*");
            System.out.println("|");

            if (!usuario.isEmpty()) {
                System.out.println("Bienvenido " + usuario + "!");
            }else System.out.println("Bienvenido! - No logueado");

            System.out.println("|__________________________________________________________");
            System.out.println("| 1 - Usuarios | 2 - Posts | 3 - Comentarios || 4 - Salir |");
            System.out.println(" ----------------------------------------------------------");

            opcion = sc.nextInt();
            if (opcion == 1) {
                GestionUsuarios.gestionMenu();
            }
            else if (opcion == 2) {
                GestionPosts.gestionPosts();
            }
            else if (opcion == 3) {
                GestionComentarios.gestionComentarios();
            }
            else if (opcion == 4) {
                System.out.println("Gracias por usar el programa de red social ;). Adiós.");
            }
            else {
                System.out.println("NO VÁLIDO. ERROR");
                System.out.println("Introduzca de nuevo un botón válido.");
                MainRS.main(args);
            }
        }
    }
}
