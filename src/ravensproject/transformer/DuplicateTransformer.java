package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.DuplicateFrame;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelbecker on 7/3/16.
 */
public class DuplicateTransformer extends Transformer{

    public DuplicateTransformer(PrimitiveTransformation transformation) {

        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {

        BufferedImage finalImage = ImageUtil.deepCopy(image);

        return finalImage;
    }

    @Override
    public PrimitiveTransformation verbalTransform(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two) {
        DuplicateFrame transformation = null;

        Map<String,Integer> shapesizeCounterOne = new HashMap<String,Integer>();
        countDuplicateObjects(one, shapesizeCounterOne);

        Map<String,Integer> shapesizeCounterTwo= new HashMap<String,Integer>();
        countDuplicateObjects(two, shapesizeCounterTwo);

        for(String oneKey : shapesizeCounterOne.keySet()){
            for(String twoKey : shapesizeCounterTwo.keySet()){
                if(oneKey.equals(twoKey)){
                    int addedDuplicates = shapesizeCounterTwo.get(twoKey) - shapesizeCounterOne.get(oneKey);
                    if(addedDuplicates > 0){
                        transformation = new DuplicateFrame();
                        transformation.setNumberOfDuplicates(addedDuplicates);
                        transformation.setShapesize(oneKey);
                    }
                }
            }
        }

        return transformation;
    }

    private void countDuplicateObjects(HashMap<String, RavensObject> one, Map<String, Integer> counterMap) {
        for(String key : one.keySet()){
            String shapesizeKey = "";
            HashMap<String,String> attributes = one.get(key).getAttributes();
            for(String attrKey : attributes.keySet()){
                if(attrKey.equals("shape") || attrKey.equals("size")){
                    shapesizeKey += attributes.get(attrKey);
                }
            }
            if(counterMap.get(shapesizeKey) == null){
                counterMap.put(shapesizeKey, 1);
            }
            else{
                counterMap.put(shapesizeKey, counterMap.get(shapesizeKey) + 1);
            }
        }
    }

    @Override
    public void initialize(BufferedImage imageOne, BufferedImage imageTwo){
        //do nothing
    }
}
