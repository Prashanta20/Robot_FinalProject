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
  private int[] robotPosition;
  JButton enter;
  private int counter = 0;
  Robot robot;
  LocalMap localMap;
  JLabel robotLabel;
  ArrayList<Tiles> options;

  ImageIcon startBlock = new ImageIcon("images/StartBlock.png");
  ImageIcon unknownBlock = new ImageIcon("images/UknownBlock.png");
  ImageIcon endBlock = new ImageIcon("images/EndBlock.png");
  ImageIcon robotImage = new ImageIcon("images/Robot.png");
  ImageIcon solidBlock = new ImageIcon("images/SolidBlock.png");
  ImageIcon notSolidBlock = new ImageIcon("images/NotSolidBlock.png");

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
    
    robotPosition = new int[2];
    

    generateMap();

    localMap = new LocalMap(map);
  }

  public void generateMap() {
    Random rand = new Random();

    for (int i = 0; i < size; i++) {
      for (int k = 0; k < size; k++) {

        if (i == 0 && k == 0) {
          // STARTING BLOCK
          map[0][0] = new Tiles(0, 0, true, false);
          mapVisual[0][0] = new JLabel();
          mapVisual[0][0].setBounds(10, 10, 30, 30);
          mapVisual[0][0].setIcon(startBlock);
          
        } else if (i == size - 1 && k == size - 1) {
          // ENDING BLOCK
          map[size - 1][size - 1] = new Tiles(0, 0, true, false);
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
          if (randomInt > 3) {
            map[i][k] = new Tiles(k, i, true, false);
          } else if (randomInt <= 3) {
            map[i][k] = new Tiles(k, i, false, false);
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

  public void updateMap(){
    try {
    for (int i = 0; i < size; i++){
      for (int k = 0; k < size; k++){
        if (map[i][k].getShow() == true){
          if (map[i][k].getType() == true){
            mapVisual[i][k].setIcon(solidBlock);
          } else {
            mapVisual[i][k].setIcon(notSolidBlock);
          }
        }
      }
    }
  } catch (IndexOutOfBoundsException e){
    System.out.println(e);
  }
  }


  public void actionPerformed(ActionEvent e) {
    int size = 0; // NOT SURE WHY THIS IS HERE
    String button = e.getActionCommand(); // MAY NOT NEED
    
    counter = counter +1;

    if (counter == 1){
      robotPosition[0] = 0;
      robotPosition[1] = 0;
      robotLabel.setBounds(10,10,30,30);

      options = localMap.updateLocalMap(robotPosition);
      map = localMap.getMap();
      updateMap();
     }
    else {
      // Robot movement
      robotPosition = robot.moveRobot(options);
      robotLabel.setBounds((robotPosition[0] * 30) + 10, (robotPosition[1] * 30) + 10,30,30);
      options = localMap.updateLocalMap(robotPosition);
      map = localMap.getMap();
      updateMap();
    }
    

  }

  public int[] getRobotPosition(){
    return robotPosition;
  }

  public Tiles[][] getMap() {
    return map;
  }

  public void setMap(Tiles[][] map){
    this.map = map;
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

}