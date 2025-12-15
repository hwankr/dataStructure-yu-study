package dataStructure.termProject;
import java.util.*;

public class GameServerMatching {
 
 // 1. 최대 MMR 점수 (예: 1,000,000점)
 // -> 점수가 높을수록 플레이어가 분산되어 매칭이 어려워집니다.
 private static final int MAX_MMR = 1_000_000;

 // 2. 매칭 허용 범위 (예: +- 10점)
 // -> 0이면 정확히 같은 점수만, 높을수록 매칭이 잘 됩니다.
 private static final int MATCH_RANGE = 10;

 // 3. Bucket 알고리즘용 버킷 크기 (예: 50점 단위)
 private static final int BUCKET_SIZE = 50;

 // 4. 테스트할 입력 크기 목록 (N명)
 // -> 300만 명(3,000,000) 테스트 시 Bucket의 성능이 극대화됩니다.
 private static final int[] INPUT_SIZES = { 1_000, 10_000, 50_000, 100_000};

 // 데이터 생성기
 public static List<Player> generateData(int count) {
     List<Player> data = new ArrayList<>(count);
     Random random = new Random();
     for (int i = 0; i < count; i++) {
         data.add(new Player(i, random.nextInt(MAX_MMR + 1)));
     }
     return data;
 }

 public static void runTest(MatchMaker matcher, List<Player> dataset, int inputSize) {
     long startTime = System.nanoTime();

     for (Player p : dataset) {
         p.setEntryTime(System.nanoTime()); 
         matcher.addPlayer(p);
     }

     long endTime = System.nanoTime();
     double executionTimeMs = (endTime - startTime) / 1_000_000.0;

     System.out.printf("%-8d | %-15s | %10.2f | %12d | %10.4f | %10.4f | %10.2f%n",
             inputSize,
             matcher.getName(),
             executionTimeMs,
             matcher.getMatchCount(),
             matcher.getAvgWaitMs(),
             matcher.getMaxWaitMs(),
             matcher.getAvgMMRDiff());
 }

 public static void main(String[] args) {
     System.out.println("=== Game Server Matching System Simulation ===");
     System.out.println("Current Settings:");
     System.out.println(" - Max MMR: " + MAX_MMR);
     System.out.println(" - Match Range: +/- " + MATCH_RANGE);
     System.out.println(" - Bucket Size: " + BUCKET_SIZE);
     System.out.println("---------------------------------------------------------------------------------------------------------");
     System.out.printf("%-8s | %-15s | %-10s | %-12s | %-10s | %-10s | %-10s%n", 
         "Input(N)", "Algorithm", "Time(ms)", "MatchedPairs", "AvgWaitMs", "MaxWaitMs", "AvgMMRdiff");
     System.out.println("---------------------------------------------------------------------------------------------------------");

     for (int n : INPUT_SIZES) {
         List<Player> dataset = generateData(n);

         // 1. Unsorted List
         runTest(new UnsortedListMatcher(MATCH_RANGE), new ArrayList<>(dataset), n);

         // 2. Sorted List
         runTest(new SortedListMatcher(MATCH_RANGE), new ArrayList<>(dataset), n);

         // 3. Balanced BST
         runTest(new BSTMatcher(MATCH_RANGE), new ArrayList<>(dataset), n);

         // 4. Bucket System
         runTest(new BucketMatcher(MATCH_RANGE, MAX_MMR, BUCKET_SIZE), new ArrayList<>(dataset), n);
         
         System.out.println("---------------------------------------------------------------------------------------------------------");
     }
 }
}

class Player {
 public int id;
 public int mmr;
 public long entryTime; 

 public Player(int id, int mmr) {
     this.id = id;
     this.mmr = mmr;
 }

 public void setEntryTime(long time) {
     this.entryTime = time;
 }
}

abstract class MatchMaker {
 protected int matchRange;
 protected int matchCount;
 
 protected long totalWaitTimeNano;
 protected long maxWaitTimeNano;
 protected long totalMMRDiff;

 public MatchMaker(int range) {
     this.matchRange = range;
     this.matchCount = 0;
     this.totalWaitTimeNano = 0;
     this.maxWaitTimeNano = 0;
     this.totalMMRDiff = 0;
 }

 public abstract void addPlayer(Player p);
 public abstract String getName();

 protected void onMatchSuccess(Player p1, Player p2) {
     matchCount++;
     long now = System.nanoTime();
     
     long wait1 = now - p1.entryTime;
     long wait2 = now - p2.entryTime;

     totalWaitTimeNano += (wait1 + wait2);
     maxWaitTimeNano = Math.max(maxWaitTimeNano, Math.max(wait1, wait2));
     totalMMRDiff += Math.abs(p1.mmr - p2.mmr);
 }

 public int getMatchCount() { return matchCount; }
 
 public double getAvgWaitMs() {
     if (matchCount == 0) return 0.0;
     return (totalWaitTimeNano / (double)(matchCount * 2)) / 1_000_000.0;
 }

 public double getMaxWaitMs() {
     return maxWaitTimeNano / 1_000_000.0;
 }

 public double getAvgMMRDiff() {
     if (matchCount == 0) return 0.0;
     return (double) totalMMRDiff / matchCount;
 }
}