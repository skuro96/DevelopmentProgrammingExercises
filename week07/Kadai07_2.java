import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;

public class Kadai07_2 extends Application
{
	private TextField tf;
	private Button[][] bt = new Button[4][5];

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		tf = new TextField();
		tf.setEditable(false);
		tf.setMaxWidth(380);
		tf.setFont(Font.font("MonoSpace", 40));
		tf.setAlignment(Pos.CENTER_RIGHT);

		String[][] bt_str = {
			{"CE", "C", "BS", "/"},
			{"7", "8", "9", "*"},
			{"4", "5", "6", "-"},
			{"1", "2", "3", "+"},
			{"±", "0", ".", "="}
		};
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				bt[i][j] = new Button(bt_str[j][i]);
				bt[i][j].setPrefWidth(95);
				bt[i][j].setPrefHeight(95);
				bt[i][j].setFont(Font.font("MonoSpace", 30));
				bt[i][j].setOnAction(new ButtonEventHandler());
			}
		}

		GridPane gp = new GridPane();
		gp.setHgap(2);
		gp.setVgap(2);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 5; j++)
				gp.add(bt[i][j], i, j);
		gp.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane();
		bp.setTop(tf);
		bp.setAlignment(tf, Pos.CENTER);
		bp.setCenter(gp);

		Scene sc = new Scene(bp, 400, 600);
		stage.setScene(sc);
		stage.setTitle("電卓");
		stage.show();
	}

	class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			String in = ((Button)e.getSource()).getText();
			StringBuffer stb = new StringBuffer(tf.getText());

			if (in == "CE" || in == "C")
				stb = new StringBuffer();
			else if (in == "BS")
				stb.deleteCharAt(stb.length() - 1);
			else if (in == "±")
			{
				char head = stb.charAt(0);
				if ('0' <= head && head <= '9')
					stb.insert(0, '-');
			}
			else if (in != "=")
				stb.append(in);

			String str = new String(stb.toString());
			tf.setText(str);

			String regex = "[+\\-]?[0-9]+.?[0-9]*[+\\-\\*/]{1}[0-9]+.?[0-9]*";
			if (in == "=" && str.matches(regex))
			{
				String regex2 = "(?=[+\\-\\*/])";
				String[] operands = str.toString().split(regex2);
				double a = Double.parseDouble(operands[0]);
				double b = Double.parseDouble(operands[1].substring(1));
				char ope = operands[1].charAt(0);

				tf.setText(Double.toString(calc(a, b, ope)));
			}
		}
	}
	
	public double calc(double a, double b, char op)
	{
		if (op == '+')
			return a + b;
		else if (op == '-')
			return a - b;
		else if (op == '*')
			return a * b;
		else
			return a / b;
	}
}