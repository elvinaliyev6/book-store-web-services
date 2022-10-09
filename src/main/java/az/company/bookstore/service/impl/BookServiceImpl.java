package az.company.bookstore.service.impl;

import az.company.bookstore.enums.EnumStatus;
import az.company.bookstore.enums.ErrorCodeEnum;
import az.company.bookstore.exception.AuthorNotFoundException;
import az.company.bookstore.exception.BookNotFoundException;
import az.company.bookstore.exception.PublisherNotFoundException;
import az.company.bookstore.model.Book;
import az.company.bookstore.model.User;
import az.company.bookstore.model.UserType;
import az.company.bookstore.repository.BookRepository;
import az.company.bookstore.repository.UserRepository;
import az.company.bookstore.repository.UserTypeRepository;
import az.company.bookstore.request.RequestBook;
import az.company.bookstore.response.Response;
import az.company.bookstore.response.ResponseBook;
import az.company.bookstore.response.ResponseUser;
import az.company.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    @Override
    public Response<List<ResponseBook>> getBookList() {
        Response<List<ResponseBook>> responseBook = new Response<>();

        List<ResponseBook> responseBookList = bookRepository.findAllByStatus(EnumStatus.ACTIVE.getValue()).stream().map(book -> convertToRespBook(book)).collect(Collectors.toList());

        if (responseBookList.isEmpty()) {
            throw new BookNotFoundException(ErrorCodeEnum.BOOK_NOT_FOUND);
        }

        responseBook.setResponse(responseBookList);

        return responseBook;
    }

    @Override
    public Response<ResponseBook> getBookById(Long id) {
        Response<ResponseBook> response = new Response<>();
        Book book = bookRepository.findByIdAndStatus(id, EnumStatus.ACTIVE.getValue());

        if (book == null) {
            throw new BookNotFoundException(ErrorCodeEnum.BOOK_NOT_FOUND);
        }
        ResponseBook responseBook = convertToRespBook(book);
        response.setResponse(responseBook);
        return response;
    }

    @Override
    public Response<List<ResponseBook>> getBooksByName(String name, Integer pageNumber, Integer limit) {


        return null;
    }

    @Override
    public Response<List<ResponseBook>> getBooksByPublisherNameAndSurname(String name, String surname) {

        User publisher = userRepository.findByNameAndSurnameAndStatus(name, surname, EnumStatus.ACTIVE.getValue());

        if (publisher == null)
            throw new PublisherNotFoundException(ErrorCodeEnum.PUBLISHER_NOT_FOUND);

        List<Book> bookList = bookRepository.findByPublisherAndStatus(publisher, EnumStatus.ACTIVE.getValue());

        if (bookList == null) {
            throw new BookNotFoundException(ErrorCodeEnum.BOOK_NOT_FOUND);
        }

        List<ResponseBook> responseBookList = bookList.stream()
                .map(book -> convertToRespBook(book))
                .collect(Collectors.toList());

        Response<List<ResponseBook>> response = new Response<>();
        response.setResponse(responseBookList);

        return response;
    }

    @Override
    public void addBook(RequestBook requestBook) {
        List<UserType> userTypes = userTypeRepository.findAllByStatus(EnumStatus.ACTIVE.getValue());
        User author = userRepository.findByIdAndUserTypeAndStatus(requestBook.getAuthorId(), userTypes.get(0), EnumStatus.ACTIVE.getValue());
        User publisher = userRepository.findByIdAndUserTypeAndStatus(requestBook.getPublisherId(), userTypes.get(1), EnumStatus.ACTIVE.getValue());

        if (author == null || publisher == null)
            throw new BookNotFoundException(ErrorCodeEnum.BOOK_NOT_FOUND);

        Book book = new Book();
        book.setName(requestBook.getName());
        book.setDescription(requestBook.getDescription());
        book.setAuthor(author);
        book.setPublisher(publisher);
        bookRepository.save(book);

    }

    @Override
    public void updateBook(RequestBook requestBook, Long id) {

        Book book = bookRepository.findByIdAndStatus(id, EnumStatus.ACTIVE.getValue());

        List<UserType> userTypes = userTypeRepository.findAllByStatus(EnumStatus.ACTIVE.getValue());
        User author = userRepository.findByIdAndUserTypeAndStatus(requestBook.getAuthorId(), userTypes.get(0), EnumStatus.ACTIVE.getValue());
        User publisher = userRepository.findByIdAndUserTypeAndStatus(requestBook.getPublisherId(), userTypes.get(1), EnumStatus.ACTIVE.getValue());

        if (book == null)
            throw new BookNotFoundException(ErrorCodeEnum.BOOK_NOT_FOUND);

        if (author == null)
            throw new AuthorNotFoundException(ErrorCodeEnum.AUTHOR_NOT_FOUND);

        if (publisher == null)
            throw new PublisherNotFoundException(ErrorCodeEnum.PUBLISHER_NOT_FOUND);

        book.setName(requestBook.getName());
        book.setDescription(requestBook.getDescription());
        book.setPublisher(publisher);
        book.setAuthor(author);


        bookRepository.save(book);
    }

    private ResponseBook convertToRespBook(Book book) {
        return ResponseBook.builder().id(book.getId()).name(book.getName()).description(book.getDescription()).author(ResponseUser.builder().name(book.getAuthor().getName()).surname(book.getAuthor().getSurname()).build()).publisher(ResponseUser.builder().name(book.getPublisher().getName()).surname(book.getPublisher().getSurname()).build()).build();
    }
}
