package ch7OODesign.CardDesignProblem;

import java.util.ArrayList;

/**
 * Created by Kevin on 4/30/16.
 */
public class Deck <T extends Card> {
    // TODO: What does T do?
    private ArrayList<T> cards;
    private int dealtIndex = 0;

    public void setDeckOfCards(ArrayList<T> deckOfCards) {
        cards = deckOfCards;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int randomIdx = randomIntInRange(i, cards.size() - i - 1);
        }
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    private int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    private int randomInt(int n) {
        // TODO: Look up with math random exactly returns
        return (int) (Math.random() * n);
    }
}
