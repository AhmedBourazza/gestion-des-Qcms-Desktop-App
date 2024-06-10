package qcmProjet;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

    public Test() {
        super();
        setOpaque(false); // make the panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Define the start and end points of the gradient
        Point2D startPoint = new Point2D.Float(0, 0);
        Point2D endPoint = new Point2D.Float(0, getHeight());

        // Define the color stops of the gradient
        Color[] colors = { Color.black, Color.WHITE, Color.gray };
        float[] stops = { 0f, 0.5f, 1.0f };

        // Create the gradient paint object
        GradientPaint gradient = new GradientPaint(startPoint, colors[0], endPoint, colors[2]);

        // Fill the panel with the gradient
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gradient Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        Test panel = new Test();
        frame.add(panel);
        frame.setVisible(true);
    }

}
