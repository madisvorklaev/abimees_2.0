import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Device {
    public String name;
    public Double power;
    public Integer channels;

    static ObservableList<String> nimed = FXCollections.observableArrayList(); //rippmenyy sisu

    public Device(String name, Double power, Integer channels) {
        this.name = name;
        this.power = power;
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        nimed.add(name);
        this.name = name;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        ain.voolud.put(name, power); //väärtus HashMapi, andmete talletamiseks
        this.power = power;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        ain.kanalid.put(name, channels); //väärtus HashMapi, andmete talletamiseks
        this.channels = channels;
    }
}
