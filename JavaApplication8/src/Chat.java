import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class Chat extends UnicastRemoteObject
           implements ChatInterface {
    
            List<String> history = new ArrayList<String>();
        
            Chat() throws RemoteException {
                
            }
            
           @Override
           public synchronized void write(String message) throws RemoteException {
               history.add(message);       
           }
           
           @Override
           public synchronized String read() throws RemoteException {
               
		String result = "";
		for (String historyLine : history)
			result+= historyLine+"\r\n";
		return result;
           }
}
