package blackjack;

public class Card
{
    private final int number;
    private final Color color;
    private int value;

    public Card(int number, Color color)
    {
        this.number = number;
        this.color = color;
        value = number;

        if(number > 10)
            value = 10;
        else if(number == 1)
            value = 11;
    }

    public int getValue()
    {
        return value;
    }
    public Color getColor()
    {
        return color;
    }
    public String toString()
    {
        String str = "";
        if(number == 1)
            str += "A";
        else if(number == 11)
            str += "J";
        else if(number == 12)
            str += "Q";
        else if (number == 13)
            str += "K";
        else
            str += number;
        str += "|" + color.literal();

        return str;
    }

}
