import javafx.application.Application;
import javafx.stage.Stage;

public class ain extends Application {
    // public static void main(String[] args) {
        @Override
        public void start(Stage primaryStage) throws Exception{

        readfile readfile = new readfile();

        //   device device = new device();
        readfile.openFile();
        readfile.readFile();
        readfile.closeFile();
        new gui();
        // device.prindi();
    }


}

