package egov.main.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.main.dao.MainDAO;
import egov.main.service.MainService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService  {

	@Resource(name="MainDAO") MainDAO mainDAO;
	
	@Override
	public HashMap<String, Object> selectMain(HashMap<String, Object> paramMap) throws Exception {
		
		return mainDAO.selectMain(paramMap);
	}

	@Override
	public HashMap<String, Object> selectLogin(HashMap<String, Object> paramMap) throws Exception {
		
		return mainDAO.selectLogin(paramMap);
	}

	@Override
	public void selectLogin2(HashMap<String, Object> paramMap) throws Exception {
		 mainDAO.selectLogin2(paramMap);
	}
}
