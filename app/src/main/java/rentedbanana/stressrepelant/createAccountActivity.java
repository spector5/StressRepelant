package rentedbanana.stressrepelant;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rentedbanana.stressrepelant.database.LocalDB.DatabaseHelper;
import rentedbanana.stressrepelant.database.LocalDB;
import rentedbanana.stressrepelant.database.User;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class createAccountActivity extends AppCompatActivity {

    private static SQLiteDatabase db;
    private static LocalDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void createAccount(View view) {
        //save the logged info someday will be sent to database
        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        EditText userField = (EditText) findViewById(R.id.createUsernameField);
        String user = userField.getText().toString();

        EditText passField = (EditText) findViewById(R.id.createPasswordField);
        String pass = passField.getText().toString();

        /*SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE);
        sharedPref.edit().putString(getString(R.string.ask_name), name).apply();
        sharedPref.edit().putString(getString(R.string.ask_username), user).apply();
        sharedPref.edit().putString(getString(R.string.ask_password), pass).apply();*/

        LocalDB.openDB(this);
        if (LocalDB.nameExists(user))
            Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show();
        else
        {
            //Toast.makeText(this, "Created user", Toast.LENGTH_LONG).show();
            LocalDB.closeDB();

            Intent i = new Intent(this, LegalActivity.class);
            i.putExtra("USERNAME", user);
            i.putExtra("PASSWORD", pass);
            i.putExtra("FIRST_NAME", name);
            startActivity(i);
            finish();
        }
        LocalDB.closeDB();
    }
}

