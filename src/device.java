import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class device {
    private String name;
    private double power;
    private int channels;
    private String Power = String.valueOf(power);
    private String Channels = String.valueOf(channels);
    public final SimpleStringProperty nimiTabelisse;
    private final SimpleStringProperty voolTabelisse;
    private final SimpleStringProperty kanalidTabelisse;
    static ObservableList<String> nimed = FXCollections.observableArrayList();

   
    public device(String nimi, double vool, int kanal){
        this.name = nimi;
        this.power = vool;
        this.channels = kanal;
        this.nimiTabelisse = new SimpleStringProperty(name);
        this.voolTabelisse = new SimpleStringProperty(Power);
        this.kanalidTabelisse = new SimpleStringProperty(Channels);
    }

    public void setName(String nimi){
           name = nimi;
        nimed.add(nimi);
    }

    public void setPower(double vool){
           power = vool;
        ain.voolud.put(name, power);
    }

    public void setChannels(int kanal){
        channels = kanal;
        ain.kanalid.put(name, channels);
    }

    public SimpleStringProperty getName(){
        System.out.println(nimiTabelisse);
        return nimiTabelisse;

    }
    public double getPower(String valitudSeadmeNimetus){
       double thisPower = ain.voolud.get(valitudSeadmeNimetus);
       return thisPower;

    }
    public int getChannels(String valitudSeadmeNimetus){
        int thisChannels = ain.kanalid.get(valitudSeadmeNimetus);
        return thisChannels;
    }


}
