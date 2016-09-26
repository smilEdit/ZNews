package com.zzz.news.util;

import com.zzz.news.model.http.ApiException;
import com.zzz.news.model.http.HttpResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 10:44
 */
public class ZRx {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<HttpResponse<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<HttpResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<HttpResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResponse<T> tHttpResponse) {
                        if(!tHttpResponse.getError()) {
                            ZLog.e("Transformer");
                            return createData(tHttpResponse.getResults());
                        } else {
                            ZLog.e("ApiException");
                            return Observable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> Observable.Transformer<GankHttpResponse<T>, T> handleResult() {   //compose判断结果
//        return new Observable.Transformer<GankHttpResponse<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<GankHttpResponse<T>> httpResponseObservable) {
//                return httpResponseObservable.flatMap(new Func1<GankHttpResponse<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(GankHttpResponse<T> tGankHttpResponse) {
//                        if(!tGankHttpResponse.getError()) {
//                            return createData(tGankHttpResponse.getResults());
//                        } else {
//                            return Observable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }
//
//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> Observable.Transformer<WXHttpResponse<T>, T> handleWXResult() {   //compose判断结果
//        return new Observable.Transformer<WXHttpResponse<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<WXHttpResponse<T>> httpResponseObservable) {
//                return httpResponseObservable.flatMap(new Func1<WXHttpResponse<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(WXHttpResponse<T> tWXHttpResponse) {
//                        if(tWXHttpResponse.getCode() == 200) {
//                            return createData(tWXHttpResponse.getNewslist());
//                        } else {
//                            return Observable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }

    /**
     * 生成Observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
