package reko;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import reko.fragment.StartFragment;
import reko.fragment.ViewsFragment;

/**
 * The MainActivity class control the Reko's activity. It contains a Fragment HashMap
 */
public class MainActivity extends AppCompatActivity {

    private ViewsFragment viewsFragment;    //instance of ViewProcessing

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets StartFragment
        StartFragment startFragment = new StartFragment();
        startFragment.setMainActivity(this);

        //sets ViewsFragment
        viewsFragment = new ViewsFragment();
        viewsFragment.setMainActivity(this);

        //start StartFragment
        getSupportFragmentManager().beginTransaction().attach(startFragment).commit();

    }

    @Override
    public void onBackPressed() {
        switch (viewsFragment.getViewsController().getNumberView()){
            case 0:{
                this.finish();
                break;
            }
            case 1:{
                viewsFragment.getViewsController().setNumberView(-4);   //animation LabelRequest-Home
                break;
            }
            case 2:{
                viewsFragment.getViewsController().setNumberView(-5);   //animation FaceRequest-Home
                break;
            }
            case 3:{
                viewsFragment.getViewsController().setNumberView(-6);   //animation TextRequest-Home
                break;
            }
            case 6:{
                viewsFragment.getViewsController().setNumberView(-6);   //animation TextRequest-Home
                break;
            }
            case 9:{
                viewsFragment.getViewsController().setNumberView(-6);   //animation TextRequest-Home
                break;
            }
        }
    }

    /**
     * Returns the ViewsFragment object
     * @return
     */
    public ViewsFragment getViewsFragment() {
        return viewsFragment;
    }
}
