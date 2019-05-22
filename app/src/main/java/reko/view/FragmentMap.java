package reko.view;


import processing.android.PFragment;

public class FragmentMap {
   private PFragment fragment;
   private boolean active;

   FragmentMap (PFragment fragment, boolean active){
       this.fragment = fragment;
       this.active = active;
   }

    public boolean isActive() {
        return active;
    }

    public PFragment getFragment() {
        return fragment;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
