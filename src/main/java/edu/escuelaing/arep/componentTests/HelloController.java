package edu.escuelaing.arep.componentTests;
import edu.escuelaing.arep.microSpring.RequestMapping;

public class HelloController {
    @RequestMapping ("/hello")
    public static String greetings () {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping ("/pi")
    public static String theValueOfPi () {
        return "Pi: " + Math.PI;
    }

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
