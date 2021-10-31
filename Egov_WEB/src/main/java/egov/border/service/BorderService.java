package egov.border.service;

import java.util.HashMap;

public interface BorderService {
	void insertBorder(HashMap<String, Object> paramMap) throws Exception;
	void selectBorder(HashMap<String, Object> paramMap) throws Exception;
	void selectBorderView(HashMap<String, Object> paramMap) throws Exception;
	void insertBorderReply(HashMap<String, Object> paramMap) throws Exception;
	void updateBorderEdit(HashMap<String, Object> paramMap) throws Exception;
	void updateBorderRemove(HashMap<String, Object> paramMap) throws Exception;
	void proctest(HashMap<String, Object> paramMap) throws Exception;
	void proctest2(HashMap<String, Object> paramMap) throws Exception;
	void procdo(HashMap<String, Object> paramMap) throws Exception;
}
