package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.HashMap;
import java.util.Random;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;

public class MapPanel extends JPanel implements ActionListener {
  // Fields
  private int size;
  private Tiles[][] map;
  JLabel[][] mapVisual;
  JLabel error;
  private int[] robotPosition;
  JButton enter;
  JButton restart;
  private int counter = 0;
  Robot robot;
  LocalMap localMap;
  JLabel robotLabel;
  ArrayList<Tiles> options;
  HashMap<String, Tiles> hashOptions;

  String file = "src/Results.txt";
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
    restart = new JButton("Restart");

    // Find the middle to set the button to be in the center everytime
    int middle = (((size * 30) + 20) / 2) - 60;
    enter.setBounds(middle, (size * 30) + 20, 120, 50);
    restart.setBounds(10, 400,100,50);
    add(restart);
    add(enter);
    restart.addActionListener(this);
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
    
    error = new JLabel("There is no path");
    error.setBounds(10, 370, 200, 30);
    error.setForeground(Color.white);
    error.setFont(new Font("Wide Latin", Font.PLAIN, 12));
    add(error);
    error.setVisible(false);

    generateMap();

    localMap = new LocalMap(map);
  }

  public void generateMap() {
    Random rand = new Random();

    for (int i = 0; i < size; i++) {
      for (int k = 0; k < size; k++) {

        if (i == 0 && k == 0) {
          // STARTING BLOCK
          map[0][0] = new Tiles(0, 0, true, false, true);
          mapVisual[0][0] = new JLabel();
          mapVisual[0][0].setBounds(10, 10, 30, 30);
          mapVisual[0][0].setIcon(startBlock);
          
        } else if (i == size - 1 && k == size - 1) {
          // ENDING BLOCK
          map[size - 1][size - 1] = new Tiles(size - 1, size - 1, true, false, false);
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
            map[i][k] = new Tiles(k, i, true, false, false);
          } else if (randomInt <= 3) {
            map[i][k] = new Tiles(k, i, false, false, false);
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

    if (robotPosition[0] == size -1 && robotPosition[1] == size - 1){
      String data = "Run Completed in: " + counter-- + " Moves\n";
      data += readFiles(file);
      writeTextToFile(file, data);

      Main.setEndPanelVisible(data);
    }

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
    mapVisual[0][0].setIcon(startBlock);
    mapVisual[size-1][size-1].setIcon(endBlock);
    robotLabel.setBounds((robotPosition[0] * 30) + 10, (robotPosition[1] * 30) + 10,30,30);
  } catch (IndexOutOfBoundsException e){
    //System.out.println(e);
  }
  }


  public void actionPerformed(ActionEvent e) {
    String button = e.getActionCommand(); 
    
    counter = counter +1;

    if (button.equals("Enter")){
      if (counter == 1){

        robotPosition[0] =  0;
        robotPosition[1] = 0;

        hashOptions = localMap.updateLocalMap(robotPosition, map);
        map = localMap.getMap();
        map[robotPosition[0]][robotPosition[1]].setWalkedOn(true);
        updateMap();

    
      }
    else {
      // Robot movement
      robotPosition = robot.moveRobot(hashOptions, map, robotPosition);

      if (robotPosition[0] == -1 && robotPosition[1] == -1){
        error.setVisible(true);
      } else {

      hashOptions = localMap.updateLocalMap(robotPosition, map);
      map = localMap.getMap();
      map[robotPosition[0]][robotPosition[1]].setWalkedOn(true);
      updateMap();
      }
    }
  }
  else if (button.equals("Restart")){
      Main.main(null);
    } else {
      //NO ELSE
    }

  }

  public static String readFiles(String fileName) {
    try {
      // initiate buffer reader and read the file thats called fileName
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      // initiate stringbuilder for the contents of the Strings
      StringBuilder fileContents = new StringBuilder();
      // read line
      String line = br.readLine();
      // while the line is not null
      while (line != null) {
        // append the line
        fileContents.append(line);
        fileContents.append(System.lineSeparator());
        // read the line
        line = br.readLine();
      } // end of while loop
      br.close();
      return fileContents.toString();
    } // end of try
    catch (IOException e) {
      // catch input output exception
      return null;
    }
  }

  public static boolean writeTextToFile(String fileName, String text) {
    try {
      // initiate print writer
      PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
      // right each line
      pw.print(text);
      pw.close();
      return true;
    } catch (IOException e) {
      // catch input output exception
      return false;
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