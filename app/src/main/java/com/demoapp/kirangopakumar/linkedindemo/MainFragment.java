package com.demoapp.kirangopakumar.linkedindemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainFragment extends Fragment {

    private ImageView profile_backarrow;

    //Empty Constructor
    public MainFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        View photoHeader = view.findViewById(R.id.photoHeader);
        profile_backarrow = view.findViewById(R.id.profile_backarrow);



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* For devices equal or higher than lollipop set the translation above everything else */
            photoHeader.setTranslationZ(6);
            /* Redraw the view to show the translation */
            photoHeader.invalidate();
        }

      profile_backarrow.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              getActivity().finish();
          }
      });





        return view;
    }

}
