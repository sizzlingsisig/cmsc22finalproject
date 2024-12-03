import java.awt.*;
import javax.swing.*;

// Interface for components that can be added to the MainMenu
interface MenuComponent {
    JPanel createPanel();
}

// Class for the main menu
public class MainMenu extends JPanel {

    private Image backgroundImage;

    public MainMenu() {
        // Load the background image
        backgroundImage = new ImageIcon("C:/Users/cj/Downloads/git/cmsc22finalproject/mainMenuScreen.png").getImage();
        // Check if the image is loaded correctly
        if (backgroundImage.getWidth(null) == -1 || backgroundImage.getHeight(null) == -1) {
            System.err.println("Image not found or failed to load!");
        }

        // Set layout for the main panel
        setLayout(new GridBagLayout());

        // Create and add panels
        addComponent(new TransparentPanel(), 0, 0, 0.5, 0.5);
        addComponent(new TransparentPanel(), 1, 0, 0.5, 0.5);
        addComponent(new BottomLeftPanel(), 0, 1, 0.5, 0.5);
        addComponent(new TransparentPanel(), 1, 1, 0.5, 0.5);
    }

    private void addComponent(MenuComponent component, int gridx, int gridy, double weightx, double weighty) {
        JPanel panel = component.createPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = GridBagConstraints.BOTH;
        add(panel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java UI with Background Image");
        MainMenu panel = new MainMenu();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width, screenSize.height);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

// Class for transparent panels
class TransparentPanel implements MenuComponent {
    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }
}

// Class for the bottom left panel with labels
class BottomLeftPanel implements MenuComponent {
    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        // Add labels to the panel
        addLabelToPanel(panel, "Let's Go Gambling", 0);
        addLabelToPanel(panel, "Save Profile", 1);
        addLabelToPanel(panel, "Load Profile", 2);
        addLabelToPanel(panel, "Exit", 3);

        return panel;
    }

    private void addLabelToPanel(JPanel panel, String text, int gridy) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Garamond", Font.BOLD, 30)); 
        label.setForeground(new Color(255, 238, 220));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand

        // Add mouse listener for click event
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelClicked(label.getText()); 
            }
        });

        addComponentToPanel(panel, label, 0, gridy, 1.0, 0.5);
    }

    private void addComponentToPanel(JPanel panel, Component component, int gridx, int gridy, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(component, gbc);
    }

    // Method to handle label click events
    private void labelClicked(String labelText) {
        // Perform action based on which label was clicked
        switch (labelText) {
            case "Let's Go Gambling":
                // Get the parent frame using the MouseEvent
          

                // Create and show the Game screen
                JFrame gameFrame = new JFrame("Game Screen");
                Game gamePanel = new Game();
                gameFrame.setContentPane(gamePanel);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                gameFrame.setSize(screenSize.width, screenSize.height); // Set size for the game window
                gameFrame.setVisible(true);
                break;
            case "Save Profile":
                JOptionPane.showMessageDialog(null, "Profile saved!");
                break;
            case "Load Profile":
                JOptionPane.showMessageDialog(null, "Profile loaded!");
                break;
            case "Exit":
                // First confirmation dialog
                int confirmed = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirmed == JOptionPane.YES_OPTION) {
                    // Second confirmation dialog
                    int reallySure = JOptionPane.showConfirmDialog(null,
                        "Are you really sure you want to exit?", "Final Confirmation",
                        JOptionPane.YES_NO_OPTION);
                    
                    if (reallySure == JOptionPane.YES_OPTION) {
                        // Third confirmation dialog
                        int reallyReallySure = JOptionPane.showConfirmDialog(null,
                            "Are you really, really sure you want to exit?", "Final Confirmation",
                            JOptionPane.YES_NO_OPTION);
                        
                        if (reallyReallySure == JOptionPane.YES_OPTION) {
                            System.exit(0); // Exit the application
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}