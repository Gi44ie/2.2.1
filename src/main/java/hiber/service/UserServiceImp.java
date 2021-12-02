package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   @SuppressWarnings("unchecked")
   public User getUser(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("select u from User u join u.car c " +
                      "where c.model= :m and c.series= :s")
              .setParameter("m", model).setParameter("s", series);
      return query.getSingleResult();
   }

}
