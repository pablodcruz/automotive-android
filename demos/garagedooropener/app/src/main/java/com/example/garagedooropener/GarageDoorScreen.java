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
