package com.example.paintdemo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import shapes.Circle;
import shapes.Shape;
import shapes.Shapes;
import shapes.Square;

import java.util.ArrayDeque;
import java.util.Deque;

public class Model {
    private final ObjectProperty<Color> color;
    public final ObjectProperty<Integer> size;

    ObservableList<Shape> shapes = FXCollections.observableArrayList();

    Deque<ObservableList<Shape>> redo = new ArrayDeque<>();
    Deque<ObservableList<Shape>> undo = new ArrayDeque<>();

    public Model() {
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleObjectProperty<>(1);
    }

    public ObservableList<Shape> getTempList() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();

        for (Shape shape : shapes) {
            tempList.add(shape.copyOf());
        }
        return tempList;
    }

    public Integer getSize() {
        return size.get();
    }

    public ObjectProperty sizeProperty() {
        return size;
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }
}
