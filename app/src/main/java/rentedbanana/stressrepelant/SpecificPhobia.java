package rentedbanana.stressrepelant;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

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

    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("Do you have specific fear or anxiety about a specific object or situation?", true);
            put("Is there a specific object or situation which causes you fear or anxiety?", true);
            put("Is there a specific situation or object that you are afraid of?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Specify if you can.", true);
            put("What sort of object or situation would cause this?", true);
            put("What is it?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Does the phobic object or situation almost always provoke immediate fear or anxiety?", true);
            put("Does it almost always cause you to be afraid or anxious?", true);
            put("Does this object or situation always make you feel afraid?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Do you avoid the phobic object or situation?", true);
            put("Do you tend to avoid problem object or situation?", true);
            put("Do you try to avoid this when possible?", true);}});
        put(4, new Hashtable<String, Boolean>(){{
            put("Is the fear or anxiety out of proportion to the actual or accepted danger posed by the specific object or situation?", true);
            put("Do you think your fear or anxiety is out of proportion to the actual or accepted danger of the object or situation?", true);
            put("Does your fear exceed the actual danger posed by the object or situation?", true);}});
        put(5, new Hashtable<String, Boolean>(){{
            put("Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?", true);
            put("Does the fear or anxiety typically persist, lasting for 6 months or more?", false);
            put("Have you been afraid and avoidant of this for more than 6 months?", false);}});
        put(6, new Hashtable<String, Boolean>(){{
            put("Does the fear, anxiety, or avoidance cause significant distress or impairment in social, occupational, or other important areas of functioning?", true);
            put("Does your fear or anxiety cause you significant distress or difficulty in important areas such as social or occupational settings?", true);
            put("Do you feel significant distress in some area of your life based on this fear?", true);}});
    }};

    /*private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Marked fear or anxiety about a specific object or situation?",
            "Specify if you can.", "Does the phobic object or situation almost always provokes immediate fear or anxiety?",
            "Do you avoid the phobic object or situation?",
            "Is the fear or anxiety out of proportion to the actual danger posed by the specific object or situation and to the sociocultural context?",
            "Does the fear, anxiety, or avoidance is persistent, typically last for 6 months or more?",
            "Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?"));*/

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
    public boolean sendAnswer(String quest, String ans, int num, Activity act)
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
                Dictionary.filterText(ans, act);
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
            // Specify if you can.
            // TODO this question may need to be switched, possibly throw an exception just to make something quick
            case 1:
                Dictionary.filterText(ans, act);
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
                Dictionary.filterText(ans, act);
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
            // Do you avoid the phobic object or situation?
            case 3:
                Dictionary.filterText(ans, act);
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
            // Is the fear or anxiety out of proportion to the actual danger posed by the specific object or situation and to the sociocultural context?
            case 4:
                Dictionary.filterText(ans, act);
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
            //Does the fear, anxiety, or avoidance is persistent, typically last for 6 months or more?
            case 5:
                Dictionary.filterText(ans, act);
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
            // Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?
            case 6:
                Dictionary.filterText(ans, act);
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

    @Override
    public int getQuestionLength() {
        return questions.size();
    }
}
