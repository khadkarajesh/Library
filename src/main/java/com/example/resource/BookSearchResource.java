package com.example.resource;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.repository.BookRepositoryStub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("search/books")
public class BookSearchResource {

    BookRepository bookRepository = new BookRepositoryStub();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchBook(@QueryParam("book_name") String bookName) {
        List<Book> books = bookRepository.searchBookByName(bookName);
        return Response.ok().entity(new GenericEntity<List<Book>>(books) {
        }).build();
    }
}
