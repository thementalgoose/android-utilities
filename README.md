# Android Utilities class

[![](https://jitpack.io/v/thementalgoose/android-utilities.svg)](https://jitpack.io/#thementalgoose/android-utilities) ![main](https://github.com/thementalgoose/android-utilities/workflows/unit-test-main/badge.svg)

Contains a collection of all the extensions and generic utilities that I commonly use.

## CI

Unit tests are ran in github actions

## Install

#### `build.gradle`

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### `app/build.gradle`

```
dependencies {
    implementation 'com.github.thementalgoose:android-utilities:2.0.1'     // Use Jitpack version if newer
}
```

[![](https://jitpack.io/v/thementalgoose/android-utilities.svg)](https://jitpack.io/#thementalgoose/android-utilities)

## License

```
Copyright (C) 2020 Jordan Fisher

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```