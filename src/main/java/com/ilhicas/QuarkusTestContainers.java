package com.ilhicas;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ilhicas.entities.Person;

@Path("/person")
public class QuarkusTestContainers {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @POST
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Person createHello() {
        Person p = new Person();
        p.name =  "ilhicas";
        p.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        p.persist();
        return p;
    }
}