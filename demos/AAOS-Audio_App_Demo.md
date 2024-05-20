# Audio Application in Android Automotive - Demo

## Description

The purpose of this demo is to delve into the inner workings of the Audio/Media center of the infotainment system. It's rather complex as it also has to tie into the chimes & alert sounds and the Vehicle HAL. Spending time to understand the differing layers & interactions will be key (See Image Below).

![](https://source.android.com/static/docs/automotive/images/14-audio-01.png)

## Prerequisites
- Basic knowledge of Android Automotive OS and its components.
- Experience with Android development, preferably with media playback.
- Familiarity with the concept of Hardware Abstraction Layer (HAL) in Android.
- Setup Android Studio with the necessary Android Automotive OS emulator.

## Objectives
- Understand the architecture and flow of audio management in Android Automotive.
- Implement basic audio playback that interacts with the vehicle's audio system.
- Explore and be able to explain how to handle system chimes and alerts within an app.

## Steps to Complete

### Step 1: Review the Audio Architecture
- Examine the provided diagram from the Android Automotive documentation to understand how audio is routed and managed within the system.
- Discuss the role of the Vehicle HAL in interfacing with the vehicle's audio hardware.

### Step 2: Set Up the Development Environment
- Ensure Android Studio is up-to-date and configured with the latest Android Automotive OS emulator.
- Clone the Turn-by-Turn Navigation with Audio example from GitHub to understand a practical implementation of audio in Android Automotive.

### Step 3: Create a Simple Audio Streaming App
- **Initialize a New Project**: Start a new Android Automotive project in Android Studio.
- **Develop the User Interface**: Design a simple interface using either XML layouts or Jetpack Compose (recommended) to control audio playback (play, pause, stop) and display audio status. 

### Step 4: Implement Audio Playback
- **Media Playback**: Utilize `MediaPlayer` to handle audio streams. Load sample audio files to test playback functionality.
- **Audio Focus Management (optional)**: Implement audio focus to ensure your app coexists with other audio sources, such as navigation prompts and system alerts.

### Step 5: Integrate with Vehicle Audio System
- **Use Vehicle HAL (optional)**: Access the Vehicle HAL APIs to understand how to send and receive audio-related commands to the vehicle’s hardware.
- **Handle Alerts and Chimes (optional)**: Learn how to trigger and manage vehicle-specific sounds using the appropriate APIs.

### Step 6: Test and Debug
- **Run the Application**: Test the app on the Android Automotive emulator, focusing on how it interacts with simulated vehicle audio systems.
- **Debugging**: Use Android Studio's logcat and debugging tools to troubleshoot and refine your application.

## Discussion Points
- Discuss/Document the challenges of managing multiple audio sources within a vehicle.
- Explore potential enhancements, such as dynamic audio adjustments based on vehicle data (e.g., speed-sensitive volume control).
- Explore and discuss how to implement the advanced steps from above.

## References

- [Android Automotoive Docs: Audio Overview](https://source.android.com/docs/automotive/audio)
- [Ignitarium: Early Audio in AAOS](https://ignitarium.com/early-audio-in-android-automotive/)
- [YT: Intro to AAOS - Chris Simmonds (Links to Audio Segment)](https://youtu.be/KVM5njlZ4sM?si=GwW8J1CDDXMduI9P&t=2159)
- [Turn-by-Turn Navigation with Audio Example](https://github.com/android/car-samples/tree/main/car_app_library/navigation)