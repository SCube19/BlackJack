package blackjack;
import java.util.ArrayList;
import java.util.Random;

public class Dealer
{
    private Deck deck;
    private final ArrayList<Card> hand;
    private final int difficulty;
    private int score;

    public Dealer(int difficulty)
    {
        deck = new Deck(difficulty);
        shuffle();

        hand = new ArrayList<Card>();
        this.difficulty = difficulty;
        score = 0;
    }

    public void shuffle()
    {
        Random rand = new Random();

        for(int i = 0; i < deck.getCards().size(); i++)
        {
            int index = rand.nextInt(deck.getCards().size() - i) + i;

            Card tmp = deck.getCards().get(index);
            deck.getCards().set(i, deck.getCards().get(index));
            deck.getCards().set(index, tmp);
        }
    }

    public int getScore()
    {
        return score;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public Card giveTop()
    {
        if(deck.getCards().size() == 0)
            deck = new Deck(difficulty);

        Card rCard = deck.getCards().get(0);
        deck.getCards().remove(0);

        return rCard;
    }

    public void requestCard()
    {
        Card tmp = giveTop();
        hand.add(tmp);

        score += tmp.getValue();
        if(score > 21)
        {
            for(Card x: hand)
                if(x.getValue() == 11)
                {
                    score -= 10;
                    if(score <= 21)
                        return;
                }
        }
    }

}
