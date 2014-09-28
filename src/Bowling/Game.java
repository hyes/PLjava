package Bowling;

import java.util.LinkedHashMap;

public class Game {

	Player player;
	String name;
	static int byFrame = 0;
	int totalSum;
	LinkedHashMap<String, int[]> scoreTable = new LinkedHashMap<String, int[]>();
	LinkedHashMap<String, Integer> totalScore = new LinkedHashMap<String, Integer>();
	
	public Game(Player player){
		this.player = player;
		this.name = player.getName();
	}

	public LinkedHashMap<String, int[]> getMap(){
		return this.scoreTable;
	}

	public void setScoreTable() {
		int[] dummy = new int[]{0,0,0};
		scoreTable.put("frame0", dummy);
//		scoreTable.put("frame-1", dummy);
	
		totalScore.put("sumframe0", 0);
//		totalScore.put("sumframe-1", 0);
		
				
			for (int i = 1; i < 11; i++) {
				
				if(i < 11){
				String tmp = "frame" + i;
				int[] framescore = new int[2];
				framescore[0] = player.rolling();
				int secondPins = Pin.afterFirstRoll(framescore[0]);
				framescore[1] = player.rolling(secondPins);
				scoreTable.put(tmp, framescore);//최초 핀 생성 결과
				
				try{
					recordScore(i, framescore[0], framescore[1]);
					}catch(NullPointerException e){
						e.printStackTrace();
						//System.out.println("과정1   "+ i + "번째 "+ framescore[0] + "," + framescore[1] +" 넘기면서 널포인터익셉션");
					}
				}
				
				
				else if(i == 11){
					String tmp = "frame" + i;
					int[] framescore = new int[3];
					framescore[0] = player.rolling();
					int secondPins = Pin.afterFirstRoll(framescore[0]);
					framescore[1] = player.rolling(secondPins);
					
					if(framescore[0]==10 || framescore[0]+framescore[1] ==10){
					framescore[2] = player.rolling();
					}
					scoreTable.put(tmp, framescore);//최초 핀 생성 결과
				}
				
			
		}

	}
	private void recordScore(int keynum, int j, int k) {
		
		int tempkeynum = keynum - 1;
		int tempkey2 = keynum - 2;
		String tempBefore = "sumframe" + tempkeynum;
		String tempEvenBefore = "sumframe" + tempkey2;
		String temp = "sumframe" + keynum;
		String beforeFrame = "frame" + tempkeynum;
		String evenBeforeFrame = "frame" + tempkey2;
	
		int[] prevFrame = scoreTable.get(beforeFrame);
		int base1 = prevFrame[0];
		int base2 = prevFrame[1];
		int[] prevFrame2 = scoreTable.get(evenBeforeFrame);
		
		
		//현재 프레임에서 스트라이크 
		if(j == 10){
			byFrame = 10;
			
			if(base1 == 10){
				int renew = totalScore.get(tempBefore);
				renew +=10;
				totalScore.put(tempBefore, renew);
				
				if(prevFrame2[1] == 10){
					int renew2 = totalScore.get(tempEvenBefore);
					renew2 +=10;
					totalScore.put(tempEvenBefore, renew2);
				}
				
			}else if(base1 != 10 && base1 + base2 == 10){
				int renew = totalScore.get(tempBefore);
				renew += 10;
				totalScore.put(tempBefore, renew);
			}else{
				
			}
			totalScore.put(temp, byFrame);
			
		}
		//현재 프레임에서 스페어  
		else if(j != 10 && j + k == 10){
			byFrame = j + k;
			
			if(base1 == 10){
				try{
				int renew = totalScore.get(tempBefore);
				renew += byFrame;
				totalScore.put(tempBefore, renew);
				}catch(NullPointerException e){
				
				}
				
				if(prevFrame2[0] == 10){
					int renew2 = totalScore.get(tempEvenBefore);
					renew2 += byFrame;
					totalScore.put(tempEvenBefore, renew2);
				}
				
			}else if(base1 != 10 && base1 + base2 == 10){
				try{
				int renew = totalScore.get(tempBefore);
				renew += j;
				totalScore.put(tempBefore, renew);
				}catch(NullPointerException e){
			
				}
			}else{
				totalScore.put(temp, byFrame);
			}
			
		
		}
		//현재프레임에서 스트라이크나 스페어가 아닌 경우 
		else if (j + k != 10){
				byFrame = j + k ;
	
				if(base1 == 10){
					try{
					int renew = totalScore.get(tempBefore);
					renew += byFrame;
					totalScore.put(tempBefore, renew);
					}catch(NullPointerException e){
						
					}
					
					if(prevFrame2[0] == 10){
							int renew2 = totalScore.get(tempEvenBefore);
							renew2 += j;
							totalScore.put(tempEvenBefore, renew2);
						}
					
				}else if(base1 != 10 && base1 + base2 == 10){
					try{
					int renew = totalScore.get(tempBefore);
					renew += j;
					totalScore.put(tempBefore, renew);
					}catch(NullPointerException e){

					}
				}else{
					//totalScore.put(temp, byFrame);
				}
				totalScore.put(temp, byFrame);
				
		}else{
			totalScore.put(temp, byFrame);
		}
	
	}

	
	public void showScoreTable(){
		//System.out.println(scoreTable.size());
		System.out.println("--------------------");
		System.out.println("Frame별 넘어뜨린 pin개수");
		System.out.println("--------------------");
		String[] keys = scoreTable.keySet().toArray(new String[0]);
			for(int i=1;i<keys.length;i++){
			 String key = keys[i];
			 int[] val = scoreTable.get(key);
			 System.out.println(key+" : "+val[0] + ", " + val[1]);

			}

	}
	public void scoringPerFrame(){
		//System.out.println(totalScore.size());
		System.out.println("--------------------");
		System.out.println("Frame별 점수");
		System.out.println("--------------------");
		String[] keys = totalScore.keySet().toArray(new String[0]);
		
			for(int i=1;i<keys.length;i++){
			 String key = keys[i];
			 int val = totalScore.get(key);
			 System.out.println(key+" : "+ val);
			 
			}
		
	}

	public void totalScore(){

		String[] keys = totalScore.keySet().toArray(new String[0]);
		
			for(int i=1;i<keys.length;i++){
			 String key = keys[i];
			 int val = totalScore.get(key);
			 totalSum += val;
			}
			System.out.println("--------------------");
			System.out.println("total: " + totalSum);
			System.out.println("--------------------");
	}
	
}
