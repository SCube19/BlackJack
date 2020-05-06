package blackjack;

public class Card
{
    private int number;
    private Color color;
    private int value;

    public Card(int number, Color color)
    {
        this.number = number;
        this.color = color;
        value = number;

        if(number > 10)
            value = 10;
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
        String str = new String();
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
