import javafx.collections.ObservableList;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientRunnable implements Runnable {
    Socket connection;
    int count;
    ObjectInputStream in;
    ObjectOutputStream out;
    ObservableList<String> messages;

    PokerInfo pokerInfo;

    ClientRunnable(Socket s, int count, ObservableList<String> msg){
        this.connection = s;
        this.count = count;
        this.messages = msg;
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            in = new ObjectInputStream(connection.getInputStream());
            out = new ObjectOutputStream(connection.getOutputStream());
            connection.setTcpNoDelay(true);
        }
        catch(Exception e) {
            System.out.println("Streams not open");
        }

        try{
            pokerInfo = new PokerInfo();
//            System.out.println(pokerInfo.dealer.dealersHand.size());
            out.writeObject(pokerInfo);
        }
        catch(Exception e) {
            System.out.println("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
            return;
        }

        while(true) {
            try {
                pokerInfo = (PokerInfo) in.readObject();

                if (pokerInfo.newCards){
                    pokerInfo.newCards = false;
                    pokerInfo.player1.hand = pokerInfo.dealer.dealHand();
                    pokerInfo.player2.hand = pokerInfo.dealer.dealHand();
                    pokerInfo.dealer.dealersHand = pokerInfo.dealer.dealHand();
                }
                else {
                    String playerName1 = "Client " + count + ": player 1";
                    EvalGame.handlePairPlusBets(pokerInfo.player1, "Player 1", pokerInfo, messages, count);

                    String playerName2 = "Client " + count + ": player 2";
                    EvalGame.handlePairPlusBets(pokerInfo.player2, "Player 2", pokerInfo, messages, count);

                    EvalGame.evaluateHands(pokerInfo.player1, pokerInfo.player2, pokerInfo.dealer, pokerInfo, messages, count);
                }
                out.writeObject(pokerInfo);

                System.out.println(pokerInfo.gameRecord);
                System.out.println(pokerInfo.player1.totalWinnings + " " + pokerInfo.player2.totalWinnings);


//                System.out.println(data.receivedBet + " " + data.player1.isFold + " " + data.player2.isFold);
//                System.out.println("Server received: " + data + " from client: " + count);
//                out.writeObject(data.toUpperCase());
            }
            catch(Exception e) {
                System.out.println("OOOOPPs...Something wrong with the socket from client: " + count +"....closing down!");
                break;
            }
        }

    }
}

