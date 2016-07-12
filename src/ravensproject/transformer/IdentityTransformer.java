package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by michaelbecker on 6/24/16.
 */
public class IdentityTransformer extends Transformer{

    public IdentityTransformer(PrimitiveTransformation transformation) {

        super(transformation);
    }

    public BufferedImage visualTransform(BufferedImage image) {

        return image;
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
