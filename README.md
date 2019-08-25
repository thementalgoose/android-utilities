# Android Utilities class 

Contains a collection of all the sample utility / base / abstract classes that I use throughout most of my projects 

[![](https://jitpack.io/v/thementalgoose/android-utilities.svg)](https://jitpack.io/#thementalgoose/android-utilities)

#### Root `build.gradle`

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### Install 

```
dependencies {
    implementation 'com.github.thementalgoose:android-utilities:'
}
```

[![](https://jitpack.io/v/thementalgoose/android-utilities.svg)](https://jitpack.io/#thementalgoose/android-utilities)


#### Usage

```
    <!-- Override colours in the setting by declaring these in your app -->
    <color name="aboutThisApp_headerDark">#3C306E</color>
    <color name="aboutThisApp_headerLight">#5D25E6</color>

    <color name="aboutThisApp_textDesc">#f2f2f2</color>
    <color name="aboutThisApp_textName">#fbfbfb</color>

    <color name="aboutThisApp_backgroundLight">#f2f2f2</color>
    <color name="aboutThisApp_textLight">#f8f8f8</color>
    <color name="aboutThisApp_textLightSecondary">#e8e8e8</color>

    <color name="aboutThisApp_backgroundDark">#282828</color>
    <color name="aboutThisApp_textDark">#222222</color>
    <color name="aboutThisApp_textDarkSecondary">#383838</color>
```