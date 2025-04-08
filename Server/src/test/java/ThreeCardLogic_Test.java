import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeCardLogic_Test {
    static Card card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
    static ArrayList<Card> hand1, hand2, hand3, hand4, hand5, hand6, hand7;

    @BeforeAll
    static void setup() {
        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();
        hand5 = new ArrayList<>();
        hand6 = new ArrayList<>();
        hand7 = new ArrayList<>();

        card1 = new Card('C', 2);
        card2 = new Card('D', 2);
        card3 = new Card('H', 2);

        card4 = new Card('S', 3);
        card5 = new Card('S', 4);
        card6 = new Card('S', 5);

        card7 = new Card('S', 7);
        card8 = new Card('H', 8);

        card9 = new Card('H', 3);
        card10 = new Card('S', 8);

        hand1.add(card1); hand1.add(card2); hand1.add(card3); // 3 of a kind
        hand2.add(card4); hand2.add(card5); hand2.add(card6); // straight flush
        hand3.add(card1); hand3.add(card4); hand3.add(card5); // straight
        hand4.add(card5); hand4.add(card6); hand4.add(card7); // flush
        hand5.add(card1); hand5.add(card2); hand5.add(card7); // pair;
        hand6.add(card1); hand6.add(card4); hand6.add(card8); // high card
        hand7.add(card2); hand7.add(card9); hand7.add(card10); // high card
    }

    @Test
    void evalHand_test1(){
        int val1 = ThreeCardLogic.evalHand(hand1);
        int val2 = ThreeCardLogic.evalHand(hand2);

        assertEquals(2, val1);
        assertEquals(1, val2);
    }

    @Test
    void evalHand_test2(){
        int val2 = ThreeCardLogic.evalHand(hand2);
        assertEquals(1, val2);
    }

    @Test
    void evalHand_test3(){
        int val3 = ThreeCardLogic.evalHand(hand3);
        assertEquals(3, val3);
    }

    @Test
    void evalHand_test4(){
        int val4 = ThreeCardLogic.evalHand(hand4);
        assertEquals(4, val4);
    }

    @Test
    void evalHand_test5(){
        int val5 = ThreeCardLogic.evalHand(hand5);
        assertEquals(5, val5);
    }

    @Test
    void evalHand_test6(){
        int val6 = ThreeCardLogic.evalHand(hand6);
        assertEquals(0, val6);
    }

    @Test
    void evalPPWinnings_test1(){
        int amount = ThreeCardLogic.evalPPWinnings(hand1, 1);
        assertEquals(30, amount);
    }

    @Test
    void evalPPWinnings_test2(){
        int amount = ThreeCardLogic.evalPPWinnings(hand2, 1);
        assertEquals(40, amount);
    }

    @Test
    void evalPPWinnings_test3(){
        int amount = ThreeCardLogic.evalPPWinnings(hand3, 1);
        assertEquals(6, amount);
    }

    @Test
    void evalPPWinnings_test4(){
        int amount = ThreeCardLogic.evalPPWinnings(hand4, 1);
        assertEquals(3, amount);
    }

    @Test
    void evalPPWinnings_test5(){
        int amount = ThreeCardLogic.evalPPWinnings(hand5, 1);
        assertEquals(1, amount);
    }

    @Test
    void evalPPWinnings_test6(){
        int amount = ThreeCardLogic.evalPPWinnings(hand6, 1);
        assertEquals(0, amount);
    }

//    hand1.add(card1); hand1.add(card2); hand1.add(card3); // 3 of a kind
//    hand2.add(card4); hand2.add(card5); hand2.add(card6); // straight flush
//    hand3.add(card1); hand3.add(card4); hand3.add(card5); // straight
//    hand4.add(card5); hand4.add(card6); hand4.add(card7); // flush
//    hand5.add(card1); hand5.add(card2); hand5.add(card7); // pair;
//    hand6.add(card1); hand6.add(card4); hand6.add(card8); // high card

    @Test
    void compareHands_test1(){
        int result = ThreeCardLogic.compareHands(hand1, hand2);
        assertEquals(2, result);
    }

    @Test
    void compareHands_test2(){
        int result = ThreeCardLogic.compareHands(hand2, hand1);
        assertEquals(1, result);
    }

    @Test
    void compareHands_test3(){
        int result = ThreeCardLogic.compareHands(hand2, hand3);
        assertEquals(1, result);
    }

    @Test
    void compareHands_test4(){
        int result = ThreeCardLogic.compareHands(hand3, hand2);
        assertEquals(2, result);
    }

    @Test
    void compareHands_test5(){
        int result = ThreeCardLogic.compareHands(hand3, hand4);
        assertEquals(1, result);
    }

    @Test
    void compareHands_test6(){
        int result = ThreeCardLogic.compareHands(hand4, hand3);
        assertEquals(2, result);
    }

    @Test
    void compareHands_test7(){
        int result = ThreeCardLogic.compareHands(hand4, hand5);
        assertEquals(1, result);
    }

    @Test
    void compareHands_test8(){
        int result = ThreeCardLogic.compareHands(hand5, hand4);
        assertEquals(2, result);
    }

    @Test
    void compareHands_test9(){
        int result = ThreeCardLogic.compareHands(hand5, hand6);
        assertEquals(1, result);
    }

    @Test
    void compareHands_test10(){
        int result = ThreeCardLogic.compareHands(hand6, hand5);
        assertEquals(2, result);
    }

    @Test
    void compareHands_test11(){
        int result = ThreeCardLogic.compareHands(hand6, hand7);
        assertEquals(0, result);
    }

    @Test
    void compareHands_test12(){
        int result = ThreeCardLogic.compareHands(hand7, hand6);
        assertEquals(0, result);
    }
}
