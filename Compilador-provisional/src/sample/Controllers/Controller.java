package sample.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import static  sample.Constants.Configs.*;

public class Controller extends Application{
private Stage stage;
@FXML private HBox PaneCod;


    private static final String sampleCode = String.join("\n", new String[] {
            "package com.example;",
            "",
            "import java.util.*;",
            "",
            "public class Foo extends Bar implements Baz {",
            "",
            "    /*",
            "     * multi-line comment",
            "     */",
            "    public static void main(String[] args) {",
            "        // single-line comment",
            "        for(String arg: args) {",
            "            if(arg.length() != 0)",
            "                System.out.println(arg);",
            "            else",
            "                System.err.println(\"Warning: empty string as argument\");",
            "        }",
            "    }",
            "",
            "}"
    });
    CodeArea codeArea = new CodeArea();
@FXML protected void initialize(){
    // add line numbers to the left of area
    codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
    codeArea.replaceText(0, 0, sampleCode);
    codeArea.setPrefSize(1000,500);
    Subscription cleanupWhenNoLongerNeedIt = codeArea
            .multiPlainChanges()
            .successionEnds(Duration.ofMillis(500))
            .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText())));


HBox.setHgrow(codeArea, Priority.ALWAYS);
    PaneCod.getChildren().add(codeArea);
}//llave load
public void evtsalir(ActionEvent event){
    System.exit(0);
}
public void evtabrir(ActionEvent event){
    FileChooser of=new FileChooser();
    of.setTitle("Abrir archivo Compiler");
    FileChooser.ExtensionFilter filtro=new FileChooser.ExtensionFilter("Archivos.ac","*.ac");
    of.getExtensionFilters().add(filtro);
    File file=of.showOpenDialog(stage);
}


    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
    }

}
