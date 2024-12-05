import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.LineBorder;

// Main Game Class
public class Game {

    public Game() {
        createGameFrame(); // Call the method to create the game frame when the Game object is instantiated
    }

    // Method to create the game frame
    private void createGameFrame() {
        JFrame frame = new JFrame("Game Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new GridBagLayout());
        frame.setResizable(true);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Add components using factories
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        frame.add(createGamePanel(), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        frame.add(ButtonPanelFactory.createButtonPanel1(), gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.05;
        frame.add(ButtonPanelFactory.createButtonPanel2(), gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.03;
        frame.add(new TopBar().render(), gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.01;
        frame.add(new HistoryBar().render(), gbc);

        frame.setVisible(true);
    }

    private JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gameGbc = new GridBagConstraints();
        gameGbc.fill = GridBagConstraints.BOTH;
        gameGbc.weightx = 0.5;
        gameGbc.weighty = 1.0;

        // Left Panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(0, 100, 0));
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.fill = GridBagConstraints.BOTH;

        // Row 1: Player Label
        leftGbc.gridy = 0;
        leftGbc.weighty = 0.05;
        leftGbc.weightx = 1.0;
        leftGbc.gridx = 1;
        JLabel playerLabel = new JLabel("Player's Hand");
        playerLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(playerLabel, leftGbc);

        // Row 2: Placeholder Content
        leftGbc.gridy = 1;
        leftGbc.weighty = 0.95;
        leftGbc.gridx = 0;
        leftGbc.gridwidth = 3;
        JPanel leftRow2Content = new JPanel();
        leftRow2Content.setBackground(new Color(0, 80, 0));
        leftPanel.add(leftRow2Content, leftGbc);

        gameGbc.gridx = 0;
        gamePanel.add(leftPanel, gameGbc);

        // Right Panel
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(100, 0, 0));
        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.fill = GridBagConstraints.BOTH;

        // Row 1: Banker Label
        rightGbc.gridy = 0;
        rightGbc.weighty = 0.05;
        rightGbc.weightx = 1.0;
        rightGbc.gridx = 1;
        JLabel bankerLabel = new JLabel("Banker's Hand");
        bankerLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        bankerLabel.setForeground(Color.WHITE);
        bankerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(bankerLabel, rightGbc);

        // Row 2: Placeholder Content
        rightGbc.gridy = 1;
        rightGbc.weighty = 0.95;
        rightGbc.gridx = 0;
        rightGbc.gridwidth = 3;
        JPanel rightRow2Content = new JPanel();
        rightRow2Content.setBackground(new Color(80, 0, 0));
        rightPanel.add(rightRow2Content, rightGbc);

        gameGbc.gridx = 1;
        gamePanel.add(rightPanel, gameGbc);

        return gamePanel;
    }
    
    // Other methods remain unchanged
}

// Factory Class for Creating Button Panels
class ButtonPanelFactory {
    public static JPanel createButtonPanel1() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 6, 6));
        buttonPanel.setBackground(new Color(46, 83, 57));

        String[] labels = {"PLAYER", "TIE", "BANKER"};
        Color[][] gradientColors = {
            {new Color(102, 110, 113), new Color(19, 28, 43)}, // Blue
            {new Color(41, 93, 18), new Color(14, 38, 14)},    // Green
            {new Color(111, 37, 20), new Color(41, 12, 14)}    // Red
        };

        addGradientButtons(buttonPanel, labels, gradientColors);

        JPanel buttonPanel1Container = new JPanel(new BorderLayout());
        buttonPanel1Container.setBackground(new Color(46, 83, 57));
        buttonPanel1Container.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        buttonPanel1Container.add(buttonPanel, BorderLayout.CENTER);

        return buttonPanel1Container;
    }

    public static JPanel createButtonPanel2() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 6, 6));
        buttonPanel.setBackground(new Color(46, 83, 57));

        String[] labels = {"P BONUS", "DOUBLE PERFECT PAIR", "B BONUS", "P PAIR", "EITHER PAIR", "B PAIR"};
        Color[][] gradientColors = {
            {new Color(102, 110, 113), new Color(19, 28, 43)}, // Blue
            {new Color(102, 62, 44), new Color(56, 41, 20)},   // Brown
            {new Color(111, 37, 20), new Color(41, 12, 14)},   // Red
            {new Color(102, 110, 113), new Color(19, 28, 43)}, // Blue
            {new Color(102, 62, 44), new Color(56, 41, 20)},   // Brown
            {new Color(111, 37, 20), new Color(41, 12, 14)}    // Red
        };

        addGradientButtons(buttonPanel, labels, gradientColors);
        JPanel buttonPanel2Container = new JPanel(new BorderLayout());
        buttonPanel2Container.setBackground(new Color(46, 83, 57));
        buttonPanel2Container.setBorder(BorderFactory.createEmptyBorder(0, 10, 6, 10));
        buttonPanel2Container.add(buttonPanel, BorderLayout.CENTER);
        return buttonPanel2Container;
    }

    private static void addGradientButtons(JPanel panel, String[] labels, Color[][] gradientColors) {
        for (int i = 0; i < labels.length; i++) {
            JButton button = new GradientButton(labels[i], gradientColors[i]);
            button.setFont(new Font("Garamond", Font.BOLD, 18));
            button.setForeground(Color.WHITE);
            panel.add(button);
        }
    }
}

// TopBar Component
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

        // Undo Bet Button
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

        // Main Menu Button
        RoundedButton mainMenuButton = new RoundedButton("Main Menu");
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
            // You can add logic here to reset the game state or initialize a new game
        });
        actionButtonPanel.add(startGameButton);

        // Deposit Button
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Garamond", Font.BOLD, 16));
        depositButton.setForeground(Color.BLACK);
        depositButton.addActionListener(e -> System.out.println("Deposit button clicked"));
        actionButtonPanel.add(depositButton);

        // Add margin around the buttons
        actionButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));

        // Add panels to topBar
        topBar.add(buttonPanelContainer, BorderLayout.CENTER);
        topBar.add(actionButtonPanel, BorderLayout.EAST);

        return topBar;
    }
}

// HistoryBar Component
class HistoryBar {
    public JPanel render() {
        JPanel historyBar = new JPanel();
        historyBar.setBackground(new Color(21, 21, 21));
        historyBar.setLayout(new BorderLayout(10, 0));

        JLabel balance = new JLabel("   Balance: ");
        balance.setForeground(Color.WHITE);
        balance.setFont(new Font("Garamond", Font.BOLD, 14));
        historyBar.add(balance, BorderLayout.WEST);

        return historyBar;
    }
}

// ButtonFactory: Encapsulates button creation logic
class ButtonFactory {
    public static JButton createBetButton(int increment, JLabel totalBetLabel) {
        JButton button = new JButton("+" + increment);
        button.setFont(new Font("Garamond", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(25, 25, 25));
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            String text = totalBetLabel.getText();
            int currentBet = Integer.parseInt(text.replaceAll("[^0-9]", ""));
            int newBet = currentBet + increment;
            totalBetLabel.setText("   Total Bet: " + newBet + " php");
        });
        return button;
    }
}

// GradientButton Class: Custom button with gradient rendering
class GradientButton extends JButton {
    private final Color[] gradientColors;
    private final int cornerRadius;

    public GradientButton(String text, Color[] gradientColors) {
        super(text);
        this.gradientColors = gradientColors;
        this.cornerRadius = 15;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        GradientPaint gradient = new GradientPaint(0, 0, gradientColors[0], 0, getHeight(), gradientColors[1]);
        g2d.setPaint(gradient);
        g2d.fill(roundedRect);

        super.paintComponent(g2d);
        g2d.dispose();
    }
}

// Main method should be in a separate file