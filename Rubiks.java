//headstrong lets go into this
import peasy.*;
PeasyCam cam;
import java.lang.Math;
Cube rub;
boolean canFlip = true;

void setup() {
  size(800, 600, P3D);
  cam = new PeasyCam(this, 400);
  rub = new Cube(3, 40);
}

void draw() {
  background(220); 
  rub.drawPieces();
  if(keyPressed && key == 106 && canFlip){
    System.out.println("Flipping corners!");
    rub.turnCorners(0, 0, true);
    canFlip = false;
  }else if(keyPressed && key == 107){
    canFlip = true;
  }else if(keyPressed && key == 108 && canFlip){
    rub.turnCorners(0,0,false);
    canFlip = false;
  }else if(keyPressed && key == 109 && canFlip){
    rub.makeTurn(0, 0, true);
    canFlip = false;
  }else if(keyPressed && key == 110 && canFlip){
    rub.turnEdges(0, 0, true);
    canFlip = false;
  }
}
