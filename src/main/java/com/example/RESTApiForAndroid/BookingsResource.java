package com.example.RESTApiForAndroid;

import com.google.gson.Gson;
import model.BookingdetailsEntity;
import model.BookingsEntity;
import model.CustomersEntity;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
}
