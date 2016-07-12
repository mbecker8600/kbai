package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class RotateFrame extends PrimitiveTransformation{

    private int angle;
    private PrimitiveTransformationType type = PrimitiveTransformationType.ROTATE;

    public RotateFrame(int angle){
        this.angle = angle;
    }

    public int getAxis() {
        return angle;
    }

    public void setAxis(int axis) {
        this.angle = angle;
    }

    @Override
    public PrimitiveTransformationType getName() {
        return type;
    }


    @Override
    public boolean equals(PrimitiveTransformation o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RotateFrame that = (RotateFrame) o;

        if (angle != that.angle) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = angle;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
