package rentedbanana.stressrepelant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

/**
 * Created by Austin on 1/20/2018.
 */

public class Agoraphobia implements Condition
{
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Do you have intense fear or anxiety triggered by the real or anticipated exposure to a wide range of situations?";

    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("Is fear or anxiety related to two (or more) of the following five situations: standing in line or being in a crowd, being in enclosed places, being in open spaces, using public transportation, being outside of the home alone?", true);
            put("Do you have anxiety or fear caused by one or more of the following situations: standing in line or being in a crowd, being in enclosed places, being in open spaces, using public transportation, being outside of the home alone?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Do you avoid these situations because of thoughts that escape might be difficult or help might not be available in the event of developing panic-like symptoms or other incapacitating or embarrassing symptoms?", true);
            put("Do you avoid these situations because you are afraid escape might be difficult if you begin to panic?", true);
            put("Have you attempted to avoid these situation due to fear that you may panic without having help available?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Does the situation almost always provoke fear or anxiety?", true);
            put("Do you always feel fear when facing this situation?", true);
            put("Do you experience fear or anxiety every time you face this situation?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Are the situations actively avoided, require the presence of a companion, or are endured with intense fear or anxiety?", true);
            put("Do you do one of the following: avoid the situation, require a companion in this situation, or endure the situation with intense fear?", true);
            put("Are one of these statements true: you require a companion in this situation, you avoid the situation, or you experience great fear when in this situation?", true);}});
        put(4, new Hashtable<String, Boolean>(){{
            put("Is the fear or anxiety out of proportion to the actual danger posed by the situations?", true);
            put("Does your fear or anxiety outweigh the actual danger from being in these situations?", true);
            put("Is your anxiety exaggerated when compared to the actual danger posed by the situation?", true);}});
        put(5, new Hashtable<String, Boolean>(){{
            put("Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?", true);
            put("Do you have persistent fear or anxiety lasting at least 6 months?", true);
            put("Have you had a persistent fear or anxiety about this situation over 6 or more months?", true);}});
        put(6, new Hashtable<String, Boolean>(){{
            put("Does the fear, anxiety, or avoidance cause significant distress or impairment in social, occupational, or other important areas of functioning?", true);
            put("Are you significantly distressed by your fear or anxiety of this situation?", true);
            put("Has your fear or anxiety of this situation impaired you in any other area of your life?", true);}});
        put(7, new Hashtable<String, Boolean>(){{
            put("If another medical condition is present, is the fear, anxiety, or avoidance clearly excessive?", true);}});
        put(8, new Hashtable<String, Boolean>(){{
            put("Is the fear, anxiety, or avoidance not better explained by the symptoms of another mental disorder?", true);
            put("Do you have a different mental disorder that may be causing this fear or anxiety?", true);
            put("Is there a different mental disorder that you have been diagnosed with that many be causing this fear or anxiety?", true);}});
    }};

    /*private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Marked fear or anxiety about two (or more) of the following five situations: standing in line or being in a crowd, being in enclosed places, being in open spaces, using public transportation, being outside of the home alone?",
            "Do you avoid these situations because of thoughts that escape might be difficult or help might not be available in the event of developing panic-like symptoms or other incapacitating or embarrassing symptoms?",
            "Does the agoraphobic situation almost always provoke fear or anxiety?",
            "Are the agoraphobic situations actively avoided, require the presence of a companion, or are endured with intense fear or anxiety?",
            "Is the fear or anxiety out of proportion to the actual danger posed by the agoraphobic situations and to the sociocultural context?",
            "Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?",
            "Does the fear, anxiety, or avoidance cause clinically significant distress or impairment in social, occupational, or other important areas of functioning?",
            "If another medical condition is present, the fear anxiety or avoidance is clearly excessive?",
            "Is the fear, anxiety, or avoidance not better explained by the symptoms of another mental disorder?"));*/

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
    public boolean sendAnswer(String quest, String ans, int num)
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
            // Do you avoid these situations because of thoughts that escape might be difficult or help might not be available in the event of developing panic-like symptoms or other incapacitating or embarrassing symptoms?
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
            // Does the agoraphobic situation almost always provoke fear or anxiety?
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
            // Are the agoraphobic situations actively avoided, require the presence of a companion, or are endured with intense fear or anxiety?
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
            // Is the fear or anxiety out of proportion to the actual danger posed by the agoraphobic situations and to the sociocultural context?
            case 4:
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
            // Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?
            case 5:
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
            // Does the fear, anxiety, or avoidance cause clinically significant distress or impairment in social, occupational, or other important areas of functioning?
            case 6:
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
            // If another medical condition is present, the fear anxiety or avoidance is clearly excessive?
            case 7:
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
            // Is the fear, anxiety, or avoidance not better explained by the symptoms of another mental disorder?
            case 8:
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
