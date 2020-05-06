package blackjack;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Game
{
    private final Dealer dealer;
    private final Player player;
    private int bet;

    public Game(int difficulty, int startMoney)
    {
        dealer = new Dealer(difficulty);
        player = new Player(startMoney);
    }

    public void start() throws IOException, InterruptedException
    {
        turn();
    }
    private void turn() throws IOException, InterruptedException
    {
        bet = player.giveBet();
        deal();
        printState(true);

        float multiplier = 2.0f;

        if (player.getScore() == 21)
            multiplier = 2.5f;
        else
        {
            Scanner scanner = new Scanner(System.in);
            int decision = 0;
            boolean valid = true;

            while(player.getScore() < 21)
            {
                decision = 0;
                if(valid)
                    System.out.println("\n1.STAND  2.HIT  3.DOUBLE  4.SPLIT");

                decision = scanner.nextInt();
                valid = false;

                if(decision == 1)
                    break;
                if(decision == 2)
                {
                    hit();
                    valid = true;
                }
                if(decision == 3 && player.getMoney() >= bet && player.getHand().size() == 2)
                {
                   _double();
                    break;
                }
                if(decision == 4 && player.getHand().size() == 2 && player.canSplit())
                {
                    
                }
            }
        }

        if(player.getScore() > 21)
            System.out.println("\nPLAYER BUST!");
        else
        {
            printState(false);
            while (dealer.getScore() < 17)
            {
                Thread.sleep(2000);
                dealer.requestCard();
                printState(false);

            }

            if (dealer.getScore() == player.getScore())
            {
                multiplier = 1;
                System.out.println("\nPUSH!\n\nYOU GET BACK " + (int) (bet * multiplier) + " COINS!");
            }
            else if (dealer.getScore() > 21)
            {
                System.out.println("\nDEALER BUST!");
                System.out.println("\n\nYOU WIN " + (int) (bet * multiplier) + " COINS!");
            }
            else if (dealer.getScore() > player.getScore())
            {
                multiplier = 0;
                System.out.println("\nDEALER WINS!\n\nYOU LOSE " + bet + " COINS!");
            }
            else
                System.out.println("\n\nYOU WIN " + (int) (bet * multiplier) + " COINS!");

            player.addMoney((int) (bet * multiplier));
        }

    }

    private void deal()
    {
        player.requestCard(dealer);
        dealer.requestCard();
        player.requestCard(dealer);
        dealer.requestCard();
    }

    public String toString(boolean hide)
    {
        String str = new String();
        for(int i = 0; i < dealer.getHand().size(); i++)
            if(hide && i > 0)
                break;
            else
                str += dealer.getHand().get(i).toString() + " ";

        if(!hide)
            str += " " + dealer.getScore();
        else
            str += "HIDDEN ";

        str += "\n\n";
        str += "  " + bet;
        str += "\n\n";

        for(Card x: player.getHand())
            str += x.toString() + " ";

        str += " " + player.getScore();
        str += "\n  " + player.getMoney();

        return str;
    }

    private void printState(boolean hide) throws IOException, InterruptedException
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        clear();
        System.out.println(this.toString(hide));
    }

    private void clear() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private void hit() throws IOException, InterruptedException
    {
        player.requestCard(dealer);
        printState(true);
    }
    private void _double() throws IOException, InterruptedException
    {
        bet += player.giveBet(bet);
        player.requestCard(dealer);
        printState(true);
    }
}
