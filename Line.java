/**
 * Line.java
 *
 * @author ryanperkins
 */
import java.io.*;
import java.awt.Graphics;

/**
 * Line.class
 */
class Line extends Shape {
    // vertices
    private static Point[] vertices;
    
    /**
     * line constructor
     *
     * @param A 1st endpoint
     * @param B 2nd endpoint
     */
    public Line(Point A, Point B) {
        vertices = new Point[]{A, B};
    }
    
    /**
     * drawShape method draws a line
     *
     * @param g graphics package
     */
    public void drawShape(Graphics g) {
        int x0 = (int)super.convertX(this.vertices[0].getX()); // convert x0 to XY
        int y0 = (int)super.convertY(this.vertices[0].getY()); // convert y0 to XY
        int x1 = (int)super.convertX(this.vertices[1].getX()); // convert x1 to XY
        int y1 = (int)super.convertY(this.vertices[1].getY()); // convert y1 to XY
        
        g.drawLine(x0, y0, x1, y1);
    }
    
    /**
     * translate method translates the XYZ by a, b, c, respectively
     *
     * @param a x translation factor
     * @param b y translation factor
     * @param c z translation factor
     */
    public static void translate(double a, double b, double c) {
        double[][] s ={{a, 0, 0, 0}, {0, b, 0, 0}, {0, 0, c, 0}, {0, 0, 0, 1}};
        int x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = (int)vertices[i].getX();
            y = (int)vertices[i].getY();
            z = (int)vertices[i].getZ();
            
            vertices[i].setX((x * s[0][0]) + (y * s[1][0]) + (z * s[2][0]) + (1 * s[3][0]));
            vertices[i].setY((x * s[0][1]) + (y * s[1][1]) + (z * s[2][1]) + (1 * s[3][1]));
            vertices[i].setZ((x * s[0][2]) + (y * s[1][2]) + (z * s[2][2]) + (1 * s[3][2]));
        }
    }
    
    /**
     * scale method scales the XYZ by a, b, c, respectively
     *
     * @param a x scaling factor
     * @param b y scaling factor
     * @param c z scaling factor
     */
    public static void scale(double a, double b, double c) {
        double[][] s ={{a, 0, 0, 0}, {0, b, 0, 0}, {0, 0, c, 0}, {0, 0, 0, 1}};
        int x = 0, y = 0, z = 0;
        
        for(int i = 0; i < vertices.length; i++) {
            x = (int)vertices[i].getX();
            y = (int)vertices[i].getY();
            z = (int)vertices[i].getZ();
            
            vertices[i].setX((x * s[0][0]) + (y * s[1][0]) + (z * s[2][0]) + (1 * s[3][0]));
            vertices[i].setY((x * s[0][1]) + (y * s[1][1]) + (z * s[2][1]) + (1 * s[3][1]));
            vertices[i].setZ((x * s[0][2]) + (y * s[1][2]) + (z * s[2][2]) + (1 * s[3][2]));
        }
    }
    
    /**
     * rotate method rotates the XYZ by an angle
     *
     * @param a x scaling factor
     * @param b y scaling factor
     * @param c z scaling factor
     */
    public static void rotate(double a, double b, double c) { }
}
