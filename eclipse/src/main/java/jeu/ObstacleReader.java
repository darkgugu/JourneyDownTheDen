package jeu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class ObstacleReader {
	private BasicGameApp basicGameApp;

	public List<SimpleEntry<Integer, Integer>> map_obstacle = new ArrayList<SimpleEntry<Integer, Integer>>();

	public void setBasicGameApp(BasicGameApp basicGameApp) {
		this.basicGameApp = basicGameApp;
	}
	public void reader() {
		
		try{
			File file = new File("./map1_obstacles.txt");  

			FileReader FileReader = new FileReader(file);  
			BufferedReader br = new BufferedReader(FileReader);  
	      
			String line;
			while((line = br.readLine()) != null){
				
				String[] parts = line.split(",");
				String x_str = parts[0];
				String y_str = parts[1];
				Integer x = Integer.valueOf(x_str);
				Integer y = Integer.valueOf(y_str);
				add(x, y);
			}
			
	      FileReader.close();  
	    }
	    catch(IOException e){
	    	
	      e.printStackTrace();
	    }
		
	}

	public void add(int x, int y) {

		map_obstacle.add(new SimpleEntry<Integer, Integer>(x, y));

	}

}
