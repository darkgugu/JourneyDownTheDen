package ui;

import com.almasb.fxgl.scene.GameScene;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jeu.Player;
import personnages.Unites;
import javafx.scene.text.Font;

public abstract class CharInfoView extends Application {

	public static void charInfoUI(GameScene gameScene, Player player) {
		String newLine = System.getProperty("line.separator");
		Text textCharInfo = new Text();
		Text textRedInfo = new Text();
		Text textHelpInfo = new Text();
		/*
		 * essayer d'utiliser une font custom
		 */
//	    Font.loadFont(
//	    	      CharInfoView.class.getResource("font.tif").toExternalForm(), 
//	    	      10
//	    	    );

//		Font font = Font.loadFont("file:resources/assets/ui/fonts/font.tif", 45);
//		textCharInfo.setFont(font);

//		textCharInfo.setFont(Font.loadFont("file:resources/assets/ui/fonts/font.ttf", 30));

		textCharInfo.setFont(Font.font("Verdana", 15));
		textCharInfo.setFill(Color.BLACK);
		textCharInfo.setTranslateX(20);
		textCharInfo.setTranslateY(923);
		textCharInfo.setText("HP         " + "ATK        " + "MVT");
		
		textHelpInfo.setFont(Font.font("Verdana", 20));
		textHelpInfo.setFill(Color.BLACK);
		textHelpInfo.setTranslateX(1200);
		textHelpInfo.setTranslateY(923);
		textHelpInfo.setText("~~Aide~~" + newLine + "Appuyer sur F pour afficher/cacher la grille");

		textRedInfo.setFont(Font.font("Verdana", 25));
		textRedInfo.setFill(Color.BLACK);
		textRedInfo.setTranslateX(20);
		textRedInfo.setTranslateY(955);
		textRedInfo.setText("                 " + "2      "+ player.getName());
		gameScene.addUINodes(textCharInfo, textHelpInfo, textRedInfo);

	}

}
