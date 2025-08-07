package ca.sheridancollege.project;

public class Dealer extends Player {
    public Dealer() {
        super("Dealer");
    }

    public void playTurn(GroupOfCards deck) {
        while (calculateTotal() < 17) {
            hit(deck);
        }
    }
}
