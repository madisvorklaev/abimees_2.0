import java.io.File;
import java.util.Scanner;

public class readfile {
    private String name;
    private double power;
    private int channels;


    private Scanner x;

    public void openFile() {
        try {
            x = new Scanner(new File("data.txt"));
        } catch (Exception e) {
            System.out.println("Faili ei leitud");
        }
    }

    public void readFile() {
        boolean tableExists = false;
        while (x.hasNext()) {
            device device = new device(name, power, channels);
            name = x.next();
            device.setName(name);
            power = Double.parseDouble(x.next());
            device.setPower(power);
            channels = Integer.parseInt(x.next());
            device.setChannels(channels);
            device.prindi();




        }

    }


    public void closeFile() {
        x.close();
    }

}
