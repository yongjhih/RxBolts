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

import org.junit.Test;

import bolts.Task;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import static org.assertj.core.api.Assertions.assertThat;


public class TaskObservableTests {

    @Test
    public void single() {
        RxTask.single(Task.<String>forResult(null))
                .test()
                .assertError(NullPointerException.class);

        RxTask.single(Task.forResult("hello"))
                .test()
                .assertValue(check(v -> assertThat(v).isEqualTo("hello")))
                .assertNoErrors()
                .assertComplete();
    }

    @Test
    public void completable() {
        RxTask.completable(Task.<String>forResult(null))
                .test()
                .assertError(NullPointerException.class);

        RxTask.completable(Task.forResult("hello"))
                .test()
                .assertValue(check(v -> assertThat(v).isEqualTo("hello")))
                .assertNoErrors()
                .assertComplete();
    }
    @Test
    public void observable() {
        RxTask.observable(Task.<String>forResult(null))
                .test()
                .assertError(NullPointerException.class);

        RxTask.observable(Task.forResult("hello"))
                .test()
                .assertValue(check(v -> assertThat(v).isEqualTo("hello")))
                .assertNoErrors()
                .assertComplete();
    }

    @Test
    public void forTaskObservable() {
        assertThat(RxTask.forTask(Observable.empty()).getResult()).isNull();
    }

    public static <T> Predicate<T> check(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return true;
        };
    }
}

