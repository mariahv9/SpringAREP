package edu.escuelaing.arep.componentTests;
import edu.escuelaing.arep.microSpring.RequestMapping;

/**
 * Class that shows interface on web site
 * @author Maria Fernanda Hernandez Vargas
 */
public class HelloController {

    /**
     * Method that shows Greetings on web site
     * @return
     */
    @RequestMapping ("/hello")
    public static String greetings () {
        return "Greetings from Spring Boot!";
    }

    /**
     * Methos that shows pi value on web site
     * @return
     */
    @RequestMapping ("/pi")
    public static String theValueOfPi () {
        return "Pi: " + Math.PI;
    }

    /**
     * Method that retuns euler value on web site
     * @return
     */
    @RequestMapping("/web") //localhost:36000/Apps/web
    public static String pageWeb (){
        String outputLine =
                "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Valor de EULER</h1> \n"
                + "<p>" + Math.E + "</p>"
                + "</body>\n"
                + "</html>\n";
        return outputLine;
    }
}
