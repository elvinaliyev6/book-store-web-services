package az.company.bookstore.service.impl;

import az.company.bookstore.enums.EnumStatus;
import az.company.bookstore.enums.ErrorCodeEnum;
import az.company.bookstore.exception.CustomNotFoundException;
import az.company.bookstore.model.User;
import az.company.bookstore.model.UserType;
import az.company.bookstore.repository.UserRepository;
import az.company.bookstore.repository.UserTypeRepository;
import az.company.bookstore.request.RequestUser;
import az.company.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    @Override
    public String checkLogin(String username, String password) {

        User user = userRepository.findByUsernameAndPasswordAndStatus(username, password, EnumStatus.ACTIVE.getValue());

        if (user == null)
            throw new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND.getMessage() + "user");

        return "success";
    }

    @Override
    public void signUp(RequestUser requestUser) {
        Date inserDate = new Date();

        User user = new User();

        UserType userType = userTypeRepository.findById(requestUser.getUserTypeId())
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.NOT_FOUND + "author or employee given id"));

        user.setName(requestUser.getName());
        user.setSurname(requestUser.getSurname());
        user.setEmail(requestUser.getEmail());
        user.setUserType(userType);
        user.setInserDate(inserDate);
        user.setStatus(EnumStatus.ACTIVE.getValue());

        userRepository.save(user);
    }
}
