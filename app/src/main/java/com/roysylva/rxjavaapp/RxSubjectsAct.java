package com.roysylva.rxjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class RxSubjectsAct extends AppCompatActivity {

    private static String TAG = "RxSubjectsAct";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_subjects);
        button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RxSubjectsAct.this,RxBindingActivity.class));
            }
        });

       /* behaviorSubjectDemo1();
        behaviorSubjectDemo();
        publishSubjectDemo();
        publishSubjectDemo1();*/
        replaySubjectDemo();
        replaySubjectDemo1();
     /*   asyncSubjectDemo();
        asyncSubjectDemo1();*/
    }

    void asyncSubjectDemo(){

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        Observable<String> observable = Observable.just("JAVA","KOTLIN","HTML");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());


    }

    void asyncSubjectDemo1(){

        AsyncSubject<String> asyncSubject = AsyncSubject.create();


        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("Kotlin");
        asyncSubject.subscribe(getSecondObserver());

        asyncSubject.onNext("Json");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());



    }
    //publish
    void publishSubjectDemo(){

        PublishSubject<String> publishSubject = PublishSubject.create();
        Observable<String> observable = Observable.just("JAVA","KOTLIN","HTML");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());


    }

    void publishSubjectDemo1(){

        PublishSubject<String> publishSubject = PublishSubject.create();


        publishSubject.subscribe(getFirstObserver());
        publishSubject.onNext("JAVA");
        publishSubject.onNext("Kotlin");
        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("Json");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());



    }

    void replaySubjectDemo(){

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        Observable<String> observable = Observable.just("JAVA","KOTLIN","HTML");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecondObserver());
        replaySubject.subscribe(getThirdObserver());


    }

    void replaySubjectDemo1(){

        ReplaySubject<String> replaySubject = ReplaySubject.create();


        replaySubject.subscribe(getFirstObserver());
        replaySubject.onNext("JAVA");
        replaySubject.onNext("Kotlin");
        replaySubject.subscribe(getSecondObserver());

        replaySubject.onNext("Json");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());



    }
//behavior
    void behaviorSubjectDemo1(){

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();


        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("Kotlin");
        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("Json");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());



    }

    void behaviorSubjectDemo(){

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();


        Observable<String> observable = Observable.just("JAVA","KOTLIN","HTML");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());



    }


    private Observer<String> getFirstObserver(){
        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"first subscribe");

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"first"+s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"first error"+e.toString());

            }

            @Override
            public void onComplete() {
                Log.i(TAG,"first completed");

            }
        };

        return myObserver;


    }
    private Observer<String> getSecondObserver(){
        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"second subscribe");

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"second"+s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"second error"+e.toString());

            }

            @Override
            public void onComplete() {
                Log.i(TAG,"second completed");

            }
        };

        return myObserver;


    }

    private Observer<String> getThirdObserver(){
        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"third subscribe");

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"third"+s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"third error"+e.toString());

            }

            @Override
            public void onComplete() {
                Log.i(TAG,"third completed");

            }
        };

        return myObserver;


    }

}