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

import bolts.*;

import rx.schedulers.*;
import rx.Observable;
import rx.functions.*;
import rx.observables.*;

public class AppLinkObservable {
    public static Observable<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.lang.String destinationUrl) {
        return TaskObservable.deferNullable(() -> AppLinkNavigation.navigateInBackground(context, destinationUrl));
    }
    public static Observable<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.lang.String destinationUrl, AppLinkResolver resolver) {
        return TaskObservable.deferNullable(() -> AppLinkNavigation.navigateInBackground(context, destinationUrl, resolver));
    }
    public static Observable<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, android.net.Uri destination) {
        return TaskObservable.deferNullable(() -> AppLinkNavigation.navigateInBackground(context, destination));
    }
    public static Observable<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, android.net.Uri destination, AppLinkResolver resolver) {
        return TaskObservable.deferNullable(() -> AppLinkNavigation.navigateInBackground(context, destination, resolver));
    }
    public static Observable<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.net.URL destination) {
        return TaskObservable.deferNullable(() -> AppLinkNavigation.navigateInBackground(context, destination));
    }
    public static Observable<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.net.URL destination, AppLinkResolver resolver) {
        return TaskObservable.deferNullable(() -> AppLinkNavigation.navigateInBackground(context, destination, resolver));
    }

    public static Observable<AppLink> getAppLink(AppLinkResolver resolver, android.net.Uri url) {
        return TaskObservable.deferNullable(() -> resolver.getAppLinkFromUrlInBackground(url));
    }
}
