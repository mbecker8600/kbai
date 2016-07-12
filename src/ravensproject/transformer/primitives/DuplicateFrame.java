package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 7/3/16.
 */
public class DuplicateFrame extends PrimitiveTransformation{

    private PrimitiveTransformationType type = PrimitiveTransformationType.DUPLICATE;
    private int numberOfDuplicates;
    private String shapesize;

    public PrimitiveTransformationType getName(){
        return type;
    }

    public void setType(PrimitiveTransformationType type) {
        this.type = type;
    }

    public int getNumberOfDuplicates() {
        return numberOfDuplicates;
    }

    public void setNumberOfDuplicates(int numberOfDuplicates) {
        this.numberOfDuplicates = numberOfDuplicates;
    }

    public String getShapesize() {
        return shapesize;
    }

    public void setShapesize(String shapesize) {
        this.shapesize = shapesize;
    }

    @Override
    public boolean equals(PrimitiveTransformation o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DuplicateFrame that = (DuplicateFrame) o;

        if (numberOfDuplicates != that.numberOfDuplicates) return false;
        if (shapesize != null && !shapesize.equals(that.getShapesize())) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + numberOfDuplicates;
        return result;
    }
}
