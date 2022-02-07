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
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private String greeting = "Hello from RxJAva";
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;
    private static final String TAG = "MainActivity";
    private TextView textView;
   // private Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tvView);

        myObservable = Observable.just(greeting);
        myObservable.subscribeOn(Schedulers.io());
        myObservable.observeOn(AndroidSchedulers.mainThread());


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

        myObservable.subscribe(myObserver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // disposable.dispose();
    }
}