package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user);
    public List<User> getUsers();
    public User getById(long id);
    public void updateUser(User user);
    public void deleteUser(long id);



}
