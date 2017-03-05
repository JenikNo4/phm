package cz.simek.phm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by jenik on 1.2.17.
 */

@Configuration
@ComponentScan(basePackages = "cz.simek.phm")
@Import(JpaConfiguration.class)
public class ApplicationConfig {


}
