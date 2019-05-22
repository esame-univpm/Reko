package reko.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import processing.android.PFragment;

public class MainFragment extends PFragment {
   public void replaceFragment (PFragment oldFragment, PFragment newFragment, Bundle bundle){
       newFragment.setArguments(bundle);

       FragmentTransaction fragmentTransaction = oldFragment.getActivity().getSupportFragmentManager().beginTransaction();

       fragmentTransaction.replace(R.id.frameLayout, newFragment);

       fragmentTransaction.commit();

   }

}
