package utils;

import logic.card.BaseCard;
import logic.game.GameLogic;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {
    private static final Scanner sc = new Scanner(System.in);

    public static void displayHand(int player) {
        System.out.println("===================================");
        System.out.println("Player " + player + "'s hand:");

        ArrayList<BaseCard> currentPlayerHand = GameLogic.getInstance().getCurrentPlayerHand();
        for (int i = 0; i < currentPlayerHand.size(); i++) {
            System.out.println(i + ") " + currentPlayerHand.get(i).toString());
        }
        System.out.println("");
    }

    public static int selectCard(ArrayList<BaseCard> hand) {
        int cardIndex;
        System.out.println("Select a card from your hand to play (0-" + (hand.size() - 1) + ").");
        while (true) {
            try {
                cardIndex = sc.nextInt();
                if (cardIndex >= hand.size())
                    throw new InputMismatchException();
                else {
                    if (hand.get(cardIndex).canPlay())
                        break;
                    else
                        System.out.println("Can't play that card, please select again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select an integer between 0-" + (hand.size() - 1));
                sc.next();
            }
        }
        return cardIndex;
    }
}
