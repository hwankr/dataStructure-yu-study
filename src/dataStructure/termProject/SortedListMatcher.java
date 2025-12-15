package dataStructure.termProject;
import java.util.*;

public class SortedListMatcher extends MatchMaker {
    private ArrayList<Player> sortedQueue;

    public SortedListMatcher(int range) { 
        super(range);
        this.sortedQueue = new ArrayList<>();
    }

    @Override
    public void addPlayer(Player newPlayer) {
        int targetMMR = newPlayer.mmr - matchRange;
        int low = 0, high = sortedQueue.size();
        int idx = high;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sortedQueue.get(mid).mmr >= targetMMR) {
                idx = mid;
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        if (idx < sortedQueue.size()) {
            Player candidate = sortedQueue.get(idx);
            if (candidate.mmr <= newPlayer.mmr + matchRange) {
                sortedQueue.remove(idx);
                onMatchSuccess(candidate, newPlayer);
                return;
            }
        }

        int insertIdx = idx;
        while(insertIdx < sortedQueue.size() && sortedQueue.get(insertIdx).mmr < newPlayer.mmr) {
            insertIdx++;
        }
        sortedQueue.add(insertIdx, newPlayer);
    }

    @Override
    public String getName() { return "Sorted List"; }
}