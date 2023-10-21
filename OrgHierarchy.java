import java.io.*; 
import java.util.*; 
class SinglyLinkedList{
	protected Node head;
	public SinglyLinkedList(Node N){
		head=N;
	}
	public void addelement(Node x){
		Node temp=head;
		while(temp.getNext()!=null){
			temp=temp.getNext();
		}
		temp.setNext(x);
	}
	public Node gethead(){
		return head;
	}
	public void remove(Node x){
		Node temp=head;
		while(temp!=null && temp.getNext()!=x){
			temp=temp.getNext();
		}
		if(temp!=null){
			Node temp1=temp.getNext();
			temp.setNext(temp1.getNext());
			temp1.setNext(null);
		}
	}
}
class Node{
	private int id;
	private Node boss;
	private SinglyLinkedList employee;
	private int level;
	private Node Next;
	private int size;
	public Node(int e,Node x,SinglyLinkedList l,Node y){
		id=e;
		boss=x;
		employee=l;
		Next=y;
		if(boss!=null){
			level=boss.getlevel()+1;
		}else{
			level=1;
		}
	}
	public void addemployee(Node y){
		if(employee!=null){
			employee.addelement(y);
		}else{
			employee=new SinglyLinkedList(y);
		}
	}
	public int getlevel(){
		return level;
	}
	public int getid(){
		return id;
	}
	public Node getboss(){
		return boss;
	}
	public void setboss(Node y){
		boss=y;
	}
	public SinglyLinkedList getemployees(){
		return employee;
	}
	public void setNext(Node x){
		Next=x;
	}
	public Node getNext(){
		return Next;
	}
	public Node find(Node root,int id){
		if(root.getid()==id){
			return root;
		}else{
			try{
				SinglyLinkedList l=root.getemployees();
				Node temp=l.gethead();
				while(temp!=null){
					Node x=find(temp,id);
					if(x!=null){
						return x;
					}else{
						temp=temp.getNext();
					}
				}
				return null;
			}catch(NullPointerException e){
				return null;
			}
		}
	}
}
public class OrgHierarchy implements OrgHierarchyInterface{
	//root node
	Node root;

	public boolean isEmpty(){
		return root==null; 	
	} 

	public int size(){
		//your implementation
	 	throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	public int level(int id) throws IllegalIDException{
		Node y=root.find(root,id);
		if(y==null){
			throw new IllegalIDException("Invalid id");
		}else{
			return y.getlevel();
		}
	} 

	public void hireOwner(int id) throws NotEmptyException{
		if(root!=null){
			throw new NotEmptyException("tree is not empty");
		}else{
			root=new Node(id,null,null,null);
		}
	}

	public void hireEmployee(int id, int bossid) throws IllegalIDException{
		Node y=root.find(root,bossid);
		if(y==null){
			throw new IllegalIDException("Invalid bossid");
		}else{
			Node k=new Node(id,y,null,null);
			y.addemployee(k);
		}
	} 

	public void fireEmployee(int id) throws IllegalIDException{
		if(root.getid()==id){
			throw new IllegalIDException("Invalid id");
		}
		else{
			Node y=root.find(root,id);
			if(y==null){
				throw new IllegalIDException("Invalid id");
			}else{
				Node p=y.getboss();
				SinglyLinkedList l=root.getemployees();
				l.remove(y);
			}
		}
	}
	public void fireEmployee(int id, int manageid) throws IllegalIDException{
		if(root.getid()==id){
			throw new IllegalIDException("Invalid id");
		}
		else{
			Node y=root.find(root,id);
			Node y1=root.find(root,manageid);
			if(y==null){
				throw new IllegalIDException("Invalid id");
			}else{
				Node p=y.getboss();
				SinglyLinkedList l=root.getemployees();
				SinglyLinkedList l1=y.getemployees();
				Node k=l1.gethead();
				y1.addemployee(k);
				while(k!=null){
					k.setboss(y1);
					k=k.getNext();
				}
				l.remove(y);
			}
		}
	} 

	public int boss(int id) throws IllegalIDException{
		if(root.getid()==id){
			return -1;
		}
		Node y=root.find(root,id);
		if(y==null){
			throw new IllegalIDException("Invalid id");
		}else{
			return y.getboss().getid();
		}
	}

	public int lowestCommonBoss(int id1, int id2) throws IllegalIDException{
		Node y1=root.find(root,id1);
		Node y2=root.find(root,id2);
		if(y1==null || y2==null){
			throw new IllegalIDException("Invalid id");
		}
		if(y1==root || y2==root){
			return -1;
		}
		while(y1!=y2){
			if(y1==root || y2==root){
				if(y1==root){
					y2=y2.getboss();
				}else{
					y1=y1.getboss();
				}
			}else{
				y1=y1.getboss();
				y2=y2.getboss();
			}
		}
		return y1.getid();
	}

	public String toString(int id) throws IllegalIDException{
		//your implementation
	 	throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

}
