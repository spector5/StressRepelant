package rentedbanana.stressrepelant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

/**
 * Created by Austin on 1/5/2018.
 */

public class SocialAnxiety implements Condition
{
    private int criteria;   // how many criteria are met
    //private int subcriteria;    // how many of the subcriteria are met (need 3 to satisfy requirement)

    private static final String starter = "Is your fear or anxiety derived from social interactions?";

    private final Hashtable<Integer, Hashtable<String, Boolean>> questions = new Hashtable<Integer, Hashtable<String, Boolean>>(){{
        put(0, new Hashtable<String, Boolean>(){{
            put("Do you experience specific fear or anxiety about one or more social situations in which you are exposed to possible scrutiny by others?", true);
            put("Do some social situations in which you are exposed to possible scrutiny by others cause you significant fear or anxiety?", true);
            put("Do you have a specific fear about a social situation?", true);}});
        put(1, new Hashtable<String, Boolean>(){{
            put("Do you fear that you will act in a way or show anxiety symptoms that will be negatively evaluated?", true);
            put("Do you fear being negatively evaluated for the way you act or anxiety that you show?", true);
            put("Are you afraid of showing symptoms of anxiety that may be seen by others?", true);}});
        put(2, new Hashtable<String, Boolean>(){{
            put("Do social situations almost always provoke fear or anxiety?", true);
            put("Do social situations almost always cause you to feel anxious or afraid?", true);
            put("Do you always feel this anxiety when faced with social situations?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Do you avoid social situations or endure them only with intense fear or anxiety?", true);
            put("Do you try to avoid social situations or endure them with intense fear?", true);
            put("Have you been enduring social situations with anxiety or avoiding them?", true);}});
        put(4, new Hashtable<String, Boolean>(){{
            put("Is the fear or anxiety out of proportion to the actual or accepted threat level of the social situation?", true);
            put("Does your fear of social situations exceed the danger posed by them?", true);
            put("Do you fear social situations even when the danger presented is not worth the fear?", true);}});
        put(5, new Hashtable<String, Boolean>(){{
            put("If another medical condition is present, is the fear, anxiety, or avoidance clearly unrelated or excessive?", true);
            put("Is your fear unrelated to a different medical condition?", true);}});
        put(6, new Hashtable<String, Boolean>(){{
            put("Is the fear, anxiety, or avoidance not better explained by the symptoms of another mental disorder?", true);
            put("Do you have a different mental disorder that may explain your anxiety?", false);
            put("Is there a diagnosed mental disorder you have that may contribute to your fear?", false);}});
        put(7, new Hashtable<String, Boolean>(){{
            put("Is the fear, anxiety, or avoidance not attributable to the physiological effects of a substance?", true);
            put("Are you using a substance that may cause this anxiety?", false);}});
        put(8, new Hashtable<String, Boolean>(){{
            put("Does the fear, anxiety, or avoidance cause clinically significant distress or impairment in social, occupational, or other important areas of functioning?", true);
            put("Does your fear, anxiety, or avoidance cause you significant distress or difficulty in important areas such as social or occupational settings?", true);
            put("Does this fear cause significant distress in some area of your life?", true);}});
        put(9, new Hashtable<String, Boolean>(){{
            put("Is the fear, anxiety, or avoidance persistent, typically lasting for 6 months or more?", true);
            put("Does the fear or anxiety typically persist, lasting for 6 months or more?", true);
            put("Have you had a persistent anxiety for the last 6 (or more) months?", true);}});
    }};

    /*private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Marked fear or anxiety about one or more social situations in which the individual is exposed to possible scrutiny by others?",
            "Are the individual fears that he or she will act in a way or show anxiety symptoms that will be negatively evaluated?",
            "Do social situations almost always provoke fear or anxiety?",
            "Are social situations avoided or endured with intense fear or anxiety?",
            "Is the fear or anxiety out of proportion to the actual threat posed by the social situation and to the sociocultural context?",
            "If another medical condition is present, the fear, anxiety, or avoidance is clearly unrelated or is excessive?",
            "Is the fear, anxiety, or avoidance is not better explained by the symptoms of another mental disorder, such as panic disorder, body dysmorphic disorder, or autism spectrum disorder?",
            "Is the fear, anxiety, or avoidance not attributable to the physiological effects of a substance?",
            "Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?",
            "Does the fear, anxiety, or avoidance is persistent, typically lasting for 6 months or more?"));*/

    /**
     * Constructor, makes everything zero
     */
    public SocialAnxiety()
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
        if (criteria >= 10) {
            //return "I believe you have Social Anxiety.";
            ArrayList<String> firstComp = new ArrayList<>(Arrays.asList("I think your stress is based on social interaction.",
                    "It seems social situations contribute to your stress.",
                    "I think you may be stressed partly because of social interactions."));
            ArrayList<String> secondComp = new ArrayList<>(Arrays.asList(" You shouldn't try to rush yourself into a quick fix.",
                    " You can do things to feel more comfortable around others, but know that this will be a slow process.",
                    " You can overcome this, but it may take some time."));
            ArrayList<String> thirdComp = new ArrayList<>(Arrays.asList(" Try to start small by getting one or two people you feel you can trust and be open with them. Then allow them to introduce you to others, slowly.",
                    " You can talk to a single person in a private setting jsut to get comfortable having conversation in a safe environment, then get abit bigger with a larger social group and more varied social situations.",
                    " A good place to start would be to find a single person you can be open with, then let them ease you into slightly more open social situations."));
            Random rand = new Random();

            String response = "";

            response = response.concat(firstComp.get(rand.nextInt(firstComp.size())));
            response = response.concat(secondComp.get(rand.nextInt(secondComp.size())));
            response = response.concat(thirdComp.get(rand.nextInt(thirdComp.size())));

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
    public boolean sendAnswer(String quest, String ans, int num, Activity act, Context con)
    {
        int countPos;
        int countNeg;

        // one case of each question, not including starter
        switch(num)
        {
            // Marked fear or anxiety about one or more social situations in which the individual is exposed to possible scrutiny by others?
            case 0:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Are the individual fears that he or she will act in a way or show anxiety symptoms that will be negatively evaluated?
            case 1:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Do social situations almost always provoke fear or anxiety?
            case 2:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                // Are social situations avoided or endured with intense fear or anxiety?
            case 3:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Is the fear or anxiety out of proportion to the actual threat posed by the social situation and to the sociocultural context?
            case 4:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // If another medical condition is present, the fear, anxiety, or avoidance is clearly unrelated or is excessive?
            case 5:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Is the fear, anxiety, or avoidance is not better explained by the symptoms of another mental disorder, such as panic disorder, body dysmorphic disorder, or autism spectrum disorder?
            case 6:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Is the fear, anxiety, or avoidance not attributable to the physiological effects of a substance?
            case 7:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Does the fear, anxiety, or avoidance causes clinically significant distress or impairment in social, occupational, or other important areas of functioning?
            case 8:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
            // Does the fear, anxiety, or avoidance is persistent, typically lasting for 6 months or more?
            case 9:
                Dictionary.filterText(ans, act, con);
                if (Dictionary.checkUnsure(ans) != 0)
                {
                    criteria++;
                    return true;
                }
                countPos = Dictionary.countPositive(ans);
                countNeg = Dictionary.countNegative(ans);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                    outputStreamWriter.write("pos:" + countPos + " neg:" + countNeg + "\n");
                    outputStreamWriter.close();
                }
                catch (Exception e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                if (questions.get(num).get(quest)) {
                    if (countPos > countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        return true;
                    } else if (countNeg == 0 && countPos == 0)
                        return false;
                    else
                        return true;
                }
                else {
                    if (countPos < countNeg) {
                        criteria++;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("fufills:true\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
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
