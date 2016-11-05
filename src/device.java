import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class device {
    private String name;
    private double power;
    private int channels;
    static ObservableList<String> nimed = FXCollections.observableArrayList();
    static ObservableList<Double> voolud = FXCollections.observableArrayList();
    static ObservableList<Integer> kanalid = FXCollections.observableArrayList();


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
            voolud.add(vool);
        //System.out.println(power);
    }
    public void setChannels(int kanal){
           channels = kanal;
            kanalid.add(kanal);
        //System.out.println(channels);
    }

    public String getName(){
        return name;
    }
    public double getPower(){
        return power;
    }
    public int getChannels(){
        return channels;
    }

   public void prindi(){
        System.out.println(getName() + " "+ getPower()+" "+getChannels());
    }

}
