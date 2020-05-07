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
    private void updateScore()
    {
        score = 0;
       for(Card x: hand)
       {
           if(x.getValue() == 11 && score + 11 > 21)
               score += 1;
           else
               score += x.getValue();
       }
    }

    public void addCard(Card toAdd)
    {
        hand.add(toAdd);

        if(hand.get(hand.size() - 1).getValue() == 11 && score + 11 > 21)
            score += 1;
        else
            score += hand.get(hand.size() - 1).getValue();
    }

    public void removeCard()
    {
        hand.remove(hand.size() - 1);
        updateScore();
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

}
