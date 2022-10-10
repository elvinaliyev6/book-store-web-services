package az.company.bookstore.repository;

import az.company.bookstore.model.User;
import az.company.bookstore.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndSurnameAndStatus(String name, String surname, Integer status);

    User findByIdAndUserTypeAndStatus(Long id, UserType userType, Integer status);

    User findByUsernameAndPasswordAndStatus(String username, String password, Integer status);

}
