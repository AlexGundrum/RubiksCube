class Cube {

  public int dim; // determines what N is in NxNxN cube
  public int block_length; // pixel size of the block
  public Block[][][] arr; //the thing that holds the thing

  Cube(int dim, int block_length) {
    this.dim = dim;
    this.block_length = block_length;
    arr = new Block[dim][dim][dim];
    for (int x = 0; x < dim; x++) {
      for (int y = 0; y < dim; y++){
        for(int z = 0; z < dim; z++){
          arr[x][y][z] = new Block(block_length, dim);        
        }
      }
    }
    
  }
  
  void drawPieces(){
    for (int x = 0; x < dim; x++) {
      for (int y = 0; y < dim; y++){
        for(int z = 0; z < dim; z++){
          arr[x][y][z].drawSelf(x,y,z);        
        }
      }
    }
    
    
  }
}
