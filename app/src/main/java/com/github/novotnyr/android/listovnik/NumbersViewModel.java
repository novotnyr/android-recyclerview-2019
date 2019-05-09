package com.github.novotnyr.android.listovnik;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NumbersViewModel extends ViewModel {
    private MutableLiveData<List<String>> numbers = new MutableLiveData<>();

    public NumbersViewModel() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(String.valueOf(i));
        }
        this.numbers.setValue(numbers);
    }

    public MutableLiveData<List<String>> getNumbers() {
        return numbers;
    }

    public void delete(int position) {
        List<String> newNumbers = new ArrayList<>(numbers.getValue());
        newNumbers.remove(position);
        numbers.postValue(newNumbers);
    }
}
