import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CajaBolas {
    private List<Integer> drawnBalls = new ArrayList<>();
    private Random random = new Random();
    private final int total = 75;

    public int Bola() {
        int bola;
        do {
            bola = random.nextInt(total) + 1;
        } while (drawnBalls.contains(bola));
        drawnBalls.add(bola);
        return bola;
    }

    public List<Integer> getBolas() {
        return drawnBalls;
    }
}

