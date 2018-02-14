package mchacks2018;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
package mchacks2018;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.*;

public class Parser {
    public Parser() { }

    private static HashMap<String, Integer> analyze(ArrayList<String> document, ArrayList<String> commonWords) {
        HashMap<String, Integer> wordsFrequencyList = new HashMap<>();
        int value;
        for (String word : document) {
            if (isNotCommon(word, commonWords)) {
                if (wordsFrequencyList.containsKey(word)) {
                    value = wordsFrequencyList.get(word);
                    wordsFrequencyList.replace(word, value, value + 1);
                } else {
                    value = 1;
                    wordsFrequencyList.put(word, value);
                }
            }
        }
        return wordsFrequencyList;
    }

    private static ArrayList<String> fillCommon(String commonWordsDatabase) throws IOException {
        ArrayList<String> commonWords = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(commonWordsDatabase));
        String commonWord;
        while ((commonWord = in.readLine()) != null) {
            //String commonWord = in.readLine();
            commonWords.add(commonWord);
        }
        in.close();
        return commonWords;
        
    }

    private static boolean isNotCommon(String word, ArrayList<String> commonWords) {
        return !commonWords.contains(word);
    }

    private static ArrayList<String> readText(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try {
            String currentLine;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] splitWords = currentLine.split("\\s");
                for (String word : splitWords)
                    words.add(word.replaceAll("\\W", ""));
            }
            for (String word : words)
                System.out.println(word);
        } catch (IOException e) {
            System.out.println("Error: There is no such file in the directory.");
        }
        return words;
    }

    public void sortByFrequency(String documentFile, String commonWordsDatabase, String outputFile) {
        try {
            ArrayList<String> commonWords = fillCommon(commonWordsDatabase);
            System.out.println(commonWords);
            ArrayList<String> document = readText(documentFile);
            HashMap<String, Integer> data = analyze(document, commonWords);
            List<Integer> values = new ArrayList<>(data.values());
            List<String> keys = new ArrayList<>(data.keySet());
            values.sort(Collections.reverseOrder());
            keys.sort(Collections.reverseOrder());
            
           
            
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            System.out.println(data);
            for (int i = 0; i < 10; i++){
            	writer.write(keys.get(i) + ":" + data.get(keys.get(i)) + "\n");
            }
      
            writer.close();

            /*BufferedWriter topWriter = new BufferedWriter(new FileWriter("top.txt"));
            int count = 0;
            for (Map.Entry<String, Integer> entry : data.entrySet()){	
   				writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
   				if (count = 10){
   					break;
   				}
   				count++;
			}
            topWriter.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    
    
}
