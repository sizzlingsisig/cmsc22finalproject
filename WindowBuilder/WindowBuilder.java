import javax.swing.*;
import java.awt.Color;
import helper_classes.*;

public class WindowBuilder {
  public static void main(String[] args) {

     JFrame frame = new JFrame("My Awesome Window");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(750, 404);
     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#f4c064"));

     JTextArea element1 = new JTextArea("");
     element1.setBounds(17, 11, 294, 330);
     element1.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
     element1.setBackground(Color.decode("#ffe7bf"));
     element1.setForeground(Color.decode("#73664e"));
     element1.setBorder(new RoundedBorder(2, Color.decode("#000"), 1));
     OnFocusEventHelper.setOnFocusText(element1, "Your long Input!", Color.decode("#000"),   Color.decode("#73664e"));
     panel.add(element1);

     JButton element2 = new JButton("Save Profile");
     element2.setBounds(385, 309, 106, 29);
     element2.setBackground(Color.decode("#bca8e4"));
     element2.setForeground(Color.decode("#000"));
     element2.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
     element2.setBorder(new RoundedBorder(4, Color.decode("#3d364a"), 1));
     element2.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(element2, Color.decode("#7c6f97"), Color.decode("#bca8e4"));
     panel.add(element2);

     JButton element3 = new JButton("Load Profile");
     element3.setBounds(569, 307, 106, 29);
     element3.setBackground(Color.decode("#bca8e4"));
     element3.setForeground(Color.decode("#000"));
     element3.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 14));
     element3.setBorder(new RoundedBorder(4, Color.decode("#3d364a"), 1));
     element3.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(element3, Color.decode("#7c6f97"), Color.decode("#bca8e4"));
     panel.add(element3);

     frame.add(panel);
     frame.setVisible(true);

  }
}