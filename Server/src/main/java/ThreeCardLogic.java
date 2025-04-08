import java.util.ArrayList;
import java.util.Comparator;

public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand){
        hand.sort(Comparator.comparingInt((Card card) -> card.value).reversed());

        Card card1 = hand.get(0);
        Card card2 = hand.get(1);
        Card card3 = hand.get(2);

//        System.out.println("" + card1.value + card2.value + card3.value);
//        System.out.println("" + card1.suit + card2.suit + card3.suit);

        if (card1.value - 1 == card2.value && card1.value - 2 == card3.value){
            if (card1.suit == card2.suit && card1.suit == card3.suit) return 1;
            else return 3;
        }
        else if (card1.value == card2.value && card1.value == card3.value) return 2;
        else if (card1.suit == card2.suit && card1.suit == card3.suit) return 4;
        else if (card1.value == card2.value || card1.value == card3.suit || card2.value == card3.value) return 5;

        return 0;
    }
    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        int handType = evalHand(hand);

        if (handType == 1) return 40 * bet;
        else if (handType == 2) return 30 * bet;
        else if (handType == 3) return 6 * bet;
        else if (handType == 4) return 3 * bet;
        else if (handType == 5) return bet;

        return 0;
    }
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        //if (dealer.get(0).value < 12 && dealer.get(1).value < 12 && dealer.get(2).value < 12) return 0;

        int dealerType = evalHand(dealer);
        int playerType = evalHand(player);

//        System.out.println(dealerType + " " + playerType);

        if (dealerType != 0 && playerType == 0) return 1;
        else if (dealerType == 0 && playerType != 0) return 2;

        if (dealerType < playerType) return 1;
        else if (dealerType > playerType) return 2;
        else{
            for (int i = 0; i < 3; i++){
                int dealerValue = dealer.get(i).value;
                int playerValue = player.get(i).value;
                int dealerSuit = dealer.get(i).suit;
                int playerSuit = player.get(i).suit;

                if (dealerValue > playerValue) return 1;
                else if (dealerValue < playerValue) return 2;
            }
            return 0;
        }
    }
}
