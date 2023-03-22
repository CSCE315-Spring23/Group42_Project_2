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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {
	boolean burger = false; // check it item is burger
	ArrayList<String> orderList = new ArrayList<String>();// ArrayList for the string of orders to print at checkout
	ArrayList<CustomPair> menuItems = new ArrayList<CustomPair>();// ArrayList for menu items to be passed into
																	// createOrder()
	ArrayList<CustomPair> inventoryItems = new ArrayList<CustomPair>();// ArrayList for inventory items to be passed
																		// into createOrder()
	double totalOrderCost = 0.0;// total cost per order
	private Database db;// database object to be used in all functions

	@FXML
	Button bBurg1;
	@FXML
	Button bBurg2;
	@FXML
	Button bBurg3;
	@FXML
	Button bBurg4;

	@FXML
	Button bThreeTender;
	@FXML
	Button bFourTender;

	@FXML
	Button bSan1;
	@FXML
	Button bSan2;
	@FXML
	Button bSan3;
	@FXML
	Button bSan4;

	@FXML
	RadioButton cNoBurg;
	@FXML
	RadioButton cChipBurg;
	@FXML
	RadioButton cFriesBurg;
	@FXML
	RadioButton patRegBurg;
	@FXML
	RadioButton patBeanBurg;
	@FXML
	RadioButton patDoubleBurg;
	@FXML
	RadioButton cNoneBurg;
	@FXML
	RadioButton cRegBurg;
	@FXML
	RadioButton cExtraBurg;
	@FXML
	RadioButton sNoneBurg;
	@FXML
	RadioButton sRegBurg;
	@FXML
	RadioButton pNoneBurg;
	@FXML
	RadioButton pRegBurg;
	@FXML
	RadioButton pExtraBurg;
	@FXML
	RadioButton lNoneBurg;
	@FXML
	RadioButton lRegBurg;
	@FXML
	RadioButton lExtraBurg;
	@FXML
	RadioButton tNoneBurg;
	@FXML
	RadioButton tRegBurg;
	@FXML
	RadioButton tExtraBurg;
	@FXML
	RadioButton oNoneBurg;
	@FXML
	RadioButton oRegBurg;
	@FXML
	RadioButton oExtraBurg;
	@FXML
	RadioButton bNoneBurg;
	@FXML
	RadioButton bRegBurg;
	@FXML
	RadioButton bExtraBurg;
	@FXML
	Button bCheckoutBurg;

	@FXML
	RadioButton cNoBask;
	@FXML
	RadioButton cYesBask;
	@FXML
	RadioButton tNoneBask;
	@FXML
	RadioButton tRegBask;
	@FXML
	RadioButton tExtraBask;
	@FXML
	RadioButton gNoneBask;
	@FXML
	RadioButton gRegBask;
	@FXML
	RadioButton gExtraBask;
	@FXML
	Button bCheckoutBask;

	@FXML
	RadioButton cNoSan;
	@FXML
	RadioButton cChipSan;
	@FXML
	RadioButton cFriesSan;
	@FXML
	RadioButton patRegSan;
	@FXML
	RadioButton patBeanSan;
	@FXML
	RadioButton patChickenSan;
	@FXML
	RadioButton cNoneSan;
	@FXML
	RadioButton cRegSan;
	@FXML
	RadioButton cExtraSan;
	@FXML
	RadioButton sNoneSan;
	@FXML
	RadioButton sRevSan;
	@FXML
	RadioButton sSpicySan;
	@FXML
	RadioButton pNoneSan;
	@FXML
	RadioButton pRegSan;
	@FXML
	RadioButton pExtraSan;
	@FXML
	RadioButton lNoneSan;
	@FXML
	RadioButton lRegSan;
	@FXML
	RadioButton lExtraSan;
	@FXML
	RadioButton tNoneSan;
	@FXML
	RadioButton tRegSan;
	@FXML
	RadioButton tExtraSan;
	@FXML
	RadioButton oNoneSan;
	@FXML
	RadioButton oRegSan;
	@FXML
	RadioButton oExtraSan;
	@FXML
	Button bCheckoutSan;

	@FXML
	Button vIce;
	@FXML
	Button cIce;
	@FXML
	Button sIce;
	@FXML
	Button coIce;
	@FXML
	Button vMilk;
	@FXML
	Button cMilk;
	@FXML
	Button sMilk;
	@FXML
	Button coMilk;
	@FXML
	Button brownie;
	@FXML
	Button cookie;
	@FXML
	Button salad;
	@FXML
	Button gigem;
	@FXML
	Button buff;
	@FXML
	Button bbq;
	@FXML
	Button hMustard;
	@FXML
	Button ranch;
	@FXML
	Button sRanch;
	@FXML
	Button drink;
	@FXML
	Button drip;
	@FXML
	Button fries;
	@FXML
	Button tTots;
	@FXML
	Button oRings;
	@FXML
	Button kChips;
	@FXML
	Button silverware;

	@FXML
	Button sb1;
	@FXML
	Button sb2;
	@FXML
	Button sb3;
	@FXML
	Button sb4;

	@FXML
	Tab checkoutTab;
	@FXML
	Label orderListLabel;
	@FXML
	Button placeOrder;
	@FXML
	Button logout;

	/**
	 * 
	 * initializes databse and java fx buttons
	 * 
	 * @param location  URL for initilaization
	 * @param resources javafx resources
	 * @author Ariela
	 */
	public void initialize(URL location, ResourceBundle resources) {
		this.db = new Database();
		initNewItem();
		System.out.println("Employee controller running");

		checkoutTab.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				String orders = "";
				for (String item : orderList) {
					orders += item;
					orders += "\n";
				}
				orderListLabel.setText(orders);
			}
		});
		/**
		 * place order
		 */
		placeOrder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Placing order...");
				// TO DO: UPDATE DATABASE WHEN ORDER PLACED
				db.createOrder(totalOrderCost, menuItems, inventoryItems);
				totalOrderCost = 0;
				menuItems.clear();
				inventoryItems.clear();
				burger = false;
				orderList.clear();
				System.out.println("Order Placed");

			}
		});
		/**
		 * Log Out
		 */
		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					// set up login scene
					FXMLLoader loginLoader = new FXMLLoader(
							getClass().getResource("login.fxml"));
					Parent loginParent = (Parent) loginLoader.load();
					Scene loginScene = new Scene(loginParent, 650, 650);

					// set up employee scene
					FXMLLoader employeeLoader = new FXMLLoader(
							getClass().getResource("employee.fxml"));
					Parent employeeParent = (Parent) employeeLoader.load();
					Scene employeeScene = new Scene(employeeParent, 650, 650);

					// set up manager scene
					FXMLLoader managerLoader = new FXMLLoader(
							getClass().getResource("manager.fxml"));
					Parent managerParent = (Parent) managerLoader.load();
					Scene managerScene = new Scene(managerParent, 650, 650);

					LoginController loginController = (LoginController) loginLoader.getController();
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					loginController.setEmployeeScene(employeeScene);
					// managerController.setEmployeeScene(employeeScene)
					loginController.setManagerScene(managerScene);

					stage.setTitle("315 Project 2");
					stage.setScene(loginScene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		/**
		 * add a Rev's Burger to the order
		 * 
		 * 
		 */
		bBurg1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				burger = true;
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoBurg);
				recipe.add(patRegBurg);
				recipe.add(cRegBurg);
				recipe.add(sRegBurg);
				recipe.add(pRegBurg);
				recipe.add(lNoneBurg);
				recipe.add(tNoneBurg);
				recipe.add(oNoneBurg);
				recipe.add(bNoneBurg);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToBurgerRecipe(recipe, "Rev's Burger\n"));
						orderList.add(compareToBurgerRecipe(recipe, "Rev's Burger\n"));

						// add revs burger to menuItems
						CustomPair menuItemToAdd = new CustomPair(1, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(1);
						// add customizations
						totalOrderCost = addToBurgerRecipe(recipe, inventoryItems, totalOrderCost);
					}
				});
			}
		});
		/**
		 * add a Double Cheese Burger to the order
		 */
		bBurg2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				burger = true;
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoBurg);
				recipe.add(patDoubleBurg);
				recipe.add(cExtraBurg);
				recipe.add(sRegBurg);
				recipe.add(pRegBurg);
				recipe.add(lNoneBurg);
				recipe.add(tNoneBurg);
				recipe.add(oNoneBurg);
				recipe.add(bNoneBurg);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToBurgerRecipe(recipe, "Double Cheese Burger\n"));
						orderList.add(compareToBurgerRecipe(recipe, "Double Cheese Burger\n"));

						CustomPair menuItemToAdd = new CustomPair(2, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(2);
						// add customizations
						totalOrderCost = addToBurgerRecipe(recipe, inventoryItems, totalOrderCost);
					}
				});
			}
		});

		/**
		 * add Classic Burger to order
		 */
		bBurg3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				burger = true;
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoBurg);
				recipe.add(patRegBurg);
				recipe.add(cNoneBurg);
				recipe.add(sNoneBurg);
				recipe.add(pRegBurg);
				recipe.add(lRegBurg);
				recipe.add(tRegBurg);
				recipe.add(oRegBurg);
				recipe.add(bNoneBurg);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToBurgerRecipe(recipe, "Classic Burger\n"));
						orderList.add(compareToBurgerRecipe(recipe, "Classic Burger\n"));

						CustomPair menuItemToAdd = new CustomPair(3, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(3);
						// add customizations
						totalOrderCost = addToBurgerRecipe(recipe, inventoryItems, totalOrderCost);
					}
				});
			}
		});

		/**
		 * add Bacon Cheese Burger to order
		 */
		bBurg4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				burger = true;
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoBurg);
				recipe.add(patRegBurg);
				recipe.add(cRegBurg);
				recipe.add(sNoneBurg);
				recipe.add(pNoneBurg);
				recipe.add(lNoneBurg);
				recipe.add(tNoneBurg);
				recipe.add(oNoneBurg);
				recipe.add(bRegBurg);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutBurg.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToBurgerRecipe(recipe, "Bacon Cheese Burger\n"));
						orderList.add(compareToBurgerRecipe(recipe, "Bacon Cheese Burger\n"));

						CustomPair menuItemToAdd = new CustomPair(4, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(4);
						// add customizations
						totalOrderCost = addToBurgerRecipe(recipe, inventoryItems, totalOrderCost);
					}
				});
			}
		});

		bThreeTender.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoBask);
				recipe.add(gRegBask);
				recipe.add(tRegBask);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutBask.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToBasketRecipe(recipe, "Three Tender Basket\n"));
						orderList.add(compareToBasketRecipe(recipe, "Three Tender Basket\n"));

						CustomPair menuItemToAdd = new CustomPair(5, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(5);
						// add customizations
						totalOrderCost = addToBasketRecipe(recipe, inventoryItems, totalOrderCost);
					}
				});
			}
		});

		/**
		 * add four tender basket to order
		 */
		bFourTender.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoBask);
				recipe.add(gRegBask);
				recipe.add(tRegBask);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutBask.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToBasketRecipe(recipe, "Four Tender Basket\n"));
						orderList.add(compareToBasketRecipe(recipe, "Four Tender Basket\n"));

						CustomPair menuItemToAdd = new CustomPair(6, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(6);
					}
				});
			}
		});

		/**
		 * add Gig Em Patty Melt to the order
		 */
		bSan1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoSan);
				recipe.add(patRegSan);
				recipe.add(cRegSan);
				recipe.add(sRevSan);
				recipe.add(pNoneSan);
				recipe.add(lNoneSan);
				recipe.add(tNoneSan);
				recipe.add(oRegSan);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToSanRecipe(recipe, "Gig Em Patty Melt\n"));
						orderList.add(compareToSanRecipe(recipe, "Gig Em Patty Melt\n"));

						CustomPair menuItemToAdd = new CustomPair(7, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(7);
					}
				});
			}
		});
		/**
		 * order Howdy Chicken Sandwich to the order
		 */
		bSan2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoSan);
				recipe.add(patChickenSan);
				recipe.add(cRegSan);
				recipe.add(sSpicySan);
				recipe.add(pNoneSan);
				recipe.add(lNoneSan);
				recipe.add(tNoneSan);
				recipe.add(oNoneSan);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToSanRecipe(recipe, "Howdy Chicken Sandwich\n"));
						orderList.add(compareToSanRecipe(recipe, "Howdy Chicken Sandwich\n"));

						CustomPair menuItemToAdd = new CustomPair(8, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(8);
					}
				});
			}
		});

		/**
		 * add Chicken Tender Sandwich to the order
		 */
		bSan3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoSan);
				recipe.add(patChickenSan);
				recipe.add(cNoneSan);
				recipe.add(sNoneSan);
				recipe.add(pRegSan);
				recipe.add(lRegSan);
				recipe.add(tRegSan);
				recipe.add(oRegSan);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToSanRecipe(recipe, "Chicken Tender Sandwich\n"));
						orderList.add(compareToSanRecipe(recipe, "Chicken Tender Sandwich\n"));

						CustomPair menuItemToAdd = new CustomPair(9, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(9);
					}
				});
			}
		});
		/**
		 * add Grilled Cheese to the order
		 */
		bSan4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				toggleOffAll();
				ArrayList<RadioButton> recipe = new ArrayList<RadioButton>();
				recipe.add(cNoSan);
				recipe.add(patRegSan);
				recipe.add(cRegSan);
				recipe.add(sNoneSan);
				recipe.add(pNoneSan);
				recipe.add(lNoneSan);
				recipe.add(tNoneSan);
				recipe.add(oNoneSan);
				for (RadioButton b : recipe) {
					b.setSelected(true);
				}
				bCheckoutSan.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						System.out.println(compareToSanRecipe(recipe, "Grilled Cheese\n"));
						orderList.add(compareToSanRecipe(recipe, "Grilled Cheese\n"));

						CustomPair menuItemToAdd = new CustomPair(10, 1);
						menuItems.add(menuItemToAdd);
						totalOrderCost += db.getPriceOfMenuItem(10);
					}
				});
			}
		});
		/**
		 * add Vanilla Ice Cream to the order
		 */
		vIce.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Vanilla Ice Cream\n");

				CustomPair menuItemToAdd = new CustomPair(14, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(14);
			}
		});

		/**
		 * add Chocolate Ice Cream to the order
		 */
		cIce.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Chocolate Ice Cream\n");
				CustomPair menuItemToAdd = new CustomPair(14, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(14);
			}
		});

		/**
		 * add Strawberry Ice cream to order
		 */
		sIce.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Strawberry Ice Cream\n");

				CustomPair menuItemToAdd = new CustomPair(14, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(14);
			}
		});

		/**
		 * add Coffee Ice Cream to order
		 */
		coIce.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Coffee Ice Cream\n");

				CustomPair menuItemToAdd = new CustomPair(14, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(14);
			}
		});

		/**
		 * add Vanilla Milkshake to order
		 */
		vMilk.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Vanilla Milkshake\n");
				CustomPair menuItemToAdd = new CustomPair(13, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(13);
			}
		});

		/**
		 * add Cofee Milkshake to order
		 */
		coMilk.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Coffee Milkshake\n");

				CustomPair menuItemToAdd = new CustomPair(13, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(13);
			}
		});

		/**
		 * add Chocolate Milkshake to order
		 */
		cMilk.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Chocolate Milkshake\n");

				CustomPair menuItemToAdd = new CustomPair(13, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(13);
			}
		});

		/**
		 * add Strawberry Milkshake to Order
		 */
		sMilk.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Strawberry Milkshake\n");

				CustomPair menuItemToAdd = new CustomPair(13, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(13);
			}
		});

		/**
		 * add Chocolate Chunk Cookie to order
		 */
		cookie.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Chocolate Chunk Cookie\n");

				CustomPair menuItemToAdd = new CustomPair(15, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(15);

			}
		});
		/**
		 * add Brownie to order
		 */
		brownie.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Brownie\n");

				CustomPair menuItemToAdd = new CustomPair(16, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(16);
			}
		});
		/**
		 * add Salad to order
		 */
		salad.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Salad\n");

				CustomPair menuItemToAdd = new CustomPair(17, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(17);
			}
		});
		/**
		 * add Gig Em Sauce to the order
		 */
		gigem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Gig Em Sauce\n");

				CustomPair menuItemToAdd = new CustomPair(18, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(18);
			}
		});

		/**
		 * add Buffalo Sauce to the order
		 */
		buff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Buffalo Sauce\n");

				CustomPair menuItemToAdd = new CustomPair(18, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(18);
			}
		});
		/**
		 * add Barbeque Sauce to the order
		 */
		bbq.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Barbeque Sauce\n");

				CustomPair menuItemToAdd = new CustomPair(18, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(18);
			}
		});
		/**
		 * add Honey Mustard to the order
		 */
		hMustard.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Honey Mustard\n");

				CustomPair menuItemToAdd = new CustomPair(18, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(18);
			}
		});
		/**
		 * add Ranch to the order
		 */
		ranch.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Ranch\n");

				CustomPair menuItemToAdd = new CustomPair(18, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(18);
			}
		});
		/**
		 * add Spicy Ranch to the order
		 */
		sRanch.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Spicy Ranch\n");

				CustomPair menuItemToAdd = new CustomPair(18, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(18);
			}
		});

		/**
		 * add Fountain Drink to the order
		 */
		drink.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Fountain Drink\n");

				CustomPair menuItemToAdd = new CustomPair(19, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(19);
			}
		});

		/**
		 * add Drip Coffee to the order
		 */
		drip.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Drip Coffee\n");

				CustomPair menuItemToAdd = new CustomPair(20, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(20);
			}
		});
		/**
		 * add Fries to the order
		 */
		fries.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Fries\n");

				CustomPair menuItemToAdd = new CustomPair(22, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(22);
			}
		});

		/**
		 * add Tater Tots to the order
		 */
		tTots.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Tater Tots\n");

				CustomPair menuItemToAdd = new CustomPair(23, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(23);
			}
		});

		/**
		 * add Onion Rings to the order
		 */
		oRings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Onion Rings\n");

				CustomPair menuItemToAdd = new CustomPair(24, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(24);
			}
		});

		/**
		 * add Kettle Chips to the order
		 */
		kChips.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Kettle Chips\n");

				CustomPair menuItemToAdd = new CustomPair(25, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(25);
			}
		});
		/**
		 * add Silverware to the order (happens every order)
		 */
		silverware.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				orderList.add("Silverware\n");

				CustomPair menuItemToAdd = new CustomPair(26, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(26);
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

	/**
	 * 
	 * @param b1 button 1
	 * @param b2 button 2
	 * @param b3 button 3
	 * @author Ariela
	 */
	void onActionGroups(RadioButton b1, RadioButton b2, RadioButton b3) {
		b1.setOnAction(new EventHandler<ActionEvent>() { // combo for burger
			public void handle(ActionEvent event) {
				b2.setSelected(false);
				b3.setSelected(false);
			}
		});

		b2.setOnAction(new EventHandler<ActionEvent>() { // combo for burger
			public void handle(ActionEvent event) {
				b1.setSelected(false);
				b3.setSelected(false);
			}
		});

		b3.setOnAction(new EventHandler<ActionEvent>() { // combo for burger
			public void handle(ActionEvent event) {
				b1.setSelected(false);
				b2.setSelected(false);
			}
		});
	}

	/**
	 * 
	 * @param b1 button 1
	 * @param b2 button 2
	 * @param b3 button 3
	 * @author Ariela
	 */
	void onActionGroups(RadioButton b1, RadioButton b2) {
		b1.setOnAction(new EventHandler<ActionEvent>() { // combo for burger
			public void handle(ActionEvent event) {
				b2.setSelected(false);
			}
		});

		b2.setOnAction(new EventHandler<ActionEvent>() { // combo for burger
			public void handle(ActionEvent event) {
				b1.setSelected(false);
			}
		});
	}

	/**
	 * turn all buttons off
	 * 
	 * @author Ariela
	 */
	void toggleOffAll() {
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

	/**
	 * compares recipe to order to check for modifications
	 * 
	 * @author Ariela
	 * @param recipe list of inventory items customized
	 * @param out    text output to screen
	 * @return specific combo selected
	 */
	String compareToBurgerRecipe(ArrayList<RadioButton> recipe, String out) {
		// String out = "";
		if (checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 2) {
			out += "Combo with chips\n";
		} else if (checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 3) {
			out += "Combo with fries\n";
		}

		if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(1)) == 2) {
			out += "Sub Bean Patty\n";
		} else if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(1)) == 3) {
			out += "Double Patty\n";
		}

		if (checkChanged(cNoneBurg, cRegBurg, cExtraBurg, recipe.get(2)) == 1) {
			out += "No Cheese\n";
		} else if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(2)) == 2) {
			out += "Add Cheese\n";
		} else if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(2)) == 3) {
			out += "Extra Cheese\n";
		}

		if (!recipe.get(3).isSelected()) {
			if (sNoneBurg.isSelected()) {
				out += "No Sauce\n";
			} else {
				out += "Add Sauce\n";
			}
		}

		if (checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 1) {
			out += "No Pickles\n";
		} else if (checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 2) {
			out += "Add Pickles\n";
		} else if (checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 3) {
			out += "Extra Pickles\n";
		}

		if (checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 1) {
			out += "No Lettuce\n";
		} else if (checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 2) {
			out += "Add Lettuce\n";
		} else if (checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 3) {
			out += "Extra Lettuce\n";
		}

		if (checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 1) {
			out += "No Tomato\n";
		} else if (checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 2) {
			out += "Add Tomato\n";
		} else if (checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 3) {
			out += "Extra Tomato\n";
		}

		if (checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 1) {
			out += "No Onion\n";
		} else if (checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 2) {
			out += "Add Onion\n";
		} else if (checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 3) {
			out += "Extra Onion\n";
		}

		if (checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 1) {
			out += "No Bacon\n";
		} else if (checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 2) {
			out += "Add Bacon\n";
		} else if (checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 3) {
			out += "Extra Bacon\n";
		}
		return out;
	}

	/**
	 * executes changes to burger recipe
	 * 
	 * @author Srikar
	 * @param recipe         list of customizations
	 * @param inventoryItems list of inventory items customized
	 * @param totalOrderCost total order cost
	 * @return cost of ordered item
	 */
	double addToBurgerRecipe(ArrayList<RadioButton> recipe, ArrayList<CustomPair> inventoryItems,
			double totalOrderCost) {
		// String out = "";
		if ((checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 2)
				|| (checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 3)) {
			// out += "Combo with chips\n";
			CustomPair menuItemToAdd = new CustomPair(11, 1);
			menuItems.add(menuItemToAdd);
			totalOrderCost += db.getPriceOfMenuItem(11);

		}
		// else if(checkChanged(cNoBurg, cChipBurg, cFriesBurg, recipe.get(0)) == 3) {
		// // out += "Combo with fries\n";
		// CustomPair menuItemToAdd = new CustomPair(11, 1);
		// menuItems.add(menuItemToAdd);
		// }

		if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(1)) == 2) {
			// out += "Sub Bean Patty\n";
			CustomPair inventoryItemToAdd = new CustomPair(3, 1);
			inventoryItems.add(inventoryItemToAdd);
			CustomPair inventoryItemToAdd2 = new CustomPair(1, -1);
			inventoryItems.add(inventoryItemToAdd2);

		} else if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(1)) == 3) {
			// out += "Double Patty\n";
			CustomPair inventoryItemToAdd2 = new CustomPair(1, 1);
			inventoryItems.add(inventoryItemToAdd2);
			totalOrderCost += db.getPriceOfInventoryItem(1);
		}

		if (checkChanged(cNoneBurg, cRegBurg, cExtraBurg, recipe.get(2)) == 1) {
			// out += "No Cheese\n";
			CustomPair inventoryItemToAdd = new CustomPair(2, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(2)) == 2) {
			// out += "Add Cheese\n";
		} else if (checkChanged(patRegBurg, patBeanBurg, patDoubleBurg, recipe.get(2)) == 3) {
			// out += "Extra Cheese\n";
			CustomPair inventoryItemToAdd = new CustomPair(2, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(2);
		}

		if (!recipe.get(3).isSelected()) {
			if (sNoneBurg.isSelected()) {
				// out += "No Sauce\n";
				CustomPair inventoryItemToAdd = new CustomPair(4, -1);
				inventoryItems.add(inventoryItemToAdd);
			} else {
				// out += "Add Sauce\n";
				CustomPair inventoryItemToAdd = new CustomPair(4, 1);
				inventoryItems.add(inventoryItemToAdd);
				totalOrderCost += db.getPriceOfInventoryItem(4);
			}
		}

		if (checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 1) {
			// out += "No Pickles\n";
			CustomPair inventoryItemToAdd = new CustomPair(5, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 2) {
			// out += "Add Pickles\n";
		} else if (checkChanged(pNoneBurg, pRegBurg, pExtraBurg, recipe.get(4)) == 3) {
			// out += "Extra Pickles\n";
			CustomPair inventoryItemToAdd = new CustomPair(5, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(5);
		}

		if (checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 1) {
			// out += "No Lettuce\n";
			CustomPair inventoryItemToAdd = new CustomPair(6, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 2) {
			// out += "Add Lettuce\n";
		} else if (checkChanged(lNoneBurg, lRegBurg, lExtraBurg, recipe.get(5)) == 3) {
			// out += "Extra Lettuce\n";
			CustomPair inventoryItemToAdd = new CustomPair(6, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(6);
		}

		if (checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 1) {
			// out += "No Tomato\n";
			CustomPair inventoryItemToAdd = new CustomPair(7, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 2) {
			// out += "Add Tomato\n";
		} else if (checkChanged(tNoneBurg, tRegBurg, tExtraBurg, recipe.get(6)) == 3) {
			// out += "Extra Tomato\n";
			CustomPair inventoryItemToAdd = new CustomPair(7, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(7);
		}

		if (checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 1) {
			// out += "No Onion\n";
			CustomPair inventoryItemToAdd = new CustomPair(8, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 2) {
			// out += "Add Onion\n";
		} else if (checkChanged(oNoneBurg, oRegBurg, oExtraBurg, recipe.get(7)) == 3) {
			// out += "Extra Onion\n";
			CustomPair inventoryItemToAdd = new CustomPair(8, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(8);
		}

		if (checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 1) {
			// out += "No Bacon\n";
			CustomPair inventoryItemToAdd = new CustomPair(10, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 2) {
			// out += "Add Bacon\n";
		} else if (checkChanged(bNoneBurg, bRegBurg, bExtraBurg, recipe.get(8)) == 3) {
			// out += "Extra Bacon\n";
			CustomPair inventoryItemToAdd = new CustomPair(10, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(10);
		}
		return totalOrderCost;
	}

	/**
	 * runs comparison to recipe for modifications
	 * 
	 * @author Ariela
	 * @param recipe list of customizations
	 * @param out    text output to screen
	 * @return customization text
	 */
	String compareToBasketRecipe(ArrayList<RadioButton> recipe, String out) {
		// String out = "";
		if (!recipe.get(0).isSelected()) {
			if (cYesBask.isSelected()) {
				out += "Combo\n";
			}
		}

		if (checkChanged(gNoneBask, gRegBask, gExtraBask, recipe.get(1)) == 1) {
			out += "No Gravy\n";
		} else if (checkChanged(gNoneBask, gRegBask, gExtraBask, recipe.get(1)) == 3) {
			out += "Extra Gravy\n";
		}

		if (checkChanged(tNoneBask, tRegBask, tExtraBask, recipe.get(2)) == 1) {
			out += "No Toast\n";
		} else if (checkChanged(tNoneBask, tRegBask, tExtraBask, recipe.get(2)) == 3) {
			out += "Extra Toast\n";
		}

		return out;
	}

	/**
	 * @author Srikar
	 * @param recipe list of customizations
	 * @param out    text output to screen
	 * @return cost of item ordered
	 */
	double addToBasketRecipe(ArrayList<RadioButton> recipe, ArrayList<CustomPair> inventoryItems,
			double totalOrderCost) {
		// String out = "";
		if (!recipe.get(0).isSelected()) {
			if (cYesBask.isSelected()) {
				// out += "Combo\n";
				CustomPair menuItemToAdd = new CustomPair(12, 1);
				menuItems.add(menuItemToAdd);
				totalOrderCost += db.getPriceOfMenuItem(12);
			}
		}

		if (checkChanged(gNoneBask, gRegBask, gExtraBask, recipe.get(1)) == 1) {
			// out += "No Gravy\n";
			CustomPair inventoryItemToAdd = new CustomPair(15, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(gNoneBask, gRegBask, gExtraBask, recipe.get(1)) == 3) {
			// out += "Extra Gravy\n";
			CustomPair inventoryItemToAdd = new CustomPair(15, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(15);
		}

		if (checkChanged(tNoneBask, tRegBask, tExtraBask, recipe.get(2)) == 1) {
			// out += "No Toast\n";
			CustomPair inventoryItemToAdd = new CustomPair(13, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(tNoneBask, tRegBask, tExtraBask, recipe.get(2)) == 3) {
			// out += "Extra Toast\n";
			CustomPair inventoryItemToAdd = new CustomPair(13, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(13);
		}

		return totalOrderCost;
	}

	/**
	 * instantiates new item
	 * 
	 * @author Daniela Santos
	 * 
	 */
	void initNewItem() {
		if (db.getNumRows("menu") > 26) {
			sb1.setText(db.getNameFromID(27));
			sb1.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					orderList.add(db.getNameFromID(27) + "\n");

					CustomPair menuItemToAdd = new CustomPair(27, 1);
					menuItems.add(menuItemToAdd);
					totalOrderCost += db.getPriceOfMenuItem(27);
				}
			});
		} else {
			sb1.setVisible(false);
		}
		if (db.getNumRows("menu") > 27) {
			sb2.setText(db.getNameFromID(28));
			sb2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					orderList.add(db.getNameFromID(28) + "\n");

					CustomPair menuItemToAdd = new CustomPair(28, 1);
					menuItems.add(menuItemToAdd);
					totalOrderCost += db.getPriceOfMenuItem(28);
				}
			});
		} else {
			sb2.setVisible(false);
		}
		if (db.getNumRows("menu") > 28) {
			sb3.setText(db.getNameFromID(29));
			sb3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					orderList.add(db.getNameFromID(29) + "\n");

					CustomPair menuItemToAdd = new CustomPair(29, 1);
					menuItems.add(menuItemToAdd);
					totalOrderCost += db.getPriceOfMenuItem(29);
				}
			});
		} else {
			sb3.setVisible(false);
		}
		if (db.getNumRows("menu") > 29) {
			sb4.setText(db.getNameFromID(30));
			sb4.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					orderList.add(db.getNameFromID(30) + "\n");

					CustomPair menuItemToAdd = new CustomPair(30, 1);
					menuItems.add(menuItemToAdd);
					totalOrderCost += db.getPriceOfMenuItem(30);
				}
			});
		} else {
			sb4.setVisible(false);
		}
	}

	/**
	 * compares sandwich recipe to sandwich order for changes
	 * 
	 * @author Ariela
	 * @param recipe list of customizations
	 * @param out    text output to screen
	 * @return Custom order output
	 */
	String compareToSanRecipe(ArrayList<RadioButton> recipe, String out) {
		// String out = "";
		if (checkChanged(cNoSan, cChipSan, cRegSan, recipe.get(0)) == 2) {
			out += "Combo with chips\n";
		} else if (checkChanged(cNoSan, cChipSan, cRegSan, recipe.get(0)) == 3) {
			out += "Combo with fries\n";
		}

		if (checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 2) {
			out += "Sub Bean Patty\n";
		} else if (checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 3) {
			out += "Chicken Tenders\n";
		}

		if (checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 1) {
			out += "No Cheese\n";
		} else if (checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 2) {
			out += "Add Cheese\n";
		} else if (checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 3) {
			out += "Extra Cheese\n";
		}

		if (checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 1) {
			out += "No Sauce\n";
		} else if (checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 2) {
			out += "Sub Rev's Sauce \n";
		} else if (checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 3) {
			out += "Sub Spicy Sauce\n";
		}

		if (checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 1) {
			out += "No Pickles\n";
		} else if (checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 2) {
			out += "Add Pickles\n";
		} else if (checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 3) {
			out += "Extra Pickles\n";
		}

		if (checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 1) {
			out += "No Lettuce\n";
		} else if (checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 2) {
			out += "Add Lettuce\n";
		} else if (checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 3) {
			out += "Extra Lettuce\n";
		}

		if (checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 1) {
			out += "No Tomato\n";
		} else if (checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 2) {
			out += "Add Tomato\n";
		} else if (checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 3) {
			out += "Extra Tomato\n";
		}

		if (checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 1) {
			out += "No Onion\n";
		} else if (checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 2) {
			out += "Add Onion\n";
		} else if (checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 3) {
			out += "Extra Onion\n";
		}

		return out;
	}

	/**
	 * adds item to order
	 * 
	 * @param recipe         list of customizations
	 * @param inventoryItems list of items customized
	 * @param totalOrderCost cost of order
	 * @return cost of customized item
	 */
	double addToSanRecipe(ArrayList<RadioButton> recipe, ArrayList<CustomPair> inventoryItems, double totalOrderCost) {
		// String out = "";
		if ((checkChanged(cNoSan, cChipSan, cRegSan, recipe.get(0)) == 2)
				|| (checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 3)) {
			// out += "Combo with chips\n";
			CustomPair menuItemToAdd = new CustomPair(11, 1);
			menuItems.add(menuItemToAdd);
			totalOrderCost += db.getPriceOfMenuItem(11);
		}
		// else if (checkChanged(cNoSan, cChipSan, cRegSan, recipe.get(0)) == 3) {
		// out += "Combo with fries\n";
		// }

		if (checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 2) {
			// out += "Sub Bean Patty\n";
			CustomPair inventoryItemToAdd = new CustomPair(3, 1);
			inventoryItems.add(inventoryItemToAdd);
			CustomPair inventoryItemToAdd2 = new CustomPair(47, -1);
			inventoryItems.add(inventoryItemToAdd2);
		} else if (checkChanged(patRegSan, patBeanSan, patChickenSan, recipe.get(1)) == 3) {
			// out += "Chicken Tenders\n";
			CustomPair inventoryItemToAdd = new CustomPair(11, 1);
			inventoryItems.add(inventoryItemToAdd);
			CustomPair inventoryItemToAdd2 = new CustomPair(47, -1);
			inventoryItems.add(inventoryItemToAdd2);
		}

		if (checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 1) {
			// out += "No Cheese\n";
			CustomPair inventoryItemToAdd = new CustomPair(2, -1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(2);
		} else if (checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 2) {
			// out += "Add Cheese\n";
		} else if (checkChanged(cNoneSan, cRegSan, cExtraSan, recipe.get(2)) == 3) {
			// out += "Extra Cheese\n";
			CustomPair inventoryItemToAdd = new CustomPair(2, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(2);
		}

		if (checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 1) {
			// out += "No Sauce\n";
			CustomPair inventoryItemToAdd = new CustomPair(4, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 2) {
			// out += "Sub Rev's Sacue \n";
		} else if (checkChanged(sNoneSan, sRevSan, sSpicySan, recipe.get(3)) == 3) {
			// out += "Sub Spicy Sauce\n";
			CustomPair inventoryItemToAdd = new CustomPair(4, -1);
			inventoryItems.add(inventoryItemToAdd);
			CustomPair inventoryItemToAdd2 = new CustomPair(28, 1);
			inventoryItems.add(inventoryItemToAdd2);
		}

		if (checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 1) {
			// out += "No Pickles\n";
			CustomPair inventoryItemToAdd = new CustomPair(5, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 2) {
			// out += "Add Pickles\n";
		} else if (checkChanged(pNoneSan, pRegSan, pExtraSan, recipe.get(4)) == 3) {
			// out += "Extra Pickles\n";
			CustomPair inventoryItemToAdd = new CustomPair(5, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(5);
		}

		if (checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 1) {
			// out += "No Lettuce\n";
			CustomPair inventoryItemToAdd = new CustomPair(6, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 2) {
			// out += "Add Lettuce\n";
		} else if (checkChanged(lNoneSan, lRegSan, lExtraSan, recipe.get(5)) == 3) {
			// out += "Extra Lettuce\n";
			CustomPair inventoryItemToAdd = new CustomPair(6, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(6);
		}

		if (checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 1) {
			// out += "No Tomato\n";
			CustomPair inventoryItemToAdd = new CustomPair(7, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 2) {
			// out += "Add Tomato\n";
		} else if (checkChanged(tNoneSan, tRegSan, tExtraSan, recipe.get(6)) == 3) {
			// out += "Extra Tomato\n";
			CustomPair inventoryItemToAdd = new CustomPair(7, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(7);
		}

		if (checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 1) {
			// out += "No Onion\n";
			CustomPair inventoryItemToAdd = new CustomPair(8, -1);
			inventoryItems.add(inventoryItemToAdd);
		} else if (checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 2) {
			// out += "Add Onion\n";
		} else if (checkChanged(oNoneSan, oRegSan, oExtraSan, recipe.get(7)) == 3) {
			// out += "Extra Onion\n";
			CustomPair inventoryItemToAdd = new CustomPair(8, 1);
			inventoryItems.add(inventoryItemToAdd);
			totalOrderCost += db.getPriceOfInventoryItem(8);
		}

		return totalOrderCost;
	}

	/**
	 * 0 for default, 1 if none, 2 if extra
	 * for combo, 2 is chips and 3 is fries
	 * 
	 * @Ariela
	 * @param b1 button 1
	 * @param b2 button 2
	 * @param b3 button 3
	 * @param b4 button 4
	 * @return returns the state of the button combination, as integer
	 */
	int checkChanged(RadioButton b1, RadioButton b2, RadioButton b3, RadioButton b4) {
		if (b4.isSelected()) {
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
