import java.util.*;

class Program {

  int staircaseTraversal(int height, int maxSteps) {
		return solve(height, maxSteps);
  }

  int solve(int height, int maxSteps){

      if(height<2) return 1;

      int ans=0;

      for(int step=1; step<= Math.min(height, maxSteps) ; step++){
          ans = ans + solve(height-step, maxSteps);
      }

      return ans;
  }
}
