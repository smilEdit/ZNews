package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.SectionChildListBean;
import com.zzz.news.model.db.RealmHelper;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.SectionChildContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 17:14
 */

public class SectionChildPresenter extends RxPresenter<SectionChildContract.View> implements SectionChildContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper mRealmHelper;

    @Inject
    public SectionChildPresenter(RetrofitHelper retrofitHelper,RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void getSectionChildData(int id) {
        Subscription rxSubscription = mRetrofitHelper.fetchSectionChildList(id)
                .compose(ZRx.<SectionChildListBean>rxSchedulerHelper())
                .map(new Func1<SectionChildListBean, SectionChildListBean>() {
                    @Override
                    public SectionChildListBean call(SectionChildListBean sectionChildListBean) {
                        List<SectionChildListBean.StoriesBean> list = sectionChildListBean.getStories();
                        for(SectionChildListBean.StoriesBean item : list) {
                            item.setReadState(mRealmHelper.queryNewsId(item.getId()));
                        }
                        return sectionChildListBean;
                    }
                })
                .subscribe(new Action1<SectionChildListBean>() {
                    @Override
                    public void call(SectionChildListBean sectionChildListBean) {
                        ZLog.i(sectionChildListBean.toString());
                        mView.showContent(sectionChildListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ZLog.i(throwable.toString());
                        mView.showError(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void intsert2db(int id) {
        mRealmHelper.inserNewsId(id);
    }
}
