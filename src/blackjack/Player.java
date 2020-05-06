package blackjack;
import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
    private int money;
    private final ArrayList<Card> hand;
    private int score;

    public Player(int money)
    {
        this.money = money;
        hand = new ArrayList<Card>();
        score = 0;
    }

    public void requestCard(Dealer dealer)
    {
        Card tmp = dealer.giveTop();
        hand.add(tmp);

        if(tmp.getValue() == 11 && score + tmp.getValue() > 21)
             score += 1;
        else
            score += tmp.getValue();
    }

    public int giveBet()
    {
        Scanner scanner = new Scanner(System.in);

        int bet = money + 1;
        while(bet > money)
            bet = scanner.nextInt();

        money -= bet;
        return bet;
    }

    public int giveBet(int bet)
    {
        money -= bet;
        return bet;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public int getScore()
    {
        return score;
    }

    public int getMoney()
    {
        return money;
    }

    public void addMoney(int money)
    {
        this.money += money;
    }

    public boolean canSplit()
    {
        return (hand.size() == 2 && hand.get(0).getValue() == hand.get(1).getValue());
    }

}
