package ui;

public abstract class GameLog {
	
	private static String gameLog = "Zone : Village\n";

	public static String getGameLog() {
		return gameLog;
	}

	public static void setGameLog(String addedLog) {
		
		gameLog += addedLog + "\n";
		CharInfoView.updateLog(getGameLog());

	}

	
}