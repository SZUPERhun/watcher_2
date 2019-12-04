package source;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import source.Machine;
import source.MachinePart;

import java.util.ArrayList;
import java.util.List;

public class Main  extends  Application{

	static List<Machine> machines;
	static Scheduler sch;

	private static void handle(WindowEvent t) {
		Platform.exit();
		System.exit(0);
	}

	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
		Parent root = loader.load();

		Controller controller = loader.<Controller>getController();

		Thread thread = new Thread(new Runnable() {
			@Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                    	controller.updateData(machines);
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
		});
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

        primaryStage.setTitle("Watcher");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(Main::handle);
	}

	public static void main(String args[]) {
		machines = new ArrayList<Machine>();

		machines.add(new Machine(0, "koho1"));
		machines.get(0).addNewMachinePart(new MachinePart(0, "egesterTemp", "tertmp", "temperature", "2019.11.05", 2, 80, 100));
		machines.get(0).addNewMachinePart(new MachinePart(1, "szellozoTemp", "szelltmp", "temperature", "2019.11.05", 2, 40, 50));
		machines.get(0).addNewMachinePart(new MachinePart(2, "langor", "lang", "langor", "2019.11.05", 2, 0, 1));
		machines.get(0).addNewMachinePart(new MachinePart(3, "gaznyomas", "press", "pressure", "2019.11.05", 2, 0, 100));
		machines.get(0).addNewMachinePart(new MachinePart(4, "gazcsap", "csap", "zar", "2019.11.05", 2, 0, 100));

		machines.add(new Machine(1, "koho2"));
		machines.get(1).addNewMachinePart(new MachinePart(0, "egesterTemp", "tertmp", "temperature", "2019.11.05", 3, 80, 100));
		machines.get(1).addNewMachinePart(new MachinePart(1, "szellozoTemp", "szelltmp", "temperature", "2019.11.05", 3, 40, 50));
		machines.get(1).addNewMachinePart(new MachinePart(2, "langor", "lang", "langor", "2019.11.05", 3, 0, 1));
		machines.get(1).addNewMachinePart(new MachinePart(3, "gaznyomas", "press", "pressure", "2019.11.05", 3, 0, 100));
		machines.get(1).addNewMachinePart(new MachinePart(4, "gazcsap", "csap", "zar", "2019.11.05", 3, 0, 100));

		sch = new Scheduler();
		sch.start(machines);

		launch(args);
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public boolean processLine(String line) {
		String[] str = line.split(";");
		if(str.length == 3)
			for(int i = 0; i < machines.size(); i++) {
				if(machines.get(i).getId() == Integer.parseInt(str[0]))
					for(int j = 0; j < machines.get(i).getMachineParts().size(); j++) {
						if(machines.get(i).getMachineParts().get(j).getId() == Integer.parseInt(str[1])) {
							machines.get(i).getMachineParts().get(j).setValue(Double.parseDouble(str[2]));
							return true;
						}
					}
			}

		return false;
	}
}
