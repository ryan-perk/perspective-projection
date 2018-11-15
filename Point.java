/**
 * Point.java
 *
 * @author ryanperkins
 */
import java.io.*;
import java.awt.Graphics;

/**
 * Point.class
 */
class Point extends Shape {
    // x coordinate
    private double x;
    // y coordinate
    private double y;
    // z coordinate
    private double z;
    
    /**
     * 2D point constructor
     *
     * @param x start x coordinate
     * @param y start y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 3D point constructor
     *
     * @param x start x coordinate
     * @param y start y coordinate
     * @param z start z coordinate
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * drawShape method draws a line
     *
     * @param g graphics package
     */
    public void drawShape(Graphics g) {
        g.drawLine((int)this.getX(), (int)this.getY(), (int)this.getX(), (int)this.getY());
    }
    
    /**
     * x coord. setter
     *
     * @param x new x coord.
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * y coord. setter
     *
     * @param y new y coord.
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * z coord. setter
     *
     * @param z new z coord.
     */
    public void setZ(double z) {
        this.z = z;
    }
    
    /**
     * x coord. getter
     */
    public double getX() {
        return x;
    }
    
    /**
     * y coord. getter
     */
    public double getY() {
        return y;
    }
    
    /**
     * z coord. getter
     */
    public double getZ() {
        return z;
    }
}
