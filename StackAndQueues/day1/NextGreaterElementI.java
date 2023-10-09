import java.util.HashMap;

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        HashMap<Integer,Integer> m = new HashMap<>();
        for(int i=0;i<nums2.length;i++){
            m.put(nums2[i],i);
        }
        for(int i=0;i<nums1.length;i++){
            int ele=nums1[i];
            int gele=-1;
            int j=m.get(ele)+1;
            while(j<nums2.length){
                
                if(nums2[j]>ele){
                    gele=nums2[j];
                    break;
                }
                j++;
            }
            res[i]=gele;
            
        }
        return res;
    }
}
