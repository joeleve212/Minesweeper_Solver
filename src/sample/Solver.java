package sample;

public class Solver {
    public Pixel[][] gameBoard;
    Solver(int w, int h){
        //TODO: generate board of Pixels
        gameBoard = new Pixel[w][h];
        for(int i = 0;i<w;i++){
            for(int j = 0;j<h;j++){
                gameBoard[i][j]=new Pixel(i,j);
            }
        }
    }

    public boolean pixelDone(Pixel checkPix){
        //TODO: function to check around one Pixel
        if(checkPix.covered){return false;}
        int nearMines = 0;
        for(int x = -1;x<=1;x++){
            for(int y = -1;y<=1;y++){
                if(x==0&y==0){continue;}
                if(gameBoard[x][y].flag) { //TODO: check for flags
                    nearMines++;
                }
            }
        }
        if(nearMines==checkPix.num){return true;}
        else{return false;}
    }
}
