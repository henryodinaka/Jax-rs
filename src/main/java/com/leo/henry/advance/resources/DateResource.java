package com.leo.henry.advance.resources;

import com.leo.henry.advance.model.MyDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

@Path("date")
public class DateResource {

    @GET
    @Path("{dateToString}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDate(@PathParam("dateToString")MyDate myDate)
    {
        return "Got "+myDate.toString();
    }
    @GET
    @Path("current")
    @Produces(MediaType.TEXT_PLAIN)
    public Date getCurrentDate()
    {
        return Calendar.getInstance().getTime();
    }
    @GET
    @Path("shortDate")
    @Produces("text/shortDate")
    public Date getShortDate()
    {
        return Calendar.getInstance().getTime();
    }
}
