package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class FillTransformer extends Transformer {

    private static final int black = 0xFF000000;
    private static final int white = 0xFFFFFFFF;

    public FillTransformer(PrimitiveTransformation transformation) {
        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {

        BufferedImage filledImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int y=0;y<image.getHeight();y++){

            int start = -1;
            int end = -1;

            //find start and end
            for(int x=0;x<image.getWidth();x++){
                if(image.getRGB(x, y) == black && start == -1){
                    start = x;
                }
                else if(image.getRGB(x, y) == black && start != -1){
                    end = x;
                }
            }

            //start filling image
            for(int x=0;x<image.getWidth();x++) {
                if (start > -1 && end > -1 && x >= start && x <= end) {
                    filledImage.setRGB(x, y, black);
                } else {
                    filledImage.setRGB(x, y, white);
                }
            }
        }

        return filledImage;
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
