
// ButtonFactory: Encapsulates button creation logic
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionListener;

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
        setForeground(Color.WHITE);
        setFont(new Font("Garamond", Font.BOLD, 18));

        // Add a default ActionListener to demonstrate functionality
        addActionListener(e -> System.out.println("Button clicked: " + text));
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

    // Allow external classes to add custom ActionListeners
    public void addCustomActionListener(ActionListener listener) {
        addActionListener(listener);
    }
}