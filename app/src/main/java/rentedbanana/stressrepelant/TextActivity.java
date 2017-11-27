package rentedbanana.stressrepelant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    private TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        tl=(TableLayout)findViewById(R.id.textTable);
    }

    /**
     * Makes a new text for the user to fill
     * @param v = view of button pressed
     */
    public void newText(View v)
    {
        // TODO this is a test, need to build computer response
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TextView textview = new TextView(this);
        textview.setText("this is a test");
        tr.addView(textview);
        tl.addView(tr);
    }

}
