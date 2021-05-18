package com.example.RESTApiForAndroid;

import com.google.gson.Gson;
import model.BookingdetailsEntity;
import model.BookingsEntity;
import model.CustomersEntity;
import model.PackagesEntity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API calls which interact with the Booking table.
 * All created by Dexter except get by Eric.
 */

@Path("/bookings")
public class BookingsResource {
    @POST
    @Path("/addnew")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBooking(String bookingJson) {

        System.out.println(bookingJson);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        Gson gson = new Gson();
        BookingsEntity bDetails = gson.fromJson(bookingJson, BookingsEntity.class);

        em.getTransaction().begin();

        // inserts entity and returns copy of new entity
        BookingsEntity result = em.merge(bDetails);

        System.out.println(result);

        em.getTransaction().commit();

        if(result!= null){
            return gson.toJson(result);
        }
        else{
            return "{'message': 'Failed to update'}";
        }
    }
    @GET
    @Path("/highestbookingid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestBookingId() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("SELECT b.bookingId FROM BookingsEntity b ORDER BY b.bookingId DESC");
        List result = query.setMaxResults(1).getResultList();
        return "{\"highestBookingId\" : " + result.get(0).toString() + "}";
    }

    @GET
    @Path("/get/{ bookingId }")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBooking(@PathParam("bookingId") int bookingId)
    {
        System.out.println(bookingId);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        BookingsEntity booking = em.find(BookingsEntity.class, bookingId);
        Gson gson = new Gson();
        return gson.toJson(booking);
    }
}
