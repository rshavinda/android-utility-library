# Android Utility Library
"android-utility-library" is an Android-library, which provides various utility classes for use in Android app development.\
⛻ Android 4.0.3+ (API level 15+)   ⛻ Language : Java\
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=15)

- AppDatabase - *Wrapper/helper for the shared preferences of Android*
- DateTimeUtil
- ImageUtil
- JsonUtil
- SystemUtil
- TextUtil
- Validator


## Installation

**Step 1. Add the JitPack repository to your build file**
###### And the following dependency to your app `build.gradle` file:

```groovy
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```

**Step 2. Add the dependency**
###### Add the following dependency to your module `build.gradle` file:
```groovy
  dependencies {
     implementation 'com.github.rshavinda:android-utility-library:v1.0.1'
  }
```



## MIT License

###### Copyright (c) 2020 Rajitha Shavinda

<sub>Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:<sub>

<sub>The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.<sub>

<sub>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.<sub>
