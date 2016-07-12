package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 6/24/16.
 */
public class IdentityFrame extends PrimitiveTransformation{

    public PrimitiveTransformationType getName(){
        return PrimitiveTransformationType.IDENTITY;
    }

    public boolean equals(PrimitiveTransformation transformation){
        return transformation.getName().equals(this.getName());
    }
}
