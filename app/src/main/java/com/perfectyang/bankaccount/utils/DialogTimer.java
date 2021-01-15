package com.perfectyang.bankaccount.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.perfectyang.bankaccount.R;

public class DialogTimer extends Dialog implements View.OnClickListener {
    DatePicker datePicker;
    Button save_btn, cancel_btn;
    public interface OnEnsureListener{
        public void onEnsure(int year,String month,String day);
    }
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public DialogTimer(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time);
        cancel_btn = findViewById(R.id.cancel_btn);
        save_btn = findViewById(R.id.save_btn);
        datePicker = findViewById(R.id.date_timer);
        cancel_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.cancel_btn:
                cancel();  //取消对话框
                break;
            case R.id.save_btn:
                int year = datePicker.getYear();  //选择年份
                int month = datePicker.getMonth()+1;
                int dayOfMonth = datePicker.getDayOfMonth();
                String monthStr = String.valueOf(month);
                if (month<10){
                    monthStr = "0"+month;
                }
                String dayStr = String.valueOf(dayOfMonth);
                if (dayOfMonth<10){
                    dayStr="0"+dayOfMonth;
                }
                if (onEnsureListener!=null) {
                    onEnsureListener.onEnsure(year,monthStr,dayStr);
                }
                cancel();  //取消对话框
                break;
        }
    }

    /* 设置Dialog的尺寸和屏幕尺寸一致*/
    public void setDialogSize () {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int)(d.getWidth());  //对话框窗口为屏幕窗口
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
    }
}
