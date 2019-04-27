import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage; 
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.event.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MPaint extends Application{
	
	Stage stage;
	Pane startMenuPane;
	
	//the scenes
	Scene scene;
	Scene newScene;
	Scene aboutScene;
	
	private Button SP_btnNew;	
	private Button SP_btnAbout;
	
	private Button btnBack;
	private Button btnMinimize;
	private Button btnClose;
	
	double dragX = 0;
	double dragY = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage mainStage) {
		
		stage = mainStage;

		/*Creating the main scene*/
		startMenuPane = startPane();
		scene = new Scene(startMenuPane, 1100, 650);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		newScene = new Scene(new newPane(), 1100, 650);
		newScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		Pane about = new aboutPane();
		aboutScene = new Scene(about, 1100, 650);
		aboutScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		SP_btnNew.setOnAction(e -> {
			mainStage.setScene(newScene);
		});		
		
		SP_btnAbout.setOnAction(e -> {
			((aboutPane)about).fadeIn();
			mainStage.setScene(aboutScene);
		});	
		
		
		mainStage.initStyle(StageStyle.UNDECORATED);
		mainStage.setScene(scene);
		mainStage.show();
	
	}
	
	public Pane startPane() {
		//menu pane
		Pane startMenuPane = new Pane();
		startMenuPane.setLayoutX(0);
		startMenuPane.setLayoutY(50);
		startMenuPane.setPrefSize(1100, 550);
		startMenuPane.getStyleClass().add("start-menu-pane");
		
		Label SP_title = new Label("MPAINT");
		SP_title.getStyleClass().add("heading");
		SP_title.setLayoutX(450);
		SP_title.setLayoutY(100);
		SP_title.toFront();
		
		SP_btnNew = new Button("New");
		SP_btnNew.setLayoutX(450);SP_btnNew.setLayoutY(350);
		SP_btnNew.setOnAction(e -> {
			
		});
		SP_btnAbout = new Button("About");
		SP_btnAbout.setLayoutX(575);SP_btnAbout.setLayoutY(350);
		
				
				
		//start menu pane
		Pane startPane = new Pane();
		startPane.getStyleClass().add("start-pane");
		
		//startmenupane dots animation
		Circle[] circles = new Circle[400];
		for(int i = 0; i < circles.length; i++) {
			circles[i] = new Circle();
		}
		addAnimationDots(startPane, circles);
		
		Timeline startTwinkle1 = new Timeline(new KeyFrame(Duration.millis(200), e -> twinkle(circles)));
		startTwinkle1.setCycleCount(Timeline.INDEFINITE);
		startTwinkle1.play();
		
		
		Timeline shooting = new Timeline(new KeyFrame(Duration.millis(1000), e -> shooting(circles)));
		shooting.setCycleCount(Timeline.INDEFINITE);
		shooting.play();
		
		/*Rectangle astronaut = new Rectangle(10, 10, 50, 64);
		astronaut.setFill(new ImagePattern(new Image("assets/astronaut.png")));*/
		
		Circle star1 = new Circle(565.0, 60, 10);
		star1.setFill(new ImagePattern(new Image("assets/star.png")));
		Circle star4 = new Circle(565.0, 230, 10);
		star4.setFill(new ImagePattern(new Image("assets/star.png")));
		
		Circle star2 = new Circle(730.0, 145, 10);
		star2.setFill(new ImagePattern(new Image("assets/star.png")));
		star2.setRotate(90);
		Circle star3 = new Circle(400.0, 145, 10);
		star3.setFill(new ImagePattern(new Image("assets/star.png")));
		star3.setRotate(180);
		
		Circle star5 = new Circle(445.0, 345, 10);
		star5.setFill(new ImagePattern(new Image("assets/star.png")));
		star5.setRotate(90);
		
		Circle star6 = new Circle(675.0, 345, 10);
		star6.setFill(new ImagePattern(new Image("assets/star.png")));
		star6.setRotate(180);
		
		TranslateTransition stars1 = new TranslateTransition(Duration.millis(2000), star1);
		stars1.setByX(0);stars1.setByY(170);stars1.setAutoReverse(true);stars1.setCycleCount(Timeline.INDEFINITE);stars1.play();
		
		TranslateTransition stars2 = new TranslateTransition(Duration.millis(2000), star2);
		stars2.setByX(-330);stars2.setByY(0);stars2.setAutoReverse(true);stars2.setCycleCount(Timeline.INDEFINITE);stars2.play();
		
		TranslateTransition stars3 = new TranslateTransition(Duration.millis(2000), star3);
		stars3.setByX(330);stars3.setByY(0);stars3.setAutoReverse(true);stars3.setCycleCount(Timeline.INDEFINITE);stars3.play();
		
		TranslateTransition stars4 = new TranslateTransition(Duration.millis(2000), star4);
		stars4.setByX(0);stars4.setByY(-170);stars4.setAutoReverse(true);stars4.setCycleCount(Timeline.INDEFINITE);stars4.play();
		
		TranslateTransition stars5 = new TranslateTransition(Duration.millis(2000), star5);
		stars5.setByX(115);stars5.setByY(60);stars5.setAutoReverse(true);stars5.setCycleCount(Timeline.INDEFINITE);stars5.play();
		
		TranslateTransition stars6 = new TranslateTransition(Duration.millis(2000), star6);
		stars6.setByX(-105);stars6.setByY(60);stars6.setAutoReverse(true);stars6.setCycleCount(Timeline.INDEFINITE);stars6.play();
		
		
		Timeline stars1a = new Timeline(new KeyFrame(Duration.millis(2000), e ->  {
			star1.toBack();
			star4.toFront();
			
			star3.toBack();
			star2.toFront();	
			
			star5.toFront();
			star6.toBack();
		}));
		stars1a.setAutoReverse(true);stars1a.setCycleCount(Timeline.INDEFINITE);stars1a.play();
		Timeline stars2a = new Timeline(new KeyFrame(Duration.millis(2000), e ->  {
			star1.toFront();
			star4.toBack();
			
			star3.toFront();
			star2.toBack();	
			
			star6.toFront();
			star5.toBack();
		}));
		stars2a.setDelay(Duration.millis(2000));stars2a.setAutoReverse(true);stars2a.setCycleCount(Timeline.INDEFINITE);stars2a.play();
				
		
		//window controls
		HBox startWindowControls = new WindowControls(0);
		
		
		startPane.setOnMousePressed(e -> {
			dragX = e.getScreenX() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getX();
			dragY = e.getScreenY() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getY();
		});
		
		startPane.setOnMouseDragged(e -> {
			stage.setX(e.getScreenX() - dragX);
			stage.setY(e.getScreenY() - dragY);
			
		});
		
		
		
		startMenuPane.getChildren().addAll(SP_title, SP_btnNew, SP_btnAbout, star1, star2, star3, star4, star5, star6);
		
		
		startPane.getChildren().addAll(startWindowControls, startMenuPane);
		return startPane;
	}
	
	public static void addAnimationDots(Pane pane, Circle[] circles) {
		
		for(int i = 0; i < circles.length; i++) {
			 double radius =  2;
			
			circles[i].setCenterX(0 + Math.random()*1100);
			circles[i].setCenterY(0 + Math.random()*650);
			
			circles[i].setRadius(radius);
			circles[i].setFill(Color.WHITE);

			pane.getChildren().add(circles[i]);
		}
		
	}
	
	public void twinkle(Circle[] circles) {
		int rndNum = (int)(Math.random()*circles.length);
		for(int i = 0; i < circles.length; i++) {
			circles[i].toBack();
			if(i == rndNum) {
				FadeTransition FT = new FadeTransition(Duration.millis(500), circles[i]);
				FT.setFromValue(1.0);
				FT.setToValue(0.5);
				FT.setCycleCount(8);
				FT.play();
			}
			
		}
	}
	
	public void shooting(Circle[] circles) {
		int rndNum = (int)(Math.random()*circles.length);
		for(int i = 0; i < circles.length; i++) {
			if(i == rndNum) {
				circles[i].setRadius(4);
				circles[i].setStyle("-fx-fill:#f4dc42");
				TranslateTransition FT = new TranslateTransition(Duration.millis(1000), circles[i]);
				FT.setByX(-1000);
				FT.setByY(1000);
				FT.play();
			}
		};
	}
	
	public class newPane extends Pane {
		
		private boolean colorEditMode = false;
		private String newColorHex = "#252525";
		private String currentColorHex = "#CE96A6";
		private String currentTool = "pencil-c";
		private String imageToolPath = "";
		
		/*CURSORS*/
		Circle pencilCCursor = new Circle();
		Rectangle pencilSCursor = new Rectangle();
		Rectangle eraserCursor = new Rectangle();
		Button bucketCursor = new Button();
		Button highlighterCursor = new Button();
		Circle ellipseCursor = new Circle();
		Rectangle rectangleCursor = new Rectangle();
		Text textCursor = new Text("");
		Text textCursor3D = new Text("");
		Circle patternBrushCursor = new Circle();
		Circle paintBrushCursor = new Circle();
		
		private double currentPencilCSize = 5;
		private double currentPencilSSize = 8;
		private double currentPolyDotSize = 2;
		private double currentHighlighterSize = 14;
		private double currentHighlighterOpacity = 0.3;
		private double currentEraserSize = 15;
		private double changeSize = 0;
		private double changeSizeText = 0;
		private String currentFontFamily = "Arial";
		private int currentFontSize = 40;
		private String currentText = "";
		private double currentText3DDistance = 1.5;
		private String currentPatternAsset= "resources/jungle.png";
		private double paintBrushSize = 30;
		private double currentPaintBrushChange = -0.2;
		private int imageShape = 0;
		private boolean polyActive = false;
		
		ArrayList<Double[]> poly = new ArrayList<Double[]>();
		int polyCount;
		
		//shapes
		private double changeStrokeSize = 2;
		private String strokeType = "square";
		private double strokeLength = 2;
		
		
		double insertX;
		double insertY;
		
		/*OBJECT LIST*/
		ArrayList<MPaintObject> MPaintObjects = new ArrayList<MPaintObject>();
		ObservableList<Integer> layersNames;
		CheckBox chkLayersHide;
		ListView<Integer> layersList;
		
		
		int layersCount = 0;
		int layersIndex = 0;
		
		HBox controls = new HBox();
		Button[][] colorButtons = new Button[8][8];
		Pane drawingPane = new Pane();
		
		/*new controls*/
		HBox newControls;
		VBox pencilSliderGroup;
		VBox highlighterSliderGroup;
		VBox strokeWSliderGroup;
		VBox strokeLSliderGroup;
		ComboBox<String> cbStrokeTypes;
		ComboBox<String> cbFonts;
		ComboBox<String> patternBrushTypes;
		VBox fontFamilyGroup;
		VBox textGroup;
		VBox fontSizeGroup;
		VBox text3DGroup;
		VBox paintBrushGroup;
		ComboBox<String> cbPaintBrushTypes;
		VBox imagePathGroup;
		VBox imageShapeGroup; 
		
		newPane(){
			this.getStyleClass().add("new-pane");
			
			bucketCursor.setStyle("-fx-graphic:url('/assets/bucket.png');-fx-background-color:transparent;");
			highlighterCursor.setStyle("-fx-graphic:url('/assets/highlighter.png');-fx-background-color:transparent;");
			
			/*window controls*/	
			HBox windowControls = new WindowControls(1);
			windowControls.setStyle("-fx-background-color:#E75A7C");
			btnBack.setOnAction(e -> backMPaint(e, 1));
			
			windowControls.setOnMousePressed(e -> {
				dragX = e.getScreenX() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getX();
				dragY = e.getScreenY() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getY();
			});
			
			windowControls.setOnMouseDragged(e -> {
				stage.setX(e.getScreenX() - dragX);
				stage.setY(e.getScreenY() - dragY);
				
			});
			
			/*new controls*/
			newControls = new HBox();
			newControls.getStyleClass().add("new-controls");
			newControls.setLayoutX(135);
			newControls.setAlignment(Pos.CENTER_LEFT);
			
			newControls.setOnMousePressed(e -> {
				dragX = e.getScreenX() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getX();
				dragY = e.getScreenY() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getY();
			});
			
			newControls.setOnMouseDragged(e -> {
				stage.setX(e.getScreenX() - dragX);
				stage.setY(e.getScreenY() - dragY);
				
			});
			
			//sliders
			pencilSliderGroup = new VBox();
			Label pencilSliderLabel = new Label("Size");
			Slider pencilSlider = new Slider(-4, 60, 0);
			pencilSlider.valueProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					changeSize = pencilSlider.getValue();
					currentPencilCSize = 5 + changeSize;
					currentPencilSSize = 8 + changeSize;
					currentHighlighterSize = 15 + changeSize;
					currentEraserSize = 15 + changeSize;
					currentPolyDotSize = 2 + changeSize;
				}
	        });
			pencilSliderGroup.getChildren().addAll(pencilSliderLabel, pencilSlider);
			
			highlighterSliderGroup = new VBox();
			Label highlighterLabel = new Label("Opacity");
			Slider highlighterSlider = new Slider(0.1, 0.5, 0.3);
			highlighterSlider.valueProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					double a = highlighterSlider.getValue();
					currentHighlighterOpacity = a;
				}
	        });
			highlighterSliderGroup.getChildren().addAll(highlighterLabel, highlighterSlider);
			
			paintBrushGroup = new VBox();
			Label paintBrushLabel = new Label("Size Change");
			Slider paintBrushSlider = new Slider(0.05, 0.5, 0.2);
			paintBrushSlider.valueProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					double a = paintBrushSlider.getValue();
					currentPaintBrushChange = a;
					
					if(cbPaintBrushTypes.getValue().equals("Increase")) {
						if(currentPaintBrushChange < 0) {
							currentPaintBrushChange *= -1;
						}
					}
					else {
						if(currentPaintBrushChange > 0) {
							currentPaintBrushChange *= -1;
						}
					}
				}
	        });
			paintBrushGroup.getChildren().addAll(paintBrushLabel, paintBrushSlider);
			
			
			
			strokeWSliderGroup = new VBox();
			Label strokeWSliderLabel = new Label("Stroke Width");
			Slider strokeWSlider = new Slider(0.5, 20, 2);
			strokeWSlider.valueProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					changeStrokeSize = strokeWSlider.getValue();
					
				}
	        });
			strokeWSliderGroup.getChildren().addAll(strokeWSliderLabel, strokeWSlider);
			
			strokeLSliderGroup = new VBox();
			Label strokeLSliderLabel = new Label("Stroke Length");
			Slider strokeLSlider = new Slider(2, 40, 2);
			strokeLSlider.valueProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					strokeLength = strokeLSlider.getValue();
					
				}
	        });
			strokeLSliderGroup.getChildren().addAll(strokeLSliderLabel, strokeLSlider);
			
			//combo boxes

			cbStrokeTypes = new ComboBox<String>();
			cbStrokeTypes.getItems().addAll("Square", "Round");
			cbStrokeTypes.setValue("Square");
			cbStrokeTypes.setOnAction(e -> {
				strokeType = cbStrokeTypes.getValue();
			});
			
			
			fontFamilyGroup = new VBox();
			Label fontFamilyLabel = new Label("Font Family");
			cbFonts = new ComboBox<String>();
			String[] fonts = Font.getFamilies().toArray(new String[0]);
			cbFonts.setValue(fonts[0]);
			for(int i = 0; i < fonts.length; i++) {
				cbFonts.getItems().add(fonts[i]);
			}
			cbFonts.setOnAction(e -> {
				currentFontFamily = cbFonts.getValue();
			});
			fontFamilyGroup.getChildren().addAll(fontFamilyLabel, cbFonts);
			
			patternBrushTypes = new ComboBox<String>();
			patternBrushTypes.getItems().addAll("Jungle", "Blue Flowers", "Orange Purple Flowers", "Earth", "Abstract", "Duc");
			patternBrushTypes.setValue("Jungle");
			patternBrushTypes.setOnAction(e -> {
				String currentPattern = patternBrushTypes.getValue();
				
				if(currentPattern.equals("Jungle")) {		
					currentPatternAsset = "resources/jungle.png";
				}
				else if(currentPattern.equals("Blue Flowers")) {
					currentPatternAsset = "resources/flowers.png";
				}
				else if(currentPattern.equals("Orange Purple Flowers")) {
					currentPatternAsset = "resources/flowers2.png";
				}
				else if(currentPattern.equals("Earth")) {
					currentPatternAsset = "resources/earth.png";
				}
				else if(currentPattern.equals("Abstract")) {
					currentPatternAsset = "resources/abstract.png";
				}
				else if(currentPattern.equals("Duc")) {
					currentPatternAsset = "resources/duc.png";
				}
				
			});

			cbPaintBrushTypes = new ComboBox<String>();
			cbPaintBrushTypes.getItems().addAll("Decrease", "Increase");
			cbPaintBrushTypes.setValue("Decrease");
			cbPaintBrushTypes.setOnAction(e -> {
				if(cbPaintBrushTypes.getValue().equals("Increase")) {
					if(currentPaintBrushChange < 0) {
						currentPaintBrushChange *= -1;
					}
				}
				else {
					if(currentPaintBrushChange > 0) {
						currentPaintBrushChange *= -1;
					}
				}
				
			});
			
			
			imageShapeGroup = new VBox();
			Label imageShapeLabel = new Label("Shape");
			ComboBox<String> cbImageShapes = new ComboBox<String>();
			cbImageShapes.getItems().addAll("Rectangle", "Circle");
			cbImageShapes.setValue("Rectangle");
			cbImageShapes.setOnAction(e -> {
				if(cbImageShapes.getValue().equals("Rectangle")) {
					imageShape = 0;
				}
				else {
					imageShape = 1;
				}
				
			});
			imageShapeGroup.getChildren().addAll(imageShapeLabel, cbImageShapes );
			/*textboxes*/
			textCursor.setText("|");
			textCursor3D.setText("|");
			textGroup = new VBox();
			Label textLabel = new Label("Text");
			TextField tfText = new TextField();
			textGroup.getChildren().addAll(textLabel, tfText);
			tfText.textProperty().addListener((observable, oldValue, newValue) -> {
			    currentText = newValue;
			    textCursor.setText(newValue+"|");
			    textCursor3D.setText(newValue+"|");
			});
			
			fontSizeGroup = new VBox();
			Label fontSizeLabel = new Label("Font Size");
			TextField tfFontSizeText = new TextField(""+currentFontSize);
			fontSizeGroup.getChildren().addAll(fontSizeLabel, tfFontSizeText);
			tfFontSizeText.textProperty().addListener((observable, oldValue, newValue) -> {
			   	int newFontSize = Integer.parseInt(newValue);
			   	if(newFontSize == 0) {
			   		newFontSize = 15;
			   	}
			   	currentFontSize = newFontSize;
			   	
			});
			
			text3DGroup = new VBox();
			Label textLabel3D = new Label("3D Distance");
			TextField tfText3D = new TextField();
			tfText3D.setText(""+currentText3DDistance);
			text3DGroup.getChildren().addAll(textLabel3D, tfText3D);
			tfText3D.textProperty().addListener((observable, oldValue, newValue) -> {
			    currentText3DDistance = Double.parseDouble(newValue);
			});
			
			imagePathGroup = new VBox();
			Label imagePathLabel = new Label("Image Path");
			TextField tfImagePath = new TextField();
			tfImagePath.setEditable(false);
			tfImagePath.setOnMouseClicked(e -> {
				try {
					FileChooser fileChooser = new FileChooser();
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif"));
					imageToolPath = fileChooser.showOpenDialog(stage).toURI().toString();
					tfImagePath.setText(imageToolPath);
				}
				catch(NullPointerException ex) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("No image was selected");
					alert.show();
				}
			});
			imagePathGroup.getChildren().addAll(imagePathLabel, tfImagePath);
			
			/*drawing pane event handling*/
			drawingPane.getStyleClass().add("drawing-pane");
			drawingPane.setLayoutY(50);
			drawingPane.setOnMouseClicked(e -> onMPaintObjectClicked(e));
			drawingPane.setOnMousePressed(e -> insert(e));
			drawingPane.setOnMouseDragged(e -> draw(e));
			drawingPane.setOnMouseEntered(e -> addCursor(e));
			drawingPane.setOnMouseMoved(e -> drawCursor(e));
			drawingPane.setOnMouseExited(e -> removeCursor(e));
			
			
			controls.getChildren().addAll(windowControls, newControls);
			
			/*toolbox*/
			VBox toolboxPane = new VBox();
			toolboxPane.setLayoutX(900);
			toolboxPane.setLayoutY(50);
			toolboxPane.getStyleClass().add("toolbox-pane");
			
			VBox colorPane = new VBox();
			colorPane.setStyle("-fx-pref-width:200;-fx-pref-height:100;");
			
			GridPane colorPalette = new GridPane();
			colorPalette.getStyleClass().add("color-palette");
			
			HBox colorControls = new HBox();
			
			String[][] defaultColorSet = {{"#050505", "#CA0CCA", "#e1e1e1", "#DB180F", "#25DB0F", "#F49D6E", "#3BCEAC","#337CA0"},
											{"#0086FC", "#F0BA03", "#F06003", "#5443C0", "#80441B","#5C415D","#331E36","#F71735"}}; 
			
			//color buttons
			
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < colorButtons.length; j++) {
					colorButtons[i][j] = new Button();
					colorButtons[i][j].getStyleClass().add("color-pane-button");
					colorButtons[i][j].setStyle(this.getStyle() + "-fx-background-color:"+defaultColorSet[i][j]+";");
					colorButtons[i][j].setOnAction(e -> onColorButtonClicked(e));
					colorPalette.add(colorButtons[i][j], j, i);
				}
			}
			
			TextField colorInput = new TextField("");
			colorInput.setStyle("-fx-pref-height:30px;-fx-pref-width:170px;-fx-text-box-border:transparent;-fx-background-radius:0;-fx-font-size:15px;"
					+ "-fx-text-fill:gray");
			
			Button colorSet = new Button("+");
			colorSet.setStyle("-fx-pref-height:30px;-fx-pref-width:30px;-fx-font-size:20px;-fx-background-radius:0;-fx-background-color:#252525;"
					+ "-fx-text-fill:#fff;-fx-padding:0;");
			//color edit mode 
			colorSet.setOnAction(e -> {
				colorEditMode = !colorEditMode;
				if(colorEditMode) {
					for(int i = 0; i < 2; i++) {
						for(int j = 0; j < colorButtons.length; j++) {
							colorButtons[i][j].setText("+");
						}
					}
				}
				else {
					for(int i = 0; i < 2; i++) {
						for(int j = 0; j < colorButtons.length; j++) {
							colorButtons[i][j].setText("");
						}
					}
				}
				
			});
			//get hex color from text field
			colorInput.setOnKeyTyped(e -> {
				     String colorInp = colorInput.getText();
				     
				     if(colorInp.length() == 6) {
				    	 colorSet.setStyle(colorSet.getStyle().replace("-fx-background-color:"+newColorHex+";", ""));
				    	 newColorHex = "#" + colorInp;
				    	 colorSet.setStyle(colorSet.getStyle() + "-fx-background-color:"+newColorHex+";");
				     }
				     else {
				    	 newColorHex = "#252525";
				    	 colorSet.setStyle(colorSet.getStyle().replace("-fx-background-color:"+newColorHex+";", ""));
				    	 colorSet.setStyle(colorSet.getStyle() + "-fx-background-color:"+newColorHex+";");
				     }
			});
			
			colorControls.getChildren().addAll(colorInput, colorSet);			
			colorPane.getChildren().addAll(colorPalette, colorControls);
			
			/*TOOLS*/
			GridPane toolPane = new GridPane();
			toolPane.getStyleClass().add("tool-pane");
			
			String[][] toolButtonIds = {{"pencil-c", "pencil-s", "highlighter", "eraser"},
										{"rectangle", "ellipse", "polygon", "cube"},
										{"line", "bucket", "pattern-brush", "paint-brush"},
										{"image", "text", "text-3D", "pewdiepie"}};
			Button[][] toolButtons = new Button[toolButtonIds.length][toolButtonIds[1].length];
			for(int i = 0; i < toolButtons.length; i++) {
				for(int j = 0; j < toolButtons[i].length;j++) {
					toolButtons[i][j] = new Button();
					toolButtons[i][j].setId(toolButtonIds[i][j]);
					toolButtons[i][j].setStyle("-fx-graphic:url('/assets/" + toolButtonIds[i][j] +".png');");
					toolButtons[i][j].setOnAction(e -> onToolButtonClicked(e));
					toolPane.add(toolButtons[i][j], j,i);
				}
			}
			
			/*layer select*/
			VBox layersPane = new VBox();
			layersPane.getStyleClass().add("layers-pane");
			
			Label layersLabel = new Label("Layers");
	
			layersNames = FXCollections.observableArrayList(layersIndex);	
			layersList = new ListView<Integer>(layersNames);
			layersList.getSelectionModel().select(0);
			layersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			layersList.getStyleClass().add("layers-list");
			
			chkLayersHide = new CheckBox("Hide Unselected"); 
			
			HBox layerButtonsPane = new HBox();
			layerButtonsPane.setSpacing(10);
			
			Button btnAddLayer = new Button("Add");
			
			layerButtonsPane.getChildren().addAll(btnAddLayer);
			
			chkLayersHide.setOnMouseClicked(e -> onLayersChanged());
			layersList.setOnMouseClicked(e -> onLayersChanged());
			
			btnAddLayer.setOnAction(e -> {
				layersCount += 1;
				layersNames.add(layersCount);
				layersList.getSelectionModel().clearAndSelect(layersCount);
				ObservableList<Integer> layersSelected = layersList.getSelectionModel().getSelectedItems();
				layersIndex = (int)layersSelected.sorted().get(layersSelected.size() - 1);
				onLayersChanged();
			});
			
			layersPane.getChildren().addAll(layersLabel, layersList, chkLayersHide, layerButtonsPane);

			
			/*save*/
			VBox savePane = new VBox();
			savePane.getStyleClass().add("save-pane");
			
			Label pathLabel = new Label("Folder");
			
			TextField tfPathName = new TextField();
			tfPathName.setEditable(false);
			
			Label saveLabel = new Label("Project Name");
			TextField tfSaveName = new TextField();
			
			
			tfPathName.setOnMouseClicked(e ->{
				try {
					DirectoryChooser dc = new DirectoryChooser();
					tfPathName.setText(dc.showDialog(stage).toString());
				}
				catch(NullPointerException ex) {
					if(tfPathName.getText().isEmpty()) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Alert!");
						alert.setHeaderText(null);
						alert.setContentText("No path has been choosen, The file would be saved in the project folder.");
						alert.showAndWait();
					}
					
				}
			});
			
			Button btnSave = new Button("Save");
			
			Label saveLabel2 = new Label("");
			saveLabel2.setWrapText(true);
			
			btnSave.setOnAction(e ->{
		    
			    try {
			    	String fileName = "";
					if(tfSaveName.getText().trim().isEmpty()) {
						int rnd =  1 + (int)(Math.random()*1000000);
						String rndHex = Integer.toHexString(rnd);
						
						fileName = "MyDrawing"+rndHex.toUpperCase();
						tfSaveName.setText(fileName);
					}
					else {
						fileName = "" + tfSaveName.getText(); 
					}
					
					String filePath = tfPathName.getText();
										
					WritableImage image = drawingPane.snapshot(new SnapshotParameters(), null);
				    
					String saveFilePath = "";
					if(!filePath.isEmpty()) {
						saveFilePath = filePath + "/" + fileName + ".png";
					}
					else {
						saveFilePath = fileName + ".png";
					}
				    File file = new File(saveFilePath);
			    	
			        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			        
			        saveLabel2.setText("Saved, yay!");
			        
			    }
			    catch (FileNotFoundException ex) {
			    	 saveLabel2.setText("Oops, something's wrong with the file path.");
			    }
			    catch (Exception ex) {
			    	 saveLabel2.setText("Oops, something's not right.");
			    }
		    
			});
			
			savePane.getChildren().addAll(pathLabel,tfPathName,saveLabel, tfSaveName, btnSave, saveLabel2);
			
			/*Page controls*/
			VBox pageControlsPane = new VBox();
			pageControlsPane.getStyleClass().add("page-controls-pane");
			
			Label pageControlLabel = new Label("Page");
			
			Button btnClear= new Button("Clear");
			btnClear.setOnAction(e -> clearPage());
			
			pageControlsPane.getChildren().addAll(pageControlLabel, btnClear);
					
			if(!newControls.getChildren().contains(pencilSliderGroup)) newControls.getChildren().add(pencilSliderGroup);
				
			toolboxPane.getChildren().addAll(colorPane, toolPane, layersPane, savePane, pageControlsPane);
			this.getChildren().addAll(controls, drawingPane, toolboxPane);
			
		} 
		
		private void onLayersChanged() {
			ObservableList<Integer> layersSelected = layersList.getSelectionModel().getSelectedItems();
			layersIndex = (int)layersSelected.sorted().get(layersSelected.size() - 1);
			
			if(chkLayersHide.isSelected()) {
				
				for(int i = 0; i < MPaintObjects.size(); i++) {

					if(!layersSelected.contains(((MPaintObject)(MPaintObjects.get(i))).layer)){
						if(drawingPane.getChildren().contains(((MPaintObject)(MPaintObjects.get(i))).node)){
							drawingPane.getChildren().remove(((MPaintObject)(MPaintObjects.get(i))).node);
							
						}
					}
					else {
						if(!drawingPane.getChildren().contains(((MPaintObject)(MPaintObjects.get(i))).node)){
							drawingPane.getChildren().add(((MPaintObject)(MPaintObjects.get(i))).node);
						}
					}
				}
			}
			else {
				for(int i = 0; i < MPaintObjects.size(); i++) {
					if(!drawingPane.getChildren().contains(((MPaintObject)(MPaintObjects.get(i))).node)){
						drawingPane.getChildren().add(((MPaintObject)(MPaintObjects.get(i))).node);
					}
				}
			}
		}
		
		private void onColorButtonClicked(ActionEvent e) {
			if(colorEditMode) {
				((Button)e.getSource()).setStyle(((Button)e.getSource()).getStyle().replace("-fx-background-color:"+newColorHex+";", ""));
				((Button)e.getSource()).setStyle(((Button)e.getSource()).getStyle() + "-fx-background-color:"+newColorHex+";");
				colorEditMode = false;
				
				for(int i = 0; i < 2; i++) {
					for(int j = 0; j < colorButtons.length; j++) {
						colorButtons[i][j].setText("");
					}
				}
			}
			else {
				currentColorHex = ((Button) e.getSource()).getStyle().substring(((Button) e.getSource()).getStyle().length()-8, ((Button) e.getSource()).getStyle().length()-1);
			}
		}
		
		@SuppressWarnings("unlikely-arg-type")
		private void onToolButtonClicked(ActionEvent e){
			
			if(newControls.getChildren().contains(strokeWSliderGroup)) newControls.getChildren().remove(strokeWSliderGroup);
			if(newControls.getChildren().contains(strokeLSliderGroup)) newControls.getChildren().remove(strokeLSliderGroup);
			if(newControls.getChildren().contains(cbStrokeTypes)) newControls.getChildren().remove(cbStrokeTypes);
			if(newControls.getChildren().contains(pencilSliderGroup)) newControls.getChildren().remove(pencilSliderGroup);
			if(newControls.getChildren().contains(highlighterSliderGroup)) newControls.getChildren().remove(highlighterSliderGroup);
			if(newControls.getChildren().contains(patternBrushTypes)) newControls.getChildren().remove(patternBrushTypes);
			if(newControls.getChildren().contains(paintBrushGroup)) newControls.getChildren().remove(paintBrushGroup);
			if(newControls.getChildren().contains(cbPaintBrushTypes)) newControls.getChildren().remove(cbPaintBrushTypes);
			if(newControls.getChildren().contains(textGroup)) newControls.getChildren().remove(textGroup);
			if(newControls.getChildren().contains(fontFamilyGroup)) newControls.getChildren().remove(fontFamilyGroup);
			if(newControls.getChildren().contains(fontSizeGroup)) newControls.getChildren().remove(fontSizeGroup);
			if(newControls.getChildren().contains(text3DGroup)) newControls.getChildren().remove(text3DGroup);
			if(newControls.getChildren().contains(imagePathGroup)) newControls.getChildren().remove(imagePathGroup);
			if(newControls.getChildren().contains(imageShapeGroup)) newControls.getChildren().remove(imageShapeGroup);
			
			currentTool = ((Button)(e.getSource())).getId();	
			
			if(currentTool == "pencil-c" || currentTool == "pencil-s" || currentTool == "eraser" || currentTool == "pattern-brush" || currentTool == "paint-brush"
					|| currentTool == "polygon" ) {
				if(!newControls.getChildren().contains(pencilSliderGroup)) newControls.getChildren().add(pencilSliderGroup);
				if(currentTool == "pattern-brush") {
					if(!newControls.getChildren().contains(patternBrushTypes)) newControls.getChildren().add(patternBrushTypes);
				}
				else if(currentTool == "paint-brush") {
					if(!newControls.getChildren().contains(paintBrushGroup)) newControls.getChildren().add(paintBrushGroup);
					if(!newControls.getChildren().contains(cbPaintBrushTypes)) newControls.getChildren().add(cbPaintBrushTypes);
				}
				else if(currentTool == "polygon") {
					
					if(!newControls.getChildren().contains(strokeWSliderGroup)) newControls.getChildren().add(strokeWSliderGroup);
					if(!newControls.getChildren().contains(strokeLSliderGroup)) newControls.getChildren().add(strokeLSliderGroup);
					if(!newControls.getChildren().contains(cbStrokeTypes)) newControls.getChildren().add(cbStrokeTypes);
					
					if(polyActive) {
						if(polyCount >= 2) {
							MPaintObjects.add(new MPaintObject("polygon", layersIndex, poly,changeStrokeSize, strokeLength, strokeType, currentColorHex));
							drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
						}
						
						ArrayList<Integer> removes = new ArrayList<Integer>();
						for(int i = 0; i < MPaintObjects.size(); i++) {

							if(((MPaintObject)MPaintObjects.get(i)).type == "polydots"){
								if(drawingPane.getChildren().contains(((MPaintObject)(MPaintObjects.get(i))).node)){
									drawingPane.getChildren().remove(((MPaintObject)(MPaintObjects.get(i))).node);			
									removes.add(i);
								}
							}
						}
						
						for(int i = 0; i < removes.size(); i++) {
							MPaintObjects.remove(removes.get(i));
						}
						
						poly.clear();
						polyCount = 0;

					}
					else {
						polyActive = !polyActive;
					}
					
				}
			}
			else if(currentTool == "highlighter") {
				if(!newControls.getChildren().contains(highlighterSliderGroup)) newControls.getChildren().add(highlighterSliderGroup);
				if(!newControls.getChildren().contains(pencilSliderGroup)) newControls.getChildren().add(pencilSliderGroup);
				
			}
			else if(currentTool == "ellipse" || currentTool == "rectangle" ||currentTool == "line" || currentTool == "cube"){
				if(!newControls.getChildren().contains(strokeWSliderGroup)) newControls.getChildren().add(strokeWSliderGroup);
				if(!newControls.getChildren().contains(strokeLSliderGroup)) newControls.getChildren().add(strokeLSliderGroup);
				if(!newControls.getChildren().contains(cbStrokeTypes)) newControls.getChildren().add(cbStrokeTypes);
			}
			else if(currentTool == "bucket") {	

			}
			else if(currentTool == "text" || currentTool == "text-3D") {
				
				if(!newControls.getChildren().contains(textGroup)) newControls.getChildren().add(textGroup);
				if(!newControls.getChildren().contains(fontFamilyGroup)) newControls.getChildren().add(fontFamilyGroup);
				if(!newControls.getChildren().contains(fontSizeGroup)) newControls.getChildren().add(fontSizeGroup);
				
				if(currentTool == "text-3D") {
					if(!newControls.getChildren().contains(text3DGroup)) newControls.getChildren().add(text3DGroup);
				}
				else {
					if(newControls.getChildren().contains(text3DGroup)) newControls.getChildren().remove(text3DGroup);
				}

			}
			else if(currentTool == "image") {
				if(!newControls.getChildren().contains(imagePathGroup)) newControls.getChildren().add(imagePathGroup);
				if(!newControls.getChildren().contains(imageShapeGroup)) newControls.getChildren().add(imageShapeGroup);
			}
			else if(currentTool == "pewdiepie") {
				
				MPaintObjects.add(new MPaintObject("pewdiepie", layersIndex, 0, 0, changeStrokeSize, strokeLength, strokeType, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
				
				MPaintObjects.add(new MPaintObject("text", layersIndex, 50, 150, changeSizeText, currentColorHex));
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setText("SUBSCRIBE");
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStyle("-fx-font-size:140;-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:#FFFFFF;-fx-font-weight:bold;");			
		
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
				
				MPaintObjects.add(new MPaintObject("text", layersIndex, 380, 330, changeSizeText, currentColorHex));
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setText("TO");
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStyle("-fx-font-size:140;-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:#FFFFFF;-fx-font-weight:bold;");			
		
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
				
				MPaintObjects.add(new MPaintObject("text", layersIndex, 70, 500, changeSizeText, currentColorHex));
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setText("PEWDIEPIE");
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStyle("-fx-font-size:140;-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:#FFFFFF;-fx-font-weight:bold;");			
		
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
				
			}
		} 
		
		private void insert(MouseEvent e) {
			insertX = e.getX();
			insertY = e.getY();
			drawingPane.toBack();
			controls.toFront();
			
			 if(currentTool == "ellipse") {
				MPaintObjects.add(new MPaintObject("ellipse", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			 }
			 else if(currentTool == "rectangle") {
				MPaintObjects.add(new MPaintObject("rectangle", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			 }
			 else if(currentTool == "image") {
				 if(imageShape == 0) {
					 MPaintObjects.add(new MPaintObject("rectangle", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, "transparent"));
				 }
				 else {
					 MPaintObjects.add(new MPaintObject("ellipse", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, "transparent"));
				 }
					drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			 }
			 else if(currentTool == "line") {
					MPaintObjects.add(new MPaintObject("line", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
					
					drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			 }
			 else if(currentTool == "cube") {
					MPaintObjects.add(new MPaintObject("rectangle", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
					MPaintObjects.add(new MPaintObject("rectangle", layersIndex, insertX+50, insertY+50, changeStrokeSize, strokeLength, strokeType, currentColorHex));
					
					MPaintObjects.add(new MPaintObject("line", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));					
					MPaintObjects.add(new MPaintObject("line", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
					MPaintObjects.add(new MPaintObject("line", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
					MPaintObjects.add(new MPaintObject("line", layersIndex, insertX, insertY, changeStrokeSize, strokeLength, strokeType, currentColorHex));
					
					for(int i = 1; i <= 6; i++) {
						drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - i)).node);
					}
					
				 }
			 else if(currentTool == "text") {
				MPaintObjects.add(new MPaintObject("text", layersIndex, insertX, insertY, changeSizeText, currentColorHex));
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setText(currentText);
				((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStyle("-fx-font-size:"+currentFontSize+";-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:"+currentColorHex+";");			
		
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			 
			 else if(currentTool == "text-3D") {
					MPaintObjects.add(new MPaintObject("text", layersIndex, insertX, insertY, changeSizeText, currentColorHex));
					((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setText(currentText);
					((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStyle("-fx-font-size:"+currentFontSize+";-fx-font-family:'"
							+ currentFontFamily+"';-fx-fill:"+currentColorHex+";");		
					MPaintObjects.add(new MPaintObject("text", layersIndex, insertX-currentText3DDistance, insertY-currentText3DDistance, changeSizeText, currentColorHex));
					((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setText(currentText);
					((Text)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStyle("-fx-font-size:"+currentFontSize+";-fx-font-family:'"
							+ currentFontFamily+"';-fx-fill:#252525;");			
					
					drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
					drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node);
			}
			 else if(currentTool == "paint-brush") {
				 	paintBrushSize = 5 + currentPencilCSize;
				 	paintBrushCursor.setRadius(paintBrushSize);
			 }
			 else if(currentTool == "polygon") {
				 if(polyActive) {
					 ++polyCount;
					 poly.add(new Double[] {insertX, insertY});
					 MPaintObjects.add(new MPaintObject("polydots", layersIndex, insertX, insertY, currentPolyDotSize, currentColorHex));
					 drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
				 }
			 }
			 			 
		}
		
		private void draw(MouseEvent e) {
			double x = e.getX();
			double y = e.getY();
			drawingPane.toBack();
			controls.toFront();
			
			if(currentTool == "pencil-c") {
				MPaintObjects.add(new MPaintObject("pencil-c", layersIndex, x, y, currentPencilCSize, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			else if(currentTool == "pencil-s") {
				MPaintObjects.add(new MPaintObject("pencil-s", layersIndex, x, y, currentPencilSSize, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			else if(currentTool == "pattern-brush") {
				MPaintObjects.add(new MPaintObject("pattern-brush", layersIndex, x, y, currentPencilSSize, "black"));
				Image im = new Image(currentPatternAsset,false);
				((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node.setFill(new ImagePattern(im));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			else if(currentTool == "highlighter") {
				MPaintObjects.add(new MPaintObject("highlighter", layersIndex, x, y, currentHighlighterSize, currentColorHex));
				((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node.setOpacity(currentHighlighterOpacity);
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			else if(currentTool == "eraser") {
				MPaintObjects.add(new MPaintObject("eraser", layersIndex, x, y, changeSize, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			else if(currentTool == "ellipse" || (currentTool == "image" && imageShape == 1)) {
				double radiusX = Math.abs(x - insertX);
				double radiusY = Math.abs(y - insertY);
				
				if(currentTool == "image") {
					try{
						Image im = new Image(imageToolPath,false);
						((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node.setFill(new ImagePattern(im));
					}
					catch(IllegalArgumentException ex) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Oopsies");
						alert.setHeaderText("Something isn't right.");
						alert.setContentText("Try choosing the image again.");
						
						alert.showAndWait();
					}	
				}
				
				((Ellipse)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setRadiusX(radiusX);
				((Ellipse)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setRadiusY(radiusY);
			}
			else if(currentTool == "rectangle" || (currentTool == "image" && imageShape == 0) ) {
				double width = x - insertX;
				double height = y - insertY;
				
				if(currentTool == "image") {
					try {
						Image im = new Image(imageToolPath,false);
						((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node.setFill(new ImagePattern(im));
					}
					catch(IllegalArgumentException ex) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Oopsies");
						alert.setHeaderText("Something isn't right.");
						alert.setContentText("Try choosing the image again.");
						
						alert.showAndWait();
					}	
				}
					
				if(width < 0) {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setX(x);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setWidth(width*-1.0);
				}
				else {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setWidth(width);
				}
				
				if(height < 0) {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setY(y);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setHeight(height*-1.0);
				}
				else {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setHeight(height);
				}
			}
			else if(currentTool == "cube") {
				double width = x - insertX;
				double height = y - insertY;
				
				
				
				if(width < 0) {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 5)).node).setX(x);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 5)).node).setWidth(width*-1.0);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 6)).node).setX(x+50);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 6)).node).setWidth(width*-1.0);
					
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStartX(x);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setEndX(x+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setStartX(x-width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setEndX(x+50-width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setStartX(x);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setEndX(x+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartX(x-width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndX(x+50-width);
					
				}
				else {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 5)).node).setWidth(width);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 6)).node).setWidth(width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setStartX(x+width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setEndX(x+50+width);
					
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setEndX(insertX);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStartX(insertX+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setEndX(insertX+width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setStartX(insertX+50+width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setStartX(insertX);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setEndX(insertX+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartX(insertX+width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndX(insertX+50+width);
					
				}
				
				if(height < 0) {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 5)).node).setY(y);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 5)).node).setHeight(height*-1.0);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 6)).node).setY(y+50);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 6)).node).setHeight(height*-1.0);
					
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStartY(y);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setEndY(y+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setStartY(y);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setEndY(y+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setStartY(y-height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setEndY(y+50-height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartY(y-height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndY(y+50-height);
				}
				else {
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 5)).node).setHeight(height);
					((Rectangle)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 6)).node).setHeight(height);
					
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setEndY(insertY);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setStartY(insertY+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setEndY(insertY);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 2)).node).setStartY(insertY+50);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setStartY(insertY + height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setEndY(insertY+50+height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartY(insertY + height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndY(insertY+50+height);
				}
				
				if(width < 0 && height > 0) {
					
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartX(x);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndX(x+50-width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setEndY(insertY+height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 3)).node).setStartY(insertY+50+height);
					
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndY(insertY+height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartY(insertY+50+height);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setStartX(x-width);
					((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 4)).node).setEndX(x+50-width);
				}

				
			}
			else if(currentTool == "line") {
				((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setEndX(x);
				((Line)((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node).setEndY(y);
			}
			else if(currentTool == "paint-brush") {
				paintBrushSize += currentPaintBrushChange;
				MPaintObjects.add(new MPaintObject("pencil-c", layersIndex, x, y, paintBrushSize, currentColorHex));
				drawingPane.getChildren().add(((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node);
			}
			
			
			drawCursor(e);
		}
		
		public void addCursor(MouseEvent e) {
			newScene.setCursor(Cursor.NONE);
			
			if(currentTool == "pencil-c") {
				pencilCCursor.setRadius(5 + currentPencilCSize);
				pencilCCursor.setStyle("-fx-fill:"+currentColorHex+";");
				drawingPane.getChildren().add(pencilCCursor);	
			}
			else if(currentTool == "paint-brush") {
				paintBrushSize = 5 + currentPencilCSize;
				paintBrushCursor.setRadius(paintBrushSize);
				paintBrushCursor.setStyle("-fx-fill:"+currentColorHex+";");
				drawingPane.getChildren().add(paintBrushCursor);	
			}
			else if(currentTool == "pencil-s") {
				pencilSCursor.setWidth(8 + currentPencilSSize);
				pencilSCursor.setHeight(8 + currentPencilSSize);
				pencilSCursor.setStyle("-fx-fill:"+currentColorHex+";");
				drawingPane.getChildren().add(pencilSCursor);
			}
			else if(currentTool == "pattern-brush") {
				Image im = new Image(currentPatternAsset,false);
				patternBrushCursor.setRadius(5 + currentPencilCSize);
				patternBrushCursor.setFill(new ImagePattern(im));

				drawingPane.getChildren().add(patternBrushCursor);	
			}
			else if(currentTool == "highlighter") {
				drawingPane.getChildren().add(highlighterCursor);
			}
			else if(currentTool == "eraser") {
				eraserCursor.setStyle("-fx-stroke:black;-fx-fill:#fff");
				eraserCursor.setWidth(currentEraserSize);
				eraserCursor.setHeight(currentEraserSize);
				drawingPane.getChildren().add(eraserCursor);
			}
			else if(currentTool == "bucket") {
				drawingPane.getChildren().add(bucketCursor);
			}
			/*else if(currentTool == "ellipse") {
				ellipseCursor.setRadius(10);
				ellipseCursor.setStyle("-fx-fill:transparent;-fx-stroke:"+newColorHex+";");
				drawingPane.getChildren().add(ellipseCursor);
			}
			else if(currentTool == "rectangle") {
				rectangleCursor.setStyle("-fx-stroke:" + newColorHex +";-fx-fill:transparent;");
				rectangleCursor.setWidth(15);
				rectangleCursor.setHeight(10);
				drawingPane.getChildren().add(rectangleCursor);
			}*/
			else if(currentTool == "line" || currentTool == "cube" || currentTool == "image" || currentTool == "ellipse" || 
					currentTool == "polygon" || currentTool == "rectangle" ) {
				newScene.setCursor(Cursor.CROSSHAIR);
			}
			else if(currentTool == "text") {
				textCursor.setStyle("-fx-font-size:"+currentFontSize+";-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:"+currentColorHex+";");	
				drawingPane.getChildren().add(textCursor);
			}
			else if(currentTool == "text-3D") {
				textCursor.setStyle("-fx-font-size:"+currentFontSize+";-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:"+currentColorHex+";");	
				textCursor3D.setStyle("-fx-font-size:"+currentFontSize+";-fx-font-family:'"
						+ currentFontFamily+"';-fx-fill:#252525;");	
				
				drawingPane.getChildren().add(textCursor3D);
				drawingPane.getChildren().add(textCursor);
			}
		}
		
		public void drawCursor(MouseEvent e) {
			double x = e.getX();
			double y = e.getY();
			drawingPane.toBack();
			controls.toFront();
				
			if(currentTool == "pencil-c") {
				pencilCCursor.setCenterX(x); pencilCCursor.setCenterY(y);
			}
			else if(currentTool == "paint-brush") {
				paintBrushCursor.setCenterX(x); paintBrushCursor.setCenterY(y);
			}
			else if(currentTool == "pencil-s") {
				pencilSCursor.setX(x-0.5*currentPencilSSize);pencilSCursor.setY(y-0.5*currentPencilSSize);
			}
			else if(currentTool == "pattern-brush") {
				patternBrushCursor.setCenterX(x); patternBrushCursor.setCenterY(y);
			}
			else if(currentTool == "highlighter") {
				highlighterCursor.toFront();
				highlighterCursor.setLayoutX(x-20); highlighterCursor.setLayoutY(y-40);
			}
			else if(currentTool == "eraser") {
				eraserCursor.toFront();
				eraserCursor.setX(x-0.5*currentEraserSize); eraserCursor.setY(y-0.5*currentEraserSize);
			}
			else if(currentTool == "bucket") {
				bucketCursor.toFront();
				bucketCursor.setLayoutX(x+5); bucketCursor.setLayoutY(y+5);
			}
			/*else if(currentTool == "ellipse") {
				ellipseCursor.toFront();
				ellipseCursor.setCenterX(x+10);ellipseCursor.setCenterY(y+10);
			}
			else if(currentTool == "rectangle") {
				rectangleCursor.toFront();
				rectangleCursor.setX(x+10); rectangleCursor.setY(y+10);
			}*/
			else if(currentTool == "text") {
				textCursor.toFront();
				textCursor.setX(x); textCursor.setY(y);
			}
			else if(currentTool == "text-3D") {	
				textCursor3D.toFront();
				textCursor3D.setX(x-currentText3DDistance); textCursor3D.setY(y-currentText3DDistance);
				textCursor.toFront();
				textCursor.setX(x); textCursor.setY(y);
			}
				
		}
		
		public void removeCursor(MouseEvent e) {
			
			newScene.setCursor(Cursor.DEFAULT);
			
			if(currentTool == "pencil-c") {
				drawingPane.getChildren().remove(pencilCCursor);
			}
			else if(currentTool == "pencil-s") {
				drawingPane.getChildren().remove(pencilSCursor);
			}
			else if(currentTool == "highlighter") {
				drawingPane.getChildren().remove(highlighterCursor);
			}
			else if(currentTool == "eraser") {
				drawingPane.getChildren().remove(eraserCursor);
			}
			else if(currentTool == "bucket") {
				drawingPane.getChildren().remove(bucketCursor);
			}
			else if(currentTool == "ellipse") {
				drawingPane.getChildren().remove(ellipseCursor);
			}
			else if(currentTool == "rectangle") {
				drawingPane.getChildren().remove(rectangleCursor);
			}
			else if(currentTool == "text") {
				drawingPane.getChildren().remove(textCursor);
			}
			else if(currentTool == "text-3D") {
				drawingPane.getChildren().remove(textCursor);
				drawingPane.getChildren().remove(textCursor3D);
			}
			else if(currentTool == "pattern-brush") {
				drawingPane.getChildren().remove(patternBrushCursor);
			}
			else if(currentTool == "paint-brush") {
				drawingPane.getChildren().remove(paintBrushCursor);
			}
		}
	
		public void onMPaintObjectClicked(MouseEvent e) {
			double x = e.getX();
			double y = e.getY();
			
			ObservableList<Integer> layersSelected = layersList.getSelectionModel().getSelectedItems();
			layersIndex = (int)layersSelected.sorted().get(layersSelected.size() - 1);
			
			if(currentTool == "bucket") {
				for(int i = 0; i < MPaintObjects.size(); i++) {
					if(((MPaintObject)(MPaintObjects.get(i))).node.contains(x, y) && layersSelected.contains(((MPaintObject)(MPaintObjects.get(i))).layer)){
						((MPaintObject)MPaintObjects.get(i)).node.setStyle(((MPaintObject)MPaintObjects.get(i)).node.getStyle().replace("-fx-fill:"+((MPaintObject)MPaintObjects.get(i)).color+";", "-fx-fill:"+currentColorHex+";"));
						((MPaintObject)MPaintObjects.get(i)).color = currentColorHex;
					}
					
				}	
			}   
			else if(currentTool == "image") {
				((MPaintObject)MPaintObjects.get(MPaintObjects.size() - 1)).node.setStyle("-fx-border-width:0");
			}
		}
	
		public void clearPage() {
			for(int i = 0; i < MPaintObjects.size(); i++) {
				if(drawingPane.getChildren().contains(((MPaintObject)MPaintObjects.get(i)).node)){
					if(drawingPane.getChildren().contains(((MPaintObject)(MPaintObjects.get(i))).node)){
						drawingPane.getChildren().remove(((MPaintObject)(MPaintObjects.get(i))).node);				
					}
				}
			}
			
			MPaintObjects.clear();
			
			layersIndex = 0;
			layersCount = 0;
			layersNames.setAll(0);
			layersList.getSelectionModel().select(0);
		}
	}
	
	public class aboutPane extends Pane{
		
		Label AP_main;
		Group credits;
	
		
		aboutPane(){
			/*window controls*/	
			HBox windowControls = new WindowControls(1);
			btnBack.setOnAction(e -> backMPaint(e, 1));
			
			this.setOnMousePressed(e -> {
				dragX = e.getScreenX() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getX();
				dragY = e.getScreenY() - ((Stage)((Pane)(e.getSource())).getScene().getWindow()).getY();
			});
			
			this.setOnMouseDragged(e -> {
				stage.setX(e.getScreenX() - dragX);
				stage.setY(e.getScreenY() - dragY);
				
			});
			
			
			
			//menu pane
			Pane aboutMenuPane = new Pane();
			aboutMenuPane.setLayoutX(0);
			aboutMenuPane.setLayoutY(50);
			aboutMenuPane.setPrefSize(1100, 550);
			aboutMenuPane.getStyleClass().add("start-menu-pane");
			
			Label AP_title = new Label("Credits");
			AP_title.getStyleClass().add("heading");
			AP_title.setLayoutX(450);
			AP_title.setLayoutY(10);
			AP_title.toFront();
			
			AP_main = new Label("MPAINT By Manan");
			AP_main.setStyle("-fx-text-fill:#fff; -fx-font-size:50; -fx-padding:5 10 5 10;-fx-background-color:#6A7FDB;");
			AP_main.setLayoutX(335);
			AP_main.setLayoutY(170);
			AP_main.toFront();
			
			credits = new Group();
			
			Label AP_iconcredits = new Label("Icon Credits");
			AP_iconcredits.setStyle("-fx-text-fill:#fff; -fx-font-size:20; -fx-padding:3 124 3 124;-fx-background-color:#57E2E5;");
			AP_iconcredits.setLayoutX(375);
			AP_iconcredits.setLayoutY(310);
			AP_iconcredits.toFront();
			
			Label AP_authors = new Label("Situ Herrera"
					+ "\nFreepik"
					+ "\nSmashicons "
					+ "\nDave Gandy"
					+ "\nDario Ferrando"
					+ "\n   -from www.flaticon.com"
					+ "\nHong Duc");
			AP_authors.setStyle("-fx-text-fill:#fff; -fx-font-size:15; -fx-padding:3 179 3 5;-fx-background-color:#252525;");
			AP_authors.setLayoutX(375);
			AP_authors.setLayoutY(350);
			AP_authors.toFront();
			
			credits.getChildren().addAll(AP_iconcredits, AP_authors);
			
			
			//start menu pane
			Pane aboutPane = new Pane();
			aboutPane.getStyleClass().add("start-pane");
			aboutPane.setPrefHeight(650);
			
			aboutMenuPane.getChildren().addAll(AP_title, AP_main, credits);
			aboutPane.getChildren().addAll(aboutMenuPane);
			this.getChildren().addAll( aboutPane, windowControls);
			
		}
		
		public void fadeIn() {
			credits.setOpacity(0);
			
			FadeTransition ft1 = new FadeTransition(Duration.millis(3000), AP_main);
			ft1.setFromValue(0);
			ft1.setToValue(1);
			ft1.play();
			
			FadeTransition ft2 = new FadeTransition(Duration.millis(3000), credits);
			ft2.setDelay(new Duration(1000));
			ft2.setFromValue(0);
			ft2.setToValue(1);
			ft2.play();
		}
		
	}
 	
	public class MPaintObject{
		
		private String type;
		
		private Shape node;
		private double size = 5;
		private int layer = 0;
		
		private double nodePropertyX;
		private double nodePropertyY;
		private String color;
		
		MPaintObject(){
			
		}
		
		MPaintObject(String type, int layer, double x, double y, double sizeChange, String color){
			if(type == "pencil-c") {
				this.type = "pencil-c";
				size = 5 + sizeChange;
				this.layer = layer;
				node = new Circle(x, y, size);
				node.setStyle("-fx-fill:"+color+";");
			}
			else if(type == "pencil-s") {
				this.type = "pencil-s";
				size = 8 + sizeChange;
				this.layer = layer;
				node = new Rectangle(x-0.5*size,y-0.5*size, size, size);
				node.setStyle("-fx-fill:"+color+";");
			}
			else if(type == "highlighter") {
				this.type = "highlighter";
				size = 12 + sizeChange;
				this.layer = layer;
				node = new Rectangle(x-0.5*size,y-0.5*size, size, size);
				node.setStyle("-fx-fill:"+color+";");
			}
			else if(type == "eraser") {
				this.type = "eraser";
				size = 15 + sizeChange;
				this.layer = layer;
				node = new Rectangle(x-0.5*size,y-0.5*size, size, size);
				node.setStyle("-fx-fill:#fff;");
			}
			else if(type == "text") {
				this.type = "text";
				this.color = color;
				size = 15 + sizeChange;
				this.layer = layer;
				node = new Text("AAAA");
				((Text)node).setX(x);
				((Text)node).setY(y);
			}
			else if(type == "pattern-brush") {
				this.type = "pattern-brush";
				size = 8 + sizeChange;
				this.layer = layer;
				node = new Circle(x, y, size);
			}
			else if(type == "polydots") {
				this.type = "polydots";
				size = sizeChange;
				this.layer = layer;
				nodePropertyX = x;
				nodePropertyY = y;
				this.color = color;
				node = new Ellipse(nodePropertyX, nodePropertyY, size, size);
				node.setStyle("-fx-stroke:"+color+";-fx-fill:" + color + ";");
			}

		}
		
		MPaintObject(String type, int layer, double x, double y, double strokeChange, double strokeLength, String strokeType,  String color){
			if(type == "ellipse") {
				this.type = "ellipse";
				size = strokeChange;
				this.layer = layer;
				nodePropertyX = x;
				nodePropertyY = y;
				this.color = "transparent";
				node = new Ellipse(nodePropertyX, nodePropertyY, 10, 10);
				node.setStyle("-fx-stroke:"+color+";-fx-fill:transparent;-fx-stroke-dash-offset: 10;"
						+ "-fx-stroke-dash-array:"+strokeLength+";-fx-stroke-line-cap:"+strokeType+";-fx-stroke-width:"+size+";");
			}
			else if(type == "pewdiepie") {
				this.type = "rectangle";
				size = strokeChange;
				this.layer = layer;
				nodePropertyX = 0;
				nodePropertyY = 0;
				this.color = color;
				node = new Rectangle(nodePropertyX, nodePropertyY, 1100, 650);
				node.setFill(new Color(Math.random(),Math.random(),Math.random(),1));
			}
			else if(type == "rectangle") {
				this.type = "rectangle";
				size = strokeChange;
				this.layer = layer;
				nodePropertyX = x;
				nodePropertyY = y;
				this.color = "transparent";
				node = new Rectangle(nodePropertyX, nodePropertyY, 0, 0);
				node.setStyle("-fx-stroke:"+color+";-fx-fill:transparent;-fx-stroke-dash-offset: 10;"
						+ "-fx-stroke-dash-array:"+strokeLength+";-fx-stroke-line-cap:"+strokeType+";-fx-stroke-width:"+size+";");
			}
			else if(type == "line") {
				this.type = "line";
				size = strokeChange;
				this.layer = layer;
				nodePropertyX = x;
				nodePropertyY = y;
				this.color = "transparent";
				node = new Line(x, y,x, y);
				node.setStyle("-fx-stroke:"+color+";-fx-fill:transparent;-fx-stroke-dash-offset: 10;"
						+ "-fx-stroke-dash-array:"+strokeLength+";-fx-stroke-line-cap:"+strokeType+";-fx-stroke-width:"+size+";");
			}
		}

		//FOR POLYGON
		MPaintObject(String type, int layer, ArrayList<Double[]> polys, double strokeChange, double strokeLength, String strokeType, String color){
			if(type == "polygon") {
				this.type = "polygon";
				size = strokeChange;
				this.layer = layer;
				node = new Polygon();
				this.color = "transparent";

				for(int i = 0; i < polys.size(); i++) {
					((Polygon)node).getPoints().addAll(polys.get(i));
				}
				
				node.setStyle("-fx-stroke:"+color+";-fx-fill:transparent;-fx-stroke-dash-offset: 10;"
						+ "-fx-stroke-dash-array:"+strokeLength+";-fx-stroke-line-cap:"+strokeType+";-fx-stroke-width:"+size+";");
			}
		}
	}
		
	public class WindowControls extends HBox{
		
		WindowControls(int scene_id){
			btnClose = new Button("X");
			btnClose.setId("WC_btnClose");
			btnClose.setOnAction(e -> closeMPaint());
			
			btnMinimize = new Button("-");
			btnMinimize.setId("WC_btnMinimize");
			btnMinimize.setOnAction(e -> minimizeMPaint(e));
			
			btnBack = new Button("");			
			btnBack.setId("WC_btnBack");
			btnBack.setOnAction(e -> backMPaint(e, scene_id));

			this.getStyleClass().add("window-controls");
			this.setAlignment(Pos.BOTTOM_RIGHT);
			
			if(scene_id == 0) {
				this.getChildren().addAll(btnMinimize, btnClose);
			}
			else {
				this.getChildren().addAll(btnMinimize, btnClose, btnBack);
			}
			


		}
	}
	
	public void backMPaint(ActionEvent e, int id) {
		if(id == 1) {
			((Stage)((Button)e.getSource()).getScene().getWindow()).setScene(scene);
		}
	}
	
	public void closeMPaint() {
		Platform.exit();
	}
	
	public void minimizeMPaint(ActionEvent e) {
		((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
	}
}
