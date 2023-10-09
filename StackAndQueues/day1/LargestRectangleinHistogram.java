import java.util.Stack;

public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] h) {
        int n=h.length;
        Stack<Integer> st = new Stack<>();

        // left smaller
        int ri[] = new int[n];

        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty() && h[st.peek()]>=h[i]){
                st.pop();
            }
            if(st.isEmpty())
                ri[i]=n-1;
            else{
                ri[i]=st.peek()-1;
            }
            st.push(i);
        }
        //empty the stack
        while(!st.isEmpty())
            st.pop();

        //right smaller
        int[] li = new int[n];
        for (int i = 0; i < n; i++) {
            while(!st.isEmpty() && h[st.peek()]>=h[i]){
                st.pop();
            }
            if(st.isEmpty())
                li[i]=0;
            else{
                li[i]=st.peek()+1;
            }
            st.push(i);
        }
//        System.out.println(Arrays.toString(li));

        //Area
        int maxArea=Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int area = (ri[i]-li[i]+1)*h[i];
            maxArea=Math.max(maxArea,area);
        }
        // System.out.println(maxArea);
        return maxArea;
    }
}
