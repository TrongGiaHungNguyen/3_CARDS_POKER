import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Deck_Dealer_Test {
    static Deck deck1, deck2, deck3, deck4;
    static Dealer dealer1, dealer2, dealer3, dealer4;

    @BeforeAll
    static void setup() {
        deck1 = new Deck();
        deck2 = new Deck();
        deck3 = new Deck();
        dealer1 = new Dealer();
        dealer2 = new Dealer();
        dealer3 = new Dealer();
        dealer4 = new Dealer();
    }

    @Test
    void deck_test1(){
        assertEquals(52, deck1.size());
    }

    @Test
    void deck_test2(){
        assertEquals(52, deck2.size());
    }

    @Test
    void deck_test3(){
        assertEquals(deck1.size(), deck2.size());
    }

    @Test
    void dealer_test1(){
        assertEquals(0, dealer1.dealersHand.size());
    }

    @Test
    void dealer_test2(){
        assertEquals(52, dealer1.theDeck.size());
    }

    @Test
    void dealer_test3(){
        dealer1.dealersHand = dealer1.dealHand();
        assertEquals(3, dealer1.dealersHand.size());
    }

    @Test
    void dealer_test4(){
        assertEquals(49, dealer1.theDeck.size());
    }

    @Test
    void dealer_test5(){
        dealer1.dealersHand = dealer1.dealHand();
        assertEquals(3, dealer1.dealersHand.size());
    }

    @Test
    void dealer_test6(){
        assertEquals(46, dealer1.theDeck.size());
    }

    @Test
    void dealer_test7(){
        dealer1.dealersHand = dealer1.dealHand();
        assertEquals(43, dealer1.theDeck.size());
    }

    @Test
    void dealer_test8(){
        dealer1.dealersHand = dealer1.dealHand();
        assertEquals(40, dealer1.theDeck.size());
    }

    @Test
    void dealer_test9(){
        dealer1.dealersHand = dealer1.dealHand();
        assertEquals(37, dealer1.theDeck.size());
    }

    @Test
    void dealer_test10(){
        dealer2.dealersHand = dealer2.dealHand();
        dealer2.dealersHand = dealer2.dealHand();
        dealer2.dealersHand = dealer2.dealHand();
        dealer2.dealersHand = dealer2.dealHand();
        dealer2.dealersHand = dealer2.dealHand();
        dealer2.dealersHand = dealer2.dealHand();

        assertEquals(34, dealer2.theDeck.size());
    }

    @Test
    void dealer_test11(){
        dealer2.dealersHand = dealer2.dealHand();

        assertEquals(49, dealer2.theDeck.size());
    }
}
