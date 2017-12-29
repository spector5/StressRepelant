package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class specific for Separation Anxiety
 * Created by Austin on 12/21/2017.
 */

public class SeparationAnxiety implements Condition
{
    private int duration;
    private int criteria;   // how many criteria are met
    private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Does it refer to separation from those that you are attached to?";

    // persistent and excessive worry about losing figures begins the subcriteria
    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("How long has the symptoms lasted?",
            "Do the disturbances causes significant distress in social, academic, occupational, or other important areas of functioning?",
            "Are there recurrent excessive distress when anticipating separation from home or from major attachment figures?",
            "Is there persistent worry about losing major attachment figures or about possible harm to them, such as illness, injury, disaster, or death?",
            "Is there persistent and excessive worry about experiencing an untoward event that causes separation from a major attachment figure?",
            "Is there persistent reluctance or refusal to go out, away from home, to school, to work, or elsewhere because of fear of separation?",
            "Is there persistent fear of or reluctance about being alone or without major attachment figures at home or in other settings?",
            "Is there persistent reluctance or refusal to sleep away from home or to got to sleep without being near a major attachment figure?",
            "Are there repeated nightmares involving the theme of separation?",
            "Are there repeated complaints of physical symptoms when separation from major attachment figures occurs or is anticipated?"));

    /**
     * Constructor, makes everything zero
     */
    public SeparationAnxiety()
    {
        this.duration = 0;
        this.criteria = 0;
        this.subcriteria = 0;
    }

    /**
     * Getter
     * @return started
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
        if (subcriteria >= 3)
            criteria++;

        if (criteria >= 2)
            return "I believe you have Separation Anxiety.";
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
            // How long has the symptoms lasted?
            case 0:
                if ((duration = Dictionary.countDays(ans)) > 0) {
                    // TODO must communicate with server to get age, for now assuming adult user
                    if (duration < 180)
                        criteria++;
                    return true;
                }
                break;
            // Do the disturbances causes significant distress in social, academic, occupational, or other important areas of functioning?
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
            // Are there recurrent excessive distress when anticipating separation from home or from major attachment figures?
            case 2:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there persistent worry about losing major attachment figures or about possible harm to them, such as illness, injury, disaster, or death?
            case 3:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there persistent and excessive worry about experiencing an untoward event that causes separation from a major attachment figure?
            case 4:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there persistent reluctance or refusal to go out, away from home, to school, to work, or elsewhere because of fear of separation?
            case 5:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there persistent fear of or reluctance about being alone or without major attachment figures at home or in other settings?
            case 6:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Is there persistent reluctance or refusal to sleep away from home or to got to sleep without being near a major attachment figure?
            case 7:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Are there repeated nightmares involving the theme of separation?
            case 8:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
                    return true;
                }
                else if (countNeg == 0 && countPos == 0)
                    return false;
                else
                    return true;
            // Are there repeated complaints of physical symptoms when separation from major attachment figures occurs or is anticipated?
            case 9:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                if (countPos > countNeg)
                {
                    subcriteria++;
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
