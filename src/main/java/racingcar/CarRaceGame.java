package racingcar;
import camp.nextstep.edu.missionutils.Randoms;
import controller.InputValidator;
import model.Player;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarRaceGame {

    public static void run() {
        // Application에다가 넣기(?) - 리펙토링
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분");
        String userInputName = readLine();
        String[] userNames = saveName(userInputName); //saveName(); 입력
        Player[] players = makePlayers(userNames, userNames.length); // player 생성
        System.out.println("시도할 횟수는 몇회인가요?");
        int gameCount = Integer.parseInt(readLine());
        System.out.println("실행 결과");
        for(int i = 0; i < gameCount; i++){
            generateRandomNumber(players);
            printResult(players);
            System.out.println();
        }
        System.out.print("최종 우승자 : ");
        List<String> winners = getWinner(players);
        for(int i = 0; i < winners.size(); i++){
            if(i == winners.size()-1)
                System.out.print(winners.get(i));
            else
                System.out.print(winners.get(i) + ", ");
        }
        // 입력한 값 숫자가 맞는지 에러처리기능(추후 메서드 화 시켜야함) - docs 변경 리펙토링
        // printResult(int n) - docs 변경 리펙토링
    }
    public static String[] saveName(String userInputName) {
        String[] userNames = InputValidator.exceptionHanding(userInputName);
        return userNames;
    }

    public static Player[] makePlayers(String[] userNames, int names) {
        Player[] players = new Player[names];
        for(int i = 0; i < names; i++) {
            players[i] = new Player(userNames[i]);
        }
        return players;
    }
    public static void generateRandomNumber(Player[] players) {
        for(int i = 0; i < players.length; i++) {
            players[i].setRandomNumber(Randoms.pickNumberInRange(0,9));
        }
    }
    public static void printResult(Player[] players) {
        for(int i = 0; i < players.length; i++){
            System.out.println(players[i].getName() + " : " + players[i].getDistanceLine());
        }
    }
    public static int getMaxDistance(Player[] players){
        int maxDistance = 0;
        for(Player player : players) {
            if (maxDistance < player.getDistance())
                maxDistance = player.getDistance();
        }
        return maxDistance;
    }
    public static List<String> getWinner(Player[] players){
        List<String> winners = new ArrayList<>();
        int maxDistance = getMaxDistance(players);
        for(Player player : players){
            if(player.getDistance() == maxDistance)
                winners.add(player.getName());
        }
        return winners;
    }

}
