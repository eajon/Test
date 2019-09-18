package cn.eajon.tool;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.View;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * @author eajon on 2019/9/18.
 */
public class FlowableUtils {


    private FlowableUtils() {
        throw new AssertionError();
    }

    /**
     * 线程调度
     */
    public static <T> FlowableTransformer<T, T> ioMain() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
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
    public static <T> FlowableTransformer<T, T> ioIo() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
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
    public static <T> FlowableTransformer<T, T> lifeCycle(final LifecycleProvider lifecycle, final ActivityEvent activityEvent) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
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
    public static <T> FlowableTransformer<T, T> lifeCycle(final LifecycleProvider lifecycle, final FragmentEvent fragmentEvent) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
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
    public static Flowable addView(Flowable flowable, View view) {
        return Flowable.using(() -> {
            view.setVisibility(View.VISIBLE);
            return view;
        }, new Function<View, Publisher<?>>() {
            @Override
            public Publisher<?> apply(View view) {
                return flowable;
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
    public static Flowable addDialog(Flowable flowable, Dialog dialog) {
        return Flowable.using(new Callable<Dialog>() {
            @Override
            public Dialog call() {
                dialog.show();
                return dialog;
            }
        }, new Function<Dialog, Flowable<Object>>() {
            @Override
            public Flowable<Object> apply(final Dialog progressDialog) {
                final BehaviorProcessor<Boolean> dialogProcessor = BehaviorProcessor.create();
                return flowable.doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialogProcessor.onNext(true);
                            }
                        });
                    }
                }).takeUntil(dialogProcessor);
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
    public static Flowable addDialog(Flowable flowable, String title, String message, boolean cancelable) {
        return Flowable.using(new Callable<Dialog>() {
            @Override
            public Dialog call() {
                return ProgressDialog.show(Utils.getContext(), title, message, true, cancelable);
            }
        }, new Function<Dialog, Flowable<Object>>() {
            @Override
            public Flowable<Object> apply(final Dialog progressDialog) {
                final BehaviorProcessor<Boolean> dialogProcessor = BehaviorProcessor.create();
                return flowable.doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialogProcessor.onNext(true);
                            }
                        });
                    }
                }).takeUntil(dialogProcessor);
            }
        }, new Consumer<Dialog>() {
            @Override
            public void accept(Dialog dialog) {
                dialog.dismiss();
            }
        });
    }

}
