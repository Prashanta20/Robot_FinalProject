package src;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

public class Main {

  public static int size;
  public static JFrame GUI = new JFrame();
  public static Container pane = GUI.getContentPane();
  
  public static void main(String[] args) {
    
    GUI.setTitle("Robot Maze Runner");
		GUI.setSize(340,420);
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIcon(GUI);

    setIntroPanelVisible();
    

    
  }

  public static void setIntroPanelVisible(){
    JPanel introPanel = new IntroPanel();
    pane.removeAll();
		pane.add(introPanel);
    GUI.setVisible(true);
  }

  public static void setMapPanelVisible(){
    JPanel mapPanel = new MapPanel(size);
    pane.removeAll();
    pane.add(mapPanel);
    GUI.setVisible(true);

    System.out.println(size);
  }

  private static void setIcon(JFrame GUI) {
        Image icon = null;

        try {
            icon = ImageIO.read(new File("images/luffyReplit2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUI.setIconImage(icon);
    }
}