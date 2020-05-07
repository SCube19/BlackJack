package blackjack;

public enum Color
{
    HEARTS, SPADES, CLUBS, DIAMONDS;

    public String literal()
    {
        String str = new String();
        switch (this.ordinal())
        {
            case 0: str =  "2665"; break;
            case 1: str = "2660"; break;
            case 2: str = "2663"; break;
            case 3: str = "2666"; break;
        }
        return String.valueOf(Character.toChars(Integer.parseInt(str, 16)));
    }
    public String colorize()
    {
        if(this.ordinal() == 0 || this.ordinal() == 3)
            return "\u001B[31m";
        return "\u001B[34m";
    }
}
