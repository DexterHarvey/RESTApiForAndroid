package com.example.RESTApiForAndroid;

import model.CustomersEntity;
import model.PackagesEntity;

import com.google.gson.Gson;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.json.JsonValue;
import java.util.List;

@Path("/customers")
public class CustomersResource {
    @GET
    @Path("/getlogin/{ Username }/{ Password }")
    @Produces(MediaType.APPLICATION_JSON)
    public String validateLogin(@PathParam("Username") String inputUsername, @PathParam("Password") String inputPassword) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        String select = "SELECT c FROM CustomersEntity c WHERE c.username = :username AND c.password = :password";
        Query query = em.createQuery(select);
        query.setParameter("username", inputUsername);
        query.setParameter("password", inputPassword);
        try {
            CustomersEntity customer = (CustomersEntity) query.getSingleResult();
            em.close();
            factory.close();
            return new Gson().toJson(customer);
        }
        catch (NoResultException ex){
            return "{'invalid' : 'login'}";
        }
    }
}
