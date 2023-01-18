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

public class MapPanel extends JPanel implements ActionListener {
  // Fields
  private int size;
  private Tiles[][] map;
  JLabel[][] mapVisual;
  JButton enter;
  int counter = 0;
  Robot robot;
  JLabel robotLabel;

  ImageIcon startBlock = new ImageIcon("images/StartBlock.png");
  ImageIcon unknownBlock = new ImageIcon("images/UknownBlock.png");
  ImageIcon endBlock = new ImageIcon("images/EndBlock.png");
  ImageIcon robotImage = new ImageIcon("images/Robot.png");

  public MapPanel(int size) {
    this.setBackground(Color.blue);
    setLayout(null);

    

    mapVisual = new JLabel[size][size];
    enter = new JButton("Enter");

    // Find the middle to set the button to be in the center everytime
    int middle = (((size * 30) + 20) / 2) - 60;
    enter.setBounds(middle, (size * 30) + 20, 120, 50);
    add(enter);
    enter.addActionListener(this);

    // set this size to size passed through
    this.size = size;
    // create the map that is a two dimentinal array of tiles
    map = new Tiles[size][size];
    // call generate map method

    robot = new Robot("Son");
    robotLabel = new JLabel();
    robotLabel.setIcon(robotImage);
    add(robotLabel);
    
    

    generateMap();
  }

  public void generateMap() {
    Random rand = new Random();

    for (int i = 0; i < size; i++) {
      for (int k = 0; k < size; k++) {

        if (i == 0 && k == 0) {
          // STARTING BLOCK
          map[0][0] = new Tiles(0, 0, true);
          mapVisual[0][0] = new JLabel();
          mapVisual[0][0].setBounds(10, 10, 30, 30);
          mapVisual[0][0].setIcon(startBlock);
          
        } else if (i == size - 1 && k == size - 1) {
          // ENDING BLOCK
          map[size - 1][size - 1] = new Tiles(0, 0, true);
          mapVisual[size - 1][size - 1] = new JLabel();
          mapVisual[size - 1][size - 1].setBounds(((size - 1) * 30) + 10, ((size - 1) * 30) + 10, 30, 30);
          mapVisual[size - 1][size - 1].setIcon(endBlock);
          
        } else {
          // ANY OTHER BLOCK
          mapVisual[i][k] = new JLabel();
          mapVisual[i][k].setBounds((i * 30) + 10, (k * 30) + 10, 30, 30);
          mapVisual[i][k].setIcon(unknownBlock);
          int randomInt = rand.nextInt(10) + 1;

          // SET IF BLOCK IS SOLID OR NOT
          if (randomInt > 4) {
            map[i][k] = new Tiles(k, i, true);
          } else if (randomInt <= 4) {
            map[i][k] = new Tiles(k, i, false);
          } else {
            // NO ELSE
          }
        }
        // ADD THE JLABEL TO THE PANEL
        add(mapVisual[i][k]);
      }
    }

    // y , x
   

  }

  public void actionPerformed(ActionEvent e) {
    int size = 0;
    String button = e.getActionCommand();
    
    counter = counter +1;

    if (counter == 1){
      robotLabel.setBounds(10,10,30,30);
     }
    else {
      robotLabel.setBounds(40,10,30,30);
    }

    

  }

  public Tiles[][] getMap() {
    return map;
  }

  public String toString() {
    String info = "";
    for (int i = 0; i < size; i++) {
      for (int k = 0; k < size; k++) {
        info += map[i][k].getType() + ", ";

      }
    }
    return info;

  }

  public void stuff() {
    map[0][0] = new Tiles(0, 0, true);
    System.out.println(map[0][0].getType());
  }
}