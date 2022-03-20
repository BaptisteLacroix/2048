package game;


import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.KeyEvent;

public class Game implements KeyListener{

    private final Random random = new Random();
    private boolean loose = false;
    private int score = 0;
    private int nbrTours = 0;
    private final int[] depDone = {0, 0, 0, 0};
    private final int[][] tableau = {
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0}
    };

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.loopLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.loopRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.loopTop();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.loopBottom();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.loopLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.loopRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.loopTop();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.loopBottom();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.loopLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.loopRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.loopTop();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.loopBottom();
        }
    }

    public void inGame() {
        int genLine = random.nextInt(3);
        int genCol = random.nextInt(3);
        this.tableau[genLine][genCol] = 2;

        System.out.println("-------------------------------");
        System.out.println(Arrays.toString(this.tableau[0]));
        System.out.println(Arrays.toString(this.tableau[1]));
        System.out.println(Arrays.toString(this.tableau[2]));
        System.out.println(Arrays.toString(this.tableau[3]));
        System.out.println("-------------------------------");


        while (!loose) {

            if (noMoreDep())
                this.loose = true;

            while (this.tableau[genLine][genCol] != 0) {
                genLine = random.nextInt(3);
                genCol = random.nextInt(3);
            }

            this.tableau[genLine][genCol] = 2;
            System.out.println("-------------------------------");
            System.out.println(Arrays.toString(this.tableau[0]));
            System.out.println(Arrays.toString(this.tableau[1]));
            System.out.println(Arrays.toString(this.tableau[2]));
            System.out.println(Arrays.toString(this.tableau[3]));
            System.out.println("-------------------------------");
            nbrTours ++;
        }
    }

    public boolean noMoreDep() {
        return this.depDone[0] == 0 && this.depDone[1] == 0 && this.depDone[2] == 0 && this.depDone[3] == 0;
    }

    public void loopLeft() {
        for (int line = 0; line < 4; line ++) {
            for (int col = 0; col < 4; col ++) {
                //System.out.println("Ligne : " + line +  "\tCol : " + col);
                this.leftDep(line, col);
            }
        }
    }

    public void loopRight() {
        for (int line = 0; line < 4; line ++) {
            for (int col = 0; col < 4; col ++) {
                //System.out.println("Ligne : " + line +  "\tCol : " + col);
                this.rightDep(line, col);
            }
        }
    }

    public void loopTop() {
        for (int line = 0; line < 4; line ++) {
            for (int col = 0; col < 4; col ++) {
                //System.out.println("Ligne : " + line +  "\tCol : " + col);
                this.topDep(line, col);
            }
        }
    }

    public void loopBottom() {
        for (int line = 0; line < 4; line ++) {
            for (int col = 0; col < 4; col ++) {
                //System.out.println("Ligne : " + line +  "\tCol : " + col);
                this.bottomDep(line, col);
            }
        }
    }

    public void leftDep(int line, int col) {
        if (col > 0 && (this.tableau[line][col-1] == 0 || this.tableau[line][col-1] == this.tableau[line][col])) {
             this.tableau[line][col-1] += this.tableau[line][col];
             this.score += this.tableau[line][col-1];
             this.tableau[line][col] = 0;
             this.depDone[0] += 1;
        }
    }

    public void rightDep(int line, int col) {
        if (col < 3 && (this.tableau[line][col+1] == 0 || this.tableau[line][col-1] == this.tableau[line][col])) {
            this.tableau[line][col+1] += this.tableau[line][col];
            this.score += this.tableau[line][col+1];
            this.tableau[line][col] = 0;
            this.depDone[1] += 1;
        }
    }

    public void topDep(int line, int col) {
        if (line > 0 && (this.tableau[line-1][col] == 0 || this.tableau[line-1][col] == this.tableau[line][col])) {
            this.tableau[line-1][col] += this.tableau[line][col];
            this.score += this.tableau[line-1][col];
            this.tableau[line-1][col] = 0;
            this.depDone[2] += 1;
        }
    }

    public void bottomDep(int line, int col) {
        if (line > 3 && (this.tableau[line+1][col] == 0 || this.tableau[line+1][col] == this.tableau[line][col])) {
            this.tableau[line+1][col] += this.tableau[line][col];
            this.score += this.tableau[line+1][col];
            this.tableau[line+1][col] = 0;
            this.depDone[3] += 1;
        }
    }

    public void affichage() {
        JFrame f = new JFrame();//creating instance of JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
        f.add(b);//adding button in JFrame
    }
}