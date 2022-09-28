package LinkedList;

public class ll {
  private Node head;
  private Node tail;
  private int size;

  
  public ll() {
    this.size = 0;
  }

  private class Node {
    private int value;
    private Node next;

    public Node(){
    
    };
    public Node(int value) {
      this.value = value;
    }

    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  // find Node by index
  public Node get(int index) {
    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node;
  }

  // find node by value
  public Node search(int value) {
    Node node = head;
    while (node != null) {
      if (node.value == value) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

  // Insertion operations
  public void insertFirst(int val) {
    Node node = new Node(val);
    node.next = head;
    head = node;
    if (tail == null) {
      tail = head;
    }
    size++;
  }

  public void insertLast(int val) {
    Node node = new Node(val);
    if (tail == null) {
      insertFirst(val);
      return;
    }
    tail.next = node;
    tail = node;
    size++;
  }

  public void insert(int val, int index) {
    if (index > size) {
      System.out.println("Index is Out of bound");
      return;
    }
    if (index == 0) {
      insertFirst(val);
      return;
    }
    if (index == size) {
      insertLast(val);
      return;
    }
    Node temp = head;
    for (int i = 1; i < index; i++) {
      temp = temp.next;
    }
    Node node = new Node(val, temp.next);
    temp.next = node;
    size++;
  }
  // deletion operations

  public int deleteFirst() {
    int value = head.value;
    head = head.next;
    if (head == null) {
      tail = null;
    }
    size--;
    return value;
  }

  public int deleteLast() {
    if (size <= 1) {
      return deleteFirst();
    }
    Node secondLast = get(size - 2);
    int value = tail.value;
    tail = secondLast;
    tail.next = null;
    size--;
    return value;
  }

  public int delete(int index) {
    if (index == 0) {
      return deleteFirst();
    }
    if (index == size - 1) {
      return deleteLast();
    }
    Node prev = get(index - 1);
    int val = prev.next.value;
    prev.next = prev.next.next;
    size--;
    return val;
  }

  // show list
  public void show() {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.value + " --> ");
      temp = temp.next;
    }
    System.out.println("End");
  }
  public Node findMid(){
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public int deleteMiddleNode(){
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node temp = head;
        while (temp.next != slow){
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return slow.value;
    }
    public Node sortlist(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node mid = findMid();
        Node left = sortlist(head);
        Node right = sortlist(mid);

        return merge(left,right);
    }
    private Node merge(Node left, Node right) {
        Node temp = new Node();
        Node tail = temp;
        while (left != null && right != null){
            if (left.value < right.value){
                tail.next = left;
                left = left.next;
                tail = tail.next;
            }
            else {
                tail.next = right;
                right = right.next;
                tail = tail.next;
            }
        }
        tail.next = (left != null) ? left : right;
        return temp.next;
    }
}
