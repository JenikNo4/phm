package cz.simek.phm;

import cz.simek.phm.service.UserService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by jenik on 28.1.17.
 */
public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean
    UserService userService;

    public HomePage() {
        //userService.createUser();
        add(new Label("message", userService.printFirstUser().getLogin()));
    }


}
