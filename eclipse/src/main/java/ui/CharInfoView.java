package ui;

import com.almasb.fxgl.scene.GameScene;
import com.almasb.fxgl.ui.FXGLTextFlow;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jeu.Player;
import jeu.Tour;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;

public class CharInfoView {
	private Text textCharInfo;
	private Text textSkip;
	private Text textHelpInfo;
	private Text textRedInfo;
	private Text textGreenInfo;
	private Text textBlueInfo;
    private ScrollPane scrollPane;
    private Text log;
    private Text textTour;
    private Text errLog;
	public CharInfoView(GameScene gameScene, Player playerRed, Player playerGreen, Player playerBlue, String gameLog) {
		String newLine = System.getProperty("line.separator");

		/*
		 * Descriptive text
		 */
		log = new Text();
		log.setFont(Font.font("Helvetica", 16));
		log.setFill(Color.BLACK);
		log.setText(gameLog);
		
		scrollPane = new ScrollPane();
		scrollPane.setPrefWidth(450);
		scrollPane.setPrefHeight(150);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setTranslateX(1440);
		scrollPane.setTranslateY(901);
		scrollPane.setContent(log);
		
		textSkip = new Text();
		textSkip.setFont(Font.font("Verdana", 20));
		textSkip.setFill(Color.BLACK);
		textSkip.setTranslateX(1390);
		textSkip.setTranslateY(936);
		textSkip.setText("END");
//		button = new Button("My Button");
//        button.setPrefSize(400, 300);
		
		textHelpInfo = new Text();
		textHelpInfo.setFont(Font.font("Verdana", 20));
		textHelpInfo.setFill(Color.BLACK);
		textHelpInfo.setTranslateX(1450);
		textHelpInfo.setTranslateY(923);
		textHelpInfo.setText("~~Commandes~~" + newLine + "F : afficher/cacher la grille" 
											 + newLine + "Clic droit : selectioner une unit�");

		textCharInfo = new Text();
		textCharInfo.setFont(Font.font("Verdana", 15));
		textCharInfo.setFill(Color.BLACK);
		textCharInfo.setTranslateX(20);
		textCharInfo.setTranslateY(923);
		textCharInfo.setText("HP            " + "ATK        " + "MVT     PA");
		
		textTour = new Text();
		textTour.setFont(Font.font("Verdana", 20));
		textTour.setFill(Color.BLACK);
		textTour.setTranslateX(400);
		textTour.setTranslateY(920);
		textTour.setText("Tour : 0");
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
							+ playerRed.getHeroClass().getMovePoint() + "         " + playerRed.getHeroClass().getActionPoint() + "               " + playerRed.getName());

		textGreenInfo = new Text();
		textGreenInfo.setFont(Font.font("Verdana", 15));
		textGreenInfo.setFill(Color.BLACK);
		textGreenInfo.setTranslateX(20);
		textGreenInfo.setTranslateY(1000);
		textGreenInfo.setText(playerGreen.getHeroClass().getPv() + "/" + playerGreen.getHeroClass().getPvMax() + "    "
							+ playerGreen.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerGreen.getHeroClass().getMovePoint() + "          " + playerGreen.getHeroClass().getActionPoint() + "   " + playerGreen.getName());

		textBlueInfo = new Text();
		textBlueInfo.setFont(Font.font("Verdana", 15));
		textBlueInfo.setFill(Color.BLACK);
		textBlueInfo.setTranslateX(20);
		textBlueInfo.setTranslateY(1050);
		textBlueInfo.setText(playerBlue.getHeroClass().getPv() + "/" + playerBlue.getHeroClass().getPvMax() + "    "
						+ playerBlue.getHeroClass().getMagicalBaseDamage() + "          "
						+ playerBlue.getHeroClass().getMovePoint() + "          "  + playerBlue.getHeroClass().getActionPoint() + "   " + playerBlue.getName());

		
		gameScene.addUINodes(textSkip, textCharInfo,textRedInfo, textBlueInfo, textGreenInfo, scrollPane, log, textTour);
	}

	//Update of units characteristics
	public void updateInfo(GameScene gamescene, Player playerRed, Player playerBlue, Player playerGreen, String gameLog, int tour) {
		
		textRedInfo.setText(playerRed.getHeroClass().getPv() + "/" + playerRed.getHeroClass().getPvMax() + "        "
							+ playerRed.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerRed.getHeroClass().getMovePoint() + "         " 
							+ playerRed.getHeroClass().getActionPoint() +"               " 
							+ playerRed.getName());
		
		textGreenInfo.setText(playerGreen.getHeroClass().getPv() + "/" + playerGreen.getHeroClass().getPvMax() + "    "
							+ playerGreen.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerGreen.getHeroClass().getMovePoint() + "         " 
							+ playerGreen.getHeroClass().getActionPoint() +"               " 
							+ playerGreen.getName());
		
		textBlueInfo.setText(playerBlue.getHeroClass().getPv() + "/" + playerBlue.getHeroClass().getPvMax() + "    "
							+ playerBlue.getHeroClass().getMagicalBaseDamage() + "          "
							+ playerBlue.getHeroClass().getMovePoint() + "         " 
							+ playerBlue.getHeroClass().getActionPoint() +"               " 
							+ playerBlue.getName());
		
		log.setText(gameLog);
		scrollPane.setContent(log);
		scrollPane.setVvalue(scrollPane.getVmax());
		
		textTour.setText("Tour : " + tour);
		
	}
	
	public void updateLog(String gameLog) {
		
		log.setText(gameLog);
		scrollPane.setContent(log);
		scrollPane.setVvalue(scrollPane.getVmax());
	}
}
