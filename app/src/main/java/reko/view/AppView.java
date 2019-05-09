package reko.view;

public interface AppView {

    //declare in this method the things that must to be initialize before show the view
    void init();

    //this method groups all the things that will be shown
    void show();

}
