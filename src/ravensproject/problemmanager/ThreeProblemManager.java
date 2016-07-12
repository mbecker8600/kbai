package ravensproject.problemmanager;

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
public class ThreeProblemManager extends ProblemManager {

    public ThreeProblemManager(RavensProblem problem) {
        super(problem);
    }

    @Override
    public List<PrimitiveTransformation> getHorizontalTransformations(BufferedImage[][] frameImages) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[0][2],
                problem.getFigures().get("G").getObjects(),
                frameImages[1][2],
                problem.getFigures().get("H").getObjects());
        Collections.sort(transformations);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getVerticalTransformations(BufferedImage[][] frameImages) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[2][0],
                problem.getFigures().get("C").getObjects(),
                frameImages[2][1],
                problem.getFigures().get("F").getObjects());
        Collections.sort(transformations);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getDiagonalTransformations(BufferedImage[][] frameImages) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[0][0],
                problem.getFigures().get("A").getObjects(),
                frameImages[1][1],
                problem.getFigures().get("E").getObjects());
        Collections.sort(transformations);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getHorizontalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String,RavensObject> objects) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[1][2],
                problem.getFigures().get("H").getObjects(),
                answerImage,
                objects);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getVerticalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String,RavensObject> objects) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[2][1],
                problem.getFigures().get("F").getObjects(),
                answerImage,
                objects);

        return transformations;
    }

    @Override
    public List<PrimitiveTransformation> getDiagonalAnswerTransformations(BufferedImage[][] frameImages, BufferedImage answerImage, HashMap<String, RavensObject> objects) {
        List<PrimitiveTransformation> transformations = new ArrayList<PrimitiveTransformation>();

        transformations = manager.determineTransformations(frameImages[1][1],
                problem.getFigures().get("E").getObjects(),
                answerImage,
                objects);

        return transformations;
    }
}
