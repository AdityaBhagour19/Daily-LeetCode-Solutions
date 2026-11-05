class Solution
{
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d)
    {
        int c = 0;
        
        for(int x : arr1)
            if(get(x,d,arr2))
                c++;
        
        return c;
    }
    
    boolean get(int n, int d, int[] arr)
    {
        for(int x : arr)
            if(Math.abs(n-x)<=d)
                return false;
        
        return true;
    }
}