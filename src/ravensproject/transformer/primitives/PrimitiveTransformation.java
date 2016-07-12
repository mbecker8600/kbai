package ravensproject.transformer.primitives;

/**
 * Created by michaelbecker on 6/24/16.
 */
public abstract class PrimitiveTransformation implements Comparable<PrimitiveTransformation>{

    private double confidence;

    public abstract PrimitiveTransformationType getName();

    public abstract boolean equals(PrimitiveTransformation transformation);

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public int compareTo(final PrimitiveTransformation o) {
        return Double.compare(confidence, o.getConfidence());
    }
}
