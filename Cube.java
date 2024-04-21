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
  void turnCorners(int planeBeingTurned, int planeValueToTurn, boolean clockwise) {
    Block tempCorner = null;
    if (planeBeingTurned == 0) {
      //x plane being turned - this refers to normal vector of it.,
      if (clockwise) {
        

        //manually switching the four corners, I am fine with this.
        tempCorner = arr[planeValueToTurn][0][0]; //saving TL corner
        
        arr[planeValueToTurn][0][0] = arr[planeValueToTurn][dim-1][0];//TL corner correct
        
        arr[planeValueToTurn][dim-1][0] = arr[planeValueToTurn][dim-1][dim-1]; //BL corner correct
        arr[planeValueToTurn][dim-1][dim-1] = arr[planeValueToTurn][0][dim-1]; // BR corner correct
        arr[planeValueToTurn][0][dim-1] = tempCorner; // TR corner correct
      }else{
        tempCorner = arr[planeValueToTurn][0][0];
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
      
      
    }
    
    
    
    
    
    
  }
  
  void turnEdges(int planeBeingTurned, int planeValueToTurn, boolean clockwise){
    if(planeBeingTurned == 0){
      if(clockwise){
        //copy top dim - 2
        Block[] temp = new Block[dim-2];
        //copying top row
        int arrCount = 0;
        for(int i = 1; i < dim - 1; i++){
          temp[arrCount] = arr[planeValueToTurn][0][i];
          //System.out.println("piece " + 0 + ", " + i);
          arrCount++;
        }
        //top row is copied, lets go back to left horizontal to steal
        int countUp = 1;
        for(int i = dim - 2; i >= 1 ; i--){
          arr[planeValueToTurn][0][countUp] = arr[planeValueToTurn][i][0];
          
          System.out.println("piece " + 0 + ", " + countUp + " copied thing below, then flippedEdge");
          System.out.println("piece " + i + ", " + 0);
          arr[planeValueToTurn][0][countUp].flipEdgeFaces(planeBeingTurned, clockwise);
          countUp++;
        }
        //our top row is now correct, lets steal bottom row to make left column correct
        //countUp = 1;
        for(int z = 1; z < dim - 1; z++){
          arr[planeValueToTurn][z][0] = arr[planeValueToTurn][dim-1][z];
          System.out.println("92piece " + z + ", " + 0 + "Copied thing below, then flipped");
          System.out.println("93piece " + (dim-1) + ", " + z);
          arr[planeValueToTurn][0][z].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        //left column, top row now correct. Lets steal right column onto bottom row. 
        
        for(int z = 1; z < dim - 1; z++){
          arr[planeValueToTurn][dim-1][z] = arr[planeValueToTurn][(dim - 1) - z][dim-1];
          System.out.println("100piece " + (dim-1) + ", " + z + "Copied below, then flipped");
          System.out.println("101piece " + ((dim - 1) - z) + ", " + (dim - 1));
          arr[planeValueToTurn][dim-1][z].flipEdgeFaces(planeBeingTurned, clockwise);
        }
        //bottom row now correct, copy temparray to right column
        for(int y = 1; y < dim - 1; y++){
          arr[planeValueToTurn][y][(dim-1)] = temp[y-1];
          System.out.println("107piece " + y + ", " + (dim-1) + "Stole OG, then flipped.");
          //System.out.println("piece " + ((dim - 1) - z) + ", " + (dim - 1));
          arr[planeValueToTurn][dim-1][y].flipEdgeFaces(planeBeingTurned, clockwise);
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
