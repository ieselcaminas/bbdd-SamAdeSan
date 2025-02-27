import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class GestionComentarios {
    public static void gestionComentarios() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        // Opciones de los Comentarios
        while (opcion != -1) {
            System.out.println("_________________________________________________________________________");
            System.out.println(" 1 - Ver comentarios | 2 - Comentar un post || 4 - Salir de Comentarios |");
            System.out.println("-------------------------------------------------------------------------");
            opcion = sc.nextInt();
            if (opcion == 1) {
                verComentariosDePosts();
            }
            else if (opcion == 2) {
                comentarPost();
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

    private static void comentarPost() throws SQLException {
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona el post que desea comentar: ");
        int post_id = Integer.parseInt(sc.nextLine());

        System.out.println("Comentar: ");
        String texto = sc.nextLine();

        java.sql.Date fecha = new java.sql.Date(new Date().getTime());

        PreparedStatement pst = con.prepareStatement(
                "INSERT INTO comentarios (texto, fecha, id_usuario, id_post)" +
                        "VALUES (?, ?, ?, ?)"
        );
        pst.setString(1, texto);
        pst.setDate(2, fecha);
        pst.setInt(3, MainRS.id_usuario);
        pst.setInt(4 ,post_id);
        pst.executeUpdate();
    }

    private static void verComentariosDePosts() throws SQLException {
        java.sql.Connection con = MainRS.connection;

        PreparedStatement pst = con.prepareStatement(
                "SELECT c.id, c.texto, c.fecha, u.nombre " +
                        "FROM comentarios as c " +
                        "INNER JOIN usuarios as u on c.id = u.id " +
                        "INNER JOIN posts as p on c.id_post = p.id " +
                        "WHERE c.id_post = ?"
        );
        //pst.setInt(1, GestionPosts.id_post);
        pst.setInt(1, 5);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getDate(3));
            System.out.println(rs.getString(4));
        }
    }
}
