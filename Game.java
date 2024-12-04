import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Game {
    private JFrame frame;

    public Game() {
        frame = createGameFrame();
        initializeGameComponents();
        frame.setVisible(true);
    }

    private void initializeGameComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Game panel
        JPanel gamePanel = new JPanel(new GridLayout(1, 2, 6, 6));
        gamePanel.setBackground(new Color(46, 83, 57));
        gamePanel.setForeground(Color.WHITE);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 100, 0));
        leftPanel.setForeground(Color.WHITE);
        gamePanel.add(leftPanel, gbc);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(100, 0, 0));
        rightPanel.setForeground(Color.WHITE);
        gamePanel.add(rightPanel, gbc);

        gbc.gridy = 0;
        gbc.weighty = 0.4;
        frame.add(gamePanel, gbc);

        // Button panel 1
        JPanel buttonPanel1 = new JPanel(new GridLayout(1, 3, 6, 6));
        buttonPanel1.setBackground(new Color(46, 83, 57));

        String[] labels1 = {"PLAYER", "TIE", "BANKER"};
        Color[][] gradientColors1 = {
            {new Color(102, 110, 113), new Color(19, 28, 43)}, // Blue
            {new Color(41, 93, 18), new Color(14, 38, 14)},    // Green
            {new Color(111, 37, 20), new Color(41, 12, 14)}    // Red
        };

        addButtonsToPanel(buttonPanel1, labels1, gradientColors1);

        JPanel buttonPanel1Container = new JPanel(new BorderLayout());
        buttonPanel1Container.setBackground(new Color(46, 83, 57));
        buttonPanel1Container.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        buttonPanel1Container.add(buttonPanel1, BorderLayout.CENTER);

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        frame.add(buttonPanel1Container, gbc);

        // Button panel 2
        JPanel buttonPanel2 = new JPanel(new GridLayout(2, 3, 6, 6));
        buttonPanel2.setBackground(new Color(46, 83, 57));

        String[] labels2 = {"P BONUS", "DOUBLE PERFECT PAIR", "B BONUS", "P PAIR", "EITHER PAIR", "B PAIR"};
        Color[][] gradientColors2 = {
            {new Color(102, 110, 113), new Color(19, 28, 43)}, // Blue
            {new Color(102, 62, 44), new Color(56, 41, 20)},   // Brown
            {new Color(111, 37, 20), new Color(41, 12, 14)},   // Red
            {new Color(102, 110, 113), new Color(19, 28, 43)}, // Blue
            {new Color(102, 62, 44), new Color(56, 41, 20)},   // Brown
            {new Color(111, 37, 20), new Color(41, 12, 14)}    // Red
        };

        addButtonsToPanel(buttonPanel2, labels2, gradientColors2);

        JPanel buttonPanel2Container = new JPanel(new BorderLayout());
        buttonPanel2Container.setBackground(new Color(46, 83, 57));
        buttonPanel2Container.setBorder(BorderFactory.createEmptyBorder(6, 10, 5, 10));
        buttonPanel2Container.add(buttonPanel2, BorderLayout.CENTER);

        gbc.gridy = 2;
        gbc.weighty = 0.2;
        frame.add (buttonPanel2Container, gbc);

        // Top bar
        gbc.gridy = 3;
        gbc.weighty = 0.03;
        frame.add(createTopBar(), gbc);

        // History bar
        JPanel historyBar = new JPanel();
        historyBar.setBackground(new Color(21, 21, 21));
        historyBar.setLayout(new BorderLayout(10, 0)); // Add horizontal spacing between components

        // Left-aligned balance label
        JLabel balance = new JLabel("   Balance: ");
        balance.setForeground(Color.WHITE);
        balance.setFont(new Font("Garamond", Font.BOLD, 14)); // Font styling
        historyBar.add(balance, BorderLayout.WEST);

        // Center-aligned history label
        JLabel historyLabel = new JLabel("Betting History", SwingConstants.CENTER);
        historyLabel.setForeground(Color.WHITE);
        historyLabel.setFont(new Font("Garamond", Font.BOLD, 14)); // Font styling
        historyBar.add(historyLabel, BorderLayout.CENTER);

        gbc.gridy = 4;
        gbc.weighty = 0.01;
        frame.add(historyBar, gbc);

        // History panel
        JPanel historyPanel = new JPanel();
        historyPanel.setBackground(Color.WHITE);
        gbc.gridy = 5;
        gbc.weighty = 0.27;
        frame.add(historyPanel, gbc);
    }

    private static JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(6, 57, 112));
        topBar.setLayout(new BorderLayout());
        topBar.setBorder(new LineBorder(Color.BLACK, 3));

        // Total Bet Label
        JLabel totalBetLabel = new JLabel("   Total Bet: 0 php");
        totalBetLabel.setFont(new Font("Garamond", Font.BOLD, 14));
        totalBetLabel.setForeground(Color.WHITE);
        topBar.add(totalBetLabel, BorderLayout.WEST);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.setBackground(new Color(6, 57, 112));

        // Define bet increment values
        int[] betIncrements = {50, 250, 1000, 5000, 25000};

        // Create and add buttons for bet increments
        for (int increment : betIncrements) {
            JButton button = new JButton("+" + increment);
            button.setFont(new Font("Garamond", Font.BOLD, 12));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(25, 25, 25));
            button.setFocusPainted(false);
            button.setBorderPainted(true);
            button.setContentAreaFilled(true);

            // Action listener to update total bet
            button.addActionListener(e -> {
                // Parse current total bet from the label and update it
                String text = totalBetLabel.getText();
                int currentBet = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                int newBet = currentBet + increment;

                // Update the label
                totalBetLabel.setText("   Total Bet: " + newBet + " php");
            });

            buttonPanel.add(button);
        }

        // Deposit Button
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Garamond", Font.BOLD, 16));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        depositButton.setBorderPainted(true);
        depositButton.setContentAreaFilled(false);
        depositButton.addActionListener(e -> System.out.println("Deposit button clicked"));

        buttonPanel.add(depositButton);

        // Add button panel to top bar
        topBar.add(buttonPanel, BorderLayout.EAST);

        return topBar;
    }

    private static void addButtonsToPanel(JPanel panel, String[] labels, Color[][] gradientColors) {
        for (int i = 0; i < labels.length; i++) {
            JButton button = new GradientButton(labels[i], gradientColors[i]);
            button.setFont(new Font("Garamond", Font.BOLD, 18));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(true);
            button.setVerticalAlignment(SwingConstants.BOTTOM);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setMargin(new Insets(5, 10, 7, 10));
            button.addActionListener(e -> System.out.println("Button clicked"));
            panel.add(button);
        }
    }

    static class GradientButton extends JButton {
        private final Color[] gradientColors;
        private final int cornerRadius; // Corner radius for rounded edges

        public GradientButton(String text, Color[] gradientColors) {
            super(text);
            this.gradientColors = gradientColors;
            this.cornerRadius = 15; // Adjust the radius as needed
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Enable anti-aliasing for smoother edges
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Create a rounded rectangle
            RoundRectangle2D roundedRect = new RoundRectangle2D.Float(
                0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius
            );

            // Draw the gradient background
            GradientPaint gradient = new GradientPaint(0, 0, gradientColors[0], 0, getHeight(), gradientColors[1]);
            g2d.setPaint(gradient);
            g2d.fill(roundedRect);

            // Paint the text
            super.paintComponent(g2d);

            g2d.dispose();
        }
    }

    public static JFrame createGameFrame() {
        JFrame frame = new JFrame("Game Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);        
        frame.setLayout(new GridBagLayout());
        frame.setResizable(true); // Allow resizing
        return frame;
    }
}