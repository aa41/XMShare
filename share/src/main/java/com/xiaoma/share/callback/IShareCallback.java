package com.xiaoma.share.callback;

public interface IShareCallback {
    void shareSuccess();


    void shareError(int code, int message);


    void shareCancel();

}
