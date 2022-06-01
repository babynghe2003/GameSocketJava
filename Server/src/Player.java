public class Player {
    int x;
    int y;
    boolean faceLeft = false;
    char direction = 'R';
    int id;
    public boolean logout;
    public boolean move = false;

    //set move
    public void setMove(boolean move) {
        this.move = move;
    }

    public char getDirection() {
        return direction;
    }



    public void setDirection(char direction) {
        this.direction = direction;
    }
   

    public Player(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.logout = false;
    }

    // set faceLeft
    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }
    
    public boolean isFaceLeft() {
        return faceLeft;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
