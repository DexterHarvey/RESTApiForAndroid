package com.example.RESTApiForAndroid;

import com.google.gson.Gson;
import model.BookingdetailsEntity;
import model.CustomersEntity;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bookingdetails")
public class BookingDetailsResource {
    @GET
    @Path("/getbookingdetailsbycustid/{ CustId }")
    @Produces(MediaType.APPLICATION_JSON)
    public String validateLogin(@PathParam("CustId") String custId) {
        int custIdInt = Integer.parseInt(custId);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        TypedQuery<BookingdetailsEntity> query2 = em.createQuery("SELECT DISTINCT bd FROM BookingsEntity b, BookingdetailsEntity bd WHERE b.customerId = :custId", BookingdetailsEntity.class);
        query2.setParameter("custId", custIdInt);
        List<BookingdetailsEntity> details;
        try {
            details = query2.getResultList();
            em.close();
            factory.close();
            return new Gson().toJson(details);
        }
        catch (NoResultException ex){
            return "{'invalid' : 'login'}";
        }
    }
    @POST
    @Path("/addnew")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBookingDetail(String BDJson) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        Gson gson = new Gson();
        BookingdetailsEntity bDetails = gson.fromJson(BDJson, BookingdetailsEntity.class);

        em.getTransaction().begin();

        // inserts entity and returns copy of new entity
        BookingdetailsEntity result = em.merge(bDetails);


        em.getTransaction().commit();

        if(result!= null){
            return gson.toJson(bDetails);
        }
        else{
            return "{'message': 'Failed to update'}";
        }
    }
}
