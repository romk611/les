package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User dima = new User("Dima", "Ovchinnikov", "dima@mail.ru");
        User jeny = new User("Jeny", "Chuchueva", "jeny@mail.ru");
        User roma = new User("Roman", "Shamsiev", "romka@mail.ru");
        User petya = new User("Petya", "Sidorova", "sidorova@mail.ru");


        Car chevrolet = new Car("Chevrolet", 214);
        Car lada = new Car("Lada", 14);
        Car bmw = new Car("BMW", 7);
        Car kia = new Car("KIA", 11);

        userService.add(dima.setCar(chevrolet).getUser(dima));
        userService.add(jeny.setCar(lada).getUser(jeny));
        userService.add(roma.setCar(bmw).getUser(roma));
        userService.add(petya.setCar(kia).getUser(petya));

        for(User user : userService.listUsers()){
            System.out.println("Пользователь "+ user.getFirstName()+" с машиной "+user.getCar());
        }

        System.out.println(userService.getUserByCar("lada",14));
        //пользователь по машине и модели

        try{
            User nullCar = userService.getUserByCar("Marusia",234);
        } catch (NoResultException e){
            System.out.println("Пользователя с такой машиной нет");
        }

        context.close();
    }
}
