package Game;

public class Shape {
    static int direct = -1;
    static int randomShape = -1;

    public static Point[] getShape(){
        if (randomShape == -1){
            randomShape = ((int) (Math.random() * 4));
        }
        switch (randomShape){
            case 0:
                return getT();
            case 1:
                return getSquare();
            case 2:
                return getLine();
            case 3:
                return getS();
            default:
                return getL();
        }
    }

    //T字型方块
    public static Point[] getT(){
        if (direct == -1) {
            direct = ((int) (Math.random() * 5));
        }
        switch (direct % 4){
            case 0:
                Point[] points = {(new Point(0, 0)), (new Point(1, 0)), (new Point(2, 0)), (new Point(1, 1))};
                return points;
            case 1:
                Point[] points1 = {(new Point(1, -1)), (new Point(1, 0)), (new Point(2, 0)), (new Point(1, 1))};
                return points1;
            case 2:
                Point[] points2 = {(new Point(1, -1)), (new Point(0, 0)), (new Point(1, 0)), (new Point(2, 0))};
                return points2;
            default:
                Point[] points3 = {(new Point(1, -1)), (new Point(0, 0)), (new Point(1, 0)), (new Point(1, 1))};
                return points3;
        }
    }

    //正方形
    public static Point[] getSquare(){
        Point[] points = {(new Point(0, 0)), (new Point(0, 1)), (new Point(1, 0)), (new Point(1, 1))};
        return points;
    }

    //线形
    public static Point[] getLine(){
        switch (direct % 2){
            case 0:
                Point[] points = {(new Point(0, 0)), (new Point(0, 1)), (new Point(0, 2)), (new Point(0, 3))};
                return points;
            default:
                Point[] points1 = {(new Point(-1, 2)), (new Point(0, 2)), (new Point(1, 2)), (new Point(2, 2))};
                return points1;
        }
    }

    //S形
    public static Point[] getS(){
        switch (direct % 2){
            case 0:
                Point[] points = {(new Point(0, 0)), (new Point(0, 1)), (new Point(1, 1)), (new Point(1, 2))};
                return points;
            default:
                Point[] points1 = {(new Point(1, 1)), (new Point(2, 1)), (new Point(0, 2)), (new Point(1, 2))};
                return points1;
        }
    }

    //L形状
    public static Point[] getL(){
        switch (direct % 4){
            case 0:
                Point[] points = {(new Point(0, 0)), (new Point(0, 1)), (new Point(0, 2)), (new Point(1, 2))};
                return points;
            case 1:
                Point[] points1 = {(new Point(0, 1)), (new Point(1, 1)), (new Point(2, 1)), (new Point(2, 0))};
                return points1;
            case 2:
                Point[] points2 = {(new Point(0, 0)), (new Point(1, 0)), (new Point(1, 1)), (new Point(1, 2))};
                return points2;
            default:
                Point[] points3 = {(new Point(-1, 1)), (new Point(-1, 2)), (new Point(0, 1)), (new Point(1, 1))};
                return points3;
        }
    }
}
