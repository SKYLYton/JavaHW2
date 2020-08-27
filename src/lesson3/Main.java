package lesson3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {

        //1 задание
        addWord("d");
        addWord("a");
        addWord("d");
        addWord("g");
        addWord("k");
        addWord("j");
        addWord("h");
        addWord("h");
        addWord("r");
        addWord("d");
        addWord("r");

        printUniqueWords(map);

        System.out.println(map);

        //2 задание

        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();

        telephoneDirectory.add("K", "876");
        telephoneDirectory.add("K", "65467878");
        telephoneDirectory.add("y", "8756");
        telephoneDirectory.add("d", "456765");
        telephoneDirectory.add("y", "84576");
        telephoneDirectory.add("K", "85676");
        telephoneDirectory.add("p", "87897876");
        telephoneDirectory.add("y", "876786786");

        System.out.println(Arrays.toString(telephoneDirectory.get("K")));

    }

    public static void printUniqueWords(Map<String, Integer> map){
        System.out.print("{ ");
        map.forEach((k, v) ->{
            if(v == 1){
                System.out.print(k + " ");
            }
        });
        System.out.println("}");
    }

    public static void addWord(String word){
        map.merge(word, 1, Integer::sum);
    }
}
