package cmsc22groupproj;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private int score;
    private boolean isBanker;

    public Player(boolean isBanker) {
        this.hand = new ArrayList<>();
        this.isBanker = isBanker;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        int total = 0;

        for (Card card : hand) {
            int value = card.getValue();
            if (value > 10) {
                value = 0;
            }
            total += value;
        }

        return total % 10;                              //para magkuha sng last digit sng total
    }

    public void resetHand() {
        hand.clear();
        score = 0;
    }

    public boolean isBanker() {
        return isBanker;
    }
}