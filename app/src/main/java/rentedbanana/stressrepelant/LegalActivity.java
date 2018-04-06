package rentedbanana.stressrepelant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LegalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);

        TextView tv1 = (TextView) findViewById(R.id.legalView);
        tv1.setMovementMethod(new ScrollingMovementMethod());
    }

    public void goToProfile(View view)
    {
        Intent i = new Intent(this, ProfilerActivity.class);
        i.putExtra("USERNAME", getIntent().getStringExtra("USERNAME"));
        i.putExtra("PASSWORD", getIntent().getStringExtra("PASSWORD"));
        i.putExtra("FIRST_NAME", getIntent().getStringExtra("FIRST_NAME"));
        startActivity(i);
        finish();
    }
}
