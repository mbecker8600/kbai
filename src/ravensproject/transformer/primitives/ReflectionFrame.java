package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 6/25/16.
 */
public class ReflectionFrame extends PrimitiveTransformation {

    private String axis;
    private PrimitiveTransformationType type = PrimitiveTransformationType.REFELCTION;

    public ReflectionFrame(String axis){
        this.axis = axis;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    @Override
    public PrimitiveTransformationType getName() {
        return type;
    }

    @Override
    public boolean equals(PrimitiveTransformation o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectionFrame that = (ReflectionFrame) o;

        if (axis != null ? !axis.equals(that.axis) : that.axis != null) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = axis != null ? axis.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
