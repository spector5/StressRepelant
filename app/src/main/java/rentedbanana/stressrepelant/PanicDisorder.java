package rentedbanana.stressrepelant;

import android.app.Activity;

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

    private static final String starter = "Do you often have fear that seems to be random and reoccuring?";

    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("Do you have recurrent, unexpected panic attacks?", true);
            put("Have you had any unexpected panic attacks that are recurring?", true);
            put("Have you been having unexpected, recurrent panic attacks?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?", true);
            put("Have you recently had an attack followed by 1 month or more of one or both of these: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Are the disturbances attributable to the physiological effects of a substance or another medical condition?", false);
            put("Are the disturbances caused by the effects of a substance or other medical condition?", false);
            put("Do you have another medical condition or use a substance that may be causing these disturbances?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Are the disturbances not better explained by another mental disorder?", false);
            put("Do you have a different mental disorder that may be causing these disturbances?", false);
            put("Is there a different mental disorder that you have been diagnosed with that many be causing these disturbances?", false);}});
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
        {
            ArrayList<String> firstComp = new ArrayList<>(Arrays.asList("It seems you have problems with panic symptoms.",
                    "I believe you may be dealing with symptoms of unexpected panic.",
                    "It looks to me like you have some issues with panicking."));
            ArrayList<String> secondComp = new ArrayList<>(Arrays.asList(" You should be aware of some signs of panic, like elevated heart rate, dizziness, or sweating.",
                    " Some symptoms you need to look out for are weakness, muscle spasms, and elevated heart rate.",
                    " You need to watch for sudden chills, shortness of breath, or a fear you are going crazy."));
            ArrayList<String> thirdComp = new ArrayList<>(Arrays.asList(" If you see these symptoms you should sit down where ever you are, relax your muscles as much as possible, and focus on breathing slowly.",
                    " If you have an attack, you should sit down and let your muscles relax. Also focus on breathing very slowly.",
                    " If you begin to panic you should focus on breathing slowly and sit down so your muscles can relax."));
            Random rand = new Random();

            String response = "";

            response = response.concat(firstComp.get(rand.nextInt(firstComp.size())));
            response = response.concat(secondComp.get(rand.nextInt(secondComp.size())));
            response = response.concat(thirdComp.get(rand.nextInt(thirdComp.size())));
            response = response + " In a panic you need to tell yourself that this is only temporary, it won't last forever and you will get through it.";

            return response;
        }
        else
            return "From what I can tell, you appear to be fine. Any stress you currently have can be atrributed to normal, everyday life. This type of stress is short term and will go away if you just give it time.";
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
            // Do you have recurrent unexpected panic attacks?
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
            // Has at least one of the attacks been followed by 1 month (or more) of one or both of the following: a significant maladaptive change in behavior related to the attacks or persistent concern or worry about additional panic attacks?
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
            // Are the disturbances not attributable to the physiological effects of a substance or another medical condition?
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
            // Are the disturbances not better explained by another mental disorder?
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
        }
        return false;
    }

    @Override
    public int getQuestionLength() {
        return questions.size();
    }
}
