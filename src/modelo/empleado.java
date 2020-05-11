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
public class empleado {
    Integer idEmpleado;
    String nombre;
    String codigo;
    boolean disponibilidad;
    tipoEmp tipoEmp;

    public empleado() {
    }

    public empleado(Integer idEmpleado, String nombre, String codigo, boolean disponibilidad, tipoEmp tipoEmp) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.codigo = codigo;
        this.disponibilidad = disponibilidad;
        this.tipoEmp = tipoEmp;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public tipoEmp getTipoEmp() {
        return tipoEmp;
    }

    public void setTipoEmp(tipoEmp tipoEmp) {
        this.tipoEmp = tipoEmp;
    }
    
    
}
