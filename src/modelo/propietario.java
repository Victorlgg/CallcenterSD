/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Victor
 */
public class propietario {
    private Integer idPropietario;
    private String nombre;
    private String usuario;
    private String password;

    public propietario() {
        this.idPropietario = null;
        this.nombre = null;
        this.usuario = null;
        this.password = null;
    }

    public propietario(Integer idPropietario, String nombre, String usuario, String password) {
        this.idPropietario = idPropietario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

    
    
    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "propietario{" + "idPropietario=" + idPropietario + ", nombre=" + nombre + ", usuario=" + usuario + ", password=" + password + '}';
    }
    
}
