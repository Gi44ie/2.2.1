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
      User user1 = new User("Rich", "Batyrov", "email");
      user1.setCar(new Car("Car", 123));
      User user2 = new User("tnehusn", "hathuntaehu", "aeuhnoet");
      user2.setCar(new Car("aoeu", 213));
      User user3 = new User("untsoehu", "oeuh", "atneohun");
      user3.setCar(new Car("oteue", 1564));

      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(user1);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      User lastTest = userService.getUser("Car", 123);
      System.out.println(lastTest.getFirstName() + " " + lastTest.getLastName());

      context.close();
   }
}
