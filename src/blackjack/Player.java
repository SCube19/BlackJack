package blackjack;
import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
    private int money;
    private ArrayList<Card> hand;
    private int score;

    public Player(int money)
    {
        this.money = money;
        hand = new ArrayList<Card>();
        score = 0;
    }

    public void requestCard(Dealer dealer)
    {
        hand.add(dealer.giveTop());
    }

    public int giveBet()
    {
        Scanner scanner = new Scanner(System.in);
        
        int bet = money + 1;
        while(bet >= money)
            bet = scanner.nextInt();

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
}
