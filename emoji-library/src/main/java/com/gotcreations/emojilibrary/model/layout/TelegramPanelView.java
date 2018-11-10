package com.gotcreations.emojilibrary.model.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.gotcreations.emojilibrary.R;

/**
 * Created by edgar on 18/02/2016.
 */
public class TelegramPanelView extends LinearLayout {

    private static final String TAG = "EmojiKeyboardLayout";

    private CharSequence hintText = "";
    private int textColor;
    private int textColorHint;
    private int audioTextColor;
    private int audioIconColor;
    private int attachIconColor;
    private int sendIconColor;

    // CONSTRUCTORS
    public TelegramPanelView(Context context) {
        super(context);
        init(context, null);
    }

    public TelegramPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TelegramPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TelegramPanelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    // INITIALIZATIONS
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rsc_telegram_panel, this, true);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TelegramPanelView);

            hintText = a.getText(R.styleable.TelegramPanelView_hintText);
            textColor = a.getColor(R.styleable.TelegramPanelView_textColor, 0x000000);
            textColorHint = a.getColor(R.styleable.TelegramPanelView_textColorHint, 0x000000);
            audioTextColor = a.getColor(R.styleable.TelegramPanelView_audioTextColor, 0x000000);
            audioIconColor = a.getColor(R.styleable.TelegramPanelView_audioIconColor, 0x000000);
            attachIconColor = a.getColor(R.styleable.TelegramPanelView_attachIconColor, 0x000000);
            sendIconColor = a.getColor(R.styleable.TelegramPanelView_sendIconColor, 0x000000);

            a.recycle();
        }
    }

    public CharSequence getHintText() {
        return hintText;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextColorHint() {
        return textColorHint;
    }

    public int getAudioTextColor() {
        return audioTextColor;
    }

    public int getAudioIconColor() {
        return audioIconColor;
    }

    public int getAttachIconColor() {
        return attachIconColor;
    }

    public int getSendIconColor() {
        return sendIconColor;
    }
}
