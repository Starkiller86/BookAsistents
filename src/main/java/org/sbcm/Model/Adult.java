package org.sbcm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.fxml.FXML;

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
    @JsonProperty("edad")
    private int edad;
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
    @JsonProperty("domicilio")
    private String domicilio;
    @JsonProperty("numeropersonal")
    private String numeropersonal;
    @JsonProperty("numeroemergencia")
    private String numeroemergencia;

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
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumeropersonal() {
        return numeropersonal;
    }

    public void setNumeropersonal(String numeropersonal) {
        this.numeropersonal = numeropersonal;
    }

    public String getNumeroemergencia() {
        return numeroemergencia;
    }

    public void setNumeroemergencia(String numeroemergencia) {
        this.numeroemergencia = numeroemergencia;
    }
}
