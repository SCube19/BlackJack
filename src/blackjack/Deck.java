package blackjack;
import java.util.ArrayList;

public class Deck
{
    private final ArrayList<Card> cards;

    public Deck(int numberOfDecks)
    {
        cards = new ArrayList<Card>(52 * numberOfDecks);
        Color tmp;

        for(int q = 0; q < numberOfDecks; q++)
            for(int  i = 0; i < 4; i++)
            {
                tmp = Color.values()[i];
                for(int j = 1; j <= 13; j++)
                    cards.add(new Card(j, tmp));
            }
    }

    public String toString()
    {
        String str = "";
        for(Card x: cards)
            str += (x.toString() + " ");

        return str;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }
}
