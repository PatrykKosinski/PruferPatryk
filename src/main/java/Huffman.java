import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Huffman {
    public static void main(String[] args) {

        HashMap<Character,Double> map = new HashMap<Character,Double>();
        String s = "aaabbcdddd";

        System.out.println("nr of chars: "+s.length());

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            Double val = map.get(c);
            if(val != null){
                map.put(c, new Double(val + 1));
            }else{
                map.put(c,1.0);
            }
        }


        double sum =0;
        Iterator<Map.Entry <Character, Double> > it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Double> pair = it.next();
            Double newCount = (pair.getValue() == null) ? 0 : pair.getValue()/s.length() ;
            pair.setValue(newCount);
            sum += newCount;
        }

        for (Character name: map.keySet()){
            String key =name.toString();
            String value = map.get(name).toString();
            System.out.println(key + " " + value);
        }

        while(it.hasNext()){

        }

        System.out.println("The sum of frequently is: " +sum );

    }
}
