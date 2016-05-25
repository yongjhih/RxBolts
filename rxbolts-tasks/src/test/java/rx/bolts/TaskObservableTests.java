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

package rx.bolts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import bolts.Task;

//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.doReturn;
//import static mocker.Mocker.mocker;
//import mocker.Mocker;
import static rx.assertions.RxAssertions.assertThat;

import rx.Observable;

public class TaskObservableTests {

    @Test
    public void just() {
        assertThat(TaskObservable.just(Task.<String>forResult(null)))
            .withoutErrors()
            .emitsNothing()
            .completes();

        assertThat(TaskObservable.just(Task.forResult("hello")))
            .withoutErrors()
            .expectedValues("hello")
            .completes();
    }

    @Test
    public void justNullable() {
        assertThat(TaskObservable.justNullable(Task.<String>forResult(null)))
            .withoutErrors()
            .expectedValues((String) null)
            .completes();
    }

    @Test
    public void defer() {
        assertThat(TaskObservable.defer(() -> Task.<String>forResult(null)))
            .withoutErrors()
            .emitsNothing()
            .completes();

        assertThat(TaskObservable.defer(() -> Task.forResult("hello")))
            .withoutErrors()
            .expectedValues("hello")
            .completes();
    }

    @Test
    public void deferNullable() {
        assertThat(TaskObservable.deferNullable(() -> Task.<String>forResult(null)))
            .withoutErrors()
            .expectedValues((String) null)
            .completes();
    }

    @Test
    public void deferNonNull() {
        assertThat(TaskObservable.deferNonNull(() -> Task.<String>forResult(null)))
            .failsWithThrowable(NullPointerException.class);
    }


}
