package com.zzz.news.widegt;

import android.os.SystemClock;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 9:22
 */

public class FrameRateCounter {
    private static long mLastTime;

    public static float timeStep() {
        final long time = SystemClock.uptimeMillis();
        final long timeDelta = time - mLastTime;
        float timeDeltaSeconds = mLastTime > 0.0f ? timeDelta / 1000.0f : 0.0f;
        mLastTime = time;
        return Math.min(0.021f, timeDeltaSeconds);
    }
}
