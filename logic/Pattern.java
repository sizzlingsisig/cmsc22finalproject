package logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pattern extends JFrame {
    private Player player;
    private Player banker;
    private Deck deck;
    private double playerBalance;
    private boolean mainBetPlaced; // Flag for main bet
    private List<Bet> sideBets; // List to store side bets
    private List<String> longRoadPattern; // List to track the Long Road pattern
    private JPanel gridPanel; // Panel for displaying the grid

    public Pattern(double initialBalance) {
        // Initialize game components
        this.deck = new Deck();
        this.player = new Player(false);
        this.banker = new Player(true);
        this.playerBalance = initialBalance;
        this.mainBetPlaced = false;
        this.sideBets = new ArrayList<>();
        this.longRoadPattern = new ArrayList<>();

        // Initialize the JFrame
        setTitle("Baccarat Long Road Pattern");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a grid panel to display the Long Road pattern
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(10, 10)); // 10x10 grid (adjust size as needed)

        // Add the grid to the JFrame
        add(gridPanel, BorderLayout.CENTER);

        // Add control buttons
        JPanel controlPanel = new JPanel();
        JButton resolveRoundButton = new JButton("Resolve Round");
        resolveRoundButton.addActionListener(e -> resolveRound());
        controlPanel.add(resolveRoundButton);

        JButton placeBetButton = new JButton("Place Main Bet");
        placeBetButton.addActionListener(e -> placeMainBet(new Bet(Bet.BetType.PLAYER, 100))); // Example main bet
        controlPanel.add(placeBetButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    // Method to place a main bet (Player, Banker, Tie)
    public boolean placeMainBet(Bet mainBet) {
        if (mainBet != null) {
            this.mainBetPlaced = true; // Main bet placed
            return true;
        }
        return false;
    }

    // Resolve a round (main bet) and update the Long Road pattern
    public void resolveRound() {
        int playerScore = player.calculateScore();
        int bankerScore = banker.calculateScore();
        String result = "";

        // Determine the result of the round
        if (playerScore > bankerScore) {
            result = "P"; // Player wins
        } else if (bankerScore > playerScore) {
            result = "B"; // Banker wins
        } else {
            result = "T"; // Tie
        }

        // Update Long Road pattern
        longRoadPattern.add(result);

        // Handle main bet result (as previously implemented)
        Bet mainBet = new Bet(result.equals("P") ? Bet.BetType.PLAYER : Bet.BetType.BANKER, 100);
        double payout = mainBet.resolveBet(player, banker);
        playerBalance += payout;

        System.out.println("Round result: " + result);
        displayLongRoadPattern(); // Display the updated pattern
    }

    // Method to display the Long Road pattern in grid format
    public void displayLongRoadPattern() {
        // Clear the grid panel
        gridPanel.removeAll();

        // Add each outcome (P, B, T) to the grid
        for (String outcome : longRoadPattern) {
            JButton cellButton = new JButton(outcome);
            cellButton.setBackground(getColorForOutcome(outcome));
            gridPanel.add(cellButton);
        }

        // Revalidate and repaint to update the grid
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // Helper method to get the color based on the outcome
    private Color getColorForOutcome(String outcome) {
        switch (outcome) {
            case "P":
                return Color.GREEN; // Player wins (green)
            case "B":
                return Color.RED; // Banker wins (red)
            case "T":
                return Color.BLUE; // Tie (blue)
            default:
                return Color.GRAY; // Default color
        }
    }

    public static void main(String[] args) {
        // Create and show the game window
        GameGUI gameGUI = new GameGUI(1000);
        gameGUI.setVisible(true);

        // Add a sample main bet and resolve a round
        gameGUI.placeMainBet(new Bet(Bet.BetType.PLAYER, 100));
        gameGUI.resolveRound();
    }
}
