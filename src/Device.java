import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Device {
    private String name;
    private Double power;
    private Integer channels;
    /*private String Power = String.valueOf(power);
    private String Channels = String.valueOf(channels);
    public final SimpleStringProperty nimiTabelisse;
    public final SimpleStringProperty voolTabelisse;
    public final SimpleStringProperty kanalidTabelisse; */
    static ObservableList<String> nimed = FXCollections.observableArrayList(); //rippmenyy sisu


    public Device() {
        this.name = "";
        this.power = 0.0;
        this.channels = 0;
    }

    public Device(String name, double power, int channels){
        this.name = name;
        this.power = power;
        this.channels = channels;
       /* this.nimiTabelisse = new SimpleStringProperty(name);
        this.voolTabelisse = new SimpleStringProperty(Power);
        this.kanalidTabelisse = new SimpleStringProperty(Channels); */
    }



    void setName(String name){ //väärtus rippmenüü ObservableListi
        this.name = name;
        nimed.add(name);
    }

    void setPower(Double power){
        this.power = power;
        ain.voolud.put(name, power); //väärtus HashMapi, andmete talletamiseks
    }

    void setChannels(Integer channels){
        this.channels = channels;
        ain.kanalid.put(name, channels); //väärtus HashMapi, andmete talletamiseks
    }

    /*public SimpleStringProperty getName(){
        System.out.println(nimiTabelisse);
        return nimiTabelisse;
        }
*/  public String getName() { //tabeli sisu jaoks
        //String name = valitudSeadmeNimetus;
        System.out.println("getName "+ name);
        return name;
            }


    double getPower(Double power){    //tabeli sisu jaoks
       //double power = ain.voolud.get(valitudSeadmeNimetus);
        System.out.println("getPower" + power);
       return power;

    }
    int getChannels(Integer channels){    //tabeli sisu jaoks
        //int thisChannels = ain.kanalid.get(valitudSeadmeNimetus);
        System.out.println("getChannels" + channels);
        return channels;
    }


}
