package egov.main.service;

import java.util.HashMap;

public interface MainService {

	HashMap<String, Object> selectMain(HashMap<String, Object> paramMap) throws Exception;
	HashMap<String, Object> selectLogin(HashMap<String, Object> paramMap) throws Exception;
	void selectLogin2(HashMap<String, Object> paramMap) throws Exception;
}
