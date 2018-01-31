package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

/**
 * Created by Austin on 1/20/2018.
 */

public class PanicDisorder implements Condition
{
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Do you often have fear what seems to be randomly and reoccuring?";

    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("Do you have recurrent, unexpected panic attacks?", true);
            put("Have you had any unexpected panic attacks that are recurring?", true);
            put("Have you been having unexpected, recurrent panic attacks?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?", true);
            put("Have you recently had an attack followed by 1 month or more of one or both of these: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Are the disturbances not attributable to the physiological effects of a substance or another medical condition?", false);
            put("Are the disturbances caused by the effects of a substance or other medical condition?", true);
            put("Do you have another medical condition or use a substance that may be causing these disturbances?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Are the disturbances not better explained by another mental disorder?", true);
            put("Do you have a different mental disorder that may be causing these disturbances?", true);
            put("Is there a different mental disorder that you have been diagnosed with that many be causing these disturbances?", true);}});
    }};

    /*private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Do you have recurrent unexpected panic attacks?",
            "Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?",
            "Are the disturbances not attributable to the physiological effects of a substance or another medical condition?",
            "Are the disturbances not better explained by another mental disorder?"));*/

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
    public boolean sendAnswer(String quest, String ans, int num)
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
            // Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?
            case 1:
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
            // Are the disturbances not attributable to the physiological effects of a substance or another medical condition?
            case 2:
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
            // Are the disturbances not better explained by another mental disorder?
            case 3:
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
        }
        return false;
    }
}
