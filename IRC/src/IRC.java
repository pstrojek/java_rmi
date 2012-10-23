
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class IRC extends UnicastRemoteObject implements IRCInterface {

    List<IRCMessage> history = new ArrayList<IRCMessage>();
    //List<IRCMessage> history = new ArrayList<IRCMessage>();
    List<MouseRank> mouseHistory = new ArrayList<MouseRank>();

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    
    IRC() throws RemoteException {
    }

    @Override
    public void addMouseDistance(MouseRank rank) throws RemoteException {
        mouseHistory.add(rank);
    }
    
    @Override
    public String getMouseDistances() throws RemoteException {
        String result = "";
        for (MouseRank s : mouseHistory) {

            result = s.username + " " + s.cm + " cm" + "\r\n" + result;
        }
        return result;
    }
    
    @Override
    public String getBestMouseDistances() throws RemoteException {
        String result = "";
        double max = 0;
        for (MouseRank s : mouseHistory) {
            if(max < s.cm)
            {
                max = s.cm;
                result = s.username + " " + s.cm + " cm";
            }
        }
        return result;
    }
    
    @Override
    public void writeUser(String username, String message) throws RemoteException {
        history.add(new IRCMessage("[" + dateFormat.format(new Date()) + "] " + username + ": " + message));
    }

    @Override
    public void writeString(String message) throws RemoteException {
        history.add(new IRCMessage(message + " (" + dateFormat.format(new Date()) + ")"));
    }

    @Override
    public String read(Date from) throws RemoteException {
        String result = "";
        for (IRCMessage m : history) {
            if (m.time.compareTo(from) < 0) {
                continue;
            }
            result += m.message + "\r\n";
        }
        return result;
    }
}

class IRCMessage {
    
    public Date time;
    public String message;

    IRCMessage(String m)
    {
        message = m;
        time = new Date();
    }
}

class MouseRank implements Serializable {
    
    public String username;
    public double cm;

    MouseRank(String _username, double _cm)
    {
        username = _username;
        cm = _cm;
    }
}