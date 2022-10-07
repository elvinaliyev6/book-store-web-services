package az.company.bookstore.service;

import az.company.bookstore.request.RequestBook;
import az.company.bookstore.response.Response;
import az.company.bookstore.response.ResponseBook;
import az.company.bookstore.response.ResponseStatusList;

import java.util.List;

public interface BookService {

    Response<List<ResponseBook>> getBookList();

    Response<ResponseBook> getBookById(Long id);

    Response<List<ResponseBook>> getBooksByName(String name,Integer pageNumber,Integer limit);

    Response<List<ResponseBook>> getBooksByPublisherNameAndSurname(String name,String surname);

    ResponseStatusList addBook(RequestBook requestBook);

    ResponseStatusList updateBook(RequestBook requestBook);

}
