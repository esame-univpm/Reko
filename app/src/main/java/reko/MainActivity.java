package reko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import reko.fragment.StartFragment;
import reko.fragment.ViewsFragment;

public class MainActivity extends AppCompatActivity {

    //instance of various fragments
    private StartFragment startFragment;
    private ViewsFragment viewsFragment;

    //on create starts StartFragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creates fragments
        startFragment = new StartFragment();
        startFragment.setMainActivity(this);
        startFragment.setActive(true);

        viewsFragment = new ViewsFragment();
        viewsFragment.setMainActivity(this);
        viewsFragment.setActive(false);

        //starts StartFragment
        getSupportFragmentManager().beginTransaction().attach(startFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(startFragment.isActive() || (viewsFragment.isActive() && viewsFragment.getViewsProcessing().getNumberView()==0)){
            this.finish();
        }
        if(viewsFragment.isActive() && viewsFragment.getViewsProcessing().getNumberView() != 0){
            viewsFragment.getViewsProcessing().setBackPressed(true);
        }
    }

    public StartFragment getStartFragment() {
        return startFragment;
    }

    public ViewsFragment getViewsFragment() {
        return viewsFragment;
    }
}
