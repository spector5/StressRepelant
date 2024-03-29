package rentedbanana.stressrepelant;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class TextActivity extends AppCompatActivity implements TaskFragment.TaskCallbacks {

    private static final String TAG_TASK_FRAGMENT = "task_fragment";
    private TaskFragment mTaskFragment;

    private final int MAX_LINES = 15;

    private TableLayout tl;
    private Button button;
    private Button send;
    private EditText textview = null;
    private Context context;
    private boolean submitted = true;
    private String response;
    private ArrayDeque<String> inputLog;
    private int state;  // 0 = initial, 1 = finding what condition to check for, 2 = checking condition
    private int condition;  // 1 = seperation anxiety, 2 = selective mutism, 3 = specific phobia,
    // 4 = social anxiety, 5 = panic disorder, 6 = agoraphobia, 7 = general stress, no flowchart just general information
    private int questionNum;
    private ArrayList<String> condQuestions = new ArrayList<String>(Arrays.asList(SeparationAnxiety.getStarter(),
            PotentialSelectiveMutism.getStarter(), SpecificPhobia.getStarter(), SocialAnxiety.getStarter(),
            PanicDisorder.getStarter(), Agoraphobia.getStarter(), Stress.getStarter()));
    private Condition cond;
    private Activity act;
    private int questionBound;
    private ArrayList<Integer> questionsNums;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        context = this;

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("textlog.txt", Context.MODE_APPEND));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            outputStreamWriter.write(dateFormat.format(date) + "\n");
            outputStreamWriter.close();
        }
        catch (Exception e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        questionsNums = new ArrayList<>();
        rand = new Random();
        state = 0;
        condition = 0;
        questionNum = 0;
        act = this;

        FragmentManager fm = getFragmentManager();
        mTaskFragment = (TaskFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        tl=(TableLayout)findViewById(R.id.textTable);
        button = (Button)findViewById(R.id.button);
        send = (Button)findViewById(R.id.sendText);
        send.setVisibility(View.INVISIBLE);

        if (mTaskFragment == null) {
            mTaskFragment = new TaskFragment();
            fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
            inputLog = new ArrayDeque<String>();
            mTaskFragment.inputLog = inputLog;
        } else {
            while (mTaskFragment.tl.getChildCount() > 0) {
                View row = mTaskFragment.tl.getChildAt(0);
                mTaskFragment.tl.removeView(row);
                int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                Log.d("screenWidth", Integer.toString(screenWidth));
                ((TextView)(((TableRow)(row)).getChildAt(0))).setWidth((screenWidth / 3) * 2);
                TableRow.LayoutParams lp = new TableRow.LayoutParams();
                if (((TextView)(((TableRow)(row)).getChildAt(0))).getLeft() == 0) {
                    lp.setMargins(0, 0, screenWidth - (76 + (screenWidth / 3) * 2), 40);
                } else {
                    lp.setMargins(screenWidth - (76 + (screenWidth / 3) * 2), 0, 0, 40);
                }
                ((TextView)(((TableRow)(row)).getChildAt(0))).setLayoutParams(lp);
                tl.addView(row);
            }
            inputLog = mTaskFragment.inputLog;
            textview = mTaskFragment.textview;
            submitted = mTaskFragment.submitted;
            Log.d("submitted", submitted ? "true": "false");
            if (!submitted) {
                button.setVisibility(View.INVISIBLE);
                send.setVisibility(View.VISIBLE);
            }
        }
        mTaskFragment.tl = tl;

        new Thread(new Runnable() {
            public void run() {
                // TODO this is where computer response goes
                //int count = Dictionary.countPositive(inputLog.peek());
                response = "Hello " + getIntent().getStringExtra("FIRST_NAME") + ", are you feeling stressed today?";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TableRow tr = new TableRow(context);
                        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        TextView textview = new TextView(context);
                        textview.setGravity(Gravity.LEFT);
                        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                        textview.setWidth((screenWidth / 3) * 2);

                        textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_comp));
                        textview.setMaxLines(MAX_LINES);
                        textview.setText(response);
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("comp:" + response + "\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        //textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

                        //textview.requestFocus();
                        TableRow.LayoutParams lp = new TableRow.LayoutParams();
                        lp.setMargins(screenWidth - (76 + (screenWidth / 3) * 2), 0, 0, 40);
                        tr.addView(textview, lp);
                        tl.addView(tr);
                    }
                }); // end runOnUIThread
            }   // end thread run method
        }).start();
    }

    /**
     * Makes a new text for the user to fill
     * @param v = view of button pressed
     */
    public void newText(View v)
    {
        Log.d("new text", "clicked new text");
        button.setVisibility(View.INVISIBLE);
        send.setVisibility(View.VISIBLE);

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        textview = new EditText(this);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        textview.setWidth((screenWidth / 3) * 2);

        textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_user));
        textview.setMaxLines(MAX_LINES);
        textview.setText("");
        textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

        textview.requestFocus();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.setMargins(0, 0, screenWidth - (76 + (screenWidth / 3) * 2), 40);
        tr.addView(textview, lp);
        tl.addView(tr);

        textview.setFocusableInTouchMode(true);
        Log.d("text", textview.toString());
        showSoftKeyboard(textview);

        submitted = false;
    }

    public void sendText(View v)
    {
        Log.d("send text", "clicked send text...");
        send.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

        hideSoftKeyboard(textview);
        textview.setEnabled(false);
        textview.setTextColor(getResources().getColor(R.color.black));

        //Dictionary.countPositive(textview.getText().toString());
        inputLog.push(textview.getText().toString());
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("textlog.txt", Context.MODE_APPEND));
            outputStreamWriter.write("user:" + textview.getText().toString() + "\n");
            outputStreamWriter.close();
        }
        catch (Exception e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        textview = null;

        submitted = true;

        new Thread(new Runnable() {
            public void run() {
                // TODO this is where computer response goes
                //int count = Dictionary.countPositive(inputLog.peek());
                //response = "Debug: user input contained " + count + " positive indicators.";
                //response = "";    TODO i dont remember why this was added, but I took it out to make the new version of sendAnswer work

                if (state == 6)
                {
                    Dictionary.filterText(inputLog.getFirst(), act, context);
                    if (Dictionary.countPositive(inputLog.getFirst()) > 0)
                    {
                        // find resources
                        response = "Continue Conversations";
                        //state = 1;
                        state = 0;
                        condition++;
                    }
                }
                // runs at startup
                if (state == 0)
                {
                    // user indicates they have a problem
                    Dictionary.filterText(inputLog.getFirst(), act, context);
                    if (Dictionary.countPositive(inputLog.getFirst()) > Dictionary.countNegative(inputLog.getFirst()))
                    {
                        state++;
                        condition++;
                        response = "Ok, let's explore this more. " + condQuestions.get(condition - 1);
                    }
                    else
                    {
                        response = "Ok then, I'll be here if you need anything.";
                    }
                }
                // runs when we know there is an issue
                else if (state == 1)
                {
                    // user indicates they have whatever condition was asked
                    Dictionary.filterText(inputLog.getFirst(), act, context);
                    if (Dictionary.countPositive(inputLog.getFirst()) > Dictionary.countNegative(inputLog.getFirst())) {
                        state++;
                        // switches on condition index to set cond to an instance of a condition
                        switch (condition) {
                            case 1:
                                cond = new SeparationAnxiety();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            case 2:
                                cond = new PotentialSelectiveMutism();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            case 3:
                                cond = new SpecificPhobia();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            case 4:
                                cond = new SocialAnxiety();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            case 5:
                                cond = new PanicDisorder();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            case 6:
                                cond = new Agoraphobia();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            case 7:
                                cond = new Stress();
                                questionBound = cond.getQuestionLength();
                                for (int i = 0; i < questionBound; i++)
                                    questionsNums.add(i);

                                questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                                response = cond.getQuestion(questionNum);
                                break;
                            default:
                                response = "Something went wrong, I don't think I can help you. Sorry.";
                                break;
                        }
                    }
                    else
                    {
                        try {
                            condition++;
                            response = condQuestions.get(condition - 1);
                        } catch (IndexOutOfBoundsException e) {
                            // runs out of conditions
                            response = "I'm not sure I know of anything else to check.";
                        }
                    }
                }
                // runs when condition is found
                else if (state == 2)
                {
                    try {
                        Log.d("state", "2");
                        // send answer to condition, get next question

                        cond.sendAnswer(response, inputLog.getFirst(), questionNum, act, context);
                        questionNum = questionsNums.remove(rand.nextInt(questionsNums.size()));
                        response = cond.getQuestion(questionNum);
                        /* (Dictionary.countPositive(inputLog.getFirst()) > Dictionary.countNegative(inputLog.getFirst()))
                        {
                            response = "You answered in agreement. " + cond.getQuestion(questionNum++);
                        }
                        else
                            response = "You answered in disagreement. " + cond.getQuestion(questionNum++);*/
                    } catch (IllegalArgumentException e) {
                        // end questioning and give advice
                        response = cond.makeResponse();
                        Log.d("null point", e.getMessage());
                        state = 5;
                        //state = 3;// TODO this was changed to make extra resrouces unreachable, this need to be fixed in the first update
                    }
                }
                else if (state == 4)
                {
                    Dictionary.filterText(inputLog.getFirst(), act, context);
                    if (Dictionary.countPositive(inputLog.getFirst()) > 0)
                    {
                        // find resources
                        response = getString(R.string.links) + getString(R.string.link1) + getString(R.string.link2) + getString(R.string.link3);
                        state = 5;
                    }
                    state = 5;
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TableRow tr = new TableRow(context);
                        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                        TextView textview = new TextView(context);
                        textview.setGravity(Gravity.LEFT);
                        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                        textview.setWidth((screenWidth / 3) * 2);

                        textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_comp));
                        textview.setMaxLines(MAX_LINES);
                        textview.setText(response);
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("textlog.txt", Context.MODE_APPEND));
                            outputStreamWriter.write("comp:" + response + "\n");
                            outputStreamWriter.close();
                        }
                        catch (Exception e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        //textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

                        //textview.requestFocus();
                        TableRow.LayoutParams lp = new TableRow.LayoutParams();
                        lp.setMargins(screenWidth - (76 + (screenWidth / 3) * 2), 0, 0, 40);
                        tr.addView(textview, lp);
                        tl.addView(tr);

                        if (state == 3)
                        {
                            tr = new TableRow(context);
                            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            textview = new TextView(context);
                            textview.setGravity(Gravity.LEFT);
                            screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                            textview.setWidth((screenWidth / 3) * 2);

                            textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_comp));
                            textview.setMaxLines(MAX_LINES);
                            textview.setText("Would you like me to find more information and resources for you?");
                            try {
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("textlog.txt", Context.MODE_APPEND));
                                outputStreamWriter.write("comp:" + response + "\n");
                                outputStreamWriter.close();
                            }
                            catch (Exception e) {
                                Log.e("Exception", "File write failed: " + e.toString());
                            }
                            //textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

                            //textview.requestFocus();
                            lp = new TableRow.LayoutParams();
                            lp.setMargins(screenWidth - (76 + (screenWidth / 3) * 2), 0, 0, 40);
                            tr.addView(textview, lp);
                            tl.addView(tr);
                            state++;
                        }

                        if (state == 5)
                        {
                            tr = new TableRow(context);
                            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                            textview = new TextView(context);
                            textview.setGravity(Gravity.LEFT);
                            screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                            textview.setWidth((screenWidth / 3) * 2);

                            textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_comp));
                            textview.setMaxLines(MAX_LINES);

                            textview.setText("Is there anything else you would like to talk about?");
                            try {
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("textlog.txt", Context.MODE_APPEND));
                                outputStreamWriter.write("comp:" + response + "\n");
                                outputStreamWriter.close();
                            }
                            catch (Exception e) {
                                Log.e("Exception", "File write failed: " + e.toString());
                            }
                            //textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

                            //textview.requestFocus();
                            lp = new TableRow.LayoutParams();
                            lp.setMargins(screenWidth - (76 + (screenWidth / 3) * 2), 0, 0, 40);
                            tr.addView(textview, lp);
                            tl.addView(tr);
                            state++;
                        }
                    }
                }); // end runOnUIThread
            }   // end thread run method
        }).start();
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view){
        Log.d("keyboard", view.toString());
        Log.d("keyboard", String.valueOf(view.requestFocus()));

        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
            Log.d("keyboard", "Finished Keyboard");
        }
        Log.d("keyboard", "finished show keyboard");
    }

    protected void onDestroy() {
        mTaskFragment.submitted = submitted;
        mTaskFragment.textview = textview;
        super.onDestroy();
    }

}
