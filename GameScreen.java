import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {
    
    public GameScreen() {
        // Set the title of the frame
        setTitle("Game Screen");
        
        // Set the layout to a 3x3 grid
        setLayout(new GridLayout(3, 3));
        
        // Create and add 9 empty panels to the frame
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional: Add a border to see the panels
            add(panel);
        }
        
        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the frame
        setSize(600, 600); // You can adjust the size as needed
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
    
    // Static method to create and display the GameScreen
    public static void createAndShowGameScreen() {
        // Create an instance of GameScreen
        GameScreen gameScreen = new GameScreen();
        // Make the frame visible
        gameScreen.setVisible(true);
    }
}