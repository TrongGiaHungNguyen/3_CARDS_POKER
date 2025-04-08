import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    PokerInfo pokerInfo;
    Socket socketClient;

    ObjectOutputStream out;
    ObjectInputStream in;

    Client (String IP_Addr, int portNum) throws IOException, ClassNotFoundException{
//        Socket socketClient;
        Scanner scanner;
//        ObjectOutputStream out;
//        ObjectInputStream in;

        socketClient= new Socket(IP_Addr, portNum);
        System.out.println("Client: "+"Connection Established");

        System.out.println("This is the remote address client is connected to: " +socketClient.getRemoteSocketAddress());
        System.out.println("And the remote port: " + socketClient.getPort());

        scanner = new Scanner(System.in);
        out = new ObjectOutputStream(socketClient.getOutputStream());
        in = new ObjectInputStream(socketClient.getInputStream());
        socketClient.setTcpNoDelay(true);

//        while(true) {
//            PokerInfo pokerInfo = (PokerInfo) in.readObject();
//            System.out.println(pokerInfo.receivedBet);
//        }
    }

    public void startClient() throws IOException, ClassNotFoundException {
        while(true) {
            try {
                pokerInfo = (PokerInfo) in.readObject();
                System.out.println(pokerInfo.dealer.dealersHand.size());
                System.out.println(pokerInfo.receivedBet);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendPokerInfo() throws IOException {
        out.writeObject(pokerInfo);
    }
}
