package com.leo.henry.advance.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secured")
public class SecuredResource {
    @GET
    @Path("message")
    @Produces(MediaType.APPLICATION_JSON)
    public String securedMethod()
    {
        return "UserAuthentication was successful, You can now access the endpoint";
    }
}
