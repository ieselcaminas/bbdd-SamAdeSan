package RedSocial_SamuelAded;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion;
        // secciones
        while (true) {
            System.out.println(azul() +
                    "___________________________________________________________________________________________________\n" +
                    " 1 - Iniciar Sesión (Loguearse) | 2 - Crear nuevo usuario (Registrarse) || " + rojo() + "4 - Salir de Usuarios  " + azul() + "|\n" +
                    "---------------------------------------------------------------------------------------------------"
            );
            opcion = sc.nextInt();
            // Opción del usuario
            switch (opcion) {
                case 1:
                    String usuario = existeElUsuario();
                    if (usuario.isEmpty()) {
                        System.out.println(rojo() +
                                "El usuario no existe o no se encuentra.\n" +
                                "Si el problema persiste, puede ser porque está mal el usuario o la contraseña. "
                        );
                    } else {
                        System.out.println(verde() + "Logueado con éxito!");
                        MainRS.usuario = usuario;
                        break;
                    }
                    break;
                case 2:
                    System.out.println(verde() + "Usuario creado con éxito" + insertarNuevoUsuario() + rset());
                    break;
                case 4:
                    System.out.println(amarillo() + "Saliendo de Usuarios..." + rset());
                    MainRS.main(null);
                    return;
                default:
                    System.out.println(rojo() + "ERROR. Formato no válido (la opción no existe)" + rset());
                    break;
            }
        }
    }

    // Método para saber si el usuario está en la BD.
    public static String existeElUsuario() throws SQLException {
        // Creamos la conexión al host.
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu usuario: ");
        String usuario = sc.nextLine();
        System.out.println("Introduce tu contraseña: ");
        String contrasena = sc.nextLine();

        // Hacer una consulta ya preparada
        PreparedStatement ps = con.prepareStatement(
                "SELECT * " +
                     "FROM usuarios " +
                     "WHERE nombre = ? AND contrasenya = ?"
        );
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            MainRS.id_usuario = rs.getInt(1);
            return usuario;
        }
        return "";
    }

    // Método para insertar un usuario existente de la BD.
    public static String insertarNuevoUsuario() throws SQLException {
        // Insertamos un usuario con los datos que queremos poner a demás de conectarnos al host
        java.sql.Connection con = MainRS.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce tu apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasena = sc.nextLine();
        // consulta
        PreparedStatement ps = con.prepareStatement(
                "Insert into usuarios (nombre, apellidos, contrasenya) " +
                        "VALUES (?, ?, ?)"
        );
        //forma de los resultados
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, contrasena);
        ps.executeUpdate();
        return "";

    }
    public static String rojo() {return ColoresRS.RED.getCode();}
    public static String azul() {return ColoresRS.BLUE.getCode();}
    public static String amarillo() {return ColoresRS.YELLOW.getCode();}
    public static String verde() {return ColoresRS.GREEN.getCode();}
    public static String rset() {return ColoresRS.RESET.getCode();}
}
