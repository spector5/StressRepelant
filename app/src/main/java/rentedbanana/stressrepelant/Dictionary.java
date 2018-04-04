package rentedbanana.stressrepelant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import android.telephony.SmsManager;

/**
 * Dictionary used to check messages for positive or negative responses
 * Created by Austin on 12/14/2017.
 */

// TODO I can break some phrases into regexs to cover more phrases with less loops
public final class Dictionary
{
    // all phrases of agreement
    private static final ArrayList<String> positiveDict = new ArrayList<>(Arrays.asList("yes", "yeah",
            "it [pos] does", "i [pos] believe so", "i [pos] believe it is", "i [pos] believe that", "that is [pos] what i think",
            "most of the time", "affirmative", "every time", "usually", "all of the time", "all the time",
            "agree", "what i said", "i [pos] just said", "agreed", "i [pos] think so", "whenever i", "everytime",
            "always", "do not disagree", "i [pos] think that is what is happening", "i am [pos] sure that is happening",
            "i am [pos] sure that is what is happening", "that is [pos] true", "[pos]", "it [pos] is", "they [pos] do",
            "i [pos] do", "they [pos] are", "i [pos] am", "there [pos] are", "that is [pos] true", "that is [pos] correct",
            "is [pos] right", "are [pos] right", "that is [pos] the case"));

    // any adjective that would be used in a positive response (replaces [pos] in above list)
    private static final ArrayList<String> posAdjective = new ArrayList<>(Arrays.asList("definitely",
            "really", "pretty", "absolutely", "positively", "surely", "truly", "unquestionably", "easily",
            "decidedly", "decisively", "certainly", "genuinely", "honestly", "legitimately", "literally",
            "undoubtedly", "admittedly", "should", "would", "distinctly", "probably", "often", "sometimes",
            "some of the time", "mostly", "absolutely"));

    // all phrases of disagreement
    private static final ArrayList<String> negativeDict = new ArrayList<>(Arrays.asList("no", "nah",
            "it does not", "do not believe so", "[neg] what i think", "[neg] most of the time", "negative",
            "never", "[neg] usually", "none of the time", "disagree", "disagreed", "[neg] what i [wrd] said",
            "did [neg] [wrd] say", "disagree", "[neg] think so", "that is [neg] true", "[neg]", "false",
            "incorrect", "not at all"));

    //private static final ArrayList<String> filterDict = new ArrayList<>(Arrays.asList());

    // sometimes, somewhat, dont know, not sure, unsure, uncertain, dont want to answer, decline, skip, maybe
    private static final ArrayList<String> unsureDict = new ArrayList<>(Arrays.asList("sometimes", "somewhat", "dont know",
            "not sure", "unsure", "uncertain", "dont want to answer", "decline", "skip", "maybe"));

    // any adjective that would be used in a negative response (replaces [neg] in above list)
    private static final ArrayList<String> negAdjective = new ArrayList<>(Arrays.asList("not", "rarely",
            "never"));

    // all phrases that indicate time that has passed and their equivelent number of days
    private static final HashMap<String, Integer> timeDict =  new HashMap<>(Collections.unmodifiableMap(
            new HashMap<String, Integer>() {
                {
                    put("[num] months", 30);
                    put("[num] weeks", 7);
                    put("[num] days", 1);
                    put("[num] years", 365);
                    put("[num] month", 30);
                    put("[num] week", 7);
                    put("[num] day", 1);
                    put("[num] year", 365);
                    put("a day", 1);
                    put("a week", 7);
                    put("a month", 30);
                    put("a year", 365);
                }
            }));

    // map of string numbers and their numeric counterpart
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

    // numbers that indicate the tens place
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

        // gets rid of contractions
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

    /**
     * Formats a string to be counted, converts numbers with letters to numbers that can be parsed into integers
     * @param str = user text
     * @return formatted string
     */
    private static String cleanNumber(String str)
    {
        StringTokenizer toke = new StringTokenizer(str, " ,.", false);
        int num;    // numeric version of number
        String prev = "";
        String cur;
        StringBuilder ret = new StringBuilder();    // string that will be returned

        // used when user indicated something has happened for 100+ units
        if (str.contains("hundred"))
        {
            String next = "";

            // iterate through words in response
            while (toke.hasMoreTokens())
            {
                cur = toke.nextToken();

                // appends word if is not part of a number
                if (!numbers.containsKey(cur) && !cur.equals("hundred"))
                    ret.append(cur + " ");

                //Log.d("in num", "!!" + cur + "!!");
                if (cur.equals("hundred"))
                {
                    //Log.d("in num", "found hundred again...");
                    // 100 * number (hundreds place)
                    num = numbersPrefix.get(cur) * numbers.get(prev);

                    // sees if a number is 100+, if so adds remainder to num
                    if (toke.hasMoreTokens()) {
                        next = toke.nextToken();

                        // if using proper grammer, this and needs to be ignored
                        if (next.equals("and"))
                            next = toke.nextToken();

                        // if remainder is 20+
                        if (numbersPrefix.containsKey(next)) {
                            num += numbersPrefix.get(next);
                            next = toke.nextToken();
                            if (numbers.containsKey(next))
                                num += numbers.get(next);
                        } else if (numbers.containsKey(next))   // if remainder is < 20
                            num += numbers.get(next);
                    }

                    //Log.d("in num", "num = " + num);
                    // append numeric version of number
                    ret.append(num + " ");

                    // append everything else
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
        else    // if a number is less than 100
        {
            prev = toke.nextToken();
            // append everything that isnt related to a number
            if (!numbersPrefix.containsKey(prev) && !numbers.containsKey(prev))
                ret.append(prev + " ");

            while (toke.hasMoreTokens())
            {
                cur = toke.nextToken();
                // append everything that isnt related to a number
                if (!numbersPrefix.containsKey(cur) && !numbers.containsKey(cur))
                    ret.append(cur + " ");

                // if token is a number
                if (numbers.containsKey(cur))
                {
                    num = numbers.get(cur);

                    // checks if number is greater than 20
                    if (numbersPrefix.containsKey(prev))
                        num += numbersPrefix.get(prev);

                    // append numeric version of number
                    ret.append(num + " ");

                    // append everything else
                    while (toke.hasMoreTokens())
                        ret.append(toke.nextToken() + " ");

                    break;
                }
            }
        }

        return ret.toString();
    }

    /**
     * Counts how many days a user has had whatever the question asks for
     * @param text = user response
     * @return number of days
     */
    public static int countDays(String text)
    {
        // formats string
        String string = cleanString(text);
        Log.d("days clean", string);
        string = cleanNumber(string);
        Log.d("num clean", string);
        int duration = -1;

        // iterate through all phrases in timeDict
        for (String key : timeDict.keySet())
        {
            // if phrases has a variable length with a unit
            if (key.contains("[")) {
                String unit = key.substring(key.indexOf("[") + 6, key.length());

                if (string.contains(unit))
                {
                    String number = "";
                    String word = "";
                    StringTokenizer toke = new StringTokenizer(string);

                    if (toke.hasMoreTokens())
                        word = toke.nextToken();

                    // iterate through all words in text
                    while (toke.hasMoreTokens())
                    {
                        number = word;
                        word = toke.nextToken();

                        if (word.equals(unit))
                            break;
                    }

                    //Log.d("run", "!" + number + "!" + word + "!");

                    try {
                        // unit length * number
                        duration = timeDict.get(key) * Integer.parseInt(number);
                    } catch (NumberFormatException e) {
                        // do nothing
                    }
                }
            }
            else if (string.contains(key)) {    // if phrases is directly related to a number
                //Log.d("pos", "match " + positiveDict.get(i));
                duration = timeDict.get(key);
            }
        }

        Log.d("in days", String.valueOf(duration));
        return duration;
    }

    /**
     * Finds if a trigger word is in the text, sends notifications
     * @param text = string to be searched
     */
    public static void filterText(String text, Activity act, Context context)
    {
        // format string
        String string = cleanString(text);
        if (string.contains("spit"))
        {
            try {
                InputStream inputStream = context.openFileInput("textlog.txt");

                if ( inputStream != null ) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    Log.d("textlog", "");
                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        Log.d("textlog", receiveString);
                    }

                    inputStream.close();
                }
            }
            catch (FileNotFoundException e) {
                Log.e("login activity", "File not found: " + e.toString());
            } catch (Exception e) {
                Log.e("login activity", "Can not read file: " + e.toString());
            }
        }

        /*for (int i = 0; i < filterDict.size(); i++)
        {
            if (string.contains(filterDict.get(i)))
            {
                // send notifications
                new SendMailTask(act).execute("stressrepellenttest@gmail.com", "7knJCUgiao6X9eCK4q",
                        new ArrayList<String>(Arrays.asList("turfandturf17@gmail.com")),
                        "Stress Repellent Test", "This is a test of stress repellent.");

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("8049292343", null,
                        "|Alert| " + null + ", in conversation with StressRepelant, said: \"" + 
                                text +"\" You may want to consider checking on them or telling someone who could help.", null, null);
                break;
            }
        }*/
    }

    /**
     * Counts how many phrases indicate the user agrees with a question
     * @param text = user response
     * @return number of positive phrases
     */
    public static int checkUnsure(String text)
    {
        // format string
        String string = cleanString(text);
        int count = 0;

        // dictionary could become very large, brainstorm preprocessing that can reduce number of loops
        for (int i = 0; i < unsureDict.size(); i++)
        {
            if (string.contains(positiveDict.get(i)))    // found phrase with no adjective
                count++;
        }

        return count;
    }

    /**
     * Counts how many phrases indicate the user agrees with a question
     * @param text = user response
     * @return number of positive phrases
     */
    public static int countPositive(String text)
    {
        // format string
        String string = cleanString(text);
        Log.d("pos clean", string);
        int count = 0;

        // dictionary could become very large, brainstorm preprocessing that can reduce number of loops
        for (int i = 0; i < positiveDict.size(); i++)
        {
            // if phrase includes an adjective
            if (positiveDict.get(i).contains("[")) {

                if (positiveDict.get(i).equals("[pos]"))
                {
                       for (String wrd : posAdjective)
                       {
                           if (text.contains(wrd))
                           {
                               count++;
                               break;
                           }
                       }
                }
                else {
                    // index before [ and index of first letter following ]
                    String first;
                    if (positiveDict.get(i).charAt(0) == '[')
                        first = "";
                    else
                        first = positiveDict.get(i).substring(0, positiveDict.get(i).indexOf("[") - 1);

                    String second = positiveDict.get(i).substring(positiveDict.get(i).indexOf("[") + 6, positiveDict.get(i).length());

                    if (string.contains(first) && string.contains(second)) {
                        String key = "";
                        try {
                            // gets adjective from string
                            key = string.substring(string.indexOf(first) + first.length() + 1, string.indexOf(second) - 1);
                        } catch (StringIndexOutOfBoundsException e) {
                            Log.d("pos", "did not find adj");
                        }

                        Log.d("run", "!" + key + "!");
                        // if adjective still indicates positive response
                        if (key.equals("") || posAdjective.contains(key))
                            count++;
                    }
                }
            }
            else if (string.contains(positiveDict.get(i))) {    // found phrase with no adjective
                //Log.d("pos", "match " + positiveDict.get(i));
                count++;
            }
        }

        Log.d("pos", String.valueOf(count));
        return count;
    }

    /**
     * Counts how many phrases indicate the user disagrees with a question
     * @param text = user response
     * @return number of negative phrases
     */
    // TODO have not updated to version 2 dictionary yet
    public static int countNegative(String text)
    {
        // format string
        String string = cleanString(text);
        //Log.d("neg clean", string);
        int count = 0;

        // dictionary could become very large, brainstorm preprocessing that can reduce number of loops
        for (int i = 0; i < negativeDict.size(); i++)
        {
            // if phrase includes an adjective
            if (negativeDict.get(i).contains("[")) {

                if (negativeDict.get(i).equals("[neg]")) {
                    for (String wrd : negAdjective) {
                        if (text.contains(wrd)) {
                            count++;
                            break;
                        }
                    }
                } else {
                    //Log.d("possum", negativeDict.get(i));
                    // index before [ and index of first letter following ]
                    String first;
                    if (negativeDict.get(i).charAt(0) == '[')
                        first = "";
                    else
                        first = negativeDict.get(i).substring(0, negativeDict.get(i).indexOf("[") - 1);

                    String second = negativeDict.get(i).substring(negativeDict.get(i).indexOf("[") + 6, negativeDict.get(i).length());

                    if (string.contains(first) && string.contains(second)) {
                        String key = "";
                        try {
                            // gets adjective from string
                            key = string.substring(string.indexOf(first) + first.length() + 1, string.indexOf(second) - 1);
                        } catch (StringIndexOutOfBoundsException e) {
                            Log.d("pos", "did not find adj");
                        }

                        Log.d("run", "!" + key + "!");
                        // if adjective still indicates negative response
                        if (key.equals("") || negAdjective.contains(key))
                            count++;
                    }
                }
            }
            else if (string.contains(negativeDict.get(i))) {    // found phrase with no adjective
                //Log.d("pos", "match " + positiveDict.get(i));
                count++;
            }
        }

        Log.d("neg", String.valueOf(count));
        return count;
    }
}
