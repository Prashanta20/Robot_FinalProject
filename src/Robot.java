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

public class Robot {
  //Fields
  private String name;
  private int[] newPosition;

  
  JLabel robot;
  
  //Contructors
  public Robot(String name){
    this.name = name;
    newPosition = new int[2];
     
  }
  //Methods TO MOVE
  public int[] moveRobot(ArrayList<Tiles> options){
   
    if (options.size() > 0){
      Random rand = new Random();

      int random = rand.nextInt(options.size());
      newPosition[0] = options.get(random).getY();
      newPosition[1] = options.get(random).getX();
    }

   
    return newPosition;
  }
  
}