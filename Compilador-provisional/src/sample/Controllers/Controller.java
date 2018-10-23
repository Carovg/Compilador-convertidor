package sample.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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
import sample.Constants.Configs;

import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static sample.Constants.Configs.*;
public class Controller extends Application {
    private Stage stage;
    @FXML
    private HBox paneSote;

    CodeArea codeArea = new CodeArea();
@FXML TextArea txtconsola;

    @FXML
    protected void initialize() {
        // add line numbers to the left of area
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.setPrefSize(950, 400);
        codeArea.replaceText(0, 0, sampleCode);
        Subscription cleanupWhenNoLongerNeedIt = codeArea
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText())));
        HBox.setHgrow(codeArea, Priority.ALWAYS);
        paneSote.getChildren().add(codeArea);
    }//load


    public void evtsalir(ActionEvent event) {
        System.exit(0);
    }

    public void evtabrir(ActionEvent event) {
        FileChooser of = new FileChooser();
        of.setTitle("Abrir archivo Compiler");
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos.ac", "*.ac");
        of.getExtensionFilters().add(filtro);
        File file = of.showOpenDialog(stage);
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
    }

public void ejecutar(ActionEvent event){

compilar();

}//llave ejecutar

public void compilar(){
        txtconsola.setText("");
        long tinicial=System.currentTimeMillis();
        String texto=codeArea.getText();
        String[] renglones=texto.split("\\n");

        for(int x=0;x<renglones.length; x++){
            boolean bandera=false;
            if(!renglones[x].trim().equals("")){

                for(int y=0;y< Configs.EXPRESIONES.length && bandera==false;y++){
                    Pattern patron=Pattern.compile(Configs.EXPRESIONES[y]);
                    Matcher matcher=patron.matcher(renglones[x]);
                    if(matcher.matches()){
                        bandera=true;
                    }
                }//llave for y
                if(bandera==false){
                    txtconsola.setText(txtconsola.getText()+ " \n "+ "Error de sintaxis en la linea" +(x+1));
                }

            }

        }//llave for x}
    long tfinal=System.currentTimeMillis()-tinicial;
        txtconsola.setText(txtconsola.getText()+"\n"+"Compilado en : " + tfinal + " milisegundos ");
}//llave compilar

}
