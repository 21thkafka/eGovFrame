package egov.main.dao;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MainDAO")
public interface MainDAO{

	HashMap<String, Object> selectMain(HashMap<String, Object> paramMap) throws Exception;
	HashMap<String, Object> selectLogin(HashMap<String, Object> paramMap) throws Exception;
	void selectLogin2(HashMap<String, Object> paramMap) throws Exception;
}
