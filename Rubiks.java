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
//when we are calling u, r, l etc, some of them will have to "flip" the call, our makeTurn's clockwise is correct for d, not for u.
void draw() {
  background(220); 
  rub.drawPieces();
  if(keyPressed && key == 106 && canFlip){
    rub.makeTurn(0, 0, true);
    canFlip = false;
  }else if(keyPressed && key == 107){
    canFlip = true;
  }else if(keyPressed && key == 108 && canFlip){
    rub.makeTurn(1, 0, true);
    canFlip = false;
  }else if(keyPressed && key == 109 && canFlip){
    
    canFlip = false;
  }else if(keyPressed && key == 110 && canFlip){
    
  }
}
