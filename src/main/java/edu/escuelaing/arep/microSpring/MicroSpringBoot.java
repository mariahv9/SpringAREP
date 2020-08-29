package edu.escuelaing.arep.microSpring;

import edu.escuelaing.arep.httpServer.HttpServer;
import edu.escuelaing.arep.httpServer.URIProcessor;

public class MicroSpringBoot {
    public static void main(String[] args){
        URIProcessor mspring = new MicroSpring();
        try {
            mspring.mapService(args [0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpServer server = new HttpServer(mspring);
        server.start();
    }
}
