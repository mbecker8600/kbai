package ravensproject.transformer.primitives;

import ravensproject.util.ImageUtil;

import java.awt.image.BufferedImage;

/**
 * Created by michaelbecker on 6/28/16.
 */
public class AddFrame extends PrimitiveTransformation {

    private boolean add;
    private PrimitiveTransformationType type = PrimitiveTransformationType.ADD;
    private BufferedImage image = null;

    public AddFrame(boolean add){
        this.add = add;
    }

    public PrimitiveTransformationType getName(){

        return type;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(PrimitiveTransformation o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddFrame addFrame = (AddFrame) o;

        if (add != addFrame.add) return false;
        return (type == addFrame.type);
//        return image != null ? ImageUtil.imagesEqual(image, addFrame.image) : addFrame.image == null;

    }

    @Override
    public int hashCode() {
        int result = (add ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
