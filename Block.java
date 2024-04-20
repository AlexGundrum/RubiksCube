public enum Clr {
    White, Yellow, Green, Blue, Red, Orange
  }

class Block{
  //idk what things block should have
  public int len;
  public int offset;
  public int dim;
  public int[][] colors= {{255,255,255}, {0,187,0}, {255,100,0}, {0,0,185}, {255,255,0}, {254,213,0}}; // white, green, red, blue, orange, yellow
  Clr col = Clr.White;
  Block(int len, int dim){
    this.len = len;
    this.dim = dim;
    offset = (int)((0.5 * dim * len) - (0.5 * len));
  }

  public void drawSelf(int x, int y, int z){
    pushMatrix();
    if(x == 2 && y ==2 && z == 2){
      col = Clr.Red;
    }
    switch (col){
      case White:
        fill(colors[0][0], colors[0][1], colors[0][2]);
        break;
      case Red:
      fill(colors[2][0], colors[2][1], colors[2][2]);
        break;
      default:
        len = len;
    }
    translate(x * len - offset, y * len - offset, z * len - offset);
    //System.out.println("" + x + "," + y + "," + z + " is at: " + (x * len - offset) + "," + (y * len - offset) + "," + (z * len - offset));
    box(len);
    popMatrix();
  }

}
