package blackjack;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
	    Game game = new Game(1, 1000);
	    game.start();

    }
}
