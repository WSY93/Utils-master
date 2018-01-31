package com.example.wsy.utils.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wsy.utils.Adapter.MyStackAdapter;
import com.example.wsy.utils.R;
import com.loopeer.cardstack.CardStackView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCardStackFragment extends Fragment implements CardStackView.ItemExpendListener {
    public static Integer[] TEST_DATAS = new Integer[]{R.color.darkorange,R.color.maroon,R.color.darkgray,R.color.darkslateblue,
            R.color.bisque,R.color.steelblue,R.color.chartreuse,R.color.darkblue};

    private CardStackView mStackView;
    private MyStackAdapter myStackAdapter;

    public MyCardStackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_card_stack, container, false);
        mStackView = (CardStackView) view.findViewById(R.id.stackview);
        mStackView.setItemExpendListener(this);
        myStackAdapter = new MyStackAdapter(getContext());
        mStackView.setAdapter(myStackAdapter);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        myStackAdapter.updateData(Arrays.asList(TEST_DATAS));
                    }
                }
                , 200
        );
        return  view;
    }

    @Override
    public void onItemExpend(boolean expend) {

    }
}
