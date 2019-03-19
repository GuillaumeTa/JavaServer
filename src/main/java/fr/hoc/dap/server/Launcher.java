//package fr.hoc.dap.server;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import fr.hoc.dap.server.service.CalendarService;
//import fr.hoc.dap.server.service.GmailService;
//
///**
// * Classe de départ de l'application.
// */
//public abstract class Launcher {
//
//    /**constante de log (pour log4j).*/
//    private static final Logger LOG = LogManager.getLogger();
//
//    /**
//     * @param args Arguments for commande-line main parametre pour les commandes en console.
//     * @throws IOException If the credentials.json file cannot be found.
//     * @throws GeneralSecurityException if exception exists avoid them.
//     */
//    public static void main(final String[] args) throws IOException, GeneralSecurityException {
//        LOG.info("ceci est un log d'info");
//        LOG.error("Error ! Error ! Error !");
//        LOG.debug("Trouve la solution patate");
//        Config myConfig = new Config();
//        myConfig.setApplicationName("Bob");
//        GmailService mailService = new GmailService();
//        mailService.setLaConf(myConfig);
//        mailService.getMails();
//
//        CalendarService service = new CalendarService();
//        service.setLaConf(myConfig);
//        //        service.getEvents();
//    }
//
//}
