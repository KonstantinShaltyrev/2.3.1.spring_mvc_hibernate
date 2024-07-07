package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public List<User> getUsers();
    public User getById(long id);
    public void updateUser(User user);
    public void deleteUser(long id);
}
