package com.roysylva.rxjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    //here we create a string and set it as an observable item
    private String greeting = "Hello from RxJAva";

    private Observable<String> myObservable;

    //then we create an observer for the observable
    //we can always create more than one observer for an observable
    private DisposableObserver<String> myObserver;

    private DisposableObserver<String> myObserver2;
    private static final String TAG = "MainActivity";
    private TextView textView;
   // private Disposable disposable;


    //composite disposable helps to clear all subscriptions when you have more than one observer
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tvView);

        //just operator converts an item to observable
        myObservable = Observable.just(greeting);


                //observer with no disposabele interface

       /* myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                Log.e(TAG,"on subscribe invoked");
                disposable = d;


            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e(TAG,"on next invoked");
                textView.setText(s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"on error invoked");

            }

            @Override
            public void onComplete() {
                Log.e(TAG,"on complete invoked");

            }
        };
*/
        //observer with disposable interface
        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {

                Log.e(TAG,"on next invoked");
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"on error invoked");
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"on complete invoked");
            }
        };

        //here we add observers to the composite disposable
        compositeDisposable.add(
        myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myObserver));



        myObserver2 = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {

                Log.e(TAG,"on next invoked");
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"on error invoked");
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"on complete invoked");
            }
        };

        compositeDisposable.add(
                myObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(myObserver2));

        compositeDisposable.add(
                myObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // disposable.dispose();

       // myObserver.dispose();
       // myObserver2.dispose();

        //here we call composite diposable to clear all subscriptions in order to prevent any memory leak
        compositeDisposable.clear();


        /*//What is the difference between clear() and dispose() ?
When you are using CompositeDisposable, If you call to dispose() method, you will no longer be able to add disposables to that composite disposable.

But if you call to clear() method you can still add disposable to the composite disposable . Clear() method just clears the disposables that are currently held within the instance. */
    }


    private DisposableObserver getObserver(){
        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {

                Log.e(TAG,"on next invoked");
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"on error invoked");
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"on complete invoked");
            }
        };

        return myObserver;
    }
}