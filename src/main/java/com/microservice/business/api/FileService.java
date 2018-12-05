package com.microservice.business.api;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	File convertMultipartToFile(MultipartFile multipartFile);
	List<String> convertFileToList(File file);
	
}
	