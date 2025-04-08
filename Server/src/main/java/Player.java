import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;
    boolean isFold;
    Player(){
        hand = new ArrayList<Card>();
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
        isFold = false;
    }
}
