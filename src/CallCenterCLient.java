
import java.net.UnknownHostException;
//import java.rmi.Naming;
import java.rmi.registry.*;
//import java.rmi.server.*;
import javax.swing.JOptionPane;

public class CallCenterCLient {

    public static void main(String args[]) throws UnknownHostException {
        double res = 0;
        try {
            Registry miRegistro = LocateRegistry.getRegistry("127.0.0.1", 12345);
            CallCenter miCallcenter = (CallCenter) miRegistro.lookup("Callcenter");

            String opciones[] = {"Iniciar Sesion", "Registro", "Salir"};
            while (true) {
                Object x = JOptionPane.showInputDialog(null, "Seleccione una acción",
                        "Banco remoto", JOptionPane.QUESTION_MESSAGE, null, opciones, "Escoja una opción");
                //SALIR O CLICK EN CANCELAR
                if (x == null || x.toString().substring(0, 1).equals("S")) {
                    System.exit(0);
                } else {

                    //INICIO DE SESION
                    if (x.toString().substring(0, 1).equals("I")) {
                        String user = JOptionPane.showInputDialog("Usuario");
                        String pwd = JOptionPane.showInputDialog("Contraseña");
                        if (miCallcenter.iniciarSesion(user,pwd)==1) {
                            while (true) {
                                String opcionesInicio[] = {"Modificar usuario", "Borrar usuario", "Adicionar dinero", "Retirar dinero", "Obtener saldo", "Salir"};
                                Object logeado = JOptionPane.showInputDialog(null, "Seleccione una acción",
                                        "Banco remoto", JOptionPane.QUESTION_MESSAGE, null, opcionesInicio, "Escoja una opción");
                                if (logeado == null || logeado.toString().substring(0, 1).equals("S")) {
                                    System.exit(0);}
                    
                                switch (logeado.toString()) {
                                    case "Modificar usuario":
                                        miCallcenter.modificarCuenta(JOptionPane.showInputDialog("Nombre (dejar en blanco si no)"),
                                                JOptionPane.showInputDialog("contraseña(dejar en blanco si no)"));
                                        break;
                                    case "Borrar usuario":
                                        boolean eliminado = miCallcenter.eliminarCuenta(JOptionPane.showInputDialog("contraseña"));
                                        if (eliminado) {
                                            System.exit(0);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La cuenta no pudo ser borrada");
                                        }
                                        break;
                                    case "Adicionar dinero":
                                        int adicionar = Integer.parseInt( JOptionPane.showInputDialog("Cantidad"));
                                        JOptionPane.showMessageDialog(null, "Status:" + miCallcenter.adicionarDinero(adicionar));
                                        break;
                                    case "Retirar dinero":
                                        int retirar = Integer.parseInt( JOptionPane.showInputDialog("Cantidad"));
                                        JOptionPane.showMessageDialog(null, "Status:" + miCallcenter.retirarDinero(retirar));
                                        break;
                                    case "Obtener saldo":
                                        JOptionPane.showMessageDialog(null, "Saldo: " + miCallcenter.ObtenerSaldo());
                                        break;
                                    case "Salir":
                                        System.exit(0);
                                        break;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encuentra registrado");
                        }
                    }

                    //REGISTRO
                    if (x.toString().substring(0, 1).equals("R")) {
                        //nombre usuario pwd
                        String nombreRegistro = "";
                        while (nombreRegistro.isEmpty()) {
                            nombreRegistro = JOptionPane.showInputDialog("Nombre de usuario");
                        }

                        String usrRegistro = "";
                        while (usrRegistro.isEmpty()) {
                            usrRegistro = JOptionPane.showInputDialog("Usuario");
                        }

                        String usrPassword = "";
                        while (usrPassword.isEmpty()) {
                            usrPassword = JOptionPane.showInputDialog("Contraseña");
                        }
                        miCallcenter.registro(nombreRegistro, usrPassword, usrPassword);
                        JOptionPane.showMessageDialog(null, "Registrado");
                    }
                }

            }

//            System.out.println(x+" " + res);
        } catch (Exception e) {
            System.err.println(" System exception" + e + java.net.InetAddress.getLocalHost().getHostAddress());
//            e.printStackTrace();
        }
        System.exit(0);
    }
}
