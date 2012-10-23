
import java.rmi.*;
import java.util.Date;

public interface IRCInterface extends Remote {
    
    public void addMouseDistance(MouseRank rank) throws RemoteException;
    
    public String getMouseDistances() throws RemoteException;
    
    public String getBestMouseDistances() throws RemoteException;

    public void writeUser(String username, String message) throws RemoteException;

    public void writeString(String message) throws RemoteException;

    public String read(Date from) throws RemoteException;
}