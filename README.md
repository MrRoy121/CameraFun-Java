# **CameraFun**

## **Overview**

**CameraFun** is an Android application that combines camera functionality with random audio playback. This project is designed to help developers understand advanced fragment management and camera handling in Android.

## **Features**

- **Camera Preview**: Displays a live camera preview using the device's camera.
- **Random Audio Playback**: Plays random audio clips based on user interaction.

## **Technical Details**

- **Permissions**: Requests camera permissions at runtime.
- **Switch Control**: Enables or disables random audio playback.
- **Media Player**: Utilizes MediaPlayer to play audio clips.
- **Handler**: Uses a Handler to manage delayed tasks for audio playback.

## **How It Works**

1. **Camera Setup**: 
    - Checks for camera permissions.
    - Initializes the camera and sets the display orientation.
    - Starts the camera preview in a `LinearLayout`.

2. **Switch Control**:
    - Uses a `Switch` to control whether the random audio playback is enabled or not.
    - The state of the switch is logged for debugging purposes.

3. **Random Audio Playback**:
    - Maps integers to specific audio clips.
    - Uses a `Handler` to periodically select and play a random audio clip if the switch is enabled.
    - Displays a toast message with a random string from a predefined `HashMap`.

## **Project Structure**

- **MainActivity**: Handles the main application logic, including camera setup and switch control.
- **CameraPreview**: Custom SurfaceView class to handle camera preview display and refresh.

## **Installation**

1. Clone the repository:
   ```bash
   git clone https://github.com/MrRoy121/CameraFun-Java.git