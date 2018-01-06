package rentedbanana.stressrepelant;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 1/5/2018.
 */

public class SpecificPhobia implements Condition
{
    private boolean shouldElaborate;
    private String fear;
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Is there something specific you are scared about?";

    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Marked fear or anxiety about a specific object or situation?",
            "Specify if you can.", "Does the phobic object or situation almost always provokes immediate fear or anxiety?",
            "Do you avoid the phobic object or situation?",
            "Is the fear or anxiety out of proportion to the actual danger posed by the specific object or situation and to the sociocultural context?",
            "Does the fear, anxiety, or avoidance is persistent, typically last for 6 months or more?",
            "Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?"));

    /**
     * Constructor, makes everything zero
     */
    public SpecificPhobia()
    {
        this.shouldElaborate = false;
        this.criteria = 0;
        this.fear = "";
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
        if (criteria >= 7)
            return "I believe you have a Specific Phobia of " + fear + ".";
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

        //if (num > 0 && shouldElaborate)
        //    num++;

        // one case of each question, not including starter
        switch(num)
        {
            // Marked fear or anxiety about a specific object or situation?
            case 0:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    Log.d("phobia", "checked1");
                    criteria++;
                    shouldElaborate = true;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Specify if you can.
            // TODO this question may need to be switched, possibly throw an exception just to make something quick
            case 1:
                fear = ans;
                countNeg = Dictionary.countNegative(ans);
                Log.d("phob", String.valueOf(countNeg));
                if (countNeg == 0) {
                    Log.d("phobia", "checked2");
                    criteria++;
                }
                Log.d("phob", "done");
                return true;
            // Does the phobic object or situation almost always provokes immediate fear or anxiety?
            case 2:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    Log.d("phobia", "checked3");
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Do you avoid the phobic object or situation?
            case 3:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    Log.d("phobia", "checked4");
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is the fear or anxiety out of proportion to the actual danger posed by the specific object or situation and to the sociocultural context?
            case 4:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    Log.d("phobia", "checked5");
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            //Does the fear, anxiety, or avoidance is persistent, typically last for 6 months or more?
            case 5:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    Log.d("phobia", "checked6");
                    criteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?
            case 6:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    Log.d("phobia", "checked7");
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
