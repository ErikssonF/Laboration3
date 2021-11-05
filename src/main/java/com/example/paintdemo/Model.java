package com.example.paintdemo;

import shapes.Shape;
import java.util.Deque;
import java.util.ArrayDeque;
import javafx.beans.property.*;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private final ObjectProperty<Color> color;
    public final ObjectProperty<Integer> size;

    ObservableList<Shape> shapes = FXCollections.observableArrayList();

    Deque<ObservableList<Shape>> redoDeque = new ArrayDeque<>();
    Deque<ObservableList<Shape>> undoDeque = new ArrayDeque<>();

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

    public void redo() {
        if (redoDeque.isEmpty())
            return;
        ObservableList<Shape> temp = getTempList();
        undoDeque.addLast(temp);
        shapes = redoDeque.removeLast();
    }

    public void undo() {
        if (undoDeque.isEmpty())
            return;
        ObservableList<Shape> temp = getTempList();
        redoDeque.addLast(temp);

        shapes = undoDeque.removeLast();
    }

    public Color getColor() {return color.get();}
    public ObjectProperty<Color> colorProperty() {return color;}

    public Integer getSize() {return size.get();}

}
