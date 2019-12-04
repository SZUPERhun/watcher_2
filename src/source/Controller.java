package source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.List;


public class Controller {

    @FXML
    private Button error1Btn;

    @FXML
    private Button reset1Btn;

    @FXML
    private Button error2Btn;

    @FXML
    private Button reset2Btn;

    @FXML
    private Button nextDayBtn;

    @FXML
    private Label tertemp1Label;

    @FXML
    private Label szelltemp1Label;

    @FXML
    private Label tertemp2Label;

    @FXML
    private Label szelltemp2Label;

    private List<Machine> machines;

    public void updateData(List<Machine> machines) {
    	this.machines = machines;

    	updateLabel(tertemp1Label, machines.get(0).getMachinePart("tertmp"));
    	updateLabel(szelltemp1Label, machines.get(0).getMachinePart("szelltmp"));
    	updateLabel(tertemp2Label, machines.get(1).getMachinePart("tertmp"));
    	updateLabel(szelltemp2Label, machines.get(1).getMachinePart("szelltmp"));

        updateButton(machines.get(0), error1Btn, reset1Btn);
        updateButton(machines.get(1), error2Btn, reset2Btn);
    }

    private void updateLabel(Label label, MachinePart part) {
    	label.setText(part.getName() + ": " + String.format("%1$,.2f", part.getValue()));
    }

    private void updateButton(Machine machine, Button errorBtn, Button resetBtn) {
    	if (isWorking(machine)) {
        	errorBtn.setStyle("-fx-background-color: #00FF00; ");
    	} else {
    		errorBtn.setStyle("-fx-background-color: #FF0000; ");
    	}

        if (machine.getMachinePart("tertmp").getValue() == 0) {
        	resetBtn.setStyle("-fx-background-color: #00FF00; ");
    	} else {
    		resetBtn.setStyle("-fx-background-color: #FF0000; ");
    	}
    }

    private Boolean isWorking(Machine machine) {
    	for(MachinePart machinePart : machine.getMachineParts()) {
    		if (machinePart.getFault() != 0) {
        		return false;
        	}
    	}

    	return true;
    }

    @FXML
    private void handleNewDayAction(ActionEvent event){
    	machines.get(0).nextDay();
    	machines.get(1).nextDay();
    }

    @FXML
    private void handleError1Action(ActionEvent event){
    	machines.get(0).getMachinePart("szelltmp").setFault(1);
    	machines.get(0).getMachinePart("tertmp").setFault(1);
    }

    @FXML
    private void handleError2Action(ActionEvent event){
    	machines.get(1).getMachinePart("szelltmp").setFault(1);
    	machines.get(1).getMachinePart("tertmp").setFault(1);
    }

    @FXML
    private void handleReset1Action(ActionEvent event){
    	if (machines.get(0).getMachinePart("tertmp").getValue() == 0) {
    		for(MachinePart machinePart : machines.get(0).getMachineParts()) {
        		machinePart.setFault(0);
        		machinePart.setInitFault(0);
        	}
    	}
    }

    @FXML
    private void handleReset2Action(ActionEvent event){
    	if (machines.get(1).getMachinePart("tertmp").getValue() == 0) {
    		for(MachinePart machinePart : machines.get(1).getMachineParts()) {
        		machinePart.setFault(0);
        		machinePart.setInitFault(0);
        	}
    	}
    }
}
