object Depend {
    object BuildPlugins {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.GradlePlugin.android}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.GradlePlugin.kotlin}"
        const val jacocoPlugin = "com.dicedmelon.gradle:jacoco-android:${Versions.GradlePlugin.jacoco}"
        const val sonarqubePlugin = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.GradlePlugin.sonarqube}"
        const val detektPlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.GradlePlugin.detekt}"
        const val spotlessPlugin = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.GradlePlugin.spotless}"
        const val jitpack = "com.github.dcendents:android-maven-gradle-plugin:${Versions.GradlePlugin.jitpack}"
    }

    object Core {
        const val lib = "android.arch.core:core:${Versions.Core.core}"
        const val common = "androidx.arch.core:core-common:${Versions.Core.common}"
        const val testing = "android.arch.core:core-testing:${Versions.Core.testing}"
        const val runtime = "android.arch.core:runtime:${Versions.Core.runtime}"
    }

    object Lifecycle {
        const val common = "androidx.lifecycle:lifecycle-common:${Versions.Lifecycle.common}"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.Lifecycle.commonJava8}"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.Lifecycle.compiler}"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Lifecycle.extensions}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata:${Versions.Lifecycle.livedata}"
        const val livedataCode = "androidx.lifecycle:lifecycle-livedata-core:${Versions.Lifecycle.livedataCode}"
        const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.Lifecycle.reactiveStreams}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.Lifecycle.runtime}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.Lifecycle.viewmodel}"
    }

    object Paging {
        const val lib = "androidx.paging:paging-runtime:${Versions.Paging.lib}"
        const val common = "androidx.paging:paging-common:${Versions.Paging.common}"
        const val rxjava2 = "androidx.paging:paging-rxjava2:${Versions.Paging.rxjava2}"
        const val runtime = "androidx.paging:paging-runtime:${Versions.Paging.runtime}"
    }

    object Persistence {
        const val common = "androidx.room:room-common:${Versions.Persistence.common}"
        const val compiler = "androidx.room:room-compiler:${Versions.Persistence.compiler}"
        const val guava = "androidx.room:room-guava:${Versions.Persistence.guava}"
        const val migration = "androidx.room:room-migration:${Versions.Persistence.migration}"
        const val runtime = "androidx.room:room-runtime:${Versions.Persistence.runtime}"
        const val rxjava2 = "androidx.room:room-rxjava2:${Versions.Persistence.rxjava2}"
        const val testing = "androidx.room:room-testing:${Versions.Persistence.testing}"
        const val sqlite = "androidx.sqlite:sqlite:${Versions.Persistence.sqlite}"
        const val sqliteFramework = "androidx.sqlite:sqlite-framework:${Versions.Persistence.sqliteFramework}"
    }

    object Constraint {
        const val lib = "androidx.constraintlayout:constraintlayout:${Versions.Constraint.lib}"
        const val solver = "androidx.constraintlayout:constraintlayout-solver:${Versions.Constraint.solver}"
    }

    object Espresso {
        const val concurrent = "androidx.test.espresso.idling:idling-concurrent:${Versions.Espresso.concurrent}"
        const val net = "androidx.test.espresso.idling:idling-net:${Versions.Espresso.net}"
        const val accessibility = "androidx.test.espresso:espresso-accessibility:${Versions.Espresso.accessibility}"
        const val contrib = "androidx.test.espresso:espresso-contrib:${Versions.Espresso.contrib}"
        const val core = "androidx.test.espresso:espresso-core:${Versions.Espresso.core}"
        const val resource = "androidx.test.espresso:espresso-idling-resource:${Versions.Espresso.resource}"
        const val intents = "androidx.test.espresso:espresso-intents:${Versions.Espresso.intents}"
        const val remote = "androidx.test.espresso:espresso-remote:${Versions.Espresso.remote}"
        const val web = "androidx.test.espresso:espresso-web:${Versions.Espresso.web}"
    }

    object Test {
        const val jankTestHelper = "androidx.test.jank:janktesthelper:${Versions.Test.jankTestHelper}"
        const val services = "androidx.test:test-services:${Versions.Test.services}"
        const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.Test.uiAutomator}"
        const val monitor = "androidx.test:monitor:${Versions.Test.monitor}"
        const val orchestrator = "androidx.test:orchestrator:${Versions.Test.orchestrator}"
        const val rules = "androidx.test:rules:${Versions.Test.rules}"
        const val runner = "androidx.test:runner:${Versions.Test.runner}"
    }

    object AndroidX {
        const val vectorDrawableAnimated = "androidx.vectordrawable:vectordrawable-animated:${Versions.AndroidX.vectorDrawableAnimated}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val asynClayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:${Versions.AndroidX.asynClayoutInflater}"
        const val car = "androidx.car:car:${Versions.AndroidX.car}"
        const val cardview = "androidx.cardview:cardview:${Versions.AndroidX.cardview}"
        const val collection = "androidx.collection:collection:${Versions.AndroidX.collection}"
        const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.AndroidX.coordinatorLayout}"
        const val cursorAdapter = "androidx.cursoradapter:cursoradapter:${Versions.AndroidX.cursorAdapter}"
        const val browser = "androidx.browser:browser:${Versions.AndroidX.browser}"
        const val customview = "androidx.customview:customview:${Versions.AndroidX.customview}"
        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"
        const val documentFile = "androidx.documentfile:documentfile:${Versions.AndroidX.documentFile}"
        const val drawerlayout = "androidx.drawerlayout:drawerlayout:${Versions.AndroidX.drawerlayout}"
        const val exifInterface = "androidx.exifinterface:exifinterface:${Versions.AndroidX.exifInterface}"
        const val gridlayout = "androidx.gridlayout:gridlayout:${Versions.AndroidX.gridlayout}"
        const val heifWriter = "androidx.heifwriter:heifwriter:${Versions.AndroidX.heifWriter}"
        const val interpolator = "androidx.interpolator:interpolator:${Versions.AndroidX.interpolator}"
        const val leanback = "androidx.leanback:leanback:${Versions.AndroidX.leanback}"
        const val loader = "androidx.loader:loader:${Versions.AndroidX.loader}"
        const val localBroadcastManager =
            "androidx.localbroadcastmanager:localbroadcastmanager:${Versions.AndroidX.localBroadcastManager}"
        const val media2 = "androidx.media2:media2:${Versions.AndroidX.media2}"
        const val media2Exoplayer = "androidx.media2:media2-exoplayer:${Versions.AndroidX.media2Exoplayer}"
        const val mediaRouter = "androidx.mediarouter:mediarouter:${Versions.AndroidX.mediaRouter}"
        const val multidex = "androidx.multidex:multidex:${Versions.AndroidX.multidex}"
        const val multidexInstrumentation = "androidx.multidex:multidex-instrumentation:${Versions.AndroidX.multidexInstrumentation}"
        const val palette = "androidx.palette:palette:${Versions.AndroidX.palette}"
        const val percentlayout = "androidx.percentlayout:percentlayout:${Versions.AndroidX.percentlayout}"
        const val leanbackPreference = "androidx.leanback:leanback-preference:${Versions.AndroidX.leanbackPreference}"
        const val legacyPreference = "androidx.legacy:legacy-preference-v14:${Versions.AndroidX.legacyPreference}"
        const val preference = "androidx.preference:preference:${Versions.AndroidX.preference}"
        const val print = "androidx.print:print:${Versions.AndroidX.print}"
        const val recommendation = "androidx.recommendation:recommendation:${Versions.AndroidX.recommendation}"
        const val recyclerviewSelection = "androidx.recyclerview:recyclerview-selection:${Versions.AndroidX.recyclerviewSelection}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerview}"
        const val sliceBuilders = "androidx.slice:slice-builders:${Versions.AndroidX.sliceBuilders}"
        const val sliceCore = "androidx.slice:slice-core:${Versions.AndroidX.sliceCore}"
        const val sliceView = "androidx.slice:slice-view:${Versions.AndroidX.sliceView}"
        const val slidingPanelayout = "androidx.slidingpanelayout:slidingpanelayout:${Versions.AndroidX.slidingPanelayout}"
        const val annotations = "androidx.annotation:annotation:${Versions.AndroidX.annotations}"
        const val core = "androidx.core:core:${Versions.AndroidX.core}"
        const val contentPager = "androidx.contentpager:contentpager:${Versions.AndroidX.contentPager}"
        const val coreUi = "androidx.legacy:legacy-support-core-ui:${Versions.AndroidX.coreUi}"
        const val coreUtils = "androidx.legacy:legacy-support-core-utils:${Versions.AndroidX.coreUtils}"
        const val dynamicAnimation = "androidx.dynamicanimation:dynamicanimation:${Versions.AndroidX.dynamicAnimation}"
        const val emoji = "androidx.emoji:emoji:${Versions.AndroidX.emoji}"
        const val emojiAppcompat = "androidx.emoji:emoji-appcompat:${Versions.AndroidX.emojiAppcompat}"
        const val emojiBundled = "androidx.emoji:emoji-bundled:${Versions.AndroidX.emojiBundled}"
        const val fragment = "androidx.fragment:fragment:${Versions.AndroidX.fragment}"
        const val media = "androidx.media:media:${Versions.AndroidX.media}"
        const val tvprovider = "androidx.tvprovider:tvprovider:${Versions.AndroidX.tvprovider}"
        const val v13 = "androidx.legacy:legacy-support-v13:${Versions.AndroidX.v13}"
        const val v4 = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.v4}"
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Versions.AndroidX.vectorDrawable}"
        const val swipereFreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipereFreshlayout}"
        const val textClassifier = "androidx.textclassifier:textclassifier:${Versions.AndroidX.textClassifier}"
        const val transition = "androidx.transition:transition:${Versions.AndroidX.transition}"
        const val versionedParcelable = "androidx.versionedparcelable:versionedparcelable:${Versions.AndroidX.versionedParcelable}"
        const val viewpager = "androidx.viewpager:viewpager:${Versions.AndroidX.viewpager}"
        const val wear = "androidx.wear:wear:${Versions.AndroidX.wear}"
        const val webkit = "androidx.webkit:webkit:${Versions.AndroidX.webkit}"
    }

    object AndroidXKTX {
        const val core = "androidx.core:core-ktx:${Versions.AndroidXKTX.core}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.AndroidXKTX.fragment}"
        const val palette = "androidx.palette:palette-ktx:${Versions.AndroidXKTX.palette}"
        const val sqlite = "androidx.sqlite:sqlite-ktx:${Versions.AndroidXKTX.sqlite}"
        const val collection = "androidx.collection:collection-ktx:${Versions.AndroidXKTX.collection}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidXKTX.viewmodel}"
        const val reactivestreams =
            "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.AndroidXKTX.reactivestreams}"
        const val navigationCommon =
            "android.arch.navigation:navigation-common-ktx:${Versions.AndroidXKTX.navigationCommon}"
        const val navigationFragment =
            "android.arch.navigation:navigation-fragment-ktx:${Versions.AndroidXKTX.navigationFragment}"
        const val navigationRuntime =
            "android.arch.navigation:navigation-runtime-ktx:${Versions.AndroidXKTX.navigationRuntime}"
        const val navigationTesting =
            "android.arch.navigation:navigation-testing-ktx:${Versions.AndroidXKTX.navigationTesting}"
        const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.AndroidXKTX.navigationUi}"
        const val work = "android.arch.work:work-runtime-ktx:${Versions.AndroidXKTX.work}"
    }

    object Retrofit {
        const val lib = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.lib}"
        const val adapterRx2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Retrofit.adapterRx2}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.gson}"
        const val liveAdapterRx2 =
            "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.Retrofit.liveAdapterRx2}"
    }

    object Dagger {
        const val lib = "com.google.dagger:dagger:${Versions.Dagger.lib}"
        const val android = "com.google.dagger:dagger-android:${Versions.Dagger.android}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.Dagger.compiler}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.Dagger.androidSupport}"
        const val androidSupportCompiler =
            "com.google.dagger:dagger-android-processor:${Versions.Dagger.androidSupportCompiler}"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.stdlib}"
        const val extensions =
            "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Kotlin.extensions}"
        const val testing = "org.jetbrains.kotlin:kotlin-test:${Versions.Kotlin.testing}"
        const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.Kotlin.testJunit}"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.plugin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.Kotlin.reflect}"
        const val allopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.Kotlin.allopen}"
        const val runtime = "org.jetbrains.kotlin:kotlin-runtime:${Versions.Kotlin.runtime}"
        const val compiler = "org.jetbrains.kotlin:kotlin-compiler:${Versions.Kotlin.compiler}"
        const val annotations =
            "org.jetbrains.kotlin:kotlin-jdk-annotations:${Versions.Kotlin.annotations}"
        const val annotationsAndroid =
            "org.jetbrains.kotlin:kotlin-annotations-android:${Versions.Kotlin.annotationsAndroid}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutinesAndroid}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutinesCore}"
    }

    object Google {
        const val services = "com.google.gms:google-services:${Versions.Google.services}"
        const val auth = "com.google.android.gms:play-services-auth:${Versions.Google.auth}"
        const val ads = "com.google.android.gms:play-services-ads:${Versions.Google.ads}"
        const val identity = "com.google.android.gms:play-services-identity:${Versions.Google.identity}"
        const val location = "com.google.android.gms:play-services-location:${Versions.Google.location}"
        const val base = "com.google.android.gms:play-services-base:${Versions.Google.base}"
        const val analytics = "com.google.android.gms:play-services-analytics:${Versions.Google.analytics}"
        const val gcm = "com.google.android.gms:play-services-gcm:${Versions.Google.gcm}"
        const val maps = "com.google.android.gms:play-services-maps:${Versions.Google.maps}"
        const val vision = "com.google.android.gms:play-services-vision:${Versions.Google.vision}"
    }

    object Firebase {
        const val core = "com.google.firebase:firebase-core:${Versions.Firebase.core}"
        const val auth = "com.google.firebase:firebase-auth:${Versions.Firebase.auth}"
        const val database = "com.google.firebase:firebase-database:${Versions.Firebase.database}"
        const val firestore = "com.google.firebase:firebase-firestore:${Versions.Firebase.firestore}"
        const val storage = "com.google.firebase:firebase-storage:${Versions.Firebase.storage}"
        const val messaging = "com.google.firebase:firebase-messaging:${Versions.Firebase.messaging}"
        const val invites = "com.google.firebase:firebase-invites:${Versions.Firebase.invites}"
    }

    object Picasso {
        const val lib = "com.squareup.picasso:picasso:${Versions.Picasso.lib}"
        const val downloader = "com.jakewharton.picasso:picasso2-okhttp3-downloader:${Versions.Picasso.downloader}"
    }

    object Glide {
        const val lib = "com.github.bumptech.glide:glide:${Versions.Glide.lib}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.Glide.compiler}"
        const val annotations = "com.github.bumptech.glide:annotations:${Versions.Glide.annotations}"
        const val downloader =
            "com.github.bumptech.glide:okhttp3-integration:${Versions.Glide.downloader}"
        const val transformations = "jp.wasabeef:glide-transformations:${Versions.Glide.transformations}"
    }

    object OkHttp {
        const val lib = "com.squareup.okhttp3:okhttp:${Versions.OkHttp.lib}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp.logging}"
    }

    object JavaxAndRx {
        const val android2 = "io.reactivex.rxjava2:rxandroid:${Versions.JavaxAndRx.android2}"
        const val java2 = "io.reactivex.rxjava2:rxjava:${Versions.JavaxAndRx.java2}"
        const val annotations = "javax.annotation:javax.annotation-api:${Versions.JavaxAndRx.annotations}"
        const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.JavaxAndRx.rxkotlin}"
        const val rxMath = "io.reactivex:rxjava-math:${Versions.JavaxAndRx.rxMath}"
        const val rxPermission = "com.tbruyelle.rxpermissions:rxpermissions:${Versions.JavaxAndRx.rxPermission}"
        const val rxBinding = "com.jakewharton.rxbinding:rxbinding:${Versions.JavaxAndRx.rxBinding}"
    }

    object Fabric {
        const val plugin = "io.fabric.tools:gradle:${Versions.Fabric.plugin}"
        const val lib = "com.crashlytics.sdk.android:crashlytics:${Versions.Fabric.lib}"
    }

    object Amazon {
        const val s3 = "com.amazonaws:aws-android-sdk-s3:${Versions.Amazon.s3}"
        const val core = "com.amazonaws:aws-android-sdk-core:${Versions.Amazon.core}"
        const val cognito = "com.amazonaws:aws-android-sdk-cognito:${Versions.Amazon.cognito}"
    }

    object Intuit {
        const val sdp = "com.intuit.sdp:sdp-android:${Versions.Intuit.sdp}"
        const val ssp = "com.intuit.ssp:ssp-android:${Versions.Intuit.ssp}"
    }

    object Leakcanary {
        const val android =
            "com.squareup.leakcanary:leakcanary-android:${Versions.Leakcanary.android}"
        const val androidNoOp =
            "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.Leakcanary.androidNoOp}"
        const val supportFragment =
            "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.Leakcanary.supportFragment}"
    }

    object Atsl {
        const val testing = "com.android.support.test:runner:${Versions.Atsl.testing}"
        const val rule = "com.android.support.test:rules:${Versions.Atsl.rule}"
    }

    object Mockito {
        const val core = "org.mockito:mockito-core:${Versions.Mockito.core}"
        const val all = "org.mockito:mockito-all:${Versions.Mockito.all}"
    }

    object Robolectric {
        const val plugin = "org.robolectric:robolectric-gradle-plugin:${Versions.Robolectric.plugin}"
        const val testing = "org.robolectric:robolectric:${Versions.Robolectric.testing}"
    }

    object Social {
        const val facebook = "com.facebook.android:facebook-login:${Versions.Social.facebook}"
        const val facebookSdk =
            "com.facebook.android:facebook-android-sdk:${Versions.Social.facebookSdk}"
        const val twitter = "com.twitter.sdk.android:twitter:${Versions.Social.twitter}"
    }

    object Payment {
        const val stripe = "com.stripe:stripe-android:${Versions.Payment.stripe}"
        const val braintree = "com.braintreepayments.api:braintree:${Versions.Payment.braintree}"
    }

    object ButterKnife {
        const val lib = "com.jakewharton:butterknife:${Versions.ButterKnife.lib}"
        const val compiler = "com.jakewharton:butterknife-compiler:${Versions.ButterKnife.compiler}"
    }

    object ThirdParty {
        const val eventbus = "org.greenrobot:eventbus:${Versions.ThirdParty.eventbus}"
        const val gson = "com.google.code.gson:gson:${Versions.ThirdParty.gson}"
        const val timeago = "com.github.marlonlom:timeago:${Versions.ThirdParty.timeago}"
        const val socketIo = "io.socket:socket.io-client:${Versions.ThirdParty.socketIo}"
        const val commonsLogging = "commons-logging:commons-logging:${Versions.ThirdParty.commonsLogging}"
        const val binding = "com.android.databinding:compiler:${Versions.ThirdParty.binding}"
        const val dexmaker = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.ThirdParty.dexmaker}"
        const val timber = "com.jakewharton.timber:timber:${Versions.ThirdParty.timber}"
        const val junit = "junit:junit:${Versions.ThirdParty.junit}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.ThirdParty.mockWebServer}"
        const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.ThirdParty.hamcrest}"
    }
}