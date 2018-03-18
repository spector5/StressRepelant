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
            put("Is there consistent failure to speak in specific social situations in which there is an expectation for speaking?", true);
            put("Do you consistently fail to speak in specific social situations you are expected to speak at?", true);
            put("Have you been unable to speak at times you were expected to speak?", true);}});
        put(3, new Hashtable<String, Boolean>(){{
            put("Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?", true);
            put("Is your failure to speak caused by a lack of knowledge of a spoken language?", false);
            put("Do you have trouble speaking because you are unfamiliar with a spoken language?", false);}});
        put(4, new Hashtable<String, Boolean>(){{
            put("Are the disturbances not better explained by a different cause?", false);
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
        if (criteria >= 5) {
            //return "I believe you have Selective Mutism.";
            ArrayList<String> firstComp = new ArrayList<>(Arrays.asList("You seem to have stress based on an inability to talk to others.",
                    "I believe you are stressed because you have trouble speaking to others.",
                    "It seems to me that you are stressed because you have a hard time speaking to others."));
            ArrayList<String> secondComp = new ArrayList<>(Arrays.asList(" You might be tempted to stay away from social situations, but this will only make things harder.",
                    " I really think you should not shy away from social situations, even if they may scare you or make you uncomfourtable.",
                    " Social situations may seem uncomfortable for you, but I think you need to be around others to make you more comfortable."));
            ArrayList<String> thirdComp = new ArrayList<>(Arrays.asList(" You can start small, just find one person, pet, or even object you feel like you can talk to.",
                    " Try to find one person, pet, or something that you can talk to, then you can branch out from there once you are more comfortable.",
                    " I want you to try to find one person or thing you can talk to, just so you can start getting comfortable talking to others."));
            ArrayList<String> fourthComp = new ArrayList<>(Arrays.asList(" Once you're abit more comfortable you should try to find a club or group of people that share an interest of yours and talk to them.",
                    " If you start feeling better talking to others you should find a social club that shares an interest of yours so you have people to talk to.",
                    " Take your time, when you feel more comfortable find a club or social group nearby that you can talk to."));
            Random rand = new Random();

            String response = "";

            response = response.concat(firstComp.get(rand.nextInt(firstComp.size())));
            response = response.concat(secondComp.get(rand.nextInt(secondComp.size())));
            response = response.concat(thirdComp.get(rand.nextInt(thirdComp.size())));
            response = response.concat(fourthComp.get(rand.nextInt(fourthComp.size())));
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
            // Have the duration of the disturbance lasted at least 1 month?
            case 0:
                Dictionary.filterText(ans, act, con);
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
            // Do the disturbance interfere with educational or occupational achievement or with social communication?
            case 1:
                Dictionary.filterText(ans, act, con);
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
            // Is there consistant failure to speak in specific social situations in which there is an expectation for speaking despite speaking in other situations?
            case 2:
                Dictionary.filterText(ans, act, con);
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
            // Is there a failure to speak not attributable to a lack of knowledge of, or comfort with, the spoken language required in the social situation?
            case 3:
                Dictionary.filterText(ans, act, con);
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
            // Are the disturbances not better explained by a different cause?
            case 4:
                Dictionary.filterText(ans, act, con);
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
