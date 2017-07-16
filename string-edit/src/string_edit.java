import java.util.*;
public class string_edit {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("문자열 입력 : ");
		String a = input.nextLine();
		
		System.out.print("역순 출력 : ");
		//역순 출력
		reverseString(a);
		//각 알파벳 개수 출력
		countString(a);
	}
	//역순으로 출력하는 메소드
	public static void reverseString(String s){
		//공백을 기준으로 문자열 분리
		String [] reverse = s.split(" ");
		//알파벳만 뒤집고, 연산자는 그대로 뒤에 이어 붙이기 위해 연산자를 저장할 리스트
		List<Character> signlist = new ArrayList<Character>();
		//자른 뒤에서부터 역순으로 출력하기 위한 반복문
		for(int i=reverse.length-1; i>=0; i--){
			for(int j=0; j<reverse[i].length(); j++){
				//if 알파벳인 경우에만 출력한다.
				//else 연산자인 경우에는 따로 list에 저장한다.
				//문제는 문장의 단어를 역순으로 출력하는 것이므로, 알파벳 이외의 기호가
				//제일 뒤가 아닌 곳에 오는 경우는 없다고 가정
				if((reverse[i].charAt(j) >= 'A' && reverse[i].charAt(j) <= 'Z') || (reverse[i].charAt(j) >= 'a' && reverse[i].charAt(j) <= 'z'))
					System.out.print(reverse[i].charAt(j));
				else
					signlist.add(reverse[i].charAt(j));
			}
			//연산자를 문장 바로 뒤에 붙이기 위해서, 제일 마지막 출력이 아닌 경우에만 띄어쓰기
			if(i != 0)
				System.out.print(" ");
		}
		//저장된 연산자의 갯수만큼 뒤에 덧붙여서 출력 ex) who + ?????
		for(int i=0; i<signlist.size(); i++){
			System.out.print(signlist.get(i));
		}
		System.out.println();
	}
	//각 알파벳의 개수를 카운팅하는 메소드
	public static void countString(String s){
		//대 소문자의 구분에 대해서 문제에 명시되어 있지 않아서
		//소문자로 전부 변환하여 문제를 해결하였습니다.
		s = s.toLowerCase();
		//전체 알파벳의 개수를 저장할 변수
		int allcount = 0;
		//각 알파벳 별 갯수를 저장할 배열 typesave 배열과 인덱스를 같이 사용
		int [] countsave = new int[26];
		//알파벳의 타입을 저장할 배열 0 = a, 1 = b ....
		int [] typesave = new int[26];
		//타입을 저장할 배열의 각 자리수에 맞는 알파벳 번호 할당
		for(int i=0; i<typesave.length; i++){
			typesave[i] = i;
		}
		//입력받은 문자열 만큼 반복문
		for(int i=0; i<s.length(); i++){
			//각 자리수 별로 아스키 코드값 a 만큼의 값을 빼줌
			int indexOfstring = (int)(s.charAt(i) - 97);
			//알파벳의 범위 안이라면 전체 개수 증가, 해당하는 알파벳의 개수 증가
			if(indexOfstring >= 0 && indexOfstring <= 26){
				countsave[indexOfstring]++;
				allcount++;
			}
		}
		//저장된 알파벳의 갯수에 따른 내림차순 정렬(최상위 빈도 출력을 위해)
		bubleSort(typesave, countsave);
		//전체 갯수 출력
		System.out.println("전체 수 : " + allcount);
		//내림차순으로 정렬된 알파벳 각각의 숫자 출력
		for(int i=0; i<countsave.length; i++){
			System.out.print((char)(typesave[i] + 97) + ": ");
			System.out.println(countsave[i]);
		}
	}
	//내림차순 정렬하여 최상위 빈도로 출력하기 위한 정렬 메소드
	public static void bubleSort(int type[], int count[]){
		for(int i=0; i<count.length; i++){
			for(int j=0; j<count.length - i - 1; j++){
				if(count[j] < count[j+1]){
					//갯수 배열과 동시에 알파벳의 타입을 저장한 배열의 위치도 바꾸어 준다.
					int temp = count[j];
					count[j] = count[j+1];
					count[j+1] = temp;
					
					temp = type[j];
					type[j] = type[j+1];
					type[j+1] = temp;
				}
			}
		}
	}

}
