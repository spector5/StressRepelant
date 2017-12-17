package rentedbanana.stressrepelant;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class TextActivity extends AppCompatActivity {

    private TableLayout tl;
    private Button button;
    private Button send;
    private EditText textview = null;
    private boolean submitted;
    private Context context;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        tl=(TableLayout)findViewById(R.id.textTable);
        button = (Button)findViewById(R.id.button);
        send = (Button)findViewById(R.id.sendText);
        send.setVisibility(View.INVISIBLE);
        submitted = false;
        context = this.context;

        // commented out because this isnt the proper way to update screen
        // TODO but, this is where we can process the computer response, its just an issue with updating the screen
        /*new Thread(new Runnable() {
            public void run() {
                while(true)
                {
                    if (submitted)
                    {
                        // TODO this is where computer response goes
                        response = "This is the computer response.";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TableRow tr = new TableRow(context);
                                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT));
                                TextView textview = new TextView(context);
                                textview.setGravity(Gravity.LEFT);
                                textview.setWidth((Resources.getSystem().getDisplayMetrics().widthPixels / 3) * 2);

                                textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_comp));
                                textview.setMaxLines(10);
                                textview.setText(response);
                                //textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

                                //textview.requestFocus();
                                tr.addView(textview);
                                tl.addView(tr);;
                            }
                        }); // end runOnUIThread
                    }
                }   // end while loop
            }   // end thread run method
        }).start();*/
    }

    /**
     * Makes a new text for the user to fill
     * @param v = view of button pressed
     */
    public void newText(View v)
    {
        // TODO need to change what the button does (needs to send)
        // not sure how to switch it back, might be able to use bool flag
        //button.setText("Send");
        Log.d("new text", "clicked new text");
        button.setVisibility(View.INVISIBLE);
        send.setVisibility(View.VISIBLE);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        textview = new EditText(this);
        //textview.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        textview.setWidth((Resources.getSystem().getDisplayMetrics().widthPixels / 3) * 2);
        //InputFilter[] filters = new InputFilter[1];
        //filters[0] = new InputFilter.LengthFilter(10);
        //textview .setFilters(filters);
        textview.setBackground(getResources().getDrawable(R.drawable.rounded_edittext_user));
        textview.setMaxLines(10);
        textview.setText("");
        textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

        textview.requestFocus();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.setMargins(0, 0, 0, 40);
        tr.addView(textview, lp);
        tl.addView(tr);

        textview.setFocusableInTouchMode(true);
        Log.d("text", textview.toString());
        showSoftKeyboard(textview);
    }

    public void sendText(View v)
    {
        Log.d("send text", "clicked send text...");
        send.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

        hideSoftKeyboard(textview);
        textview.setEnabled(false);
        textview.setTextColor(getResources().getColor(R.color.black));
        Dictionary.countPositive(textview.getText().toString());
        textview = null;

        submitted = true;
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

}
