package ravensproject.problemmanager;

import ravensproject.RavensProblem;
import ravensproject.util.ProblemAnswer;

/**
 * Created by michaelbecker on 7/9/16.
 */
public class ProblemManagerFactory {

    private static ProblemManagerFactory instance = null;

    private ProblemManagerFactory(){
        //private constructor
    }

    public static ProblemManagerFactory getInstance(){
        if(instance == null) instance = new ProblemManagerFactory();
        return instance;
    }

    public ProblemManager getProblemManager(RavensProblem problem){
        ProblemManager manager = null;

        if(problem.getProblemType().equals("2x2")) manager = new TwoProblemManager(problem);
        else if(problem.getProblemType().equals("3x3")) manager = new ThreeProblemManager(problem);

        return manager;
    }
}
