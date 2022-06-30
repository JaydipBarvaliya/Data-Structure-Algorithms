import java.util.*;

class Program {

  

  int staircaseTraversal(int height, int maxSteps) {
  
        int[][] dp = new int[height+1][maxSteps+1];

    for(int[] row : dp)
        Arrays.fill(row, -1);
    
		return solve(height, maxSteps, dp);
  }

  int solve(int height, int maxSteps, int[][] dp ){

      if(height<2) return 1;

        if(dp[height][maxSteps] != -1) return dp[height][maxSteps];
      

      int ans=0;

      for(int step=1; step<= Math.min(height, maxSteps) ; step++){
          ans = ans + solve(height-step, maxSteps, dp);
      }

      dp[height][maxSteps]  = ans;
    
    return dp[height][maxSteps];
  }
}
