package com.example.dylan.manhunt;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * This class is a testing utility to display toast messages on screens I am debugging.
 *
 * @author Dylan
 * @version 12/10/15
 */
public class CustomOnItemSelectedListener implements OnItemSelectedListener {

    /**
     * Generic item list of things to make a toast appear.
     * @param parent - the parent object
     * @param view - view that is being clicked
     * @param pos - position of click
     * @param id - identification name of whatever you want
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    /**
     * In case nothing is selected and how to handle that
     *
     * @param arg0 - the argument in case nothing is selected.
     */
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}