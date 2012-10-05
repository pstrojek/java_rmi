import java.rmi.Naming;
import java.rmi.registry.Registry;

public class ChatServer {

        public static void main(String[] args) {
                try {
                        Registry r = java.rmi.registry.LocateRegistry.createRegistry(1399);//1099 is the port number
                        r.rebind("Chat", new Chat());
                        System.out.println("Server is connected and ready for operation.");
                } catch (Exception e) {
                        System.out.println("Server not connected: " + e);
                }
        }
}