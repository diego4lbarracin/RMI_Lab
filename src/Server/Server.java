package Server;

import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try{
            //1024 66636
            System.setProperty("java.rmi.server.hostname", "98.81.215.75");
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(9999);
            r.rebind("ServerInterface", new ServerImpl());
            System.out.println("Server created successfully.");
        }catch (Exception e){
            System.out.println("Error in the server..."+e);
        }
    }
}
