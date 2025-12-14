import java.util.*;

public class UnsortedListMatcher extends MatchMaker {
    private List<Player> waitingQueue;

    public UnsortedListMatcher(int range) { 
        super(range);
        this.waitingQueue = new ArrayList<>();
    }

    @Override
    public void addPlayer(Player newPlayer) {
        Iterator<Player> it = waitingQueue.iterator();
        while (it.hasNext()) {
            Player p = it.next();
            if (Math.abs(p.mmr - newPlayer.mmr) <= matchRange) {
                it.remove();
                onMatchSuccess(p, newPlayer);
                return;
            }
        }
        waitingQueue.add(newPlayer);
    }

    @Override
    public String getName() { return "Unsorted List"; }
}