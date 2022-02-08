package com.roysylva.rxjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;



//in this section we will take a look at flatmap, concatMap operators

public class MoreRxOperations extends AppCompatActivity {
    private String greeting = "Hello from RxJAva";
    private DisposableObserver<String> myObserver2;
    private static final String TAG = "MoreRxOperations";
    private TextView textView;
    private Button button;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();




    //create operator
    private Observable<Student> myObservable;
    private DisposableObserver<Student> myObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_rx_operations);
        textView = (TextView)findViewById(R.id.tvView2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreRxOperations.this,MoreOperators.class);
                startActivity(intent);
            }
        });

        myObservable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Student> emitter) throws Throwable {
                ArrayList<Student> studentsArray = getStudents();

                for (Student student:studentsArray){
                    emitter.onNext(student);
                }
                emitter.onComplete();
            }
        });

        compositeDisposable.add(
                myObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())


                        //flatmap operator

                       /* .flatMap(new Function<Student, ObservableSource<?>>() {
                            @Override
                            public Observable<Student> apply(Student student) throws Throwable {

                                Student student1 = new Student();
                                student1.setName("Tom");

                                Student student2 = new Student();
                                student2.setName("Francis");

                                student.setName(student.getName().toUpperCase());
                                return Observable.just(student,student1,student2);
                            }
                        })*/

                        //concat map operator

                        .concatMap(new Function<Student, ObservableSource<?>>() {
                            @Override
                            public Observable<Student> apply(Student student) throws Throwable {

                                Student student1 = new Student();
                                student1.setName("Tom");

                                Student student2 = new Student();
                                student2.setName("Francis");

                                student.setName(student.getName().toUpperCase());
                                return Observable.just(student,student1,student2);
                            }
                        })
                        .subscribeWith(getObserver()));
    }

    private DisposableObserver getObserver(){
        myObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(@NonNull Student s) {

                Log.e(TAG, s.getName());

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

    private ArrayList<Student> getStudents(){

        ArrayList<Student> students = new ArrayList<>();

        Student student = new Student();
        student.setName("Mike");
        student.setAge(5);
        student.setEmail("a@mail.com");
        student.setRegistrationDate("5th March");
        students.add(student);

        Student student1 = new Student();
        student1.setName("Mike");
        student1.setAge(5);
        student.setEmail("a@mail.com");
        student1.setRegistrationDate("5th March");
        students.add(student1);

        Student student2 = new Student();
        student2.setName("Mike");
        student2.setAge(5);
        student.setEmail("a@mail.com");
        student2.setRegistrationDate("5th March");
        students.add(student2);

        return students;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}