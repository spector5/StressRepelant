package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 12/21/2017.
 */

public class SeparationAnxiety implements Condition
{
    private int duration;
    private int criteria;
    private int subcriteria;

    private static final String starter = "Does it refer to separation from those that you are attached to?";

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

    public SeparationAnxiety()
    {
        this.duration = 0;
        this.criteria = 0;
        this.subcriteria = 0;
    }

    public static String getStarter()
    {
        return starter;
    }

    public String getQuestion(int num)
    {
        return questions.get(num);
    }

    public String makeResponse()
    {
        if (subcriteria >= 3)
            criteria++;

        if (criteria >= 2)
            return "I believe you have Separation Anxiety.";
        else
            return "You seem to be fine.";
    }

    public boolean sendAnswer(String ans, int num)
    {
        int countPos;
        int countNeg;

        switch(num)
        {
            case 0:
                if ((duration = Dictionary.countDays(ans)) > 0) {
                    // TODO must communicate with server to get age, for now assuming adult user
                    if (duration < 180)
                        criteria++;
                    return true;
                }
                break;
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
