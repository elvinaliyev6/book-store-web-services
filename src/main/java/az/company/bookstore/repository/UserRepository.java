package az.company.bookstore.repository;

import az.company.bookstore.model.User;
import az.company.bookstore.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByNameAndSurnameAndStatus(String name, String surname, Integer status);

    User findByIdAndUserTypeAndStatus(Long id, UserType userType, Integer status);

}
