package org.sbcm.Dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.sbcm.Model.PersonalDataAdult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class PersonalDataAdultDaoImp implements CRUD<PersonalDataAdult> {
    URI uri;
    HttpURLConnection connection;


    public PersonalDataAdultDaoImp() {
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public ObservableList<PersonalDataAdult> getAllResources() throws Exception {
        return null;
    }

    @Override
    public int postResourse(PersonalDataAdult personalDataAdult) throws Exception {
        uri = new URI(host+":4040/sbcm/personalA");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 10; x64)");
        connection.setDoOutput(true);
        JSONObject json = new JSONObject(mapper.writeValueAsString(personalDataAdult));
        System.out.println(json.toString(1));
        OutputStream salidaTransmision = connection.getOutputStream();
        byte[] transBytes = json.toString().getBytes(StandardCharsets.UTF_8);
        salidaTransmision.write(transBytes);
        if (connection.getResponseCode() != 200){
            throw new Exception();
        }
        InputStreamReader lectura = new InputStreamReader(connection.getInputStream());
        BufferedReader lecturaB = new BufferedReader(lectura);
        String line = lecturaB.readLine();
        StringBuilder lines = new StringBuilder();
        while (line != null){
            lines.append(line);
            line = lecturaB.readLine();
        }
        lecturaB.close();

        connection.disconnect();
        return Integer.parseInt(lines.toString());

    }

    @Override
    public PersonalDataAdult getOneResourceById(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteResource(int id) throws Exception {

    }

    @Override
    public void putResource(PersonalDataAdult personalDataAdult) throws Exception {

    }

    @Override
    public ObservableList<PersonalDataAdult> getAllResourcesByName(String name) throws Exception {
        return null;
    }

    @Override
    public void putAssistance(PersonalDataAdult personalDataAdult) throws Exception {

    }

    @Override
    public void setConnection(HttpURLConnection connection) {

    }

    @Override
    public URI getUri() {
        return null;
    }
}
