package az.company.bookstore.repository;

import az.company.bookstore.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {

    List<UserType> findAllByStatus(Integer status);

    UserType findByIdAndStatus(Long id,Integer status);

}
