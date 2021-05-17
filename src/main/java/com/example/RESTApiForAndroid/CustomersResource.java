package com.example.RESTApiForAndroid;

import model.CustomersEntity;
import model.PackagesEntity;

import com.google.gson.Gson;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.json.JsonValue;
import java.util.List;

/**
 * API calls which interact with the Customers table.
 * Created by Dexter and Eric as noted atop method calls.
 */

//Dexter
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

    // Eric
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateCustomer(String CustJson) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        Gson gson = new Gson();
        CustomersEntity cust = gson.fromJson(CustJson, CustomersEntity.class);

        em.getTransaction().begin();

        // inserts entity and returns copy of new entity
        CustomersEntity result = em.merge(cust);

        em.getTransaction().commit();

        if(result!= null){
            return gson.toJson(cust);
        }
        else{
            return "{'message': 'Failed to update'}";
        }
    }

    // Eric
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postPackage(String jsonString)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        Gson gson = new Gson();
        CustomersEntity customersEntity = gson.fromJson(jsonString, CustomersEntity.class);
        System.out.println(customersEntity);
        em.getTransaction().begin();
        em.merge(customersEntity);
        em.getTransaction().commit();
//        if (result != null)
//        {
        return "{ 'message':'Update Successful!' }";
    }
}
