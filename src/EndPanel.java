package src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EndPanel extends JPanel implements ActionListener{

    JLabel endImage;
    JLabel endText;
    JButton playAgain;
    JTextArea txtArea;
    JScrollPane jScrollPane;

    public EndPanel(String data) {

    this.setBackground(Color.yellow);
    setLayout(null);

    playAgain = new JButton("Play Again");
    endImage = new JLabel();
    endText = new JLabel("Congratulations On Completing The Game!!!");
    txtArea = new JTextArea();
    ImageIcon trophy = new ImageIcon("images/luffygame.png");

    endImage.setIcon(trophy);

    playAgain.setBounds(10,360, 100, 50);
    endText.setBounds(10,10,400,40);
    endImage.setBounds(10,50,300,300);

    playAgain.addActionListener(this);

    txtArea.setText(data); 

    jScrollPane = new JScrollPane(txtArea);
    jScrollPane.setBounds(120,360,200,100);


    add(jScrollPane);
    add(endImage);
    add(endText);
    add(playAgain);
    }

    public void actionPerformed(ActionEvent e) {
        Main.main(null);
    }
}
