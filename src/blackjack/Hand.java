package blackjack;
import java.util.ArrayList;

public class Hand
{
    private ArrayList<Card> hand;
    private int score;

    public Hand()
    {
        hand = new ArrayList<Card>();
        score = 0;
    }

    public int getScore()
    {
        return score;
    }

    public void addScore(int toAdd)
    {
        score += toAdd;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }
}
