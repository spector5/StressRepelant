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
            "it does", "i believe so", "that is what i think", "most of the time", "affirmative", "every time",
            "usually", "all of the time", "all the time", "agree", "what i said", "i just said", "agreed",
            "i [pos] think so", "whenever i", "everytime", "always", "do not disagree",
            "i think that is what is happening", "i am [pos] sure that is happening",
            "i am [pos] sure that is what is happening"));

    private static final ArrayList<String> posAdjective = new ArrayList<>(Arrays.asList("definitely",
            "really", "pretty", "absolutely", "positively", "surely", "truly", "unquestionably", "easily",
            "decidedly", "decisively", "certainly", "genuinely", "honestly", "legitimately", "literally",
            "undoubtedly", "admittedly", "should", "would", "distinctly"));

    private static final ArrayList<String> negativeDict = new ArrayList<>(Arrays.asList("no"));
    /*
    This is version 1 of the dictionary, i will leave it just incase something goes wrong
    This was made before I started using 4 lists for more generalized responses

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
    */

    /**
     * Formats the string to be used with the dictionary
     * @param str = unformatted string
     * @return formatted string
     */
    private static String cleanString(String str)
    {
        str = str.toLowerCase();

        str = str.replaceAll("n't"," not");
        str = str.replaceAll("'s"," is");
        str = str.replaceAll("'d"," would");
        str = str.replaceAll("'ll"," will");
        str = str.replaceAll("'m"," am");
        str = str.replaceAll("'ve"," have");
        str = str.replaceAll("'re"," are");

        str = str.replaceAll("[-+.^:,'\"]","");

        return str;
    }

    public static int countPositive(String text)
    {
        Log.d("pos text", text);
        String string = cleanString(text);
        Log.d("pos clean", string);
        int count = 0;

        // dictionary could become very large, brainstorm preprocessing that can reduce number of loops
        for (int i = 0; i < positiveDict.size(); i++)
        {
            if (positiveDict.get(i).contains("[")) {

                String first = positiveDict.get(i).substring(0, positiveDict.get(i).indexOf("[") - 1);
                String second = positiveDict.get(i).substring(positiveDict.get(i).indexOf("[") + 6, positiveDict.get(i).length());

                if (string.contains(first) && string.contains(second))
                {
                    String key = string.substring(string.indexOf(first) + first.length() + 1, string.indexOf(second) - 1);
                    Log.d("run", "!" + key + "!");
                }
            }
            else if (string.contains(positiveDict.get(i))) {
                Log.d("pos", "match " + positiveDict.get(i));
                count++;
            }
        }

        Log.d("pos", String.valueOf(count));
        return count;
    }

    public static int countNegative(String text)
    {
        String string = cleanString(text);
        int count = 0;

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
