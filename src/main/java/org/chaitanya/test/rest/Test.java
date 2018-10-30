package org.chaitanya.test.rest;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
		//Test arrays of elements
		String[] arr1 = {"this", "that", "them"};
		String[] arr2 = {"ad", "ac", "ab", "ab", "ae"};
		String[] arr3 = {"bza", "cz", "dzal", "cya"};
		String[] arr4 = {"catsy", "batsy", "ratsy", "fatsy", "matsy"};
		String[] arr5 = {"lone", "alone", "one", "on", "no"};
		//performed sorting by word values
		arr1 = sortStringsByValue(arr1);
		arr2 = sortStringsByValue(arr2);
		arr3 = sortStringsByValue(arr3);
		arr4 = sortStringsByValue(arr4);
		arr4 = sortStringsByValue(arr4);
		//Test case 1: Display sorted 4 letter words
		System.out.println("Test Case 1:\n");
		for(String ele:arr1) {
			System.out.println(ele);
		}
		//Test case2: Display sorted 2 letter words
		System.out.println("\nTest case 2:\n");
		for(String ele:arr2) {
			System.out.println(ele);
		}
		//Test case3: Display sorted varying-lettered words
		System.out.println("\nTest case 3:\n");
		for(String ele:arr3) {
			System.out.println(ele);
		}
		//Test case4: Display sorted 5 letter words
		System.out.println("\nTest case 4:\n");
		for(String ele:arr4) {
			System.out.println(ele);
		}
		//Test case5: Display sorted varying-lettered words
		System.out.println("\nTest case 5:\n");
		for(String ele:arr5) {
			System.out.println(ele);
		}
	}
	
public static String[] sortStringsByValue(String[] inputArr) {
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		Map<Integer, Integer> stringValuesWithIndex = new HashMap<>();
		Map<Integer, Integer> sortedValuesWithIndex = new HashMap<>();
		Set<Integer> values = new LinkedHashSet<Integer>();
		String[] sortedArray = new String[inputArr.length];
		for(int i = 0; i < inputArr.length; i++) {
			
			int totalForString = 0;
			int alphabetIndex = 0;
			for(int j = 0; j < inputArr[i].length(); j++) {
			
				for(char ch: alphabet.toCharArray()){					
					if(inputArr[i].charAt(j) == ch) {
						totalForString += totalForString + alphabetIndex + 1; 
						
					}
					alphabetIndex++;
				}
			}
			stringValuesWithIndex.put(i, totalForString);
		}
		
		sortedValuesWithIndex = sortByComparator(stringValuesWithIndex);
		values = sortedValuesWithIndex.keySet();
		int sortedArrayIndex = 0;
		for(Integer val:values) {
			
			sortedArray[sortedArrayIndex] = inputArr[val];
			sortedArrayIndex++;
		}
		return sortedArray;
	}
	
	private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap)
    {

        List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>()
        {
            
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());  
			}
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Entry<Integer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
