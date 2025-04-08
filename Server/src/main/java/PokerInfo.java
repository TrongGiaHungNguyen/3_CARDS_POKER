import java.io.Serializable;

public class PokerInfo implements Serializable {
    Player player1;
    Player player2;
    Dealer dealer;

    boolean newCards;
    int roundNum;
    boolean receivedBet;
    String gameRecord;
    int ante1Value, ante2Value, pairPlus1Value, pairPlus2Value;

    PokerInfo(){
        newCards = false;
        roundNum = 1;
        gameRecord = "Round 1:\n";
        receivedBet = false;
        player1 = new Player();
        player2 = new Player();
        dealer = new Dealer();
        player1.hand = dealer.dealHand();
        player2.hand = dealer.dealHand();
        dealer.dealersHand = dealer.dealHand();

//        System.out.println(dealer.dealersHand.size());
    }

    PokerInfo(Player player1, Player player2, Dealer dealer){
        this.player1 = player1;
        this.player2 = player2;
        this.dealer = dealer;
    }
}
