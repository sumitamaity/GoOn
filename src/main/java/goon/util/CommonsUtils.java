package goon.util;
import java.util.Map;


public class CommonsUtils {

    public static boolean compareData(Map<String, String> map1, Map<String, String> map2){

        for(String a: map1.keySet()){
        if(!(a.isEmpty())){
            if(map2.containsKey(a)) {
                if (!map2.containsValue(map1.get(a))) {
                    //System.out.println(a);
                    return false;
                }
            }else {
                /*add exceptions for the names that are similar, search for
                common words and compare - time doesn't permit
                */
                System.out.println(a+ " is not found in the other table to compare");
            }

        }

    }
return true;
    }
}
