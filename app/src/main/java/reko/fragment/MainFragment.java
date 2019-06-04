package reko.fragment;

import processing.android.PFragment;
import reko.MainActivity;

/**
 * The MainFragment class defines the methods that are common to all the other fragments used in Reko project.
 *
 * All fragments have a boolean variable that allow to know if one of they is in use or it was been
 * replaced with another fragment and then is not more active.
 *
 * Moreover, all the fragments have an instance of the main activity, this allows the single
 * fragment to have a complete map of all the other fragments, thus allowing an easy navigation
 * between them.
 *
 */
public class MainFragment extends PFragment {

    /**
     * This boolean value is set to true if the Fragment is active.
     */
    private boolean active;

    /**
     * Object of the MainActivity class.
     */
    protected MainActivity mainActivity;

    /**
     * Sets the instance of the MainActivity's object
     * @param mainActivity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Sets value of active variable
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the value of active variable
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }


}
