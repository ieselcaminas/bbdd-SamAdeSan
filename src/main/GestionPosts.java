import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionPosts() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion;
        // Opciones de la sección.
        while (true) {
            System.out.println(ColoresDeSocialNetwork.BLUE.getCode() +
                    "____________________________________________________________\n" +
                    " 1 - Postear | 2 - Listar mis Posts ||" + ColoresDeSocialNetwork.RED.getCode() + " 4 - Salir de Posts  " + ColoresDeSocialNetwork.BLUE.getCode() + "|\n" +
                    "------------------------------------------------------------" + ColoresDeSocialNetwork.RESET.getCode()
            );
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    GestionPosts.nuevoPost();
                    break;
                case 2:
                    GestionPosts.listarTodosLosPosts();
                case 4:
                    return;
                default:
                    System.out.println(ColoresDeSocialNetwork.RED.getCode() + "NO VÁLIDO. ERROR\n" +
                            "Introduzca de nuevo un botón válido." + ColoresDeSocialNetwork.RESET.getCode()
                    );
                    break;
            }
        }
    }
    // Método para hacer un post nuevo.
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
    // Método para listar todos los post según el ID del usuario.
    private static void listarTodosLosPosts() throws SQLException {
        java.sql.Connection con = MainRS.connection;

        PreparedStatement pst = con.prepareStatement(
                "SELECT p.id, p.texto, p.likes, p.fecha, u.nombre " +
                        "FROM posts as p " +
                        "inner join usuarios as u ON p.id_usuario = u.id " +
                        "where p.id_usuario = ?"
        );
        pst.setInt(1,  MainRS.id_usuario);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Post " + rs.getInt(1));
            System.out.println("\tConcepto: " + rs.getString(2));
            System.out.println("\tFecha: " + rs.getDate(3));
            System.out.println("\t" + rs.getString(4));

        }
    }
}
