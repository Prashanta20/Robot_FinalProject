package src;

public class LocalMap {
  // Fields
  private Tiles[][] localMap;
  private int[][] robotPosition;

  // Constructor
  public LocalMap() {

  }
  // Methods
  public void updateLocalMap(Robot robot, MapPanel map){
    localMap = map.getMap();
    System.out.println("Hello");
    robotPosition[0][0] = map.robotLabel.getBounds();
  }
}