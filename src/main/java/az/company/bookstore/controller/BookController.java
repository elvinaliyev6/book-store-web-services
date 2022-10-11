package az.company.bookstore.controller;

import az.company.bookstore.request.RequestBook;
import az.company.bookstore.response.Response;
import az.company.bookstore.response.ResponseBook;
import az.company.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/bookList")
    public Response<List<ResponseBook>> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping(value = "/book/{id}")
    public Response<ResponseBook> getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/name/{name}/{pageNumber}/{limit}")
    public Response<List<ResponseBook>> getBookByName(@PathVariable("name") String name,@PathVariable("pageNumber") Integer pageNumber,@PathVariable("limit") Integer limit) {
        return bookService.getBooksByName(name,pageNumber,limit);
    }


    @PostMapping(value = "/addBook")
    public void addBook(@RequestBody RequestBook requestBook) {
        bookService.addBook(requestBook);
    }

    @PutMapping(value = "/book/{id}")
    public void updateBook(@RequestBody RequestBook requestBook, @PathVariable("id") Long id) {
        bookService.updateBook(requestBook, id);
    }

    @GetMapping(value = "/publisher")
    public Response<List<ResponseBook>> getBooksByPublisherNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return bookService.getBooksByPublisherNameAndSurname(name, surname);
    }


}
