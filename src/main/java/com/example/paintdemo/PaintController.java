package com.example.paintdemo;

import shapes.Shape;
import shapes.Shapes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;

public class PaintController {

    Model model;

    @FXML
    private Canvas canvas;

    @FXML
    public Button UndoButton;

    @FXML
    public Button RedoButton;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    public RadioButton squareButton;

    @FXML
    public RadioButton circleButton;

    @FXML
    public RadioButton selectButton;

    @FXML
    private Spinner<Integer> shapeSize;

    public PaintController() {
    }

    public void initialize() {

        model = new Model();

        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        model.size.bindBidirectional(shapeSize.getValueFactory().valueProperty());

    }

    private void draw() {

        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

    public void canvasClicked(MouseEvent event) {

        if (selectButton.isSelected()) {
            ObservableList<Shape> temp = model.getTempList();
            model.undoDeque.addLast(temp);
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .reduce((first, second) -> second)
                    .ifPresent(shape -> shape.setColor(model.getColor()));
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .reduce((first, second) -> second)
                    .ifPresent(shape -> shape.setSize(shapeSize.getValue()));
        } else {
            ObservableList<Shape> temp = model.getTempList();
            if (squareButton.isSelected()) {
                model.undoDeque.addLast(temp);
                model.shapes.add(Shapes.squareOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
            } else if (circleButton.isSelected()) {
                model.undoDeque.addLast(temp);
                model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
            }
        }
        draw();
    }

    public void undo() {
        model.undo();
        draw();
    }

    public void redo() {
        model.redo();
        draw();
    }

    public void onSave() {
        SvgConverter svgConverter = new SvgConverter();
        svgConverter.saveSVGFile(model);
    }
}