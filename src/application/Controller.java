package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;

public class Controller {
	public static boolean activated = true;
	public static int errorNumber;
	public static int repetitionNumber;
	public static boolean TESTsoundON=true,TESTsoundOFF=false;
	public static MediaPlayer mediaPlayer;
	Parent root ;
	@FXML
	MenuBar myMenuBar;
	@FXML
	Button button1,button2,button3,aboutButton,returnButton;
	@FXML
	AnchorPane anchor,mainAnchor;
	@FXML
	ImageView iv1,iv2,iv3;
	@FXML
	MenuItem mi0,mi1,mi2,mi3;
	@FXML
	public CheckMenuItem soundON,soundOFF;
	@FXML
	AnchorPane aboutPane;
	@FXML
	RadioMenuItem rmi0,rmi1,rmi2,rmi3,rmi4,rmi5,rmi6;
	@FXML
	RadioMenuItem Rrmi1,Rrmi2,Rrmi3,Rrmi4,Rrmi5,Rrmi6,Rrmi7,Rrmi8,Rrmi9;
	@FXML
	public void setONSelected() {
		if(soundOFF.isSelected()) {
			soundOFF.setSelected(false);
			soundON.setSelected(true);
			TESTsoundOFF=false;
			TESTsoundON=true;
			if(skillMeter.Controller.activated) {
				demineur.Controller.activated=false;
				skillMeter.Controller.mediaPlayer.play();
				//mediaPlayer.stop();
			}
			if(demineur.Controller.activated) {
				skillMeter.Controller.activated =false;
				demineur.Controller.mediaPlayer.play();
				//mediaPlayer.stop();
			}
			if(activated) {
				demineur.Controller.activated=false;
				skillMeter.Controller.activated =false;
				mediaPlayer.play();
			}
		}
	}
	@FXML
	public void setOFFSelected() {
		if(soundON.isSelected()) {
			soundON.setSelected(false);
			soundOFF.setSelected(true);
			TESTsoundON=false;
			TESTsoundOFF=true;
			if(skillMeter.Controller.activated) {
				activated=false;
				demineur.Controller.activated =false;
				skillMeter.Controller.mediaPlayer.stop();
				//mediaPlayer.stop();
			}
			if(demineur.Controller.activated) {
				activated=false;
				skillMeter.Controller.activated =false;
				demineur.Controller.mediaPlayer.stop();
				//mediaPlayer.stop();
			}
			if(activated) {
				demineur.Controller.activated=false;
				skillMeter.Controller.activated =false;
				mediaPlayer.stop();
			}
		}
	}
	@FXML
	public void showAbout() {
		anchor.setVisible(false);
		aboutPane.setVisible(true);
	}
	@FXML
	public void showMain() {
		anchor.setVisible(true);
		aboutPane.setVisible(false);
	}
	@FXML
	public void initialize() {
		skillMeter.Controller.activated =false;
		demineur.Controller.activated =false;
		soundON.setSelected(TESTsoundON);
		soundOFF.setSelected(TESTsoundOFF);

		String musicFile = "happy.mp3";
		Media sound = new Media(getClass().getResource(musicFile).toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		if(TESTsoundON) {
			mediaPlayer.play();
		}else  {
			mediaPlayer.stop();
		}
		
		Image imageDemineur = new Image(getClass().getResourceAsStream("demineur.png"));
		Image imagePaint = new Image(getClass().getResourceAsStream("paint.png"));
		Image imageSkillMeter = new Image(getClass().getResourceAsStream("skillMeter.png"));
		
		iv1.setImage(imageDemineur);
		iv1.setPreserveRatio(true);
		iv2.setImage(imagePaint);
		iv2.setPreserveRatio(true);
		iv3.setImage(imageSkillMeter);
		iv3.setPreserveRatio(true);
		
		Image imageabout = new Image(getClass().getResourceAsStream("about.png"));
		aboutButton.setGraphic(new ImageView(imageabout));
		aboutButton.setBackground(null);
		
		Image imagereturn = new Image(getClass().getResourceAsStream("return.png"));
		returnButton.setGraphic(new ImageView(imagereturn));
		returnButton.setBackground(null);
		
		rmi6.setSelected(true);
		ToggleGroup toggleGroup = new ToggleGroup();
		rmi1.setToggleGroup(toggleGroup);
		rmi2.setToggleGroup(toggleGroup);
		rmi3.setToggleGroup(toggleGroup);
		rmi4.setToggleGroup(toggleGroup);
		rmi5.setToggleGroup(toggleGroup);
		rmi6.setToggleGroup(toggleGroup);
		setErrorNumber();
		
		Rrmi3.setSelected(true);
		ToggleGroup toggleGroup2 = new ToggleGroup();
		Rrmi1.setToggleGroup(toggleGroup2);
		Rrmi2.setToggleGroup(toggleGroup2);
		Rrmi3.setToggleGroup(toggleGroup2);
		Rrmi4.setToggleGroup(toggleGroup2);
		Rrmi5.setToggleGroup(toggleGroup2);
		Rrmi6.setToggleGroup(toggleGroup2);
		Rrmi7.setToggleGroup(toggleGroup2);
		Rrmi8.setToggleGroup(toggleGroup2);
		Rrmi9.setToggleGroup(toggleGroup2);
		setRepetitionNumber();
	}
	@FXML
	public void setRepetitionNumber() {
		if(Rrmi1.isSelected())
			repetitionNumber=1;
		if(Rrmi2.isSelected())
			repetitionNumber=2;
		if(Rrmi3.isSelected())
			repetitionNumber=3;
		if(Rrmi4.isSelected())
			repetitionNumber=4;
		if(Rrmi5.isSelected())
			repetitionNumber=5;
		if(Rrmi6.isSelected())
			repetitionNumber=6;
		if(Rrmi7.isSelected())
			repetitionNumber=7;
		if(Rrmi8.isSelected())
			repetitionNumber=8;
		if(Rrmi9.isSelected())
			repetitionNumber=9;
	}
	@FXML
	public void setErrorNumber() {
		if(rmi1.isSelected())
			errorNumber=1;
		if(rmi2.isSelected())
			errorNumber=2;
		if(rmi3.isSelected())
			errorNumber=3;
		if(rmi4.isSelected())
			errorNumber=4;
		if(rmi5.isSelected())
			errorNumber=5;
		if(rmi6.isSelected())
			errorNumber=6;
	}
	@FXML
	public void mainMenu(ActionEvent event) throws IOException {
		showMain();
		if(skillMeter.Controller.activated)
			skillMeter.Controller.mediaPlayer.stop();
		if(demineur.Controller.activated)
			demineur.Controller.mediaPlayer.stop();
		if(application.Controller.activated)
			application.Controller.mediaPlayer.stop();
		Stage appStage =null ;
		if(event.getSource().equals(mi0)) {
			appStage = (Stage)((Node)myMenuBar).getScene().getWindow();
		}
		appStage.setTitle("Main Menu");
		appStage.setResizable(false);
		appStage.setWidth(741);
		appStage.setHeight(688);
		root = FXMLLoader.load(getClass().getResource("View.fxml"));
		activated=true;
		mainAnchor.getChildren().setAll(root);
	}
	@FXML
	public void loadScene1(ActionEvent event) throws IOException {
		showMain();
		if(skillMeter.Controller.activated)
			skillMeter.Controller.mediaPlayer.stop();
		if(application.Controller.activated)
			application.Controller.mediaPlayer.stop();
		Stage appStage =null ;
		if(event.getSource().equals(button1)) {
			appStage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		}else if(event.getSource().equals(mi1)) {
			appStage = (Stage)((Node)myMenuBar).getScene().getWindow();
		}
		appStage.setTitle("demineur");
		appStage.setResizable(false);
		appStage.setHeight(763);
		appStage.setWidth(715);
		root = FXMLLoader.load(getClass().getResource("../demineur/View.fxml"));
		anchor.getChildren().setAll(root);
		/*try {
			root = FXMLLoader.load(getClass().getResource("../demineur/View.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../demineur/application.css").toExternalForm());
		Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		appStage.setScene(scene);
		appStage.show();
		*/
	}
	@FXML
	public void loadScene2(ActionEvent event) throws IOException {
		showMain();
		if(skillMeter.Controller.activated)
			skillMeter.Controller.mediaPlayer.stop();
		if(demineur.Controller.activated)
			demineur.Controller.mediaPlayer.stop();
		if(application.Controller.activated)
			application.Controller.mediaPlayer.stop();
		Stage appStage =null ;
		if(event.getSource().equals(button2)) {
			appStage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		}else if(event.getSource().equals(mi2)) {
			appStage = (Stage)((Node)myMenuBar).getScene().getWindow();
		}
		appStage.setTitle("paint");
		appStage.setResizable(false);
		appStage.setHeight(678);
		appStage.setWidth(915);
		root = FXMLLoader.load(getClass().getResource("../paint/FXMLDocument.fxml"));
		anchor.getChildren().setAll(root);
	}
	@FXML
	public void loadScene3(ActionEvent event) throws IOException {
		showMain();
		if(application.Controller.activated)
			application.Controller.mediaPlayer.stop();
		if(demineur.Controller.activated)
			demineur.Controller.mediaPlayer.stop();
		Stage appStage =null ;
		if(event.getSource().equals(button3)) {
			appStage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		}else if(event.getSource().equals(mi3)) {
			appStage = (Stage)((Node)myMenuBar).getScene().getWindow();
		}
		appStage.setTitle("skillMeter");
		appStage.setResizable(false);
		appStage.setHeight(763);
		appStage.setWidth(715);
		root = FXMLLoader.load(getClass().getResource("../skillMeter/MainView.fxml"));
		anchor.getChildren().setAll(root);
	}
	
}
