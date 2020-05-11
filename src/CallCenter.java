public interface CallCenter extends java.rmi.Remote
{
 //RemoteInterface
public int iniciarSesion(String usuario, String Pwd) throws java.rmi.RemoteException;
public String registro( String nombre, String usuario, String pwd) throws java.rmi.RemoteException;
public void crearCuenta( String usuario, String pwd) throws java.rmi.RemoteException;
public void modificarCuenta(String nombre, String pwd) throws java.rmi.RemoteException; 
public boolean eliminarCuenta(String password) throws java.rmi.RemoteException;
public String adicionarDinero( int cantidad) throws java.rmi.RemoteException;
public String retirarDinero( int cantidad) throws java.rmi.RemoteException;
public double ObtenerSaldo() throws java.rmi.RemoteException; 

}

