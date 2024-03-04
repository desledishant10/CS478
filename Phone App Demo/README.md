PhoneAppDemo


Overview
PhoneAppDemo is an Android application developed as part of the CS478 - Software Development for Mobile Platforms course. The app showcases the use of implicit intents to interact with the phone's dialer and messaging applications, allowing users to enter a phone number, validate its format, and either dial the number or compose a message to it without sending. It features dynamic layout adjustments for different device orientations and ensures state preservation across configuration changes.


Features
-  Phone Number Validation: Supports two formats for phone numbers.
-  Dialer Intent: Opens the phone's dialer with the entered and validated phone number.
-  Messaging Intent: Opens the messaging app to compose a message to the entered and validated phone number.
-  Dynamic Layouts: Adjusts layout based on device orientation.
-  State Preservation: Retains user input across configuration changes.


Installation
To run PhoneAppDemo, you will need:
-  Android Studio.
-  An AVD (Android Virtual Device) with Pixel 5 specifications running Android 14 (API Level 34).
-    Steps:
  -  Clone the repository or download the project to your local machine.
  -  Open Android Studio and import the project.
  -  Set up a Pixel 5 AVD via the AVD Manager in Android Studio, selecting an Android 14 (API Level 34) system image.
  -  Run the app on the AVD by pressing the 'Run' button or Shift + F10.


Usage
-  Start the app. You'll be greeted with a simple UI prompting you to enter a phone number.
-  Enter a phone number in one of the accepted formats.
-  Press either "Dial" to open the dialer app or "Message" to compose a message in the messaging app.
-  If the entered phone number is in an incorrect format, a toast message will inform you of the invalid input.


Permissions
-  QUERY_ALL_PACKAGES: Initially considered for querying available apps for handling intents, but replaced with specific <queries> in the AndroidManifest.xml for improved privacy and security, following best practices as of Android 12.
