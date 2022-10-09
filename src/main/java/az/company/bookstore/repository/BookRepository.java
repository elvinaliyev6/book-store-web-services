package az.company.bookstore.repository;

import az.company.bookstore.model.Book;
import az.company.bookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByStatus(Integer status);
    Book findByIdAndStatus(Long id,Integer status);
    Page<Book> findByNameAndStatus(Pageable pageable, String name, Integer status);
    List<Book> findByPublisherAndStatus(User publisher, Integer status);
}
