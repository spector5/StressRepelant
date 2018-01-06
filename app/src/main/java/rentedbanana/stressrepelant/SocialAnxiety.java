package rentedbanana.stressrepelant;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 1/5/2018.
 */

public class SocialAnxiety implements Condition
{
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Is your fear or anxiety derived from social interactions?";

    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Marked fear or anxiety about one or more social situations in which the individual is exposed to possible scrutiny by others?",
            "Are the individual fears that he or she will act in a way or show anxiety symptoms that will be negatively evaluated?",
            "Do social situations almost always provoke fear or anxiety?",
            "Are social situations avoided or endured with intense fear or anxiety?",
            "Is the fear or anxiety out of proportion to the actual threat posed by the social situation and to the sociocultural context?",
            "If another medical condition is present, the fear, anxiety, or avoidance is clearly unrelated or is excessive?",
            "Is the fear, anxiety, or avoidance is not better explained by the symptoms of another mental disorder, such as panic disorder, body dysmorphic disorder, or autism spectrum disorder?",
            "Is the fear, anxiety, or avoidance not attributable to the physiological effects of a substance?",
            "Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?",
            "Does the fear, anxiety, or avoidance is persistent, typically lasting for 6 months or more?"));

    /**
     * Constructor, makes everything zero
     */
    public SocialAnxiety()
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
        if (criteria >= 10)
            return "I believe you have Social Anxiety.";
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
            // Marked fear or anxiety about one or more social situations in which the individual is exposed to possible scrutiny by others?
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
            // Are the individual fears that he or she will act in a way or show anxiety symptoms that will be negatively evaluated?
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
            // Do social situations almost always provoke fear or anxiety?
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
                // Are social situations avoided or endured with intense fear or anxiety?
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
            // Is the fear or anxiety out of proportion to the actual threat posed by the social situation and to the sociocultural context?
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
            // If another medical condition is present, the fear, anxiety, or avoidance is clearly unrelated or is excessive?
            case 5:
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
            // Is the fear, anxiety, or avoidance is not better explained by the symptoms of another mental disorder, such as panic disorder, body dysmorphic disorder, or autism spectrum disorder?
            case 6:
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
            // Is the fear, anxiety, or avoidance not attributable to the physiological effects of a substance?
            case 7:
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
            // Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?
            case 8:
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
            // Does the fear, anxiety, or avoidance is persistent, typically lasting for 6 months or more?
            case 9:
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
