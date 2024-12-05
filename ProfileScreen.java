import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ProfileScreen {
    // Define the main color
    private static final Color MAIN_COLOR = new Color(24, 66, 40); // #184228

    public void createProfileScreen() {
        // Create the profile screen
        JFrame profileFrame = new JFrame("Profile");
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Set the frame size to the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        profileFrame.setSize(screenSize.width, screenSize.height); // Set to screen size

        // Create the main panel (rectangle) for the profile
        JPanel mainPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        mainPanel.setLayout(gridBagLayout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the main panel
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Create a panel for the name at the top
        JPanel namePanel = new JPanel();
        namePanel.setBackground(MAIN_COLOR); // Use the main color
        JLabel nameLabel = new JLabel("Profile Name", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        nameLabel.setForeground(Color.WHITE); // White text
        namePanel.add(nameLabel);
        
        // Add name panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1; // Give it some height
        mainPanel.add(namePanel, gbc);

        // Create the middle panel for the ID card layout using GridBagLayout
        JPanel middlePanel = new JPanel();
        GridBagLayout middleLayout = new GridBagLayout();
        middlePanel.setLayout(middleLayout);
        GridBagConstraints middleGbc = new GridBagConstraints();
        middleGbc.fill = GridBagConstraints.BOTH;
        middleGbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Profile picture panel (0,0)
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBorder(new LineBorder(MAIN_COLOR, 2)); // Add a border to the profile panel
        profilePanel.setLayout(new BorderLayout()); // Use BorderLayout to position the label
        profilePanel.add(new JLabel("Profile Picture Here", SwingConstants.CENTER), BorderLayout.CENTER); // Placeholder for profile picture
        middleGbc.gridx = 0;
        middleGbc.gridy = 0;
        middleGbc.weightx = 0.5; // Give it some weight
        middleGbc.weighty = 0.5; // Give it some height
        middlePanel.add(profilePanel, middleGbc); // Add profile panel to the middle panel

        // Add a label for the profile picture panel
        JLabel profileLabel = new JLabel("Profile Picture", SwingConstants.CENTER);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 16));
        profilePanel.add(profileLabel, BorderLayout.NORTH);

        // Stats panel (0,1) - occupies all three remaining cells
        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(new Color(173, 216, 230)); // Light cyan
        statsPanel.setBorder(new LineBorder(MAIN_COLOR, 2)); // Add a border to the stats panel
        statsPanel.setLayout(new GridLayout(4, 1)); // Vertical layout for stats
        statsPanel.add(new JLabel("Stats:", SwingConstants.CENTER));
        statsPanel.add(new JLabel("Wins: 10", SwingConstants.CENTER));
        statsPanel.add(new JLabel("Losses: 5", SwingConstants.CENTER));
        middleGbc.gridx = 0;
        middleGbc.gridy = 1;
        middleGbc.weightx = 0.5; // Give it some weight
        middleGbc.weighty = 1.0; // Give it more height
        middlePanel.add(statsPanel, middleGbc); // Add stats panel to the middle panel

        // Add a label for the stats panel
        JLabel statsLabel = new JLabel("Player Stats", SwingConstants.CENTER);
        statsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsPanel.add(statsLabel, 0); // Add at the top of the stats panel

        // Add the middle panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8; // Give it more height
        mainPanel.add(middlePanel, gbc);

        // Create a panel for the button card layout
        JPanel buttonCardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        buttonCardPanel.setLayout(cardLayout);

        // Create the first card (button panel)
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout());
        buttonPanel1.setBackground(new Color(240, 240, 240)); // Light gray background
        buttonPanel1.add(createStyledButton("Edit Profile"));
        buttonPanel1.add(createStyledButton("Settings"));
        buttonPanel1.add(createStyledButton("Logout"));

        // Create the second card (additional options)
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout());
        buttonPanel2.setBackground(new Color(240, 240, 240)); // Light gray background
        buttonPanel2.add(createStyledButton("View Activity"));
        buttonPanel2.add(createStyledButton("Change Password"));
        buttonPanel2.add(createStyledButton("Help"));

        // Add a label for the second button panel
        JLabel buttonPanel2Label = new JLabel("Additional Options", SwingConstants.CENTER);
        buttonPanel2Label.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel2.add(buttonPanel2Label);

        // Add cards to the card panel
        buttonCardPanel.add(buttonPanel1, "Card1");
        buttonCardPanel.add(buttonPanel2, "Card2");

        // Create a panel for the bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Add the button card panel to the left side of the bottom panel
        bottomPanel.add(buttonCardPanel, BorderLayout.CENTER);

        // Create a panel for the button to switch cards
        JPanel switchButtonPanel = new JPanel();
        switchButtonPanel.setLayout(new FlowLayout());
        
        // Add a button to switch to the second card
        JButton switchToAdditionalOptionsButton = new JButton("Switch to Additional Options");
        switchToAdditionalOptionsButton.addActionListener(e -> {
            cardLayout.show(buttonCardPanel, "Card2"); // Switch to the second card
        });
        switchButtonPanel.add(switchToAdditionalOptionsButton);

        // Add a button to switch back to the first card
        JButton switchBackButton = new JButton("Back to Main Options");
        switchBackButton.addActionListener(e -> {
            cardLayout.show(buttonCardPanel, "Card1"); // Switch back to the first card
        });
        switchButtonPanel.add(switchBackButton);

        // Add the switch button panel to the right side of the bottom panel
        bottomPanel.add(switchButtonPanel, BorderLayout.EAST);

        // Add the bottom panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1; // Give it some height
        mainPanel.add(bottomPanel, gbc);

        // Add the main panel to the frame
        profileFrame.add(mainPanel);
        profileFrame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(MAIN_COLOR); // Use the main color
        button.setForeground(Color.WHITE); // White text
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        button.setFocusPainted(false); // Remove focus border
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProfileScreen profileScreen = new ProfileScreen();
            profileScreen.createProfileScreen();
        });
    }
}