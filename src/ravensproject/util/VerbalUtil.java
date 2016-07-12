package ravensproject.util;

import ravensproject.RavensFigure;
import ravensproject.RavensObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelbecker on 7/3/16.
 */
public class VerbalUtil {

    public static final double VERBAL_CONFIDENCE = 1.0;


    public static boolean sameFigure(HashMap<String,String> one, HashMap<String,String> two){
        boolean same = true;

        if(!one.get("shape").equals(two.get("shape"))) same = false;
        if(!one.get("fill").equals(two.get("fill"))) same = false;

        return same;
    }

    public static List<RavensObject> differences(HashMap<String,RavensObject> one, HashMap<String,RavensObject> two){
        List<RavensObject> differences = new ArrayList<RavensObject>();

        findDifferences(one, two, differences);
        findDifferences(two, one, differences);

        return differences;
    }

    private static void findDifferences(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two, List<RavensObject> differences) {
        for(String oneKey : one.keySet()){
            RavensObject objectOne = one.get(oneKey);
            boolean contains = false;
            for(String twoKey : two.keySet()){
                RavensObject objectTwo = two.get(twoKey);
                if(compareObject(objectOne, objectTwo)) contains = true;
            }
            if(!contains){
                differences.add(objectOne);
            }
        }
    }

    public static boolean compareObject(RavensObject one, RavensObject two){

        boolean equals = true;

        if(one.getAttributes().get("size") != null){
            if(!one.getAttributes().get("size").equals(two.getAttributes().get("size"))) equals = false;
        }
        if(one.getAttributes().get("shape") != null){
            if(!one.getAttributes().get("shape").equals(two.getAttributes().get("shape"))) equals = false;
        }
        if(one.getAttributes().get("fill") != null){
            if(!one.getAttributes().get("fill").equals(two.getAttributes().get("fill"))) equals = false;
        }

        return equals;
    }

    public static int findObjectDifferences(RavensObject one, RavensObject two){

        HashMap<String, String> oneAttributes = one.getAttributes();
        HashMap<String, String> twoAttributes = two.getAttributes();
        int misses = 0;

        for(String key : oneAttributes.keySet()){
            if(!oneAttributes.get(key).equals(twoAttributes.get(key))){
                misses ++;
            }
        }

        if(twoAttributes.values().size() > oneAttributes.size()){
            misses += twoAttributes.values().size() - oneAttributes.size();
        }

        return misses;
    }

    public static double compareObjects(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two){
        List<RavensObject> oneObjects = new ArrayList<RavensObject>(one.values());
        List<RavensObject> twoObjects = new ArrayList<RavensObject>(two.values());
        double total = Math.max(getTotalAttributes(one), getTotalAttributes(two));
        int misses = 0;

        for(int i=0; i<Math.min(oneObjects.size(), twoObjects.size()); i++){
            misses += findObjectDifferences(oneObjects.get(i), twoObjects.get(i));
        }

        if(two.size() > one.size()){
            misses += getTotalAttributes(two);
        }

        return (total - misses) / total;
    }

    private static int getTotalAttributes(HashMap<String, RavensObject> one){
        int total = 0;
        for(String key : one.keySet()){
            total += one.get(key).getAttributes().size();
        }
        return total;
    }

    public static void rotateImage(HashMap<String, RavensObject> one , int angle){
        for(String key : one.keySet()){
            HashMap<String, String> attributes = one.get(key).getAttributes();
            String objectAngle = attributes.get("angle");
            if(objectAngle != null){
                attributes.put("angle", String.valueOf((Integer.parseInt(objectAngle) + angle) % 360));
            }
        }
    }
}
