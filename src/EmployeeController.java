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

	@FXML Button bThreeTender;
	@FXML Button bFourTender;

	@FXML Button bSan1;
	@FXML Button bSan2;
	@FXML Button bSan3;
	@FXML Button bSan4;

	boolean burger = false;
	ArrayList<String> orderList = new ArrayList<String>();
	ArrayList<CustomPair> menuItems = new ArrayList<CustomPair>();
	ArrayList<CustomPair> inventoryItems = new ArrayList<CustomPair>();

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

	@FXML RadioButton cNoBask;
	@FXML RadioButton cYesBask;
	@FXML RadioButton tNoneBask;
	@FXML RadioButton tRegBask;
	@FXML RadioButton tExtraBask;
	@FXML RadioButton gNoneBask;
	@FXML RadioButton gRegBask;
	@FXML RadioButton gExtraBask;
	@FXML Button bCheckoutBask;

	@FXML RadioButton cNoSan;
	@FXML RadioButton cChipSan;
	@FXML RadioButton cFriesSan;
	@FXML RadioButton patRegSan;
	@FXML RadioButton patBeanSan;
	@FXML RadioButton patChickenSan;
	@FXML RadioButton cNoneSan;
	@FXML RadioButton cRegSan;
	@FXML RadioButton cExtraSan;
	@FXML RadioButton sNoneSan;
	@FXML RadioButton sRevSan;
	@FXML RadioButton sSpicySan;
	@FXML RadioButton pNoneSan;
	@FXML RadioButton pRegSan;
	@FXML RadioButton pExtraSan;
	@FXML RadioButton lNoneSan;
	@FXML RadioButton lRegSan;
	@FXML RadioButton lExtraSan;
	@FXML RadioButton tNoneSan;
	@FXML RadioButton tRegSan;
	@FXML RadioButton tExtraSan;
	@FXML RadioButton oNoneSan;
	@FXML RadioButton oRegSan;
	@FXML RadioButton oExtraSan;
	@FXML Button bCheckoutSan;

	@FXML Button vIce;
	@FXML Button cIce;
	@FXML Button sIce;
	@FXML Button coIce;
	@FXML Button vMilk;
	@FXML Button cMilk;
	@FXML Button sMilk;
	@FXML Button coMilk;
	@FXML Button brownie;
	@FXML Button cookie;
	@FXML Button salad;
	@FXML Button gigem;
	@FXML Button buff;
	@FXML Button bbq;
	@FXML Button hMustard;
	@FXML Button ranch;
	@FXML Button sRanch;
	@FXML Button drink;
	@FXML Button drip;
	@FXML Button fries;
	@FXML Button tTots;
	@FXML Button oRings;
	@FXML Button kChips;
	@FXML Button silverware;


	@FXML Tab checkoutTab;
	@FXML Label orderListLabel;
	@FXML Button placeOrder;


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

        placeOrder.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		System.out.println("Placing order...");
		 		//TO DO: UPDATE DATABASE WHEN ORDER PLACED
				// createOrder()

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

						//add revs burger to menuItems
						CustomPair menuItemToAdd = new CustomPair(1, 1);
						menuItems.add(menuItemToAdd);
						//add customizations
						// CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						// inventoryItems.add(InventoryItemToAdd);
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

						 CustomPair menuItemToAdd = new CustomPair(2, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
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

						 CustomPair menuItemToAdd = new CustomPair(3, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
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

						 CustomPair menuItemToAdd = new CustomPair(4, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		 bThreeTender.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoBask);
		 		recipe.add(gRegBask);
		 		recipe.add(tRegBask);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutBask.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToBasketRecipe(recipe, "Three Tender Basket\n"));
		 				orderList.add(compareToBasketRecipe(recipe, "Three Tender Basket\n"));

						 CustomPair menuItemToAdd = new CustomPair(5, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		  bFourTender.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoBask);
		 		recipe.add(gRegBask);
		 		recipe.add(tRegBask);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutBask.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToBasketRecipe(recipe, "Four Tender Basket\n"));
		 				orderList.add(compareToBasketRecipe(recipe, "Four Tender Basket\n"));

						 CustomPair menuItemToAdd = new CustomPair(6, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		bSan1.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoSan);
		 		recipe.add(patRegSan);
		 		recipe.add(cRegSan);
		 		recipe.add(sRevSan);
		 		recipe.add(pNoneSan);
		 		recipe.add(lNoneSan);
		 		recipe.add(tNoneSan);
		 		recipe.add(oRegSan);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToSanRecipe(recipe, "Gig Em Patty Melt\n"));
		 				orderList.add(compareToSanRecipe(recipe, "Gig Em Patty Melt\n"));

						 CustomPair menuItemToAdd = new CustomPair(7, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		bSan2.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoSan);
		 		recipe.add(patChickenSan);
		 		recipe.add(cRegSan);
		 		recipe.add(sSpicySan);
		 		recipe.add(pNoneSan);
		 		recipe.add(lNoneSan);
		 		recipe.add(tNoneSan);
		 		recipe.add(oNoneSan);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToSanRecipe(recipe, "Howdy Chicken Sandwich\n"));
		 				orderList.add(compareToSanRecipe(recipe, "Howdy Chicken Sandwich\n"));

						 CustomPair menuItemToAdd = new CustomPair(8, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		bSan3.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoSan);
		 		recipe.add(patChickenSan);
		 		recipe.add(cNoneSan);
		 		recipe.add(sNoneSan);
		 		recipe.add(pRegSan);
		 		recipe.add(lRegSan);
		 		recipe.add(tRegSan);
		 		recipe.add(oRegSan);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToSanRecipe(recipe, "Chicken Tender Sandwich\n"));
		 				orderList.add(compareToSanRecipe(recipe, "Chicken Tender Sandwich\n"));

						 CustomPair menuItemToAdd = new CustomPair(9, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		bSan4.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		toggleOffAll();
		 		ArrayList<RadioButton> recipe =  new ArrayList<RadioButton>();
		 		recipe.add(cNoSan);
		 		recipe.add(patRegSan);
		 		recipe.add(cRegSan);
		 		recipe.add(sNoneSan);
		 		recipe.add(pNoneSan);
		 		recipe.add(lNoneSan);
		 		recipe.add(tNoneSan);
		 		recipe.add(oNoneSan);
				for(RadioButton b : recipe){
		 			b.setSelected(true);
		 		}
		 		bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
		 				System.out.println(compareToSanRecipe(recipe, "Grilled Cheese\n"));
		 				orderList.add(compareToSanRecipe(recipe, "Grilled Cheese\n"));

						 CustomPair menuItemToAdd = new CustomPair(10, 1);
						 menuItems.add(menuItemToAdd);
						 //add customizations
						 // CustomPair InventoryItemToAdd = new CustomPair(1, -1);
						 // inventoryItems.add(InventoryItemToAdd);
		 			}
		 		});
		 	}
		 });

		vIce.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Vanilla Ice Cream\n");
		 	}
		 });

		cIce.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Chocolate Ice Cream\n");
		 	}
		 });

		sIce.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Strawberry Ice Cream\n");
		 	}
		 });

		coIce.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Coffee Ice Cream\n");
		 	}
		 });

		vMilk.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Vanilla Milkshake\n");
		 	}
		 });

		coMilk.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Coffee Milkshake\n");
		 	}
		 });

		cMilk.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Chocolate Milkshake\n");
		 	}
		 });

		sMilk.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Strawberry Milkshake\n");
		 	}
		 });

		cookie.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Chocolate Chunk Cookie\n");
		 	}
		 });

		brownie.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Brownie\n");
		 	}
		 });

		salad.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Salad\n");
		 	}
		 });

		gigem.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Gig Em Sauce\n");
		 	}
		 });

		buff.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Buffalo Sauce\n");
		 	}
		 });

		bbq.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Barbeque Sauce\n");
		 	}
		 });

		hMustard.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Honey Mustard\n");
		 	}
		 });

		ranch.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Ranch\n");
		 	}
		 });

		sRanch.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Spicy Ranch\n");
		 	}
		 });
	
		drink.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Fountain Drink\n");
		 	}
		 });

		drip.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Drip Coffee\n");
		 	}
		 });

		fries.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Fries\n");
		 	}
		 });

		tTots.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Tater Tots\n");
		 	}
		 });

		oRings.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Onion Rings\n");
		 	}
		 });

		kChips.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Kettle Chips\n");
		 	}
		 });

		silverware.setOnAction(new EventHandler<ActionEvent>() {
		 	public void handle(ActionEvent event) {
		 		orderList.add("Silverware\n");
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
		 onActionGroups(cNoBask, cYesBask);
		 onActionGroups(gNoneBask, gRegBask, gExtraBask);
		 onActionGroups(tNoneBask, tRegBask, tExtraBask);
		 onActionGroups(cNoSan, cChipSan, cFriesSan);
		 onActionGroups(patRegSan, patBeanSan, patChickenSan);
		 onActionGroups(cNoneSan, cRegSan, cExtraSan);
		 onActionGroups(sNoneSan, sRevSan, sSpicySan);
		 onActionGroups(pNoneSan, pRegSan, pExtraSan);
		 onActionGroups(lNoneSan, lRegSan, lExtraSan);
		 onActionGroups(tNoneSan, tRegSan, tExtraSan);
		 onActionGroups(oNoneSan, oRegSan, oExtraSan);

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
		cNoBask.setSelected(false);
		cYesBask.setSelected(false);
		gNoneBask.setSelected(false);
		gRegBask.setSelected(false);
		gExtraBask.setSelected(false);
		tNoneBask.setSelected(false);
		tRegBask.setSelected(false);
		tExtraBask.setSelected(false);
		cNoSan.setSelected(false);
		cFriesSan.setSelected(false);
		cChipSan.setSelected(false);
		patRegSan.setSelected(false);
		patBeanSan.setSelected(false);
		patChickenSan.setSelected(false);
		cNoneSan.setSelected(false);
		cRegSan.setSelected(false);
		cExtraSan.setSelected(false);
		sNoneSan.setSelected(false);
		sRevSan.setSelected(false);
		sSpicySan.setSelected(false);
		pNoneSan.setSelected(false);
		pRegSan.setSelected(false);
		pExtraSan.setSelected(false);
		lNoneSan.setSelected(false);
		lRegSan.setSelected(false);
		lExtraSan.setSelected(false);
		tNoneSan.setSelected(false);
		tRegSan.setSelected(false);
		tExtraSan.setSelected(false);
		oNoneSan.setSelected(false);
		oRegSan.setSelected(false);
		oExtraSan.setSelected(false);
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

	String compareToBasketRecipe(ArrayList<RadioButton> recipe, String out){
		//String out = "";
		if(!recipe.get(0).isSelected()) {
			if(cYesBask.isSelected()) {
				out += "Combo\n";
			}
		}

		if(checkChanged(gNoneBask, gRegBask, gExtraBask, recipe.get(1)) == 1) {
			out += "No Gravy\n";
		} else if(checkChanged(gNoneBask, gRegBask, gExtraBask, recipe.get(1)) == 3) {
			out += "Extra Gravy\n";
		}

		if(checkChanged(tNoneBask, tRegBask, tExtraBask, recipe.get(2)) == 1) {
			out += "No Toast\n";
		} else if(checkChanged(tNoneBask, tRegBask, tExtraBask, recipe.get(2)) == 3) {
			out += "Extra Toast\n";
		}
		
		return out;
	}

	String compareToSanRecipe(ArrayList<RadioButton> recipe, String out){
		//String out = "";
		if(checkChanged(cNoSan, cChipSan, cRegSan, recipe.get(0)) == 2) {
			out += "Combo with chips\n";
		} else if(checkChanged(cNoSan, cChipSan, cRegSan, recipe.get(0)) == 3) {
			out += "Combo with fries\n";
		}

		if(checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 2) {
			out += "Sub Bean Patty\n";
		} else if(checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 3) {
			out += "Chicken Tenders\n";
		}

		if(checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 1) {
			out += "No Cheese\n";
		} else if(checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 2) {
			out += "Add Cheese\n";
		} else if(checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 3) {
			out += "Extra Cheese\n";
		}

		if(checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 1) {
			out += "No Sauce\n";
		} else if(checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 2) {
			out += "Sub Rev's Sacue \n";
		} else if(checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 3) {
			out += "Sub Spicy Sauce\n";
		}

		if(checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 1) {
			out += "No Pickles\n";
		} else if(checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 2) {
			out += "Add Pickles\n";
		} else if(checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 3) {
			out += "Extra Pickles\n";
		}

		if(checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 1) {
			out += "No Lettuce\n";
		} else if(checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 2) {
			out += "Add Lettuce\n";
		} else if(checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 3) {
			out += "Extra Lettuce\n";
		}

		if(checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 1) {
			out += "No Tomato\n";
		} else if(checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 2) {
			out += "Add Tomato\n";
		} else if(checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 3) {
			out += "Extra Tomato\n";
		}

		if(checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 1) {
			out += "No Onion\n";
		} else if(checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 2) {
			out += "Add Onion\n";
		} else if(checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 3) {
			out += "Extra Onion\n";
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
