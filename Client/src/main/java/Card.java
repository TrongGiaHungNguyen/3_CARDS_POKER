import java.io.Serializable;

public class Card implements Serializable {
    char suit;
    int value;
    Card(char suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public String stringSuit(){
        if (suit == 'C') return "clubs";
        else if (suit == 'D') return "diamonds";
        else if (suit == 'H') return "hearts";
        else return "spades";
    }

    public String getImagePath() {
        return "/cards/" + value + "_of_" + stringSuit().toLowerCase() + ".png";
    }
}
