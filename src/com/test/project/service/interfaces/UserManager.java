package com.test.project.service.interfaces;

import com.test.project.util.PageResult;

public interface UserManager {
	
	void AccountRegister(); // 계정 등록 
	PageResult accountEdit();      // 계정 수정

}
