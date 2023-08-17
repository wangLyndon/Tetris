package Game;

import javax.swing.*;

public class Tetris01 extends JFrame {
    MyPanel myPanel;

    public Tetris01(){
        myPanel = new MyPanel();
        myPanel.begin();
        this.add(myPanel);
        this.setSize(500, 800);
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Tetris01 tetris01 = new Tetris01();
    }
}
