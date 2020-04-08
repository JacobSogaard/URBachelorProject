package emptyproject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class VersionComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		String version1 = o1;
		String version2 = o2;
		
		List<String> splittedVer1 = Arrays.asList(version1.split("-|\\."));
		List<String> splittedVer2 = Arrays.asList(version2.split("-|\\."));
		
		//Make sure that both lists has equal length
		balanceList(splittedVer1, splittedVer2);
		
		for (int i = 0; i < splittedVer1.size(); i++) {
			int currentVersion1 = Integer.parseInt(splittedVer1.get(i)); 
			int currentVersion2 = Integer.parseInt(splittedVer2.get(i)); 
			if (currentVersion1 > currentVersion2) {
				return 1;
			} else if (currentVersion1 < currentVersion2) {
				return -1;
			} 				
		}
		return 0;

	}

	private void balanceList(List<String> list1, List<String> list2 ) {
		int offset = 0;
		if(list1.size() > list2.size()) {
			offset = list1.size() - list2.size();
			for (int i = 0; i < offset; i++) {
				list2.add("0");
			}
		} else if (list1.size() < list2.size()) {
			offset = list2.size() - list1.size();
			for (int i = 0; i < offset; i++) {
				list1.add("0");
			}
		}
		
	}
}
