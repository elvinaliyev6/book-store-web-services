package az.company.bookstore.service;

import az.company.bookstore.request.RequestUser;

public interface UserService {

    String checkLogin(String username, String password);

    void signUp(RequestUser requestUser);
}
