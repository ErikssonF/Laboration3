package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Square extends Shape {
    private double size;

    public Square(Color color, double x, double y, double size) {
        super(color, x, y, size);
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        double halfSize = size * 0.5;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(getX() - halfSize, getY() - halfSize, size, size);
    }

    @Override
    public boolean isInside(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;
        return distanceFromCircleCenterSquared < size*size;
    }

    public Square(Square shape) {
        super(shape);
        this.size = shape.size;
    }

    @Override
    public String drawSVG() {
        String convertColor = "#" + getColor().toString().substring(2, 10);
        return "<rect x=\"" + (getX() - size) + "\" " +
                "y=\"" + (getY() - size) + "\" " +
                "width=\"" + (2 * size) + "\" " +
                "height=\"" + (2* size) + "\" " +
                "fill=\"" + convertColor + "\" />";
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public Shape copyOf() {return new Square(this);}
}