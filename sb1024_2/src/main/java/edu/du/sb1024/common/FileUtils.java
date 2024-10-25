package edu.du.sb1024.common;

import edu.du.sb1024.entity.BoardFileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {

	/**
	 * 파일 업로드 정보를 파싱하여 BoardFileDto 객체 리스트로 반환하는 메서드.
	 * @param boardIdx 게시물 인덱스
	 * @param multipartHttpServletRequest 파일이 포함된 HTTP 요청
	 * @return 업로드된 파일 정보 리스트
	 * @throws Exception 파일 처리 중 발생할 수 있는 예외
	 */
	public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

		// 요청이 비어 있는지 확인
		if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null; // 요청이 비어있으면 null 반환
		}

		// 업로드된 파일 정보를 담을 리스트 초기화
		List<BoardFileDto> fileList = new ArrayList<>();

		// 현재 날짜를 "yyyyMMdd" 형식으로 포맷팅하기 위한 DateTimeFormatter 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now(); // 현재 시간 가져오기

		// 이미지 저장 경로 설정 (예: ./src/main/resources/static/images/20241025)
		String path = "./src/main/resources/static/images/" + current.format(format);
		File file = new File(path); // File 객체 생성

		// 저장할 디렉토리가 존재하지 않는 경우 생성
		if (file.exists() == false) {
			file.mkdirs(); // 필요한 모든 디렉토리 생성, 당일 날짜의 파일을 생성하는 것.
		}

		// 파일 이름을 가져오기 위한 Iterator 초기화
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		String newFileName; // 새 파일 이름
		String originalFileExtension; // 원본 파일 확장자
		String contentType; // 파일 콘텐츠 타입

		// 요청에 포함된 모든 파일에 대해 반복
		while (iterator.hasNext()) {
			// 현재 파일 이름으로 MultipartFile 리스트 가져오기
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for (MultipartFile multipartFile : list) {
				// 파일이 비어있지 않은 경우에만 처리
				if (multipartFile.isEmpty() == false) {
					contentType = multipartFile.getContentType(); // 콘텐츠 타입 가져오기

					// 콘텐츠 타입이 비어 있는지 확인
					if (ObjectUtils.isEmpty(contentType)) {
						break; // 콘텐츠 타입이 없으면 루프 종료
					} else {
						// 콘텐츠 타입에 따라 파일 확장자 설정
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg"; // JPEG 파일
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png"; // PNG 파일
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif"; // GIF 파일
						} else {
							break; // 지원하지 않는 파일 형식인 경우 루프 종료
						}
					}

					// 새로운 파일 이름 생성 (현재 나노초로 고유하게 만듦)
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					BoardFileDto boardFile = new BoardFileDto(); // DTO 객체 생성

					// DTO에 파일 정보 설정
					boardFile.setBoardIdx(boardIdx);
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());

					// 저장 경로 설정 (상대 경로로 설정)
					boardFile.setStoredFilePath("/images/" + current.format(format) + "/" + newFileName);

					// 파일 정보를 리스트에 추가
					fileList.add(boardFile);

					// 파일 객체 생성 및 파일 저장
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file); // 파일 저장
				}
			}
		}
		return fileList; // 업로드된 파일 정보 리스트 반환
	}
}
