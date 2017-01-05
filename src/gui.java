import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.HashMap;

public class gui {

    int seadmeKogus = 0;
    int nextPossibleAddress = 1;
    int dmxUniverse = 1;
    double voolKokku = 0;
    String valitudSeadmeNimetus = "";

// Nupud - tekstiväljad
    final Button addButton = new Button("Lisa");
    final Button clearButton = new Button("Puhasta");
    final TextField vool = new TextField("");
    final TextField kanaleid = new TextField("");
    final TextField seadmeid = new TextField("1");
    final TextField vooluTarveKokku = new TextField("");
    final TextField linkeKokku = new TextField("");
    final TextField binaryAddress = new TextField("");
    private TableView <Device> table = new TableView();

// Massiivid
    private static ArrayList<Integer> universe = new ArrayList();
    private static ArrayList<Integer> aadressid = new ArrayList(); //Aadresside generaator patch listi jaoks
    static HashMap<String, Double> voolud = new HashMap<>();
    static HashMap<String, Integer> kanalid = new HashMap<>();
    ObservableList<Device> devicesToTable = FXCollections.observableArrayList();

    public gui(){
        startStage();
    }
//Defineeri stage
    private void startStage(){
        Stage stage = new Stage();
        stage.setTitle("Java projekt 2.0");
        Scene scene = new Scene(new Group());
        stage.setWidth(480);
        stage.setHeight(720);

        Image img = new Image("file:eventech.jpg");
        ImageView imgView = new ImageView(img);

        final ComboBox seadmeComboBox = new ComboBox(); // loo drop-down menüü
        FXCollections.sort(Device.nimed); // seadmete nimed tähestikujärjekorda
        seadmeComboBox.setItems(Device.nimed); //võta andmed ObservableListist

// Tabeli pealkiri
        final Label label = new Label("Patch list");
        label.setFont(new Font("Arial", 14));

        //Layout
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(imgView, 0, 0);
        grid.add(new Label("Vali seade: "), 0, 1);
        grid.add(seadmeComboBox, 0, 2);
        grid.add(new Label("Voolutarve/Võimsus"), 1, 0);
        grid.add(vool, 1, 1);
        grid.add(new Label("DMX kanaleid"), 1, 2);
        grid.add(kanaleid, 1, 3);
        grid.add(new Label("Mitu seadet: "), 0, 3);
        grid.add(seadmeid, 0, 4);
        grid.add(addButton, 0, 4);
        grid.add(clearButton, 0,4);
        grid.add(new Label("Voolutarve kokku"), 1, 4);
        grid.add(vooluTarveKokku, 1,5);
        grid.add(new Label("DMX linke"), 1,6);
        grid.add(linkeKokku, 1,7);
        grid.add(label, 0, 7);
        grid.add(table, 0, 8);
        grid.add(binaryAddress, 1, 8);
        grid.add(new Label("                     DIP switch"), 1,8);
        GridPane.setHalignment(addButton, HPos.CENTER);
        GridPane.setHalignment(clearButton, HPos.RIGHT);
        GridPane.setHalignment(binaryAddress, HPos.RIGHT );
        seadmeid.setMaxWidth(80);
        table.setMaxWidth(250);

        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();

// Nuppude action
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CharSequence textFieldValue = seadmeid.getCharacters();
                String kontroll = textFieldValue.toString();
                if (!kontroll.isEmpty() && kontroll.matches("\\d*") && !valitudSeadmeNimetus.isEmpty()){ //Kas rippemnüüst on seade valitud?
                    seadmeKogus = Integer.parseInt(textFieldValue.toString());
                    aadressid();
                    populateTable();
                    setVooluTarveKokku();
                    setLinkeKokku();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vali seade ja sisesta seadmete arv!", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
            }}});

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                clearAll();
                        }});

// Drop-down menüü valik
        seadmeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                valitudSeadmeNimetus = (String) seadmeComboBox.getSelectionModel().getSelectedItem();
//                System.out.println("ComboBox Action (selected: " + valitudSeadmeNimetus.toString() + ")");
                vooluTarbeAken();
                dmxKanaliteAken();
            }});
    }

// Voolutarbe aken
    private double vooluTarbeAken() {
        Device device = new Device(valitudSeadmeNimetus, voolud.get(valitudSeadmeNimetus), kanalid.get(valitudSeadmeNimetus));
        double voolutarve = device.getPower();
        int wattides = (int)voolutarve * 230;
        vool.setText(String.valueOf(voolutarve) + " A / " + String.valueOf(wattides) + " W");
        return voolutarve;
    }

// DMX kanalite aken
    private void dmxKanaliteAken() {
        Device device = new Device(valitudSeadmeNimetus, voolud.get(valitudSeadmeNimetus), kanalid.get(valitudSeadmeNimetus));
        int kanalitearv = device.getChannels();
        kanaleid.setText(String.valueOf(kanalitearv));
    }

// Arvutab kogu voolutarbe
    private void setVooluTarveKokku(){
        Device device = new Device(valitudSeadmeNimetus, voolud.get(valitudSeadmeNimetus), kanalid.get(valitudSeadmeNimetus));
        for (int i = 0; i < seadmeKogus ; i++){
        double voolutarve = device.getPower();
        voolKokku = voolKokku + voolutarve;
            int ymardatud = (int) voolKokku;
            int perFaas = ymardatud/3;
        vooluTarveKokku.setText(String.valueOf(ymardatud) + " A / " + perFaas + " A per faas");
    }}

// Leiab iga järgneva seadme aadressi ja universe
    private void aadressid(){
        int nextAddress = 1;
        int step = kanalid.get(valitudSeadmeNimetus);

        for (int i = 0; i < seadmeKogus ; i++) {
                  if (aadressid.isEmpty()) {
                    nextAddress = nextPossibleAddress;
                    nextPossibleAddress = nextPossibleAddress + step;
                      universe.add(dmxUniverse);
                } else if (nextPossibleAddress + step < 512){
                    nextAddress = nextPossibleAddress;
                    nextPossibleAddress = nextPossibleAddress + step;
                      universe.add(dmxUniverse);
                }
                    else {
                      nextPossibleAddress = 1;
                      dmxUniverse += 1;
                  }
                aadressid.add(nextAddress);
            }
//        System.out.println(Integer.toBinaryString(nextAddress));
//        System.out.println(aadressid);
//        System.out.println(universe);
            }

    private void setLinkeKokku(){
        int i = universe.size();
        linkeKokku.setText(String.valueOf(universe.get(i-1)));
    }

//ObservableList tabeli jaoks
    private ObservableList<Device> devicesToTable(){
        int listipikkus = devicesToTable.size();
        for (int i = listipikkus; i < listipikkus + seadmeKogus; i++) {
            devicesToTable.add(new Device(valitudSeadmeNimetus,voolud.get(valitudSeadmeNimetus) , aadressid.get(i)));

        }

        return devicesToTable;
    }

// Andmed tabelisse
    public void populateTable(){
        table.getColumns().clear();
        table.setEditable(false);

// Järjekorranumbrid
//http://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx
        TableColumn numberCol = new TableColumn("#");
        numberCol.setMinWidth(25);
        numberCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Device, Device>, ObservableValue<Device>>() {
            @Override
            public ObservableValue<Device> call(TableColumn.CellDataFeatures<Device, Device> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        numberCol.setCellFactory(new Callback<TableColumn<Device, Device>, TableCell<Device, Device>>() {
            @Override
            public TableCell<Device, Device> call(TableColumn<Device, Device> param) {
                return new TableCell<Device, Device>() {
                    @Override protected void updateItem(Device item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            int jrk = (this.getTableRow().getIndex()+1);
                            setText(String.valueOf(jrk));
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        numberCol.setSortable(false);

// Ülejäänud veerud
        TableColumn<Device, String> seadeTabelisse = new TableColumn("Nimetus");
        seadeTabelisse.setMinWidth(100);
        seadeTabelisse.setMaxWidth(100);
        TableColumn<Device, Integer> aadressTabelisse = new TableColumn("Aadress");
        aadressTabelisse.setMinWidth(55);
        aadressTabelisse.setMaxWidth(55);

        seadeTabelisse.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        aadressTabelisse.setCellValueFactory(
                new PropertyValueFactory<>("channels"));
        table.setItems(devicesToTable());
        table.getColumns().addAll(numberCol, seadeTabelisse, aadressTabelisse);


//Tabeli ridade selekteerimine http://stackoverflow.com/questions/17388866/getting-selected-item-from-a-javafx-tableview
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//Check whether item is selected and set value of selected item to Label
                try{
                if(table.getSelectionModel().getSelectedItem() != null)
                {
                    TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    String binary = Integer.toBinaryString((Integer) val);
                    while (binary.length()<9){
                        binary = binary+"0";
                    }
                    binaryAddress.setText(binary);
                } }
                catch (Exception e) {
                    System.out.println("Selekteeri aadressiväli!");
                }
            }
        });
    }

    public void  clearAll(){
        table.getColumns().clear();
        aadressid.clear();
        universe.clear();
        vooluTarveKokku.clear();
        linkeKokku.clear();
        seadmeid.clear();
        binaryAddress.clear();
        devicesToTable.removeAll();
        seadmeKogus = 0;
        nextPossibleAddress = 1;
        dmxUniverse = 1;
        voolKokku = 0;
        devicesToTable = FXCollections.observableArrayList();
    }
}