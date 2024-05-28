# IoT for Opening a Garage Door in Android Automotive - Demo

## Description

This demo demonstrates how to build an IoT app for Android Automotive to control a garage door opener. The app will use HTTP requests to communicate with a backend server that interfaces with the garage door opener. Although we are focusing on HTTP requests for simplicity, this implementation serves as a form of V2X (Vehicle-to-Everything) communication. We will explain how more advanced V2X technologies like DSRC and C-V2X can enhance this system but require more complex setup and integration.

## Prerequisites

- Basic knowledge of Android Automotive OS and its components.
- Experience with Android development, including handling HTTP requests.
- Familiarity with IoT concepts and communication protocols.

## Objectives

- Understand how to declare IoT category support in the Android Automotive manifest.
- Implement a basic Android Automotive app to control a garage door opener.
- Send HTTP requests from the app to a backend server to open and close the garage door.
- Understand how V2X communication can be implemented using the internet and discuss more advanced V2X technologies.

## Setup and Testing

### Step 1: Set Up the Development Environment

- Ensure Android Studio is up-to-date and configured with the latest Android Automotive OS emulator.
- Familiarize yourself with the Android Automotive Emulator's extended controls.

### Step 2: Declare IoT Category in Your Manifest

Add the `androidx.car.app.category.IOT` category in your `AndroidManifest.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Garagedooropener"
        tools:targetApi="31">
        <meta-data
            android:name="com.google.android.gms.car.application"
            android:resource="@xml/automotive_app_desc"/>
        <meta-data android:name="androidx.car.app.minCarApiLevel"
            android:value="1"/>

        <service
            android:name=".MyCarAppService"
            android:exported="true">
            <intent-filter>
                <action android:name="androidx.car.app.CarAppService" />
                <category android:name="androidx.car.app.category.IOT"/>
            </intent-filter>
        </service>

        <activity
            android:name="androidx.car.app.activity.CarAppActivity"
            android:exported="true"
            android:launchMode="singleTask"
            android:theme="@android:style/Theme.DeviceDefault.Light.DarkActionBar">

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

            <meta-data
                android:name="distractionOptimized"
                android:value="true" />
        </activity>
    </application>

</manifest>
```

### Step 3: Implement the Car App Service

Create a service that extends `CarAppService` to handle the logic for the car app.

#### MyCarAppService.java

```java
package com.example.garagedooropener;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Session;
import androidx.car.app.Screen;
import androidx.car.app.CarAppService;

public class MyCarAppService extends CarAppService {

    @NonNull
    @Override
    public HostValidator createHostValidator() {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR;
    }

    @NonNull
    @Override
    public Session onCreateSession() {
        return new GarageDoorSession();
    }
}
```

#### GarageDoorSession.java

Create a session that manages the lifecycle of your car app.

```java
package com.example.garagedooropener;

import androidx.annotation.NonNull;
import androidx.car.app.Session;
import androidx.car.app.Screen;

public class GarageDoorSession extends Session {

    @NonNull
    @Override
    public Screen onCreateScreen(@NonNull Intent intent) {
        return new GarageDoorScreen(getCarContext());
    }

}
```

### Step 4: Design the User Interface

Use the GridTemplate to display the garage door opener and allow user interaction.

#### GarageDoorScreen.java

```java
package com.example.garagedooropener;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;

public class GarageDoorScreen extends Screen {

    public GarageDoorScreen(@NonNull CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        ItemList.Builder listBuilder = new ItemList.Builder();

        listBuilder.addItem(
                new GridItem.Builder()
                        .setTitle("Garage Door")
                        .setImage(new CarIcon.Builder(IconCompat.createWithResource(
                                getCarContext(), R.drawable.ic_garage_door)).build())
                        .setOnClickListener(() -> openGarageDoor())
                        .build()
        );

        return new GridTemplate.Builder()
                .setTitle("Devices")
                .setHeaderAction(Action.APP_ICON)
                .setSingleList(listBuilder.build())
                .build();
    }

    private void openGarageDoor() {
        Log.d(TAG, "openGarageDoor: Garage Door Opening!");
        String urlString = "https://your-garage-door-api.com/open";
        String payload = "{}";
        String accessToken = "<your_access_token>";

        NetworkUtils.sendPostRequest(urlString, payload, accessToken);
    }
}

```

### Step 5: Handle Network Communication

Implement the logic to communicate with the garage door opener through HTTP requests.

#### NetworkUtils.java

```java
package com.example.garagedooropener;

import android.os.AsyncTask;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    public static void sendPostRequest(String urlString, String payload, String accessToken) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Authorization", "Bearer " + accessToken);

                    OutputStream os = connection.getOutputStream();
                    os.write(payload.getBytes());
                    os.close();

                    int responseCode = connection.getResponseCode();
                    System.out.println("POST Response Code: " + responseCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
```

### Step 6: Test and Debug

1. **Run the Application**: Test the app on the Android Automotive emulator.
2. **Debugging**: Use Android Studio's logcat and debugging tools to troubleshoot and refine your application.

## Explanation of V2X Technologies

### V2X Overview

- **V2X (Vehicle-to-Everything)**: A communication technology that enables vehicles to communicate with each other and with infrastructure, pedestrians, and networks.
- **Types of V2X Communication**:
  - **V2V (Vehicle-to-Vehicle)**: Communication between vehicles to enhance safety and traffic efficiency.
  - **V2I (Vehicle-to-Infrastructure)**: Communication between vehicles and traffic infrastructure for better traffic management.
  - **V2P (Vehicle-to-Pedestrian)**: Communication between vehicles and pedestrians to improve pedestrian safety.
  - **V2N (Vehicle-to-Network)**: Communication between vehicles and the broader network for internet access and real-time updates.

### Key Technologies

- **DSRC (Dedicated Short Range Communications)**: A wireless communication standard specifically designed for automotive use, enabling low-latency, high-reliability communication.
- **C-V2X (Cellular Vehicle-to-Everything)**: A communication technology based on cellular networks (4G/5G) that supports direct communication between vehicles and other V2X entities.

### V2X in Android Automotive

Android Automotive is an open-source operating system and platform designed for use in vehicles, allowing developers to create applications that interact with vehicle hardware and external services. Integrating V2X capabilities into Android Automotive can involve the following components:

- **V2X Communication Stack**: Integration of a V2X communication stack, either DSRC or C-V2X, into the vehicle’s hardware and software architecture.
- **Vehicle Hardware Abstraction Layer (VHAL)**: Extending the VHAL to support V2X properties and services, enabling the Android Automotive OS to interact with the V2X communication stack.
- **Application Layer**: Developing Android Automotive applications that leverage V2X data to provide safety alerts, traffic updates, and enhanced navigation features.

### Simplified V2X Communication Using HTTP Requests

In this tutorial, we are implementing a type of V2X communication using the internet connectivity of the In-Vehicle Infotainment (IVI) system. This approach leverages the existing cellular or Wi-Fi connectivity of the IVI system to send and receive messages to other devices connected to the internet, effectively simulating V2X communication. This method is simpler to implement compared to DSRC and C-V2X, which require specialized hardware and complex integration.

## Discussion Points

- **Challenges**: Discuss the challenges of managing real-time data in a vehicle environment.
- **Enhancements**: Explore potential enhancements, such as integrating predictive maintenance alerts.
- **Future Steps**: Discuss how to implement advanced V2X features using dedicated communication protocols like DSRC and C-V2X.

## References

- [Android Automotive Documentation](https://developer.android.com/cars)
- [MyQ API Documentation](https://developer.myq.com/)
- [Nexx API Documentation](https://docs.nexx.cloud/)
- [Gogogate API Documentation](http://www.gogogate.com/pdf/gogogate2-api.pdf)
- [Garadget API Documentation](https://docs.garadget.com/start/api/)
- [Android Studio Automotive Emulator - Extended Controls](https://developer.android.com/studio/run/emulator-extended-controls)
