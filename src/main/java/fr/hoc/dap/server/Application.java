package fr.hoc.dap.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Alucard
 */
@SpringBootApplication
public abstract class Application {

    /** @return Config*/
    @Bean
    public Config conf() {
        Config config = new Config();
        config.setApplicationName("suOER dAp §");
        return config;
    }

    /** @param args for advanced and customized application.*/
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
