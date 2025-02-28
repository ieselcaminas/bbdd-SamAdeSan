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
            System.out.println(ColoresDeSocialNetwork.BLUE.getCode() +
                    "___________________________________________________________________________________________________\n" +
                    " 1 - Iniciar Sesión (Loguearse) | 2 - Crear nuevo usuario (Registrarse) || " + ColoresDeSocialNetwork.RED.getCode() + "4 - Salir de Usuarios  " + ColoresDeSocialNetwork.BLUE.getCode() + "|\n" +
                    "---------------------------------------------------------------------------------------------------"
            );
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    String usuario  = existeElUsuario();
                    if (usuario.isEmpty()) {
                        System.out.println(ColoresDeSocialNetwork.RED.getCode() +
                                "El usuario no existe o no se encuentra.\n" +
                                "Si el problema persiste, puede ser porque está mal el usuario o la contraseña."
                        );
                    }else{
                        System.out.println(ColoresDeSocialNetwork.GREEN.getCode() + "Logueado con éxito!");
                        MainRS.usuario = usuario;
                        break;
                    }
                    break;
                case 2:
                    System.out.println(ColoresDeSocialNetwork.YELLOW.getCode() + "Usuario en la cuenta: " + insertarNuevoUsuario());
                    break;
                case 4:
                    System.out.println("Saliendo de Usuarios...");
                    MainRS.main(null);
                    return;
                default:
                    System.out.println(ColoresDeSocialNetwork.RED.getCode() + "ERROR. Formato no válido (la opción no existe)" + ColoresDeSocialNetwork.RESET.getCode());
                    break;
            }
        }
    }

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
}
