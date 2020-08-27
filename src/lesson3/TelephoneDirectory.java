package lesson3;

import java.util.*;

public class TelephoneDirectory {
    private final Map<String, HashSet<String>> map = new HashMap<>();

    public void add(String surname, String tel) {
        map.computeIfAbsent(surname, k -> new HashSet<String>());

        map.get(surname).add(tel);
    }

    public String[] getTels(String surname) {
        return map.get(surname).toArray(new String[0]);
    }

}
