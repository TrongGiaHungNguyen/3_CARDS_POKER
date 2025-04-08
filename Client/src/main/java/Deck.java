import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> implements Serializable {
    public Deck() {
        newDeck(); // Create a new shuffled deck on initialization
    }

    // Method to create a new deck of 52 cards and shuffle them
    public void newDeck() {
        this.clear(); // Clear the current deck if there are any cards

        char[] suits = {'C', 'D', 'H', 'S'}; // Clubs, Diamonds, Hearts, Spades
        for (char suit : suits) {
            for (int value = 2; value <= 14; value++) { // 2 to Ace (14)
                this.add(new Card(suit, value));
            }
        }

        Collections.shuffle(this); // Shuffle the deck after adding all cards
    }

    public void printDeck(){
        System.out.println();
        for (Card card : this) {
            System.out.print(card.value + "" + card.suit + " ");
        }
    }
}
