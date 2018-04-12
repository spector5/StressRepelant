package rentedbanana.stressrepelant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import rentedbanana.stressrepelant.database.LocalDB;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToCreateAccount(View view) {
        Intent i = new Intent(this, createAccountActivity.class);
        startActivity(i);
    }

    public void goToHomePage(View view) {
        //SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE);
        //TODO get the account info from server or umcomment if we drop the server to check it
        EditText usernameField = (EditText) findViewById(R.id.usernameLoginField);
        String username = usernameField.getText().toString();

        EditText passwordField = (EditText) findViewById(R.id.passwordLoginField);
        String password = passwordField.getText().toString();

        boolean passCorrect = false;
        boolean userCorrect = false;
        //TODO check is password is correct and change that back to false above
        /*if(sharedPref.getString(getString(R.string.ask_password), "Doesn't exist").equals(password)) {
            passCorrect = true;
        }*/
        LocalDB.openDB(this);
        if (LocalDB.getUser(username) == null)
            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
        else if (LocalDB.getPass(username) == null) {
            Toast.makeText(this, "Password incorrect", Toast.LENGTH_LONG).show();
            Log.d("login stuff.........", "no pass");
        }else if (!LocalDB.getPass(username).equals(password)) {
            Toast.makeText(this, "Password incorrect", Toast.LENGTH_LONG).show();
            Log.d("login stuff.........", password + " did not match " + LocalDB.getPass(username));
        }else
        {
            userCorrect = true;
            passCorrect = true;
        }
        LocalDB.closeDB();

        if(!passCorrect) {
            passwordField.setError(getString(R.string.incorrect_password));
            passwordField.requestFocus();
        }

        //TODO check if username is correct and change that back to false above
        /*if(sharedPref.getString(getString(R.string.ask_username), "Doesn't exist").equals(username)) {
            userCorrect = true;
        }*/

        if(!userCorrect) {
            usernameField.setError(getString(R.string.incorrect_username));
            usernameField.requestFocus();
        }
        if(userCorrect && passCorrect) {
            Intent i = new Intent(this, TextActivity.class);
            LocalDB.openDB(this);
            i.putExtra("FIRST_NAME", LocalDB.getFirstName(username));
            LocalDB.closeDB();
            startActivity(i);
            finish();
        }
    }

    public void stayLoggedIn(View view) {

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE);

        CheckBox v = (CheckBox) findViewById(R.id.stayLoggedInCheckBox);
        if(v.isChecked()) {
            sharedPref.edit().putBoolean(getString(R.string.stay_logged_in), true).apply();
        }
        else {
            sharedPref.edit().putBoolean(getString(R.string.stay_logged_in), false).apply();
        }
    }
}
