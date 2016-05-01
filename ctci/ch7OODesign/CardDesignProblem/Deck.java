package ch7OODesign.CardDesignProblem;

import java.util.ArrayList;

/**
 * Created by Kevin on 4/30/16.
 */

// What does extends do?
    // Means that the declared class is a subclass of a what ever is after "extends"
    // This whole statement "<T extends Card>" means that the deck will take any types
    // that are the subclasses of card.
public class Deck <T extends Card> {
    // T here denotes a generic type. Meaning you can pass in any
    // type argument you want.
    private ArrayList<T> cards;
    private int dealtIndex = 0;

    // We are only going to set a list of cards that are subclasses of the main card
    // class
    public void setDeckOfCards(ArrayList<T> deckOfCards) {
        cards = deckOfCards;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int randomIdx = randomIntInRange(i, cards.size() - i - 1);
            T card1 = cards.get(i);
            T card2 = cards.get(randomIdx);
            cards.set(i, card2);
            cards.set(randomIdx, card1);
        }
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    // We are going to return an array of generic type defined in the
    // class definition
    public T[] dealHand(int number) {
        if (remainingCards() < number) {
            return null;
        }

        T[] hand = (T[]) new Card[number];
        int count = 0;
        while (count < number) {
            T card = dealCard();
            if (card != null) {
                hand[count] = card;
                count++;
            }
        }

        return hand;
    }

    public T dealCard() {
        if (remainingCards() == 0) {
            return null;
        }

        T card = cards.get(dealtIndex);
        card.markUnavailable();
        dealtIndex++;

        return card;
    }

    public void print() {
        for (Card card : cards) {
            card.print();
        }
    }

    private int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    private int randomInt(int n) {
        // Math.random returns a decimal between 0 and 1, inclusive
        return (int) (Math.random() * n);
    }
}
