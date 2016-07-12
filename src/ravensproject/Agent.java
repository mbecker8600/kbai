package ravensproject;

// Uncomment these lines to access image processing.
//import java.awt.Image;
//import java.io.File;
//import javax.imageio.ImageIO;

import ravensproject.problemmanager.ProblemManager;
import ravensproject.problemmanager.ProblemManagerFactory;
import ravensproject.transformer.primitives.PrimitiveTransformation;
import ravensproject.transformer.TransformManager;
import ravensproject.util.ImageUtil;
import ravensproject.util.ProblemAnswer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 * 
 * You may also create and submit new files in addition to modifying this file.
 * 
 * Make sure your file retains methods with the signatures:
 * public Agent()
 * public char Solve(RavensProblem problem)
 * 
 * These methods will be necessary for the project's main method to run.
 * 
 */
public class Agent {
    /**
     * The default constructor for your Agent. Make sure to execute any
     * processing necessary before your Agent starts solving problems here.
     * 
     * Do not add any variables to this signature; they will not be used by
     * main().
     * 
     */
    public Agent() {
        
    }
    /**
     * The primary method for solving incoming Raven's Progressive Matrices.
     * For each problem, your Agent's Solve() method will be called. At the
     * conclusion of Solve(), your Agent should return an int representing its
     * answer to the question: 1, 2, 3, 4, 5, or 6. Strings of these ints 
     * are also the Names of the individual RavensFigures, obtained through
     * RavensFigure.getName(). Return a negative number to skip a problem.
     * 
     * Make sure to return your answer *as an integer* at the end of Solve().
     * Returning your answer as a string may cause your program to crash.
     * @param problem the RavensProblem your agent should solve
     * @return your Agent's answer to this problem
     */
    public int Solve(RavensProblem problem) {
        int answer = -1;
        TransformManager manager = new TransformManager();
        ProblemManager problemManager = ProblemManagerFactory.getInstance().getProblemManager(problem);

        //retrieve images for all frames - will be a 2x2 matrix or a 3x3 matrix depending on the problem
        BufferedImage[][] frameImages = ImageUtil.getFrameImages(problem);

        List<PrimitiveTransformation> horizontalTransformations = problemManager.getHorizontalTransformations(frameImages);
        List<PrimitiveTransformation> verticalTransformations = problemManager.getVerticalTransformations(frameImages);
        List<PrimitiveTransformation> diagonalTransformations = problemManager.getDiagonalTransformations(frameImages);

        //get list of answers
        List<BufferedImage> answerImages = ImageUtil.getAnswerImages(problem);

        List<ProblemAnswer> potentialAnswers = new ArrayList<ProblemAnswer>();

        for(int i=0; i<answerImages.size(); i++){
            List<PrimitiveTransformation> answerHorizontalTransformations = new ArrayList<PrimitiveTransformation>();
            List<PrimitiveTransformation> answerVerticalTransformations = new ArrayList<PrimitiveTransformation>();
            List<PrimitiveTransformation> answerDiagonalTransformations = new ArrayList<PrimitiveTransformation>();

            answerHorizontalTransformations =
                    problemManager.getHorizontalAnswerTransformations(frameImages, answerImages.get(i), problem.getFigures().get(Integer.toString(i+1)).getObjects());

            answerVerticalTransformations =
                    problemManager.getVerticalAnswerTransformations(frameImages, answerImages.get(i), problem.getFigures().get(Integer.toString(i+1)).getObjects());

            answerDiagonalTransformations =
                    problemManager.getDiagonalAnswerTransformations(frameImages, answerImages.get(i), problem.getFigures().get(Integer.toString(i+1)).getObjects());


            for(PrimitiveTransformation horizontalTranformation : horizontalTransformations){
                for(PrimitiveTransformation verticalTransformation : verticalTransformations){
                    if(containsInList(horizontalTranformation, answerHorizontalTransformations) &&
                            containsInList(verticalTransformation, answerVerticalTransformations)){

                        potentialAnswers.add(new ProblemAnswer(i+1, horizontalTranformation.getConfidence() + verticalTransformation.getConfidence()));
                    }

                }
            }

            for(PrimitiveTransformation diagonalTransformation : diagonalTransformations){
                if(containsInList(diagonalTransformation, answerDiagonalTransformations)){
                    potentialAnswers.add(new ProblemAnswer(i+1, diagonalTransformation.getConfidence()));
                }
            }


        }


        Collections.sort(potentialAnswers);
        if(!potentialAnswers.isEmpty()){
            return potentialAnswers.get(0).getAnswer();
        }
        else return -1;
    }

    private boolean containsInList(PrimitiveTransformation transformation, List<PrimitiveTransformation> transformationList) {
        for(PrimitiveTransformation pt : transformationList){
            if(transformation.equals(pt)) return true;
        }
        return false;
    }

}
