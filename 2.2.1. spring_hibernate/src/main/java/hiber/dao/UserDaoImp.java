package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(Car car) {
//        String hqlQuery = "SELECT user FROM Car car JOIN car.user user WHERE car.models = :carModel" +
//                " AND car.series = :carSeries";
        String hqlQuery = "from User where car.models = :carModel and car.series = :carSeries";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlQuery, User.class);
        query.setParameter("carModel", car.getModels());
        query.setParameter("carSeries", car.getSeries());
        User user = query.getSingleResult();
        return user;
    }
}
