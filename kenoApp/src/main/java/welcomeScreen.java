import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class welcomeScreen extends Application {

    // public static void main(String[] args) 
	// {
	// 	// TODO Auto-generated method stub
	// 	launch(args);
	// }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        primaryStage.setTitle("Welcome to Keno Lottery Game!");

        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        canvas.setPrefSize(200,200);
        Circle circle = new Circle(50,Color.BLUE);
        circle.relocate(20, 20);
        Rectangle rectangle = new Rectangle(100,100,Color.RED);
        rectangle.relocate(70,70);
        canvas.getChildren().addAll(circle,rectangle);

        Scene welcomeScene = new Scene(canvas, 700, 700);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }
    
}
