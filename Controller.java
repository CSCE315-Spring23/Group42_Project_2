
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML Button btn1;
	@FXML Button btn2;
	@FXML Button btn3;
	@FXML Button btn4;
	@FXML Button btn5;
	@FXML Button btn6;
	@FXML Button btn7;
	@FXML Button btn8;
	@FXML Label name1;
	@FXML Label count1;
	@FXML Label name2;
	@FXML Label count2;
	@FXML Label name3;
	@FXML Label count3;
	@FXML Label name4;
	@FXML Label count4;
	@FXML Label name5;
	@FXML Label count5;
	@FXML Label name6;
	@FXML Label count6;
	@FXML Label name7;
	@FXML Label count7;
	@FXML Label name8;
	@FXML Label count8;
	@FXML ImageView img1;
	@FXML ImageView img2;
	@FXML ImageView img3;
	@FXML ImageView img4;
	@FXML ImageView img5;
	@FXML ImageView img6;
	@FXML ImageView img7;
	@FXML ImageView img8;
	@FXML ImageView imgRet;
	ArrayList <String> types;
	
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("controller running");


		//btn1 = (Button)findIdById(R.id.btn1);
	    btn1.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent event) {
	            System.out.println("Selected 1");
	        }
	    });
	}


	private void loadIntoGUIItems(ArrayList<String> items) {
		name1.setText("a");
		name2.setText("a");
		name3.setText("a");
	}


}
