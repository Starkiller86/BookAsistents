package org.sbcm.Dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbcm.Model.Adult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**AdultRegisterdaolmp es la implementación del CRUD, de tal manera que mediante las conexiones HTTP, es posible poder utilizar los registros o datos que se
     * encuentran en la base de datos, haciendo que se puedan Crear, Actualizar, Leer y Eliminar cualquier registro mediante las conexiones HTTP, estos métodos
     * son los que se implementan con el CRUD y que hacen que se realicen estas acciones con los registros**/

public class AdultRegisterdaoImp implements CRUD<Adult>{
    //Estas son las variables de instancia para la conexión Http, las cuales ayudaran a que la base de datos se conecte con el codigo
    HttpURLConnection connection;
    URI uri;
    boolean usingTest = true;

    /***
     * Este método se encarga de poder devolver todos los registros en la base de datos, este utiliza las variables de conexión HttpURLConnection, las cuales
     * hacen que conecten las bases de datos con el código, es un método que va a devolvder un JSOnArray, es decir todos los registros de la base de datos
     * @return ListAdult es la variable que se va a retornar y en donde se encuentran todos los registros en la base de datos
     * @throws Exception se realiza cuando la conexion HTTP es diferente a 200, de otra forma continua el programa
     */
    @Override
    public ObservableList<Adult> getAllResources() throws Exception {
        /**Uri o (Uniform resource identifier) es un identificador único que se encarga de localizar o identificar algún único recurso, en este caso se utiliza esta Uri,
         * para poder identificar los datos que se encuentran en esa localizacion, haciendo que se guarden en la variable uri, guardando una localizacion de datos.**/
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults");

        /** HttpURLConnection sirve para poder crear una coneccion única con la localización de la base de datos, abriendo una conexión.**/
            connection = (HttpURLConnection) uri.toURL().openConnection();

        /**El setResequesMethod, se utiliza para determinar la acción que se realizara en el getAllResources, en este caso se utilizo GET por que lo que se hará en esta parte del
         * código es obtener todos los datos de la base.**/
        connection.setRequestMethod("GET");

        /**Este es un encabezado que se usa para determinar que el método que se esta usando tiene cuerpo de respuesta.**/
        connection.setRequestProperty("Accept", "application/json");

        /**Este encabezado es utilizado para indicar el remitente sistema operativo o aplicación que se esta utilizando, describiendo todas las características que contiene.**/
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");

        /**ESta línea de código tiene la función de mostrar todas las propiedades o encabezados de este método.**/
        System.out.println(connection.getRequestProperties());

        /**En este if lo que se hará es comprobar si la conexión esta bien, ya que, si es igual a 200, la conxión es bueno y puede continuar el programa, dentro del if hay
         * exepciones, es decir que si no es igual a 200 se va a mostrar el error y en caso de que en la conexión el número sea igual a 200, lo que se hará es continuar con
         * el programa aunque este el Exception, pues esto quire decir que no hay error y que el programa está bien.**/
        if(connection.getResponseCode()!=200){
            throw new Exception();
        }
        /**InputStreamReader es un puente de flujo de bytes y es utilizado para leer bytes y descodificar a caracteres **/
        InputStreamReader lecturaVytesStram = new InputStreamReader(connection.getInputStream());

        /**BufferedReader es utilizado principalmente para poder leer los caracteres que se obtienen con InputStreamReader almacenandolos en un buffer para poder leer cada línea de texto**/
        BufferedReader lecturabuffer = new BufferedReader(lecturaVytesStram);

        /**En esta línea de código se declara una variable la cual se encargara de leer cada línea o caracteres que se encuentran en el buffer de igual manera se usa el readLine() para
         * poder leer un byte como un entero.**/
        String linea = lecturabuffer.readLine();

        /**StringBuilder se encarga de modificar una cadena de caracteres sin que se cree algún objeto y su función tambien es crear el cuerpo de las cadenas de texto para colocar
         * el texto por línea.**/
        StringBuilder lineas = new StringBuilder();

        /**Este siglo While es utilizado para que se repita el número de líneas que tiene el cuerpo y cada vez que se encuentre alguna repeticion esta se añade a un constructor**/
        while (linea != null){
            lineas.append(linea);
            linea = lecturabuffer.readLine();//se encargara de leer la línea que continua en el lecturabuffer,haciendo que cuando el valor sea null, este salga del ciclo While.
        }
        lecturabuffer.close();//aquí se cierra la lectura de lecturabuffer para poder hacer que haya más espacio con esa función.

        /**JSONArray tiene la función de guardar una serie de datos o elementos**/
        JSONArray jsonListAdult = new JSONArray(lineas.toString());//en este punto se crea un ObservableList para poder guardar la respuesta de los JSON.
        ObservableList<Adult> ListAdult = FXCollections.observableArrayList();//en este punto se crea un ObservableList para poder guardar la respuesta de los JSON.
        /**Este ciclo foreach se encargara de que todos los objetos contenga JSON en objetos JAVA**/
        for (Object o: jsonListAdult){ //se crea un foreach para poder observar las respuestas JSON.
            JSONObject jsonObject = (JSONObject) o; //se crea un  JSONObject de tal manera que se hace una conversión del objeto(o) a JSONObject.
            ObjectMapper objectMapper = new ObjectMapper();//se crea un objeto objectMapper
            Adult adult = objectMapper.readValue(jsonObject.toString(), Adult.class);//en esta línea de código lo que se hara es crear un objeto con el cual con ayuda de objectMapper
            //este lea lo que hay en jsonObject en forma de texto basado en la estructura del objeto Adult.
            ListAdult.add(adult);//se añade lo que se encuentra en adult a ListAdult.
        }

        /**En esta línea de código se muestran los encabezados que se realizaron.**/
        System.out.println(connection.getHeaderFields());

        /**Aqui la conxión se desconecta haciendo que retorne ListAdult.**/
        connection.disconnect();

        /**En esta línea de código se van a retornar todos los datos JSON en objetos JAVA con ListAdult.**/
        return ListAdult;
    }

    /***
     * El método Post se refiere a crear un nuevo registro en la base de datos y
     * en la interfaz mediante las variables HttpURLConnection, las cuales ayudaran
     * a que el registro en coloque en la base de datos.
     * @param adult es el objeto que se genera de Adult y que se utiliza en este método con todos los datos
     * @throws Exception se va a retornar una excepción, cuando la respuesta del código sea distinta a 200, de otra forma continua con el programa
     */

    @Override
    public int postResourse(Adult adult) throws Exception {
        {
            uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults");

            connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent","JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");

            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jsonAdult = new JSONObject(objectMapper.writeValueAsString(adult));
            OutputStream transmisionSalida = connection.getOutputStream();
            byte[] salidaBytes = jsonAdult.toString().getBytes(StandardCharsets.UTF_8);
            transmisionSalida.write(salidaBytes);
            if(connection.getResponseCode()!=200){
                throw  new Exception();
            }//Se supone que cómo solo enviabamos aquí terminaba el metodo post, pero ahora regresa un entero, entonces vamos a recibir ese entero

            InputStreamReader lecturaTransmision = new InputStreamReader(connection.getInputStream());
            BufferedReader lecturaBuffer = new BufferedReader(lecturaTransmision);
            String line = lecturaBuffer.readLine();
            StringBuilder lines = new StringBuilder();
            while (line!=null){
                lines.append(line);
                line = lecturaBuffer.readLine();
            }
            lecturaBuffer.close();
            int idRegistrado = Integer.parseInt(lines.toString());


            connection.disconnect();
            return idRegistrado;
        }
    }

    /***
     * Este es un método utilizado para poder obtener un registro especifico que se requieran mediente la variable de identificación, es utilizada
     * para poder mostrar cualquier registro que se indique, además  que esto se realizara por medio de las variables de conexión HttpURLConnection las cuales
     * hacen que se encuentre el registro que se indiquen en la base de datos, en este método es importante que se devuelvan JSON como
     * resultado
     * @param id esta variable se utiliza para poder mostrar el registro que se requirio, ya que, para cada registro es distinta y es importante
     *         por que con esa variable se localiza el registro en la base de datos
     * @return adult es lo que va a retornar, dando como resultado el registro que se indico en este método
     * @throws Exception es lo que se retornara cuando la coneccion sea distinta a 200, de otro modo continua el código
     */

    @Override
    public Adult getOneResourceById(int id) throws Exception {
            uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults"+id);
            if(!usingTest)
                connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent","JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
            if(connection.getResponseCode()!=200){
                throw new Exception();
            }
            InputStreamReader lecturaTransmision = new InputStreamReader(connection.getInputStream());
            BufferedReader lecturabuffer = new BufferedReader(lecturaTransmision);
            String linea = lecturabuffer.readLine();
            StringBuilder lineas = new StringBuilder();
            while (linea != null){
                lineas.append(linea);
                linea = lecturabuffer.readLine();
            }
            lecturabuffer.close();
            JSONObject jsonAdult = new JSONObject(lineas.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            Adult adult = objectMapper.readValue(jsonAdult.toString(),Adult.class);
            connection.disconnect();
        return adult;
    }

    /***
     * Este método se encarga de poder eliminar el registro que se indique mediante el id en la base de datos, ya que,
     * esto se realiza mediante las variables de conexiones las cuales ayudan a poder conectar con los datos en donde
     * se encuentran los registros
     * @param id es la variable que se utilizara para poder eliminar el registro que se requiere en la base de datos
     * @throws Exception es lo que se creara si la conexión es distinta a 200, de otra forma continuara el programa
     */

    @Override
    public void deleteResource(int id) throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults/"+id);

            connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        if(connection.getResponseCode()!=200){
            throw new Exception();
        }
        connection.disconnect();
    }

    /***
     * En este método se actualizan los datos de los registros en la base de datos, mediante su variable de
     * identificación, esto se realiza mediante las variables de conexión las cuales se encargaran de
     * buscar el registro que se índica para poder actualizar los datos, este metodo utiliza el formato JSON cuando
     * se devuelven los registros.
     * @param adult es el objeto de Adult, el cual sera la variable de identificación para encontrar el registro en la base de datos
     * @throws Exception esto se retornara cuando la conexión HTTP sea diferente a 200, de otra forma continua el código
     */

    @Override
    public void putResource(Adult adult) throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        connection.setDoOutput(true);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonAdult = new JSONObject(objectMapper.writeValueAsString(adult));
        OutputStream transmisionSalida = connection.getOutputStream();
        byte[] salidaBytes = jsonAdult.toString().getBytes(StandardCharsets.UTF_8);
        transmisionSalida.write(salidaBytes);
        if (connection.getResponseCode()!=200){
            throw new Exception();
        }
        connection.disconnect();
    }

        /**
         * La implementación de obtener todas las coincidencia por el nombre no es muy distinto a obtener todos los recursos, es demasiado parecido
         * @param name nombre estrella que se desea buscar
         * @return
         */
        @Override
        public ObservableList<Adult> getAllResourcesByName(String name) throws Exception{
            //Al parecer los espacios no los interpreta como el navegador con %20 entonces vamos a colocarlos
            //Entonces creamos una nueva cadena donde remplacemos los espacios por %20
            String nameReplace = name.replace(" ", "%20");//Aquí indicamos que remplace todos los espacios vacios con %20
            //Tenemos un uri con el endpoint al que vamos a acceder
            uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults/search/" + nameReplace);
            connection = (HttpURLConnection) uri.toURL().openConnection();
            //ya que tenemos la conexión vamos a darle sus propiedades correspondientes
            //Indicamos que tipo de metodo HTTP es
            connection.setRequestMethod("GET");
            //indicamos el tipo de respuesta que queremos
            connection.setRequestProperty("Accept", "application/json");
            //indicamos desde donde estamos realizando la conexión
            connection.setRequestProperty("User-Agent", "JAVAFX 1.0 SNAPSHOT (Windows 11, x64)");

            //verificamos si la conexión ha sido exitosa
            if (connection.getResponseCode() != 200)
                throw new Exception();
            //En caso de ser correcta continuará con el codigo

            //Hacemos la lectura utilizando la libreria Streams de net

            InputStreamReader lecturaBytes = new InputStreamReader(connection.getInputStream());
            BufferedReader lecturaBuffer = new BufferedReader(lecturaBytes);
            String line = lecturaBuffer.readLine();
            StringBuilder lines = new StringBuilder();
            while(line != null){
                lines.append(line);
                line = lecturaBuffer.readLine();
            }
            lecturaBuffer.close();

            //Hasta este punto ya tenemos la respuesta de todas la coincidencias en lines, vamos a colocarlo en un JSONArray

            JSONArray arrayCoincidencias = new JSONArray(lines.toString());
            //Creamo la lista observable para almacenar los resultados de la consulta en objetos java que podamos colocar en la tabla
            ObservableList<Adult> listaCoincidencias = FXCollections.observableArrayList();
            //Neceistamos un objectMapper para mapear los valores de los json en los objetos java
            ObjectMapper mapper = new ObjectMapper();
            //Creamos un foreach que por cada elemento del array lo colocará de manera ordenada en la lista observable

            for (Object o: arrayCoincidencias) {
                JSONObject jsonObject = (JSONObject) o;
                Adult adult = mapper.readValue(jsonObject.toString(), Adult.class);
                listaCoincidencias.add(adult);
            }
            //Hasta este punto ya tenemos una lista observable con todas las coincidencias
            //Solo queda desconectar y retornar
            connection.disconnect();
            return listaCoincidencias;
        }

    @Override
    public void putAssistance(Adult adult) throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults/mark");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type" , "application/json");
        connection.setRequestProperty("User-Agent" , "JAVAFX/1.0 SNAPSHOT (Windows 10; x64)");
        connection.setDoOutput(true);

        ObjectMapper mapper = new ObjectMapper();
        JSONObject json = new JSONObject(mapper.writeValueAsString(adult));
        OutputStream transmisionSalida = connection.getOutputStream();
        byte[] salidaBytes = json.toString().getBytes(StandardCharsets.UTF_8);
        transmisionSalida.write(salidaBytes);
        if (connection.getResponseCode() != 200)
            throw new Exception();

        connection.disconnect();
    }

    @Override
    public void setConnection(HttpURLConnection connection) {
    }

    @Override
    public URI getUri() {
        return null;
    }
}
