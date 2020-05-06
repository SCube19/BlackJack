package blackjack;
import java.util.Random;

public class Dealer
{
    private Deck deck;

    public Dealer(int difficulty)
    {
        deck = new Deck(difficulty);
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

    public Deck getDeck()
    {
        return deck;
    }

    public Card giveTop()
    {
        Card rCard = deck.getCards().get(0);
        deck.getCards().remove(0);

        return rCard;
    }
}
