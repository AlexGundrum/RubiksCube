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
  if(keyPressed && key == 98 && canFlip){
    //b
    rub.makeTurn(2, 0, true); // BLUE
    canFlip = false;
  }else if(keyPressed && key == 'g' && canFlip){
    //f
    rub.makeTurn(2, 2, true);  //GREEN
    canFlip = false;
  }else if(keyPressed && key == 'o' && canFlip){
    //l
    rub.makeTurn(0, 2, true); //ORANGE
    canFlip = false;
  }else if(keyPressed && key == 114 && canFlip){
    //r
    rub.makeTurn(0, 0, true); //RED
    canFlip = false;
  }else if(keyPressed && key == 'w' && canFlip){
    //u
    rub.makeTurn(1, 2, true); //WHITE
    canFlip = false;
  }else if(keyPressed && key == 'y' && canFlip){
    //d
    rub.makeTurn(1, 0, true); //YELLOW
    canFlip = false;
  }else if(keyPressed && key == 'z'){
    rub.scramble();
  }
  if(keyPressed && key == 'k'){
    canFlip = true;
  }
}
