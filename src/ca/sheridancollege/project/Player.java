package ca.sheridancollege.project;

public class Player {
    private String name;
    protected GroupOfCards hand;

    public Player(String name) {
        this.name = name;
        this.hand = new GroupOfCards(0); // Size is not used here but needed for constructor
    }

    public void hit(GroupOfCards deck) {
        hand.addCard(deck.drawCard());
    }

    public void stand() {
        // This could be expanded in UI
    }

    public GroupOfCards getHand() {
        return hand;
    }

    public int calculateTotal() {
        return hand.calculateTotal();
    }

    public void resetHand() {
        hand.clear();
    }

    public String getName() {
        return name;
    }
}
