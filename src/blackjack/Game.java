package blackjack;

public class Game
{
    private Dealer dealer;
    private Player player;
    private int bet;

    public Game(int difficulty, int startMoney)
    {
        dealer = new Dealer(difficulty);
        player = new Player(startMoney);
    }

    public void start()
    {
        turn();
    }
    private void turn() {
        bet = player.giveBet();
        deal();
        printState(true);

        float multiplier = 2.0f;
        if (player.getScore() == 21)
            multiplier = 2.5f;
        else
        {

        }
        dealer.putCards();
        printState(false);

        if (dealer.getScore() == player.getScore())
        {
            multiplier = 1;
            System.out.println("\nPUSH!");
        }
        else if (dealer.getScore() > 21)
            System.out.println("\nDEALER BUST!");
        else if(dealer.getScore() > player.getScore())
        {
            multiplier = 0;
            System.out.println("\nDEALER WINS!");
        }


        System.out.println("\n\nYOU WIN " + (int)(bet*multiplier) + " COINS!");
        player.addMoney((int)(bet * multiplier));

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

    private void printState(boolean hide)
    {
        clear();
        System.out.println(this.toString(hide));
    }

    private void clear()
    {
        System.out.print("\033[H\033[2J");
    }
}
