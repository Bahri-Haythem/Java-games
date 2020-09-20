package demineur;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller {
	public static boolean activated = false;
	public static MediaPlayer mediaPlayer;
	private int errorNumber;
	private int sucessNumber;
	int t[][]= new int[6][6];
	@FXML
	GridPane gridPane;
	@FXML
	Button startButton,viewButton;
	@FXML
	Label label,errorLabel,sucessLabel,label1;
	@FXML
	public void initialize() {
		activated=true;
		skillMeter.Controller.activated =false;
		application.Controller.activated=false;
		Image image = new Image(getClass().getResourceAsStream("view.png"));
		viewButton.setGraphic(new ImageView(image));
		//viewButton.setBackground(null);
		String musicFile = "ChillMusic.mp3";
		Media sound = new Media(getClass().getResource(musicFile).toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		if(application.Controller.TESTsoundON) {
			mediaPlayer.play();			
		}else if (application.Controller.TESTsoundOFF) {
			mediaPlayer.stop();
		}
	}
	static public void affiche(int[][] t) {
		for(int i=0;i<6;++i) {
			for(int j =0 ; j<6;++j) {
				System.out.print(t[i][j]+" ");
			}
			System.out.println();
		}
	}
	static public int count(int[][] t,int row,int col) {
		int score = 0;
		if(row-1>=0 && col-1>=0) {
			score+=t[row-1][col-1];
		}
		if(row>0) {
			score+=t[row-1][col];
		}
		if(row-1>=0 && col+1<6) {
			score+=t[row-1][col+1];
		}
		if(col-1>=0) {
			score+=t[row][col-1];
		}
		if(col+1<6) {
			score+=t[row][col+1];
		}
		if(row+1<6 && col-1>=0) {
			score+=t[row+1][col-1];
		}
		if(row+1<6) {
			score+=t[row+1][col];
		}
		if(row+1<6 && col+1<6) {
			score+=t[row+1][col+1];
		}
		return score;
	}
	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();
	    for (Node node : childrens) {
	        if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
	@FXML
	public void discover() {
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt ->{
			for(int i=0;i<6;++i) {
				for(int j =0 ; j<6;++j) {
					if(t[i][j]==1 && !getNodeByRowColumnIndex(i, j, gridPane).isDisable()) {
						final Node node = getNodeByRowColumnIndex(i, j, gridPane);
						//node.setVisible(false);
						node.setStyle("-fx-background-color: #fc3c3c");
					}
				}
			}
		} ),
        new KeyFrame(Duration.seconds(0.5), evt -> {
			for(int i=0;i<6;++i) {
				for(int j =0 ; j<6;++j) {
					if(t[i][j]==1 && !getNodeByRowColumnIndex(i, j, gridPane).isDisable()) {
						final Node node = getNodeByRowColumnIndex(i, j, gridPane);
						//node.setVisible(true);
						node.setStyle("-fx-background-color: #4d80e4");
					}
				}
			}
		}));
		timeline.setCycleCount(3);
		timeline.play();
	}
	@FXML
	public void initiate() {
		for(int i=0;i<6;++i) {
			for(int j =0 ; j<6;++j) {
				t[i][j]=0;
			}
		}
		viewButton.setVisible(true);
		gridPane.getChildren().clear();
		errorNumber=0;
		sucessNumber=0;
		gridPane.setVisible(true);
		label.setVisible(false);
		label1.setVisible(false);
		errorLabel.setVisible(false);
		sucessLabel.setVisible(false);
		startButton.setText("Restart");
		int counter=0;
		
		//the bombs number
		while(counter<6) {
			Random random = new Random();
			int rand_col = random.nextInt(6);
			int rand_row = random.nextInt(6);
			for(int i=0;i<6;i++) {
				for(int j =0 ; j<6;j++) {
					if(t[rand_col][rand_row]==0) {
						t[rand_col][rand_row] = 1;
						counter++;
					}
				}
			}			
		}
		
		//this.affiche(t);
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				Button button = new Button();
				button.setText("");
				button.getStyleClass().add("elements");
				button.setPrefWidth(60);
				button.setPrefHeight(60);
				button.setOnAction(e ->{
					int row = gridPane.getRowIndex(button);
					int col = gridPane.getColumnIndex(button);
					if(t[row][col]==0) {
						button.setText(count(t,row,col)+"");
						button.setStyle("-fx-background-color: #fbe555");
						sucessNumber++;
						if(sucessNumber>=30) {
							sucessLabel.setText("you win.\n You just finished all the bombs with "+
									errorNumber + " errors. Press restart to try again.");
							sucessLabel.setVisible(true);
							gridPane.setVisible(false);
						}
					}else {
						button.setStyle("-fx-background-color: #fc3c3c");
						Image image = new Image(getClass().getResourceAsStream("Bomb.png"));
						image.isPreserveRatio();
						button.setGraphic(new ImageView(image));
						errorNumber++;
						if(errorNumber>=application.Controller.errorNumber) {
							errorLabel.setText("you failed.\n"+application.Controller.errorNumber+" bombs exploded.\n Press restart to try again.");
							errorLabel.setVisible(true);
							gridPane.setVisible(false);
						}
					}
					button.setDisable(true);
				});
				HBox.setMargin(button, new Insets(12,0,0,12));
				gridPane.add(button, i, j);
			}
		}
	}
}
