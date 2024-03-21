package org.sbcm.Model.SingletonModels;

import org.sbcm.Model.Kid;

public class KidSinglenton extends Kid {
    /**El atributo principal debe ser la propia instancia estatica y privada de la clase**/
    public static KidSinglenton kidSinglenton;
    /**El constructor debe de ser privado
    Debajo podemos colocar los atributos que vamos a guardar de los usuarios**/

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
}
