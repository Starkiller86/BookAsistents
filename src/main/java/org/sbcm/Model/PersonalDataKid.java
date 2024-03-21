package org.sbcm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDataKid {
    @JsonProperty("id")
    private Integer id;
    //Al igual que en Spring interpretamos la llave foranea como un objeto del otro mmodelo que hace referencia, no como int tal cual
    @JsonProperty("idKid")
    private Kid idKid;
    @JsonProperty("numeropersonal")
    private String numeropersonal;
    @JsonProperty("numeroemergencia")
    private String numeroemergencia;
    @JsonProperty("domicilio")
    private String domicilio;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Kid getIdKid() {
        return idKid;
    }

    public void setIdKid(Kid idKid) {
        this.idKid = idKid;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
