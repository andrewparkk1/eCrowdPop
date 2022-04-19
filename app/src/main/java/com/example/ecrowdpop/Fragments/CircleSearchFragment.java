package com.example.ecrowdpop.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecrowdpop.R;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ecrowdpop.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hendraanggrian.appcompat.socialview.Hashtag;
import com.hendraanggrian.appcompat.widget.HashtagArrayAdapter;
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;
import com.example.ecrowdpop.Adapter.TagAdapter;
import com.example.ecrowdpop.Adapter.UserAdapter;

import com.example.ecrowdpop.Model.HashTag;
import com.example.ecrowdpop.Model.User;
import com.example.ecrowdpop.R;

import java.util.ArrayList;
import java.util.List;



public class CircleSearchFragment extends Fragment {
    TextView ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle_search, container, false);

        ma = view.findViewById(R.id.marketingadvertisement);

        ViewGroup.LayoutParams layoutParams = ma.getLayoutParams();
        layoutParams.width = 500;
        layoutParams.height = 500;
        ma.setLayoutParams(layoutParams);


        ma.setOnClickListener(new View.OnClickListener() {
            long clicked = 0;

            @Override
            public void onClick(View v) {
                long mLastClickTime = 0;
                // mis-clicking prevention, using threshold of 1000 ms
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    clicked++;
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (clicked == 0) {
                    ma.setText("hola");
                } else {
                    ma.setText("asdf");
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }




}