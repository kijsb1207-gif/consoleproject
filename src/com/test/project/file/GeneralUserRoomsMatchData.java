// GeneralUserRoomsMatchData.java
package com.test.project.file;

import java.io.BufferedReader;
import java.io.BufferedWriter; // BufferedWriter import 추가
import java.io.FileReader;
import java.io.FileWriter; // FileWriter import 추가
import java.io.IOException; // IOException import 추가
import java.util.ArrayList;

import com.test.project.data.GeneralUserRoomsMatch;

public class GeneralUserRoomsMatchData {
	public static ArrayList<GeneralUserRoomsMatch> roomsMatchList;
	static {
		roomsMatchList = new ArrayList<GeneralUserRoomsMatch>();
	}

	public static void roomsMatchDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.GENERALUSERROOMSMATCH));

			String line = null;

			while((line = reader.readLine()) != null) {
//				객실일련번호■일반회원ID
//				1■wjswoaks , int, string
				String[] temp = line.split("■");
				GeneralUserRoomsMatch roomsMatch = new GeneralUserRoomsMatch(Integer.parseInt(temp[0]), temp[1]);

				roomsMatchList.add(roomsMatch);
			}
			//System.out.println(roomsMatchList);
			reader.close();

		} catch (Exception e) {
			//
			System.out.println("GeneralUserRoomsMatchData.roomsMatchDataLoad");
			e.printStackTrace();
		}
	}

    
    /**
     * roomsMatchList에 있는 데이터를 파일에 저장합니다.
     */
    public static void saveRoomsMatchData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.GENERALUSERROOMSMATCH))) {
            for (GeneralUserRoomsMatch match : roomsMatchList) {
                writer.write(String.format("%d■%s", match.getRoomSerial(), match.getGeneralUserId()));
                writer.newLine();
            }
            // System.out.println("회원-객실 매칭 정보가 파일에 저장되었습니다."); // 디버깅용, 필요에 따라 주석 해제
        } catch (IOException e) {
            System.out.println("GeneralUserRoomsMatchData.saveRoomsMatchData 오류 발생");
            e.printStackTrace();
        }
    }
    // ⚠ 추가해야 하는 부분 끝 ⚠
}