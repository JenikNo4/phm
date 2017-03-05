package cz.simek.phm.init;

import cz.simek.phm.WicketApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by jenik on 2.2.17.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    //@Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        container.addListener(new ContextLoaderListener(context));
        context.register(ApplicationConfiguration.class);

        FilterRegistration filter = container.addFilter("cz.simek.phm", WicketFilter.class);
        filter.setInitParameter("applicationClassName", WicketApplication.class.getName());
        filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

}