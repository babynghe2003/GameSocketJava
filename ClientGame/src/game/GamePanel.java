package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import com.google.gson.*;

public class GamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WITDH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    static final int UNIT_SIZE = 100;
    static final int GAME_UNITS = (SCREEN_WITDH*SCREEN_HEIGHT)/UNIT_SIZE;

    Timer timer = new Timer(1000/60, this);
    public ArrayList<Player> players = new ArrayList<Player>();
    Player playerCurrent = null;
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    int ip = 8080;

    int DELAY = 1000/200;
    Random random;

    public int id;
    int time = 0;
    int step = 1;

    Map map = new Map(this);

    public boolean upPressed, downPressed, leftPressed, rightPressed, attackPressed;

    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WITDH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdaper());
        this.timer.start();
        connectToServer();
        receiveData();
        startGame();
    }
    public void startGame(){
        timer.stop();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        draw(g2d);
    }

    public void draw(Graphics2D g){
        g.translate(-(playerCurrent.x - SCREEN_WITDH/2 + UNIT_SIZE/2), -(playerCurrent.y-SCREEN_HEIGHT/2 + UNIT_SIZE/2));
        map.draw(g);
        if (playerCurrent.logout){
            System.exit(0);
        }
        for(Player player: players)
        if(!player.logout)
        {
            new PlayerUI(player.getId(),player.x, player.y, this, step,player.isFaceLeft(),player.move, player.health).draw(g);
            new Weapon(player.getId(),player.x, player.y, this, player.direction, player.attack, player.isFaceLeft()).draw(g);
        }
        map.drawWall(g);
         
    }
    public void update(){
        if (playerCurrent != null){
            if (playerCurrent.move){
                if(playerCurrent.getDirection() == 'R')
                playerCurrent.x += 4;
                else if(playerCurrent.getDirection() == 'L')
                playerCurrent.x -= 4;
                else if(playerCurrent.getDirection() == 'U')
                playerCurrent.y -= 4;
                else if(playerCurrent.getDirection() == 'D')
                playerCurrent.y += 4;
                sendData("x " + playerCurrent.x + " y " + playerCurrent.y);

            }
        }
        
        
    }

    //create socket clients and connect to server
    public void connectToServer(){
        try{
            socket = new Socket("localhost", ip);
            System.out.println("Connected to server");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            this.id = Integer.parseInt(dis.readUTF());
            System.out.println("Your id is: " + this.id);
            Gson gson = new Gson();
            Player[] players = gson.fromJson(dis.readUTF(), Player[].class);
            this.players = new ArrayList<Player>();
            for(Player player: players){
                if(player.getId() == this.id)
                this.playerCurrent = player;
                this.players.add(player);
            }
            System.out.println("Received players: " + this.players.size());
        }catch(Exception e){
            System.out.println("Could not connect to server");
        }
    }

    // create thread to sen

    //send data to server and receive data from server
    //
    public void sendData(String data){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{ 
                    dos.writeUTF(data);
                }catch(Exception e){
                    System.out.println("Could not send data to server");
                }
            }
        });
        thread.start();
    }

    // create thread to receive arraylist json from server
    public void receiveData(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                while (true) {
                    try{
                    Gson gson = new Gson();
                    String json = dis.readUTF();
                    if(json.equals("You died")){
                        System.out.println("You died");
                        System.exit(0);
                    }
                    Player[] p = gson.fromJson(json, Player[].class);
                    players = new ArrayList<Player>();
                    for(Player player: p){
                        if(player.getId() == id)
                        playerCurrent = player;
                        players.add(player);
                        
                    }
                }catch(Exception e){
            
                    System.out.println("Could not receive data from server");
                }
                }
                
            }
        });
        thread.start();
    }

    //set players
    public void setPlayers(ArrayList<Player> players){
        this.players = players;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        if (time % 10==0){
            step++;
            if(step > 4)
                step = 1;
        }
        update();
        repaint();
        
    }

    public class MyKeyAdaper extends KeyAdapter {
        //override keytype
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    sendData("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    sendData("RIGHT");
                    break;
                case KeyEvent.VK_UP:
                    sendData("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    sendData("DOWN");
                    break;
                case KeyEvent.VK_A:
                    sendData("ATTACK");
                    break;
                case KeyEvent.VK_SPACE:
                    
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    sendData("STAND");
                    break;
                case KeyEvent.VK_RIGHT:
                    sendData("STAND");
                    break;
                case KeyEvent.VK_UP:
                    sendData("STAND");
                    break;
                case KeyEvent.VK_DOWN:
                    sendData("STAND");
                    break;
                case KeyEvent.VK_A:
                    sendData("A");
                    break;
                case KeyEvent.VK_SPACE:
                    break;
                default:
                    break;
            }
        }
    }
}
