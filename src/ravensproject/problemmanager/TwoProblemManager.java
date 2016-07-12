package ravensproject.problemmanager;

import ravensproject.RavensFigure;
import ravensproject.RavensObject;
import ravensproject.RavensProblem;
import ravensproject.transformer.primitives.PrimitiveTransformation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelbecker on 7/9/16.
 */
public class TwoProblemManager extends ProblemManager {

    public TwoProblemManager(RavensProblem problem) {
        super(problem);
    }

    @Override
    public List<PrimitiveTransformation> getHorizontalTransformations(BufferedImage[][]frameImages) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[0][0],
                problem.getFigures().get("A").getObjects(),
                frameImages[1][0],
                problem.getFigures().get("B").getObjects());
        Collections.sort(transformations);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getVerticalTransformations(BufferedImage[][] frameImages) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[0][0],
                problem.getFigures().get("A").getObjects(),
                frameImages[0][1],
                problem.getFigures().get("C").getObjects());
        Collections.sort(transformations);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getDiagonalTransformations(BufferedImage[][] frameImages) {
        return new ArrayList<PrimitiveTransformation>();
    }

    @Override
    public List<PrimitiveTransformation> getHorizontalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String,RavensObject> objects) {
        return manager.determineTransformations(frameImages[0][1],
                problem.getFigures().get("C").getObjects(),
                answerImage,
                objects);
    }

    @Override
    public List<PrimitiveTransformation> getVerticalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String,RavensObject> objects) {
        return manager.determineTransformations(frameImages[1][0],
                problem.getFigures().get("B").getObjects(),
                answerImage,
                objects);
    }

    @Override
    public List<PrimitiveTransformation> getDiagonalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String, RavensObject> objects) {
        return new ArrayList<PrimitiveTransformation>();
    }
}
