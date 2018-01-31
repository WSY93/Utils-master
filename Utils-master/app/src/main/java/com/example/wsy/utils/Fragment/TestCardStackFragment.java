package com.example.wsy.utils.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wsy.utils.Adapter.TestStackAdapter;

import com.example.wsy.utils.R;
import com.loopeer.cardstack.CardStackView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestCardStackFragment extends Fragment implements CardStackView.ItemExpendListener {
    public static Integer[] TEST_DATAS = new Integer[]{R.color.aliceblue,R.color.antiquewhite,R.color.aqua,R.color.aquamarine,
            R.color.bisque,R.color.steelblue,R.color.chartreuse,R.color.darkblue};
    private CardStackView mStackView;
    private TestStackAdapter mTestStackAdapter;

    public TestCardStackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_card_stack, container, false);
        mStackView = (CardStackView) view.findViewById(R.id.stackview);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(getContext());
        mStackView.setAdapter(mTestStackAdapter);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
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
