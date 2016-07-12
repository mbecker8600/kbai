package ravensproject.transformer;

import ravensproject.RavensObject;
import ravensproject.transformer.primitives.AddFrame;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.util.ImageUtil;
import ravensproject.util.VerbalUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class RemoveTransformer extends Transformer {

    private static final int white = 0xFFFFFFFF;
    private static final int black = 0xFF000000;

    private BufferedImage removeImage;

    public RemoveTransformer(PrimitiveTransformation transformation) {

        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {

        BufferedImage finalImage = ImageUtil.deepCopy(image);
        if(removeImage != null){
            for(int x=0; x<image.getWidth(); x++){
                for(int y=0; y<image.getHeight(); y++){
                    if(x <= removeImage.getWidth() &&
                            y<= removeImage.getHeight() &&
                            removeImage.getRGB(x, y) == black){
                        finalImage.setRGB(x, y, white);
                    }
                    else finalImage.setRGB(x, y, image.getRGB(x, y));
                }
            }
        }

        return finalImage;
    }

    @Override
    public PrimitiveTransformation verbalTransform(HashMap<String, RavensObject> one, HashMap<String, RavensObject> two) {
        AddFrame add = null;

        List<RavensObject> differences = VerbalUtil.differences(one, two);
        if(differences.size() == 1 && one.keySet().size() - 1 == two.keySet().size()){
            add = new AddFrame(false);
        }

        return add;
    }

    @Override
    public void initialize(BufferedImage imageOne, BufferedImage imageTwo){
        removeImage = ImageUtil.differences(imageOne, imageTwo);
        if(ImageUtil.figureArea(removeImage) > 1500) removeImage = null;
        AddFrame transformation = (AddFrame) this.getTransformation();
        transformation.setImage(removeImage);
    }

}
