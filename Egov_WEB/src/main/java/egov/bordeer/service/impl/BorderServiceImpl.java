package egov.bordeer.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.border.dao.BorderDAO;
import egov.border.service.BorderService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BorderService")
public class BorderServiceImpl extends EgovAbstractServiceImpl implements BorderService  {
	
	@Resource(name="BorderDAO") BorderDAO borderDAO;
	
	@Override
	public void insertBorder(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.insertBorder(paramMap);
	}

	@Override
	public void selectBorder(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.selectBorder(paramMap);
	}

	@Override
	public void selectBorderView(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.selectBorderView(paramMap);
	}

	@Override
	public void insertBorderReply(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.insertBorderReply(paramMap);
	}

	@Override
	public void updateBorderEdit(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.updateBorderEdit(paramMap);
	}

	@Override
	public void updateBorderRemove(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.updateBorderRemove(paramMap);
	}

	@Override
	public void procdo(HashMap<String, Object> paramMap) throws Exception {
		proctest(paramMap);
		String id = paramMap.get("userId").toString()+"bca321";
		paramMap.put("userId", id);
		proctest2(paramMap);
	}
	
	@Override
	public void proctest(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.proctest(paramMap);
		
	}
	@Override
	public void proctest2(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.proctest2(paramMap);
		
	}

	@Override
	public void insertText(HashMap<String, Object> paramMap) throws Exception {
		borderDAO.insertText(paramMap);
		
	}
}
