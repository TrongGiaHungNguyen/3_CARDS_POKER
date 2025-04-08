import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    private ObservableList<String> messages;
    private ArrayList<ClientRunnable> cl2;
    int count;
    int port;

    Server(ObservableList<String> messages) {
        cl2 = new ArrayList<ClientRunnable>();
        this.messages = messages;
        count = 0;
    }

    public void threadCheck2(){

        for(int i = 0; i<cl2.size(); i++) {
            ClientRunnable t = cl2.get(i);
            System.out.println("Client #: " + t.count);
        }
    }

    public void runServer(int  portNum) {
        int count = 1;

        try(ServerSocket mysocket = new ServerSocket(portNum);){
            System.out.println("Server is waiting for a client!");

            while(true) {
                try {
                    threadCheck2();
                    ClientRunnable cr = new ClientRunnable(mysocket.accept(), count, messages);
                    Thread t = new Thread(cr);
                    cl2.add(cr);
                    t.start();

                    int clientCount = count;
                    Platform.runLater(() -> messages.add("Client #: " + clientCount));
//                    messages.add("Client #: " + clientCount);
                    count++;
                } catch (Exception e) {
                    Platform.runLater(() -> messages.add("Error accepting client: " + e.getMessage()));
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e) {
            System.out.println("Server socket did not launch");
        }
    }
}
