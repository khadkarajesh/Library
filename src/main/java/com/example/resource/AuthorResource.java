package com.example.resource;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
import com.example.repository.AuthorRepositoryStub;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rajesh on 8/4/16.
 */
@Path("authors")
public class AuthorResource {
    AuthorRepository authorRepository = new AuthorRepositoryStub();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAuthor(Author author) {
        return Response.status(Response.Status.CREATED).entity(authorRepository.createAuthor(author)).build();
    }
}
