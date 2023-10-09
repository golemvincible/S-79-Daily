import java.util.HashMap;

public class LRUCache {
    class DLinkedNode{
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
    }
    /**
     * Always add the new node right after head;
    */

    private void addNode(DLinkedNode node){
        
        node.prev=head;
        node.next=head.next;
        
        head.next.prev=node;
        head.next=node;
    }
    
    /*
        if i accessed any node then i'll have to remove 
        that node from its pos and insert it after head so 
        that it will most recently used node
    */
    
    private void removeNode(DLinkedNode node){
        DLinkedNode prvN = node.prev;
        DLinkedNode nxtN = node.next;
        
        prvN.next=nxtN;
        nxtN.prev=prvN;
    }
    
    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        addNode(node);
    }
    
    /*
        if the capacity is full then we need to pop the node which is at the tail
        because we know it's most previously used node
    */
    
    private DLinkedNode popTail(){
        DLinkedNode res = tail.prev;
        this.removeNode(res);
        return res;
    }
    
    private HashMap<Integer,DLinkedNode> cache = new HashMap<>();
    private int count=0;
    private int capacity=0;
    private DLinkedNode head,tail;
    
    public LRUCache(int capacity) {
        this.count=0;
        this.capacity=capacity;
        
        head = new DLinkedNode();
        tail = new DLinkedNode();
        
        head.prev = null;
        
        head.next=tail;
        
        tail.prev=head;
        
        tail.next=null;
        
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        
        if(node==null){
            return -1;
        }
        
        this.moveToHead(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        
        DLinkedNode node = cache.get(key);
        
        if(node==null){
            
            DLinkedNode newNode = new DLinkedNode();
            newNode.key=key;
            newNode.val=value;
            
            this.cache.put(key,newNode);
            
            this.addNode(newNode);
            
            count++;
            
            if(count>capacity){
                DLinkedNode tail = popTail();
                this.cache.remove(tail.key);
                count--;
            }
        }
        //node already exists so we will just change its value and move it to the head
        else{
            node.val=value;
            this.moveToHead(node);
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
