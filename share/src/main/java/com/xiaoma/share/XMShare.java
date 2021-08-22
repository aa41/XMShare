package com.xiaoma.share;

import android.app.Activity;

public class XMShare {


    public static ShareAction.Builder with(Activity activity) {
        return new ShareAction.Builder(activity);
    }





}
