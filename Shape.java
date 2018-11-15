/**
 * Shape.java
 *
 * @author ryanperkins
 */
import java.io.*;
import java.awt.Graphics;

/**
 * Shape.class
 */
class Shape {
    // visibility
    private boolean visible;
    
    /**
     * shape constructor
     */
    public Shape() {
        visible = true;
    }
    
    /**
     * draw method to be overidden
     *
     * @param g graphics package
     */
    public void drawShape(Graphics g) { }
    
    /**
     * multiply method that multiplies matricies
     *
     * @param a first matrix
     * @param b second matrix
     */
    public static double[][] multiply(double[][] a, double[][] b) {
        int row0 = a.length;
        int col0 = a[0].length;
        int row1 = b.length;
        int col1 = b[0].length;
        if(col0 != row1) throw new RuntimeException("illegal dimensions");
        double[][] c = new double[row0][col1];
        for(int i = 0; i < row0; i++)
            for(int j = 0; j < col1; j++)
                for(int k = 0; k < col0; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }
    
    /**
     * convertX method that converts the point an cartesian coord.
     *
     * @param x coord. to convert
     */
    public double convertX(double x) {if(x >= 0)
            x += 512;
        else
            x = 512 - Math.abs(x);
        
        return x;
    }
    
    /**
     * convertY method that converts the point an cartesian coord.
     *
     * @param y coord. to convert
     */
    public double convertY(double y) {
        if(y >= 0)
            y = 384 - y;
        else
            y = Math.abs(y) + 384;
        
        return y;
    }
    
    /**
     * perspective method that displays an object with perspective projection 
     *
     * @param vertices
     * @param x viewpoint coord.
     * @param y viewpoint coord.
     * @param z viewpoint coord.
     * @param d distance from the screen
     * @param s size of the screen
     */
    public static Point[] perspective(Point[] vertices, double x,  double y,  double z,  double d,  double s) {
        /* V matrix calculations */
        // t1 * t2
        double[][] t1 = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0,}, {-x, -y, -z, 1}};
        double[][] t2 = {{1, 0, 0, 0}, {0, 0, -1, 0}, {0, 1, 0, 0,}, {0, 0, 0, 1}};
        double[][] t12 = multiply(t1, t2);
        
        // t12 * t3
        double cos0 = (y/Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        double sin0 = (x/Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        double[][] t3 = {{-cos0, 0, sin0, 0}, {0, 1, 0, 0}, {-sin0, 0, -cos0, 0,}, {0, 0, 0, 1}};
        double[][] t123 = multiply(t12, t3);
        
        // t123 * t4
        double cos1 = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double sin1 = (Math.abs(z))/Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double[][] t4 = {{1, 0, 0, 0}, {0, cos1, sin1, 0}, {0, -sin1, cos0, 0,}, {0, 0, 0, 1}};
        double[][] t1234 = multiply(t123, t4);
        
        // t1234 * t5
        double[][] t5 = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, -1, 0,}, {0, 0, 0, 1}};
        double[][] V = multiply(t1234, t5);
        /* end of V calculations */
        
        double[][] N = {{d/s, 0, 0, 0}, {0, d/s, 0, 0}, {0, 0, 1, 0,}, {0, 0, 0, 1}};
        double[][] T = multiply(V, N);
        
        Point[] temp = new Point[]{new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 0, 0)};
        
        for(int i = 0; i < temp.length; i++) {
            temp[i].setX((vertices[i].getX() * T[0][0]) + (vertices[i].getY() * T[1][0]) + (vertices[i].getZ() * T[2][0]) + (1 * T[3][0]));
            temp[i].setY((vertices[i].getX() * T[0][1]) + (vertices[i].getY() * T[1][1]) + (vertices[i].getZ() * T[2][1]) + (1 * T[3][1]));
            temp[i].setZ((vertices[i].getX() * T[0][2]) + (vertices[i].getY() * T[1][2]) + (vertices[i].getZ() * T[2][2]) + (1 * T[3][2]));
        }
        
        for(int i = 0; i < temp.length; i++) {
            temp[i].setX(((temp[i].getX()/temp[i].getZ()) * 384) + 384);
            temp[i].setY(-((temp[i].getY()/temp[i].getZ()) * 384) + 384);
        }
        
        return temp;
    }
}

