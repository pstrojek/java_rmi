import java.rmi.*;

public interface ChatInterface extends Remote {

        public void write(String message) throws RemoteException;
        
        public String read() throws RemoteException;
}