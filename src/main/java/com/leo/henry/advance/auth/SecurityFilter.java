package com.leo.henry.advance.auth;

import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER_KEY ="Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX ="Basic ";
    private static final String SECUREED_URL_PREFIX ="secured";
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().contains(SECUREED_URL_PREFIX))
        {
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                if ("user".equals(username) && "password".equals(password)) return;
                Response unAuthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                        .entity("User can not access the resource. Authentication failed due wrong username or password")
                        .build();

                requestContext.abortWith(unAuthorizedStatus);
            }
        }
    }
}
