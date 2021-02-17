/* 
* Exercise: java 2 18.39
* Lauren Smith 
* 2/16/21 
* Description makes a recursive tree that follows the mouse when it's dragged and clicked 
*/

package e1839;

import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class E1839 extends Application {

    @Override
    public void start(Stage primaryStage) {
       //makes an HBox with some spacing to organize the pane 
        HBox hbox=new HBox(10); 
        //makes an empty text feild for number of branches to be entered
        TextField tfOrder=new TextField(); 
        //sets column number for text field for apperance 
        tfOrder.setPrefColumnCount(4);
        //alins data in text field to bottom right 
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT); 
        //adds the text field alongside a label asking for input to the hbox
        hbox.getChildren().addAll(new Label("Enter an order: "),tfOrder);
        //alins hbox items to the center of the hbox
        hbox.setAlignment(Pos.CENTER); 
        //makes the main pane a treePane 
        TreePane pane=new TreePane(); 
        //updates tree when new number is entered in branches text field 
        tfOrder.setOnAction(e->{
           pane.setDepth(Integer.parseInt(tfOrder.getText())); 
        });
        //moves tree when mouse is clicked and dragged 
          pane.setOnMouseDragged(e -> {
                    //updates movement x with mouse x 
                    pane.setTranslateX(pane.getTranslateX() + e.getX());
                    pane.setTranslateY(pane.getTranslateY() + e.getY());
            });
        //makes BorderPane to hold and orginize hbox and TreePane       
        BorderPane borderPane=new BorderPane(); 
        //sets bottom of borderpane to hbox 
        borderPane.setBottom(hbox); 
        //center of borderpane is TreePane 
        borderPane.setCenter(pane); 
        
        Scene scene = new Scene(borderPane, 200, 210);

        primaryStage.setTitle("Recursive Tree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    } 
    
    //inner class TreePane to create and display recursive tree
    static class TreePane extends Pane 
    {
        //declares some variables to be used in methods 
        private int depth=0; 
        private double angleFactor=Math.PI/5;  
        private double sizeFactor=0.58; 
        
        //method to set the depth of the tree
        public void setDepth(int d) 
        {
            //depth is set to 0 then runs paint method
            this.depth=d; 
            paint(); 
        } 
        //method used when depth is set (every time number of tree branches changes)
        public void paint() 
        {
            //clears the previous tree drawn
            getChildren().clear(); 
            //runs recursive paintBranch method to paint whole tree
            paintBranch(depth,getWidth()/2,getHeight()-10,getHeight()/3,Math.PI/2); 
        }
        
        //recursive method to paint a branch of the tree and paints branches until
        //whole tree is done 
        public void paintBranch(int depth, double x1, double y1, double length, double angle) 
        {
            //if depth isn't 0 
            if(depth>=0) 
            {
                //set x2 to what x1 is and use some trig to find out angles 
                double x2=x1+Math.cos(angle)*length; 
                //same with y2 excepts subtracts from y1 
                double y2=y1-Math.sin(angle)*length;  
                //makes and adds a line starting at x1,y1 and goes to x2,y2
                getChildren().add(new Line(x1,y1,x2,y2)); 
                //paints left branch 
                paintBranch(depth-1,x2,y2,length*sizeFactor,angle+angleFactor);
                //paints right branch
                paintBranch(depth-1,x2,y2,length*sizeFactor,angle-angleFactor);
            }
        } 
        
        
    }

} 


