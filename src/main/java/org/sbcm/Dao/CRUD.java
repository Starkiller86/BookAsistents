package org.sbcm.Dao;

import javafx.collections.ObservableList;
import org.sbcm.Model.Adult;

import java.net.HttpURLConnection;
import java.net.URI;

/***
 * El CRUD proporciona método abstractos los cuales se colocan el los objetos
 * y que hacen que se puedan realizar cada una de las operaciones CRUD (Agregar, Leer, Eliminar, Actualizar) en los
 * objetos en donde se implemento estos metodos
 * @param <Resource> esta es el modelo que se utiliza para realizar las operaciones CRUD
 */

public interface CRUD<Resource> {
    /***
     * Este método se encarga de mostrar todos los datos que se encuentran en la base de datos
     * mediante conecciones HTTP, las cuales realiza usando GET en el metodo
     * @return observablelist es lo que retorna con todos los registros de la base de datos
     * @throws Exception es lo que se coloca cuando la conexión HTTP es diferente a 200, de otra forma continua el programa
     */
    ObservableList<Resource> getAllResources() throws Exception;

    /***
     * Este método se encarga de poder generar un registro en la base de datos mediante las conexiones HTTP y
     * mediante el uso de POST en el método
     * @param resource es el objeto JAVA y sus datos
     * @throws Exception es lo que se realiza cuando la conexión HTTP es diferente a 200 de otra forma continua el código
     */
    void postResourse(Resource resource) throws Exception;

    /***
     * En este método se obtienen todos los datos de un registro en especifico con su id, y mediante las conecciones
     * HTTP y de igual manera usando GET en el método
     * @param id es el identificador del registro que se requiere en la base de datos
     * @return el modelo JAVA los datos
     * @throws Exception es lo que se realiza cuando la conexión HTTP es diferente a 200, de otra forma continua el código
     */
    Resource getOneResourceById(int id) throws Exception;

    /***
     * Es un método que se encarga de eliminar un registro en la base de datos mediante el id, esto se realiza por
     * medio de la conexión HTTP y usando DELETE en el método
     * @param id es el identificador de los registros que se requieren en la base de datos
     * @throws Exception es lo que se realiza cuando la conexión HTTP es diferente a 200, de otra manera continua el código
     */
    void deleteResource(int id) throws Exception;

    /***
     * Este método se encarga de actualizar un registro en la base de datos, esto se realiza mediante las conexiones HTTP y
     * del uso de PUT en el método
     * @param resource es el objeto JAVA con los datos
     * @throws Exception es lo que se realiza cuando una conexión HTTP es diferente a 200, de otra manera continua el código
     */
    void putResource(Resource resource) throws Exception;

    /**
     * Es un método que se encarga de obtener todos los registros que tengan un coincidencia con el nombre que se le propociona
     * por parametro, ordenando la mejor coincidencia a la peor
     * @param name nombre estrella que se desea buscar
     * @return Lista Observable con todas las coincidencias
     */
    ObservableList<Resource> getAllResourcesByName(String name) throws Exception;

    void putAssistance(Resource resource) throws Exception;
    void setConnection(HttpURLConnection connection);
    URI getUri();

}
