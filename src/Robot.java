package src;


import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.Random;
import java.util.HashMap;

public class Robot {
  //Fields
  private String name;
  private int[] newPosition;
  private ArrayList<Tiles> previousMoves;
  private LocalMap localMap;

  
  JLabel robot;
  
  //Contructors
  public Robot(String name){
    this.name = name;
    newPosition = new int[2];
    previousMoves = new ArrayList<Tiles>();
  }
  //Methods TO MOVE
  public int[] moveRobot(HashMap<String, Tiles> hashOptions, Tiles[][] map, int[] currentPosition){
    Random rand = new Random();
    HashMap<String, Tiles> nonWalkedTiles = new HashMap<>(); 

    System.out.println("NUMBER OF OPTIONS: " + hashOptions.size());

    if (hashOptions.size() == 1){
       hashOptions.values().toArray();
       String firstKey = hashOptions.keySet().stream().findFirst().get();
       Tiles check = hashOptions.get(firstKey);
      if (check.getX() == 0 && check.getY() == 0){
        newPosition[0] = 0;
        newPosition[1] = 0;
        return newPosition;
      }
    }

    if (hashOptions != null && hashOptions.size() > 0) {
    for (String key: hashOptions.keySet()){
      if (hashOptions.get(key).getWalkedOn() == false){
      nonWalkedTiles.put(key, hashOptions.get(key));
      }
    }

    if (nonWalkedTiles != null && nonWalkedTiles.size() > 0){
      ArrayList<String> keysAsArrays = new ArrayList<String>(nonWalkedTiles.keySet());
      int randomNum = rand.nextInt(nonWalkedTiles.size());
      newPosition[0] = nonWalkedTiles.get(keysAsArrays.get(randomNum)).getY();
      newPosition[1] = nonWalkedTiles.get(keysAsArrays.get(randomNum)).getX();

      previousMoves.add(0, nonWalkedTiles.get(keysAsArrays.get(randomNum)));

      return newPosition;
    }

    else {
      // IF THERE ARE TILES BUT THEY HAVE ALL BEEN WALKED ON
      boolean end = true;

      for (int i = 0; i < map.length; i++){
        for (int k = 0; k < map.length; k++){
          if(map[i][k].getWalkedOn() == false && map[i][k].getType() == true && map[i][k].getShow() == true){
            end = false;
          }
        }
      }

      if (end){
        newPosition[0] = -1;
        newPosition[1] = -1;
  
        return newPosition;
      } else { // if there are still paths to try (Make it go backwards and find them)

        if (currentPosition[0] == 0 && currentPosition[1] == 1){
          if (map[1][0].getWalkedOn() == false){
          newPosition[0] = 0;
          newPosition[1] = 0;
          return newPosition;
          }
        } else if (currentPosition[0] == 1 && currentPosition[1] == 0){
          if (map[0][1].getWalkedOn() == false){
          newPosition[0] = 0;
          newPosition[1] = 0;
          
          return newPosition;
          }
        }
        try {
        localMap = new LocalMap(map);
        HashMap<String, Tiles> mapPlaceholder = new HashMap<>();

        newPosition[0] = previousMoves.get(0).getY();
        newPosition[1] = previousMoves.get(0).getX();

        mapPlaceholder = localMap.updateLocalMap(newPosition, map);
        boolean remove = true;
        for (String key: mapPlaceholder.keySet()){
          if (mapPlaceholder.get(key).getWalkedOn() == false && mapPlaceholder.get(key).getType() == true){
            remove = false;
          }
        }

        if (remove){
          previousMoves.remove(0);

        }
      } catch (IndexOutOfBoundsException e){
        System.out.println(e);
      }
        
      }
    }
  } 
    else {
      // return -1, -1 if the there is no place to move
      // MAKE SURE TO WRITE IN MAPPANEL TO TAKE IN THIS
      newPosition[0] = -1;
      newPosition[1] = -1;

      return newPosition;
    }
// check if all of them are walked on or not
// if it hasnt been walked on, go on that tile
// record all the tiles that you decide to move to and add it to arraylist
// check if u have a place to walk to 
// if there is not more places to go to and all the solid tiles are walked on, no more moves (display label)

   
    return newPosition;  
  } 
  
}