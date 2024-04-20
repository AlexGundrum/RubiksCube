//headstrong lets go into this
Cube rub;
float angleX = 0;
float angleY = 0;
float camDist = 500;
float camX = 0;
float camY = 0;
float camZ = camDist; 
float prevMouseX, prevMouseY;

void setup(){
  size(800, 600, P3D);
  prevMouseX = mouseX;
  prevMouseY = mouseY;
  rub = new Cube(3, 40);
}




void draw(){
  background(220);
  //sphere(20);
  //box(28);
  camera(camX, camY, camZ, // Camera position (center of the screen, camZ)
         0, 0, 0,   // Look at point (center of the screen, 0)
         0, 1, 0);  
  if (mousePressed && mouseButton == LEFT) {
    float dx = mouseX - prevMouseX;
    float dy = mouseY - prevMouseY;
    angleX += radians(dy * 0.5); // Adjust rotation based on vertical mouse movement
    angleY += radians(dx * 0.5); // Adjust rotation based on horizontal mouse movement
  }
  prevMouseX = mouseX;
  prevMouseY = mouseY;
  
  camX = sin(angleY) * camDist;
  camY = -sin(angleX) * camDist;
  camZ = cos(angleY) * camDist;
  
  rotateX(angleX);
  rotateY(angleY);
  
  //translate(width/2, height/2, 0); // Move origin to center of the canvas

  // Rotate the box based on time
  rub.drawPieces();
}
