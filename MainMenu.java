import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Class for the main menu
public class MainMenu extends JPanel {
    private Image backgroundImage;
    private JPanel[] panels; // Array to hold the panels
    
    public MainMenu() {
        // Load the background image
        backgroundImage = new ImageIcon("C:/Users/cj/Downloads/git/cmsc22finalproject/297.png").getImage();
    
        // Check if the image is loaded correctly
        if (backgroundImage.getWidth(null) == -1 || backgroundImage.getHeight(null) == -1) {
            System.err.println("Image not found or failed to load!");
        }
    
        // Set layout for the main panel
        setLayout(new GridLayout(3, 3)); // 3x3 grid layout
    
        // Initialize the panel array
        panels = new JPanel[9];
    
        // Create the first six panels in a loop
        for (int i = 0; i < 6; i++) {
            panels[i] = new JPanel();
            panels[i].setOpaque(false); // Set the panels to be transparent
            panels[i].setLayout(new BorderLayout()); // Use BorderLayout for positioning
            add(panels[i]); // Add the panel to the grid
        }

        // Manually create and add buttons to the bottom three panels
        panels[6] = createButtonPanel("LETS GO GAMBLING");
        panels[7] = createButtonPanel("SHOW PROFILE");
        panels[8] = createButtonPanel("EXIT");
    }
    
    private JPanel createButtonPanel(String buttonText) {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Set the panel to be transparent
        panel.setLayout(new BorderLayout()); // Use BorderLayout for positioning
        
        JButton button = new JButton(buttonText);
        button.addActionListener(new ButtonClickListener(buttonText)); // Pass button text to listener
        panel.add(button, BorderLayout.CENTER); // Add button to the center
        add(panel); // Add the panel to the grid
        
        return panel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    
    private class ButtonClickListener implements ActionListener {
        private String buttonText;

        public ButtonClickListener(String buttonText) {
            this.buttonText = buttonText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (buttonText) {
                case "LETS GO GAMBLING":
                    // Dispose of the current frame
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MainMenu.this);
                    if (frame != null) {
                        frame.dispose(); // Dispose the main menu frame
                    }
                    // Show the GameScreen
                    GameScreen.createAndShowGameScreen();
                    break;
                case "SHOW PROFILE":
                    // Action for the profile button
                    JOptionPane.showMessageDialog(MainMenu.this, "Showing profile information...");
                    break;
                case "EXIT":
                    // Action for the exit button
                    int confirm = JOptionPane.showConfirmDialog(MainMenu.this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0); // Exit the application
                    }
                    break;
                default:
                    System.out.println("Unknown button clicked: " + buttonText);
                    break;
            }
        }
    }

    // Method to initialize and return a JFrame containing the MainMenu
    public static JFrame createMainMenuFrame() {
        JFrame frame = new JFrame("Java UI with Background Image");
        MainMenu panel = new MainMenu();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }
}