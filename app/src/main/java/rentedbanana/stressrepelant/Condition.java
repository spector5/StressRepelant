package rentedbanana.stressrepelant;

import android.app.Activity;
import android.content.Context;

/**
 * Base for every mental condition we check for
 * Created by Austin on 12/22/2017.
 */

public interface Condition
{
    String getQuestion(int num);
    boolean sendAnswer(String quest, String ans, int num, Activity act, Context con);
    String makeResponse();
    int getQuestionLength();
}
