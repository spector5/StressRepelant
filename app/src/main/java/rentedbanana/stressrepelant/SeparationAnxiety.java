package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Austin on 12/21/2017.
 */

public class SeparationAnxiety implements Condition
{
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

    public static String getStarter()
    {
        return starter;
    }

    public String getQuestion(int num)
    {
        return questions.get(num);
    }
}
