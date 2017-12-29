package rentedbanana.stressrepelant;

/**
 * Base for every mental condition we check for
 * Created by Austin on 12/22/2017.
 */

public interface Condition
{
    String getQuestion(int num);
    boolean sendAnswer(String ans, int num);
    String makeResponse();
}
