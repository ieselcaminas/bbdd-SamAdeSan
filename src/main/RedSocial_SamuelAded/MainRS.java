package RedSocial_SamuelAded;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainRS {

    static String usuario = "";
    static int id_usuario = -1;
    // Conexión a la Base de Datos de SQLite
    static Connection connection;

    public static Connection getConnection(){
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
        int opcion;
        // secciones
        while (true) {

            System.out.println(verde() +
                            "╔════════════════════════════════════════════════════════════════════════╗\n" +
                            "║██████╗ ███████╗██████╗     ███████╗ ██████╗  ██████╗██╗ █████╗ ██╗     ║\n" +
                            "║██╔══██╗██╔════╝██╔══██╗    ██╔════╝██╔═══██╗██╔════╝██║██╔══██╗██║     ║\n" +
                            "║██████╔╝█████╗  ██║  ██║    ███████╗██║   ██║██║     ██║███████║██║     ║\n" +
                            "║██╔══██╗██╔══╝  ██║  ██║    ╚════██║██║   ██║██║     ██║██╔══██║██║     ║\n" +
                            "║██║  ██║███████╗██████╔╝    ███████║╚██████╔╝╚██████╗██║██║  ██║███████╗║\n" +
                            "║╚═╝  ╚═╝╚══════╝╚═════╝     ╚══════╝ ╚═════╝  ╚═════╝╚═╝╚═╝  ╚═╝╚══════╝║\n" +
                            "╚════════════════════════════════════════════════════════════════════════╝" + rst()
                    );
            if (!usuario.isEmpty()) {
                System.out.println("Bienvenido " + verde() + usuario + rst() + "!");
            }else {
                System.out.println("Bienvenido! -" + rojo() + " No logueado");
            }
            System.out.println(azul() +
                    "__________________________________________________________\n" +
                    "| 1 - Usuarios | 2 - Posts | 3 - Comentarios || " + rojo() + "4 - Salir " + azul() + "|\n" +
                    "----------------------------------------------------------"
            );
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    GestionUsuarios.gestionMenu();
                    break;
                case 2:
                    GestionPosts.gestionPosts();
                    break;
                case 3:
                    GestionComentarios.gestionComentarios();
                    break;
                case 4:
                    System.out.println(amarillo() + "Gracias por usar el programa de red social ;). Adiós.");
                    System.exit(0);
                    break;
                default:
                    System.out.println(rojo() +
                            "NO VÁLIDO. ERROR\n" +
                            "Introduzca de nuevo un botón válido." + rst()
                    );
                    break;
            }
        }
    }
    public static String rojo() {return ColoresRS.RED.getCode();}
    public static String azul() {return ColoresRS.BLUE.getCode();}
    public static String amarillo() {return ColoresRS.YELLOW.getCode();}
    public static String verde() {return ColoresRS.GREEN.getCode();}
    public static String rst() {return ColoresRS.RESET.getCode();}
}
