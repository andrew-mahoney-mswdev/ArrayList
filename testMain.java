import java.util.LinkedList;
import java.util.List;

public class testMain {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList();
		LinkedList<String> linkedList = new LinkedList();
		
		linkedList.add("zero");
//		linkedList.add("two");
//		linkedList.add("four");
		linkedList.add("five");
//		linkedList.add("one");
//		linkedList.add("three");
//		linkedList.add("six");
		
		arrayList.add("zero");
		arrayList.add("one");
		arrayList.add("two");
		arrayList.add("three");
		arrayList.add("four");
		arrayList.add("five");
		
		arrayList.retainAll(linkedList);
		
		for (int index = 0; index < arrayList.size(); index++) {
			System.out.println(arrayList.get(index));
		}

//		for (String s : arrayList) {
//			System.out.println(s);
//		}
		
	}
