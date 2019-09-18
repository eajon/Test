package cn.eajon.tool;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.View;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author eajon on 2019/9/18.
 */
public class RxJavaUtils {

    private RxJavaUtils() {
        throw new AssertionError();
    }


    /**
     * 线程调度
     */
    public static <T> ObservableTransformer<T, T> ioMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 线程调度
     */
    public static <T> ObservableTransformer<T, T> ioIo() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
            }
        };
    }

    /**
     * 生命周期
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> lifeCycle(final LifecycleProvider lifecycle, final ActivityEvent activityEvent) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                if (lifecycle != null) {
                    if (activityEvent != null) {
                        return upstream.compose(lifecycle.bindUntilEvent(activityEvent));
                    } else {
                        return upstream.compose(lifecycle.bindToLifecycle());
                    }
                }
                return upstream;
            }
        };
    }

    /**
     * 生命周期
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> lifeCycle(final LifecycleProvider lifecycle, final FragmentEvent fragmentEvent) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                if (lifecycle != null) {
                    if (fragmentEvent != null) {
                        return upstream.compose(lifecycle.bindUntilEvent(fragmentEvent));
                    } else {
                        return upstream.compose(lifecycle.bindToLifecycle());
                    }
                }
                return upstream;
            }
        };
    }

    /**
     * add ProgressBar
     */
    @SuppressWarnings("unchecked")
    public static Observable addView(Observable observable, View view) {
        return Observable.using(new Callable<View>() {
            @Override
            public View call() {
                view.setVisibility(View.VISIBLE);
                return view;
            }
        }, new Function<View, Observable<Object>>() {
            @Override
            public Observable<Object> apply(final View view) {
                return observable;
            }
        }, new Consumer<View>() {
            @Override
            public void accept(View view) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * add DIALOG
     */
    @SuppressWarnings("unchecked")
    public static Observable addDialog(Observable observable, Dialog dialog) {
        return Observable.using(new Callable<Dialog>() {
            @Override
            public Dialog call() {
                dialog.show();
                return dialog;
            }
        }, new Function<Dialog, Observable<Object>>() {
            @Override
            public Observable<Object> apply(final Dialog progressDialog) {
                final BehaviorSubject<Boolean> dialogSubject = BehaviorSubject.create();
                return observable.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(final Disposable disposable) throws Exception {
                        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialogSubject.onNext(true);
                            }
                        });
                    }
                }).takeUntil(dialogSubject);
            }
        }, new Consumer<Dialog>() {
            @Override
            public void accept(Dialog dialog) {
                dialog.dismiss();
            }
        });
    }

    /**
     * add DIALOG
     */
    @SuppressWarnings("unchecked")
    public static Observable addDialog(Observable observable, String title, String message, boolean cancelable) {
        return Observable.using(new Callable<Dialog>() {
            @Override
            public Dialog call() {
                return ProgressDialog.show(Utils.getContext(), title, message, true, cancelable);
            }
        }, new Function<Dialog, Observable<Object>>() {
            @Override
            public Observable<Object> apply(final Dialog progressDialog) {
                final BehaviorSubject<Boolean> dialogSubject = BehaviorSubject.create();
                return observable.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(final Disposable disposable) throws Exception {
                        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialogSubject.onNext(true);
                            }
                        });
                    }
                }).takeUntil(dialogSubject);
            }
        }, new Consumer<Dialog>() {
            @Override
            public void accept(Dialog dialog) {
                dialog.dismiss();
            }
        });
    }

}
