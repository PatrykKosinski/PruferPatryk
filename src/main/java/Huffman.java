import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.HashMap;

public class Huffman {
    public static void main(String[] args) {

        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        String s = "lksdjfksjldfsasdfasfa,sfdsaf;asdfsaf;asdfsa;";
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            Integer val = map.get(c);
            if(val != null){
                map.put(c, new Integer(val + 1));
            }else{
                map.put(c,1);
            }
        }

        for (Character name: map.keySet()){

            String key =name.toString();
            String value = map.get(name).toString();
            System.out.println(key + " " + value);


        }

    }
}
