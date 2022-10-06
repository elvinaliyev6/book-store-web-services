package az.company.bookstore.repository;

import az.company.bookstore.model.UserType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository {

    List<UserType> findByStatus(Integer status);

}
