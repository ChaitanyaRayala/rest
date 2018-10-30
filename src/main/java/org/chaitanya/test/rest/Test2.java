package org.chaitanya.test.rest;

public class Test2 {

	public static void main(String[] args) {
		
		int[][] a = {{1,10},{1,6},{2,8},{3,5},{4,7},{0,8}};
		System.out.println(a.length);
		System.out.println(maxFlorists(9, a));
		
		String[] input = {"GoCardinals", "Doge", "nExTcapITalxnextcapital", "ThreeSThree", "traY"};
		String[] output = strengthenPasswords(input);
		for(String outputStr:output) {
			System.out.println(outputStr);
		}
	}
	
	public static int maxFlorists(int pathLength, int[][] floristIntervals) {
		
		int startPoint = -1;
		int endPoint = -1;
		
		int superFloristcount = 0;
		for(int[] i:floristIntervals) {
			int flag = 0;
			for(int j:i) {
				if(j >= 0) {
					if(flag == 0) {
						if(startPoint >= 0) {
							if(j < startPoint) {
								startPoint = j;
							}
						}else {
							startPoint = j;
						}					
					}else if(flag == 1) {
						if(endPoint >= 0) {
							if(j > endPoint) {
								if(j > pathLength) {
									endPoint = pathLength;
								}else {
									endPoint = j;
								}								
							}
						}else {
							endPoint = j;
						}
					}
				}				
				flag++;
			}
		}
		
		int[] path = new int[endPoint-startPoint];
		
		for(int i=0; i<path.length;i++) {
			path[i] = 0; 
		}
		
		int breakCheck = 0;
		for(int[] i:floristIntervals) {
			for(int j = i[0]; j < i[1]; j++) {
				if(path[j] < 3) {
					path[j]++;
				}else {
					breakCheck = 1;
					break;
				}
			}
			if(breakCheck == 1) {
				break;
			}
			superFloristcount++;
		}
		return superFloristcount;
    }
	
	
	public static String[] strengthenPasswords(String[] passwords) {

		String[] outputArr = new String[passwords.length];
		int index = 0;
		int nextCapitalIndex = 0;
		for(String inputString:passwords) {
			
			String mutableRule1String = "let'schange";
			String mutableRule2String = "let'schange";
			String mutableRule3String = "let'schange";
			String mutableRule4String = "let'schange";
			String testString = "nextCapitalTestString";
			int flag = 0;
			for(int i = 0; i < inputString.length(); i++) {
				if(inputString.charAt(i) == 's' || inputString.charAt(i) == 'S') {
					if(i == 0) {
						mutableRule1String = "5"+inputString.substring(i+1);
					}else {
						mutableRule1String = inputString.substring(0, i)+"5"+inputString.substring(i+1);
					}
					flag++;
				}
			}
			
			if(flag==0) {
				
				mutableRule1String = inputString;
			}
			
			if(mutableRule1String.length() > 1) {
				int middleVal = mutableRule1String.length() % 2;
				if(middleVal != 0) {
					int middleIndex = mutableRule1String.length()/2;
					char middleChar = mutableRule1String.charAt(mutableRule1String.length()/2);
					if(Character.isDigit(middleChar)){
						String sub1 = mutableRule1String.substring(0, middleIndex);
						String sub2 = mutableRule1String.substring(middleIndex+1);
						int value = Integer.parseInt(String.valueOf(middleChar));
						String insertValue = Integer.toString(value*2);
						mutableRule2String = sub1+insertValue+sub2;
					}else {
						mutableRule2String = mutableRule1String;
					}
				}else {
					mutableRule2String = mutableRule1String;
				}
			}
			
			if(mutableRule2String.length() % 2 == 0) {
				String sub1;
				String sub2;
				if(Character.isUpperCase(mutableRule2String.charAt(mutableRule2String.length()-1))) {
					sub1 = Character.toString(mutableRule2String.charAt(mutableRule2String.length()-1)).toLowerCase();
				}else {
					sub1 = Character.toString(mutableRule2String.charAt(mutableRule2String.length()-1)).toUpperCase();
				}
				
				if(Character.isUpperCase(mutableRule2String.charAt(0))) {
					sub2 = Character.toString(mutableRule2String.charAt(0)).toLowerCase();
				}else {
					sub2 = Character.toString(mutableRule2String.charAt(0)).toUpperCase();
				}
				mutableRule3String = sub1+mutableRule2String.substring(1, mutableRule2String.length()-1)+sub2;
			}else {
				mutableRule3String = mutableRule2String;
			}
			
			testString = mutableRule3String;
			testString = testString.toLowerCase();
			if((nextCapitalIndex = testString.indexOf("nextcapital"))!= -1) {
				String next = mutableRule3String.substring(nextCapitalIndex, nextCapitalIndex+4);
				StringBuilder sb = new StringBuilder();
				for(int i=next.length()-1; i >= 0; i--) {
					sb.append(Character.toString(next.charAt(i)));
				}
				mutableRule4String = mutableRule3String.substring(0, nextCapitalIndex)+sb.toString()+mutableRule3String.substring(nextCapitalIndex+4);
			}else {
				mutableRule4String = mutableRule3String;
			}
			
			outputArr[index] = mutableRule4String;
			index++;
		}
		return outputArr;
    }
}
