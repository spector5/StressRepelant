package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 1/5/2018.
 */

public class PotentialSelectiveMutism implements Condition
{
    //private int duration;
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Do you have problems initiating conversation in specific situations or to specific people?";

    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Have the duration of the disturbance lasted at least 1 month?",
            "Do the disturbance interfere with educational or occupational achievement or with social communication?",
            "Is there consistant failure to speak in specific social situations in which there is an expectation for speaking despite speaking in other situations?",
            "Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?",
            "Are the disturbances not better explained by a different cause?"));

    /**
     * Constructor, makes everything zero
     */
    public PotentialSelectiveMutism()
    {
        //this.duration = 0;
        this.criteria = 0;
        //this.subcriteria = 0;
    }

    /**
     * Getter
     * @return starter
     */
    public static String getStarter()
    {
        return starter;
    }

    /**
     * Getter
     * @param num = index for which question to get
     * @return question
     */
    public String getQuestion(int num)
    {
        return questions.get(num);
    }

    /**
     * Where the computer will make a desicion and come up with advice
     * @return response and advice
     */
    public String makeResponse()
    {
        // TODO havent filled this out yet, just giving a basic response
        if (criteria >= 5)
            return "I believe you have Selective Mutism.";
        else
            return "You seem to be fine.";
    }

    /**
     * Gets answer from user and decides if criteria is met
     * @param ans = user answer
     * @param num = index of question
     * @return true if the answer makes sense, flase if the app cant figure out what the answer means
     */
    public boolean sendAnswer(String ans, int num)
    {
        int countPos;
        int countNeg;

        // one case of each question, not including starter
        switch(num)
        {
            // Have the duration of the disturbance lasted at least 1 month?
            case 0:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Do the disturbance interfere with educational or occupational achievement or with social communication?
            case 1:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there consistant failure to speak in specific social situations in which there is an expectation for speaking despite speaking in other situations?
            case 2:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?
            case 3:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Are the disturbances not better explained by a different cause?
            case 4:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
        }

        return false;
    }
}
