package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 7/2/16.
 */
public class TransformFrame extends PrimitiveTransformation{

    public PrimitiveTransformationType getName(){
        return PrimitiveTransformationType.TRANSFORM;
    }

    public boolean equals(PrimitiveTransformation transformation){
        return transformation.getName().equals(this.getName());
    }
}
