package com.azyu.myfakeshop.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.azyu.myfakeshop.models.ModelM;
import com.azyu.myfakeshop.repos.JemRepo;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private JemRepo jemRepo;
    private LiveData<List<ModelM>> modelMResponseLiveData;
    public HomeViewModel() {
        jemRepo = new JemRepo();
        modelMResponseLiveData = jemRepo.getDashJeminyList();
    }

    public LiveData<List<ModelM>> getModelMResponseLiveData() {
        return modelMResponseLiveData;
    }
}