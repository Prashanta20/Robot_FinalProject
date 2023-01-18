package src;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class IntroPanel extends JPanel implements ActionListener {
  // Fields
  JButton small;
  JButton medium;
  JButton large;
  JTextField intro;

  public IntroPanel() {

    this.setBackground(Color.blue);
    setLayout(null);

    small = new JButton("5 X 5");
    medium = new JButton("8 X 8");
    large = new JButton("10 X 10");
    intro = new JTextField("Select What Size Map to PLay");

    intro.setBounds(25, 5, 250, 50);
    small.setBounds(100, 70, 100, 50);
    medium.setBounds(100, 140, 100, 50);
    large.setBounds(100, 210, 100, 50);

    add(intro);
    add(small);
    add(medium);
    add(large);

    small.addActionListener(this);
    medium.addActionListener(this);
    large.addActionListener(this);
  } // END OF CONSTRUCTOR

  public void actionPerformed(ActionEvent e) {
    int size = 0;
    String button = e.getActionCommand();

    if (button.equals("5 X 5")) {
      size = 5;
    } else if (button.equals("8 X 8")) {
      size = 8;
    } else if (button.equals("10 X 10")) {
      size = 10;
    } else {
      // NO ELSE
    }

    Main.size = size;
    Main.setMapPanelVisible();

  }// END OF ACTIONPERFORMED METHOD
} // END OF CLASS