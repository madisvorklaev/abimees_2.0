import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class ain extends Application {
   static HashMap<String, Double> voolud = new HashMap<>();
   static HashMap<String, Integer> kanalid = new HashMap<>();
    public static ArrayList<Integer> aadressid = new ArrayList(); //Aadresside generaator patch listi jaoks

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

