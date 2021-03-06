package blackjack;

import java.io.IOException;
import java.util.Scanner;

public class Game
{
    private final Dealer dealer;
    private final Player player;
    private int bet;
    private boolean split;

    public Game(int difficulty, int startMoney)
    {
        dealer = new Dealer(difficulty);
        player = new Player(startMoney);
    }

    public void start() throws IOException, InterruptedException
    {
       while(true)
       {
           clear();
           if (player.getMoney() == 0)
           {
               System.out.println("YOU HAVE NO MONEY, THE HOUSE WON! :c");
               Runtime.getRuntime().exit(0);
           }

           System.out.print("Money: " + player.getMoney() + "\nPlace bet: ");
           bet = player.giveBet();
           deal();
           printState(true);

           split = false;
           float multiplier = 2.0f;

           if (player.getScore(1) == 21)
           {
               multiplier = 2.5f;
               System.out.println("BLACKJACK!");
               Thread.sleep(1200);
           }
           else
           {
               Scanner scanner = new Scanner(System.in);
               int decision = 0;
               boolean valid = true;

               while (player.getScore(1) < 21)
               {
                   if (valid)
                       System.out.println("\u001B[34m" + "\n1.STAND" + "\u001B[31m" + "  2.HIT" + "\u001B[32m" + "   3.DOUBLE" + "\u001B[35m" + "  4.SPLIT" +
                               "\u001B[37m" + "  5.EXIT");

                   decision = scanner.nextInt();
                   valid = false;

                   if (decision == 1)
                       break;
                   if (decision == 2)
                   {
                       hit(1);
                       valid = true;
                   }
                   if (decision == 3 && player.getMoney() >= bet && player.getHand(1).getHand().size() == 2)
                   {
                       _double();
                       break;
                   }
                   if (decision == 4 && player.getMoney() >= bet && player.getHand(1).getHand().size() == 2 && player.canSplit())
                   {
                       split = true;
                       player.split();
                       int original_bet = bet;
                       bet += player.giveBet(bet);

                      if(!simpleDecision(1))
                          bet -= original_bet;
                      if(!simpleDecision(2))
                          bet -= original_bet;

                       break;
                   }
                   if (decision == 5)
                       Runtime.getRuntime().exit(0);
               }
           }

           if (player.getScore(1) > 21 && (player.getScore(2) > 21 || player.getScore(2) == 0))
           {
               System.out.println("\nPLAYER BUST!");
           }
           else
           {
              dealerDraw();

              int biggerScore = chooseBigger();

               if (dealer.getScore() == biggerScore)
               {
                   multiplier = 1;
                   System.out.println("\nPUSH!\n\nYOU GET BACK " + (int) (bet * multiplier) + " COINS!");
               }
               else if (dealer.getScore() > 21)
               {
                   System.out.println("\nDEALER BUST!");
                   System.out.println("\n\nYOU WIN " + (int) (bet * multiplier) + " COINS!");
               }
               else if (dealer.getScore() > biggerScore)
               {
                   multiplier = 0;
                   System.out.println("\nDEALER WINS!\n\nYOU LOSE " + bet + " COINS!");
               }
               else
                   System.out.println("\n\nYOU WIN " + (int) (bet * multiplier) + " COINS!");

               player.addMoney((int) (bet * multiplier));
           }
           Thread.sleep(2000);
           player.giveUpCards();
           dealer.giveUpCards();
           split = false;
       }
    }

    private void deal()
    {
        player.requestCard(dealer, 1);
        dealer.requestCard();
        player.requestCard(dealer, 1);
        dealer.requestCard();
    }

    private String toString(boolean hide)
    {
        String reset = "\u001B[0m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";

        String str = new String();
        for(int i = 0; i < dealer.getHand().getHand().size(); i++)
            if(hide && i > 0)
                break;
            else
                str += dealer.getHand().getHand().get(i).toString() + " ";

        if(!hide)
            str += (yellow + " (SCORE: " + dealer.getScore() + ")" + reset);
        else
            str += "\u001B[36m" + "HIDDEN " + reset;

        str += "\n\n";
        str += "  " + "\u001B[32m" + bet;
        str += "\n\n";


        for(Card x: player.getHand(1).getHand())
            str += x.toString() + " ";
        str += (yellow + " (SCORE: " + player.getScore(1) + ")" + reset);

        if(split)
        {
            str += " ||| ";
            for (Card x : player.getHand(2).getHand())
                str += x.toString() + " ";
            str += (yellow + " (SCORE: " + player.getScore(2) + ")" + reset);
        }
        str += (" ||" + green + " MONEY: " + player.getMoney() + reset);

        return str;
    }

    private void printState(boolean hide) throws IOException, InterruptedException
    {
        clear();
        System.out.println(this.toString(hide));
    }

    private void clear() throws IOException, InterruptedException
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    private void hit(int i) throws IOException, InterruptedException
    {
        player.requestCard(dealer, i);
        printState(true);
    }
    private void _double() throws IOException, InterruptedException
    {
        bet += player.giveBet(bet);
        player.requestCard(dealer, 1);
        printState(true);
    }
    private boolean simpleDecision(int handNum) throws IOException, InterruptedException
    {
        Scanner scanner = new Scanner(System.in);
        int decision = 0;
        boolean valid = true;

        player.requestCard(dealer, handNum);
        printState(true);
        String str = new String();
        if(handNum == 2)
        {
            for (Card x : player.getHand(1).getHand())
                str += "    ";
            str += "                   ";
        }

        if(player.getHand(handNum).getHand().get(0).getValue() != 11)
        while(player.getHand(handNum).getScore() < 21)
        {

            if(valid)
            {
                System.out.println(str + "^");
                System.out.println("\u001B[34m" + "\n1.STAND" + "\u001B[31m" + "  2.HIT" + "\u001B[32m" + "   3.DOUBLE" + "\u001B[0m");
            }

            decision = scanner.nextInt();
            valid = false;

            if(decision == 1)
                return true;
            if(decision == 2)
            {
                hit(handNum);
                valid = true;
            }
            if(decision == 3)
                Runtime.getRuntime().exit(0);
        }

        if(player.getHand(handNum).getScore() == 21)
            return true;
        return false;
    }

    private void dealerDraw() throws IOException, InterruptedException
    {
        printState(false);
        Thread.sleep(2000);
        while (dealer.getScore() < 17)
        {
            dealer.requestCard();
            printState(false);
            Thread.sleep(2000);
        }
    }

    private int chooseBigger()
    {
        if(player.getScore(1) > 21)
            return player.getScore(2);
        else if(player.getScore(2) > 21)
            return player.getScore(1);
        return Math.max(player.getScore(1), player.getScore(2));

    }
}
