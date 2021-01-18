package ui;

public abstract class GameLog {
	
	private static String gameLog = "Début du log\n";

	public static String getGameLog() {
		return gameLog;
	}

	public static void setGameLog(String addedLog) {
		
		gameLog += addedLog + "\n";
	}

	
}