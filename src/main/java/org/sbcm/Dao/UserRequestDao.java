package org.sbcm.Dao;

import javafx.collections.ObservableList;
import org.sbcm.Model.UserRequest;

import java.net.HttpURLConnection;
import java.net.URI;

public class UserRequestDao implements CRUD<UserRequest> {
    HttpURLConnection connection;
    URI uri;
    @Override
    public ObservableList<UserRequest> getAllResources() throws Exception {
        return null;
    }

    @Override
    public int postResourse(UserRequest userRequest) throws Exception {
        return 0;
    }

    @Override
    public UserRequest getOneResourceById(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteResource(int id) throws Exception {

    }

    @Override
    public void putResource(UserRequest userRequest) throws Exception {


    }

    @Override
    public ObservableList<UserRequest> getAllResourcesByName(String name) throws Exception {
        return null;
    }

    @Override
    public void putAssistance(UserRequest userRequest) throws Exception {

    }

    @Override
    public void setConnection(HttpURLConnection connection) {

    }

    @Override
    public URI getUri() {
        return null;
    }
}
