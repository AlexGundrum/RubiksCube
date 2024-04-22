import java.util.Random;
class Cube {

  public int dim; // determines what N is in NxNxN cube
  public int block_length; // pixel size of the block
  public Block[][][] arr; //the thing that holds the thing

  Cube(int dim, int block_length) {
    this.dim = dim;
    this.block_length = block_length;
    arr = new Block[dim][dim][dim];
    for (int x = 0; x < dim; x++) {
      for (int y = 0; y < dim; y++) {
        for (int z = 0; z < dim; z++) {
          arr[x][y][z] = new Block(block_length, dim, x, y, z);
        }
      }
    }
  }

  void drawPieces() {
    for (int x = 0; x < dim; x++) {
      for (int y = 0; y < dim; y++) {
        for (int z = 0; z < dim; z++) {
          arr[x][y][z].drawSelf(x, y, z);
        }
      }
    }
  }
  
  void scramble(){
    Random rand = new Random();
    int curRandom1;
    int curRandom2;
    for(int i = 0; i < 30; i++){
      curRandom1 = rand.nextInt(3);
      curRandom2 = rand.nextInt(2);
      if(curRandom2 == 1){
        curRandom2 = 2;
      }
      makeTurn(curRandom1, curRandom2 , true);
    }
  }
  
  void turnCorners(int planeBeingTurned, int planeValueToTurn, boolean clockwise) {
    Block tempCorner = null;
    if (planeBeingTurned == 0) {
      //x plane being turned - this refers to normal vector of it.,
      tempCorner = arr[planeValueToTurn][0][0];
      if (clockwise) {
        arr[planeValueToTurn][0][0] = arr[planeValueToTurn][dim-1][0];//TL corner correct
        arr[planeValueToTurn][dim-1][0] = arr[planeValueToTurn][dim-1][dim-1]; //BL corner correct
        arr[planeValueToTurn][dim-1][dim-1] = arr[planeValueToTurn][0][dim-1]; // BR corner correct
        arr[planeValueToTurn][0][dim-1] = tempCorner; // TR corner correct
      }else{
        arr[planeValueToTurn][0][0] = arr[planeValueToTurn][0][dim-1];
        arr[planeValueToTurn][0][dim-1] = arr[planeValueToTurn][dim-1][dim-1];
        arr[planeValueToTurn][dim-1][dim-1] = arr[planeValueToTurn][dim-1][0];
        arr[planeValueToTurn][dim-1][0] = tempCorner;
      }
      //below we are correcting the faces of the corners in new spots
      arr[planeValueToTurn][0][0].flipCornerFaces(planeBeingTurned);
      arr[planeValueToTurn][dim-1][0].flipCornerFaces(planeBeingTurned);
      arr[planeValueToTurn][dim-1][dim-1].flipCornerFaces(planeBeingTurned);
      arr[planeValueToTurn][0][dim-1].flipCornerFaces(planeBeingTurned);
      
      
    }else if(planeBeingTurned == 1){
      tempCorner = arr[0][planeValueToTurn][0];
      if(clockwise){
        arr[0][planeValueToTurn][0] = arr[0][planeValueToTurn][dim - 1];
        arr[0][planeValueToTurn][dim - 1] = arr[dim-1][planeValueToTurn][dim-1];
        arr[dim-1][planeValueToTurn][dim-1] = arr[dim-1][planeValueToTurn][0];
        arr[dim-1][planeValueToTurn][0] = tempCorner;
      }else{
        arr[0][planeValueToTurn][0] = arr[dim-1][planeValueToTurn][0];// TL correct
        arr[dim-1][planeValueToTurn][0] = arr[dim-1][planeValueToTurn][dim-1]; //TR correct
        arr[dim-1][planeValueToTurn][dim-1] = arr[dim-1][planeValueToTurn][0]; //BR correct
        arr[0][planeValueToTurn][dim-1] = tempCorner; // BL correct
      }
      arr[0][planeValueToTurn][0].flipCornerFaces(planeBeingTurned);
      arr[dim-1][planeValueToTurn][0].flipCornerFaces(planeBeingTurned);
      arr[dim-1][planeValueToTurn][dim-1].flipCornerFaces(planeBeingTurned);
      arr[0][planeValueToTurn][dim-1].flipCornerFaces(planeBeingTurned);
      
    }else if(planeBeingTurned == 2){
      tempCorner = arr[0][0][planeValueToTurn];
      if(clockwise){
        arr[0][0][planeValueToTurn] = arr[dim - 1][0][planeValueToTurn];
        arr[dim -1 ][0][planeValueToTurn] = arr[dim - 1][dim - 1][planeValueToTurn];
        arr[dim - 1][dim - 1][planeValueToTurn] = arr[0][dim - 1][planeValueToTurn];
        arr[0][dim - 1][planeValueToTurn] = tempCorner;
      }else{
        arr[0][0][planeValueToTurn] = arr[0][dim - 1][planeValueToTurn];
        arr[0][dim - 1][planeValueToTurn] = arr[dim - 1][dim - 1][planeValueToTurn];
        arr[dim - 1][dim - 1][planeValueToTurn] = arr[dim - 1][0][planeValueToTurn];
        arr[dim - 1][0][planeValueToTurn] = tempCorner;
      }
      arr[0][0][planeValueToTurn].flipCornerFaces(planeBeingTurned);
      arr[0][dim - 1][planeValueToTurn].flipCornerFaces(planeBeingTurned);
      arr[dim - 1][dim - 1][planeValueToTurn].flipCornerFaces(planeBeingTurned);
      arr[dim - 1][0][planeValueToTurn].flipCornerFaces(planeBeingTurned);
      
    }
    
    
    
    
    
    
  }
  
  void turnEdges(int planeBeingTurned, int planeValueToTurn, boolean clockwise){
    if(planeBeingTurned == 0){
      if(clockwise){
        
        Block[] temp = new Block[dim-2]; //copy top dim - 2
        //copying top row
        int arrCount = 0;
        for(int i = 1; i < dim - 1; i++){
          temp[arrCount] = arr[planeValueToTurn][0][i];
          arrCount++;
        }
        
        //top row is copied, lets go back to left horizontal to steal
        int countUp = 1;
        for(int i = dim - 2; i >= 1 ; i--){
          arr[planeValueToTurn][0][countUp] = arr[planeValueToTurn][i][0];
          arr[planeValueToTurn][0][countUp].flipEdgeFaces(planeBeingTurned, clockwise);
          countUp++;
        }
        
        //our top row is now correct, lets steal bottom row to make left column correct
        for(int z = 1; z < dim - 1; z++){
          arr[planeValueToTurn][z][0] = arr[planeValueToTurn][dim-1][z];
          arr[planeValueToTurn][z][0].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        
        //left column, top row now correct. Lets steal right column onto bottom row. 
        for(int z = 1; z < dim - 1; z++){
          arr[planeValueToTurn][dim-1][z] = arr[planeValueToTurn][(dim - 1) - z][dim-1];
          arr[planeValueToTurn][dim-1][z].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        
        //bottom row now correct, copy temparray to right column
        for(int y = 1; y < dim - 1; y++){
          arr[planeValueToTurn][y][(dim-1)] = temp[y-1];
          arr[planeValueToTurn][y][(dim-1)].flipEdgeFaces(planeBeingTurned, clockwise);
        }
      }else{
         
      }
    }else if(planeBeingTurned == 1){
      if(clockwise){
        Block[] temp = new Block[dim-2]; //copy top dim - 2
        //copying top row
        int arrCount = 0;
        for(int i = 1; i < dim - 1; i++){
          temp[arrCount] = arr[i][planeValueToTurn][0];
          arrCount++;
        }
        
        //top row is copied, lets go back to left horizontal to steal
        int countUp = 1;
        for(int i = dim - 2; i >= 1 ; i--){
          arr[countUp][planeValueToTurn][0] = arr[0][planeValueToTurn][i];
          arr[countUp][planeValueToTurn][0].flipEdgeFaces(planeBeingTurned, clockwise);
          countUp++;
        }
        
        //our top row is now correct, lets steal bottom row to make left column correct
        for(int z = 1; z < dim - 1; z++){
          arr[0][planeValueToTurn][z] = arr[z][planeValueToTurn][dim-1];
          arr[0][planeValueToTurn][z].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        
        //left column, top row now correct. Lets steal right column onto bottom row. 
        for(int z = 1; z < dim - 1; z++){
          arr[z][planeValueToTurn][dim-1] = arr[dim-1][planeValueToTurn][(dim - 1) - z];
          arr[z][planeValueToTurn][dim-1].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        
        //bottom row now correct, copy temparray to right column
        for(int z = 1; z < dim - 1; z++){
          arr[(dim-1)][planeValueToTurn][z] = temp[z-1];
          arr[(dim-1)][planeValueToTurn][z].flipEdgeFaces(planeBeingTurned, clockwise);
        }
      }else{
        
      }
    }else if(planeBeingTurned == 2){
      if(clockwise){
        Block[] temp = new Block[dim-2]; //copy top dim - 2
        //copying top row
        int arrCount = 0;
        for(int i = 1; i < dim - 1; i++){
          temp[arrCount] = arr[0][i][planeValueToTurn];
          arrCount++;
        }
        
        //top row is copied, lets go back to left horizontal to steal
        int countUp = 1;
        for(int i = dim - 2; i >= 1 ; i--){
          arr[0][countUp][planeValueToTurn] = arr[i][0][planeValueToTurn];
          arr[0][countUp][planeValueToTurn].flipEdgeFaces(planeBeingTurned, clockwise);
          countUp++;
        }
        
        //our top row is now correct, lets steal bottom row to make left column correct
        for(int x = 1; x < dim - 1; x++){
          arr[x][0][planeValueToTurn] = arr[dim-1][x][planeValueToTurn];
          arr[x][0][planeValueToTurn].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        
        //left column, top row now correct. Lets steal right column onto bottom row. 
        for(int y = 1; y < dim - 1; y++){
          arr[dim-1][y][planeValueToTurn] = arr[(dim - 1) - y][dim-1][planeValueToTurn];
          arr[dim-1][y][planeValueToTurn].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        
        //bottom row now correct, copy temparray to right column
        for(int z = 1; z < dim - 1; z++){
          arr[z][(dim-1)][planeValueToTurn] = temp[z-1];
          arr[z][(dim-1)][planeValueToTurn].flipEdgeFaces(planeBeingTurned, clockwise);
        }
      }else{
        
      }
    }
  }

  void makeTurn(int planeBeingTurned, int planeValueToTurn, boolean clockwise) {
    turnCorners(planeBeingTurned, planeValueToTurn, clockwise);
    turnEdges(planeBeingTurned, planeValueToTurn, clockwise);
    if (planeBeingTurned == 0) {
      //x plane being turned - this refers to normal vector of it.,
      if (clockwise) {
        for (int y = 0; y < dim; y++) {
          for (int z = 0; z < dim; z++) {
            if (!((y == 0 || y == dim - 1) && (z == 0 || z == dim - 1))) {
                //checking for center piece
                if(!((y == 0 || y == dim-1) || (z == 0 || z == dim -1))){
                  //is center piece
                }else{
                  //edge
                  
                }
              
              } 
            } 
          }
        }
      }else if (planeBeingTurned == 1) {
      //y plane being turned - this refers to normal vector of it.
    } else if (planeBeingTurned ==2) {
      //z plane being turned - this refers to normal vector of it.
    }
  }
}
