package com.gotcreations.emojilibrary.controller.emoji_pages;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.gotcreations.emojilibrary.R;
import com.gotcreations.emojilibrary.adapter.EmojiAdapter;
import com.gotcreations.emojilibrary.controller.FragmentEmoji;
import com.gotcreations.emojilibrary.model.Emoji;
import com.gotcreations.emojilibrary.model.People;
import com.gotcreations.emojilibrary.util.Constants;

/**
 * Created by edgar on 18/02/2016.
 */
public class FragmentEmojiPeople extends FragmentEmoji {

    public static final String TAG = "FragmentEmojiPeople";

    private View mRootView;
    private Emoji[] mData;
    private boolean mUseSystemDefault = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.frag_emoji_people, container, false);
        return this.mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView gridView = (GridView) view.findViewById(R.id.Emoji_GridView);
        Bundle bundle = getArguments();

        if (bundle == null) {
            this.mData = People.DATA;
            this.mUseSystemDefault = false;
        } else {
            Parcelable[] parcels = bundle.getParcelableArray(Constants.EMOJI_KEY);
            this.mData = new Emoji[parcels.length];

            for (int i = 0; i < parcels.length; i++) {
                this.mData[i] = (Emoji) parcels[i];
            }

            this.mUseSystemDefault = bundle.getBoolean(Constants.USE_SYSTEM_DEFAULT_KEY);
        }
        gridView.setAdapter(new EmojiAdapter(view.getContext(), this.mData, this.mUseSystemDefault));
        gridView.setOnItemClickListener(this);
    }
}
