package com.jcja.fountain_wishes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.jcja.fountain_wishes.app.NetStatus;


public class Notnet extends Fragment {
    public Notnet(){

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootnotred = inflater.inflate(R.layout.nonet, container, false);

        // Comprobar si hay internet
        if (NetStatus.isNetworkAvailable(requireContext())){

        }
        return rootnotred;
    }
}
