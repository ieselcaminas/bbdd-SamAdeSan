import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainRS {
    static java.sql.Connection connection;

    public static java.sql.Connection getConnection() throws SQLException {
        String host = "jjdbc:sqlite:src/main/resources/network.sqlite";
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
        while (opcion != -1) {
            System.out.println("1 - Usuarios");
            System.out.println("2 - Posts");
            System.out.println("3 - Comentarios");
            opcion = sc.nextInt();
            if (opcion == 1) {
                GestionUsuarios.gestionMenu();
            }
        }
    }
}
