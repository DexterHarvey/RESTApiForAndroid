package com.example.RESTApiForAndroid;

import model.PackagesEntity;

import com.google.gson.Gson;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/packages")
public class PackagesResource {
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<PackagesEntity> packages;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        packages = em.createQuery("SELECT p FROM PackagesEntity p").getResultList();

        em.close();
        factory.close();

        return new Gson().toJson(packages);
    }
}