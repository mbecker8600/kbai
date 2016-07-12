package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class XAxisReflectionTransformer extends Transformer {


    public XAxisReflectionTransformer(PrimitiveTransformation transformation) {
        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {

        BufferedImage reflectedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int x=0;x<image.getWidth();x++){
            for(int y=0;y<image.getHeight();y++){
                reflectedImage.setRGB(x, y, image.getRGB(x, image.getHeight() - (y+1)));
            }
        }

        return reflectedImage;
    }

    @Override
    public PrimitiveTransformation verbalTransform(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two) {
        return null;
    }

    @Override
    public void initialize(BufferedImage imageOne, BufferedImage imageTwo){
        //no initialization needed
    }
}
