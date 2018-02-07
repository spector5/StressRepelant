package rentedbanana.stressrepelant;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

/**
 * Created by Austin on 1/5/2018.
 */

public class PotentialSelectiveMutism implements Condition
{
    //private int duration;
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Do you have problems initiating conversation in specific situations or to specific people?";

    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("Have the duration of the disturbance lasted at least 1 month?", true);
            put("Have you been having this issue for more than 1 month?", true);
            put("Has this disturbance lasted for more than 1 month?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Do the disturbance interfere with educational or occupational achievement or with social communication?", true);
            put("Have you had issues at school, work, or in social situations related to these disturbances?", true);
            put("Do these disturbances interfere with your ability to communicate socially?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Is there consistent failure to speak in specific social situations in which there is an expectation for speaking despite speaking in other situations?", false);
            put("Do you consistently fail to speak in specific social situations you are expected to speak at?", true);
            put("Have you been unable to speak at times you were expected to speak?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?", true);
            put("Is your failure to speak caused by a lack of knowledge of a spoken language?", false);
            put("Do you have trouble speaking because you are unfamiliar with a spoken language?", false);}});
        put(4, new Hashtable<String, Boolean>(){{
            put("Are the disturbances not better explained by a different cause?", true);
            put("Do you have a different medical or mental condition that explains your inability to speak?", false);}});
    }};

    /*private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Have the duration of the disturbance lasted at least 1 month?",
            "Do the disturbance interfere with educational or occupational achievement or with social communication?",
            "Is there consistant failure to speak in specific social situations in which there is an expectation for speaking despite speaking in other situations?",
            "Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?",
            "Are the disturbances not better explained by a different cause?"));*/

    /**
     * Constructor, makes everything zero
     */
    public PotentialSelectiveMutism()
    {
        //this.duration = 0;
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
        if (criteria >= 5)
            return "I believe you have Selective Mutism.";
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

        // one case of each question, not including starter
        switch(num)
        {
            // Have the duration of the disturbance lasted at least 1 month?
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
            // Do the disturbance interfere with educational or occupational achievement or with social communication?
            case 1:
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
            // Is there consistant failure to speak in specific social situations in which there is an expectation for speaking despite speaking in other situations?
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
            // Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?
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
            // Are the disturbances not better explained by a different cause?
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
        }

        return false;
    }

    @Override
    public int getQuestionLength() {
        return questions.size();
    }
}
