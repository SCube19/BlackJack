package blackjack;

public class Main {

    public static void main(String[] args) {
	    Dealer dealer = new Dealer(1);

	    dealer.shuffle();

	    System.out.println(dealer.getDeck().toString());
    }
}
