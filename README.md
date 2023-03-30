# DanceSync

DanceSync is an Android application that uses deep learning to estimate human body pose and analyze dance performances. The app allows users to compare their dance performance with a reference performance or compare two dancers performing the same dance. By leveraging state-of-the-art pose estimation models like MoveNet or PoseNet, DanceSync provides real-time feedback on how well the user is performing a particular dance and generates a score measuring the deviation between the performances.

## Features

- Real-time pose estimation using TensorFlow Lite
- Comparison of dance performances using Dynamic Time Warping (DTW)
- Visual feedback with skeleton overlays on video frames
- Supports a variety of dance styles and skill levels

## App Structure

DanceSync is divided into several modules, each dealing with different aspects of the application:

- Front-end UI: User interface components, including camera feed and settings
- Pose estimation: Integration of TensorFlow Lite models for body pose estimation
- Dance comparison: Algorithms for comparing and scoring dance performances
- Data handling: Utilities for handling data storage and user preferences

## Getting Started

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Download the pre-trained MoveNet Lightning model in the TensorFlow Lite format (.tflite) and place it in the `app/src/main/assets` folder of your project.
4. Build and run the application on your Android device or emulator.

## Contributing

DanceSync is a work-in-progress, and contributions are welcome! Please feel free to open issues or submit pull requests to improve the app's functionality and performance.

## License

DanceSync is released under the [MIT License](LICENSE).

