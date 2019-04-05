package fr.hoc.dap.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author house
 * ajout de com pour tester transfert repo to github
 * nouveau test
 */
@SpringBootApplication
public abstract class Application {

    /**
     * @return Element géré par spring config par défaut.
     */
    @Bean
    public Config conf() {
        Config config = new Config();
        String appName = "String and Api Google";
        config.setApplicationName(appName);
        //        config.setCredentialFilePath("/credential_web.json");
        return config;
    }

    /**
     * @param args all args for run spring.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @param ctx .
     * @return .
     */
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {

        return args -> {

            System.out.println("Application Name : " + conf().getApplicationName());
        };
    }
}
