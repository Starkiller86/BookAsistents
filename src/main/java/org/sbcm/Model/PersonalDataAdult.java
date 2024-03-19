package org.sbcm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDataAdult {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("idAdulto")
    private Adult idAdulto;

    @JsonProperty("numeropersonal")
    private String numeropersonal;
    @JsonProperty("numeroemergencia")
    private String numeroemergencia;
    @JsonProperty("domicilio")
    private String domicilio;

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adult getIdAdulto() {
        return idAdulto;
    }

    public void setIdAdulto(Adult idAdulto) {
        this.idAdulto = idAdulto;
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
