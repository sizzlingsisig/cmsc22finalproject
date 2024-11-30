
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel {

    private Image backgroundImage;

    public MainMenu() {
        // Load the background image
        backgroundImage = new ImageIcon("C:/Users/ACER/Downloads/git/cmsc22finalproject/mainMenuScreen.png").getImage();

        // Check if the image is loaded correctly
        if (backgroundImage.getWidth(null) == -1 || backgroundImage.getHeight(null) == -1) {
            System.err.println("Image not found or failed to load!");
        }

        // Set layout for the main panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill the available space

        // Create panels for each section
        JPanel topLeftPanel = createTransparentPanel(); // Transparent panel
        JPanel topRightPanel = createTransparentPanel(); // Transparent panel
        JPanel bottomLeftPanel = createBottomLeftPanel(); // Create the bottom left panel with clickable JButtons
        JPanel bottomRightPanel = createTransparentPanel(); // Transparent panel

        // Add panels to the main panel with GridBagLayout
        gbc.weightx = 0.5; // Take half the width
        gbc.weighty = 0.5; // Take half the height
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        add(topLeftPanel, gbc);

        gbc.gridx = 1; // Column 1
        add(topRightPanel, gbc);

        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        add(bottomLeftPanel, gbc);

        gbc.gridx = 1; // Column 1
        add(bottomRightPanel, gbc);
    }

    private JPanel createTransparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make the panel transparent
        return panel;
    }

    private JPanel createBottomLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for uniform JButton sizes
        panel.setOpaque(false); // Make the panel transparent

        // Create clickable JButtons
        JButton monkeyButton = createClickableButton("Monkey");
        JButton helloButton = createClickableButton("Hello");

        // Create GridBagConstraints for JButtons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill the width
        gbc.weightx = 1.0; // Allow JButtons to expand to fill the space
        gbc.weighty = 1.0; // Allow JButtons to expand to fill the height

        // Add JButtons to the panel
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        panel.add(monkeyButton, gbc);

        gbc.gridy = 1; // Second row
        panel.add(helloButton, gbc);

        return panel;
    }

    private JButton createClickableButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 30)); // Set a smaller preferred size for the buttons
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for visibility

        // Add action listener to handle click events
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the click event
                JOptionPane.showMessageDialog(button, text + " clicked!");
            }
        });

        return button;
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
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
