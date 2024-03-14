package org.sbcm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDataAdult {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("idAdulto")
    private int idAdulto;
    @JsonProperty("domicilio")
    private String domicilio;
    @JsonProperty("numeropersonal")
    private String numeropersonal;
    @JsonProperty("numeroemergencia")
    private String numeroemergencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdAdulto() {
        return idAdulto;
    }

    public void setIdAdulto(int idAdulto) {
        this.idAdulto = idAdulto;
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
