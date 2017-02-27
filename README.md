##ZNews


学习新技术  MVP + Retrofit + RxJava + Dagger2  + Reaml

 出于学习目的做的阅读App，采用的各个框架都是目前Android开发比较新比较常用的。（框架搭建方面学习了LookLook，GeekNews开源应用）
 
 Api获取来源于聚合数据(前几天推出会员制,没有免费了好像)，知乎日报，干货集中营。
 目前只是实现了基本的阅读功能，还存在一些Bug。
 
 用了许多开源的三方的，像下拉小游戏，Yalantis的ContextMenu，比较炫酷的Depth-LIB-Android。
 之后会添加缓存数据清除，夜间模式，收藏夹等基本功能。

##预览

![](https://github.com/smilEdit/EShare/blob/master/screenshots/aboutme.gif) &nbsp;&nbsp;&nbsp;
![](https://github.com/smilEdit/EShare/blob/master/screenshots/tulin.gif) &nbsp;&nbsp;&nbsp;
![](https://github.com/smilEdit/EShare/blob/master/screenshots/youxi.gif)


##三方库

####UI
* compile 'com.android.support:appcompat-v7:24.2.0'
* compile 'com.android.support:design:24.2.0'
* compile 'com.android.support:recyclerview-v7:24.2.0'
* compile 'com.android.support:cardview-v7:24.2.0'

####RX
* [compile 'io.reactivex:rxjava:1.1.0'](https://github.com/ReactiveX/RxJava)
* [compile 'io.reactivex:rxandroid:1.1.0'](https://github.com/ReactiveX/RxAndroid)
* [compile 'com.jakewharton.rxbinding:rxbinding:0.4.0'](https://github.com/JakeWharton/RxBinding)

####NET
* [compile 'com.google.code.gson:gson:2.4'](https://github.com/google/gson)
* [compile 'com.squareup.retrofit2:retrofit:2.0.2'](https://github.com/square/retrofit)
* [compile 'com.squareup.retrofit2:converter-gson:2.0.2'](https://github.com/google/gson)
* [compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'](https://github.com/ReactiveX/RxJava)
* [compile 'com.squareup.okhttp3:okhttp:3.2.0'](https://github.com/square/okhttp)
* [compile 'com.squareup.okhttp3:logging-interceptor:3.0.1'](https://github.com/square/okhttp)
* [compile 'com.github.bumptech.glide:glide:3.7.0'](https://github.com/bumptech/glide)
* [compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'](https://github.com/square/okhttp)

####WIDGET
* [compile 'com.prolificinteractive:material-calendarview:1.4.0'](https://github.com/prolificinteractive/material-calendarview)
* [compile 'net.opacapp:multiline-collapsingtoolbar:1.2.2'](https://github.com/opacapp/multiline-collapsingtoolbar)
* [compile 'com.github.chrisbanes:PhotoView:1.3.0'](https://github.com/chrisbanes/PhotoView)
* [compile 'com.android.support.constraint:constraint-layout:1.0.0-alpha1'](https://github.com/googlecodelabs/constraint-layout)
* [compile 'com.yalantis:contextmenu:1.0.7'](https://github.com/Yalantis/Context-Menu.Android)
* [compile 'com.melnykov:floatingactionbutton:1.3.0'](https://github.com/makovkastar/FloatingActionButton)
* [compile 'tyrantgit:explosionfield:1.0.1'](https://github.com/tyrantgit/ExplosionField)
* [compile 'com.github.elevenetc:textsurface:0.9.1'](https://github.com/elevenetc/TextSurface)
* [compile 'de.hdodenhof:circleimageview:1.3.0'](https://github.com/hdodenhof/CircleImageView)

####[CANARY](https://github.com/square/leakcanary)
* debugCompile 'com.github.moduth:blockcanary-android:1.2.1'
* releaseCompile 'com.github.moduth:blockcanary-no-op:1.2.1'
* debugCompile 'com.squareup.leakcanary:leakcanary-android:1.4-beta2'
* releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.4-beta2'

####DI
* [compile 'com.google.dagger:dagger:2.0.2'](https://github.com/google/dagger)
* [compile 'com.google.dagger:dagger-compiler:2.0.2'](https://github.com/google/dagger)
* [compile 'com.jakewharton:butterknife:8.2.1'](https://github.com/JakeWharton/butterknife)
* [apt 'com.jakewharton:butterknife-compiler:8.2.1'](https://github.com/JakeWharton/butterknife)
* provided 'org.glassfish:javax.annotation:10.0-b28'

####OTHER
* [compile 'com.orhanobut:logger:1.15'](https://github.com/orhanobut/logger)
* [compile 'com.zhy:base-rvadapter:3.0.3'](https://github.com/hongyangAndroid/baseAdapter)
* [compile 'me.yokeyword:fragmentation:0.7.9'](https://github.com/YoKeyword/Fragmentation)

