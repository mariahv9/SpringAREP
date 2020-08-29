package edu.escuelaing.arep.httpServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Request {
    private String method;
    private String requestURI;
    private String HTTPVersion;
    private URI theuri;
    private Map<String,String> query;

    public Request(String requestLine){
        parseRequestLine(requestLine);
    }

    public void parseRequestLine(String requestLine){
        try {
            String[] components= requestLine.split("\\s");
            method = components[0];
            requestURI = components[1];
            HTTPVersion = components[2];
            setTheuri(new URI(requestURI));
            query = parseQuery(theuri.getQuery());
        } catch (URISyntaxException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getMethod() {
        return method;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getHTTPVersion() {
        return HTTPVersion;
    }

    public String toString(){
        return method + " " + requestURI + " " + HTTPVersion + "\n\r" + getTheuri() + "\n\r" + "Query: " + query;
    }

    public URI getTheuri() {
        return theuri;
    }

    public void setTheuri(URI theuri) {
        this.theuri = theuri;
    }

    private Map<String, String> parseQuery(String query) {
        if( query == null) return null;
        Map<String, String> theQuery = new HashMap();
        String[] nameValuePairs = query.split("&");
        for(String nameValuePair: nameValuePairs){
            int index = nameValuePair.indexOf("=");
            if(index!=-1){
                theQuery.put(nameValuePair.substring(0, index), nameValuePair.substring(index+1));
            }
        }
        return theQuery;
    }

    public String getValFromQuery(String varname){
        return query.get(varname);
    }
}