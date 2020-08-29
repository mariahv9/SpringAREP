package edu.escuelaing.arep.httpServer;

/**
 * Class of manage URI
 * @author Maria Fernanda Hernandez Vargas
 */
public interface URIProcessor {

    public abstract void mapService(String command) throws Exception;
    public String executeService (String uri);
}
