package com.example.dawa1.thirty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreFragment extends Fragment {

    private final String TOTAL_SCORE= "mTotalScore";
    private final String SCORE_BOARD = "mScoreHolder";

    private TextView mTotalScoreText;
    private Button mNewGameButton;

    private ListView mScoreHolder;
    private ArrayList<Integer> mScoreList;

    private int mTotalScore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        // Not needed since all important values are gathered each reset
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_score, container, false);
        setScoreList(v);
        setTotalScore(v);
        setButtons(v);

        return v;
    }

    private void setButtons(View v) {
        mNewGameButton = (Button) v.findViewById(R.id.new_game_button);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setScoreList(View v) {
        mScoreHolder = (ListView) v.findViewById(R.id.score_list);
        mScoreList = getActivity().getIntent().getIntegerArrayListExtra("mScoreList");

        String[] scoreList = getResources().getStringArray(R.array.spinner_selections);

        for (int i = 0; i < 10; i++) {
            scoreList[i] += (" " + mScoreList.get(i));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), R.layout.spinner_list_item, scoreList);

        mScoreHolder.setAdapter(adapter);
    }

    private void setTotalScore(View v) {
        mTotalScoreText = (TextView) v.findViewById(R.id.total_score_tV);
        mTotalScoreText.setText("Total Score: " + calcTotalScore());
    }

    public int calcTotalScore() {
        int totalScore = 0;

        for (int i = 0; i < mScoreList.size(); i++) {
            totalScore += mScoreList.get(i);
        }

        return totalScore;
    }
}
