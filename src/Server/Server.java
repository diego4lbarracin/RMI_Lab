package Server;

import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try{
            //1024 66636
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("ServerInterface", new ServerImpl());
            System.out.println("Server created successfully.");
        }catch (Exception e){
            System.out.println("Error in the server..."+e);
        }
    }
}
