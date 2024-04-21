public enum Clr {
    White, Yellow, Green, Blue, Red, Orange
  }

class Block{
  //idk what things block should have
  //have 6? color slots for z+ z-, x+ x-, y+ y-
  //when you rotate x+, z,y faces will change.
  
  public int posX = -1;
  public int negX = -1;
  public int posY = -1;
  public int negY = -1;
  public int posZ = -1;
  public int negZ = -1;
  
  /*public boolean hasPosX = false;
  public boolean hasNegX = false;
  public boolean hasPosY = false;
  public boolean hasNegY = false;
  public boolean hasPosZ = false;
  public boolean hasNegZ = false;*/
  
  public int faces[] = {0, 0, 0};
  public int faceColor[] = {-1, -1, -1};
  public int len;
  public int offset;
  public int dim;
  public int[][] colors= {{255,255,255}, {81,227,0}, {232,32,2}, {100,117,226}, {243,136,21}, {245,238,1}}; // white, green, red, blue, orange, yellow
  //Clr col = Clr.White;
  
  Block(int len, int dim, int x, int y, int z){
    this.len = len;
    this.dim = dim;
    offset = (int)((0.5 * dim * len) - (0.5 * len));
    if(x == 0){
      faces[0] = -1;
      faceColor[0] = 2;
    }else if(x == dim - 1){
      faces[0] = 1;
      faceColor[0] = 4;
    }
    
    if(y == 0){
      faces[1] = -1;
      faceColor[1] = 5;
    }else if(y == dim - 1){
      faces[1] = 1;
      faceColor[1] = 0;
    }
    
    if(z == 0){
      faces[2] = -1;
      faceColor[2] = 3;
    }else if(z == dim - 1){
      faces[2] = 1;
      faceColor[2] = 1;
    }
    //
  }

  public void flipCornerFaces(int por){
    int a;
    int b; //a, b represent the two axises that are gonna be changed aka whatever por is not. if por == 0, which means x is plane of rotation, y,z will change for a corner.
    if(por == 0){
      a = 1;
      b = 2;
    } else if(por ==1){
      a = 0;
      b = 2;
    } else{
      a = 0;
      b = 1;
    }
    //we flip, change second
        int tempColor;
        //we switch the colors in faceColor, change second face int value * -1
        tempColor = faceColor[a];
        faceColor[a] = faceColor[b];
        faceColor[b] = tempColor;
        //colors switched, switch faces vals
        int tempFace = faces[a] * -1;
        faces[a] = faces[b];
        faces[b] = tempFace;
    
  }

  public void flipEdgeFaces(int por, boolean clockwise){
    
    if(por == 0){
      if(clockwise){
        if(faces[2] != 0){
          //when we are sitting at a z, the sign doesn't change. 
          System.out.println("before flip, faces[2] = " + faces[2] + " color is: " + faceColor[2]);
          System.out.println("before flip, faces[1] = " + faces[1] + " color is: " + faceColor[1]);
          faces[1]= faces[2];
          faces[2] = 0;
          faceColor[1] = faceColor[2];
          faceColor[2] = -1;
          System.out.println("after flip, faces[2] = " + faces[2] + " color is: " + faceColor[2]);
          System.out.println("after flip, faces[1] = " + faces[1] + " color is: " + faceColor[1]);
          System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/");
          //System.out.println("Flip from z -> y");
        }else{
          System.out.println("before flip2, faces[2] = " + faces[2] + " color is: " + faceColor[2]);
          System.out.println("before flip2, faces[1] = " + faces[1] + " color is: " + faceColor[1]);
          faces[2] = -1 * faces[1];
          faces[1] = 0;
          faceColor[2] = faceColor[1];
          faceColor[1] = -1;
          //System.out.println("Flip from y -> z");
          System.out.println("after flip, faces[2] = " + faces[2] + " color is: " + faceColor[2]);
          System.out.println("after flip, faces[1] = " + faces[1] + " color is: " + faceColor[1]);
          System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/");
        }
      }
    } else if(por ==1){
     
    } else{
     
    }
  }
  public void drawSelf(int x, int y, int z){
    fill(255);
    stroke(0);
    strokeWeight(8);
    pushMatrix();
    
    translate(x * len - offset, y * len - offset, z * len - offset);
    float r = len / 2;
    // z-fixed
    if(faces[2] != 0){
    int zed = faceColor[2];
    //System.out.println("Zed = " + zed);
    beginShape();
    fill(colors[zed][0], colors[zed][1], colors[zed][2]);
    vertex(-r, -r, -r);
    vertex(r, -r, -r);
    vertex(r, r, -r);
    vertex(-r, r, -r);
    endShape(CLOSE);

    beginShape();
    fill(colors[zed][0], colors[zed][1], colors[zed][2]);
    vertex(-r, -r, r);
    vertex(r, -r, r);
    vertex(r, r, r);
    vertex(-r, r, r);
    endShape(CLOSE);
    }
    // y-fixed
    
    if(faces[1] != 0){
    int yed = faceColor[1];
    beginShape();
    fill(colors[yed][0], colors[yed][1], colors[yed][2]);
    vertex(-r, r, -r);
    vertex(r, r, -r);
    vertex(r, r, r);
    vertex(-r, r, r);
    endShape(CLOSE);
    
    beginShape();
    fill(colors[yed][0], colors[yed][1], colors[yed][2]);
    vertex(-r, -r, -r);
    vertex(r, -r, -r);
    vertex(r, -r, r);
    vertex(-r, -r, r);
    endShape(CLOSE);
    }
    
    
    // x-fixed
    if(faces[0] != 0){
    int xed = faceColor[0];
    beginShape();
    fill(colors[xed][0], colors[xed][1], colors[xed][2]);
    vertex(-r, -r, -r);
    vertex(-r, r, -r);
    vertex(-r, r, r);
    vertex(-r, -r, r);
    endShape(CLOSE);
    
    beginShape();
    fill(colors[xed][0], colors[xed][1], colors[xed][2]);
    vertex(r, -r, -r);
    vertex(r, r, -r);
    vertex(r, r, r);
    vertex(r, -r, r);
    endShape(CLOSE);
    }
    //System.out.println("" + x + "," + y + "," + z + " is at: " + (x * len - offset) + "," + (y * len - offset) + "," + (z * len - offset));
    //box(len);
    popMatrix();
  }

}



/*

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


*/
