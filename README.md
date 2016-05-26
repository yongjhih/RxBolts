# RxBolts

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxBolts-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/2645)
[![JitPack](https://img.shields.io/github/tag/yongjhih/RxBolts.svg?label=JitPack)](https://jitpack.io/#yongjhih/RxBolts)
[![Download](https://api.bintray.com/packages/yongjhih/maven/rxbolts/images/download.svg) ](https://bintray.com/yongjhih/maven/rxbolts/_latestVersion)
[![javadoc](https://img.shields.io/github/tag/yongjhih/RxBolts.svg?label=javadoc)](https://jitpack.io/com/github/yongjhih/RxBolts/-SNAPSHOT/javadoc/)
[![Build Status](https://travis-ci.org/yongjhih/RxBolts.svg)](https://travis-ci.org/yongjhih/RxBolts)
[![Methods Count](https://img.shields.io/badge/Methods and size-core: 54 | deps: 4111 | 6 KB-e91e63.svg)](http://www.methodscount.com/?lib=com.infstory%3Arxbolts%3A1.0.0)
[![Gitter Chat](https://img.shields.io/gitter/room/yongjhih/RxBolts.svg)](https://gitter.im/yongjhih/RxBolts)
[![Coverage Status](https://coveralls.io/repos/github/yongjhih/RxBolts/badge.svg)](https://coveralls.io/github/yongjhih/RxBolts)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/05a9b9b101b24073bc021f41bafc5090)](https://codacy.com/app/yongjhih/RxBolts)
<!--[![javadoc.io](https://javadocio-badges.herokuapp.com/com.infstory/rxbolts/badge.svg)](http://www.javadoc.io/doc/com.infstory/rxbolts/)-->
<!--[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxBolts-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1670)-->
<!--[![Download](https://api.bintray.com/packages/yongjhih/maven/RxBolts/images/download.svg) ](https://bintray.com/yongjhih/maven/RxBolts/_latestVersion)-->
<!--[![Bountysource](https://www.bountysource.com/badge/team?team_id=43965&style=bounties_posted)](https://www.bountysource.com/teams/8tory/bounties?utm_source=8tory&utm_medium=shield&utm_campaign=bounties_posted)-->

[![rxbolts-gray-fg-blue-bg.png](art/rxbolts-gray-fg-blue-bg.png)](art/rxbolts-gray-fg-blue-bg.png)

Reactive [Bolts-Android](https://github.com/BoltsFramework/Bolts-Android). Allow convert `Task<T>` to `Observable<T>`.

## Usage

TaskObservable.defer():

```java
TaskObservable.defer(() -> Task.forResult("Hello, world!")).subscribe(it -> {
  System.out.println(it);
});
```

Take 5 users using Parse:

```java
TaskObservable.defer(() -> ParseUser.getQuery().findInBackground()).flatMap(Observable::from).take(5).subscribe(user -> {
  System.out.println(user.getObjserId());
});
```

Update location of 5 users using Parse:

```java
TaskObservable.defer(() -> ParseUser.getQuery().findInBackground()).flatMap(Observable::from).take(5).flatMap(user -> {
  user.put("location", "Taipei");
  return TaskObservable.defer(() -> user.saveInBackground());
}).subscribe();
```

### Null task handling

TaskObservable.defer():

```java
TaskObservable.defer(() -> Task.forResult(null)).subscribe(it -> {}, e -> {}, () -> {
  System.out.println("onCompleted");
});
```

TaskObservable.deferNullable():

```java
TaskObservable.deferNullable(() -> Task.forResult(null)).subscribe(it -> {
  System.out.println(it); // print null
});
```

TaskObservable.deferNullable() for printing updated location of 5 users:

```java
TaskObservable.defer(() -> ParseUser.getQuery().findInBackground()).flatMap(Observable::from).take(5).flatMap(user -> {
  user.put("location", "Taipei");
  // ParseUser.saveInBackground() returns Task<Void> conatins null
  // Allow omit null with TaskObservable.deferNullable()
  return TaskObservable.deferNullable(() -> user.saveInBackground()).map(it -> user);
}).subscribe(user -> {
  System.out.println(user.getObjectId());
});
```

TaskObservable.deferNonNull():

```java
TaskObservable.deferNonNull(() -> Task.forResult(null)).subscribe(it -> {}, e -> {
  e.printStackTrace(); // NullPointerException
});
```

### Error handling

Failed:

```java
TaskObservable.defer(() -> Task.forError(new RuntimeException("An error message."))).subscribe(it -> {}, e -> {
  e.printStackTrace(); // RuntimeException
});
```

## AppLinks

```java
AppLinkObservable.navigate(context, url, resolver);
```

```java
AppLinkNavigation.setDefaultResolver(resolver);
AppLinkObservable.navigate(context, url);
```

## Sample code

* https://github.com/yongjhih/RxParse/blob/master/rxparse/src/main/java/rx/parse/ParseObservable.java

## Installation

via jcenter

```gradle
repositories {
    jcenter()

}

dependencies {
    compile 'com.infstory:rxbolts:1.0.1'
}
```

Or via jitpack.io

```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.yongjhih.RxBolts:rxbolts-android:-SNAPSHOT'
    //compile 'com.github.yongjhih.RxBolts:rxbolts:-SNAPSHOT'
    //compile 'com.github.yongjhih.RxBolts:rxbolts-applinks:-SNAPSHOT'
}
```

## See Also


[![](https://raw.githubusercontent.com/yongjhih/RxParse/master/art/rxparse.png)](https://github.com/yongjhih/RxParse)

* https://github.com/yongjhih/RxParse used

## ref.

* https://github.com/BoltsFramework/Bolts-Android

<!-- http://www.norio.be/android-feature-graphic-generator/?config=%7B%22background%22%3A%7B%22color%22%3A%22%233b5fad%22%2C%22gradient%22%3A%7B%22type%22%3A%22radial%22%2C%22radius%22%3A%22915%22%2C%22angle%22%3A%22vertical%22%2C%22color%22%3A%22%23000000%22%7D%7D%2C%22title%22%3A%7B%22text%22%3A%22%22%2C%22color%22%3A%22%23ffffff%22%2C%22size%22%3A200%2C%22font%22%3A%7B%22family%22%3Anull%2C%22effect%22%3A%22bold%22%7D%7D%2C%22subtitle%22%3A%7B%22text%22%3A%22%22%2C%22color%22%3A%22%23ffffff%22%2C%22size%22%3A100%2C%22font%22%3A%7B%22family%22%3Anull%2C%22effect%22%3A%22normal%22%7D%7D%2C%22image%22%3A%7B%22position%22%3A%22100%22%2C%22file%22%3A%7B%7D%7D%2C%22size%22%3A%22custom%22%7D -->

## LICENSE

Copyright 2015 8tory, Inc.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
