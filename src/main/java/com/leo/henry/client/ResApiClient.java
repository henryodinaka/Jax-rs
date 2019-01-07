package com.leo.henry.client;

import com.leo.henry.advance.model.Message;
import com.leo.henry.advance.model.MyDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ResApiClient {
   static Client client = ClientBuilder.newClient();
   static WebTarget bassTarget = client.target("http://localhost:8080/messenger/");
   static WebTarget messageTarget = bassTarget.path("messages");
   static WebTarget singleMessageTarget = messageTarget.path("{messageId}");

    public static void main(String[] args){
//        postMessage(messageTarget);
//        getSingleMessage(singleMessageTarget);
//        getAllMessages(messageTarget);
//        getMessageClass();
    getMessageByYear(2019,messageTarget);
    }

    public static void getMessageClass() {
        Client client = ClientBuilder.newClient();
        Message message =client
                .target("http://localhost:8080/messenger/messages/1")
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);
        System.out.println(message);
    }
    public static void getMessageByYear(int year , WebTarget webTarget) {
        Client client = ClientBuilder.newClient();
        List<Message> message =webTarget
                .queryParam("year",year )
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType< List<Message>>(){});
        System.out.println(message);
    }
    public static void postMessage(WebTarget messageTarget) {
        Message newMessage = new Message(6L,"This is just Advance jax-rs post","Leo");
       Response postResponse = messageTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newMessage));
        Message postResponseString = postResponse.readEntity(Message.class);
        System.out.println("Message Created");
        System.out.println(postResponseString);
    }

    public static void getAllMessages(WebTarget messageTarget) {
        Response response = messageTarget
                .request()
                .get();
        String responseString = response.readEntity(String.class);
        System.out.println(responseString);
    }

    public static void getSingleMessage(WebTarget singleMessageTarget) {
        getAllMessages(singleMessageTarget
                .resolveTemplate("messageId",2));
    }
//    public static WebTarget target(Client client)
//    {
//        WebTarget bassTarget = client.target("http://localhost:8080/messenger/");
//        WebTarget messageTarget = bassTarget.path("messages");
//        WebTarget singleMessageTarget = messageTarget.path("{messageId}");
//        return bassTarget;
//    }
}
