# AudioClip Player: A Dual-Application Audio Management System for Android

## Project Overview

This project involves the development of two Android applications, ClipServer and AudioClient, which together provide a comprehensive audio management system. The ClipServer app stores and plays audio clips, while the AudioClient app allows users to interact with ClipServer to control audio playback.

## Project Structure

The project is divided into two main applications:

1. **ClipServer**
2. **AudioClient**

### ClipServer

**ClipServer** is responsible for storing and playing a number of audio clips. It exposes a service API for clients to interact with, supporting functionality such as playing, pausing, resuming, and stopping audio clips. This service runs in the foreground to ensure continuous playback and user control.

#### File Structure

ClipServer/
├── app/
│ ├── src/
│ │ ├── main/
│ │ │ ├── aidl/com/example/clipserver/
│ │ │ │ └── IClipService.aidl
│ │ │ ├── java/com/example/clipserver/
│ │ │ │ ├── ClipService.java
│ │ │ │ └── MainActivity.java
│ │ │ ├── res/
│ │ │ │ ├── layout/
│ │ │ │ │ └── activity_main.xml
│ │ │ │ ├── values/
│ │ │ │ │ ├── strings.xml
│ │ │ │ │ ├── styles.xml
│ │ │ │ │ └── colors.xml
│ │ │ │ ├── mipmap/
│ │ │ │ │ └── ic_notification.png
│ │ │ └── AndroidManifest.xml
└── build.gradle


### AudioClient

**AudioClient** interacts with the ClipServer service to control audio playback. It allows the user to start and stop the service, play, pause, resume, and stop audio clips through a simple UI.

#### File Structure

AudioClient/
├── app/
│ ├── src/
│ │ ├── main/
│ │ │ ├── aidl/com/example/clipserver/
│ │ │ │ └── IClipService.aidl
│ │ │ ├── java/com/example/audioclient/
│ │ │ │ └── MainActivity.java
│ │ │ ├── res/
│ │ │ │ ├── layout/
│ │ │ │ │ └── activity_main.xml
│ │ │ │ ├── values/
│ │ │ │ │ ├── strings.xml
│ │ │ │ │ ├── styles.xml
│ │ │ │ │ └── colors.xml
│ │ │ └── AndroidManifest.xml
└── build.gradle


## Installation and Setup

### Prerequisites

- Android Studio
- Android SDK
- Gradle

### Steps to Set Up and Run the Applications

1. **Clone the repository**:
    ```
    git clone https://github.com/your-repo-url
    cd AudioClipPlayer
    ```

2. **Open the projects in Android Studio**:
    - Open Android Studio.
    - Select "Open an existing project".
    - Navigate to the `ClipServer` directory and open it.
    - Repeat the same for the `AudioClient` directory.

3. **Build the ClipServer project**:
    - Open the `ClipServer` project in Android Studio.
    - Sync the Gradle files.
    - Build the project to ensure there are no errors.

4. **Build the AudioClient project**:
    - Open the `AudioClient` project in Android Studio.
    - Sync the Gradle files.
    - Build the project to ensure there are no errors.

5. **Run the ClipServer application**:
    - Run the ClipServer application on an Android emulator or physical device.
    - Ensure that the service is running and the audio clips are correctly set up.

6. **Run the AudioClient application**:
    - Run the AudioClient application on an Android emulator or physical device.
    - Use the UI to start the service, and interact with the audio clips.

## Functionality and Usage

### ClipServer

- **Play Clip**: Start playing a specified audio clip.
- **Pause Clip**: Pause the currently playing audio clip.
- **Resume Clip**: Resume the paused audio clip.
- **Stop Clip**: Stop the currently playing audio clip.

### AudioClient

- **Start Service**: Start the ClipServer service.
- **Stop Service**: Stop the ClipServer service.
- **Play Clip**: Select and play a specified audio clip from the ClipServer.
- **Pause Clip**: Pause the currently playing audio clip.
- **Resume Clip**: Resume the paused audio clip.
- **Stop Clip**: Stop the currently playing audio clip.

### UI Elements

- **Service Status**: Display the current status of the ClipServer service.
- **Buttons**: Control buttons for starting, stopping the service, and managing audio playback (play, pause, resume, stop).

## Troubleshooting

- Ensure both applications are installed on the same device/emulator.
- Start the ClipServer service before attempting to control playback from AudioClient.
- Check logcat for any error messages or issues with service binding.


## Acknowledgements

- This project was developed as part of the CS478: Software Development for Mobile Platforms course at University of Illinois at Chicago.

