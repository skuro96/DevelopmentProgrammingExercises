import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class ex04_1 extends Application
{
	private Canvas cv;
	final int WIDTH = 200;
	final int HEIGHT = 200;

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		cv = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = cv.getGraphicsContext2D();

		gc.setStroke(Color.RED);
		gc.strokeLine(50, 10, 150, 10);
		gc.strokeLine(50, 110, 150, 110);
		gc.setStroke(Color.BLUE);
		gc.strokeLine(50, 10, 50, 110);
		gc.strokeLine(150, 10, 150, 110);
		gc.setLineWidth(3.0);
		gc.setStroke(Color.BLACK);
		gc.strokeOval(50, 10, 100, 100);
		gc.setLineWidth(1);
		String str = "中心";
		gc.strokeText(str, 85, 65, 30);

		BorderPane bp = new BorderPane();
		bp.setCenter(cv);
		Scene sc = new Scene(bp, WIDTH, HEIGHT);
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}
}