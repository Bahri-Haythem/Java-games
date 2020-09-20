package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class FXMLDocumentController implements Initializable {
	
    private GraphicsContext gcB,gcF;
    private boolean drawline = false,drawoval = false,drawrectangle = false,erase = false,freedesign = true;
    double startX, startY, lastX,lastY,oldX,oldY;
    double hg;

    @FXML private RadioButton strokeRB,fillRB;
    @FXML private ColorPicker colorPick;
    @FXML private Canvas TheCanvas,canvasGo;
    @FXML private Button rectButton,lineButton,ovlButton,pencButton;
    @FXML private Slider sizeSlider;
    @FXML private AnchorPane anchor;
    @FXML private ImageView ImageRect,ImageLine,ImageCircle,ImagePencil;

    @FXML
    private void onMousePressedListener(MouseEvent e){
        this.startX = e.getX();
        this.startY = e.getY();
        this.oldX = e.getX();
        this.oldY = e.getY();
        
    }

    @FXML
    private void onMouseDraggedListener(MouseEvent e){
        this.lastX = e.getX();
        this.lastY = e.getY();

        if(drawrectangle)
            drawRectEffect();
        if(drawoval)
            drawOvalEffect();
        if(drawline)
            drawLineEffect();
        if(freedesign)
            freeDrawing();
        if(erase)
        	eraseZone();
    }

    @FXML
    private void onMouseReleaseListener(MouseEvent e){
    	if(drawrectangle)
            drawRect();
        if(drawoval)
            drawOval();
        if(drawline)
            drawLine();
        
    }
    @FXML
    private void setHelp() {
    	if(anchor.isVisible()) {
    		anchor.setVisible(false);
    	}else {
    		anchor.setVisible(true);
    	}
    }
    private void eraseZone() {
    	oldX = lastX;
    	oldY = lastY;
    	gcB.setLineWidth(sizeSlider.getValue());
    	gcB.setFill(Color.WHITE);
    	gcB.fillRect(oldX, oldY, sizeSlider.getValue(), sizeSlider.getValue());
    	gcF.setFill(Color.WHITE);
    	gcF.fillRect(oldX, oldY, sizeSlider.getValue(), sizeSlider.getValue());
        //gcB.clearRect(oldX, oldY, sizeSlider.getValue(), sizeSlider.getValue());
        //gcF.clearRect(oldX, oldY, sizeSlider.getValue(), sizeSlider.getValue());
        
    }
    private void drawOval()
    {
        double wh = lastX - startX;
        double hg = lastY - startY;
        gcB.setLineWidth(sizeSlider.getValue());

        if(fillRB.isSelected()){
            gcB.setFill(colorPick.getValue());
            gcB.fillOval(startX, startY, wh, hg);
        }else{
            gcB.setStroke(colorPick.getValue());
            gcB.strokeOval(startX, startY, wh, hg);
        }
    }

    private void drawRect()
    {
        double wh = lastX - startX;
        double hg = lastY - startY;
        gcB.setLineWidth(sizeSlider.getValue());

        if(fillRB.isSelected()){
            gcB.setFill(colorPick.getValue());
            gcB.fillRect(startX, startY, wh, hg);
        }else{
            gcB.setStroke(colorPick.getValue());
            gcB.strokeRect(startX, startY, wh, hg);
        }
    }

    private void drawLine()
    {
        gcB.setLineWidth(sizeSlider.getValue());
        gcB.setStroke(colorPick.getValue());
        gcB.strokeLine(startX, startY, lastX, lastY);
    }

    private void freeDrawing()
    {
        gcB.setLineWidth(sizeSlider.getValue());
        gcB.setStroke(colorPick.getValue());
        gcB.strokeLine(oldX, oldY, lastX, lastY);
        oldX = lastX;
        oldY = lastY;
    }

    private void drawOvalEffect()
    {
        double wh = lastX - startX;
        double hg = lastY - startY;
        gcF.setLineWidth(sizeSlider.getValue());

        if(fillRB.isSelected()){
            gcF.clearRect(0, 0, canvasGo.getWidth(), canvasGo.getHeight());
            gcF.setFill(colorPick.getValue());
            gcF.fillOval(startX, startY, wh, hg);
        }else{
            gcF.clearRect(0, 0, canvasGo.getWidth(), canvasGo.getHeight());
            gcF.setStroke(colorPick.getValue());
            gcF.strokeOval(startX, startY, wh, hg );
        }
       }

    private void drawRectEffect()
    {
        double wh = lastX - startX;
        double hg = lastY - startY;
        gcF.setLineWidth(sizeSlider.getValue());

        if(fillRB.isSelected()){
            gcF.clearRect(0, 0, canvasGo.getWidth(), canvasGo.getHeight());
            gcF.setFill(colorPick.getValue());
            gcF.fillRect(startX, startY, wh, hg);
        }else{
            gcF.clearRect(0, 0, canvasGo.getWidth(), canvasGo.getHeight());
            gcF.setStroke(colorPick.getValue());
            gcF.strokeRect(startX, startY, wh, hg );
        }
    }

    private void drawLineEffect()
    {
        gcF.setLineWidth(sizeSlider.getValue());
        gcF.setStroke(colorPick.getValue());
        gcF.clearRect(0, 0, canvasGo.getWidth() , canvasGo.getHeight());
        gcF.strokeLine(startX, startY, lastX, lastY);
    }

    @FXML 
    private void clearCanvas(ActionEvent e)
    {
        gcB.clearRect(0, 0, TheCanvas.getWidth(), TheCanvas.getHeight());
        gcF.clearRect(0, 0, TheCanvas.getWidth(), TheCanvas.getHeight());
        gcB.setFill(Color.WHITE);
        gcF.setFill(Color.WHITE);
        gcB.fillRect(0, 0, TheCanvas.getWidth(), TheCanvas.getHeight());
        gcF.fillRect(0, 0, TheCanvas.getWidth(), TheCanvas.getHeight());
    }


    @FXML
    private void setOvalAsCurrentShape(ActionEvent e)
    {
        drawline = false;
        drawoval = true;
        drawrectangle = false;
        freedesign = false;
        erase = false;
    }

     @FXML
    private void setLineAsCurrentShape(ActionEvent e)
    {
        drawline = true;
        drawoval = false;
        drawrectangle = false;
        freedesign = false;
        erase = false;
    }
     @FXML
    private void setRectangleAsCurrentShape(ActionEvent e)
    {
        drawline = false;
        drawoval = false;
        freedesign = false;
        erase=false;
        drawrectangle = true;
    }

    @FXML
    private void setErase(ActionEvent e)
    {
        drawline = false;
        drawoval = false;
        drawrectangle = false;    
        erase = true;
        freedesign= false;
    }

    @FXML
    private void setFreeDesign(ActionEvent e)
    {
        drawline = false;
        drawoval = false;
        drawrectangle = false;    
        erase = false;
        freedesign = true;
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	skillMeter.Controller.activated =false;
		application.Controller.activated=false;
		demineur.Controller.activated=false;
        gcB = TheCanvas.getGraphicsContext2D();
        gcF = canvasGo.getGraphicsContext2D();

        sizeSlider.setMin(1);
        sizeSlider.setMax(50);
        colorPick.setValue(Color.BLACK);
        
        gcB.setFill(Color.WHITE);
        gcF.setFill(Color.WHITE);
        gcB.fillRect(0, 0, TheCanvas.getWidth(), TheCanvas.getHeight());
        gcF.fillRect(0, 0, TheCanvas.getWidth(), TheCanvas.getHeight());
        Image imageRect = new Image(getClass().getResourceAsStream("rectangle-32.png"));
        ImageView rectangle = new ImageView(imageRect);
        rectangle.setFitWidth(32);
        rectangle.setFitHeight(32);
        rectButton.setGraphic(rectangle);  

        Image imageLine = new Image(getClass().getResourceAsStream("ruler-32.png"));
        ImageView line = new ImageView(imageLine);
        line.setFitWidth(32);
        line.setFitHeight(32);
        lineButton.setGraphic(line);

        Image imageCircle = new Image(getClass().getResourceAsStream("circle-32.png"));
        ImageView circle = new ImageView(imageCircle);
        circle.setFitWidth(32);
        circle.setFitHeight(32);
        ovlButton.setGraphic(circle);

        Image imagePencil = new Image(getClass().getResourceAsStream("pencil-32.png"));
        ImageView pencil = new ImageView(imagePencil);
        pencil.setFitWidth(32);
        pencil.setFitHeight(32);
        pencButton.setGraphic(pencil);
        
        ImageRect.setImage(imageRect);
        ImageCircle.setImage(imageCircle);
        ImageLine.setImage(imageLine);
        ImagePencil.setImage(imagePencil);
    }    

}