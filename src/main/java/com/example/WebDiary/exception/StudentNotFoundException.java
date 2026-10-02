package com.example.WebDiary.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message){super(message);}
    public StudentNotFoundException() {super("Student Not Found");}

}
