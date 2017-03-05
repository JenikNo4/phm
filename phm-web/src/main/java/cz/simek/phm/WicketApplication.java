package cz.simek.phm;


import cz.simek.phm.config.ApplicationConfig;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see org.wickedsource.Start#main(String[])
 */
//public class WicketApplication extends AuthenticatedWebApplication {
@Configuration
@ComponentScan("cz.simek.phm.*")
public class WicketApplication extends AuthenticatedWebApplication {

    @SpringBean
    ApplicationContext applicationContext;

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, context));

        mountPage("login", LoginPage.class);
        mountPage("home", HomePage.class);
        //mountPage("secure", SecurePage.class);
        //mountPage("secure/extreme", ExtremeSecurePage.class);
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return SecureWebSession.class;
    }
}
