import java.util.Iterator;
import java.util.LinkedList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Company testCompany = new Company();
		
		testCompany.addEmployee("Barry");
		testCompany.addEmployee("Amy");
		
		Employee barry = testCompany.searchEmployee("Barry");
		Employee amy = testCompany.searchEmployee("Amy");
		
		barry.addToDiary(2018, 4, 2, 10, 00, 12, 00, "10-12");
		barry.addToDiary(2018, 4, 2, 13, 00, 15, 00, "13-15");
		amy.addToDiary(2018, 4, 2, 11, 00, 13, 00, "11-13");
		amy.addToDiary(2018, 4, 2, 14, 00, 17, 00, "14-17");
		barry.printDiary();
		amy.printDiary();
		String[] criteria = new String[2];
		criteria[0] = "Barry";
		criteria[1] = "Amy";
		LinkedList<Meeting> free_time = testCompany.searchDiaries(criteria);
		Iterator<Meeting> iter_free_time = free_time.iterator();
		System.out.println("");
		System.out.println("You can organise a group meeting at:");
		while (iter_free_time.hasNext()) {
			iter_free_time.next().printMeeting();
		}
		
		testCompany.saveFile();
		testCompany = new Company();
		testCompany.openFile();
		barry = testCompany.searchEmployee("Barry");
		barry.printDiary();
		amy = testCompany.searchEmployee("Amy");
		amy.printDiary();
		
	}

}
