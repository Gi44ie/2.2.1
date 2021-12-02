package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      User user1 = new User("Richard", "Batyrov", "rich@gmail.com");
      user1.setCar(new Car("KAMAZ", 2115));
      User user2 = new User("Oleg", "Kirsanov", "oleg@gmail.com");
      user2.setCar(new Car("UAZ", 123123));
      User user3 = new User("Iosif", "Antonov", "iosif@gmail.com");
      user3.setCar(new Car("Chevrolet", 546));

      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      User lastTest = userService.getUser("KAMAZ", 2115);
      System.out.println(lastTest.getFirstName() + " has a " + lastTest.getCar().getModel());

      context.close();
   }
}
