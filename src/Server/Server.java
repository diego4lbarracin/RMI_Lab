package Server;

import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try{
            // Set the system property 'java.rmi.server.hostname' to the IP address of the server
            /*Use this line of code when working in different networks and replace the ip address to the corresponding one.*/
            //System.setProperty("java.rmi.server.hostname", "98.81.215.75");

            // Create a new RMI registry on the specified port
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(9999);

            // Bind the name 'ServerInterface' to the ServerImpl object in the RMI registry
            r.rebind("ServerInterface", new ServerImpl());

            // Print a success message to the console
            System.out.println("Server created successfully.");
        }catch (Exception e){
            // Print any exceptions that occur to the console
            System.out.println("Error in the server..."+e);
        }
    }
}