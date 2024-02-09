package org.sbcm.SingletonModels;

public class KidSinglenton {
    /**El atributo principal debe ser la propia instancia estatica y privada de la clase**/
    public static KidSinglenton kidSinglenton;
    /**El constructor debe de ser privado
    Debajo podemos colocar los atributos que vamos a guardar de los usuarios**/
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String escolaridad;
    private String discapacidad;
    private String ocupacion;
    private int nVisitas;
    private String tipoDeVisitante;
    private KidSinglenton(){}

    /**Declarar el método getInstance para devolver la misma instancia en caso de ser creada en cualquier parte del código**/
    public static KidSinglenton getInstance() {
        if (kidSinglenton != null) {
            return kidSinglenton;
        } else {
            kidSinglenton = new KidSinglenton();
            return kidSinglenton;
        }
    }
    /**Solo se genera getter y setter para los demás atributos sin contar la instancia**/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
