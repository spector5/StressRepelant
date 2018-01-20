package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 1/20/2018.
 */

public class Agoraphobia implements Condition
{
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Do you have intense fear or anxiety triggered by the real or anticipated exposure to a wide range of situations?";

    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Marked fear or anxiety about two (or more) of the following five situations: standing in line or being in a crowd, being in enclosed places, being in open spaces, using public transportation, being outside of the home alone?",
            "Do you avoid these situations because of thoughts that escape might be difficult or help might not be available in the event of developing panic-like symptoms or other incapacitating or embarrassing symptoms?",
            "Does the agoraphobic situation almost always provoke fear or anxiety?",
            "Are the agoraphobic situations actively avoided, require the presence of a companion, or are endured with intense fear or anxiety?",
            "Is the fear or anxiety out of proportion to the actual danger posed by the agoraphobic situations and to the sociocultural context?",
            "Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?",
            "Does the fear, anxiety, or avoidance cause clinically significant distress or impairment in social, occupational, or other important areas of functioning?",
            "If another medical condition is present, the fear anxiety or avoidance is clearly excessive?",
            "Is the fear, anxiety, or avoidance not better explained by the symptoms of another mental disorder?"));

    /**
     * Constructor, makes everything zero
     */
    public Agoraphobia()
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
        if (criteria >= 9)
            return "I believe you have Agoraphobia.";
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
            // Marked fear or anxiety about two (or more) of the following five situations: standing in line or being in a crowd, being in enclosed places, being in open spaces, using public transportation, being outside of the home alone?
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
            // Do you avoid these situations because of thoughts that escape might be difficult or help might not be available in the event of developing panic-like symptoms or other incapacitating or embarrassing symptoms?
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
            // Does the agoraphobic situation almost always provoke fear or anxiety?
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
            // Are the agoraphobic situations actively avoided, require the presence of a companion, or are endured with intense fear or anxiety?
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
            // Is the fear or anxiety out of proportion to the actual danger posed by the agoraphobic situations and to the sociocultural context?
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
            // Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?
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
            // Does the fear, anxiety, or avoidance cause clinically significant distress or impairment in social, occupational, or other important areas of functioning?
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
            // If another medical condition is present, the fear anxiety or avoidance is clearly excessive?
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
            // Is the fear, anxiety, or avoidance not better explained by the symptoms of another mental disorder?
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
        }
        return false;
    }
}
