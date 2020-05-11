
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.server.*;
//import java.sql.Connection;
import java.sql.SQLException;
//import servicios.conexion;

public class CallCenterServer {

    public static void main(String args[]) throws java.net.UnknownHostException, SQLException, ClassNotFoundException {
        try {
//            CallCenterImpl misuma = new CallCenterImpl("MiSumador");
    Registry miRegistro = LocateRegistry.createRegistry(12345);
            miRegistro.rebind("Callcenter", new CallCenterImpl("Callcenter"));
            System.out.println("Servidor ON");
            
        } catch (RemoteException e) {
            System.err.println("System exception" + e);
             e.printStackTrace();
             
        }
        
    }
}
