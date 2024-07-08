package web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        this.entityManager = entityManagerFactory.getObject().createEntityManager();
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
