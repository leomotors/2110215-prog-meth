package logic.game;

import logic.card.*;

import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {
    private final int initialPlayerCount;
    private final ArrayList<BaseCard> deck;
    private final ArrayList<ArrayList<BaseCard>> playerHands;
    private int currentPlayer;
    private PlayDirection playDirection = PlayDirection.FORWARD;
    private int drawAmount = 0;
    private BaseCard topCard = null;

    private static GameLogic instance = null;

    private GameLogic(int initialPlayerCount) {
        this.initialPlayerCount = initialPlayerCount;
        this.deck = new ArrayList<>();
        this.playerHands = new ArrayList<>();
        for (int i = 0; i < initialPlayerCount; i++) {
            ArrayList<BaseCard> hand = new ArrayList<>();
            playerHands.add(hand);
        }
        this.currentPlayer = 0;
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic(4);
        }
        return instance;
    }

    public static GameLogic getInstance(int initialPlayerCount) {
        if (instance == null) {
            instance = new GameLogic(initialPlayerCount);
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    public void initGame() {
        // Create the full deck of 112 cards
        // Change Color x4
        for (int i = 0; i < 4; i++)
            deck.add(new ChangeColorCard());
        // Draw Four x4
        for (int i = 0; i < 4; i++)
            deck.add(new DrawFourCard());
        // Skip, Reverse, Draw Two, 0-9 x2 per color
        for (CardColor color : CardColor.values()) {
            for (int i = 0; i < 2; i++) {
                deck.add(new DrawTwoCard(color));
                deck.add(new SkipCard(color));
                deck.add(new ReverseCard(color));
                for (CardSymbol symbol : CardSymbol.getNumberSymbols()) {
                    deck.add(new NumberCard(color, symbol));
                }
            }
        }

        // Shuffle and hand out 7 cards to each player
        Collections.shuffle(deck);
        for (int i = 0; i < initialPlayerCount * 7; i++) {
            BaseCard card = deck.remove(0);
            playerHands.get(i % initialPlayerCount).add(card);
        }

        // Set the top card of the discard pile
        topCard = deck.remove(0);

        // If top card has no color, give it a random color
        if (topCard.getColor() == null) {
            topCard.setColor(CardColor.randomColor());
        }
    }

    // For student to implement
    public boolean isHandPlayable(int playerIndex) {
        return this.getPlayerHand(playerIndex).stream()
                .anyMatch(card -> card.canPlay());
    }

    public ArrayList<BaseCard> draw(int amount) {
        var drawSize = Math.min(amount, this.deck.size());

        var drawnCards = new ArrayList<BaseCard>();

        for (int i = 0; i < drawSize; i++) {
            drawnCards.add(this.deck.remove(0));
        }

        this.getCurrentPlayerHand().addAll(drawnCards);
        return drawnCards;
    }

    public boolean isGameOver() {
        return getPlayerHand(0).size() == 0 || deck.size() == 0 || getPlayerCount() == 1;
    }

    public int getPlayerCount() {
        int playerCount = 0;
        for (ArrayList<BaseCard> hand : getAllPlayerHands()) {
            if (hand.size() > 0) {
                playerCount++;
            }
        }
        return playerCount;
    }

    public ArrayList<BaseCard> getDeck() {
        return deck;
    }

    public ArrayList<BaseCard> getPlayerHand(int playerIndex) {
        return playerHands.get(playerIndex);
    }

    public ArrayList<ArrayList<BaseCard>> getAllPlayerHands() {
        return playerHands;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<BaseCard> getCurrentPlayerHand() {
        return playerHands.get(currentPlayer);
    }

    public void goToNextPlayer() {
        if (playDirection == PlayDirection.FORWARD)
            currentPlayer = Math.floorMod(currentPlayer + 1, initialPlayerCount);
        else if (playDirection == PlayDirection.BACKWARD)
            currentPlayer = Math.floorMod(currentPlayer - 1, initialPlayerCount);
    }

    public int getInitialPlayerCount() {
        return initialPlayerCount;
    }

    public PlayDirection getPlayDirection() {
        return playDirection;
    }

    public void setPlayDirection(PlayDirection playDirection) {
        this.playDirection = playDirection;
    }

    public int getDrawAmount() {
        return drawAmount;
    }

    public void setDrawAmount(int drawAmount) {
        if (drawAmount >= 0)
            this.drawAmount = drawAmount;
    }

    public void incrementDrawAmount(int amount) {
        this.drawAmount += amount;
    }

    public BaseCard getTopCard() {
        return topCard;
    }

    public void setTopCard(BaseCard topCard) {
        this.topCard = topCard;
    }
}
