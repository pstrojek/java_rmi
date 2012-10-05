
import java.rmi.*;

public interface IRCInterface extends Remote {

    public void writeUser(String username, String message) throws RemoteException;

    public void writeString(String message) throws RemoteException;

    public String read() throws RemoteException;
}