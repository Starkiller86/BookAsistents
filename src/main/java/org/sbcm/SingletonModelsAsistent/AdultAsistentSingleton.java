package org.sbcm.SingletonModelsAsistent;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AdultAsistentSingleton {
    private static AdultAsistentSingleton adultAsistentSingleton;
    private int id;
    private int edad;
    private String genero;
    private  String escolaridad;
    private String discapacidad;
    private String ocupacion;
    private int nVisitas;
    private String tipoDeVisitante;
    private TextField nRegistroAsistenciaRA;
    private TextField nVisitasAsistenciaRA;
    private DatePicker fechasVisitasAsistenciaRA;
    public static AdultAsistentSingleton getInstance(){
        if(adultAsistentSingleton != null){
            return adultAsistentSingleton;
        }else {
            adultAsistentSingleton = new AdultAsistentSingleton();
            return adultAsistentSingleton;
        }
    }

    public TextField getnRegistroAsistenciaRA() {
        return nRegistroAsistenciaRA;
    }

    public void setnRegistroAsistenciaRA(TextField nRegistroAsistenciaRA) {
        this.nRegistroAsistenciaRA = nRegistroAsistenciaRA;
    }

    public TextField getnVisitasAsistenciaRA() {
        return nVisitasAsistenciaRA;
    }

    public void setnVisitasAsistenciaRA(TextField nVisitasAsistenciaRA) {
        this.nVisitasAsistenciaRA = nVisitasAsistenciaRA;
    }

    public DatePicker getFechasVisitasAsistenciaRA() {
        return fechasVisitasAsistenciaRA;
    }

    public void setFechasVisitasAsistenciaRA(DatePicker fechasVisitasAsistenciaRA) {
        this.fechasVisitasAsistenciaRA = fechasVisitasAsistenciaRA;
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

    public int getnVisitas() {
        return nVisitas;
    }

    public void setnVisitas(int nVisitas) {
        this.nVisitas = nVisitas;
    }

    public String getTipoDeVisitante() {
        return tipoDeVisitante;
    }

    public void setTipoDeVisitante(String tipoDeVisitante) {
        this.tipoDeVisitante = tipoDeVisitante;
    }
}
