package ui;

import com.almasb.fxgl.scene.GameScene;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jeu.Player;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;
import javafx.scene.text.Font;

public class CharInfoView {
	private Text textCharInfo;
	private Text textSkip;
	private Text textHelpInfo;
	private Text textRedInfo;
	private Text textGreenInfo;
	private Text textBlueInfo;
    private ScrollPane test;
    private Text logTest;



	public CharInfoView(GameScene gameScene, Player playerRed, Player playerGreen, Player playerBlue, String gameLog) {
		String newLine = System.getProperty("line.separator");

		/*
		 * Descriptive text
		 */
		
		logTest = new Text();
		logTest.setFont(Font.font("Verdana", 20));
		logTest.setFill(Color.BLACK);
		logTest.setText(gameLog);

		test = new ScrollPane();
		test.setPrefWidth(450);
		test.setPrefHeight(150);
		test.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		test.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		test.setTranslateX(1440);
		test.setTranslateY(901);
		test.setContent(logTest);
		
		textSkip = new Text();
		textSkip.setFont(Font.font("Verdana", 20));
		textSkip.setFill(Color.BLACK);
		textSkip.setTranslateX(1390);
		textSkip.setTranslateY(936);
		textSkip.setText("END");
		
//		textHelpInfo = new Text();
//		textHelpInfo.setFont(Font.font("Verdana", 20));
//		textHelpInfo.setFill(Color.BLACK);
//		textHelpInfo.setTranslateX(1450);
//		textHelpInfo.setTranslateY(923);
//		textHelpInfo.setText("~~Commandes~~" + newLine + "F : afficher/cacher la grille" 
//											 + newLine + "Clic droit : selectioner une unité");

		textCharInfo = new Text();
		textCharInfo.setFont(Font.font("Verdana", 15));
		textCharInfo.setFill(Color.BLACK);
		textCharInfo.setTranslateX(20);
		textCharInfo.setTranslateY(923);
		textCharInfo.setText("HP            " + "ATK        " + "MVT");
		
		/*
		 * Units characteristics
		 */
		textRedInfo = new Text();
		textRedInfo.setFont(Font.font("Verdana", 15));
		textRedInfo.setFill(Color.BLACK);
		textRedInfo.setTranslateX(20);
		textRedInfo.setTranslateY(955);
		textRedInfo.setText(playerRed.getHeroClass().getPv() + "/" + playerRed.getHeroClass().getPvMax() + "        "
							+ playerRed.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerRed.getHeroClass().getMovePoint() + "               " + playerRed.getName());

		textGreenInfo = new Text();
		textGreenInfo.setFont(Font.font("Verdana", 15));
		textGreenInfo.setFill(Color.BLACK);
		textGreenInfo.setTranslateX(20);
		textGreenInfo.setTranslateY(1000);
		textGreenInfo.setText(playerGreen.getHeroClass().getPv() + "/" + playerGreen.getHeroClass().getPvMax() + "    "
							+ playerGreen.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerGreen.getHeroClass().getMovePoint() + "               " + playerGreen.getName());

		textBlueInfo = new Text();
		textBlueInfo.setFont(Font.font("Verdana", 15));
		textBlueInfo.setFill(Color.BLACK);
		textBlueInfo.setTranslateX(20);
		textBlueInfo.setTranslateY(1050);
		textBlueInfo.setText(playerBlue.getHeroClass().getPv() + "/" + playerBlue.getHeroClass().getPvMax() + "    "
						+ playerBlue.getHeroClass().getMagicalBaseDamage() + "          "
						+ playerBlue.getHeroClass().getMovePoint() + "               " + playerBlue.getName());

		
		gameScene.addUINodes(textSkip, textCharInfo, /*textHelpInfo,*/ textRedInfo, textBlueInfo, textGreenInfo, test, logTest);
	}

	//Update of units characteristics
	public void updateInfo(GameScene gamescene, Player playerRed, Player playerBlue, Player playerGreen, String gameLog) {
		
		textRedInfo.setText(playerRed.getHeroClass().getPv() + "/" + playerRed.getHeroClass().getPvMax() + "        "
							+ playerRed.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerRed.getHeroClass().getMovePoint() + "               " + playerRed.getName());
		
		textGreenInfo.setText(playerGreen.getHeroClass().getPv() + "/" + playerGreen.getHeroClass().getPvMax() + "    "
							+ playerGreen.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerGreen.getHeroClass().getMovePoint() + "               " + playerGreen.getName());
		
		textBlueInfo.setText(playerBlue.getHeroClass().getPv() + "/" + playerBlue.getHeroClass().getPvMax() + "    "
							+ playerBlue.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerBlue.getHeroClass().getMovePoint() + "               " + playerBlue.getName());
		
		logTest.setText(gameLog);
		test.setContent(logTest);
		
	}
}
