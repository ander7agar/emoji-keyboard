package com.gotcreations.emojilibrary.controller;

import android.content.res.ColorStateList;
import android.os.Handler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.gotcreations.emojilibrary.R;
import com.gotcreations.emojilibrary.model.layout.AppPanelEventListener;
import com.gotcreations.emojilibrary.model.layout.EmojiCompatActivity;
import com.gotcreations.emojilibrary.model.layout.EmojiEditText;

/**
 * Created by edgar on 18/02/2016.
 */
public class WhatsAppPanel extends AppPanel {

    private static final String TAG = "WhatsAppPanel";

    private ImageView mEmojiButton;
    private int mButtonColor;

    // CONSTRUCTOR
    public WhatsAppPanel(EmojiCompatActivity activity, AppPanelEventListener listener, int color) {
        super(activity);
        this.mActivity = activity;
        this.mButtonColor = color;
        init();
        this.mEmojiKeyboard = new EmojiKeyboard(this.mActivity, this.mInput);
        this.mListener = listener;
    }

    public WhatsAppPanel(EmojiCompatActivity activity, int color) {
        this(activity, null, color);
    }

    // INITIALIZATION
    @Override
    protected void initBottomPanel() {
        this.mEmojiButton = (ImageView) mActivity.findViewById(R.id.emojiButton);
        mActivity.findViewById(R.id.emojiButtonWrapper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WhatsAppPanel.this.isEmojiKeyboardVisible) {
                    WhatsAppPanel.this.closeCurtain();
                    if (WhatsAppPanel.this.mInput.isSoftKeyboardVisible()) {
                        WhatsAppPanel.this.mEmojiButton.setImageResource(R.drawable.ic_keyboard_grey600_24dp);
                        WhatsAppPanel.this.mInput.hideSoftKeyboard();
                    } else {
                        WhatsAppPanel.this.mEmojiButton.setImageResource(R.drawable.input_emoji);
                        WhatsAppPanel.this.mInput.showSoftKeyboard();
                    }
                } else {
                    WhatsAppPanel.this.mEmojiButton.setImageResource(R.drawable.ic_keyboard_grey600_24dp);
                    WhatsAppPanel.this.closeCurtain();
                    WhatsAppPanel.this.showEmojiKeyboard(0);
                }
            }
        });
        FloatingActionButton mSend = (FloatingActionButton) mActivity.findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireOnSendClicked();
            }
        });
        mSend.setBackgroundTintList(ColorStateList.valueOf(mActivity.getResources().getColor(mButtonColor)));
        this.mCurtain = (LinearLayout) mActivity.findViewById(R.id.curtain);
    }

    @Override
    protected void setInputConfig() {
        this.mInput = (EmojiEditText) mActivity.findViewById(R.id.input);
        this.mInput.addOnSoftKeyboardListener(new EmojiEditText.OnSoftKeyboardListener() {
            @Override
            public void onSoftKeyboardDisplay() {
                if (!WhatsAppPanel.this.isEmojiKeyboardVisible) {
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.schedule(new Runnable() {
                        @Override
                        public void run() {
                            Handler mainHandler = new Handler(WhatsAppPanel.this.mActivity.getMainLooper());
                            Runnable myRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    WhatsAppPanel.this.openCurtain();
                                    WhatsAppPanel.this.showEmojiKeyboard(0);
                                }
                            };
                            mainHandler.post(myRunnable);
                        }
                    }, 150, TimeUnit.MILLISECONDS);
                }
            }

            @Override
            public void onSoftKeyboardHidden() {
                if (WhatsAppPanel.this.isEmojiKeyboardVisible) {
                    WhatsAppPanel.this.closeCurtain();
                    WhatsAppPanel.this.hideEmojiKeyboard(200);
                }
            }
        });
    }

    @Override
    public void hideEmojiKeyboard(int delay) {
        super.hideEmojiKeyboard(delay);
        WhatsAppPanel.this.mEmojiButton.setImageResource(R.drawable.input_emoji);
    }

    @Override
    public void showAudioPanel(boolean show) {

    }
}
