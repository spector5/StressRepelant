package rentedbanana.stressrepelant;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

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

    // true = agreement fufills criteria, false = disagreement fufils criteria
    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("How long has the symptoms lasted?", true);
            put("How long have you felt like this?", true);
            put("Just how long have you been feeling like this?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Do the disturbances causes significant distress in social, academic, occupational, or other important areas of functioning?", true);
            put("Does this feeling cause significant distress in some social, occupational, or other area of your life?", true);
            put("Do you feel significant distress caused by these symptoms?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Are there recurrent excessive distress when anticipating separation from home or from major attachment figures?", true);
            put("Do you feel distressed when anticipating separation from home or major attachment figures?", true);
            put("Have you been feeling stressed when you anticipate being separated from home or people you care about?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Is there persistent worry about losing major attachment figures or about possible harm to them, such as illness, injury, disaster, or death?", true);
            put("Do you persistently worry about losing a major attachment figure?", true);
            put("Do you constantly worry about someone you care about experiencing pain?", true);}});
        put(4, new Hashtable<String, Boolean>(){{
            put("Is there persistent and excessive worry about experiencing an untoward event that causes separation from a major attachment figure?", true);
            put("Do you excessively worry about being separated from major attachment figures?", true);
            put("Do you worry a lot about being separated from people you care about?", true);}});
        put(5, new Hashtable<String, Boolean>(){{
            put("Is there persistent reluctance or refusal to go out, away from home, to school, to work, or elsewhere because of fear of separation?", true);
            put("Have you refused to go somewhere because of fear of separation?", true);
            put("Have you stopped going places because you are afraid of being separated from others?", true);}});
        put(6, new Hashtable<String, Boolean>(){{
            put("Is there persistent fear of or reluctance about being alone or without major attachment figures at home or in other settings?", true);
            put("Do you have a persistent fear of being alone?", true);
            put("Are you afraid of being left alone?", true);}});
        put(7, new Hashtable<String, Boolean>(){{
            put("Is there persistent reluctance or refusal to sleep away from home or to got to sleep without being near a major attachment figure?", true);
            put("Do you have problems sleeping away from home or people you care about?", true);
            put("Have you been having problems sleeping away from people you care about or away from home?", true);}});
        put(8, new Hashtable<String, Boolean>(){{
            put("Are there repeated nightmares involving the theme of separation?", true);
            put("Have you been having nightmares involving separation?", true);
            put("Are you having nightmares that revolve around the theme of separation?", true);}});
        put(9, new Hashtable<String, Boolean>(){{
            put("Are there repeated complaints of physical symptoms when separation from major attachment figures occurs or is anticipated?", true);
            put("Do you have physical symptoms, like headaches and stomachaches, when separated from attachment figures?", true);
            put("Have you had any physical symptoms, such as nausea, related to your feelings of separation?", true);}});
    }};

    // persistent and excessive worry about losing figures begins the subcriteria
    // version 1 of list, kept just in case
    /*private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("How long has the symptoms lasted?",
            "Do the disturbances causes significant distress in social, academic, occupational, or other important areas of functioning?",
            "Are there recurrent excessive distress when anticipating separation from home or from major attachment figures?",
            "Is there persistent worry about losing major attachment figures or about possible harm to them, such as illness, injury, disaster, or death?",
            "Is there persistent and excessive worry about experiencing an untoward event that causes separation from a major attachment figure?",
            "Is there persistent reluctance or refusal to go out, away from home, to school, to work, or elsewhere because of fear of separation?",
            "Is there persistent fear of or reluctance about being alone or without major attachment figures at home or in other settings?",
            "Is there persistent reluctance or refusal to sleep away from home or to got to sleep without being near a major attachment figure?",
            "Are there repeated nightmares involving the theme of separation?",
            "Are there repeated complaints of physical symptoms when separation from major attachment figures occurs or is anticipated?"));*/

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
        Set<String> set = questions.get(num).keySet();
        Random rand = new Random();
        int stop = rand.nextInt(set.size());

        String ret = "";
        int i = 0;
        for (String dat : set) {
            ret = dat;
            if (++i == stop)
                break;
        }

        return ret;
    }

    /**
     * Getter, deprecated
     * @param num = index for which question to get
     * @return question
     */
    /*public String getQuestion(int num)
    {
        return questions.get(num);
    }*/

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
     * @param quest = question you are answering
     * @param ans = user answer
     * @param num = index of question
     * @return true if the answer makes sense, flase if the app cant figure out what the answer means
     */
    public boolean sendAnswer(String quest, String ans, int num)
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
                Log.d("case1hash", questions.get(num).toString());
                Hashtable<String, Boolean> hold = questions.get(num);
                Log.d("case1quest", quest);
                //boolean yes = hold.get(quest);
                Log.d("case1bool", String.valueOf(hold.get(quest)));

                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Are there recurrent excessive distress when anticipating separation from home or from major attachment figures?
            case 2:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Is there persistent worry about losing major attachment figures or about possible harm to them, such as illness, injury, disaster, or death?
            case 3:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Is there persistent and excessive worry about experiencing an untoward event that causes separation from a major attachment figure?
            case 4:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Is there persistent reluctance or refusal to go out, away from home, to school, to work, or elsewhere because of fear of separation?
            case 5:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Is there persistent fear of or reluctance about being alone or without major attachment figures at home or in other settings?
            case 6:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Is there persistent reluctance or refusal to sleep away from home or to got to sleep without being near a major attachment figure?
            case 7:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Are there repeated nightmares involving the theme of separation?
            case 8:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Are there repeated complaints of physical symptoms when separation from major attachment figures occurs or is anticipated?
            case 9:
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        subcriteria++;
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
        }

        return false;
    }

    /**
     * Gets answer from user and decides if criteria is met, deprecated
     * @param ans = user answer
     * @param num = index of question
     * @return true if the answer makes sense, flase if the app cant figure out what the answer means
     */
    /*public boolean sendAnswer(String ans, int num)
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
    }*/
}
