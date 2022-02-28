package com.wittyly.witpms.presenter;

import com.wittyly.witpms.interactor.GetMentionHashtagUseCase;
import com.wittyly.witpms.interactor.Params;
import com.wittyly.witpms.model.POJO.HashtagMentionModel;
import com.wittyly.witpms.view.GetMentionHashtagView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class GetMentionHashtagPresenter implements Presenter {

    private final GetMentionHashtagUseCase useCase;
    private GetMentionHashtagView view;

    @Inject
    public GetMentionHashtagPresenter(GetMentionHashtagUseCase useCase) {
        this.useCase = useCase;
    }

    public void setView(GetMentionHashtagView view) {
        this.view = view;
    }

    public void start(Params params) {
        useCase.execute(new DisposableObserver<HashtagMentionModel>() {

            @Override
            public void onNext(HashtagMentionModel value) {
                if (view != null) {
                    view.onResult(value);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (view != null) {
                    view.onHalt(e);
                }
            }

            @Override
            public void onComplete() {
                if (view != null) {
                    view.onDone();
                }
            }

        }, params);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        useCase.dispose();
        view = null;
    }

}
