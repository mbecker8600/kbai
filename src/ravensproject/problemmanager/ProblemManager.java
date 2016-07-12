package ravensproject.problemmanager;

import ravensproject.RavensFigure;
import ravensproject.RavensObject;
import ravensproject.RavensProblem;
import ravensproject.transformer.TransformManager;
import ravensproject.transformer.primitives.PrimitiveTransformation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelbecker on 7/9/16.
 */
public abstract class ProblemManager {

    TransformManager manager = new TransformManager();
    RavensProblem problem;

    public ProblemManager(RavensProblem problem){
        this.problem = problem;
    }

    public abstract List<PrimitiveTransformation> getHorizontalTransformations(BufferedImage[][] frameImages);

    public abstract List<PrimitiveTransformation> getVerticalTransformations(BufferedImage[][] frameImages);

    public abstract List<PrimitiveTransformation> getDiagonalTransformations(BufferedImage[][] frameImages);

    public abstract List<PrimitiveTransformation> getHorizontalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String, RavensObject> objects);

    public abstract List<PrimitiveTransformation> getVerticalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String,RavensObject> objects);

    public abstract List<PrimitiveTransformation> getDiagonalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String,RavensObject> objects);

}
