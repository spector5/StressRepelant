package rentedbanana.stressrepelant;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dictionary used to check messages for positive or negative responses
 * Created by Austin on 12/14/2017.
 */

// TODO I can break some phrases into regexs to cover more phrases with less loops
// ex. I'm pretty sure that's happening = im [non negative word] sure thats happening
public final class Dictionary
{
    private static final ArrayList<String> positiveDict = new ArrayList<>(Arrays.asList("yes", "yeah",
            "it does", "i believe so", "thats what i think", "most of the time", "affirmative",
            "every time", "usually", "all of the time", "all the time", "agree", "i just said",
            "what i said", "agreed", "agree", "i think so", "i should think so", "i would think so",
            "whenever i", "everytime", "always", "dont disagree", "do not disagree",
            "i think that is whats happening", "i think thats whats happening", "i think thats what is happening",
            "i think thats happening", "i think that is happening"));

    private static final ArrayList<String> negativeDict = new ArrayList<>(Arrays.asList("no", "nah",
            "it does not", "it doesnt", "dont believe so", "do not believe so", "not what i think",
            "not most of the time", "negative", "never", "not usually", "none of the time", "disagree",
            "not what i said", "not what i just said", "didnt just say that", "did not just say that",
            "dont think so", "do not think so", "would not think so", "wouldnt think so", "should not think so",
            "shouldnt think so", "nope"));

    public static int countPositive(String text)
    {
        Log.d("pos text", text);
        String string = text.toLowerCase();
        Log.d("pos str", string);
        int count = 0;

        string = string.replaceAll("[-+.^:,'\"]","");
        Log.d("pos reg", string);
        // dictionary could become very large, brainstorm preprocessing that can reduce number of loops
        for (int i = 0; i < positiveDict.size(); i++)
        {
            if (string.contains(positiveDict.get(i))) {
                Log.d("pos", "match " + positiveDict.get(i));
                count++;
            }
        }

        Log.d("pos", String.valueOf(count));
        return count;
    }

    public static int countNegative(String text)
    {
        String string = text.toLowerCase();
        int count = 0;

        string = string.replaceAll("[-+.^:,'\"]","");
        // dictionary could become very large, brainstorm preprocessing that can reduce number of loops
        for (int i = 0; i < negativeDict.size(); i++)
        {
            if (string.contains(negativeDict.get(i)))
                count++;
        }

        Log.d("neg", String.valueOf(count));
        return count;
    }
}
