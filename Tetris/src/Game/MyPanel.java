package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener, ActionListener {

    int score;

    boolean running = true;

    Point point = new Point(4, 0);

    boolean[] area = new boolean[150];

    Point[] points = Game.Shape.getShape();

    Timer timer = new Timer(500, this);

    public void begin() {
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画出大致的棋盘
        g.setColor(Color.white);
        g.fillRect(0, 50, 500, 750);

        //画出网格线
        g.setColor(Color.black);
        for (int i = 0; i != 10; i++) {
            g.drawLine(50 * i, 50, 50 * i, 750);
        }
        for (int i = 0; i != 15; i++) {
            g.drawLine(0, 50 * (i + 1), 500, 50 * (i + 1));
        }

        //画出方块
        g.setColor(Color.red);
        for (int i = 0; i < area.length; i++) {
            if (area[i]) {
                int y = i / 10;
                int x = i % 10;
                g.fillRect(x * 50, y * 50, 50, 50);
            }
        }

        for (Point p : points) {
            g.fillRect((p.x + point.x) * 50, (p.y + point.y) * 50, 50, 50);
        }

        //画出上方的分数栏目
        g.setColor(Color.blue);
        g.fillRect(0, 0, 500, 50);
        //写出分数
        g.setColor(Color.black);
        Font font = new Font("微软雅黑", 20, 20);
        g.setFont(font);
        g.drawString("分数: " + score, 200, 30);
    }

    public boolean moveLeft() {
        for (Point p : points) {
            if ((p.x + point.x - 1) < 0) {
                return false;
            }
            int index = (p.y + point.y) * 10 + (p.x + point.x - 1);
            if (area[index]) {
                return false;
            }
        }
        return true;
    }

    public boolean moveRight() {
        for (Point p : points) {
            if ((p.x + point.x + 1) > 9) {
                return false;
            }
            int index = (p.y + point.y) * 10 + (p.x + point.x + 1);
            if (area[index]) {
                return false;
            }
        }
        return true;
    }

    public boolean changeShape() {
        //先判断旋转后的图形符不符合标准
        ++Shape.direct;
        Point[] points_ = Shape.getT();
        for (Point p : points_) {
            if ((p.x + point.x) < 0 || (p.x + point.x) > 9
                    || p.y + point.y > 14) {
                return false;
            }
            int index = (p.y + point.y) * 10 + (p.x + point.x);
            if (area[index]) {
                return false;
            }
        }
        Shape.direct--;
        return true;
    }

    public boolean moveDown() {
        for (Point p : points) {
            int index = (p.y + point.y + 1) * 10 + (p.x + point.x);
            if ((p.y + point.y + 1) > 14) {
                return false;
            }
            if (area[index] || index > 149) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!running) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //正方形不用旋转
            if (Shape.randomShape == 1) {
                return;
            }
            if (changeShape()) {
                Shape.direct++;
                points = Shape.getShape();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (moveLeft()) {
                point.x--;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (moveRight()) {
                point.x++;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (moveDown()) {
                point.y++;
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pointDown();
    }

    public void pointDown() {
        boolean stop = false;
        for (Point p : points) {
            int index = (p.y + point.y + 1) * 10 + (p.x + point.x);
            if (index > 149 || area[index]) {
                stop = true;
                break;
            }
        }
        if (stop) {
            for (Point p : points) {
                int index = (p.y + point.y) * 10 + (p.x + point.x);
                area[index] = true;
            }
            point.x = 4;
            point.y = 0;
            Shape.direct = -1;
            Shape.randomShape = -1;
            points = Shape.getShape();
        } else {
            point.y++;
        }

        boolean[] newArea = new boolean[150];
        int line = 14;
        int num = 0;
        for (int j = 14; j != 0; j--) {
            for (int i = 0; i != 10; i++) {
                if (area[j * 10 + i]) {
                    num++;
                }
            }
            if (num != 10) {
                for (int i = 0; i != 10; i++) {
                    newArea[line * 10 + i] = area[j * 10 + i];
                }
                line--;
            } else {
                score = score + 100;
            }
            num = 0;
        }
        area = newArea;

        for (int i = 0; i != 20; i++) {
            if (area[i]) {
                timer.stop();
                System.out.println("游戏结束");
                running = false;
                break;
            }
        }
        if (running) {
            repaint();
        }
    }
}
