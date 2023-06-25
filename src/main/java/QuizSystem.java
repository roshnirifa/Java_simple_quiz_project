import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class QuizSystem {
    public static void main(String[] args) throws IOException, ParseException {
        JSONArray userArray = utils.readJsonData("./src/main/resources/user.json");

        try {
            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();


            // Check user credentials
            boolean isLoggedIn = false;
            String role = "";

            for (int i = 0; i < userArray.toArray().length; i++) {
                JSONObject userObj = (JSONObject) userArray.get(i);
                String storedRole = (String) userObj.get("role");
                String storedPass = (String) userObj.get("password");
                String storedUsername = (String) userObj.get("username");

//               System.out.println(storedPass);

                if (storedUsername.equals(username) && storedPass.equals(password)) {
                    isLoggedIn = true;
                    role = storedRole;
                    break;
                }

            }
            // Process user based on role
            if (isLoggedIn) {
                if (role.equals("admin")) {
                    System.out.println("Welcome admin! Please create new questions in the question bank.");
                    addQuestions(scanner);

                } else if (role.equals("student")) {
                    System.out.println(" Welcome " + username + " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");

                    playQuiz(scanner);

                }
            } else {
                System.out.println(" Invalid username or password. Please try again.");
            }

            scanner.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    //add question function
    private static void addQuestions(Scanner scanner) throws IOException, ParseException {
        String filePath = "./src/main/resources/quiz.json";
        JSONArray questionArray;
        while (true) {
            JSONParser jsonParser = new JSONParser();
            questionArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
            JSONObject questionObject = new JSONObject();

            System.out.println("Input your question:");
            String question = scanner.nextLine();
            questionObject.put("question", question);
            System.out.println("Input option 1:");
            String option1 = scanner.nextLine();
            questionObject.put("option 1", option1);

            System.out.println("Input option 2:");
            String option2 = scanner.nextLine();
            questionObject.put("option 2", option2);

            System.out.println("Input option 3:");
            String option3 = scanner.nextLine();
            questionObject.put("option 3", option3);

            System.out.println("Input option 4");
            String option4 = scanner.nextLine();
            questionObject.put("option 4:", option4);

            System.out.println("What is the answer key?");
            int answerKey = scanner.nextInt();
            scanner.nextLine();
            questionObject.put("answerkey", answerKey);

            questionArray.add(questionObject);
            System.out.println(questionObject);


            System.out.println("Saved successfully! press 'q' for quit)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(String.valueOf(questionArray));
        fw.flush();
        fw.close();
    }



    // Play quiz function for students
    private static void playQuiz(Scanner scanner) throws IOException, ParseException {
        char ch = 's';
        do {
            JSONArray questionArray = utils.readJsonData("./src/main/resources/quiz.json");
//        JSONParser jsonParser = new JSONParser();
//        JSONArray questionArray = (JSONArray) jsonParser.parse(new FileReader("./src/main/resources/quiz.json"));
            Random random = new Random();
            Scanner input = new Scanner(System.in);
            int score = 0;

            for (int i = 0; i < 10; i++) {
                System.out.println("\n[Question " + (i + 1) + "]");
                JSONObject question = (JSONObject) questionArray.get(random.nextInt(questionArray.size()));

                System.out.println(question.get("question"));
                System.out.println("1. " + question.get("option 1"));
                System.out.println("2. " + question.get("option 2"));
                System.out.println("3. " + question.get("option 3"));
                System.out.println("4. " + question.get("option 4"));

                System.out.print("\nStudent:> ");
                int answer = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                int answerKey = ((Long) question.get("answerkey")).intValue();
                if (answer == answerKey) {
                    score++;
                }
            }

            System.out.println("\nQuiz finished!");
            System.out.println("Your score: " + score + " out of 10");

            if (score >= 8) {
                System.out.println("Excellent! You have got " + score + " out of 10");
            } else if (score >= 5) {
                System.out.println("Good. You have got " + score + " out of 10");
            } else if (score >= 2) {
                System.out.println("Very poor! You have got " + score + " out of 10");
            } else {
                System.out.println("Very sorry, you have failed. You have got " + score + " out of 10");
            }
            System.out.println("Would you like to start again? Press 's' for start or 'q' for quit");
            ch = input.next().charAt(0);
        }
        while (ch != 'q');


    }


}













