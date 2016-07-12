package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by michaelbecker on 6/24/16.
 */
public abstract class Transformer {

    private PrimitiveTransformation transformation;

    public Transformer(PrimitiveTransformation transformation){
        this.transformation = transformation;
    }

    public abstract void initialize(BufferedImage imageOne, BufferedImage imageTwo);

    public abstract BufferedImage visualTransform(BufferedImage image);

    public abstract PrimitiveTransformation verbalTransform(HashMap<String,RavensObject> one, HashMap<String,RavensObject> two);

    /**
     *
     * @param one
     * @param oneObjects
     * @param two
     * @param twosObjects
     * @return
     */
    public double compareTransformation(BufferedImage one, HashMap<String,RavensObject> oneObjects, BufferedImage two, HashMap<String,RavensObject> twosObjects){
        BufferedImage transformedImage = visualTransform(one);
        double confidence = ImageUtil.compareImages(transformedImage, two);
        if(confidence < ImageUtil.COMPARE_THRESHOLD){
            PrimitiveTransformation transformation = verbalTransform(oneObjects, twosObjects);
            if(transformation != null){
                setTransformation(transformation);
                confidence = 1.0;
            }
        }
        return confidence;
    }

    public PrimitiveTransformation getTransformation(){
        return transformation;
    }

    public void setTransformation(PrimitiveTransformation transformation) {
        this.transformation = transformation;
    }
}
