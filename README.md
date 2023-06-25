# Java_simple_quiz_project


## Project Summery
This is a software system implemented in Java that allows admin to create and manage multiple-choice questions (MCQs) in a quiz bank. The system also enables students to log in and take quizzes generated from the question bank.


## Technology Used
- Java

## Pre requidtes
- JDK 11
- Simple JSON

## Functionalities
- Admin users can:

  - Log in with their credentials and add new questions to the question bank.
  - Save MCQs with four options and their corresponding answer key in a JSON file.
- Student users can:

   - Log in with their credentials and take quizzes generated from the question bank.
   - Receive 10 randomly selected questions from the quiz bank.
   - Answer the questions and receive immediate feedback on their score.
   - View their overall performance at the end of the quiz. 

- marks are distributed like this
   - if mark is above 8>= message: Excellent! You have got [marks] out of 10
    - if mark is above 5>= but less than 8, message: Good. You have got [marks] out of 10

   - if mark is above 2>= but less than 5, message: Very poor! You have got [marks] out of 10

   - if mark is 0 or less than 2 message: Very sorry you are failed. You have got [marks] out of 10


## Files
- users.json: Contains user information, including admin and student credentials.
- quiz.json: Stores the questions, options, and answer keys for the quiz bank.

## login credential

- Admin:
  - username: admin
  - password: 1234

- Student:
  - username: rifa
  - password: 1234


## How to run
- ``git clone``


A report will be generate in your project source root


## A video output for better understanding

(https://drive.google.com/file/d/19PwoHIp4nrEU4jaWkVIysAt7fqJiXBJe/view?usp=sharing)