package blackjack;

public enum Color
{
    HEARTS, SPADES, CLUBS, DIAMONDS;

    public String literal()
    {
        switch (this.ordinal())
        {
            case 0: return "H";
            case 1: return "S";
            case 2: return "C";
            case 3: return "D";
        }
        return "-";
    }
}
