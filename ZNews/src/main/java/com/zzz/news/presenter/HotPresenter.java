package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.HotListBean;
import com.zzz.news.model.db.RealmHelper;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.HotContract;
import com.zzz.news.util.ZRx;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:09
 */
public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    private RealmHelper mRealmHelper;

    @Inject
    public HotPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void getHotData() {
        Subscription rxSubscription = mRetrofitHelper.fetchHotList()
                .compose(ZRx.<HotListBean>rxSchedulerHelper())
                .map(new Func1<HotListBean, HotListBean>() {
                    @Override
                    public HotListBean call(HotListBean hotListBean) {
                        List<HotListBean.RecentBean> list = hotListBean.getRecent();
                        for(HotListBean.RecentBean item : list) {
                            item.setReadState(mRealmHelper.queryNewsId(item.getNews_id()));
                        }
                        return hotListBean;
                    }
                })
                .subscribe(new Action1<HotListBean>() {
                    @Override
                    public void call(HotListBean hotListBean) {
                        mView.showContent(hotListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void insert2db(int id) {
        mRealmHelper.inserNewsId(id);
    }
}
