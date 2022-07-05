package game;
public class Player {
    int x;
    int y;
    
    boolean faceLeft = false;

    char direction = 'R';
    int id;
    public boolean logout;
    public boolean move = false;
    public boolean attack = false;

    int health = 6;

    // setHealth
    public void setHealth(int health) {
        this.health = health;
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

    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }

    //get faceLeft
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

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    
}
