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
public class AddTransformer extends Transformer {

    private static final int black = 0xFF000000;

    private BufferedImage addImage;

    public AddTransformer(PrimitiveTransformation transformation) {

        super(transformation);
    }

    @Override
    public BufferedImage visualTransform(BufferedImage image) {

        BufferedImage finalImage = ImageUtil.deepCopy(image);
        if(addImage != null){
            for(int x=0; x<image.getWidth(); x++){
                for(int y=0; y<image.getHeight(); y++){
                    if(x <= addImage.getWidth() &&
                            y<= addImage.getHeight() &&
                            addImage.getRGB(x, y) == black){
                        finalImage.setRGB(x, y, black);
                    }
                }
            }
        }

        return finalImage;
    }

    @Override
    public PrimitiveTransformation verbalTransform(HashMap<String,RavensObject> one, HashMap<String,RavensObject> two) {

        AddFrame add = null;

        List<RavensObject> differences = VerbalUtil.differences(one, two);
        if(differences.size() == 1 && one.keySet().size() + 1 == two.keySet().size()){
            add = new AddFrame(true);
        }

        return add;
    }

    @Override
    public void initialize(BufferedImage imageOne, BufferedImage imageTwo){
        addImage = ImageUtil.differences(imageOne, imageTwo);
        if(ImageUtil.figureArea(addImage) > 1500) addImage = null;
        AddFrame transformation = (AddFrame) this.getTransformation();
        transformation.setImage(addImage);
    }

}
