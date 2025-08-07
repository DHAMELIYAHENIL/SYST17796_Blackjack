package ca.sheridancollege.project;

import java.util.*;

public class Game {
    private GroupOfCards deck;
    private Player player;
    private Dealer dealer;
    private Map<String, Integer> scores;

    public Game(String playerName) {
        player = new Player(playerName);
        dealer = new Dealer();
        scores = new HashMap<>();
        scores.put(playerName, 0);
        scores.put("Dealer", 0);
    }

    public void start() {
        deck = createDeck();
        deck.shuffle();

        player.resetHand();
        dealer.resetHand();

        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);

        showHands(false);
    }

    private GroupOfCards createDeck() {
        GroupOfCards deck = new GroupOfCards(52);
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.addCard(new Card(suit, rank));
            }
        }

        return deck;
    }

    public void evaluateWinner() {
        int playerTotal = player.calculateTotal();
        int dealerTotal = dealer.calculateTotal();

        System.out.println("\nFinal Hands:");
        showHands(true);

        if (playerTotal > 21) {
            System.out.println("Player busted! Dealer wins.");
            scores.put("Dealer", scores.get("Dealer") + 1);
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("Player wins!");
            scores.put(player.getName(), scores.get(player.getName()) + 1);
        } else if (dealerTotal > playerTotal) {
            System.out.println("Dealer wins!");
            scores.put("Dealer", scores.get("Dealer") + 1);
        } else {
            System.out.println("It's a tie!");
        }

        System.out.println("Scores: " + scores);
    }

    private void showHands(boolean revealDealer) {
        System.out.println(player.getName() + "'s Hand: " + player.getHand().getCards() + " (Total: " + player.calculateTotal() + ")");
        if (revealDealer) {
            System.out.println("Dealer's Hand: " + dealer.getHand().getCards() + " (Total: " + dealer.calculateTotal() + ")");
        } else {
            System.out.println("Dealer's Hand: [" + dealer.getHand().getCards().get(0) + ", Hidden]");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GroupOfCards getDeck() {
        return deck;
    }

    public boolean promptPlayAgain(Scanner sc) {
        System.out.print("\nPlay another round? (yes/no): ");
        String response = sc.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }
}
