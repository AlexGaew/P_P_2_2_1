package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args)  {
         AnnotationConfigApplicationContext context =
                 new AnnotationConfigApplicationContext(AppConfig.class);

         UserService userService = context.getBean(UserService.class);
         User user1 = new User("User1", "Lastname1", "user1@mail.ru");
         User user2 = new User("User2", "Lastname2", "user2@mail.ru");
         User user3 = new User("User3", "Lastname3", "user3@mail.ru");
         User user4 = new User("User4", "Lastname4", "user4@mail.ru");
         user1.setCar(new Car(user1, "BMW", 752));
         user2.setCar(new Car(user2, "asd", 456));
         user3.setCar(new Car(user3, "fnj", 366));
         user4.setCar(new Car(user4, "zxc", 123));


         userService.add(user1);
         userService.add(user2);
         userService.add(user3);
         userService.add(user4);

         User getUser = userService.getUser(new Car(user1, "BMW", 752));

         System.out.println(getUser.getLastName());



         List<User> users = userService.listUsers();
         for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println();
         }

         context.close();
      }
}
