import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Быки и коровы");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите тип игры:\nбез ограничений: 0\n по попыткам: 1\n по времени: 2");

        int gameType = scanner.nextInt();

        Back back = new Back(gameType);

        while (true) {
            System.out.println("Игра началась");
            back.startGame();

            System.out.print("Если хотите закончить игру напишите (y/n): ");
            if ("y".equals(scanner.next())) {
                System.out.println("Игра закончена");
                break;
            }

            System.out.print("Если вы хотите поменять режим напишите его номер: ");
            int newGameType = scanner.nextInt();

            if (newGameType != gameType) {
                back.setGameType(newGameType);
            }
        }
    }
}
