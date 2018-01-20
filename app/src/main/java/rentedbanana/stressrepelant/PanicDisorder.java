package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 1/20/2018.
 */

public class PanicDisorder implements Condition
{
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Do you often have fear what seems to be randomly and reoccuring?";

    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Do you have recurrent unexpected panic attacks?",
            "Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?",
            "Are the disturbances not attributable to the physiological effects of a substance or another medical condition?",
            "Are the disturbances not better explained by another mental disorder?"));

    /**
     * Constructor, makes everything zero
     */
    public PanicDisorder()
    {
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
        if (criteria >= 4)
            return "I believe you have Panic Disorder.";
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
            // Do you have recurrent unexpected panic attacks?
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
            // Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?
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
            // Are the disturbances not attributable to the physiological effects of a substance or another medical condition?
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
            // Are the disturbances not better explained by another mental disorder?
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
        }
        return false;
    }
}
