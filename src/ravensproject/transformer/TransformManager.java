package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.*;
import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelbecker on 6/24/16.
 */
public class TransformManager {

    public TransformManager(){

    }

    /**
     * Determine the transformations that it takes to make the first image most similar to the
     * second image.
     *
     * @param one
     * @param two
     * @return
     */
    public List<PrimitiveTransformation> determineTransformations(BufferedImage one,
                                                                  HashMap<String,RavensObject> oneObjects,
                                                                  BufferedImage two,
                                                                  HashMap<String,RavensObject> twoObjects){

        List<Transformer> transformers = getTransformers();
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        for(Transformer transformer : transformers){
            transformer.initialize(one, two);
            double comparision = transformer.compareTransformation(one, oneObjects, two, twoObjects);
            if(comparision >= ImageUtil.COMPARE_THRESHOLD){
                PrimitiveTransformation transformation = transformer.getTransformation();
                transformation.setConfidence(comparision);
                transformations.add(transformation);
            }
        }

        //if nothing was found, it was transformed
        if(transformations.isEmpty()){
            PrimitiveTransformation transform = new TransformFrame();
            transform.setConfidence(1.0);
            transformations.add(transform);
        }

        return transformations;
    }

    private List<Transformer> getTransformers(){
        List<Transformer> transformers = new ArrayList<Transformer>();

        Transformer identityTransformer = new IdentityTransformer(new IdentityFrame());
        transformers.add(identityTransformer);

        Transformer yAxisReflection = new YAxisReflectionTransformer(new ReflectionFrame("Y"));
        transformers.add(yAxisReflection);

        Transformer xAxisReflection = new XAxisReflectionTransformer(new ReflectionFrame("X"));
        transformers.add(xAxisReflection);

        Transformer rotate90 = new Rotate90Transformer(new RotateFrame(90));
        transformers.add(rotate90);

        Transformer rotate180 = new Rotate180Transformer(new RotateFrame(180));
        transformers.add(rotate180);

        Transformer rotate270 = new Rotate270Transformer(new RotateFrame(270));
        transformers.add(rotate270);

        Transformer fill = new FillTransformer(new FillFrame(true));
        transformers.add(fill);

        Transformer add = new AddTransformer(new AddFrame(true));
        transformers.add(add);

        Transformer remove = new RemoveTransformer(new AddFrame(false));
        transformers.add(remove);

        Transformer enlarge = new EnlargeTransformer(new SizeFrame(true));
        transformers.add(enlarge);

        Transformer duplicate = new DuplicateTransformer(new DuplicateFrame());
        transformers.add(duplicate);

        return transformers;
    }
}
