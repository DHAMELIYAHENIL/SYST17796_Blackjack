package ca.sheridancollege.project;

import java.util.Scanner;

public class GamePlay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        Game game = new Game(name);

        boolean playAgain = true;
        while (playAgain) {
            game.start();

            Player player = game.getPlayer();
            GroupOfCards deck = game.getDeck();

            boolean playerTurn = true;
            while (playerTurn && player.calculateTotal() < 21) {
                System.out.print("\nDo you want to hit or stand? ");
                String action = sc.nextLine().trim().toLowerCase();

                if (action.equals("hit")) {
                    player.hit(deck);
                    System.out.println("Your hand: " + player.getHand().getCards() + " (Total: " + player.calculateTotal() + ")");
                } else if (action.equals("stand")) {
                    playerTurn = false;
                } else {
                    System.out.println("Invalid input. Please type 'hit' or 'stand'.");
                }
            }

            if (player.calculateTotal() <= 21) {
                game.getDealer().playTurn(deck);
            }

            game.evaluateWinner();

            playAgain = game.promptPlayAgain(sc);
        }

        System.out.println("Thanks for playing!");
    }
}
