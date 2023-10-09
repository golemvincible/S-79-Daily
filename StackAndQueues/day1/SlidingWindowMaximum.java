import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int n=nums.length;
        int[] res = new int[n-k+1];
        int ind=0;
        
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int i=0;i<n;i++){
            
            //checking if the ind is valid or not
            if(!q.isEmpty() && q.peek()==i-k)
                q.poll();
            
            /*
                if the top ele is smaller than curr element then we don't need the top
                ele so we will pop it and we will continue to do so till we get element which
                is large than curr ele because the elements are in increasing fasion so we 
                will pop from the back that's why we use deque
            */
            while(!q.isEmpty() && nums[q.peekLast()]<nums[i])
                q.pollLast();
            
            q.offer(i);
            
            //we will start finding our ans when we are at last index of our window
            /*
                consider k=3
                we won't find any ans for i=0,i=1 but as we move to i=2 we are at last index of our window
                so we know we got ans for that window
            */
            if(i>=k-1){
                res[ind]=nums[q.peek()];
                ind++;
            }
        }
        return res;
    }
}
