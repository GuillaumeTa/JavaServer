package fr.hoc.dap.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Alucard
 */
@SpringBootApplication
public abstract class Application {

    /**
     * @return Config
     */
    @Bean
    public Config conf() {
        Config config = new Config();
        return config;
    }

    /**
     * @param args for advanced and customized application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @param ctx application context
     * @return args
     */
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {

        return args -> {
            //TODO tag by Djer |Spring| Attention tu créé une nouvelle "Config" en appelant cette méthode, si celle créée par Spring est modifiée "ailleur" dans le code tu afficheras une informations FAUSSE !
            //CHANGED + question est ce que ça serait pas mieux de le  mettre en log plutot qu'en Sysout
            System.out.println("Application Name : " + conf().getApplicationName());
        };
    }
}
