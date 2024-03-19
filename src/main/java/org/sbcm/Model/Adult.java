package org.sbcm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Adult {
/**Este modelo de Adult en donde se encuentra la estructura de los datos que se deben de tener en los registros y cada uno de estos
 * contiene campos que corresponden con la base de datos
 * **/
    @JsonProperty("id")
    private int id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellido")
    private String apellido;
    @JsonProperty("fechaNacimiento")
    private Date fechaNacimiento;
    @JsonProperty("genero")
    private String genero;
    @JsonProperty("escolaridad")
    private  String escolaridad;
    @JsonProperty("discapacidad")
    private String discapacidad;
    @JsonProperty("ocupacion")
    private String ocupacion;
    @JsonProperty("nVisitas")
    private int nVisitas;
    @JsonProperty("tipoDeVisitante")
    private String tipoDeVisitante;
    private String simmpleDate;

    public String getSimmpleDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(fechaNacimiento);
    }

    public void setSimmpleDate(String simmpleDate) {
        this.simmpleDate = simmpleDate;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNVisitas() {
        return nVisitas;
    }

    public void setNVisitas(int nVisitas) {
        this.nVisitas = nVisitas;
    }

    public String getTipoDeVisitante() {
        return tipoDeVisitante;
    }

    public void setTipoDeVisitante(String tipoDeVisitante) {
        this.tipoDeVisitante = tipoDeVisitante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }


}
