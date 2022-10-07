package az.company.bookstore.service.impl;

import az.company.bookstore.repository.BookRepository;
import az.company.bookstore.request.RequestBook;
import az.company.bookstore.response.Response;
import az.company.bookstore.response.ResponseBook;
import az.company.bookstore.response.ResponseStatusList;
import az.company.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Response<List<ResponseBook>> getBookList() {
        return (Response<List<ResponseBook>>) bookRepository.findAllByStatus(1);
    }

    @Override
    public Response<ResponseBook> getBookById(Long id) {
        return null;
    }

    @Override
    public Response<List<ResponseBook>> getBooksByName(String name, Integer pageNumber, Integer limit) {
        return null;
    }

    @Override
    public Response<List<ResponseBook>> getBooksByPublisherNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public ResponseStatusList addBook(RequestBook requestBook) {
        return null;
    }

    @Override
    public ResponseStatusList updateBook(RequestBook requestBook) {
        return null;
    }
}
