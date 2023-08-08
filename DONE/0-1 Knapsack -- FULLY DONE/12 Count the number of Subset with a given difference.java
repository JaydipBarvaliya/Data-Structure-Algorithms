// Explaination 
// Let sum of subset 1 be s1 and subset 2 with s2
// s1 - s2 = diff (given)
// s1 + s2=sum of array (logical)
// Therefore adding both eq we get :
// 2s1= diff + sum of array
// s1= (diff + sum of array)/2;
// Problem reduces to find no of subsets with given sum**



int countWithGivenSum(int arr[ ], int n, int diff){   
     
    int totalSum=0;
    for(int i=0;i<n;i++)
         totalSum = totalSum + nums[i]
	 
	 int reqSum = (diff + totalSum)/2;
	 return subsetSum(arr, n, reqSum);
}



int subsetSum(int a[], int n, int sum){
    // Initializing the matrix
    int matrix[n + 1][sum + 1];
    
    // Initializing the first value of matrix
    matrix[0][0] = 1;
    
    for (int i = 1; i <= sum; i++)
        matrix[0][i] = 0;
    for (int i = 1; i <= n; i++)
        matrix[i][0] = 1;
 
    for (int i = 1; i <= n; i++){
        for (int j = 1; j <= sum; j++){
          
          // if the value is greater than the sum
            if (a[i - 1] <= j)
                matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - a[i - 1]]; 
            else
                matrix[i][j] = matrix[i - 1][j];
        }
    }

    return matrix[n][sum];
}









int countWithGivenSum(int arr[ ], int n, int diff){  

    int totalSum  = 0;
    for(int ele :  arr)
            totalSum = totalSum + ele;

    int s1 = (diff  + totalSum)/2
    return counts1(arr, s1);
}


public int countS1(int arr[], int sum){

        int n = arr.length;

    int[][] matrix = new int[n+1][sum+1];


    for(int j=0; j<sum+1; j++)
        matrix[0][j] = false;
        
    for(int i=0; i<n+1; i++)
        matrix[i][0] = true;

    for(i=1; i<n+1; i++){
        for(j=1; j<sum+1; j++){

            if(arr[i-1] < j){
                matrix[i][j] = matrix[i-1][j-arr[i-1]] + matrix[i-1][j];
            }else{
                matrix[i][j] = matrix[i-1][j];
            }
        }
        return matrix[n][sum];
    }
}


