package ch7OODesign.CardDesignProblem;

import java.util.ArrayList;

/**
 * Created by kevint on 4/30/2016.
 */
public class Hand <T extends Card>{
    protected ArrayList<T> cards = new ArrayList<>();

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }

    public void print() {
        for (Card card : cards) {
            card.print();
        }
    }
}
