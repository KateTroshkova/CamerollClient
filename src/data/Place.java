package data;

public class Place{
    private int x;
    private int y;
    private int row;
    private int column;
    private PLACE_STATUS status;

    public Place(int x, int y, PLACE_STATUS status){
        this.x=x;
        this.y=y;
        this.row=x/80;
        this.column=y/80;
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

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public int getTicketColumn(){
        return row+1;
    }

    public int getTicketRow(){
        return column+1;
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
        status=PLACE_STATUS.STATUS_TAKEN;
    }

    public void setBooked(){
        status=PLACE_STATUS.STATUS_BOOKED;
    }

    public void setFree(){
        status=PLACE_STATUS.STATUS_FREE;
    }
}
