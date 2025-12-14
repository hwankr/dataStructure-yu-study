import java.util.*;

public class BucketMatcher extends MatchMaker {
    private List<LinkedList<Player>> buckets;
    private int bucketSize;

    public BucketMatcher(int range, int maxMMR, int bucketSize) {
        super(range);
        this.bucketSize = bucketSize;
        
        int numBuckets = (maxMMR / bucketSize) + 1;
        buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new LinkedList<>());
        }
    }
    
    public BucketMatcher(int range) {
        this(range, 3000, 50);
    }

    @Override
    public void addPlayer(Player newPlayer) {
        int bucketIndex = newPlayer.mmr / bucketSize;
        if (bucketIndex >= buckets.size()) bucketIndex = buckets.size() - 1;
        
        int startB = Math.max(0, bucketIndex - 1);
        int endB = Math.min(buckets.size() - 1, bucketIndex + 1);

        for (int i = startB; i <= endB; i++) {
            Iterator<Player> it = buckets.get(i).iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if (Math.abs(p.mmr - newPlayer.mmr) <= matchRange) {
                    it.remove();
                    onMatchSuccess(p, newPlayer);
                    return;
                }
            }
        }
        buckets.get(bucketIndex).add(newPlayer);
    }

    @Override
    public String getName() { return "Bucket System"; }
}