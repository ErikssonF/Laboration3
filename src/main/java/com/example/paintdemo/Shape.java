package com.example.paintdemo;

import javafx.scene.paint.Color;

public class Shape {
    private Color color;
    private final double x;
    private final double y;


    public Shape(Color color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
