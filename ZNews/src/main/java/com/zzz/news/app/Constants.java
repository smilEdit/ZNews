package com.zzz.news.app;

import java.io.File;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 10:04
 */

public class Constants {

    /**
     * KEY
     */

    public static final String JUHE_JOKE_KEY = "189df93e385f308c4ca8c71c4bc7fbae";
    public static final String JUHE_NEWS_KEY = "bd8e0a7114136f806abe023bfd16d4d6";
    public static final String JUHE_ROBOT_KEY = "647657f24cb2146d82a0663404bc0d16";
    public static final String JUHE_WEIXIN_KEY = "4d1699d5395753d462ed71a96d1aea8c";
    public static final String  JUHE_LISHI_KEY = "08edba274089920b2b605630e7240faf";

    /**
     * PATH
     */


    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
