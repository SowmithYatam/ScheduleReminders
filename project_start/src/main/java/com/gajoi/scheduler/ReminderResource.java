package com.gajoi.scheduler;

import com.gajoi.scheduler.impl.ReminderImpl;
import com.gajoi.scheduler.utils.Utils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.StringReader;

@RequestScoped
@Path("/")
public class ReminderResource {

    @Inject
    private ReminderImpl impl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAllReminders(){
        return Response.status(Response.Status.OK).entity(Utils.convertAll(impl.getAllReminders())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createReminder(String body){
        JsonReader jsonReader = Json.createReader(new StringReader(body));
        JsonObject obj = jsonReader.readObject();
        return Response.status(Response.Status.OK).entity(Utils.convert(impl.createReminder(obj.getString("userId"),obj.getString("title"),obj.getString("message"),obj.getString("phoneNumber"),obj.getString("email"),obj.getString("sendTime")))).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateRemainder(String body){
        JsonReader jsonReader = Json.createReader(new StringReader(body));
        JsonObject obj = jsonReader.readObject();
        return Response.status(Response.Status.OK).entity(Utils.convert(impl.updateRemainder(obj.getString("id"),obj.getString("title"),obj.getString("message"),obj.getString("phoneNumber"),obj.getString("email"),obj.getString("sendTime")))).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteRemainder(@QueryParam("id") String id){
        impl.deleteRemainder(id);
        return Response.status(Response.Status.OK).build();
    }
}
