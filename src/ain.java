import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class ain extends Application {
   static HashMap<String, Double> voolud = new HashMap<>();
   static HashMap<String, Integer> kanalid = new HashMap<>();
    // public static void main(String[] args) {
        @Override
        public void start(Stage primaryStage) throws Exception{

        readfile readfile = new readfile();
        readfile.openFile();
        readfile.readFile();
        readfile.closeFile();
        new gui();

    }


}

