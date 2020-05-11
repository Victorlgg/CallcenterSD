/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.Calendar;


/**
 *
 * @author Victor
 */
public class cuenta {
    private final Integer numCuenta;
    private Integer idPropietario;
    private Integer saldo;
    private Date fechaRegistro;
    
    public cuenta(){
    this.numCuenta = null;
    this.idPropietario=null;
    this.saldo=null;
    this.fechaRegistro=null;
    }

    public cuenta(int numCuenta, int idPropietario, int saldo, Date fechaRegistro) {
        this.numCuenta = numCuenta;
        this.idPropietario = idPropietario;
        this.saldo = saldo;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int dinero) {
        this.saldo = dinero;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "cuenta{" + "numCuenta=" + numCuenta + ", idPropietario=" + idPropietario + ", dinero=" + saldo + ", fechaRegistro=" + fechaRegistro + '}';
    }
    
    
}
/*
public class Tarea {
   private final Integer id_tarea;
   private String titulo;
   private String descripcion;
   private Integer nivel_de_prioridad;
   public Tarea() {
      this.id_tarea = null;
      this.titulo = null;
      this.descripcion = null;
      this.nivel_de_prioridad = null;
   }
   public Tarea(Integer id_tarea, String titulo, String descripcion, Integer nivel_de_prioridad) {
      this.id_tarea = id_tarea;
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.nivel_de_prioridad = nivel_de_prioridad;
   }
   public Integer getId_tarea() {
      return id_tarea;
   }
   public String getTitulo() {
      return titulo;
   }
   public String getDescripcion() {
      return descripcion;
   }
   public Integer getNivel_de_prioridad() {
      return nivel_de_prioridad;
   }
   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
   public void setNivel_de_prioridad(Integer nivel_de_prioridad) {
      this.nivel_de_prioridad = nivel_de_prioridad;
   }
   @Override
   public String toString() {
      return "Tarea{" + "id_tarea=" + id_tarea + ", titulo=" + titulo + ", descripcion=" + descripcion + ", nivel_de_prioridad=" + nivel_de_prioridad + '}';
   } 
}


*/