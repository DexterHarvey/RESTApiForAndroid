package com.example.RESTApiForAndroid;

import com.google.gson.Gson;
import model.CoordinatesEntity;
import model.PackagesEntity;
import model.PkgDestinationsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/destinations")
public class PkgDestinationResource {
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<PkgDestinationsEntity> dests;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        dests = em.createQuery("SELECT c FROM PkgDestinationsEntity c").getResultList();

        em.close();
        factory.close();

        return new Gson().toJson(dests);
    }
}