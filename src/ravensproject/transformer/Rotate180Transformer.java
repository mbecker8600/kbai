package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.transformer.primitives.RotateFrame;
import ravensproject.util.ImageUtil;
import ravensproject.util.VerbalUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class Rotate180Transformer extends Transformer{

    public Rotate180Transformer(PrimitiveTransformation transformation) {
        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {
        BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for(int x=0; x<image.getWidth(); x++){
            for(int y=0; y<image.getHeight(); y++){
                rotatedImage.setRGB(x, y, image.getRGB(image.getWidth() - 1 - x, image.getWidth() - 1 - y));
            }
        }

        return rotatedImage;
    }

    @Override
    public PrimitiveTransformation verbalTransform(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two) {
        RotateFrame rotateTransformation = null;
        VerbalUtil.rotateImage(one, 180);
        double confidence = VerbalUtil.compareObjects(one, two);

        if(confidence > VerbalUtil.VERBAL_CONFIDENCE){
            rotateTransformation = new RotateFrame(180);
        }

        return rotateTransformation;
    }

    @Override
    public void initialize(BufferedImage imageOne, BufferedImage imageTwo){
        //no initialization needed
    }

}
