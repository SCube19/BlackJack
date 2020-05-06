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
        printState(true);
    }
    private void turn()
    {
        bet = player.giveBet();
        deal();
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
                str += "HIDDEN ";
            else
                str += dealer.getHand().get(i).toString() + " ";

        if(!hide)
            str += " " + dealer.getScore();

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
        System.out.println(this.toString(hide));
    }
}
