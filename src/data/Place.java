package data;

public class Place{
    private int x;
    private int y;
    private PLACE_STATUS status;

    public Place(int x, int y, PLACE_STATUS status){
        this.x=x;
        this.y=y;
        this.status=status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isExist(){
        return true;
    }

    public boolean isTaken(){
        return false;
    }

    public boolean isBooked(){
        return false;
    }

    public boolean isFree(){
        return true;
    }

    public void setTaken(){

    }

    public void setBooked(){

    }

    public void setFree(){

    }
}
