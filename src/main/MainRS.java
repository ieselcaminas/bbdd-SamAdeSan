import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainRS {

    static String usuario = "";
    static int id_usuario = -1;
    // Conexión a la Base de Datos de SQLite
    static java.sql.Connection connection;
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

            System.out.println(ColoresDeSocialNetwork.GREEN.getCode() +
                            "╔════════════════════════════════════════════════════════════════════════╗\n" +
                            "║██████╗ ███████╗██████╗     ███████╗ ██████╗  ██████╗██╗ █████╗ ██╗     ║\n" +
                            "║██╔══██╗██╔════╝██╔══██╗    ██╔════╝██╔═══██╗██╔════╝██║██╔══██╗██║     ║\n" +
                            "║██████╔╝█████╗  ██║  ██║    ███████╗██║   ██║██║     ██║███████║██║     ║\n" +
                            "║██╔══██╗██╔══╝  ██║  ██║    ╚════██║██║   ██║██║     ██║██╔══██║██║     ║\n" +
                            "║██║  ██║███████╗██████╔╝    ███████║╚██████╔╝╚██████╗██║██║  ██║███████╗║\n" +
                            "║╚═╝  ╚═╝╚══════╝╚═════╝     ╚══════╝ ╚═════╝  ╚═════╝╚═╝╚═╝  ╚═╝╚══════╝║\n" +
                            "╚════════════════════════════════════════════════════════════════════════╝"
                    );
            System.out.println(ColoresDeSocialNetwork.RESET.getCode());

            if (!usuario.isEmpty()) {
                System.out.println("Bienvenido " + ColoresDeSocialNetwork.GREEN.getCode() + usuario + "!");
            }else {
                System.out.println("Bienvenido! -" + ColoresDeSocialNetwork.RED.getCode() + " No logueado");
            }
            System.out.println(ColoresDeSocialNetwork.BLUE.getCode() +
                    "__________________________________________________________\n" +
                    "| 1 - Usuarios | 2 - Posts | 3 - Comentarios || " + ColoresDeSocialNetwork.RED.getCode() + "4 - Salir " + ColoresDeSocialNetwork.BLUE.getCode() + "|\n" +
                    "----------------------------------------------------------"
            );
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
                System.out.println(ColoresDeSocialNetwork.YELLOW.getCode() + "Gracias por usar el programa de red social ;). Adiós.");
            }
            else {
                System.out.println(ColoresDeSocialNetwork.RED.getCode() +
                        "NO VÁLIDO. ERROR\n" +
                        "Introduzca de nuevo un botón válido." + ColoresDeSocialNetwork.RESET.getCode()
                );
                MainRS.main(args);
            }
        }
    }
}
