/**
 * DrawinStuff.java
 *
 * @author ryanperkins
 */
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * DrawinStuff.class
 */
public class DrawinStuff extends JPanel {
    // lines
    private static ArrayList<Shape> shapes = new ArrayList<Shape>();
    
    /** paintComponent method overrides the parent method to draw lines
     *
     * @param g graphics to be drawn
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.drawLine(0, 384, 768, 384); // x-axis
        g.drawLine(384, 0, 384, 768); // y-axis
        g.setColor(Color.black);
        
        for(int i = 0; i < shapes.size(); i++) {
            shapes.get(i).drawShape(g);
        }
    }
    
    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        // scanner initialized
        Scanner scan = new Scanner(System.in);
        
        // window is created
        JFrame f = new JFrame("DrawinStuff");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(768, 768));
        f.pack();
        
        // panel with all drawn lines is created and added
        DrawinStuff canvas = new DrawinStuff();
        f.add(canvas);
        
        // menu
        JMenuBar menuBar = new JMenuBar();
        
        /* draw menu */
        JMenu draw = new JMenu("draw");
        menuBar.add(draw);
        
        // draw cube
        JMenuItem drawCube = new JMenuItem("drawCube");
        drawCube.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Point[] vertices = new Point[8];
                
                JTextField x = new JTextField(5);
                JTextField y = new JTextField(5);
                JTextField z = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("x:"));
                info.add(x);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("y:"));
                info.add(y);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("z:"));
                info.add(z);
                
                for(int i = 0; i < 8; i ++) {
                    int result = JOptionPane.showConfirmDialog(null, info, "drawCube", JOptionPane.OK_CANCEL_OPTION);
                    
                    if(result == JOptionPane.OK_OPTION) {
                        vertices[i] = new Point(Double.parseDouble(x.getText()), Double.parseDouble(y.getText()), Double.parseDouble(z.getText()));
                    }
                    
                    System.out.println("" + i + ":   x-" + vertices[i].getX() + ", y-" + vertices[i].getY() + ", z-" + vertices[i].getZ());
                    
                    x.setText("");
                    y.setText("");
                    z.setText("");
                }
                
                shapes.add(new Cube(vertices[0], vertices[1], vertices[2], vertices[3], vertices[4], vertices[5], vertices[6], vertices[7]));
                canvas.repaint();
            }
        });
        draw.add(drawCube);
        
        // default cube
        shapes.add(new Cube(new Point(-1, 1, -1), new Point(1, 1, -1), new Point(1, -1, -1), new Point(-1, -1, -1), new Point(-1, 1, 1), new Point(1, 1, 1), new Point(1, -1, 1), new Point(-1, -1, 1)));
        
        // draw lines
        JMenuItem drawLine = new JMenuItem("drawLine");
        drawLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Point[] vertices = new Point[2];
                
                JTextField x0 = new JTextField(5);
                JTextField y0 = new JTextField(5);
                JTextField x1 = new JTextField(5);
                JTextField y1 = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("x0:"));
                info.add(x0);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("y0:"));
                info.add(y0);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("x1:"));
                info.add(x1);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("y1:"));
                info.add(y1);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                               "drawLine", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    vertices[0] = new Point(Double.parseDouble(x0.getText()), Double.parseDouble(y0.getText()));
                    vertices[1] = new Point(Double.parseDouble(x1.getText()), Double.parseDouble(y1.getText()));
                }
                
                x0.setText("");
                y0.setText("");
                x1.setText("");
                y1.setText("");
                
                shapes.add(new Line(vertices[0], vertices[1]));
                canvas.repaint();
            }
        });
        draw.add(drawLine);
        /* end draw menu */
        
        /* edit menu */
        JMenu edit = new JMenu("edit");
        menuBar.add(edit);
        
        // change viewpoint
        JMenuItem viewpoint = new JMenuItem("viewpoint");
        viewpoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField x = new JTextField(5);
                JTextField y = new JTextField(5);
                JTextField z = new JTextField(5);
                JTextField d = new JTextField(5);
                JTextField s = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("x:"));
                info.add(x);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("y:"));
                info.add(y);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("z:"));
                info.add(z);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("d:"));
                info.add(d);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("s:"));
                info.add(s);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                           "viewpoint", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    Cube.setX(Double.parseDouble(x.getText()));
                    Cube.setY(Double.parseDouble(y.getText()));
                    Cube.setZ(Double.parseDouble(z.getText()));
                    Cube.setD(Double.parseDouble(d.getText()));
                    Cube.setS(Double.parseDouble(s.getText())/2);
                }
                
                canvas.repaint();
            }
        });
        edit.add(viewpoint);
        
        // translate
        JMenuItem translate = new JMenuItem("translate");
        translate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField a = new JTextField(5);
                JTextField b = new JTextField(5);
                JTextField c = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("Tx:"));
                info.add(a);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("Ty:"));
                info.add(b);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("Tz:"));
                info.add(c);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                           "translate", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    Cube.translate(Double.parseDouble(a.getText()), Double.parseDouble(b.getText()), Double.parseDouble(c.getText()));
                }
                
                canvas.repaint();
            }
        });
        edit.add(translate);
        
        // scale
        JMenuItem scale = new JMenuItem("scale");
        scale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField a = new JTextField(5);
                JTextField b = new JTextField(5);
                JTextField c = new JTextField(5);
                JTextField sX = new JTextField(5);
                JTextField sY = new JTextField(5);
                JTextField sZ = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("Sx:"));
                info.add(a);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("Sy:"));
                info.add(b);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("Sz:"));
                info.add(c);
                info.add(new JLabel("x:"));
                info.add(sX);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("y:"));
                info.add(sY);
                info.add(Box.createHorizontalStrut(15));
                info.add(new JLabel("z:"));
                info.add(sZ);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                           "scale", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    double x = Double.parseDouble(sX.getText());
                    double y = Double.parseDouble(sY.getText());
                    double z = Double.parseDouble(sZ.getText());
                    
                    Cube.translate(-x, -y, -z);
                    Cube.scale(Double.parseDouble(a.getText()), Double.parseDouble(b.getText()), Double.parseDouble(c.getText()));
                    Cube.translate(x, y, z);
                }
                
                canvas.repaint();
            }
        });
        edit.add(scale);
        
        // rotateX
        JMenuItem rotateX = new JMenuItem("rotateX");
        rotateX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField angleX = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("angleX:"));
                info.add(angleX);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                           "rotateX", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    Cube.rotateX(Double.parseDouble(angleX.getText()));
                }
                
                canvas.repaint();
            }
        });
        edit.add(rotateX);
        
        // rotateY
        JMenuItem rotateY = new JMenuItem("rotateY");
        rotateY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField angleY = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("angleY:"));
                info.add(angleY);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                           "rotateY", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    Cube.rotateY(Double.parseDouble(angleY.getText()));
                }
                
                canvas.repaint();
            }
        });
        edit.add(rotateY);
        
        // rotateZ
        JMenuItem rotateZ = new JMenuItem("rotateZ");
        rotateZ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField angleZ = new JTextField(5);
                
                JPanel info = new JPanel();
                info.add(new JLabel("angleZ:"));
                info.add(angleZ);
                
                int result = JOptionPane.showConfirmDialog(null, info,
                                                           "rotateZ", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION) {
                    Cube.rotateZ(Double.parseDouble(angleZ.getText()));
                }
                
                canvas.repaint();
            }
        });
        edit.add(rotateZ);
        
        // clear
        JMenuItem clear = new JMenuItem("clear");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                canvas.repaint();
            }
        });
        edit.add(clear);
        /* end transform menu */
        
        f.setJMenuBar(menuBar);
        
        f.setVisible(true);
    }
}
