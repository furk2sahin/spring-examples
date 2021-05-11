package furkan.springmvcdemo.service;

import furkan.springmvcdemo.model.User;

public interface UserService {
    User findByUserName(String username);

    User findByEmail(String email);

    void saveUser(User user);
}
