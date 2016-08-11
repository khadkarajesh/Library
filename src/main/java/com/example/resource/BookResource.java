package com.example.resource;

import com.example.model.Book;
import com.example.repository.BookRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("books")
public class BookResource {

    BookRepositoryStub bookRepositoryStub = new BookRepositoryStub();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getResult(@QueryParam("page") int pageIndex, @QueryParam("offSet") int offSet) {
        System.out.println("max = " + pageIndex + " :offset" + offSet);
        List<Book> books = bookRepositoryStub.getAllBooks(pageIndex, offSet);
        return Response.ok().entity(new GenericEntity<List<Book>>(books) {
        }).build();
    }

    @GET
    @Path("{bookId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBookById(@PathParam("bookId") int id) {
        Book book = bookRepositoryStub.findBookById(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(book).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createBook(Book book) {
        return Response.status(Response.Status.CREATED).entity(bookRepositoryStub.createBook(book)).build();
    }

    @DELETE
    @Path("{bookId}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response deleteBook(@PathParam("bookId") int id) {
        bookRepositoryStub.deleteBook(id);
        return Response.status(Response.Status.NO_CONTENT).entity("Book deleted successfully").build();
    }

    @PUT
    @Path("{bookId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateBook(@PathParam("bookId") int id, Book book) {
        return Response.status(Response.Status.ACCEPTED).entity(bookRepositoryStub.updateBook(id, book)).build();
    }
}
