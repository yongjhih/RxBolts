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
