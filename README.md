# ⏭ Skipper

An Android app that automatically skips YouTube ads using Android Accessibility Services.

## Features
- Auto-skips YouTube ads instantly
- Only activates when YouTube is open
- No screenshots or OCR — fast and battery friendly
- Simple on/off toggle via Accessibility Settings

## Requirements
- Android 8.0 (API 26) or higher
- YouTube app installed

## Installation
1. Download the latest APK from [Releases](../../releases)
2. On your Android phone, enable **Install from unknown sources**
3. Install the APK
4. Open Skipper and tap **Enable Skipper**
5. Find Skipper in Accessibility Settings and toggle it ON
Note: Depending on your phone, you might need to authorize the app to skip the security checks

## How It Works
Skipper uses Android's Accessibility Service API to monitor YouTube's screen content. When a Skip button appears during an ad, Skipper taps it automatically.

## ⚠️ Banking Apps
Some banking apps detect Accessibility Services and may show a security warning. Before opening banking apps:
1. Open Skipper
2. Tap "Enable Skipper"
3. Find Skipper in the list and toggle it OFF
4. Re-enable it when done

## Disclaimer
This app is intended for personal use only. It is not affiliated with or endorsed by YouTube or Google.

## License
This project is licensed under CC BY-NC 4.0 — free to use and modify, but not for commercial purposes.
See [LICENSE](LICENSE) for details.