package rentedbanana.stressrepelant;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        tl=(TableLayout)findViewById(R.id.textTable);
        button = (Button)findViewById(R.id.button);
    }

    /**
     * Makes a new text for the user to fill
     * @param v = view of button pressed
     */
    public void newText(View v)
    {
        // TODO need to change what the button does (needs to send)
        // not sure how to switch it back, might be able to use bool flag
        button.setText("Send");
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        EditText textview = new EditText(this);
        //textview.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        textview.setWidth((Resources.getSystem().getDisplayMetrics().widthPixels / 3) * 2);
        //InputFilter[] filters = new InputFilter[1];
        //filters[0] = new InputFilter.LengthFilter(10);
        //textview .setFilters(filters);
        textview.setBackgroundResource(R.color.userText);
        textview.setMaxLines(10);
        textview.setText("");
        textview.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);

        textview.requestFocus();
        tr.addView(textview);
        tl.addView(tr);

        textview.setFocusableInTouchMode(true);
        Log.d("text", textview.toString());
        showSoftKeyboard(textview);
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
