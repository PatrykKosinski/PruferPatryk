import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Huffman {

    public static void main(String[] args) {
        Graph graph = new SingleGraph("Prufer graph");


      String s = readFile("huffman.txt");

        System.out.println("nr of chars: " + s.length());

        HashMap<Character,Double> map = calculateFrequency(s);

        displayHashMap(map);

        findKeyOfMinimalValueFromHashmap(map);


        for (int i = 0; i<4; i++){
            Character key1 = findKeyOfMinimalValueFromHashmap(map);
            Double value1  = findMinimalValueFromHashmap(map);

            if(graph.getNode(String.valueOf(key1))==null){
                graph.addNode(String.valueOf(key1));
            }
            map.remove(key1);
            Character key2 = findKeyOfMinimalValueFromHashmap(map);
            Double value2  = findMinimalValueFromHashmap(map);

            if(graph.getNode(String.valueOf(key2))==null){
                graph.addNode(String.valueOf(key2));
            }
            map.remove(key2);

            map.put((char)i,value1+value2);




            if(graph.getNode(String.valueOf(i))==null){
                graph.addNode(String.valueOf(i));
            }
            String nameOfEdge1 = String.valueOf(key1) + String.valueOf(i);
            String nameOfEdge2 = String.valueOf(key2) + String.valueOf(i);
            graph.addEdge(nameOfEdge1, String.valueOf(key1), String.valueOf(i));
            graph.addEdge(nameOfEdge2, String.valueOf(key2), String.valueOf(i));




        }

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        graph.display();


    }

    public static String readFile(String source){
        String s = null;
        try {
            FileReader fileReader = new FileReader(source);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            s = "";
            String line = bufferedReader.readLine();

            while (line != null) {
                s += line;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

    public static HashMap<Character, Double> calculateFrequency(String s){

        HashMap<Character, Double> map = new HashMap<Character, Double>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Double val = map.get(c);
            if (val != null) {
                map.put(c, new Double(val + 1));
            } else {
                map.put(c, 1.0);
            }
        }


        double sum = 0;
        Iterator<Map.Entry<Character, Double>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Double> pair = it.next();
            Double newCount = (pair.getValue() == null) ? 0 : pair.getValue() / s.length();
            pair.setValue(newCount);
            sum += newCount;
        }
        System.out.println("The sum of frequently is: " + sum);

        return map;
    }

    public static void displayHashMap(HashMap<Character, Double> map){

        for (Character name : map.keySet()) {
            String key = name.toString();
            String value = map.get(name).toString();
            System.out.println(key + " " + value);
        }

    }

    public static Character findKeyOfMinimalValueFromHashmap(HashMap<Character,Double> map){



        Map.Entry<Character, Double> min = null;
        for (Map.Entry<Character, Double> entry : map.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }

        Character key = min.getKey();
        System.out.println("minimal value key is:  "+ min.getKey()); // 0.1

        return key;
    }

    public static Double findMinimalValueFromHashmap(HashMap<Character,Double> map){



        Map.Entry<Character, Double> min = null;
        for (Map.Entry<Character, Double> entry : map.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getValue();
    }

}
