package com.gis.service.gis;

import java.util.List;

import com.gis.dto.gis.DateCoord;
import com.gis.dto.gis.GpsTempData;
import com.gis.dto.gis.NoiseTempData;
import com.gis.dto.gis.RpmTempData;

public interface IGisService {
	/**
	 * Temp Table에서 차량 번호 조회
	 * @author 여수한
	 */
	List<String> selectCarNumber();
	/**
	 * Temp Table에서 GPS, 소음, 진동 데이터 불러와 평균내기
	 * @author 여수한
	 */
	void selectTempData(String carNum);
	/**
	 * Temp Table 비우기
	 * @author 여수한
	 */
	void deleteTempTable();
	/**
	 * Temp Table 넣기
	 * @author 여수한
	 */
	void insertGpsTempData(GpsTempData gtd);
	void insertNoiseTempData(NoiseTempData ntd);
	void insertRpmTempData(RpmTempData rtd);
	/**
	 * 달력 날짜 누르면 청소 운행거리 계산
	 * @author 여수한
	 */
	int selectDateCleanDistance(String date);
	/**
	 * 달력 날짜 누르면 전체 운행거리 계산
	 * @author 여수한
	 */
	int selectDateTotalDistance(String date);
	/**
	 * 달력 날짜 누르면 청소 비율 계산
	 * @author 여수한
	 */
	int selectDateCleanRatio(String date);
	/**
	 * 달력 날짜 누르면 운행 시간 계산
	 * @author 여수한
	 */
	String selectDateCleanTime(String date);
	/**
	 * 달력 날짜 누르면 해당 날짜의 좌표 데이터 조회
	 * @author 여수한
	 */
	DateCoord selectDateCoord(String date, String carNum);
}
