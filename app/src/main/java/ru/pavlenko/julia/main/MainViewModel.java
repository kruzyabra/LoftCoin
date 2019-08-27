package ru.pavlenko.julia.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.pavlenko.julia.R;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> mTitle = new MutableLiveData<>();

    private final MutableLiveData<Integer> mSelectedId = new MutableLiveData<>();

    public MainViewModel() {
        mSelectedId.postValue(R.id.wallets);
    }

    public void setTitle(String title) {
        mTitle.postValue(title);
    }

    public void setSelectedId(int id) {
        mSelectedId.postValue(id);
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }

    public LiveData<Integer> getSelectedId() {
        return mSelectedId;
    }
}
