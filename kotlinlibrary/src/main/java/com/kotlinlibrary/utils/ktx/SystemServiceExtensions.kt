package com.kotlinlibrary.utils.ktx

import android.accounts.AccountManager
import android.annotation.TargetApi
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.LauncherApps
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textservice.TextServicesManager
import androidx.annotation.RequiresApi
import com.kotlinlibrary.utils.getContextFromSource

@Suppress("UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER")
fun <T> Any.getSystemService(name: String) = getContextFromSource(this).getSystemService(name) as? T

inline val Any.accessibilityManager
    get() = getSystemService<AccessibilityManager>(Context.ACCESSIBILITY_SERVICE)

inline val Any.accountManager
    get() = getSystemService<AccountManager>(Context.ACCOUNT_SERVICE)

inline val Any.activityManager
    get() = getSystemService<ActivityManager>(Context.ACTIVITY_SERVICE)

inline val Any.alarmManager
    get() = getSystemService<AlarmManager>(Context.ALARM_SERVICE)

inline val Any.appWidgetManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<AppWidgetManager>(Context.APPWIDGET_SERVICE)

inline val Any.appOpsManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService<AppOpsManager>(Context.APP_OPS_SERVICE)

inline val Any.audioManager
    get() = getSystemService<AudioManager>(Context.AUDIO_SERVICE)

inline val Any.batteryManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<BatteryManager>(Context.BATTERY_SERVICE)

inline val Any.bluetoothManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    get() = getSystemService<BluetoothManager>(Context.BLUETOOTH_SERVICE)

inline val Any.cameraManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<CameraManager>(Context.CAMERA_SERVICE)

inline val Any.captioningManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService<CaptioningManager>(Context.CAPTIONING_SERVICE)

inline val Any.clipboardManager
    get() = getSystemService<ClipboardManager>(Context.CLIPBOARD_SERVICE)

inline val Any.connectivityManager
    get() = getSystemService<ConnectivityManager>(Context.CONNECTIVITY_SERVICE)

inline val Any.consumerIrManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService<ConsumerIrManager>(Context.CONSUMER_IR_SERVICE)

inline val Any.devicePolicyManager
    get() = getSystemService<DevicePolicyManager>(Context.DEVICE_POLICY_SERVICE)

inline val Any.displayManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = getSystemService<DisplayManager>(Context.DISPLAY_SERVICE)

inline val Any.downloadManager
    get() = getSystemService<DownloadManager>(Context.DOWNLOAD_SERVICE)

inline val Any.dropBoxManager
    get() = getSystemService<DropBoxManager>(Context.DROPBOX_SERVICE)

inline val Any.inputMethodManager
    get() = getSystemService<InputMethodManager>(Context.INPUT_METHOD_SERVICE)

inline val Any.inputManager
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    get() = getSystemService<InputManager>(Context.INPUT_SERVICE)

inline val Any.jobScheduler
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<JobScheduler>(Context.JOB_SCHEDULER_SERVICE)

inline val Any.keyguardManager
    get() = getSystemService<KeyguardManager>(Context.KEYGUARD_SERVICE)

inline val Any.launcherApps
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<LauncherApps>(Context.LAUNCHER_APPS_SERVICE)

inline val Any.layoutInflater
    get() = getSystemService<LayoutInflater>(Context.LAYOUT_INFLATER_SERVICE)

inline val Any.locationManager
    get() = getSystemService<LocationManager>(Context.LOCATION_SERVICE)

inline val Any.mediaProjectionManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<MediaProjectionManager>(Context.MEDIA_PROJECTION_SERVICE)

inline val Any.mediaRouter
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    get() = getSystemService<MediaRouter>(Context.MEDIA_ROUTER_SERVICE)

inline val Any.mediaSessionManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<MediaSessionManager>(Context.MEDIA_SESSION_SERVICE)

inline val Any.nfcManager
    get() = getSystemService<NfcManager>(Context.NFC_SERVICE)

inline val Any.notificationManager
    get() = getSystemService<NotificationManager>(Context.NOTIFICATION_SERVICE)

inline val Any.nsdManager
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    get() = getSystemService<NsdManager>(Context.NSD_SERVICE)

inline val Any.powerManager
    get() = getSystemService<PowerManager>(Context.POWER_SERVICE)

inline val Any.printManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService<PrintManager>(Context.PRINT_SERVICE)

inline val Any.restrictionsManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<RestrictionsManager>(Context.RESTRICTIONS_SERVICE)

inline val Any.searchManager
    get() = getSystemService<SearchManager>(Context.SEARCH_SERVICE)

inline val Any.sensorManager
    get() = getSystemService<SensorManager>(Context.SENSOR_SERVICE)

inline val Any.storageManager
    get() = getSystemService<StorageManager>(Context.STORAGE_SERVICE)

inline val Any.telecomManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<TelecomManager>(Context.TELECOM_SERVICE)

inline val Any.telephonyManager
    get() = getSystemService<TelephonyManager>(Context.TELEPHONY_SERVICE)

inline val Any.textServicesManager
    get() = getSystemService<TextServicesManager>(Context.TEXT_SERVICES_MANAGER_SERVICE)

inline val Any.tvInputManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService<TvInputManager>(Context.TV_INPUT_SERVICE)

inline val Any.uiModeManager
    get() = getSystemService<UiModeManager>(Context.UI_MODE_SERVICE)

inline val Any.usbManager
    get() = getSystemService<UsbManager>(Context.USB_SERVICE)

inline val Any.userManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = getSystemService<UserManager>(Context.USER_SERVICE)

inline val Any.vibrator
    get() = getSystemService<Vibrator>(Context.VIBRATOR_SERVICE)

inline val Any.wallpaperManager
    get() = getSystemService<WallpaperManager>(Context.WALLPAPER_SERVICE)

inline val Any.wifiP2pManager
    get() = getSystemService<WifiP2pManager>(Context.WIFI_P2P_SERVICE)

inline val Any.wifiManager
    get() = getContextFromSource(this).applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

inline val Any.windowManager
    get() = getSystemService<WindowManager>(Context.WINDOW_SERVICE)
