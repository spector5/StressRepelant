package rentedbanana.stressrepelant;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Spedial case condition, there is no flowchart, this is a catch all class
 * it is less extensive than the rest, it just gives general advice
 * Created by Austin on 2/11/2018.
 */

public class Stress implements Condition {
    private int questNum = 0;
    private static final String starter = "Would it be fair to say you feel stressed?";

    private final ArrayList<String> questions = new ArrayList<>(Arrays.asList("Let's figure out what caused this. Is there something happening at home, or is this related to work/school.",
            "Well, walk me through what happened recently to make you feel stressed. I'm all ears.",
            "What things have you done or plan on doing to cope with this."));

    public static String getStarter()
    {
        return starter;
    }

    /**
     * Num doesnt actually do anything
     * @param num
     * @return
     */
    @Override
    public String getQuestion(int num) {
        return questions.get(questNum++);
    }

    /**
     * I am well aware this method does nothing, but this is the catch all class so we aren't going to do anything other than
     * just ask them to talk about their problems
     * @param quest
     * @param ans
     * @param num
     * @param act
     * @return
     */
    @Override
    public boolean sendAnswer(String quest, String ans, int num, Activity act, Context con) {
        Dictionary.filterText(quest, act, con);
        return true;
    }

    @Override
    public String makeResponse() {
        ArrayList<String> firstComp = new ArrayList<>(Arrays.asList(" Don't be afraid of avoiding stressful situations if you have the option.",
                " I can only help you so much. I recommend finding someone in real life you trust to help you through this.",
                " Your diet is very important to stress relief. I recommend eating a healthy diet, oranges, spinach, and tea are good things to add to your diet."));
        ArrayList<String> secondComp = new ArrayList<>(Arrays.asList(" It's also very important to exercise regularly, you can run, lift weights, or anything else that gets you moving.",
                " It would help alot to stay away from alcohol. It won't help you get long term closure or stress relief.",
                " It may sound strange, but deep breathing could be very helpful to you. Just sit down, close your eyes, fill your lungs completely, then let it out. Do this for a few minutes every day."));
        Random rand = new Random();

        String response = "I think you will be fine.";

        response = response.concat(firstComp.get(rand.nextInt(firstComp.size())));
        response = response.concat(firstComp.get(rand.nextInt(secondComp.size())));
        return response;
    }

    @Override
    public int getQuestionLength() {
        return questions.size();
    }
}
