import java.awt.*;
import javax.swing.*;

// Main Game Class
public class Game {
    private boolean gameStarted = false; // Flag to check if the game has started
    private JPanel leftRow2Content; // Panel for player's cards
    private JPanel rightRow2Content; // Panel for banker's cards

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

    public void startGame() {
        if (!gameStarted) {
            gameStarted = true; // Set the flag to true
            // Call the method to display the cards
            displayCards();
        }
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
    
        // Row 2: Card Images for Player
        leftGbc.gridy = 1;
        leftGbc.weighty = 0.95;
        leftGbc.gridx = 0;
        leftGbc.gridwidth = 3;
        leftRow2Content = new JPanel(new FlowLayout()); // Use FlowLayout for horizontal alignment
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
    
        // Row 2: Card Images for Banker
        rightGbc.gridy = 1;
        rightGbc.weighty = 0.95;
        rightGbc.gridx = 0;
        rightGbc.gridwidth = 3;
        rightRow2Content = new JPanel(new FlowLayout()); // Use FlowLayout for horizontal alignment
        rightRow2Content.setBackground(new Color(80, 0, 0));
    
        rightPanel.add(rightRow2Content, rightGbc);

        gameGbc.gridx = 1;
        gamePanel.add(rightPanel, gameGbc);

        return gamePanel;
    }

    private void displayCards() {
        // Load card images for player (replace with your actual image paths)
        String[] playerCardPaths = {
            "./Cards/cardClubs2.png", 
            "./Cards/cardClubs3.png", 
            "./Cards/cardClubs4.png"
        };

        // Load card images for banker (replace with your actual image paths)
        String[] bankerCardPaths = {
            "./Cards/cardHearts2.png", 
            "./Cards/cardHearts3.png", 
            "./Cards/cardHearts4.png"
        };

        // Add cards to the player's hand with animation
        addCardsToPanelWithAnimation(leftRow2Content, playerCardPaths, new GridBagConstraints());

        // Add cards to the banker's hand with animation
        addCardsToPanelWithAnimation(rightRow2Content, bankerCardPaths, new GridBagConstraints());
    }

    private void addCardsToPanelWithAnimation(JPanel panel, String[] cardPaths, GridBagConstraints gbc) {
        SwingWorker<Void, ImageIcon> worker = new SwingWorker<Void, ImageIcon>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (String path : cardPaths) {
                    ImageIcon cardImage = new ImageIcon(path);
                    publish(cardImage);
                    Thread.sleep(500); // Pause for half a second before adding the next card
                }
                return null;
            }

            @Override
            protected void process(java.util.List<ImageIcon> chunks) {
                for (ImageIcon cardImage : chunks) {
                    JLabel cardLabel = new JLabel(cardImage);
                    panel.add(cardLabel);
                    panel.revalidate(); // Refresh the panel to show the new card
                    panel.repaint(); // Repaint the panel to update the display
                }
            }

            @Override
            protected void done() {
                // Ensure that any remaining cards are added after all processing is complete
                try {
                    get(); // This will throw an exception if something went wrong in doInBackground()
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions appropriately
                }
            }
        };
        worker.execute();
    }
}