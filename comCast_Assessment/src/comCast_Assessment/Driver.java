package comCast_Assessment;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Driver {

	public static void main(String[] args) {
		List<Map<String , String>> aList = new ArrayList<>();
		List<List<String>> CSVData = new ArrayList<>();
		Map<String , String> setData = null;
		String Filepath = "/Users/rameshkumarpalanisamy/Downloads/comcast/ComcastCSV.csv";
		CSVData = ReadCSVFile(Filepath);
		
		if(CSVData.size()>0) {
			for (int i = 2; i <= CSVData.size()-1; i++) {
				setData = new HashMap<String , String>();
				for (int j = 0; j < CSVData.get(i).size(); j++) {
					setData.put("field"+j, CSVData.get(i).get(j));
			}
				aList.add(setData);
			}
			getTotalNumberofFruits(aList);
			getTotalTypesOfFruit(aList);
			getOldestFruitAndAge(aList);
			getTheNumberOfEachTypeOfFruitInDescendingOrder(aList);
			getVariousCharacteristicsOfEachFruitByYype(aList);	
		}
	}
	
	public static List<List<String>> ReadCSVFile(String Filepath) {
		List<List<String>> DataList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(Filepath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        DataList.add(Arrays.asList(values));
		    }
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found in provided path. Please check your provided path ");
		}	
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
		return DataList;
	}
	
	public static void getTotalNumberofFruits(List<Map<String , String>> aList) {
		Long sum = aList.stream().map(a -> a.get("field0")).collect(Collectors.counting());
		System.out.println("Total number of fruit: " + "\n" + sum);
		System.out.println("*********************************************");
	}

	public static void getTotalTypesOfFruit(List<Map<String , String>> aList) {
		Set<String> uniqueNames = aList.stream().map(a -> a.get("field0")).collect(Collectors.toSet());
		System.out.println("Total types of fruit: " + "\n" + uniqueNames.size());
		System.out.println("*********************************************");
	}
	
	public static void getOldestFruitAndAge(List<Map<String , String>> aList) {
		Optional<String> obj1 = aList.stream().map(a -> a.get("field0")).collect(Collectors.minBy(Comparator.reverseOrder()));
		Optional<String> obj = aList.stream().map(a -> a.get("field1")).collect(Collectors.minBy(Comparator.reverseOrder()));
		System.out.println("Oldest fruit & age:"  + "\n" + obj1.get() + ": " + obj.get());
		System.out.println("*********************************************");
	}
	
	public static void getTheNumberOfEachTypeOfFruitInDescendingOrder(List<Map<String , String>> aList) {
		Map<String, Long> counting = aList.stream().collect(
                Collectors.groupingBy(a -> a.get("field0"), Collectors.counting()));
        System.out.println("The number of each type of fruit in descending order:");
        counting.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + ": " + entry.getValue());  
         });
        System.out.println("*********************************************");
   }
	public static void getVariousCharacteristicsOfEachFruitByYype(List<Map<String , String>> aList) { 
		Map<Object, Long> VariousCharacteristicsOfEachFruitByYype = aList.stream().collect(Collectors.groupingBy(x -> Arrays.asList(x.get("field0"), x.get("field2"), x.get("field3")), Collectors.counting()));
		System.out.println("The various characteristics (count, color, shape, etc.) of each fruit by type: ");	
		VariousCharacteristicsOfEachFruitByYype.entrySet().forEach(entry->{
            System.out.println(entry.getValue() + ": " + entry.getKey());  
         });
		System.out.println("*********************************************");
	}
}
