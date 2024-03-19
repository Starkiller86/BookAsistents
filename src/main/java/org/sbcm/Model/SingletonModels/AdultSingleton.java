package org.sbcm.Model.SingletonModels;

import org.sbcm.Model.Adult;

public class AdultSingleton extends Adult {
    /**El patrón de diseño nos marca que tenemos que cumplir con varias reglas
    El atributo principal debe ser la propia instancia estatica y privada de la clase**/
    private static AdultSingleton adultSingleton;
    /**EL constructor debe ser privado
    Debajo podemos colocar los atributos que vamos a guardar de los usuarios**/



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

    }
