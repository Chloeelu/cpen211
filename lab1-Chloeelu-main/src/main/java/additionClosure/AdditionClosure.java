package additionClosure;

import java.util.List;

public class AdditionClosure {

	public static boolean isClosed(List<Integer> intList, Integer n) {
		for (int i = 0; i < intList.size()-1; i++){
			loop:
			for(int j =1+i; j< intList.size(); j++){
				int val1 = (intList.get(i)+intList.get(j)) % n;
				boolean val1exist = intList.contains(val1);
				if(!val1exist){
					for(int k = -10; k<=10; k++){
						int val2 = k * n + val1;
						boolean val2exist = intList.contains(val2);
						if(val2exist){
							continue loop;
						}
					}
					return false;
				}

			}

		}
		return true;
	}

}
