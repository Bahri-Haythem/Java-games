package skillMeter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller {
	public static boolean activated=false;
	public static MediaPlayer mediaPlayer;
	@FXML
	Button button,startButton;
	@FXML
	AnchorPane anchorPane,anchorPane2;
	@FXML
	Label label1,label2;
	@FXML
	Label fLabel1,fLabel2;
	
	boolean second=false;
	Timer myTimer = new Timer();
	int counter = 0;
	long start=0;
	double scores[] = new double[10];
	
	/*
	public void animateImage(ImageView imageView) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2),imageView);
		scaleTransition.setCycleCount(Animation.INDEFINITE);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setFromX(1);
		scaleTransition.setFromY(1);
		scaleTransition.setToX(2);
		scaleTransition.setToY(2);
		scaleTransition.play();
	}
	*/
	@FXML
	public void initialize() {
		activated=true;
		demineur.Controller.activated =false;
		application.Controller.activated=false;
		//anchorPane.setTopAnchor(anchorPane, (double)0);
		//anchorPane.setBottomAnchor(anchorPane, (double)0);
		//anchorPane.setRightAnchor(anchorPane, (double)0);
		//anchorPane.setLeftAnchor(anchorPane, (double)0);
		String musicFile = "PercussionSpaceMusic.mp3";
		Media sound = new Media(getClass().getResource(musicFile).toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		if(application.Controller.TESTsoundON) {
			mediaPlayer.play();	
		}else if (application.Controller.TESTsoundOFF) {
			mediaPlayer.stop();
		}
	}
	@FXML
	public void remove() throws InterruptedException {
		if(second) {
			label1.setVisible(false);
			label2.setVisible(false);
			myTimer = new Timer();
			scores = new double[10];
			counter=0;
		}
		fLabel1.setVisible(false);
		fLabel2.setVisible(false);
		startButton.setVisible(false);
		button.setVisible(true);
		Image image = new Image(getClass().getResourceAsStream("Bomb.gif"));
		//animateImage(new ImageView(image));
		
		button.setGraphic(new ImageView(image));
		button.setBackground(null);
		Image image2 = new Image(getClass().getResourceAsStream("Desert.png"));
		BackgroundImage background = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
		anchorPane.setBackground(new Background(background));
		activate();
	}
	
	@FXML
	public void activate() throws InterruptedException {
		anchorPane2.setVisible(false);
		double finalScore=0;
		long elapsedTime = System.nanoTime() - start;
		button.setVisible(false);
		scores[counter] = Math.round(elapsedTime/1000000)/1000.0;
		for (int i = 1; i < scores.length; i++) {
			finalScore += scores[i];
		}
		finalScore = finalScore / counter;
		Random random = new Random();
		//Time of bomb respawn
		double time = random.nextDouble()*5;
		myTimer.schedule(new TimerTask(){
	        @Override
	        public void run() {
	        	int width = (int)anchorPane.getWidth();
	    		int height = (int)anchorPane.getHeight();
	    		
	    		Random random = new Random();
	    		int randWidth = random.nextInt(width-59);
	    		int randHeight = random.nextInt(height-59);
	    		
	    		button.setLayoutX(randWidth);
	    		button.setLayoutY(randHeight);
	    		button.setVisible(true);
	    		start = System.nanoTime();
	        }
	    },(long) (1000*time)); 
		counter++;
		if(counter==application.Controller.repetitionNumber+1) {
			myTimer.cancel();
			label1.setVisible(true);
			label2.setVisible(true);
			label2.setText("your final score is : "+String.format("%.2f", finalScore));
			startButton.setText("Restart");
			startButton.setVisible(true);
			anchorPane2.setVisible(true);
			second=true;
		}
	}
	public void playSound() {
		String musicFile = "Explosion.wav";
		/*new File(musicFile).toURI().toString()*/
		Media sound = new Media(getClass().getResource(musicFile).toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
}
