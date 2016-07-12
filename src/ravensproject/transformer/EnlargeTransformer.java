package ravensproject.transformer;


import ravensproject.RavensObject;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.transformer.primitives.SizeFrame;
import ravensproject.util.ImageUtil;
import ravensproject.util.SizeEnum;
import ravensproject.util.VerbalUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class EnlargeTransformer extends Transformer {

    private double imageRatio = 1.0;

    public EnlargeTransformer(PrimitiveTransformation transformation) {
        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {

        BufferedImage filledImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        return filledImage;
    }

    @Override
    public PrimitiveTransformation verbalTransform(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two) {
        SizeFrame transformation = null;

        //make sure nothing was added or removed
        if(sameNumberOfComponents(one, two)){
            for(String oneKey : one.keySet()){
                for(String twoKey : two.keySet()){
                    String oneSize = one.get(oneKey).getAttributes().get("size");
                    String twoSize = two.get(twoKey).getAttributes().get("size");

                    if(oneSize != null &&
                            twoSize != null  &&
                            VerbalUtil.sameFigure(one.get(oneKey).getAttributes(), two.get(twoKey).getAttributes())){

                        SizeEnum oneSizeEnum = SizeEnum.fromString(oneSize);
                        if(oneSizeEnum != null){
                            SizeEnum nextOne = oneSizeEnum.next();
                            SizeEnum nextNextOne = null;
                            if(nextOne != null){
                                nextNextOne = nextOne.next();
                            }
                            SizeEnum previousOne = oneSizeEnum.previous();
                            if((nextOne != null && nextOne.equals(SizeEnum.fromString(twoSize))) ||
                                    (nextNextOne != null && nextNextOne.equals(SizeEnum.fromString(twoSize)))){
                                transformation = new SizeFrame(true);
                                transformation.setShape(one.get(oneKey).getAttributes().get("shape"));
                            }
                            else if(previousOne != null && previousOne.equals(SizeEnum.fromString(twoSize))){
                                transformation = new SizeFrame(false);
                                transformation.setShape(one.get(oneKey).getAttributes().get("shape"));
                            }
                        }
                    }
                }
            }
        }

        return transformation;
    }

    private boolean sameNumberOfComponents(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two){
        return one.keySet().size() == two.keySet().size();
    }

    @Override
    public void initialize(BufferedImage imageOne, BufferedImage imageTwo){
        int twoArea = ImageUtil.figureArea(imageTwo);
        int oneArea = ImageUtil.figureArea(imageOne);
        if(oneArea > 0){
            imageRatio = (double) ImageUtil.figureArea(imageTwo) / ImageUtil.figureArea(imageOne);
        }
    }

}
