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
public class tipoEmp {
    Integer idTipoEmp;
    String nombreTipo;

    public tipoEmp() {
    }

    public tipoEmp(Integer idTipoEmp, String nombreTipo) {
        this.idTipoEmp = idTipoEmp;
        this.nombreTipo = nombreTipo;
    }

    public Integer getIdTipoEmp() {
        return idTipoEmp;
    }

    public void setIdTipoEmp(Integer idTipoEmp) {
        this.idTipoEmp = idTipoEmp;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
    
    
}
