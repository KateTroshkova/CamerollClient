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
        return status!=PLACE_STATUS.STATUS_NOT_EXIST;
    }

    public boolean isTaken(){
        return status==PLACE_STATUS.STATUS_TAKEN;
    }

    public boolean isBooked(){
        return status==PLACE_STATUS.STATUS_BOOKED;
    }

    public boolean isFree(){
        return status==PLACE_STATUS.STATUS_FREE;
    }

    public void setTaken(){

    }

    public void setBooked(){

    }

    public void setFree(){

    }
}
