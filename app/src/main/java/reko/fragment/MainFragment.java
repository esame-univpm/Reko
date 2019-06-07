package reko.fragment;

import processing.android.PFragment;
import reko.MainActivity;

/**
 * The MainFragment class defines the methods that are common to both fragments used in Reko project.
 *
 * The fragments have an instance of the main activity, this allows the single
 * fragment to have a complete map of all the other fragments, thus allowing an easy navigation
 * between them.
 */
public class MainFragment extends PFragment {

    protected MainActivity mainActivity;    //instance of the MainActivity

    public MainFragment(){
        super();
    }

    /**
     * Set method that allows fragments to have a MainActivity object.
     * @param mainActivity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity(){
        return mainActivity;
    }
}
