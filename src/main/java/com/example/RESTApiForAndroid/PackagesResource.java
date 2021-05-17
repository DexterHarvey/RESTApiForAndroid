package com.example.RESTApiForAndroid;

import model.PackagesEntity;

import com.google.gson.Gson;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API calls which interact with the BookingDetails table.
 * Created by all 3 members, as noted atop methods..
 */

// Made by Dexter, bugfixes by Eric
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
    // Eric
    @GET
    @Path("/getallupcoming")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUpcoming() {
        List<PackagesEntity> packages;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        packages = em.createQuery("SELECT p FROM PackagesEntity p WHERE p.pkgStartDate > current_time ").getResultList();

        em.close();
        factory.close();

        return new Gson().toJson(packages);
    }

    // Jetlyn
    @GET
    @Path("/getpackage/{ packageId }")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPackage(@PathParam("packageId") int packageId)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        PackagesEntity packagesEntity = em.find(PackagesEntity.class, packageId);
        Gson gson = new Gson();
        return gson.toJson(packagesEntity);
    }

    // Jetlyn
    @POST
    @Path("/postpackage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postPackage(String jsonString)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        Gson gson = new Gson();
        PackagesEntity packagesEntity = gson.fromJson(jsonString, PackagesEntity.class);
        System.out.println(packagesEntity);
        em.getTransaction().begin();
        em.merge(packagesEntity);
        em.getTransaction().commit();
//        if (result != null)
//        {
            return "{ 'message':'Update Successful!' }";
//        }
//        else
//        {
//            return "{ 'message':'*Update Failed!!!*' }";
//        }
    }

    // Jetlyn
    @PUT
    @Path("/putpackage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putPackage(String jsonString)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        Gson gson = new Gson();
        PackagesEntity packagesEntity = gson.fromJson(jsonString, PackagesEntity.class);
        System.out.println(packagesEntity);
        em.getTransaction().begin();
        em.persist(packagesEntity);
        em.getTransaction().commit();
//        if (result != null)
//        {
            return "{ 'message':'Insert Successful' }";
//        }
//        else
//        {
//            return "{ 'message':'Insert Failed' }";
//        }
    }

    // Jetlyn
    @DELETE
    @Path("/deletepackage/{ packageId }")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePackage(@PathParam("packageId") int packageId)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        PackagesEntity packagesEntity = em.find(PackagesEntity.class, packageId);
        em.getTransaction().begin();
        em.remove(packagesEntity);
        System.out.println(packagesEntity);
        if (em.contains(packagesEntity))
        {
            em.getTransaction().rollback();
            em.close();
            return "{ 'message':'Delete Failed' }";
        }
        else
        {
            em.getTransaction().commit();
            em.close();
            return "{ 'message':'Delete Successful' }";
        }
    }

}