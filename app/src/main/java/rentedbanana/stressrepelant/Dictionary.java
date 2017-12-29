package rentedbanana.stressrepelant;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Dictionary used to check messages for positive or negative responses
 * Created by Austin on 12/14/2017.
 */

// TODO I can break some phrases into regexs to cover more phrases with less loops
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

    private static final HashMap<String, Integer> timeDict =  new HashMap<>(Collections.unmodifiableMap(
            new HashMap<String, Integer>() {
                {
                    put("[num] months", 0);
                    put("[num] weeks", 0);
                    put("[num] days", 0);
                    put("[num] years", 0);
                    put("[num] month", 0);
                    put("[num] week", 0);
                    put("[num] day", 0);
                    put("[num] year", 0);
                    put("a day", 1);
                    put("a week", 7);
                    put("a month", 30);
                    put("a year", 365);
                }
            }));

    private static final HashMap<String, Integer> numbers =  new HashMap<>(Collections.unmodifiableMap(
            new HashMap<String, Integer>() {
                {
                    put("zero", 0);
                    put("one", 1);
                    put("two", 2);
                    put("three", 3);
                    put("four", 4);
                    put("five", 5);
                    put("six", 6);
                    put("seven", 7);
                    put("eight", 8);
                    put("nine", 9);
                    put("ten", 10);
                    put("eleven", 11);
                    put("twelve", 12);
                    put("thirteen", 13);
                    put("fourteen", 14);
                    put("fifteen", 15);
                    put("sixteen", 16);
                    put("seventeen", 17);
                    put("eighteen", 18);
                    put("nineteen", 19);
                }
            }));

    private static final HashMap<String, Integer> numbersPrefix =  new HashMap<>(Collections.unmodifiableMap(
            new HashMap<String, Integer>() {
                {
                    put("twenty", 20);
                    put("thirty", 30);
                    put("forty", 40);
                    put("fifty", 50);
                    put("sixty", 60);
                    put("seventy", 70);
                    put("eighty", 80);
                    put("ninety", 90);
                    put("hundred", 100);
                }
            }));
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

        str = str.replaceAll("[+.^:,'\"]","");
        str = str.replaceAll("-", " ");
        return str;
    }

    private static String cleanNumber(String str)
    {
        StringTokenizer toke = new StringTokenizer(str, " ,.", false);
        int num;
        String prev = "";
        String cur;
        StringBuilder ret = new StringBuilder();

        if (str.contains("hundred"))
        {
            //Log.d("in num", "found hundred");
            String next = "";
            while (toke.hasMoreTokens())
            {
                cur = toke.nextToken();

                if (!numbers.containsKey(cur) && !cur.equals("hundred"))
                    ret.append(cur + " ");

                //Log.d("in num", "!!" + cur + "!!");
                if (cur.equals("hundred"))
                {
                    //Log.d("in num", "found hundred again...");
                    num = numbersPrefix.get(cur) * numbers.get(prev);

                    if (toke.hasMoreTokens()) {
                        next = toke.nextToken();

                        if (next.equals("and"))
                            next = toke.nextToken();

                        if (numbersPrefix.containsKey(next)) {
                            num += numbersPrefix.get(next);
                            next = toke.nextToken();
                            if (numbers.containsKey(next))
                                num += numbers.get(next);
                        } else if (numbers.containsKey(next))
                            num += numbers.get(next);
                    }

                    //Log.d("in num", "num = " + num);
                    ret.append(num + " ");

                    if (!numbers.containsKey(next) && !numbersPrefix.containsKey(next))
                        ret.append(next + " ");

                    while(toke.hasMoreTokens())
                        ret.append(toke.nextToken() + " ");

                    break;
                }
                else
                    prev = cur;
            }
        }
        else
        {
            prev = toke.nextToken();
            if (!numbersPrefix.containsKey(prev) && !numbers.containsKey(prev))
                ret.append(prev + " ");

            while (toke.hasMoreTokens())
            {
                cur = toke.nextToken();
                if (!numbersPrefix.containsKey(cur) && !numbers.containsKey(cur))
                    ret.append(cur + " ");

                if (numbers.containsKey(cur))
                {
                    num = numbers.get(cur);
                    if (numbersPrefix.containsKey(prev))
                        num += numbersPrefix.get(prev);

                    while (toke.hasMoreTokens())
                        ret.append(toke.nextToken() + " ");

                    break;
                }
            }
        }

        return ret.toString();
    }

    public static int countDays(String text)
    {
        String string = cleanString(text);
        Log.d("days clean", string);
        string = cleanNumber(string);
        Log.d("num clean", string);
        int duration = -1;

        //duration = Integer.parseInt(string);
        // TODO i kind of forgot what I was doing with the first and second, so fix this
        for (String key : timeDict.keySet())
        {
            if (key.contains("[")) {
                String unit = key.substring(key.indexOf("[") + 6, key.length());

                if (string.contains(unit))
                {
                    String word = "";
                    try{
                        word = string.substring(string.indexOf(first) + first.length() + 1, string.indexOf(second) - 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        Log.d("pos", "did not find adj");
                    }

                    Log.d("run", "!" + word + "!");
                    if (word.equals("") || posAdjective.contains(word))
                        count++;
                }
            }
            else if (string.contains(key)) {
                //Log.d("pos", "match " + positiveDict.get(i));
                duration = timeDict.get(key);
            }
        }

        return duration;
    }

    public static int countPositive(String text)
    {
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
                    String key = "";
                    try{
                        key = string.substring(string.indexOf(first) + first.length() + 1, string.indexOf(second) - 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        Log.d("pos", "did not find adj");
                    }

                    Log.d("run", "!" + key + "!");
                    if (key.equals("") || posAdjective.contains(key))
                        count++;
                }
            }
            else if (string.contains(positiveDict.get(i))) {
                //Log.d("pos", "match " + positiveDict.get(i));
                count++;
            }
        }

        Log.d("pos", String.valueOf(count));
        return count;
    }

    public static int countNegative(String text)
    {
        String string = cleanString(text);
        Log.d("neg clean", string);
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
