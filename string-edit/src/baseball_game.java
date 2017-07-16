import java.util.*;
public class baseball_game {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		//1 ~ 9 까지의 수를 저장하여 둔 배열
		String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		//data 안에 든 값을 이용해 ArrayList 생성
		List<String> dataList = new ArrayList<String>(Arrays.asList(data));
		//dataList ArrayList안의 값을 섞어주기 위해 shuffle 메소드 사용
		Collections.shuffle(dataList);
		//섞어준 dataList ArraList의 앞에 저장된 3가지의 값을 가지고 와서
		//컴퓨터가 고른 임의의수 3가지를 골라 computerdata라는 List에 입력
		List<String> computerdata = dataList.subList(0, 3);
		//유저가 입력한 값이 저장될 ArrayList
		List<String> userdata = new ArrayList<String>();
		
		//3개의 숫자를 모두 맞히기 전까지 계속 반복
		while(true){
			System.out.print("숫자를 입력해주세요 ex)123 : ");
			String inputData = input.nextLine();
			//반복문이 새로 돌고, 값을 새로 입력받을때마다 이미 입력되어 있는
			//값을 초기화한다.
			userdata.removeAll(userdata);
			//입력받은 값을 ArrayList에 잘라서 하나씩 저장
			for(int i=0; i<inputData.length(); i++){
				userdata.add(inputData.charAt(i) + "");
			}
			//만약 3개 모두 맞추어, 3스트라이크인 경우 종료
			if(checkAnswer(computerdata, userdata)){
				break;
			}
		}
	}
	//ArrayList를 출력하기 위한 메소드
	public static void printList(List<String> list){
		for(int i=0; i<list.size(); i++){
			System.out.print(list.get(i));
		}
		System.out.println();
	}
	//스트라이크와 볼의 개수를 카운팅하여 결과를 프린트, 반환해주는 메소드
	//두 ArrayList를 받아서 비교한다.
	public static boolean checkAnswer(List<String> computerdata, List<String> userdata){
		//볼의 개수를 저장할 변수
		int ballcount = 0;
		//스트라이크의 개수를 저장할 변수
		int strikecount = 0;
		//저장된 3개의 값을 비교하기 위한 반복문
		for(int i=0; i<computerdata.size(); i++){
			//컴퓨터가 가진 리스트 안에, 유저가 고른 i번째 수가 있는지 체크
			if(computerdata.contains(userdata.get(i))){
				//만약 그 수가 존재한다면, 현재 인덱스의 값끼리 일치하는지 확인하고 일치한다면 스트라이크 갯수 증가
				//값은 있지만, 현재 인덱스의 값과 일치하지 않는 경우 볼의 개수 증가
				if(computerdata.get(i).equals(userdata.get(i))){
					strikecount++;
				}else{
					ballcount++;
				}
			}
		}
		//위에서 연산 결과, 스트라이크 값이 1 이상 나온 경우
		if(strikecount!=0){
			System.out.print(strikecount + " 스트라이크 ");
		}
		//위의 연산 결과, 볼의 값이 1 이상 나온 경우
		if(ballcount!=0){
			System.out.print(ballcount + "볼 ");
		}
		//위의 연산 결과 볼도, 스트라이크도 없는 경우 낫싱
		if(strikecount == 0 && ballcount == 0)
			System.out.print("낫싱");
		System.out.println();
		//만약 스트라이크가 3인 경우 답이 맞았기 때문에 true 반환후 게임 종료
		if(strikecount == 3){
			System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
			System.out.print("컴퓨터의 숫자 : ");
			printList(computerdata);
			System.out.print("유저의 숫자 : ");
			printList(userdata);
			return true;
		}
			
		return false;
	}
}
