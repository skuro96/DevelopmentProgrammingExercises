import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.text.*;
import javafx.util.*;

public class ex04_2 extends Application
{
	private Canvas cv;
	final int WIDTH = 200;
	final int HEIGHT = 200;
	final int RADIUS = 80;

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		cv = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = cv.getGraphicsContext2D();
		BorderPane bp = new BorderPane();
		bp.setCenter(cv);
		Scene sc = new Scene(bp, WIDTH, HEIGHT);
		stage.setScene(sc);
		stage.setTitle("6秒タイマー");
		stage.show();

		Thread th = new Thread(() ->{
			for (int t = 0; t < 60; t++, t %= 60)
			{
				gc.clearRect(0, 0, WIDTH, HEIGHT);
				int rad_s = (int)(RADIUS * 0.8);
				int cx = WIDTH / 2;
				int cy = HEIGHT / 2;
				double theta_sec = t * 6 / 180.0 * Math.PI;
				double xs = rad_s * Math.cos(theta_sec);
				double ys = rad_s * Math.sin(theta_sec);
				gc.setStroke(Color.BLACK);
				gc.strokeOval(cx - RADIUS, cy - RADIUS, RADIUS * 2, RADIUS * 2);
				gc.setStroke(Color.RED);
				gc.strokeLine(cx, cy, (int)(cx + xs), (int)(cy + ys));
				try
				{
					Thread.sleep(100);
				}
				catch(Exception e){}
			}
		});

		th.setDaemon(true);
		th.start();
	}
}