package az.company.bookstore.service.impl;

import az.company.bookstore.enums.EnumStatus;
import az.company.bookstore.enums.ErrorCodeEnum;
import az.company.bookstore.exception.CustomNotFoundException;
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

import java.util.Date;
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
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"book");
        }

        responseBook.setResponse(responseBookList);

        return responseBook;
    }

    @Override
    public Response<ResponseBook> getBookById(Long id) {
        Response<ResponseBook> response = new Response<>();
        Book book = bookRepository.findByIdAndStatus(id, EnumStatus.ACTIVE.getValue());

        if (book == null) {
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"book");
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
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"publisher");

        List<Book> bookList = bookRepository.findByPublisherAndStatus(publisher, EnumStatus.ACTIVE.getValue());

        if (bookList == null) {
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"book");
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

        if (author == null)
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"author");

        if(publisher==null)
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"publisher");

        Date insertDate=new Date();

        Book book = new Book();
        book.setName(requestBook.getName());
        book.setDescription(requestBook.getDescription());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setInsertDate(insertDate);
        book.setStatus(EnumStatus.ACTIVE.getValue());
        bookRepository.save(book);

    }

    @Override
    public void updateBook(RequestBook requestBook, Long id) {

        Book book = bookRepository.findByIdAndStatus(id, EnumStatus.ACTIVE.getValue());

        List<UserType> userTypes = userTypeRepository.findAllByStatus(EnumStatus.ACTIVE.getValue());
        User author = userRepository.findByIdAndUserTypeAndStatus(requestBook.getAuthorId(), userTypes.get(0), EnumStatus.ACTIVE.getValue());
        User publisher = userRepository.findByIdAndUserTypeAndStatus(requestBook.getPublisherId(), userTypes.get(1), EnumStatus.ACTIVE.getValue());

        if (book == null)
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"book");

        if (author == null)
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"author");

        if (publisher == null)
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage()+"publisher");

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
