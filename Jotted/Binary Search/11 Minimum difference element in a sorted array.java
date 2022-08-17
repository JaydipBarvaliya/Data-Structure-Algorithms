//This problem can be solved in another way like, find floor and ceil of target and then find the minimum absolute differnce of them and return it.

//In this below example what we did....  if the target is present in the array then that is the element which generate minimum diff.
//If that is not present then we can say that the two elements which are nearer or both the side of elements in the array would be the candiate for the min 
//diff elements. so we can find those elements and then find the answers....so if we go for the Binary search then if key is not present in the array then
//our loop will stop at the place where our start and end cross each other and co-incidently that will be the ceil/floor or the two candiates who are near 
//to the our main key element 


  while (start <= end) {

	  int mid = start + (end - start) / 2;

	  if (arr[mid] == key) return arr[mid];
	  else if (arr[mid] > key) end = mid - 1;
	  else if (arr[mid] < key) end = mid + 1;
 }

 
 return (arr[mid] - arr[start] < arr[mid] - arr[end]) ? arr[start] : arr[end];