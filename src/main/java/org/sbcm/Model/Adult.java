package org.sbcm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Adult {
/**Este modelo de Adult en donde se encuentra la estructura de los datos que se deben de tener en los registros y cada uno de estos
 * contiene campos que corresponden con la base de datos
 * **/
    @JsonProperty("id")
    private int id;
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

}
