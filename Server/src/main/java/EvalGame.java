import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EvalGame {
    public static boolean dealerDoesNotQualify(ArrayList<Card> dealerHand) {
        for (Card card : dealerHand) {
            if (card.value >= 12) { // Assuming value >= 12 corresponds to Queen or higher
                return false;
            }
        }
        return true;
    }

    public static void handlePairPlusBets(Player player, String playerName, PokerInfo pokerInfo, ObservableList<String> msg, int count) {
//        int clientCount = ClientRunnab
        if (player.pairPlusBet > 0) {
            int winAmount = ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
            if (winAmount <= 0) {
                player.totalWinnings -= player.pairPlusBet;
                pokerInfo.gameRecord += playerName + " loses Pair Plus\n";

                Platform.runLater(() -> msg.add("Client " + count + ": " + playerName + " loses Pair Plus"));
            } else {
                pokerInfo.gameRecord += playerName + " wins Pair Plus\n";
                player.totalWinnings += winAmount;
                Platform.runLater(() -> msg.add("Client " + count + ": " + playerName + " wins Pair Plus"));
            }
            player.pairPlusBet = 0;
        }
    }

    public static void evaluateHands(Player player1, Player player2, Dealer dealer, PokerInfo pokerInfo, ObservableList<String> msg, int count) {
        // Handle folded players
        if (player1.isFold) {
            player1.totalWinnings -= player1.anteBet + player1.pairPlusBet;
            player1.anteBet = 0;
            player1.playBet = 0;
            player1.pairPlusBet = 0;
            pokerInfo.gameRecord += "Player one folded\n";
            Platform.runLater(() -> msg.add("Client " + count + ": " + "Player one folded"));
        }

        if (player2.isFold) {
            player2.totalWinnings -= player2.anteBet + player2.pairPlusBet;
            player2.anteBet = 0;
            player2.playBet = 0;
            player2.pairPlusBet = 0;
            pokerInfo.gameRecord += "Player two folded\n";
            Platform.runLater(() -> msg.add("Client " + count + ": " + "Player two folded"));
        }

        int result1 = ThreeCardLogic.compareHands(dealer.dealersHand, player1.hand);
        int result2 = ThreeCardLogic.compareHands(dealer.dealersHand, player2.hand);

        if (dealerDoesNotQualify(dealer.dealersHand)) {
            pokerInfo.gameRecord += "Dealer does not have at least Queen high; ante wager is pushed\n";
            Platform.runLater(() -> msg.add("Client " + count + ": " + "Dealer does not have at least Queen high; ante wager is pushed"));
            // Ante wager is pushed; do not change totalWinnings for ante bets
            // Play bets are returned to players
            player1.playBet = 0;
            player2.playBet = 0;
        } else {
            if (!player1.isFold) {
                if (result1 == 1) {
                    // Player loses
                    player1.totalWinnings -= player1.anteBet + player1.playBet;
                    pokerInfo.gameRecord += "Player one loses to dealer\n";
                    Platform.runLater(() -> msg.add("Client " + count + ": " + "Player one loses to dealer"));
                } else if (result1 == 2) {
                    // Player wins
                    player1.totalWinnings += player1.anteBet + player1.playBet;
                    pokerInfo.gameRecord += "Player one beats dealer\n";
                    Platform.runLater(() -> msg.add("Client " + count + ": " + "Player one beats dealer"));
                } else {
                    // Tie
                    pokerInfo.gameRecord += "Player one ties with dealer\n";
                    Platform.runLater(() -> msg.add("Client " + count + ": " + "Player one ties with dealer"));
                }
                player1.anteBet = 0;
                player1.playBet = 0;
            }

            if (!player2.isFold) {
                if (result2 == 1) {
                    // Player loses
                    player2.totalWinnings -= player2.anteBet + player2.playBet;
                    pokerInfo.gameRecord += "Player two loses to dealer\n";
                    Platform.runLater(() -> msg.add("Client " + count + ": " + "Player two loses to dealer"));
                } else if (result2 == 2) {
                    // Player wins
                    player2.totalWinnings += player2.anteBet + player2.playBet;
                    pokerInfo.gameRecord += "Player two beats dealer\n";
                    Platform.runLater(() -> msg.add("Client " + count + ": " + "Player two beats dealer"));
                } else {
                    // Tie
                    pokerInfo.gameRecord += "Player two ties with dealer\n";
                    Platform.runLater(() -> msg.add("Client " + count + ": " + "Player two ties with dealer"));
                }
                player2.anteBet = 0;
                player2.playBet = 0;
            }
        }

        player1.isFold = false;
        player2.isFold = false;
    }
}
