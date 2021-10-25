package egov.border.dao;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BorderDAO")
public interface BorderDAO{
	void insertBorder(HashMap<String, Object> paramMap)throws Exception;
	void selectBorder(HashMap<String, Object> paramMap)throws Exception;
	void selectBorderView(HashMap<String, Object> paramMap)throws Exception;
	void insertBorderReply(HashMap<String, Object> paramMap)throws Exception;
	void updateBorderEdit(HashMap<String, Object> paramMap)throws Exception;
	void updateBorderRemove(HashMap<String, Object> paramMap)throws Exception;
}
