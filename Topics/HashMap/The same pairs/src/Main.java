import java.util.*;
import java.util.stream.Collectors;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        System.out.println(map1.entrySet().stream()
                .filter(entry -> map2.containsKey(entry.getKey()) && map2.get(entry.getKey()).equals(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).size());

        //map1.entrySet().retainAll(map2.entrySet());
    }
}