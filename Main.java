package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	//this data i use in different places in code
	DoubleCircularLinkedList list = new DoubleCircularLinkedList();
	RadioButton rbtn1, rbtn2, rbtn3, rbtn4;
	Label lbl3;
	TextField txt3, txtf, txtu55, txtu66, txtu77, txtu88, txtd;
	Button btn2, next, btnNext, btnPrev;
	ArrayList<String> array = new ArrayList();
	Stage primaryStage;
	Node n;
	Note note = new Note();
	TextArea ta3, ta, ta4;
	ComboBox<String> cbox;
	Button yes = new Button("Yes");
	String selected;

	@Override
	public void start(Stage primaryStage) {
		//first Stage Shown To user in very Sinple Way
		
		Label title = new Label("Welcome To The Country Of The Martyrs ");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		Image m = new Image("C:\\Users\\user\\Desktop\\Afnan\\phase1\\src\\projectimage.png");
		ImageView img = new ImageView(m);
		Button btn1 = new Button("Get The Data From File");
		btn1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 10));
		btn1.setOnAction(e -> {
			readFromFile();

		});

		img.setFitHeight(600);
		img.setFitWidth(600);
		next = new Button("Next");
		next.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 10));
		try {
			next.setOnAction(e -> {
				primaryStage.close();
				stage2();
				

			});
		} catch (Exception e) {
			System.out.println("Read a file please");
		}
		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(title, img, btn1, next);
		vBox.setStyle("-fx-background:RED");
		vBox.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(vBox, 800, 800);
		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	// this Method to Read the File
	public void readFromFile() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*"));
		File selectedFile = chooser.showOpenDialog(primaryStage);
		SimpleDateFormat Format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Scanner scan = new Scanner(selectedFile);
			String line;
			while (scan.hasNext()) {
				line = scan.nextLine();
				String[] token = line.split(",");
				String name = token[0];
				int age = Integer.parseInt(token[1].trim());
				String location = token[2];
				Date date2 = Format.parse(token[3]);
				char gender = (token[4].charAt(0));
				Location location2 = new Location(location);
				Date_Stack date3 = new Date_Stack(date2);
				// location2.setLocation(location);
				Node node = list.search(location2);
				Martyr martyr = new Martyr(name, age, date2, gender);
				if (list.search(location2) == null) {
					// this condition to check if the location is exist or not
					list.insertAndSort(location2);
					list.search(location2).element.getAvl1().insertNode(martyr);
					array.add(location);
					System.out.println(location);
					System.out.println(name);

				} else {
					list.search(location2).element.getAvl1().insertNode(martyr);

				}
				if (list.search(location2).element.getTree().search(date3) == null) {
					list.search(location2).element.getTree().insertNode(new Date_Stack(date2));
					list.search(location2).element.getTree().search(date3).getStack().push(martyr);
				} else {
					list.search(location2).element.getTree().search(date3).getStack().push(martyr);
				}
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			System.out.println(" the file is not found");
		} catch (NumberFormatException e2) {
			System.out.println("there's no age found for someone");
		} catch (ParseException e3) {
			System.out.println("There's a wrong date");
		}

	}
//this method cotians a secand Stage apper to user also in simple Way
	public void stage2() {
		Stage stage2 = new Stage();
		TabPane tabs = new TabPane();
		Tab tab1 = new Tab("Location");
		Tab tab2 = new Tab("Martyrs");
		Tab tab3 = new Tab("Statistics");
		Tab tab4 = new Tab("Save");
		tabs.getTabs().addAll(tab1, tab2, tab3, tab4);
		tab1.setContent(tabOne());
		tab2.setContent(tabTwo());
		tab3.setContent(tabThree());
		tab4.setContent(tabFour());
		VBox vBox2 = new VBox(tabs);
		Scene s2 = new Scene(vBox2, 800, 800);
		stage2.setScene(s2);
		tabs.setStyle("-fx-background:LIGHTGREY");
		stage2.showAndWait();
	}
//method Tab one contians a locatin operation like add,delete ,update,Search
	public GridPane tabOne() {
		GridPane gpane = new GridPane();
		RadioButton rbtn1 = new RadioButton("insert");
		RadioButton rbtn2 = new RadioButton("update");
		RadioButton rbtn3 = new RadioButton("Delete");
		RadioButton rbtn4 = new RadioButton("Search");
		gpane.addColumn(1, rbtn1, rbtn2, rbtn3, rbtn4);
		lbl3 = new Label("Location");
		lbl3.setVisible(false);
		txt3 = new TextField();
		txt3.setVisible(false);
		btn2 = new Button("insert");
		btn2.setVisible(false);
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(lbl3, txt3);
		gpane.addRow(0, hbox, btn2);
		rbtn1.setOnAction(e -> {
			if (rbtn1.isSelected())
				lbl3.setVisible(true);
			txt3.setVisible(true);
			btn2.setVisible(true);
		});

		btn2.setOnAction(e -> {
			if (txt3.getText() == null) {
				note.no();
			} else if (txt3.getText() != null) {
				if ((list.search(new Location(txt3.getText())) == null)) {
					list.insertAndSort(new Location(txt3.getText()));
					note.massege(" ", "Dear user:Add This Location to List Done Successsful");
					array.add(txt3.getText());
					txt3.clear();

				} else

					note.display("Worning ", "This Location already Exist");
			}
		});

		TextField txtu1 = new TextField();
		TextField txtu2 = new TextField();
		Button btn3 = new Button("update");
		txtu1.setVisible(false);
		txtu2.setVisible(false);
		btn3.setVisible(false);
		gpane.addRow(1, txtu1, txtu2, btn3);
		rbtn2.setOnAction(e -> {
			if (rbtn2.isSelected())
				txtu1.setVisible(true);
			txtu2.setVisible(true);
			btn3.setVisible(true);
		});
		btn3.setOnAction(e -> {
			new Location(txtu1.getText());
			if (list.search(new Location(txtu1.getText())) == null) {
				note.error(" ", "Dear User This Location unvalid in your list");
			} else {
				list.update(txtu1.getText(), txtu2.getText());
				list.sort();
				note.massegeu("  ", "Dear user:update at This Location to List Done Successs");
				array.remove(txt3.getText());
				array.add(txtu2.getText());
			}
			txtu1.clear();
			txtu2.clear();

		});

		Button btn4 = new Button("Delete");
		txtd = new TextField();

		btn4.setVisible(false);
		yes = new Button();
		yes.setVisible(false);
		gpane.addRow(2, txtd, btn4);
		rbtn3.setOnAction(e -> {
			if (rbtn2.isSelected())
				txtd.setVisible(true);
			btn4.setVisible(true);
		});

		btn4.setOnAction(e -> {
			if (list.search(new Location(txtd.getText())) == null) {
				note.error(" ", "Dear User This Location unvalid in your list");
			} else {
				if (list.search(new Location(txtd.getText())).element.getAvl1() != null) {
					list.remove(new Location(txtd.getText()));
					note.delete2();

				}
				// ObservableList<String> options = FXCollections.observableArrayList(array);
				// cbox.setItems(options);
				txtd.clear();
			}
		});
		cbox = new ComboBox<String>();
		cbox.setVisible(false);
		ta = new TextArea();
		ta.setVisible(false);
		gpane.addRow(3, cbox, ta);
		ta.setText("");
		rbtn4.setOnAction(e -> {
			cbox.setVisible(true);
			ta.setVisible(true);

			cbox.getItems().addAll(array);
			ObservableList<String> options = FXCollections.observableArrayList(array);
			cbox.setItems(options);
			// cbox.setValue(cbox.getItems().get(0));
			// selected = cbox.getValue();
			ta.setVisible(true);
			ta.setText("");
		});
		cbox.setOnAction(e -> {
			ta.setVisible(true);
			String selected = cbox.getSelectionModel().getSelectedItem();
			StringBuilder sb = new StringBuilder();
			if (list.search(new Location(selected)).element.getAvl1() == null) {
				sb.append("This Location doesn't contain any martyres");
				ta.setText(sb.toString());

			} else {
				// sb.append(list.search(new Location(selected)).element.getAvl1().printTree());
				ta.setText(list.search(new Location(selected)).element.getAvl1().printTree());
				selected = cbox.getValue();
			}
		});
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(80);
		return gpane;
	}

//tab2 is contains a martyr information and operation like add,update,delete,search
	public BorderPane tabTwo() {
		BorderPane pane = new BorderPane();
		GridPane gpane = new GridPane();
		RadioButton rbtn1 = new RadioButton("insert");
		RadioButton rbtn2 = new RadioButton("update");
		RadioButton rbtn3 = new RadioButton("Delete");
		RadioButton rbtn4 = new RadioButton("Search");
		gpane.addRow(0, rbtn1, rbtn2, rbtn3, rbtn4);
		ToggleGroup group = new ToggleGroup();
		Label lbl1 = new Label("Name");
		Label lbl2 = new Label("age");
		Label lbl4 = new Label("Date of Death");
		Label lbl5 = new Label("Gender");
		lbl1.setVisible(false);
		lbl2.setVisible(false);
		lbl4.setVisible(false);
		lbl5.setVisible(false);
		rbtn1.setToggleGroup(group);
		rbtn2.setToggleGroup(group);
		rbtn3.setToggleGroup(group);
		rbtn4.setToggleGroup(group);
		TextField txt1 = new TextField();
		TextField txt2 = new TextField();
		TextField txt3 = new TextField();
		TextField txt5 = new TextField();
		txt1.setVisible(false);
		txt2.setVisible(false);
		txt3.setVisible(false);
		txt5.setVisible(false);
		Button btn2 = new Button("insert");
		btn2.setVisible(false);
		gpane.addRow(1, lbl1, txt1);
		gpane.addRow(2, lbl2, txt2);
		gpane.addRow(3, lbl4, txt3);
		gpane.addRow(4, lbl5, txt5);
		gpane.addRow(5, btn2);
		gpane.setHgap(20);
		gpane.setVgap(20);
		gpane.setAlignment(Pos.CENTER);
		rbtn1.setOnAction(e -> {
			if (rbtn1.isSelected())
				lbl1.setVisible(true);
			txtu55 = new TextField();
			txtu55.setVisible(false);
			txtu66 = new TextField();
			txtu66.setVisible(false);
			txtu77 = new TextField();
			txtu77.setVisible(false);
			txtu88 = new TextField();
			txtu88.setVisible(false);
			lbl2.setVisible(true);
			lbl4.setVisible(true);
			lbl5.setVisible(true);
			txt1.setVisible(true);
			txt2.setVisible(true);
			txt3.setVisible(true);
			txt5.setVisible(true);
			btn2.setVisible(true);
		});
		pane.setCenter(gpane);
		btn2.setOnAction(e -> {
			SimpleDateFormat Format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				if (list.search(new Location(cbox.getValue())) != null) {
					list.search(new Location(cbox.getValue())).element.getAvl1().insertNode(new Martyr(txt1.getText(),
							Integer.parseInt(txt2.getText()), Format.parse(txt3.getText()), txt5.getText().charAt(0)));
					if (list.search(new Location(cbox.getValue())).element.getTree()
							.search(new Date_Stack(Format.parse(txt3.getText()))) == null) {
						list.search(new Location(cbox.getValue())).element.getTree()
								.insertNode(new Date_Stack(Format.parse(txt3.getText())));
						list.search(new Location(cbox.getValue())).element.getTree()
								.search(new Date_Stack(Format.parse(txt3.getText()))).stack
								.push(new Martyr(txt1.getText(), Integer.parseInt(txt2.getText()),
										Format.parse(txt3.getText()), txt5.getText().charAt(0)));
					} else {
						list.search(new Location(cbox.getValue())).element.getTree()
								.search(new Date_Stack(Format.parse(txt3.getText()))).stack
								.push(new Martyr(txt1.getText(), Integer.parseInt(txt2.getText()),
										Format.parse(txt3.getText()), txt5.getText().charAt(0)));
					}
					note.massege(" ", "Dear user:Add This  to List Done Successsful");

				}
			} catch (NumberFormatException e1) {
				note.no();
				e1.printStackTrace();
			} catch (Exception e1) {
				note.no();
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt5.clear();
		});
		txt1.setVisible(false);
		txt2.setVisible(false);
		txt3.setVisible(false);
		txt5.setVisible(false);
		txtu55 = new TextField();
		txtu55.setVisible(false);
		txtu66 = new TextField();
		txtu66.setVisible(false);
		txtu77 = new TextField();
		txtu77.setVisible(false);
		txtu88 = new TextField();
		txtu88.setVisible(false);
		Button btn3 = new Button("update");
		btn3.setVisible(false);
		gpane.addColumn(2, txtu55, txtu66, txtu77, txtu88);
		gpane.add(btn3, 2, 6);
		rbtn2.setOnAction(e -> {
			btn2.setVisible(false);
			txt1.setVisible(true);
			txt2.setVisible(true);
			txt3.setVisible(true);
			txt5.setVisible(true);
			txtu55.setVisible(true);
			txtu66.setVisible(true);
			txtu77.setVisible(true);
			txtu88.setVisible(true);
			btn3.setVisible(true);
		});
		btn3.setOnAction(e -> {
			SimpleDateFormat Format = new SimpleDateFormat("MM/dd/yyyy");
			AvlTree list1 = list.search(new Location(cbox.getValue())).element.getAvl1();
			AvlTreeTwo list2 = list.search(new Location(cbox.getValue())).element.getTree();

			try {
				if (list2.search(new Date_Stack(Format.parse(txtu77.getText()))) == null) {
					note.dMartyr(" ", "Dear User This Martye not vaild in your list");
				} else {
					list2.updateNode(Format.parse(txt3.getText()),
							new Martyr(txt1.getText(), Integer.parseInt(txt2.getText()), Format.parse(txt3.getText()),
									txt5.getText().charAt(0)),
							new Martyr(txtu55.getText(), Integer.parseInt(txtu66.getText()),
									Format.parse(txtu77.getText()), txtu88.getText().charAt(0)));
				}
				note.massegeu(" ", "update done successfully");
				if (list1.update(
						new Martyr(txt1.getText(), Integer.parseInt(txt2.getText()), Format.parse(txt3.getText()),
								txt5.getText().charAt(0)),
						new Martyr(txtu55.getText(), Integer.parseInt(txtu66.getText()), Format.parse(txtu77.getText()),
								txtu88.getText().charAt(0))) == true)
					;
				{
					note.massegeu(" ", "update done successfully");
					txt1.clear();
					txt2.clear();
					txt3.clear();
					txt5.clear();
					txtu55.clear();
					txtu66.clear();
					txtu77.clear();
					txtu88.clear();
				}
				if (list1.update(
						new Martyr(txt1.getText(), Integer.parseInt(txt2.getText()), Format.parse(txt3.getText()),
								txt5.getText().charAt(0)),
						new Martyr(txtu55.getText(), Integer.parseInt(txtu66.getText()), Format.parse(txtu77.getText()),
								txtu88.getText().charAt(0))) == false) {
					note.dMartyr(" ", "Dear User This Martye not vaild in your list");
					txt1.clear();
					txt2.clear();
					txt3.clear();
					txt5.clear();
					txtu55.clear();
					txtu66.clear();
					txtu77.clear();
					txtu88.clear();
				}

			} catch (NumberFormatException e1) {
				note.no();
			} catch (Exception e1) {
				note.no();
			}

		});
		Button btn4 = new Button("Delete");
		btn4.setVisible(false);

		rbtn3.setOnAction(e -> {
			txtu55.setVisible(false);
			txtu66.setVisible(false);
			txtu77.setVisible(false);
			txtu88.setVisible(false);
			txt1.setVisible(true);
			txt2.setVisible(true);
			txt3.setVisible(true);
			txt5.setVisible(true);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(true);
		});

		btn4.setOnAction(e -> {
			Stack temp = new Stack();
			SimpleDateFormat Format = new SimpleDateFormat("MM/dd/yyyy");

			Martyr martyr2;
			try {
				martyr2 = new Martyr(txt1.getText(), Integer.parseInt(txt2.getText()), Format.parse(txt3.getText()),
						txt5.getText().charAt(0));
				System.out.println(martyr2);
				if (list.search(new Location(cbox.getValue())).element.getAvl1().search(martyr2) != null) {
					System.out.println("h*i");
					list.search(new Location(cbox.getValue())).element.getAvl1().deleteAvlNode(martyr2);
					note.delete();
				} else {
					note.dTree();
				}

				if (list.search(new Location(cbox.getValue())).element.getTree()
						.search(new Date_Stack(Format.parse(txt3.getText()))) != null) {
					while (!list.search(new Location(cbox.getValue())).element.getTree()
							.search(new Date_Stack(Format.parse(txt3.getText()))).stack.isEmpty()) {
						Martyr mart = list.search(new Location(cbox.getValue())).element.getTree()
								.search(new Date_Stack(Format.parse(txt3.getText()))).stack.pop();
						if (mart.getName().trim().compareToIgnoreCase(martyr2.getName()) == 0
								&& mart.getAge() == martyr2.getAge()
								&& (mart.getDateOfDeath().compareTo(martyr2.getDateOfDeath()) == 0)
								&& mart.getGender() == martyr2.getGender()) {
							note.delete();
							System.out.println("he");
						} else {
							temp.push(mart);
						}

					}
					while (!temp.isEmpty()) {
						temp.pop();
						list.search(new Location(cbox.getValue())).element.getTree()
								.search(new Date_Stack(Format.parse(txt3.getText()))).stack.push(martyr2);

					}
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		rbtn4.setOnAction(e -> {
			if (rbtn4.isSelected()) {
				btnSearch();
				// printS();
				// print();
			}
		});
		gpane.add(btn4, 3, 6);
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(30);
		pane.setCenter(gpane);
		return pane;
	}

	public void btnSearch() {
		StringBuilder sb = new StringBuilder();
		GridPane grid = new GridPane();
		TextField txtss = new TextField();
		grid.add(txtss, 1, 1);
		TextArea ta2 = new TextArea();
		grid.add(ta2, 2, 2);
		Button btn5 = new Button("Search");
		grid.add(btn5, 3, 3);
		btn5.setOnAction(e -> {
			ta.clear();
			if (list.search(new Location(cbox.getSelectionModel().getSelectedItem())).element.getAvl1()
					.searchbyName(txtss.getText()) == null) {
				ta2.setVisible(true);
				sb.append("Sorry can't find martyrs starting in this name");
				ta2.setText(sb.toString());
			} else {
				ta2.setVisible(true);
				sb.append(list.search(new Location(cbox.getValue())).element.getAvl1().searchbyName(txtss.getText()));
						
				ta2.setText(sb.toString());
			}
		});
		Scene s3 = new Scene(grid, 600, 600);
		Stage Stage3 = new Stage();
		Stage3.setScene(s3);
		Stage3.showAndWait();

	}

	public void print2() {
		Node head = list.getFirst();
		// NodeAvl2 v = list.getFirst().element.getTree().getRoot().right;
		// System.out.println(v.height);
		if (head == null) {
			return;
		}
		Node current = head;
		while (true) {
			System.out.println(current.element.getLocation() + " ");
			current.element.getTree().printTree3();
			System.out.println(current.element.getTree().height());
			current = current.next;
			if (current == head) {
				break;
			}
		}
	}

	public void print() {
		Node head = list.getFirst();
		AvlNode v = list.getFirst().element.getAvl1().getRoot().right;
		System.out.println(v.height);
		if (head == null) {
			return;
		}
		Node current = head;
		while (true) {
			System.out.println(current.element.getLocation() + " ");
			current.element.getAvl1().printTree2();
			System.out.println(current.element.getAvl1().height());
			current = current.next;
			if (current == head) {
				break;
			}
		}
	}

	public GridPane tabThree() {
		selected = cbox.getValue();
		ta4 = new TextArea();
		ta4.setPrefRowCount(450);
		ta4.setPrefColumnCount(450);
		GridPane gpane = new GridPane();
		gpane.add(ta4, 1, 1);
		Button btnPrev = new Button("prev");
		Button btnNext = new Button("next");
		gpane.add(btnNext, 0, 2);
		gpane.setHgap(20);
		gpane.setVgap(40);
		gpane.add(btnPrev, 1, 2);
		ta4.setFont(new Font("Arial", 15));
		ta4.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		StringBuilder string = new StringBuilder();
		// Check if the selected location exists in the list
		Node current = list.getFirst();
		// list.search(new Location(selected));
		// System.out.println(current);
		if (current != null && current.element != null) {
			string.append("The Height of AVL1: " + current.element.getAvl1().height() + "\n");
			string.append("The Height of AVL2: " + current.element.getTree().height() + "\n");
			string.append("Print AVL in backWord order: " + current.element.getTree().stackinorder() + "\n");
			string.append("Print the Martyrs Number:" + countNodes(current.element.getAvl1()) + "\n");
			string.append("Print AVL in levels: " + printAVLLevelByLevel(current.element.getAvl1()) + "\n");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date maxDate = current.element.getTree().getMaxMartyrsDate();
			String dateString = dateFormat.format(maxDate);
			string.append("max Date have Martyr: " + dateString + "\n");
			ta4.setText(string.toString());
		} else {
			string.append("no");
			ta4.setText(string.toString());
			System.out.println("hhh");
		}
		btnNext.setOnAction(e -> {
			next();

		});
		btnPrev.setOnAction(e -> {
			prev();

		});
		return gpane;
	}

	public void next() {
		StringBuilder string = new StringBuilder();
		Node current = list.getFirst().next;

		if (current != null && current.element != null) {
			string.append("The Height of AVL1: " + current.element.getAvl1().height() + "\n");
			string.append("The Height of AVL2: " + current.element.getTree().height() + "\n");
			string.append("Print AVL in backWord order: " + current.element.getTree().stackinorder() + "\n");
			string.append("Print the Martyrs Number:" + countNodes(current.element.getAvl1()) + "\n");
			string.append("Print AVL in levels: " + printAVLLevelByLevel(current.element.getAvl1()) + "\n");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date maxDate = current.element.getTree().getMaxMartyrsDate();
			String dateString = dateFormat.format(maxDate);
			string.append("max Date have Martyr: " + dateString + "\n");

			ta4.setText(string.toString());
		}
	}

	public void prev() {
		StringBuilder string = new StringBuilder();
		Node current = list.getFirst().prev;
		if (current != null && current.element != null) {
			string.append("The Height of AVL1: " + current.element.getAvl1().height() + "\n");
			string.append("The Height of AVL2: " + current.element.getTree().height() + "\n");
			string.append("Print AVL in backWord order: " + current.element.getTree().stackinorder() + "\n");
			string.append("Print the Martyrs Number:" + countNodes(current.element.getAvl1()) + "\n");
			string.append("The AVL in levels: " + printAVLLevelByLevel(current.element.getAvl1()) + "\n");
			Date maxDate = current.element.getTree().getMaxMartyrsDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String dateString = dateFormat.format(maxDate);
			string.append("max Date have Martyr: " + dateString + "\n");

			ta4.setText(string.toString());
		}
	}
//		btnNext.setOnAction(e -> {
//			// move to the next location in the double linked list
//			ta4.setText("");
//			if (list.search(new Location(cbox.getValue())).next != null) {
//				selectedLocation = list.search(new Location(cbox.getValue())).next.element.getLocation();
//				uploadBT.setText(
//						"Upload the summary report for the selected location" + "(" + selectedLocation + ")");
//				locationl.setText(selectedLocation);
//			} else {
//				dialog(AlertType.ERROR, "There is no next location");
//			}
//		});
//		}
	// Button preNext = new Button("Previos");
	// btnNext.setOnAction(e -> {
	// current =current.next; });

	// bulid.append(showData(new Location(cbox.getValue())));
	// ta4.setText(bulid.toString());
	// return gpane;
	// }

	/*
	 * public String showData(Location obj) { Node current = list.search(new
	 * Location(cbox.getValue())); String text; text
	 * +=("The height for Scelected location in Avl1 is:"+(current.element.getAvl1()
	 * .height())+"The height location in Avl2 is:"+
	 * (current.element.getTree().height())+"print Avl backWord:"+current.element.
	 * getTree().stackinorder()); return text;
	 * 
	 * }
	 */
	// public String showData(Location obj) {
	// Node current = list.search(new Location(cbox.getValue()));
	// String result = " ";
	// if (list.search(new Location(cbox.getValue())) != null) {
	// result += ("The Height of AVL1:" + list.search(new
	// Location(cbox.getValue())).element.getAvl1().height() + "\n" + "The Height of
	// AVL1:"
	// +list.search(new Location(cbox.getValue())).element.getTree().height() + "\n"
	// + "print Avl backWord:"
	// + list.search(new Location(cbox.getValue())).element.getTree().stackinorder()
	// + "\n");
	// }
	// return result;

//	}
//this tad to save Data in file using File chooser
	public VBox tabFour() {
		Label lblsave = new Label("To save you Data in file Please Dear user Click to button");
		lblsave.setFont(Font.font("Times New Romman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		Button btnSave = new Button("Click");
		btnSave.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
		btnSave.setOnAction(e -> {
			//printToFile();
			showtheFile();
		});
		VBox vbox1 = new VBox(50);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.getChildren().addAll(lblsave, btnSave);
		return vbox1;

	}
//method to saving 
	public void showtheFile() {
		Node current = list.getFirst();
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
		chooser.getExtensionFilters().add(textFilter);
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*"));
		File selectedFile = chooser.showOpenDialog(primaryStage);

		if (selectedFile != null) { // Check if a file was selected
			try (PrintWriter writer = new PrintWriter(new FileWriter(selectedFile))) {
				if (current == null) {
					writer.println("Linked list is empty.");
				} else {

					do {
						writer.println("Node Data: " + current.element);
						printAVLTreeToFile(current.element.getAvl1().getRoot(), writer);
						writer.println("------******------******-------");
						current = current.next;
					} while (current != list.getFirst());
				}

				writer.close();
				System.out.println(" printed to file successfully.");
			} catch (IOException e) {
				System.out.println("Error writing to file: " + e.getMessage());
			}
		}

	}

	private void printAVLTreeToFile(AvlNode node, PrintWriter writer) {
		if (node != null) {
			printAVLTreeToFile(node.left, writer);
			writer.println(node.target);
			printAVLTreeToFile(node.right, writer);
		}
	}

	public void printToFile() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*"));
		File selectedFile = chooser.showOpenDialog(primaryStage);
		File file = chooser.showOpenDialog(primaryStage);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			Node current = list.getFirst();
			int x = 1;
			do {
				if (current.element.getAvl1() != null && current.element.getAvl1().getRoot() != null) {
					current.element.getAvl1().printAvlTree(writer);
				}
				/*
				 * Martyr martyr = current.element.getAvl1().getRoot().target; String name =
				 * martyr.getName(); int age = martyr.getAge(); String district =
				 * current.element.getLocation(); Date dateOfDeath = martyr.getDateOfDeath();
				 * char gender = martyr.getGender();
				 * 
				 * String line = name + ", " + age + ", " + district + ", " + dateOfDeath + ", "
				 * + gender;
				 */
				// writer.write(line);
				// writer.newLine();

				current = current.next;
				x++;
			} while (current != list.getFirst());

			// writer.newLine();
			// writer.write("AVL Tree:");
			writer.close();
			// printAvlTree(current.element.getAvl1().getRoot(), writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printS() {
		String s = "1/22/2022";
		try {
			Date d = new SimpleDateFormat("MM/dd/yyy").parse(s);
			Date_Stack date = new Date_Stack(d);

			while (!list.search(new Location("Nablus")).element.getTree().search(date).stack.isEmpty())

			{
				System.out.println(list.search(new Location("Nablus")).element.getTree().search(date).stack.pop());
				System.out.println(list.search(new Location("Nablus")).element.getTree().height());
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}
//this method to print Data level by level
	public String printAVLLevelByLevel(AvlTree tree) {
		return printAVLLevelByLevel(tree.getRoot());

	}

	private String printAVLLevelByLevel(AvlNode node) {
		StringBuilder s = new StringBuilder();
		if (node == null) {
			return null;
		}
		Queue q = new Queue();
		q.enqueue(node);
		while (!q.isEmpty()) {
			AvlNode curr = q.dequeue();
			s.append(curr.toString() + "\n");
			if (curr.left != null) {
				q.enqueue(curr.left);
			}
			if (curr.right != null) {
				q.enqueue(curr.right);
			}
		}
		return s.toString();
	}
//this method to count the nodes 
	public int countNodes(AvlTree avlTree) {
		return countNodes(avlTree.getRoot());
	}

	private int countNodes(AvlNode node) {
		if (node == null) {
			return 0;
		}

		int leftCount = countNodes(node.left);
		int rightCount = countNodes(node.right);

		return 1 + leftCount + rightCount;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
