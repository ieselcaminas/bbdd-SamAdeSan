import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionPosts() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != -1) {
            System.out.println("____________________________________");
            System.out.println(" 1 - Postear | 4 - Salir de Posts  |");
            System.out.println("------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: GestionPosts.nuevoPost();
                break;
                case 4: return;
                default:
                    System.out.println("NO VÁLIDO. ERROR");
                    System.out.println("Introduzca de nuevo un botón válido.");
                break;
            }
        }
    }
    public static String nuevoPost() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = MainRS.connection;
        System.out.println("Introduce un texto para el post: ");
        String texto = sc.nextLine();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        PreparedStatement pst = con.prepareStatement("INSERT INTO posts (texto, likes, fecha, id_usuario) VALUES (?, 0, ?, ?)");
        pst.setString(1, texto);
        pst.setInt(2, 0);
        pst.setDate(3, fecha);
        pst.executeUpdate();
        return "";
    }
}
