import java.awt.event.MouseEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class View creates the GUI for postfixed Rational Calculator
 * It extends the Application class.
 **/
public class View extends Application {
    
    //Variables and Class instantiation
    TextField display = new TextField();
    TextField display2 = new TextField();
    MyStackRational model = new MyStackRational();
    Boolean flag = true;
    
    /**
     * @param primaryStage initial view
     **/
    @Override
    public void start(Stage primaryStage) {
        
        //Class instantiation
        BorderPane  mainPane      = new BorderPane();
        HBox        hBox          = new HBox();
        VBox        vBox          = new VBox();
        GridPane    inputWordPane = new GridPane();
        Button btn;
        
        primaryStage.setResizable(false);
        
        //Sets style of calculator displays
        display2.setPrefSize(242, 34);
        display.setPrefSize(242, 60);       
        display2.setStyle("-fx-font: 20 arial; -fx-base:#00FFFF; -fx-text-box-border: transparent; "
                + "-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;\n" +
                "-fx-background-insets: 0; -fx-font-weight: bold;    -fx-padding: 1 3 1 3;");
        display.setStyle("-fx-font: 34 arial; -fx-base:#00FFFF;-fx-text-box-border: transparent; "
                + "-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;\n" +
                "-fx-background-insets: 0; -fx-font-weight: bold;    -fx-padding: 1 3 1 3;");
        display.setDisable(true);
        display2.setDisable(true);
        
        //mainPane style
        mainPane.setStyle("-fx-border-color: grey; -fx-background-color: #D3D3D3;");
        
        //Array use for inputWordPane
        String []numAndOper = {"1","2","3","+","4","5","6","-","7","8","9","*"};

        //Event handler to get source text
        EventHandler<ActionEvent> unaryHandler = e->{
            flag = true;
            display.appendText( ((Button)e.getSource()).getText() );
            ((Button)e.getSource()).getText();
        };
                     
        //Creates Clx button
        btn = new Button("Clx");
        btn.setMinSize(60, 60);
        inputWordPane.add(btn, 0, 0);
        
        //Clears display and display2
        btn.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1){
                display.clear();
            }else if (event.getClickCount() == 2 ) {
                model.clear();
                display.clear();
                display2.clear();
            }
        });
        
        btn.setStyle("-fx-font: 16 arial; -fx-base:#00FFFF;");
        
        //Creates (-) button
        btn = new Button("(-)");
        btn.setMinSize(60, 60);
        inputWordPane.add(btn, 1, 0);
        btn.setOnAction( e->{
            display.appendText("-");
        });        
        btn.setStyle("-fx-font: 16 arial; -fx-base:#00FFFF;");
        
        //Creates a/b button
        btn = new Button("a/b");
        btn.setMinSize(60, 60);
        inputWordPane.add(btn, 2, 0);
        btn.setOnAction( e->{
            display.appendText("/");
        });
        btn.setStyle("-fx-font: 16 arial; -fx-base:#00FFFF;");
        
        //Creates p button
        btn = new Button("Sum");
        btn.setMinSize(60, 60);
        inputWordPane.add(btn, 3, 0);
        btn.setOnAction(e->{ 
            flag = true;
            if(model.size()>1){
                
                try{
                    Object obj = model.newElement(display.getText());
                    model.push((Rational)obj);
                    display.setText(model.sum());
                    Object obj2 = model.newElement(display.getText());
                    model.clear();
                    model.push((Rational)obj2);
                    display2.setText(model.toString());
                }catch(Exception a){
                    
                    display.setText(model.sum());
                    Object obj = model.newElement(display.getText());
                    model.clear();
                    model.push((Rational)obj);
                    display2.setText(model.toString());
                }
            }else{
                display.clear();
            }
        });  
        btn.setStyle("-fx-font: 16 arial; -fx-base:#00FFFF;");

        //Creates Number buttons
        for(int i=0; i < numAndOper.length; i++ ){
            btn = new Button(numAndOper[i]);
            btn.setMinSize(60, 60);
            inputWordPane.add(btn, i%4, (i/4)+1);
            btn.setStyle("-fx-font: 16 arial;");
            
            if((i%4)==3){
                btn.setStyle("-fx-font: 20 arial; -fx-base:#00FFFF;");
                btn.setOnAction(e->{
  
                    try{
                        if(flag){
                        Object obj = model.newElement(display.getText());
                        model.push((Rational)obj);
                        display2.setText(model.toString());
                        }
                    }catch(Exception exe){                        
                    }
 
                    try{
                        if(model.getSize() > 1){
                            flag = false;                     
                            switch(((Button)e.getSource()).getText()){                            
                                case"+": model.push(model.plus(model.pop(), model.pop()));
                                         break;
                                case"-": model.push(model.minus(model.pop(), model.pop()));
                                         break;
                                case"*": model.push(model.multiply(model.pop(), model.pop()));
                                         break;
                            }
                            display.setText(model.peek().toString());
                            display2.setText(model.toString());
                        }
                    }catch(Exception b){
                        display.clear();
                        
                    }
                });
            }else{
                btn.setOnAction(unaryHandler);
            }          
        }
        
        //Creates 0 button
        btn = new Button("0");
        btn.setStyle("-fx-font: 16 arial;");
        btn.setMinSize(60, 60);
        btn.setOnAction(unaryHandler);
        hBox.getChildren().add(btn);
                
        //Creates Enter button
        btn = new Button("Enter");
        btn.setStyle("-fx-font: 16 arial;");
        btn.setMinSize(120, 60);
        hBox.getChildren().add(btn);
        btn.setOnAction(e-> {

            try{
                
                flag = true;
                Object obj = model.newElement(display.getText());
                display.clear();
                model.push((Rational)obj);
                display2.appendText(" "+model.peek().toString());
                
            }catch(Exception ex){  
                display.clear();
            }
        });
        
        //Creates 00 button
        btn = new Button("1/x");
        btn.setStyle("-fx-font: 16 arial; -fx-base:#00FFFF;");
        btn.setMinSize(60, 60);
        btn.setOnAction(e-> {
            try{
                model.push(model.newElement(model.inverse(display.getText())));
                display.clear();
                display2.appendText(" "+model.peek().toString());
            }catch(Exception c){
                display.clear();
            }
        });
        hBox.getChildren().add(btn);
        
        //Creates Vertical Box
        vBox.getChildren().add(display2);
        vBox.getChildren().add(display);
         
        //Creates Main Pane
        mainPane.setTop(vBox);
        mainPane.setCenter(inputWordPane);
        mainPane.setBottom(hBox);
        mainPane.setPrefWidth(242);
        mainPane.setPrefHeight(395);
        
        Scene scene = new Scene(mainPane, 242, 395);
          
        //Creates Stage
        primaryStage.setTitle("Rational Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     **/
    public static void main(String[] args) {
        launch(args);
    }
    
}
