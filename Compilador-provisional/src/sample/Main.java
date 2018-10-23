package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Controllers.Splash;

public class Main extends Application {
    public static int duracion=20000;
    public static int steps=1;

    @Override
    public void init() throws Exception {
        for(int i=0; i<duracion;i++){
            double progress=(100*i)/duracion;
            LauncherImpl.notifyPreloader(this, new Splash.ProgressNotification(progress));
        }//llave for
    }//llave init

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/principal.fxml"));
        primaryStage.setTitle("Convertidor de Sistemas Numéricos");
        primaryStage.setScene(new Scene(root, 960, 600));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "../Imagenes/ICONITO.png")));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class,Splash.class,args);
        //launch(args);
    }
}
