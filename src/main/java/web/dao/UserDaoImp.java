package web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImp(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        this.entityManager = entityManagerFactory.getObject().createEntityManager();

        this.entityManager.getTransaction().begin();
        addUser(new User("Name1", "Lastname1", LocalDate.of(1991, 1, 21), "user1@mail.ru", "123-456-7890"));
        addUser(new User("Name2", "Lastname2", LocalDate.of(1992, 2, 22), "user2@mail.ru", "(123) 456-7890"));
        addUser(new User("Name3", "Lastname3", LocalDate.of(1993, 3, 23), "user3@mail.ru", "+1(345)234-45-67"));
        addUser(new User("Name4", "Lastname4", LocalDate.of(1994, 4, 24), "user4@mail.ru", "123.456.7890"));
        addUser(new User("Name5", "Lastname5", LocalDate.of(1995, 5, 25), "user5@mail.ru", "+91 (123) 456-7890"));
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> list = entityManager.createQuery("SELECT e FROM User e").getResultList();
        return list;
    }

    @Override
    public User getById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = getById(id);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
