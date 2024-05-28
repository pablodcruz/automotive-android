package com.example.garagedooropener;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Session;
import androidx.car.app.Screen;
import androidx.car.app.CarAppService;
import androidx.car.app.validation.HostValidator;

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
