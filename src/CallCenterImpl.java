
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
//import java.util.Calendar;

//
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import modelo.cuenta;
import modelo.propietario;
import servicios.conexion;
//

public class CallCenterImpl extends UnicastRemoteObject implements CallCenter {

    private cuenta cuenta = null;
    private propietario propietario = null;
    private Connection Conexion;

    public CallCenterImpl(String name) throws RemoteException, SQLException, ClassNotFoundException {
        super();
        this.Conexion = conexion.obtener();
    }

    @Override
    public int iniciarSesion(String usuario, String Pwd) throws RemoteException {
        int ingresado = 0;
        try {
//            PreparedStatement consulta = Conexion.prepareStatement("SELECT P.idPropietario, P.nombre, p.usuario, p.pwd FROM Propietario P  WHERE usuario = ? and pwd = ?");
            PreparedStatement consulta = Conexion.prepareStatement("SELECT P.idPropietario, P.nombre, p.usuario, p.pwd, c.numCuenta, c.saldo ,c.fecha FROM Propietario P JOIN cuenta C on(p.idPropietario=c.idPropietario)  WHERE usuario = ? and pwd = ?");
            consulta.setString(1, usuario);
            consulta.setString(2, Pwd);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                ingresado=1;
                //System.out.println(resultado.getTimestamp("fecha"));
                //System.out.println(resultado.getInt("idPropietario"));
                this.propietario = new propietario(resultado.getInt("idPropietario"), resultado.getString("nombre"),
                        resultado.getString("usuario"), resultado.getString("pwd"));
                this.cuenta = new cuenta(resultado.getInt("numCuenta"), resultado.getInt("idPropietario"), resultado.getInt("saldo"), resultado.getDate("fecha"));
            }
            consulta.close();
        } catch (SQLException ex) {
        }
        //System.out.println(propietario.toString());
        //System.out.println(cuenta.toString());
        return ingresado;
    }

    @Override
    public String registro(String nombre, String usuario, String pwd) throws RemoteException {
        try {
            PreparedStatement INSERT = Conexion.prepareStatement("Insert into propietario (nombre, usuario, pwd) values ( ?, ?, ?)");
            INSERT.setString(1, nombre);
            INSERT.setString(2, usuario);
            INSERT.setString(3, pwd);
//            //System.out.println(INSERT.toString());
            int rowAffected = INSERT.executeUpdate();
            INSERT.close();
            crearCuenta( usuario, pwd);
        } catch (SQLException ex) {
        }
        return "Registrado";
    }

    @Override
    public void crearCuenta( String usuario, String pwd) throws RemoteException {
        try {
            PreparedStatement INSERT = Conexion.prepareStatement("Insert into cuenta(idPropietario, saldo, fecha) select idPropietario,0, CURRENT_DATE FROM propietario WHERE usuario = ? and pwd = ?");
//            INSERT.setInt(1, idPropietario);
            INSERT.setString(1, usuario);
            INSERT.setString(2, pwd);
            //System.out.println(INSERT.toString());
            int rowAffected = INSERT.executeUpdate();
            INSERT.close();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void modificarCuenta(String nombre, String pwd) throws RemoteException {
        String sql = "UPDATE propietario set ";
        if (!nombre.isEmpty() && !pwd.isEmpty()) {
            sql += " nombre = ? , pwd = ? WHERE idPropietario = ?";
        } else {
            if (!nombre.isEmpty() && pwd.isEmpty()) {
                sql += " nombre = ? WHERE idPropietario = ?";
            }
            if (!pwd.isEmpty() && nombre.isEmpty()) {
                sql += "pwd = ? WHERE idPropietario = ?";
            }
        }
//        //System.out.println(nombre.isEmpty() + " pwd " + pwd.isEmpty());
        try {
            PreparedStatement consulta = Conexion.prepareStatement(sql);
            //System.out.println(consulta.toString());
            if (!nombre.isEmpty() && !pwd.isEmpty()) {
                consulta.setString(1, nombre);
                consulta.setString(2, pwd);
                consulta.setInt(3, this.propietario.getIdPropietario());
                this.propietario.setNombre(pwd);
                this.propietario.setPassword(pwd);
                
            } else {
                if (!nombre.isEmpty() && pwd.isEmpty()) {
                    consulta.setString(1, nombre);
                    consulta.setInt(2, this.propietario.getIdPropietario());
                    this.propietario.setNombre(pwd);
                }
                if (!pwd.isEmpty() && nombre.isEmpty()) {
                    consulta.setString(1, pwd);
                    consulta.setInt(2, this.propietario.getIdPropietario());
                    this.propietario.setPassword(pwd);
                }
            }
            int rowAffected = consulta.executeUpdate();
            
            //System.out.println(String.format("Actualizar Row affected %d", rowAffected));
            consulta.close();
        } catch (SQLException ex) {
        }

    }

    @Override
    public boolean eliminarCuenta(String password) throws RemoteException {
        boolean eliminado=false;
        try {
            PreparedStatement consulta = Conexion.prepareStatement("DELETE FROM propietario  WHERE idPropietario = ? AND pwd= ?");
            consulta.setInt(1, this.propietario.getIdPropietario());
            consulta.setString(2, password);
            //System.out.println(consulta.toString());
            int rowAffected = consulta.executeUpdate();
            consulta.close();
            if(rowAffected==1){
            eliminado=true;
            }
        } catch (SQLException ex) {
        }
        return eliminado;
    }

    @Override
    public String adicionarDinero(int cantidad) throws RemoteException {
        if (cantidad < 0) {
            return "Valor menor a cero";
        }
        try {
            PreparedStatement Update = Conexion.prepareStatement("UPDATE cuenta set saldo =saldo+? WHERE idPropietario = ?");
            Update.setInt(1, cantidad);
//            Update.setInt(2, cantidad);
            Update.setInt(2, this.propietario.getIdPropietario());

//         ResultSet resultado = actualizar.executeQuery();
            int rowAffected = Update.executeUpdate();
            //System.out.println(String.format("Adicionar Row affected %d", rowAffected));
            //System.out.println(Update.toString());
            this.cuenta.setSaldo(this.cuenta.getSaldo()+cantidad);
            Update.close();
        } catch (SQLException ex) {
//         throw new SQLException(ex);
        }
//        String sql = "update cuenta set saldo=saldo+" + cantidad+" WHERE idPropietario="+idPropietario;
        return "Adicionado";
    }

    @Override
    public String retirarDinero(int cantidad) throws RemoteException {
        if (cantidad < 0) {
            return "Valor menor a cero";
        }
        if (cantidad > this.cuenta.getSaldo()) {
            return "Valor mayor a saldo";
        }

        try {
            PreparedStatement consulta = Conexion.prepareStatement("UPDATE cuenta set saldo = saldo-? WHERE idPropietario = ?");
            consulta.setInt(1, cantidad);
            consulta.setInt(2, this.propietario.getIdPropietario());
//         ResultSet resultado = consulta.executeQuery();
            int rowAffected = consulta.executeUpdate();
            //System.out.println(String.format("Retirar Row affected %d", rowAffected));
            //System.out.println(consulta.toString());
            this.cuenta.setSaldo(this.cuenta.getSaldo()-cantidad);
        } catch (SQLException ex) {
//         throw new SQLException(ex);
        }
//        String sql = "update cuenta set saldo=saldo-" + cantidad+" WHERE idPropietario="+idPropietario;
        return "Retirado";
    }

    @Override
    public double ObtenerSaldo() throws RemoteException {
        double saldo = 0;
        try {
            PreparedStatement consulta = Conexion.prepareStatement("SELECT saldo FROM cuenta WHERE idPropietario=?");
            consulta.setInt(1, this.propietario.getIdPropietario());
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
//            tarea = new Tarea(id_tarea, resultado.getString("titulo"), resultado.getString("descripcion"), resultado.getInt("nivel_de_prioridad"));
                saldo = resultado.getInt("saldo");
            }
            consulta.close();
        } catch (SQLException ex) {
        }
        return saldo;
    }

}


