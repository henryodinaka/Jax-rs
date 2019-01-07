package com.leo.henry.advance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@Data
@NoArgsConstructor
public class Message {
    private Long id;
    private String message;
    private String createdAt;
    private String updatedAt;
    private String author;

    private List<Link> links = new ArrayList<>();
//    @XmlTransient
//    private Map<Long,Comment> comments = new HashMap<>();

    public Message(Long id, String message,String author) {
        this.id = id;
        this.message = message;
        this.author = author;
    }

//    public void addLinks(String url,String rel)
//    {
//        Link link = new Link();
//        link.setLink(url);
//        link.setRel(rel);
//        links.add(link);
//    }
}
