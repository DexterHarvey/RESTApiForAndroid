package com.example.RESTApiForAndroid;

import com.google.gson.Gson;
import model.BookingdetailsEntity;
import model.BookingsEntity;
import model.CustomersEntity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API calls which interact with the Booking table.
 * Created by Dexter.
 */

@Path("/bookings")
public class BookingsResource {
    @POST
    @Path("/addnew")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBooking(String bookingJson) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        Gson gson = new Gson();
        BookingsEntity bDetails = gson.fromJson(bookingJson, BookingsEntity.class);

        em.getTransaction().begin();

        // inserts entity and returns copy of new entity
        BookingsEntity result = em.merge(bDetails);


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
}
