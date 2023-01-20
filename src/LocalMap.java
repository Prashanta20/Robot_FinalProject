package src;
import java.util.ArrayList;
public class LocalMap {
  // Fields
  private Tiles[][] fullMap;
  private Tiles[][] localMap;

  // Constructor
  public LocalMap(Tiles[][] map) {
    fullMap = map;
    
  }
  // Methods
  public ArrayList<Tiles> updateLocalMap(int[] robotPosition){

    // return a list of the possible tiles that robot can move
    ArrayList<Tiles> options = new ArrayList<>();

    int [] robotPositionPlaceHolder = robotPosition.clone();
    
    try {
      robotPositionPlaceHolder = robotPosition.clone();
      robotPositionPlaceHolder[0] = robotPositionPlaceHolder[0] -1;
      Tiles up = fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]];
      if (up.getType() == true){
      options.add(up);
      }
      fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]].setShow(true);

    } catch (IndexOutOfBoundsException e){
        System.out.println(e);
    }

    try {
      robotPositionPlaceHolder = robotPosition.clone();
      robotPositionPlaceHolder[1] = robotPositionPlaceHolder[1] +1;
      Tiles right = fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]];
      if (right.getType() == true){
        options.add(right);
        }
      fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]].setShow(true);

    } catch (IndexOutOfBoundsException e){
        System.out.println(e);
    }
    
    try {
      robotPositionPlaceHolder = robotPosition.clone();
      robotPositionPlaceHolder[0] = robotPositionPlaceHolder[0] +1;
      Tiles down = fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]];
      if (down.getType() == true){
        options.add(down);
        }
      fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]].setShow(true);

    } catch (IndexOutOfBoundsException e){
        System.out.println(e);
    }


    try {
      robotPositionPlaceHolder = robotPosition.clone();
      robotPositionPlaceHolder[1] = robotPositionPlaceHolder[1] -1;
      Tiles left = fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]];
      if (left.getType() == true){
        options.add(left);
        }
      fullMap[robotPositionPlaceHolder[0]][robotPositionPlaceHolder[1]].setShow(true);

    } catch (IndexOutOfBoundsException e){
        System.out.println(e);
    }

    return options;

  }

  public Tiles[][] getMap(){
    return fullMap;
  }

  
}