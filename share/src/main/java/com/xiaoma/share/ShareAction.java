package com.xiaoma.share;

import android.app.Activity;

import com.xiaoma.share.callback.IShareCallback;

import java.lang.ref.WeakReference;

public class ShareAction {
    private final WeakReference<Activity> actRef;
    private IShareCallback callback;

    private ShareAction(WeakReference<Activity> actRef) {
        this.actRef = actRef;
    }

    public static class Builder {
        private final WeakReference<Activity> actRef;
        private IShareCallback callback;

        public Builder(Activity activity) {
            this.actRef = new WeakReference<>(activity);
        }

        public Builder callback(IShareCallback callback) {
            this.callback = callback;
            return this;
        }

        public ShareAction share() {
            ShareAction shareAction = new ShareAction(actRef);
            shareAction.callback = callback;


            return shareAction;

        }


    }
}
