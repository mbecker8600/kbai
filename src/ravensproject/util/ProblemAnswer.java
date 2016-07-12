package ravensproject.util;

/**
 * Created by michaelbecker on 7/2/16.
 */
public class ProblemAnswer implements Comparable<ProblemAnswer> {

    double overallConfidence = 0;
    int answer = -1;

    public ProblemAnswer(int answer, double overallConfidence) {
        this.overallConfidence = overallConfidence;
        this.answer = answer;
    }

    public double getOverallConfidence() {
        return overallConfidence;
    }

    public void setOverallConfidence(double overallConfidence) {
        this.overallConfidence = overallConfidence;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public int compareTo(ProblemAnswer o) {
        return Double.compare(overallConfidence, o.getOverallConfidence());
    }
}
