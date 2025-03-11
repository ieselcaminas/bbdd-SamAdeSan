package RedSocial_SamuelAded;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class GestionComentarios {
    public static void gestionComentarios() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion;
        // Opciones de los Comentarios
        while (true) {
            System.out.println(azul() +
                    "_________________________________________________________________________\n" +
                    " 1 - Ver comentarios | 2 - Comentar un post || " + rojo() + "4 - Salir de Comentarios " + azul() + "|\n" +
                    "-------------------------------------------------------------------------"
            );
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    verComentariosDePosts();
                    break;
                case 2:
                    comentarPost();
                    break;
                case 4:
                    break;
                default:
                    System.out.println(rojo() +
                            "NO VÁLIDO. ERROR \n" +
                            "Introduzca de nuevo un botón válido." + rset()
                    );
                    gestionComentarios();
                    break;

            }
        }
    }

    private static void comentarPost() throws SQLException {
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona el post que desea comentar: ");
        int post_id = Integer.parseInt(sc.nextLine());

        System.out.println(azul() + "--> " + rset());
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
        pst.setInt(1, 5);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println("\tC: " + rs.getString(2));
            System.out.println("\tFecha del comentario: " + rs.getDate(3));
            System.out.println("\t\tComentario de " + rs.getString(4));
        }
        if (!rs.next()) {
            System.out.println(rojo() + "No se ha encontrado ningún comentario." + rset());
        }
    }
    public static String rojo() {return ColoresRS.RED.getCode();}
    public static String azul() {return ColoresRS.BLUE.getCode();}
    public static String rset() {return ColoresRS.RESET.getCode();}
}
