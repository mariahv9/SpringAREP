package edu.escuelaing.arep.microSpring;

import edu.escuelaing.arep.httpServer.URIProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that implements uri
 * @author Maria Fernanda Hernandez Vargas
 */
public class MicroSpring implements URIProcessor {
    private Map<String, Method> webservices = new HashMap<>();

    /**
     * Mapping methods and services
     * @param componentName
     * @throws Exception
     */
    public void mapService (String componentName) throws Exception{
        int nMethods = 0;
        for (Method m: Class.forName(componentName).getMethods()){
            System.out.println("Revisando metodos: " + m.getName());
            if (m.isAnnotationPresent(RequestMapping.class)){
                System.out.println("Tiene anotacion @RequestMapping");
                RequestMapping rm = m.getAnnotation(RequestMapping.class);
                webservices.put(rm.value(), m);
                nMethods++;
            }
        }
        System.out.println("No. of web services %d %n" + nMethods);
    }

    /**
     * Execute methods
     * @param theuri
     * @return
     */
    public String executeService (String theuri){
        try {
            return webservices.get(theuri).invoke(null).toString();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MicroSpring.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MicroSpring.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MicroSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
}
