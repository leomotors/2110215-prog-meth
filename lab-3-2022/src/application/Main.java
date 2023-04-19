package application;

import logic.card.BaseCard;
import logic.game.GameLogic;
import utils.IO;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        GameLogic gameInstance = GameLogic.getInstance();

        System.out.println("no u GAME START: " + gameInstance.getInitialPlayerCount() + " players");
        System.out.println("You are player 0.");

        // --- GAME STEP 1. Init game ---
        gameInstance.initGame();

        while (!gameInstance.isGameOver()) {
            int currentPlayer = gameInstance.getCurrentPlayer();
            ArrayList<BaseCard> currentPlayerHand = gameInstance.getCurrentPlayerHand();

            // If the current player's hand is empty, they don't play.
            if (currentPlayerHand.size() == 0) {
                System.out.println("Player " + currentPlayer + " has already won.");
                gameInstance.goToNextPlayer();
                continue;
            }

            // If player 0's turn, show hand.
            if (currentPlayer == 0) {
                IO.displayHand(currentPlayer);
                System.out.println("The top card is " + gameInstance.getTopCard().toString());
            }

            // --- GAME STEP 2. Choose a card to be played ---
            int cardIndex = -1;

            // If there are playable cards
            if (gameInstance.isHandPlayable(currentPlayer)) {

                // If player 0's turn, user selects a card to play
                if (currentPlayer == 0) {
                    cardIndex = IO.selectCard(currentPlayerHand);
                }

                // If other player's turn, they play their first playable card automatically
                else
                    for (int i = 0; i < currentPlayerHand.size(); i++)
                        if (currentPlayerHand.get(i).canPlay()) {
                            cardIndex = i;
                            break;
                        }
            }

            // If no cards are playable, draw a card from deck
            else {
                ArrayList<BaseCard> newCards = gameInstance.draw(1);
                System.out.println("No cards in player " + currentPlayer + "'s hand are playable. Drew a new card. "
                        + currentPlayerHand.size() + " cards remaining.");

                // If the new card is playable, that new card will be played
                if (newCards.get(0).canPlay())
                    cardIndex = currentPlayerHand.size() - 1;
            }

            // --- GAME STEP 3. Play the card at cardIndex (or not play, if no playable card
            // exists) ---
            if (cardIndex != -1) {
                BaseCard selectedCard = currentPlayerHand.get(cardIndex);
                System.out.println("Player " + currentPlayer + " played " + selectedCard + ". "
                        + (currentPlayerHand.size() - 1) + " cards remaining.");
                String message = selectedCard.play();
                if (message != null)
                    System.out.println(message);
            }

            gameInstance.goToNextPlayer();
        }

        if (gameInstance.getPlayerHand(0).size() == 0) {
            System.out.println("\nCongratulations! You win!");
        } else {
            System.out.println("\nYou lose...");
        }
    }
}
