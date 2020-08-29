package edu.escuelaing.arep.httpServer;

import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class that connects with port on localhost
 * @author Maria Fernanda Hernandez Vargas
 */
public class HttpServer {

    private int port = 36000;
    private boolean running = false;
    URIProcessor up;

    /**
     * Turn up port
     * @param up
     */
    public HttpServer(URIProcessor up) {
        this.up = up;
    }

    /**
     * Refresh port
     * @param port
     */
    public HttpServer(int port) {
        this.port = port;
    }

    /**
     * Start connection with server socket
     */
    public void start() {
        try {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + port);
                System.exit(1);
            }

            running = true;
            while (running) {
                try {
                    Socket clientSocket = null;
                    try {
                        System.out.println("Listo para recibir en puerto 36000 ...");
                        clientSocket = serverSocket.accept();
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                        System.exit(1);
                    }

                    processRequest(clientSocket);

                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Start process request
     * @param clientSocket
     * @throws IOException
     */
    private void processRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Map<String, String> request = new HashMap<>();
        boolean requestLineReady = false;
        while ((inputLine = in.readLine()) != null) {
            if (!requestLineReady) {
                request.put("requestLine", inputLine);
                requestLineReady = true;
            } else {
                String[] entry = createEntry (inputLine);
                if (entry.length > 1) {
                    request.put(entry[0], entry[1]);
                }
            }
            if (!in.ready()) {
                break;
            }
        }
        Request req = new Request(request.get("requestLine"));

        System.out.println("RequestLine: " + req);

        createResponse(req, new PrintWriter (clientSocket.getOutputStream(), true));
        in.close();
    }

    /**
     * Split entry link
     * @param rawEntry
     * @return
     */
    private String[] createEntry(String rawEntry) {
        return rawEntry.split(":");
    }

    /**
     * Split request
     * @param req
     * @param out
     */
    private void createResponse(Request req, PrintWriter out) {
        String outputLine = testResponse();
        URI theuri = req.getTheuri();
        if (theuri.getPath().startsWith("/Apps")) {
            getAppResponse (theuri.getPath().substring(5), out);
        } else {
            getStaticResource(theuri.getPath(), out);
        }
        out.close();
    }

    /**
     * Get request
     * @param theuri
     * @param out
     */
    private void getAppResponse(String theuri, PrintWriter out) {
        String response = up.executeService(theuri);
        String header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
        out.println(header + response);
    }

    /**
     * Shows Mi propio mensaje on web site
     * @return
     */
    private String testResponse() {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n";
        return outputLine;
    }

    /**
     * Get static services
     * @param path
     * @param out
     */
    private void getStaticResource(String path, PrintWriter out) {
        Path file = Paths.get("target/classes/public_html" + path);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String header = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
            out.println(header);
            String line = null;
            while ((line = reader.readLine()) != null) {
                out.println(line);
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}