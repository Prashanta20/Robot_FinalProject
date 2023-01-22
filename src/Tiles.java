package src;
public class Tiles {
  // Feilds
  private int X;
  private int Y;
  private boolean type;
  private boolean show;
  private boolean walkedOn;
  // Contructor
  public Tiles(int X, int Y, boolean type, boolean show, boolean walkedOn) {
    this.X = X;
    this.Y = Y;
    this.type = type;
    this.show = show;
    this.walkedOn = walkedOn;

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

  public void setShow(boolean show){
    this.show = show;
  }

  public boolean getShow(){
    return show;
  }

  public boolean getWalkedOn(){
    return walkedOn;
  }

  public void setWalkedOn(boolean walkedOn){
    this.walkedOn = walkedOn;
  }

  // Overridden
  public String toString(){
    String info = "(" + X +", " + Y + ") " + "solid: " + type;
    return info;
  }

}