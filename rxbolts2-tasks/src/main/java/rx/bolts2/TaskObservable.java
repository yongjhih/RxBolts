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
import io.reactivex.Observable;

/**
 * Bolts2Rx
 * Bolts.Task2Observable
 * RxBolts
 * RxTask
 * BoltsObservable
 * TaskObservable
 */
public class TaskObservable {

    public static <R> Task<R> forTask(final Observable<R> obs){
        return Task.callInBackground(() -> obs.blockingSingle()); // FIXME: null is not acceptable
    }

    public static <R> Observable<R> just(Task<R> task) {
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

    public static <R> Observable<R> just(Callable<Task<R>> task) {
        return defer(task);
    }

    public static <R> Observable<R> defer(Callable<Task<R>> task) {
        return Observable.defer(() -> just(task.call()));
    }
}
