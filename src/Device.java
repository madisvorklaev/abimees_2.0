import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Device {
    private String name;
    private double power;
    private int channels;
    private String Power = String.valueOf(power);
    private String Channels = String.valueOf(channels);
    public final SimpleStringProperty nimiTabelisse;
    public final SimpleStringProperty voolTabelisse;
    public final SimpleStringProperty kanalidTabelisse;
    static ObservableList<String> nimed = FXCollections.observableArrayList(); //rippmenyy sisu


    public Device(String nimi, Double vool, Integer kanal){
        this.name = nimi;
        this.power = vool;
        this.channels = kanal;
        this.nimiTabelisse = new SimpleStringProperty(name);
        this.voolTabelisse = new SimpleStringProperty(Power);
        this.kanalidTabelisse = new SimpleStringProperty(Channels);
    }



    public void setName(String nimi){ //väärtus rippmenüü ObservableListi
           name = nimi;
        nimed.add(nimi);
    }

    public void setPower(double vool){ //väärtus HashMapi, andmete talletamiseks
           power = vool;
        ain.voolud.put(name, power);
    }

    public void setChannels(int kanal){ //väärtus HashMapi, andmete talletamiseks
        channels = kanal;
        ain.kanalid.put(name, channels);
    }

    /*public SimpleStringProperty getName(){
        System.out.println(nimiTabelisse);
        return nimiTabelisse;
        }
*/  public String getName(String valitudSeadmeNimetus) { //tabeli sisu jaoks
        String thisName = valitudSeadmeNimetus;
        System.out.println(thisName);
        return thisName;
            }


    public double getPower(String valitudSeadmeNimetus){    //tabeli sisu jaoks
       double thisPower = ain.voolud.get(valitudSeadmeNimetus);
        System.out.println(thisPower);
       return thisPower;

    }
    public int getChannels(String valitudSeadmeNimetus){    //tabeli sisu jaoks
        int thisChannels = ain.kanalid.get(valitudSeadmeNimetus);
        System.out.println(thisChannels);
        return thisChannels;
    }


}
