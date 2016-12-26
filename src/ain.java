import javafx.application.Application;
import javafx.stage.Stage;

public class ain extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

        readfile readfile = new readfile();
        readfile.openFile();
        readfile.readFile();
        readfile.closeFile();
        new gui();
            //System.out.println(Device.nimed);
            //System.out.println(voolud);

    }


}

