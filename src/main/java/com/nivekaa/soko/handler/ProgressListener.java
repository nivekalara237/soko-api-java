package com.nivekaa.soko.handler;

/**
 * @author nivekaa
 * Created 27/03/2020 at 23:59
 * Class com.nivekaa.soko.handler.ProgressListener
 */

public interface ProgressListener {
    void transferred(long transfered);
    interface Callback{
        void progress(float percent);
    }
}
