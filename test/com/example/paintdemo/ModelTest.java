package com.example.paintdemo;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapes.Shapes;
import static org.assertj.core.api.Assertions.assertThat;

class ModelTest {

    Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
    }

    @Test
    void addSquareToArrayDequeAndChecksIfArrayDequeIsEqualToOne() {
        model.shapes.add(Shapes.squareOf(1,1,5, Color.BLACK));
        assertThat(model.shapes.size()).isEqualTo(1);
    }

    @Test
    void addCircleToArrayDequeAndChecksIfArrayDequeIsEqualToOne() {
        model.shapes.add(Shapes.circleOf(1,1,5, Color.BLACK));
        assertThat(model.shapes.size()).isEqualTo(1);
    }

    @Test
    void getSizeShouldReturnSize() {

        assertThat(model.getSize().equals(model.size));
    }

    @Test
    void getColorShouldReturnColor() {

        assertThat(model.getSize().equals(model.size));
    }
}