package com.perfectyang.bankaccount.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class CopyPaste {
    ClipboardManager clipboard;
    Context context;
    public void setCopy (String content) {
        if (clipboard == null) {
            clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
        //创建ClipData对象
        //第一个参数只是一个标记，随便传入。
        //第二个参数是要复制到剪贴版的内容
        ClipData clip = ClipData.newPlainText(content, content);
        //传入clipdata对象.
        clipboard.setPrimaryClip(clip);
    }
}
