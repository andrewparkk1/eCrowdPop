package com.example.ecrowdpop.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecrowdpop.R;

import androidx.fragment.app.FragmentActivity;


public class CircleSearchFragment extends Fragment {
    TextView ma, re, sc, ct, ga, hc, md, as, mi, si, sm, ed, en, fd, f;
    Button all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle_search, container, false);

        ma = view.findViewById(R.id.marketingadvertisement);
        re = view.findViewById(R.id.retailecommerce);
        sc = view.findViewById(R.id.science);
        ct = view.findViewById(R.id.consumertechnology);
        ga = view.findViewById(R.id.games);
        hc = view.findViewById(R.id.healthcare);
        md = view.findViewById(R.id.media);
        as = view.findViewById(R.id.artstyle);
        mi = view.findViewById(R.id.manufacturingindustry);
        si = view.findViewById(R.id.socialimpact);
        sm = view.findViewById(R.id.socialmedia);
        ed = view.findViewById(R.id.education);
        en = view.findViewById(R.id.energy);
        fd = view.findViewById(R.id.fooddrink);
        f = view.findViewById(R.id.finance);
        all = view.findViewById(R.id.all);

//        randomShape(ma);
//        randomShape(re);
//        randomShape(sc);
//        randomShape(ct);
//        randomShape(ga);
//        randomShape(hc);
//        randomShape(md);
//        randomShape(as);
//        randomShape(mi);
//        randomShape(si);
//        randomShape(sm);
//        randomShape(ed);
//        randomShape(en);
//        randomShape(fd);
//        randomShape(f);

        click(ma, ma.getText().toString());
        click(re, re.getText().toString());
        click(sc, sc.getText().toString());
        click(ct, ct.getText().toString());
        click(ga, ga.getText().toString());
        click(hc, hc.getText().toString());
        click(md, md.getText().toString());
        click(as, as.getText().toString());
        click(mi, mi.getText().toString());
        click(si, si.getText().toString());
        click(sm, sm.getText().toString());
        click(ed, ed.getText().toString());
        click(en, en.getText().toString());
        click(fd, fd.getText().toString());
        click(f, f.getText().toString());
        click(all, "");


        // Inflate the layout for this fragment
        return view;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public void randomShape(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        double d = ((Math.random() * (500 - 100)) + 100);
        layoutParams.width = (int) d;
        layoutParams.height = (int) d;
        view.setLayoutParams(layoutParams);
    }

    public void click(View view, String text) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getContext().getSharedPreferences("PREFS" , Context.MODE_PRIVATE).edit();
                editor.putString("category", text);
                editor.apply();
                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
            }
        });
    }
}