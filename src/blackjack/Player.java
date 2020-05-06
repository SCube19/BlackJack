package blackjack;
import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
    private int money;
    private Hand hand;
    private Hand hand_split;

    public Player(int money)
    {
        this.money = money;
        hand = new Hand();
        hand_split = new Hand();
    }

    public void requestCard(Dealer dealer, int i)
    {
        Hand tmpHand;
        if(i == 1)
            tmpHand = hand;
        else
            tmpHand = hand_split;

        Card tmp = dealer.giveTop();
        tmpHand.getHand().add(tmp);

        if(tmp.getValue() == 11 && tmpHand.getScore() + tmp.getValue() > 21)
             tmpHand.addScore(1);
        else
             tmpHand.addScore(tmp.getValue());
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

    public Hand getHand(int which)
    {
        return hand;
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
        return (hand.getHand().size() == 2 && hand.getHand().get(0).getValue() == hand.getHand().get(1).getValue());
    }

    public int getScore(int which)
    {
        if(which == 1)
            return hand.getScore();
        return hand_split.getScore();
    }

    public void split()
    {
        hand_split.getHand().add(hand.getHand().get(1));
        hand.getHand().remove(1);
    }
}
