/**
 * Cube.java
 *
 * @author ryanperkins
 */
import java.io.*;
import javax.swing.*;
import java.awt.Graphics;

import java.util.*;

/**
 * Cube.class
 */
class Cube extends Shape {
    // vertices
    private static Point[] vertices;
    // x
    private static double x = 6;
    // y
    private static double y = 8;
    // z
    private static double z = 7.5;
    // d
    private static double d = 60;
    // s
    private static double s = 15;
    
    /**
     * cube constructor
     *
     * @param A 1st corner
     * @param B 2nd corner
     * @param C 3rd corner
     * @param D 4th corner
     * @param E 5th corner
     * @param F 6th corner
     * @param G 7th corner
     * @param H 8th corner
     */
    public Cube(Point A, Point B, Point C, Point D, Point E, Point F, Point G, Point H) {
        vertices = new Point[]{A, B, C, D, E, F, G, H};
    }
    
    /**
     * drawShape method draws a cube using perspective projection 
     *
     * @param g graphics package
     */
    public void drawShape(Graphics g) {
        Point temp[] = Shape.perspective(vertices, Cube.x,  Cube.y,  Cube.z,  Cube.d,  Cube.s);
        
        // front square
        g.drawLine((int)temp[0].getX(), (int)temp[0].getY(), (int)temp[1].getX(), (int)temp[1].getY());
        g.drawLine((int)temp[1].getX(), (int)temp[1].getY(), (int)temp[2].getX(), (int)temp[2].getY());
        g.drawLine((int)temp[2].getX(), (int)temp[2].getY(), (int)temp[3].getX(), (int)temp[3].getY());
        g.drawLine((int)temp[3].getX(), (int)temp[3].getY(), (int)temp[0].getX(), (int)temp[0].getY());
        
        // back square
        g.drawLine((int)temp[4].getX(), (int)temp[4].getY(), (int)temp[5].getX(), (int)temp[5].getY());
        g.drawLine((int)temp[5].getX(), (int)temp[5].getY(), (int)temp[6].getX(), (int)temp[6].getY());
        g.drawLine((int)temp[6].getX(), (int)temp[6].getY(), (int)temp[7].getX(), (int)temp[7].getY());
        g.drawLine((int)temp[7].getX(), (int)temp[7].getY(), (int)temp[4].getX(), (int)temp[4].getY());
        
        // connect squares
        g.drawLine((int)temp[0].getX(), (int)temp[0].getY(), (int)temp[4].getX(), (int)temp[4].getY());
        g.drawLine((int)temp[1].getX(), (int)temp[1].getY(), (int)temp[5].getX(), (int)temp[5].getY());
        g.drawLine((int)temp[2].getX(), (int)temp[2].getY(), (int)temp[6].getX(), (int)temp[6].getY());
        g.drawLine((int)temp[3].getX(), (int)temp[3].getY(), (int)temp[7].getX(), (int)temp[7].getY());
    }
    
    /**
     * translate method translates the XYZ by a, b, c around x, y, z respectively
     *
     * @param a x translation factor
     * @param b y translation factor
     * @param c z translation factor
     */
    public static void translate(double a, double b, double c) {
        double[][] s ={{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {a, b, c, 1}};
        double x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = vertices[i].getX();
            y = vertices[i].getY();
            z = vertices[i].getZ();
            
            vertices[i].setX((x * s[0][0]) + (y * s[1][0]) + (z * s[2][0]) + (1 * s[3][0]));
            vertices[i].setY((x * s[0][1]) + (y * s[1][1]) + (z * s[2][1]) + (1 * s[3][1]));
            vertices[i].setZ((x * s[0][2]) + (y * s[1][2]) + (z * s[2][2]) + (1 * s[3][2]));
        }
    }
    
    /**
     * scale method scales the XYZ by a, b, c around x, y, z respectively
     *
     * @param a x scaling factor
     * @param b y scaling factor
     * @param c z scaling factor
     */
    public static void scale(double a, double b, double c) {
        double[][] s ={{a, 0, 0, 0}, {0, b, 0, 0}, {0, 0, c, 0}, {0, 0, 0, 1}};
        double x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = vertices[i].getX();
            y = vertices[i].getY();
            z = vertices[i].getZ();
            
            vertices[i].setX((x * s[0][0]) + (y * s[1][0]) + (z * s[2][0]) + (1 * s[3][0]));
            vertices[i].setY((x * s[0][1]) + (y * s[1][1]) + (z * s[2][1]) + (1 * s[3][1]));
            vertices[i].setZ((x * s[0][2]) + (y * s[1][2]) + (z * s[2][2]) + (1 * s[3][2]));
        }
    }
    
    /**
     * rotateX method rotates the XYZ by an angle around the x-axis
     *
     * @param angle
     */
    public static void rotateX(double angle) {
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        double[][] rX ={{1, 0, 0, 0}, {0, cos, sin, 0}, {0, -sin, cos, 0}, {0, 0, 0, 1}};
        double x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = vertices[i].getX();
            y = vertices[i].getY();
            z = vertices[i].getZ();
            
            vertices[i].setX((x * rX[0][0]) + (y * rX[1][0]) + (z * rX[2][0]) + (1 * rX[3][0]));
            vertices[i].setY((x * rX[0][1]) + (y * rX[1][1]) + (z * rX[2][1]) + (1 * rX[3][1]));
            vertices[i].setZ((x * rX[0][2]) + (y * rX[1][2]) + (z * rX[2][2]) + (1 * rX[3][2]));
        }
    }
    
    /**
     * rotateY method rotates the XYZ by an angle around the y-axis
     *
     * @param angle
     */
    public static void rotateY(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double[][] rX ={{cos, 0, -sin, 0}, {0, 1, 0, 0}, {sin, 0, cos, 0}, {0, 0, 0, 1}};
        double x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = vertices[i].getX();
            y = vertices[i].getY();
            z = vertices[i].getZ();
            
            vertices[i].setX((x * rX[0][0]) + (y * rX[1][0]) + (z * rX[2][0]) + (1 * rX[3][0]));
            vertices[i].setY((x * rX[0][1]) + (y * rX[1][1]) + (z * rX[2][1]) + (1 * rX[3][1]));
            vertices[i].setZ((x * rX[0][2]) + (y * rX[1][2]) + (z * rX[2][2]) + (1 * rX[3][2]));
        }
    }
    
    /**
     * rotateZ method rotates the XYZ by an angle around the z-axis
     *
     * @param angle
     */
    public static void rotateZ(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double[][] rX ={{cos, sin, 0, 0}, {-sin, cos, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        double x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = vertices[i].getX();
            y = vertices[i].getY();
            z = vertices[i].getZ();
            
            vertices[i].setX((x * rX[0][0]) + (y * rX[1][0]) + (z * rX[2][0]) + (1 * rX[3][0]));
            vertices[i].setY((x * rX[0][1]) + (y * rX[1][1]) + (z * rX[2][1]) + (1 * rX[3][1]));
            vertices[i].setZ((x * rX[0][2]) + (y * rX[1][2]) + (z * rX[2][2]) + (1 * rX[3][2]));
        }
    }
    
    /**
     * vertices getter
     */
    public static Point[] getvertices() {
        return vertices;
    }
    
    /**
     * x coord. setter
     *
     * @param x new x coord.
     */
    public static void setX(double x) {
        Cube.x = x;
    }
    
    /**
     * y coord. setter
     *
     * @param y new y coord.
     */
    public static void setY(double y) {
        Cube.y = y;
    }
    
    /**
     * z coord. setter
     *
     * @param z new z coord.
     */
    public static void setZ(double z) {
        Cube.z = z;
    }
    
    /**
     * d setter
     *
     * @param d new d value
     */
    public static void setD(double d) {
        Cube.d = d;
    }
    
    /**
     * s setter
     *
     * @param s new s value
     */
    public static void setS(double s) {
        Cube.s = s;
    }
}
