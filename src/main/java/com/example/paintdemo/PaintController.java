package com.example.paintdemo;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import shapes.Shape;
import shapes.Shapes;

import javax.imageio.ImageIO;
import java.io.File;


public class PaintController {

    Model model;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    public RadioButton squareButton;

    @FXML
    public RadioButton circleButton;

    @FXML
    public RadioButton selectButton;

    @FXML
    public Button undoButton;

    @FXML
    private Spinner<Integer> shapeSize;

    public PaintController() {
    }

    public PaintController(Model model) {
        this.model = model;
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

    public void onSave() {
        SvgConverter svgConverter = new SvgConverter();
        svgConverter.saveSVGFile(model);
    }

    public void canvasClicked(MouseEvent event) {

        if (selectButton.isSelected()) {
            ObservableList<Shape> temp = model.getTempList();
            model.undo.addLast(temp);
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
                model.undo.addLast(temp);
                model.shapes.add(Shapes.squareOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
            } else if (circleButton.isSelected()) {
                model.undo.addLast(temp);
                model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
            }
        }
        draw();
    }

//    public void redo() {
//        if (model.redo.isEmpty())
//            return;
//        ObservableList<Shape> temp = model.getTempList();
//        model.undo.addLast(temp);
//        model.shapes = model.redo.removeLast();
//        System.out.println(model.undo);
//        draw();
//    }

    public void undo() {
        if (model.undo.isEmpty())
            return;
//        ObservableList<Shape> temp = model.getTempList();
//        model.redo.addLast(temp);

        model.shapes = model.undo.removeLast();
        draw();
    }

}