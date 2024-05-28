package com.example.garagedooropener;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.car.app.Session;
import androidx.car.app.Screen;
import androidx.car.app.validation.HostValidator;

public class GarageDoorSession extends Session {

    @NonNull
    @Override
    public Screen onCreateScreen(@NonNull Intent intent) {
        return new GarageDoorScreen(getCarContext());
    }

}
