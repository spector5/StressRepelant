package rentedbanana.stressrepelant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import rentedbanana.stressrepelant.database.LocalDB;
import rentedbanana.stressrepelant.database.User;

public class ProfilerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiler);

        Spinner age = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Select One", "18-21", "21-27", "27-35", "35-50", "50+"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        age.setAdapter(adapter);

        Spinner gender = (Spinner) findViewById(R.id.spinner2);
        items = new String[]{"Select One", "Male", "Female", "Other"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        gender.setAdapter(adapter);

        Spinner race = (Spinner) findViewById(R.id.spinner3);
        items = new String[]{"Select One", "Asian", "African American", "Caucasian", "Hispanic", "Native American", "Pacific Islander", "Other"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        race.setAdapter(adapter);
    }

    public void createAccount(View view)
    {
        Spinner age = (Spinner) findViewById(R.id.spinner);
        Spinner gender = (Spinner) findViewById(R.id.spinner2);
        Spinner race = (Spinner) findViewById(R.id.spinner3);
        String ageVal = age.getSelectedItem().toString();
        String genderVal = gender.getSelectedItem().toString();
        String raceVal = race.getSelectedItem().toString();

        if (ageVal.equals("Select One") || genderVal.equals("Select One") || raceVal.equals("Select One"))
            Toast.makeText(this, "Invalid Entry", Toast.LENGTH_LONG).show();
        else
        {
            String name = getIntent().getStringExtra("FIRST_NAME");
            String user = getIntent().getStringExtra("USERNAME");
            String pass = getIntent().getStringExtra("PASSWORD");
            LocalDB.openDB(this);
            LocalDB.addUser(new User(name, user, pass, ageVal, raceVal, genderVal));
            LocalDB.closeDB();
            Toast.makeText(this, "Created User", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

    }
}
