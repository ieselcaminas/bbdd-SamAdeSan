import java.sql.*;
import java.util.Scanner;

public class GestionComentarios {
    public static void gestionComentarios() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != -1) {
            System.out.println("______________________________________________________");
            System.out.println(" 1 - Ver comentarios | 2 - Comentar un post || 4 - Salir de Usuarios  |");
            System.out.println("------------------------------------------------------");
            opcion = sc.nextInt();
            if (opcion == 1) {
                verComentariosDePosts();
            }
            else if (opcion == 2) {
                comentarpost();
            }
            else if (opcion == 4) {
                break;
            }
            else {
                System.out.println("NO VÁLIDO. ERROR");
                System.out.println("Introduzca de nuevo un botón válido.");
                gestionComentarios();
            }
        }
    }

    private static void comentarpost() throws SQLException {
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Comenta en el post: ");
        String comentario = sc.nextLine();
        PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM"
        );
        pst.setString(1, comentario);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

        }
    }

    private static void verComentariosDePosts() throws SQLException {
        java.sql.Connection con = MainRS.connection;
        PreparedStatement pst = con.prepareStatement("SELECT * FROM ");
        pst.setString(1, "Post");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

        }
    }
}
