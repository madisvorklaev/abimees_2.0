import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class device {
    private String name;
    private double power;
    private int channels;
    static ObservableList<String> nimed = FXCollections.observableArrayList();
   // static ObservableList<Double> voolud = FXCollections.observableArrayList();
   // static ObservableList<Integer> kanalid = FXCollections.observableArrayList();


    public device(String nimi, double vool, int kanal){
        this.name = nimi;
        this.power = vool;
        this.channels = kanal;

    }



    public void setName(String nimi){
           name = nimi;
        nimed.add(nimi);
        //System.out.println(name);
    }

    public void setPower(double vool){
           power = vool;
        ain.voolud.put(name, power);
    }

    public void setChannels(int kanal){
        channels = kanal;
        ain.kanalid.put(name, channels);
    }

    public String getName(){
        return name;
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
