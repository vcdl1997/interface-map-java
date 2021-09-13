package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Program {

	public static void main(String[] args) {
		
		File path = new File("C:\\temp\\candidatos.csv");
		Map<String, Integer> votes = new HashMap<String, Integer>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			
			while(line != null) {
				String[] params = line.split(",");
				
				if(votes.containsKey(params[0])) {
					votes.put(params[0], votes.get(params[0]) + Integer.valueOf(params[1]));
				}else {
					votes.put(params[0], Integer.valueOf(params[1]));
				}
				
				line = br.readLine();
			}
			
			Map<String, Integer> votesOrdered = new LinkedHashMap<String, Integer>();  
			
			votes
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> votesOrdered.put(x.getKey(), x.getValue()));
			;
			
			for(Entry<String, Integer> vote : votesOrdered.entrySet()) {
				System.out.println(vote.getKey() + ": " + vote.getValue());
			}
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
