import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Dealer implements Serializable {
    Deck theDeck;
    ArrayList<Card> dealersHand;

    Dealer(){
        theDeck = new Deck();
        dealersHand = new ArrayList<>();
    }

    public ArrayList<Card> dealHand(){
        if (theDeck.size() <= 34){
            theDeck.newDeck();
        }

        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            hand.add(theDeck.remove(0));
        }

        hand.sort(Comparator.comparingInt((Card card) -> card.value).reversed());

//        System.out.println();
//        for (int i = 0; i < theDeck.size(); i++){
//            System.out.print(theDeck.get(i).value + theDeck.get(i).suit + " ");
//        }

        return hand;
    }
}
