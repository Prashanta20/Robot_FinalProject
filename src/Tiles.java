package src;
public class Tiles {
  // Feilds
  private int X;
  private int Y;
  private boolean type;
  // Contructor
  public Tiles(int X, int Y, boolean type) {
    this.X = X;
    this.Y = Y;
    this.type = type;
  }
  // getters and setters
  public int getX() {
    return X;
  }

  public void setX(int x) {
    X = x;
  }

  public int getY() {
    return Y;
  }

  public void setY(int y) {
    Y = y;
  }

  public boolean getType() {
    return type;
  }

  public void setType(boolean type) {
    this.type = type;
  }

  // Overridden
  public String toString(){
    String info = "(" + X +", " + Y + ") " + "solid: " + type;
    return info;
  }

}