package sample;

public class Pixel {
    public int x, y, num;
    public boolean flag = false, covered = true;

    Pixel(){
        x = 0;
        y = 0;
    }
    Pixel(int xCoord,int yCoord){
        x = xCoord;
        y = yCoord;
        num = -1;  //pixel is unclicked
    }
    void setVal(){
        //TODO: change num to appropriate #/mines around
    }
}
