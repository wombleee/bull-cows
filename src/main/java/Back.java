import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Back {
    private int gameType;
    private boolean isWin = false;

    public Back(int gameType){
        this.gameType = gameType;
    }

    private static int randNum(final double max) {
        return (int)(Math.random() * max);
    }

    private boolean getResult() {
        return this.isWin;
    }

    public void setGameType(int gameType){
        this.gameType = gameType;
    }

    private String genRandomNumber(){
        ArrayList<String> result = new ArrayList<>();
        while (result.size() < 4) {
            String num = String.valueOf(randNum(10));
            if (!result.contains(num)){
                result.add(num);
            }
        }

        return String.join("", result);
    }

    private String checkWin(String step, String ranNum) {
        ArrayList<String> stepArr = new ArrayList<>(Arrays.asList(step.split("")));
        ArrayList<String> ranNumArr = new ArrayList<>(Arrays.asList(ranNum.split("")));

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < stepArr.size(); i++) {
            if (stepArr.get(i).equals(ranNumArr.get(i))) {
                bulls += 1;
            } else {
                if (ranNumArr.contains(stepArr.get(i))){
                    cows += 1;
                }
            }
        }
        if (bulls == 4){
            this.isWin = true;
        }
        return String.format("%dБ%dК", bulls, cows);
    }

    private boolean gameStep(Scanner scanner, String randomNum){
        System.out.print("Введите число: ");
        String userNum = scanner.next();

        if (userNum.length() !=4){
            System.out.println("Длина введенного вами числа меньше 4 символов");
            return false;
        } else {
            String userStepResult = checkWin(userNum, randomNum);
            System.out.println(userStepResult);
            return true;
        }
    }
    
    public void startGame(){
        String randomNum = genRandomNumber();
        Scanner scanner = new Scanner(System.in);

        if (gameType == 0){
            while (!getResult()){
                gameStep(scanner, randomNum);
            }
            System.out.format("Поздравляю, вы угадали загаданное число: %s \n", randomNum);

        } else if (gameType == 1) {
            int maxStepCount = 7;
            int userStepCount = 0;

            while (!getResult()){
                if (gameStep(scanner, randomNum)) {
                    userStepCount += 1;
                    if (userStepCount > maxStepCount) {
                        System.out.format("Вы превысили число попыток, загаданное число: %s \n", randomNum);
                        break;
                    }
                }
            }
            if (this.isWin){
                System.out.format("Поздравляю, вы угадали загаданное число: %s \n", randomNum);
                String resText = "";
                if (userStepCount == 1){
                    resText = "попытку";
                } else if (userStepCount > 1 && userStepCount < 5) {
                    resText = "попытки";
                } else if (userStepCount > 4) {
                    resText = "попыток";
                }
                System.out.format("Вы справились за %d %s \n", userStepCount, resText);
            }
        } else if (this.gameType == 2) {
            long maxGameTime = System.currentTimeMillis() / 1000 + 10;
            long usedTime = 0;

            while (!getResult()){
                long startStepTime = System.currentTimeMillis() / 1000;
                gameStep(scanner, randomNum);
                long endStepTime = System.currentTimeMillis() / 1000;
                usedTime =  10 - (maxGameTime - endStepTime);

                if (startStepTime > maxGameTime || endStepTime > maxGameTime){
                    System.out.format("Вы превысили время игры, загаданное число: %s \n", randomNum);
                    break;
                }
            }
            if (this.isWin){
                System.out.format("Поздравляю, вы угадали загаданное число: %s \n", randomNum);
                System.out.format("Вы справились за %d сек \n", usedTime);
            }
        }
        this.isWin = false;
    }
}
