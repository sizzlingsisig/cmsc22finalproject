import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {

    public Game() {
        // Initialize the game panel
        setBackground(Color.GREEN); // Example background color
        setLayout(new BorderLayout());

        // Add some game components (for demonstration)
        JLabel gameTitle = new JLabel("Welcome to the Gambling Game!", SwingConstants.CENTER);
        gameTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gameTitle.setForeground(Color.WHITE);
        add(gameTitle, BorderLayout.CENTER);

        // Create a back to menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the game frame and open the main menu
                JFrame gameFrame = (JFrame) SwingUtilities.getWindowAncestor(Game.this);
                if (gameFrame != null) {
                    gameFrame.dispose(); // Dispose of the current game frame
                }

                // Create and show the Main Menu screen
                JFrame mainMenuFrame = new JFrame("Main Menu");
                MainMenu mainMenuPanel = new MainMenu();
                mainMenuFrame.setContentPane(mainMenuPanel);
                mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainMenuFrame.setSize(800, 600); // Set the size of the main menu window
                mainMenuFrame.setLocationRelativeTo(null); // Center the frame on the screen
                mainMenuFrame.setVisible(true);
            }
        });

        // Add the back button to the panel
        add(backButton, BorderLayout.SOUTH);
    }

    // Main method to run the game
    public static void main(String[] args) {
        // Create the game frame
        JFrame gameFrame = new JFrame("Gambling Game");
        Game gamePanel = new Game(); // Create an instance of the Game panel
        gameFrame.setContentPane(gamePanel);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(800, 600); // Set the size of the game window
        gameFrame.setLocationRelativeTo(null); // Center the frame on the screen
        gameFrame.setVisible(true);
    }
}