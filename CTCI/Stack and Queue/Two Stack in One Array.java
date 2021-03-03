class TwoStack
{

	int size;
	int top1,top2;
	int arr[] = new int[100];

	TwoStack()
	{
		size = 100;
		top1 = -1;
		top2 = size;
	}
}*/

class Stacks
{
    void push1(int x, TwoStack sq)
    {
            sq.top1++;
            sq.arr[sq.top1] = x;
    }

    void push2(int x, TwoStack sq)
    {
            sq.top2--;
            sq.arr[sq.top2--] = x;
    }

    int pop1(TwoStack sq)
    {
//      if(sq.top1 == -1){
//          return -1;
//      }        
// 	int temp = sq.arr[sq.top1];
// 	sq.top1--;
// 	return temp;

        
        return sq.top1 == -1 ? -1: sq.arr[sq.top1--];
    }

    int pop2(TwoStack sq)
    {
        
        return sq.top2 == sq.size ? -1: sq.arr[sq.top2++];
    }
}
