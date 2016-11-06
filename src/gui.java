import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

    public class gui {

        int seadmeKogus = 0;
        String valitudSeadmeNimetus;

        // Nupud - tekstiväljad
        final Button button = new Button("Lisa");
        final TextField vool = new TextField("");
        final TextField kanaleid = new TextField("");
        final TextField seadmeid = new TextField("1");
        private TableView<device> patch = new TableView();

        public gui(){
            startStage();
        }

        //@Override
        private void startStage(){
            Stage stage = new Stage();
            stage.setTitle("Java projekt 2.0");
            Scene scene = new Scene(new Group());
            stage.setWidth(500);
            stage.setHeight(700);

            // Drop-down menüüd
            final ComboBox tootjaComboBox = new ComboBox();
            tootjaComboBox.getItems().addAll(
                    "SeeEiTöötaPraegu1",
                    "SeeEiTöötaPraegu2"
            );
            final ComboBox seadmeComboBox = new ComboBox();
            seadmeComboBox.setItems(device.nimed);


            // Tabeli pealkiri
            final Label label = new Label("Patch list");
            label.setFont(new Font("Arial", 16));

            // Tabel


            //Layout
            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setHgap(10);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("Vali tootja: "), 0, 0);
            grid.add(tootjaComboBox, 0, 1);
            grid.add(new Label("Vali seade: "), 0, 2);
            grid.add(seadmeComboBox, 0, 3);
            grid.add(new Label("Voolutarve/Võimsus"), 1, 0);
            grid.add(vool, 1, 1);
            grid.add(new Label("DMX kanaleid"), 1, 2);
            grid.add(kanaleid, 1, 3);
            grid.add(new Label("Mitu seadet: "), 0, 4);
            grid.add(seadmeid, 0, 5);
            grid.add(button, 1, 5);
            grid.add(label, 0, 7);
            grid.add(patch, 0, 8);

            Group root = (Group)scene.getRoot();
            root.getChildren().add(grid);
            stage.setScene(scene);
            stage.show();

            // Nupu action
            button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    CharSequence textFieldValue = seadmeid.getCharacters();
                    seadmeKogus = Integer.parseInt(textFieldValue.toString());
                    populateTable();
                    System.out.println(seadmeKogus);
                    System.out.println(valitudSeadmeNimetus);
                }});

            // Drop-down menüü valik
            seadmeComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    valitudSeadmeNimetus = (String) seadmeComboBox.getSelectionModel().getSelectedItem();
                    System.out.println("ComboBox Action (selected: " + valitudSeadmeNimetus.toString() + ")");
                    vooluTarbeAken();
                    dmxKanaliteAken();
                }});
        }

            // Voolutarbe aken
            private void vooluTarbeAken() {
            device device = new device(valitudSeadmeNimetus, ain.voolud.get(valitudSeadmeNimetus), ain.kanalid.get(valitudSeadmeNimetus));
            double voolutarve = device.getPower(valitudSeadmeNimetus);
            int wattides = (int)voolutarve * 230;
            vool.setText(String.valueOf(voolutarve) + " A / " + String.valueOf(wattides) + " W");
            }

        // DMX kanalite aken
        private void dmxKanaliteAken() {
            device device = new device(valitudSeadmeNimetus, ain.voolud.get(valitudSeadmeNimetus), ain.kanalid.get(valitudSeadmeNimetus));
            int kanalitearv = device.getChannels(valitudSeadmeNimetus);
            kanaleid.setText(String.valueOf(kanalitearv));
        }

        // Andmed tabelisse
        private void populateTable(){
            device device = new device(valitudSeadmeNimetus, ain.voolud.get(valitudSeadmeNimetus), ain.kanalid.get(valitudSeadmeNimetus));
            patch.setEditable(false);
            device.getName();
            String nimiTabelisse = String.valueOf(device.getName());

            final ObservableList<device> data =
                    FXCollections.observableArrayList(
                            new device(valitudSeadmeNimetus, ain.voolud.get(valitudSeadmeNimetus), ain.kanalid.get(valitudSeadmeNimetus))
              );


            TableColumn jrk = new TableColumn("Nr");
            TableColumn seadmeNimi = new TableColumn("Seadme nimi");
            seadmeNimi.setCellValueFactory(
                    new PropertyValueFactory<device, String>(valitudSeadmeNimetus));
            TableColumn aadress = new TableColumn("Aadress");

            patch.setItems(data);
            patch.getColumns().addAll(jrk, seadmeNimi, aadress);

        }



    }