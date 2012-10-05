
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class IRC extends UnicastRemoteObject implements IRCInterface {

    List<String> history = new ArrayList<String>();
    
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    
    IRC() throws RemoteException {
    }

    @Override
    public void writeUser(String username, String message) throws RemoteException {
        history.add("[" + dateFormat.format(new Date()) + "] " + username + ": " + message);
    }

    @Override
    public void writeString(String message) throws RemoteException {
        history.add(message + " (" + dateFormat.format(new Date()) + ")");
    }

    @Override
    public String read() throws RemoteException {
        String result = "";
        for (String historyLine : history) {
            result += historyLine + "\r\n";
        }
        return result;
    }
}
