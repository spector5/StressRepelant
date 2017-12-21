package rentedbanana.stressrepelant;

import android.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.EditText;
import android.widget.TableLayout;

import java.util.ArrayDeque;

/**
 * Class taken from an Android blog online; used to retain activity information
 * on transitions (i.e. orientation change)
 * Created by Daniel on 12/18/2017.
 */

public class TaskFragment extends Fragment {

    protected TableLayout tl;
    protected ArrayDeque<String> inputLog;
    protected boolean submitted;
    protected EditText textview;

    /**
     * Callback interface through which the fragment will report the
     * task's progress and results back to the Activity.
     */
    interface TaskCallbacks {
        /*void onPreExecute();
        void onProgressUpdate(int percent);
        void onCancelled();
        void onPostExecute();*/
    }

    private TaskCallbacks mCallbacks;

    /**
     * Hold a reference to the parent Activity so we can report the
     * task's current progress and results. The Android framework
     * will pass us a reference to the newly created Activity after
     * each configuration change.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (TaskCallbacks) activity;
    }

    /**
     * This method will only be called once when the retained
     * Fragment is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    /**
     * Set the callback to null so we don't accidentally leak the
     * Activity instance.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
