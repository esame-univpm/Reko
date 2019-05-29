package reko.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        viewsFragment = new ViewsFragment();
        viewsFragment.setMainActivity(this);

        //starts StartFragment
        getSupportFragmentManager().beginTransaction().attach(startFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(startFragment.isActive()){
            this.finish();
        }
    }

    public StartFragment getStartFragment() {
        return startFragment;
    }

    public ViewsFragment getViewsFragment(){
        return viewsFragment;
    }
}
