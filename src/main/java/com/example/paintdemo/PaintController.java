package com.example.paintdemo;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


import javax.imageio.ImageIO;
import java.io.File;


public class PaintController {

    Model model;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button rectangleButton;
    @FXML
    private Button circleButton;
    @FXML
    private Button lineButton;
    @FXML
    private Button pointButton;

    @FXML
    private Spinner<Integer> shapeSize;

    @FXML
    private CheckBox eraser;

//    public HelloController() {
//    }
//
//    public HelloController(Model model) {
//        this.model = model;
//    }

    public void initialize() {

        model = new Model();

        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());

        model.size.bindBidirectional(shapeSize.getValueFactory().valueProperty());
    }

    private void draw() {
        var gc = canvas.getGraphicsContext2D();
        //double size = Double.parseDouble(shapeSize.getText());
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        System.out.println(model.shapes);
        System.out.println(model.getSize());


        for (var shape : model.shapes) {
            gc.setFill(shape.getColor());
            gc.fillOval(shape.getX(), shape.getY(), model.getSize(), model.getSize());
        }
    }

    public void onSave() {
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("Artwork.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image" + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

    public void canvasClicked(MouseEvent event) {
//        var context = canvas.getGraphicsContext2D();
//        canvas.setOnMousePressed(e -> paintAction(context, e));
        model.shapes.add(new Shape(model.getColor(), event.getX(), event.getY()));


        draw();

    }

//    private void paintAction(GraphicsContext context, MouseEvent e) {
//        double size = Double.parseDouble(shapeSize.getText());
//        double x = e.getX() - size / 2;
//        double y = e.getY() - size / 2;
//
//        if (eraser.isSelected()) {
//            context.clearRect(x, y, size, size); //Suddigum, kanske inte behövs till labb.
//        } else {
//            context.setFill(colorPicker.getValue());
//            context.fillOval(x, y, size, size);
//        }
//    }

//    public void draggedOnCanvas() {
//        var context = canvas.getGraphicsContext2D();
//        canvas.setOnMouseDragged(e -> paintAction(context, e));
//    }

    //För att ta bort? eller använda till att justera shapes?
//    public void clickDelete(ActionEvent event) {
//        personView.getItems().remove(personView.getSelectionModel().getSelectedItem());
//    }

}