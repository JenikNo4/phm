package cz.simek.phm;

import cz.simek.phm.config.ApplicationConfig;
import cz.simek.phm.model.User;
import cz.simek.phm.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by jenik on 1.2.17.
 */
public class RunIt {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);


        UserService service = (UserService) context.getBean("userService");
        User user = service.printFirstUser();


        System.out.println(user.getLogin() + " " + user.getEmail());


    }

}
