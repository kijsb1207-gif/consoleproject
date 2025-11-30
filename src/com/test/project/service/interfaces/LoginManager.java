package com.test.project.service.interfaces;

import com.test.project.util.PageResult;

public interface LoginManager {
	PageResult login();
	PageResult logOut();
	boolean userInfoLoad(String id, String password);

}
