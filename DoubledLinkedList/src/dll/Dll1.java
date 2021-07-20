package dll;

import java.util.*;

class Node {
	Node llink;
	Node rlink;
	int info;
}

public class Dll1 {

	static Node head = null;
	Node tail = null;
	Scanner sc = new Scanner(System.in);

	public void create() {
		Node p = new Node();
		System.out.println("Enter info :");
		p.info = sc.nextInt();
		p.llink = null;
		p.rlink = null;
		head = p;
		tail = p;
		Node newnode;
		System.out.println("do you want to add more nodes ? (1/0)");
		int check = sc.nextInt();
		while (check == 1) {
			newnode = new Node();
			System.out.println("enter info :");
			newnode.info = sc.nextInt();

			newnode.rlink = null;
			newnode.llink = p;
			p.rlink = newnode;
			p = newnode;
			tail = newnode;
			System.out.println("do you want to add more nodes(1/0)");
			check = sc.nextInt();
		}

	}

	public void displayforward() {
		Node temp = head;
		System.out.println();
		System.out.println("DISPLAYING FORWARD :");
		System.out.println();
		System.out.println("start = " + head);

		do {
			System.out.println(temp.llink + " --- " + temp.info + " --- " + temp.rlink);
			temp = temp.rlink;
		} while (temp != null);
	}

	public void displaybackward() {
		System.out.println();
		System.out.println("DISPLAYING BACKWARD :");
		System.out.println();
		System.out.println("End = " + tail);
		Node temp = tail;
		while (temp != null) {
			System.out.println(temp.llink + " --- " + temp.info + " --- " + temp.rlink);
			temp = temp.llink;
		}
	}

	public static int count(Node temp) {
		int count = 0;
		temp = head;
		while (temp != null) {
			count++;
			temp = temp.rlink;
		}
		return count;
	}

	public int search(int ele) {
		int pos = 0;
		Node temp = head;
		while (temp != null) {
			pos++;
			if (temp.info == ele)
				return pos;
			temp = temp.rlink;
		}
		return -1;
	}

	public void insBeg(int ele) {
		Node temp = new Node();
		temp.info = ele;
		temp.rlink = temp.llink = null;
		if (head == null)
			head = tail = temp;
		else {
			temp.rlink = head;
			head.llink = temp;
			head = temp;
		}
	}

	public void insEnd(int ele) {
		Node temp = new Node();
		temp.info = ele;
		temp.rlink = temp.llink = null;
		if (head == null) {
			head = tail = temp;
		} else {

			tail.rlink = temp;
			temp.llink = tail;
			tail = temp;
		}
	}

	public void insLoc(int ele) {
		System.out.println("Enter the location you want to insert :");
		Node temp = new Node();
		temp.info = ele;
		temp.llink = temp.rlink = null;
		int count = count(head);
		int loc = sc.nextInt();
		if (loc >= 1 && loc <= count + 1) {
			if (loc == 1)
				insBeg(ele);
			else if (loc == count + 1) {
				insEnd(ele);
			} else {
				Node p = head;
				int cnt = 1;
				while (cnt < loc - 1) {
					cnt++;
					p = p.rlink;

				}
				temp.rlink = p.rlink;
				p.rlink.llink = temp;
				p.rlink = temp;
				temp.llink = p;
			}
		} else {
			System.out.println("Invalid position, This position doesn't exist.");
		}
	}

	public void delBeg() {
		if (head == null) {
			System.out.println("UNDERFLOW : no elements present at head.");
			return;
		}
		if (head.rlink == null) {
			head = tail = null;
		} else {
			head = head.rlink;
			head.llink = null;
		}
	}

	public void delEnd() {
		if (head == null) {
			System.out.println("UNDERFLOW : no elements present at head.");
			return;
		}
		if (head.rlink == null) {
			head = tail = null;
		} else {
			tail = tail.llink;
			tail.rlink = null;
		}

	}

	public void delLoc(int loc, int length) {
		if (loc == length) {
			delEnd();
		} else if (loc == 1) {
			delBeg();
		} else {
			int i = 1;
			Node temp = head;
			while (i != loc) {
				i++;
				temp = temp.rlink;
			} // now temp is refering to the location we have entered.
			temp = temp.llink;// pointing to previous location of temp.
			temp.rlink = temp.rlink.rlink;// skipping the pointer of right link to point to the next's next location.
											// jvm collects the misplaced data that is the one we want to delete
			temp.rlink.llink = temp;// pointing the left pointer the next node of deleted element to temp(previous
									// of deleted).
		}
	}

	public void sort() {

		Node temp1 = head;
		Node x = head;//only created to set back head to original place : last line of this method.
		Node temp = new Node();
		while (temp1 != null) {
			Node temp2 = temp1.rlink;
			while (temp2 != null) {
				if (temp2.info > temp1.info) {
				temp.info=temp1.info;
				temp1.info=temp2.info;
				temp2.info=temp.info;
					
				}
				temp2 = temp2.rlink;
			}
			temp1 = temp1.rlink;
		}
		System.out.println("Sorted and displayed the info below : ");
		int i =1;
		while (head != null) {
			System.out.println(i+"."+head.info);
			head = head.rlink;
			i++;
		}
		head = x;//here.
		
	}



	public static void main(String[] args) {
		Dll1 obj = new Dll1();
		Scanner sc = new Scanner(System.in);
		char check;
		do {
			System.out.println("<<<<<<<<<<<<<<  MENU  >>>>>>>>>>>>>>>");
			System.out.println("1.Create Doubled LinkedList");
			System.out.println("2.Display backward.");
			System.out.println("3.Display forward.");
			System.out.println("4.Count total no. of elements.");
			System.out.println("5.Search an element position.");
			System.out.println("6.Insert an element at the beggining .");
			System.out.println("8.Insert an element at the ending .");
			System.out.println("9.Insert an element at wished location .");
			System.out.println("10.Delete from begining .");
			System.out.println("11.Delete from ending .");
			System.out.println("12.Delete from wished location .");
			System.out.println("13.Sort as descending order of elements values.");
			System.out.println("Choose from above Menu:");
			int x = sc.nextInt();
			switch (x) {
			case 1:
				obj.create();
				break;
			case 2:
				obj.displaybackward();
				break;
			case 3:
				obj.displayforward();
				break;
			case 4:
				System.out.println("TOTAL NO. OF NODES = " + obj.count(head));
				break;
			case 5:
				System.out.println("Enter the element you want to search :");
				int ele = sc.nextInt();
				System.out.println("Element is present at " + obj.search(ele) + " index.");
				break;
			case 6:
				System.out.println("Enter the info of the element you want to enter :");
				int u = sc.nextInt();
				obj.insBeg(u);
				break;
			case 7:
				System.out.println("Enter info :");
				int v = sc.nextInt();
				obj.insEnd(v);
				break;
			case 8:
				System.out.println("Enter info :");
				int p = sc.nextInt();
				obj.insEnd(p);
				break;
			case 9:
				System.out.println("Enter element you want to enter :");
				int elem = sc.nextInt();
				obj.insLoc(elem);
				break;
			case 10:
				obj.delBeg();
				break;
			case 11:
				obj.delEnd();
				break;
			case 12:
				System.out.println("Enter the node's location you want to delete :");
				int location = sc.nextInt();
				int length = obj.count(head);
				if (location > length + 1 || location < 0) {// checking whether location entered is correct or not .
					System.out.println("Invalid Location entered .");

				} else {
					obj.delLoc(location, length);
				}
				break;
			case 13:
				obj.sort();
				
				break;
			default:
				System.out.println("INVALID INPUT : Please choose from the menu .");
				break;
			}

			System.out.println("Do you want to continue to the menu ? choose ( 'y' / 'n' ):");

			check = sc.next().charAt(0);
		} while (check != 'n');

		System.out.println("THE PROGRAM HAS BEEN ELIMINATED .");
		sc.close();
	}
}