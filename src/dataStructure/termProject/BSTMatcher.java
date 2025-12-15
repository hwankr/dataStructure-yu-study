package dataStructure.termProject;

import java.util.*;

public class BSTMatcher extends MatchMaker {
    private TreeSet<Player> treeQueue;

    public BSTMatcher(int range) {
        super(range);
        this.treeQueue = new TreeSet<>((p1, p2) -> {
            if (p1.mmr != p2.mmr) return Integer.compare(p1.mmr, p2.mmr);
            return Integer.compare(p1.id, p2.id);
        });
    }

    @Override
    public void addPlayer(Player newPlayer) {
        Player target = new Player(-1, newPlayer.mmr - matchRange);
        Player candidate = treeQueue.ceiling(target);

        if (candidate != null && candidate.mmr <= newPlayer.mmr + matchRange) {
            treeQueue.remove(candidate);
            onMatchSuccess(candidate, newPlayer);
        } else {
            treeQueue.add(newPlayer);
        }
    }

    @Override
    public String getName() { return "Balanced BST"; }
}