import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {
	@FXML Button bBurg1;
	@FXML Button bBurg2;
	@FXML Button bBurg3;
	@FXML Button bBurg4;

	boolean burger = false;
	ArrayList<String> orderList = new ArrayList<String>();

	@FXML RadioButton cNoBurg;
	@FXML RadioButton cChipBurg;
	@FXML RadioButton cFriesBurg;
	@FXML RadioButton patRegBurg;
	@FXML RadioButton patBeanBurg;
	@FXML RadioButton patDoubleBurg;
	@FXML RadioButton cNoneBurg;
	@FXML RadioButton cRegBurg;
	@FXML RadioButton cExtraBurg;
	@FXML RadioButton sNoneBurg;
	@FXML RadioButton sRegBurg;
	@FXML RadioButton pNoneBurg;
	@FXML RadioButton pRegBurg;
	@FXML RadioButton pExtraBurg;
	@FXML RadioButton lNoneBurg;
	@FXML RadioButton lRegBurg;
	@FXML RadioButton lExtraBurg;
	@FXML RadioButton tNoneBurg;
	@FXML RadioButton tRegBurg;
	@FXML RadioButton tExtraBurg;
	@FXML RadioButton oNoneBurg;
	@FXML RadioButton oRegBurg;
	@FXML RadioButton oExtraBurg;
	@FXML RadioButton bNoneBurg;
	@FXML RadioButton bRegBurg;
	@FXML RadioButton bExtraBurg;
	@FXML Button bCheckoutBurg;
	@FXML Tab checkoutTab;
	@FXML Label orderListLabel;


	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("Employee controller running");

		checkoutTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            	String orders = "";
            	for(String item: orderList){
            		orders += item;
            		orders += "\n";
            	}
                orderListLabel.setText(orders);
            }
        });

		 bBurg1.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		burger = true;
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoBurg);
		 		recipe.add(patRegBurg);
		 		recipe.add(cRegBurg);
		 		recipe.add(sRegBurg);
		 		recipe.add(pRegBurg);
		 		recipe.add(lNoneBurg);
		 		recipe.add(tNoneBurg);
		 		recipe.add(oNoneBurg);
		 		recipe.add(bNoneBurg);
		 		for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToBurgerRecipe(recipe, "Rev's Burger\n"));
		 				orderList.add(compareToBurgerRecipe(recipe, "Rev's Burger\n"));
		 			}
		 		});
		 	}
		 });

		 bBurg2.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		burger = true;
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoBurg);
		 		recipe.add(patDoubleBurg);
		 		recipe.add(cExtraBurg);
		 		recipe.add(sRegBurg);
		 		recipe.add(pRegBurg);
		 		recipe.add(lNoneBurg);
		 		recipe.add(tNoneBurg);
		 		recipe.add(oNoneBurg);
		 		recipe.add(bNoneBurg);
		 		for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToBurgerRecipe(recipe, "Double Cheese Burger\n"));
		 				orderList.add(compareToBurgerRecipe(recipe, "Double Cheese Burger\n"));
		 			}
		 		});
		 	}
		 });

		 bBurg3.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		burger = true;
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoBurg);
		 		recipe.add(patRegBurg);
		 		recipe.add(cNoneBurg);
		 		recipe.add(sNoneBurg);
		 		recipe.add(pRegBurg);
		 		recipe.add(lRegBurg);
		 		recipe.add(tRegBurg);
		 		recipe.add(oRegBurg);
		 		recipe.add(bNoneBurg);
		 		for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToBurgerRecipe(recipe, "Classic Burger\n"));
		 				orderList.add(compareToBurgerRecipe(recipe, "Classic Burger\n"));
		 			}
		 		});
		 	}
		 });

		 bBurg4.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		burger = true;
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoBurg);
		 		recipe.add(patRegBurg);
		 		recipe.add(cRegBurg);
		 		recipe.add(sNoneBurg);
		 		recipe.add(pNoneBurg);
		 		recipe.add(lNoneBurg);
		 		recipe.add(tNoneBurg);
		 		recipe.add(oNoneBurg);
		 		recipe.add(bRegBurg);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToBurgerRecipe(recipe, "Bacon Cheese Burger\n"));
		 				orderList.add(compareToBurgerRecipe(recipe, "Bacon Cheese Burger\n"));
		 			}
		 		});
		 	}
		 });

		 onActionGroups(cNoBurg, cChipBurg, cFriesBurg);
		 onActionGroups(patRegBurg, patBeanBurg, patDoubleBurg);
		 onActionGroups(cNoneBurg, cRegBurg, cExtraBurg);
		 onActionGroups(sNoneBurg, sRegBurg);
		 onActionGroups(pNoneBurg, pRegBurg, pExtraBurg);
		 onActionGroups(lNoneBurg, lRegBurg, lExtraBurg);
		 onActionGroups(tNoneBurg, tRegBurg, tExtraBurg);
		 onActionGroups(oNoneBurg, oRegBurg, oExtraBurg);
		 onActionGroups(bNoneBurg, bRegBurg, bExtraBurg);

	}

	void onActionGroups(RadioButton b1, RadioButton b2, RadioButton b3) {
		b1.setOnAction(new EventHandler<ActionEvent>() { //combo for burger
		 	public void handle(ActionEvent event) {
		 		b2.setSelected(false);
		 		b3.setSelected(false);
		 	}
		 });

		 b2.setOnAction(new EventHandler<ActionEvent>() { //combo for burger
		 	public void handle(ActionEvent event) {
		 		b1.setSelected(false);
		 		b3.setSelected(false);
		 	}
		 });

		 b3.setOnAction(new EventHandler<ActionEvent>() { //combo for burger
		 	public void handle(ActionEvent event) {
		 		b1.setSelected(false);
		 		b2.setSelected(false);
		 	}
		 });
	}

	void onActionGroups(RadioButton b1, RadioButton b2) {
		b1.setOnAction(new EventHandler<ActionEvent>() { //combo for burger
		 	public void handle(ActionEvent event) {
		 		b2.setSelected(false);
		 	}
		 });

		 b2.setOnAction(new EventHandler<ActionEvent>() { //combo for burger
		 	public void handle(ActionEvent event) {
		 		b1.setSelected(false);
		 	}
		 });
	}

	void toggleOffAll(){
		cNoBurg.setSelected(false);
		cChipBurg.setSelected(false);
		cFriesBurg.setSelected(false);
		patRegBurg.setSelected(false);
		patBeanBurg.setSelected(false);
		patDoubleBurg.setSelected(false);
		cNoneBurg.setSelected(false);
		cRegBurg.setSelected(false);
		cExtraBurg.setSelected(false);
		sNoneBurg.setSelected(false);
		sRegBurg.setSelected(false);
		pNoneBurg.setSelected(false);
		pRegBurg.setSelected(false);
		pExtraBurg.setSelected(false);
		lNoneBurg.setSelected(false);
		lRegBurg.setSelected(false);
		lExtraBurg.setSelected(false);
		tNoneBurg.setSelected(false);
		tRegBurg.setSelected(false);
		tExtraBurg.setSelected(false);
		oNoneBurg.setSelected(false);
		oRegBurg.setSelected(false);
		oExtraBurg.setSelected(false);
		bNoneBurg.setSelected(false);
		bRegBurg.setSelected(false);
		bExtraBurg.setSelected(false);
	}

	String compareToBurgerRecipe(ArrayList<RadioButton> recipe, String out){
		//String out = "";
		if(checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 2) {
			out += "Combo with chips\n";
		} else if(checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 3) {
			out += "Combo with fries\n";
		}

		if(checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(1)) == 2) {
			out += "Sub Bean Patty\n";
		} else if(checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(1)) == 3) {
			out += "Double Patty\n";
		}

		if(checkChanged(cNoneBurg, cRegBurg, cExtraBurg, recipe.get(2)) == 1) {
			out += "No Cheese\n";
		} else if(checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(2)) == 2) {
			out += "Add Cheese\n";
		} else if(checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(2)) == 3) {
			out += "Extra Cheese\n";
		}

		if(!recipe.get(3).isSelected()) {
			if(sNoneBurg.isSelected()) {
				out += "No Sauce\n";
			} else {
				out += "Add Sauce\n";
			}
		}

		if(checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 1) {
			out += "No Pickles\n";
		} else if(checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 2) {
			out += "Add Pickles\n";
		} else if(checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 3) {
			out += "Extra Pickles\n";
		}

		if(checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 1) {
			out += "No Lettuce\n";
		} else if(checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 2) {
			out += "Add Lettuce\n";
		} else if(checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 3) {
			out += "Extra Lettuce\n";
		}

		if(checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 1) {
			out += "No Tomato\n";
		} else if(checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 2) {
			out += "Add Tomato\n";
		} else if(checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 3) {
			out += "Extra Tomato\n";
		}

		if(checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 1) {
			out += "No Onion\n";
		} else if(checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 2) {
			out += "Add Onion\n";
		} else if(checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 3) {
			out += "Extra Onion\n";
		}

		if(checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 1) {
			out += "No Bacon\n";
		} else if(checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 2) {
			out += "Add Bacon\n";
		} else if(checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 3) {
			out += "Extra Bacon\n";
		}
		return out;
	}

	// returns 0 if default, 1 if none, 2 if extra
	// for combo, 2 is chips 3 is fries
	int checkChanged(RadioButton b1, RadioButton b2, RadioButton b3, RadioButton b4) {
		if(b4.isSelected()){
			return 0;
		} else if (b1.isSelected()) {
			return 1;
		} else if (b2.isSelected()) {
			return 2;
		} else if (b3.isSelected()) {
			return 3;
		}
		return 0;
	}


}
