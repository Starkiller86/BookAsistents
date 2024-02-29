package org.sbcm.SingletonModels;

public class AdultSingleton {
    /**El patrón de diseño nos marca que tenemos que cumplir con varias reglas
    El atributo principal debe ser la propia instancia estatica y privada de la clase**/
    private static AdultSingleton adultSingleton;
    /**EL constructor debe ser privado
    Debajo podemos colocar los atributos que vamos a guardar de los usuarios**/
    private int id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String genero;
    private  String escolaridad;
    private String discapacidad;
    private String ocupacion;
    private int nVisitas;
    private String tipoDeVisitante;
    private String domicilio;
    private String npersonal;
    private String nemergencia;

    private AdultSingleton(){}
    /**Declarar el método getInstance para devolver la misma instancia en caso de ser creada en cualquier parte del código**/

    public static AdultSingleton getInstance(){
        if (adultSingleton != null){
            return adultSingleton;
        }
        else{
            adultSingleton = new AdultSingleton();
            return adultSingleton;
        }
    }
    /**Solo se genera getter y setter para los demás atributos sin contar la instancia**/
    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNpersonal() {
        return npersonal;
    }

    public void setNpersonal(String npersonal) {
        this.npersonal = npersonal;
    }

    public String getNemergencia() {
        return nemergencia;
    }

    public void setNemergencia(String nemergencia) {
        this.nemergencia = nemergencia;
    }
}
