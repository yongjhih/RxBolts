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

import bolts.*;
import io.reactivex.Single;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;

public class AppLinkObservable {
    @CheckReturnValue
    @NonNull
    public static Single<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.lang.String destinationUrl) {
        return RxTask.single(() -> AppLinkNavigation.navigateInBackground(context, destinationUrl));
    }
    @CheckReturnValue
    @NonNull
    public static Single<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.lang.String destinationUrl, AppLinkResolver resolver) {
        return RxTask.single(() -> AppLinkNavigation.navigateInBackground(context, destinationUrl, resolver));
    }
    @CheckReturnValue
    @NonNull
    public static Single<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, android.net.Uri destination) {
        return RxTask.single(() -> AppLinkNavigation.navigateInBackground(context, destination));
    }
    @CheckReturnValue
    @NonNull
    public static Single<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, android.net.Uri destination, AppLinkResolver resolver) {
        return RxTask.single(() -> AppLinkNavigation.navigateInBackground(context, destination, resolver));
    }
    @CheckReturnValue
    @NonNull
    public static Single<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.net.URL destination) {
        return RxTask.single(() -> AppLinkNavigation.navigateInBackground(context, destination));
    }
    @CheckReturnValue
    @NonNull
    public static Single<AppLinkNavigation.NavigationResult> navigate(android.content.Context context, java.net.URL destination, AppLinkResolver resolver) {
        return RxTask.single(() -> AppLinkNavigation.navigateInBackground(context, destination, resolver));
    }
    @CheckReturnValue
    @NonNull
    public static Single<AppLink> getAppLink(AppLinkResolver resolver, android.net.Uri url) {
        return RxTask.single(() -> resolver.getAppLinkFromUrlInBackground(url));
    }
}
