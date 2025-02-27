import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionPosts() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != -1) {
            System.out.println("____________________________________________________________");
            System.out.println(" 1 - Postear | 2 - Listar mis Posts || 4 - Salir de Posts  |");
            System.out.println("------------------------------------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: GestionPosts.nuevoPost();
                break;
                case 2: GestionPosts.listarTodosLosPosts();
                case 4: return;
                default:
                    System.out.println("NO VÁLIDO. ERROR");
                    System.out.println("Introduzca de nuevo un botón válido.");
                break;
            }
        }
    }

    private static void listarTodosLosPosts() throws SQLException {
        java.sql.Connection con = MainRS.connection;
        PreparedStatement ps = con.prepareStatement(
                "SELECT " +
                        "p.id, p.texto, p.likes, p.fecha, u.nombre " +
                     "FROM posts as p " +
                     "inner join usuarios as u ON p.id_usuario = u.id"
        );
        ps.executeUpdate();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getDate(3));
            System.out.println(rs.getString(4));

        }
    }
    public static String nuevoPost() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = MainRS.connection;
        System.out.println("Introduce un texto para el post: ");
        String texto = sc.nextLine();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        PreparedStatement pst = con.prepareStatement(
                "INSERT INTO posts (texto, likes, fecha, id_usuario) " +
                        "VALUES (?, 0, ?, ?)"
        );
        pst.setString(1, texto);
        pst.setDate(2, fecha);
        pst.setInt(3, MainRS.id_usuario);
        pst.executeUpdate();
        return "";
    }
}
