package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 6/28/16.
 */
public class SizeFrame extends PrimitiveTransformation{

    private static final int ERROR_THRESHOLD = 100;

    private boolean enlarged;
    private double ratio;
    private String shape;
    private PrimitiveTransformationType type = PrimitiveTransformationType.SIZE;

    public SizeFrame(boolean enlarged){
        this.enlarged = enlarged;
    }

    public PrimitiveTransformationType getName(){

        return type;
    }

    public boolean isEnlarged() {
        return enlarged;
    }

    public void setEnlarged(boolean enlarged) {
        this.enlarged = enlarged;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public boolean equals(PrimitiveTransformation o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SizeFrame sizeFrame = (SizeFrame) o;

        if (enlarged != sizeFrame.enlarged) return false;
//        if (!compareRatios(ratio, sizeFrame.ratio)) return false;
        if(!shape.equals(sizeFrame.shape)) return false;
        return (type == sizeFrame.type);
//        if (type != sizeFrame.type) return false;
//        return ratio == sizeFrame.ratio;

    }

    @Override
    public int hashCode() {
        int result = (enlarged ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    private boolean compareRatios(double one, double two){
        boolean equal = false;
//        if(!(two > one + ERROR_THRESHOLD && two < one - ERROR_THRESHOLD)){
            equal = true;
            setConfidence(1.0 - (Math.max(one, two) - Math.min(one, two)));
//        }
        return equal;
    }
}
