package com.example.paintdemo;

import shapes.Shape;
import java.util.List;
import javafx.stage.Stage;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import javafx.stage.FileChooser;

public class SvgConverter {
    private final static FileChooser fileChooser = new FileChooser();

    public void saveSVGFile(Model model) {
        fileChooser.setTitle("Save to SVG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVGFile", "*.svg"));
        Path path = Path.of(fileChooser.showSaveDialog(new Stage()).toURI());
        List<String> strings = new ArrayList<>();
        buildSVGString(model, strings);
        try {
            Files.write(path , strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String startOfSVGString() {
        return String.join(" ",
                "<svg",
                "xmlns=\"http://www.w3.org/2000/svg\"",
                "version=\"1.1\"",
                "width=\"800.0\"",
                "height=\"800.0\">");
    }
    private static void buildSVGString(Model model, List<String> strings) {
        strings.add(startOfSVGString());
        model.shapes.forEach(shape -> shapeSVGInfoToString(shape, strings));
        strings.add(endOfSVGString());
    }

    private static String endOfSVGString() {
        return "</svg>";
    }

    private static void shapeSVGInfoToString(Shape shape, List<String> strings) {
        strings.add(String.join(" ", shape.drawSVG()));
    }
}