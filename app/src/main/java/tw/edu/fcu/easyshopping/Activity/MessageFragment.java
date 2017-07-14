package tw.edu.fcu.easyshopping.Activity;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.edu.fcu.easyshopping.R;

public class MessageFragment extends Fragment {


    public MessageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_message, container, false);

        return viewGroup;
    }

}
