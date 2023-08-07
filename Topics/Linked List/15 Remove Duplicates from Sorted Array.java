//REMEMBER  : The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

//Bruteforce Approch is to store all of them in Set and return the size of the set
class Solution {
    public int removeDuplicates(int[] nums) {


       Set<Integer> set = new TreeSet < > ();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

//Keep in mind that Set will retrive the data in random order...that is why we have used the TreeSet here...
        int i=0;
        for(int ele : set){
            nums[i] = ele;
            i++;
        }
        return set.size();
    }
}


// Time complexity: O(n*log(n))+O(n)
// Space Complexity: O(n) 

//---------------------------------------------------------------------------------------------------------------------------------



// There is a one confusion which can arise that why we are keeping the NND to the 1 from ///starting ....even if that is the case then why we are doing NND-1 in to the if condition.
// The reason is that, whenever we are finding the next non duplicate number we are taking the value of that variable and then replacing it to the the position where NND positioning...which means we are finding unique elem and pushing them to the front of the list...By doing this we also need take care of the comparision as well .....so in that case ...curr and NND-1 variable help us to do that....so NND has two use cases ....first to hold the replacement position and second is to hold the comparision element

// class Solution {
//     public int removeDuplicates(int[] nums) {

//         int nextNonDuplicate = 1;

//         for(int curr=0; curr<nums.length; curr++){

//             if(nums[curr] != nums[nextNonDuplicate - 1]){
//                 nums[nextNonDuplicate] = nums[curr];
//                 nextNonDuplicate++;
//             }
//         }

//         return nextNonDuplicate;

//     }
// }