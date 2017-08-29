/*
 * Copyright (C) 2015 8tory, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rx.bolts2;

import java.util.concurrent.Callable;

import bolts.Task;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;

/**
 * Bolts2Rx
 * Bolts.Task2Observable
 * RxBolts
 * RxTask
 * BoltsObservable
 * TaskObservable
 */
public class RxTask {

    @CheckReturnValue
    @NonNull
    public static <R> Task<R> forTask(@NonNull final Observable<R> obs){
        return Task.callInBackground(() -> obs.blockingSingle()); // FIXME: null is not acceptable
    }

    @CheckReturnValue
    @NonNull
    public static <R> Task<R> forTask(@NonNull final Single<R> single){
        return Task.callInBackground(() -> single.blockingGet()); // FIXME: null is not acceptable
    }

    //@CheckReturnValue
    //@NonNull
    //public static Task<Void> forTask(@NonNull final Completable completable){
    //    return Task.callInBackground(() -> Task.forResult(completable.blockingAwait())); // FIXME: null is not acceptable
    //}

    @CheckReturnValue
    @NonNull
    public static <R> Observable<R> observable(@NonNull final Task<R> task) {
        return Observable.create(emitter -> {
            task.continueWith(t -> {
                if (emitter.isDisposed()) return null;

                if (t.isCancelled()) {
                    // NOTICE: doOnUnsubscribe(() -> Observable.just(query) in outside
                    emitter.onComplete();
                } else if (t.isFaulted()) {
                    Throwable error = t.getError();
                    emitter.onError(error);
                } else {
                    R r = t.getResult();
                    if (r != null) emitter.onNext(r);
                    emitter.onComplete();
                }
                return null;
            });
        });
        // TODO .doOnUnsubscribe(() -> task.setCancelled());
    }

    @NonNull
    public static <R> Completable completable(@NonNull final Task<R> task) {
        return Completable.create(emitter -> {
            task.continueWith(t -> {
                if (emitter.isDisposed()) return null;

                if (t.isCancelled()) {
                    // NOTICE: doOnUnsubscribe(() -> Observable.just(query) in outside
                    emitter.onComplete();
                } else if (t.isFaulted()) {
                    Throwable error = t.getError();
                    emitter.onError(error);
                } else {
                    t.getResult();
                    emitter.onComplete();
                }
                return null;
            });
        });
        // TODO .doOnUnsubscribe(() -> task.setCancelled());
    }

    /**
     * Please don't put the Task&lt;Void&gt; as parameter
     * @param task
     * @param <R>
     * @return
     */
    @CheckReturnValue
    @NonNull
    public static <R> Single<R> single(@NonNull final Task<R> task) {
        return Single.create(emitter -> {
            task.continueWith(t -> {
                if (emitter.isDisposed()) return null;

                if (t.isCancelled()) {
                    emitter.onError(new RuntimeException("Cancelled task"));
                } else if (t.isFaulted()) {
                    Throwable error = t.getError();
                    emitter.onError(error);
                } else {
                    R r = t.getResult();
                    emitter.onSuccess(r);
                }
                return null;
            });
        });
        // TODO .doOnUnsubscribe(() -> task.setCancelled());
    }

    /**
     * Please use observable(Callable) instead of this for defer unless you know what are you doing
     * Please don't put the Task&lt;Void&gt; as parameter
     * @param task
     * @param <R>
     * @return
     */
    @CheckReturnValue
    @NonNull
    public static <R> Observable<R> observable(@NonNull final Callable<Task<R>> task) {
        return Single.fromCallable(task).flatMapObservable(RxTask::observable);
    }

    /**
     * Please use single(Callable) instead of this for defer unless you know what are you doing
     * Please don't put the Task&lt;Void&gt; as parameter
     * @param task
     * @param <R>
     * @return
     */
    @CheckReturnValue
    @NonNull
    public static <R> Single<R> single(@NonNull final Callable<Task<R>> task) {
        return Single.fromCallable(task).flatMap(RxTask::single);
    }

    /**
     * Please use single(Callable) instead of this for defer unless you know what are you doing
     * @param task
     * @param <R>
     * @return
     */
    @NonNull
    public static <R> Completable completable(@NonNull final Callable<Task<R>> task) {
        return Single.fromCallable(task).flatMapCompletable(RxTask::completable);
    }
}
