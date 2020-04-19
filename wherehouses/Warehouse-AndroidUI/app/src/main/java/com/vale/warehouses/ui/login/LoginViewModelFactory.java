//package com.vale.warehouses.ui.login;
//
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.annotation.NonNull;
//
//import com.vale.warehouses.repository.LoginRepository;
//import com.vale.warehouses.service.LoginDataSource;
//
///**
// * ViewModel provider factory to instantiate LoginViewModel.
// * Required given LoginViewModel has a non-empty constructor
// */
//public class LoginViewModelFactory implements ViewModelProvider.Factory {
//
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
//            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(getApplicationContext())));
//        } else {
//            throw new IllegalArgumentException("Unknown ViewModel class");
//        }
//    }
//}
