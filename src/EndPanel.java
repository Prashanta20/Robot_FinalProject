package src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Random;

public class EndPanel extends JPanel{

    JLabel endImage;
    JLabel endText;
    JButton playAgain;

    public EndPanel() {

    this.setBackground(Color.yellow);
    setLayout(null);

    playAgain = new JButton("Play Again");
    endImage = new JLabel();
    endText = new JLabel("Congratulations On Completing The Game!!!");
    ImageIcon trophy = new ImageIcon("images/luffygame.png");

    endImage.setIcon(trophy);

    playAgain.setBounds(10,360, 100, 50);
    endText.setBounds(10,10,400,40);
    endImage.setBounds(10,50,300,300);

    add(endImage);
    add(endText);
    }
}
