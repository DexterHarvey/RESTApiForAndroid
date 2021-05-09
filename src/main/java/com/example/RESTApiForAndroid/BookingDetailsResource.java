package com.example.RESTApiForAndroid;

import com.google.gson.Gson;
import model.BookingdetailsEntity;
import model.CustomersEntity;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
