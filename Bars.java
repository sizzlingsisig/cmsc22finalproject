import java.awt.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.LineBorder;

// TopBar Component
class TopBar {
    private Stack<Integer> betHistory = new Stack<>(); // Stack to track bets
    private int totalBet = 0; // Total bet amount

    public JPanel render() {
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(6, 57, 112));
        topBar.setLayout(new BorderLayout());
        topBar.setBorder(new LineBorder(Color.BLACK, 3));

        // Total Bet Label
        JLabel totalBetLabel = new JLabel("   Total Bet: 0 php");
        totalBetLabel.setFont(new Font("Garamond", Font.BOLD, 14));
        totalBetLabel.setForeground(Color.WHITE);
        topBar.add(totalBetLabel, BorderLayout.WEST);

        // Button Panel
        JPanel buttonPanelContainer = new JPanel(new GridLayout(1, 8, 6, 6)); // Adjusted to accommodate new buttons
        buttonPanelContainer.setBackground(new Color(6, 57, 112));
        buttonPanelContainer.setBorder(BorderFactory.createEmptyBorder(5, 300, 5, 200));

        // Bet Increment Buttons
        int[] betIncrements = {50, 250, 1000, 5000, 25000};
        for (int increment : betIncrements) {
            JButton button = ButtonFactory.createBetButton(increment, totalBetLabel);
            button.addActionListener(e -> {
                betHistory.push(increment); // Track the bet
                totalBet += increment; // Update total bet
                totalBetLabel.setText("   Total Bet: " + totalBet + " php");
            });
            buttonPanelContainer.add(button);
        }

        // Panel for Undo, Main Menu, and Start Game Buttons
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionButtonPanel.setBackground(new Color(6, 57, 112));

        // Undo Bet Button (RoundedButton)
        RoundedButton undoBetButton = new RoundedButton("Undo Bet");
        undoBetButton.setFont(new Font("Garamond", Font.BOLD, 16));
        undoBetButton.setForeground(Color.BLACK);
        undoBetButton.addActionListener(e -> {
            if (!betHistory.isEmpty()) {
                int lastBet = betHistory.pop(); // Remove the latest bet
                totalBet -= lastBet; // Subtract from total
                totalBetLabel.setText("   Total Bet: " + totalBet + " php");
            }
        });
        actionButtonPanel.add(undoBetButton);

        // Deposit Button (RoundedButton)
        RoundedButton depositButton = new RoundedButton("Deposit");
        depositButton.setFont(new Font("Garamond", Font.BOLD, 16));
        depositButton.setForeground(Color.BLACK);
        depositButton.addActionListener(e -> System.out.println("Deposit button clicked"));
        actionButtonPanel.add(depositButton);

        // Main Menu Button (JButton)
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font("Garamond", Font.BOLD, 16));
        mainMenuButton.setForeground(Color.BLACK);
        mainMenuButton.addActionListener(e -> {
            // Dispose of the current frame and show the main menu
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(topBar);
            if (frame != null) {
                frame.dispose(); // Dispose the current game frame
            }
            MainMenu.createMainMenuFrame(); // Show the main menu
        });
        actionButtonPanel.add(mainMenuButton);

            // Start Game Button
    JButton startGameButton = new JButton("Start Game");
    startGameButton.setFont(new Font("Garamond", Font.BOLD, 16));
    startGameButton.setForeground(Color.BLACK);
    startGameButton.addActionListener(e -> {
        // Logic to start a new game
        System.out.println("Starting a new game...");
        Game game = new Game(); // Create a new Game instance
        game.startGame(); // Call the method to start the game and display cards
    });
    actionButtonPanel.add(startGameButton);
        // Add margin around the buttons
        actionButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));

        // Add panels to topBar
        topBar.add(buttonPanelContainer, BorderLayout.CENTER);
        topBar.add(actionButtonPanel, BorderLayout.EAST);

        return topBar;
    }
}