package shapes;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public final class Circle extends Shape {
    private double radius;

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y, radius);
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillOval(getX()-radius, getY()-radius,2*radius,2*radius);
    }

    @Override
    public boolean isInside(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;
        return distanceFromCircleCenterSquared < radius*radius;
    }

    public Circle(Circle shape) {
        super(shape);
        this.radius = shape.radius;
    }

    @Override
    public String drawSVG() {
        String convertColor = "#" + getColor().toString().substring(2, 10);
        return "<circle cx=\"" + getX() + "\" " +
                "cy=\"" + getY() + "\" " +
                "r=\"" + radius + "\" " +
                "fill=\"" + convertColor + "\" />";
    }
    @Override
    public void setSize(double radius) {this.radius = radius;}

    @Override
    public Shape copyOf() {return new Circle(this);}
}