package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class FillFrame extends PrimitiveTransformation {

    private boolean filled;
    private PrimitiveTransformationType type = PrimitiveTransformationType.FILL;

    public FillFrame(boolean filled){
        this.filled = filled;
    }

    public PrimitiveTransformationType getName(){

        return type;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public boolean equals(PrimitiveTransformation o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FillFrame fillFrame = (FillFrame) o;

        if (filled != fillFrame.filled) return false;
        return type == fillFrame.type;

    }

    @Override
    public int hashCode() {
        int result = (filled ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
