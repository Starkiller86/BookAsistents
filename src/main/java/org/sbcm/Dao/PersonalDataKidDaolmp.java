package org.sbcm.Dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.sbcm.Model.PersonalDataKid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class PersonalDataKidDaolmp implements CRUD<PersonalDataKid>{
    URI uri;
    HttpURLConnection connection;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ObservableList<PersonalDataKid> getAllResources() throws Exception {
        return null;
    }

    @Override
    public int postResourse(PersonalDataKid personalDataKid) throws Exception {
        uri = new URI(host+":4040/sbcm/personalK");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 10; x64)");
        connection.setDoOutput(true);
        JSONObject json = new JSONObject(mapper.writeValueAsString(personalDataKid));
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
    public PersonalDataKid getOneResourceById(int id) throws Exception {
        return null;
    }


    @Override
    public void deleteResource(int id) throws Exception {

    }

    @Override
    public void putResource(PersonalDataKid personalDataKid) throws Exception {

    }

    @Override
    public ObservableList<PersonalDataKid> getAllResourcesByName(String name) throws Exception {
        return null;
    }



    @Override
    public void putAssistance(PersonalDataKid personalDataKid) throws Exception {

    }

    @Override
    public void setConnection(HttpURLConnection connection) {

    }

    @Override
    public URI getUri() {
        return null;
    }


}
