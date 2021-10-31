package egov.border.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egov.border.service.BorderService;
import egov.lib.pagination.PaginationInfo;



@Controller
public class BorderController {

	private static final Logger logger = LoggerFactory.getLogger(BorderController.class);
	
	@Resource(name="BorderService") BorderService borderService;
	@Resource(name="fileUploadProperty") Properties properties;
	
	@RequestMapping(value="/borderWrite.do")
	public String borderWrite(HttpServletRequest request,ModelMap model)
	{
		String userId="";
		
		//글쓰기권한 검사도 가능.
		if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/login.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
		}
			
		model.addAttribute("userId",userId);
		return "border/borderwrite";
	}
	
	@RequestMapping(value="/borderInsert.do")
	public String borderInsert(HttpServletRequest request,ModelMap model)throws Exception
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		
		String title = request.getParameter("title").toString();
		String mytextarea = request.getParameter("mytextarea").toString();
		String userId= "";
		
		//javascript유효성을 앞서서 진행해주시고요.
		//서버단에서 재검증.
		if(title.length()>15)
		{	
			return "redirect:/borderWrite.do";
		}
		//2000자이상이면
		else if(mytextarea.length()>2000)
		{

			return "redirect:/borderWrite.do";
		}
		else if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/login.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
			paramMap.put("userId", userId);
			paramMap.put("userIp", request.getRemoteAddr());
			paramMap.put("title", title);
			paramMap.put("mytextarea", mytextarea);
		}
		
		/*파일업로드 처리*/
		String convertuid = "";
		String uploadPath = properties.getProperty("file.uploadborderImg.path");
		String originalEX = "";
		String filePath = "";
		
		if(request instanceof MultipartHttpServletRequest){
			final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			final Map<String,MultipartFile> files = multiRequest.getFileMap();
			
			File saveFolder = new File(uploadPath);
			if(!saveFolder.exists()||saveFolder.isFile()){
				saveFolder.mkdirs();
			}
			
			Iterator<Entry<String,MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			
			/*파일을 반복해서 읽어들임*/
			while(itr.hasNext()){
				Entry<String,MultipartFile> entry = itr.next();
				file = entry.getValue();
				
				if(!"".equals(file.getOriginalFilename())){
					/*용량파일 제한*/
					int filesize = (int)file.getSize();
					int maxSize = 1 * 1024 * 1024;
					if(filesize>maxSize){
						return "redirect:/borderWrite.do";
					}
					
					Calendar cal = Calendar.getInstance();
					int year = cal.get ( Calendar.YEAR );
					int month = cal.get ( Calendar.MONTH ) + 1 ;
					int date = cal.get ( Calendar.DATE ) ;
					int hour = cal.get ( Calendar.HOUR_OF_DAY ) ;
					
					//서버에 저장할 파일이름
					convertuid = UUID.randomUUID().toString().replace("-", "")+year+month+date+hour;
					originalEX = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					
					convertuid = convertuid + "." + originalEX;
					//서버의 파일 경로
					filePath = uploadPath+convertuid;
					file.transferTo(new File(filePath.replaceAll(" ", "")));
					
				}
			}
		}
		paramMap.put("filename", convertuid);
		paramMap.put("filetype", originalEX);
		paramMap.put("fileurl","http://localhost:8080/Egov_WEB/borderView/image.do?file=" + convertuid);
		
		
		borderService.insertBorder(paramMap);
		
		return "redirect:/borderList.do";
	}


	 //ResponseEntity<byte[]>대신에 AbstractView방법도 있습니다.
	 @RequestMapping(value = "/borderView/image.do")
	 public ResponseEntity<byte[]> imageshow(HttpServletRequest request, ModelMap model) throws Exception {
	    request.setCharacterEncoding("UTF-8");
	    
	    String fileName="";
	    fileName=request.getParameter("file").toString();
	    
	        String uploadPath = properties.getProperty("file.uploadborderImg.path");
	   
	        InputStream in = null;
	        ResponseEntity<byte[]> entity = null;
	        
	        if(fileName.equals("")||fileName==null)
	        {
	        return null;
	        }
	        
	        try {
	            /*요청된 확장자를 제한할 수 있습니다.*/
	        /*보안적인 요소를 더 추가할 수 있습니다.*/
	        /*대용량을 다운로드 내보낼시 속도제어가 필요합니다.*/
	        
	            HttpHeaders headers = new HttpHeaders();
	            in = new FileInputStream(uploadPath + fileName);

	            //알려지지 않은 파일 타입.
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.add("Content-Disposition", "attatchment; filename=\"" + 
	                    new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + 
	                    "\"");
	             
	            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	        } catch(Exception e) {
	            e.printStackTrace();
	            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	        } finally {
	            in.close();
	        }
	        
	        return entity;
	    }
	
	@RequestMapping(value="/borderList.do")
	public String borderList(HttpServletRequest request,ModelMap model) throws Exception{
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor",null);
	
		//Start페이징처리
		String pageNo="";
		pageNo = request.getParameter("pageNo");
		if(pageNo==null||pageNo.equals(""))
		{
		pageNo ="1";
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt(pageNo)); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈ex:10입력<1>~<10>
	
		int currentPageNo = paginationInfo.getCurrentPageNo();//현재페이지번호
		int recordCountPerPage = paginationInfo.getRecordCountPerPage(); ////한 페이지에 게시되는 게시물 건수
	
		paramMap.put("currentPageNo", currentPageNo);
		paramMap.put("recordCountPerPage", recordCountPerPage );
	
		borderService.selectBorder(paramMap);
	
		    int listcount = 0;
		    listcount= Integer.parseInt(paramMap.get("list_count").toString());
		    paginationInfo.setTotalRecordCount(listcount); //전체 게시물 건 수
		    //End페이징처리
	
		list = (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
		model.addAttribute("borderlist",list);
		 model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("pageNo",pageNo);
	
		return "border/borderlist";
		}
	
		@RequestMapping(value="/borderView.do")
		public String borderView(HttpServletRequest request,ModelMap model) throws Exception
		{
			ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String no = request.getParameter("no").toString();
			
			paramMap.put("borderid", no);
			paramMap.put("ref_cursor", null);
	
			borderService.selectBorderView(paramMap);
			
			list = (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
			
			if(request.getSession().getAttribute("USER_ID") == null)
			{
				model.addAttribute("userId", "");
			}
			else
			{
				model.addAttribute("userId", request.getSession().getAttribute("USER_ID").toString());
	
			}
			
			model.addAttribute("resultList", list);
			
			return "border/borderview";
		}

	@RequestMapping(value="/borderReply.do")
	public String borderReply(HttpServletRequest request,ModelMap model) throws Exception
	{
		String userId="";
		String no = "";
		
		//글쓰기권한 검사도 가능.
		if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/login.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
		}
		
		no = request.getParameter("no").toString();
		model.addAttribute("userId",userId);
		model.addAttribute("no",no);
		
		return "border/borderreply";
	}
	
	@RequestMapping(value="/borderReplyReq.do")
	public String borderReplyReq(HttpServletRequest request,ModelMap model)throws Exception
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		
		String title = request.getParameter("title").toString();
		String mytextarea = request.getParameter("mytextarea").toString();
		String userId= "";
		String no = request.getParameter("no").toString();
		
		//javascript유효성을 앞서서 진행해주시고요.
		//서버단에서 재검증.
		if(title.length()>15)
		{	
			return "redirect:/borderList.do";
		}
		//2000자이상이면
		else if(mytextarea.length()>2000)
		{

			return "redirect:/borderList.do";
		}
		else if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/borderList.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
			//borderView에서 borderid로 보냈기에 borderid로 통일
			paramMap.put("borderid", no);	
			paramMap.put("userId", userId);
			paramMap.put("userIp", request.getRemoteAddr());
			paramMap.put("title", title);
			paramMap.put("mytextarea", mytextarea);
		}
		
		borderService.insertBorderReply(paramMap);
		
		return "redirect:/borderList.do";
	}
	

	@RequestMapping(value="/borderEdit.do")
	public String borderEdit(HttpServletRequest request,ModelMap model) throws Exception
	{
		String userId="";
		String no = "";
		
		//글쓰기권한 검사도 가능.
		if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/login.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
		}
		
		no = request.getParameter("no").toString();
		model.addAttribute("userId",userId);
		model.addAttribute("no",no);
		
		return "border/borderedit";
	}

	@RequestMapping(value="/borderEditReq.do")
	public String borderEditReq(HttpServletRequest request,ModelMap model) throws Exception
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		
		String title = request.getParameter("title").toString();
		String mytextarea = request.getParameter("mytextarea").toString();
		String userId= "";
		String no = request.getParameter("no").toString();
		
		//javascript유효성을 앞서서 진행해주시고요.
		//서버단에서 재검증.
		if(title.length()>15)
		{	
			return "redirect:/borderList.do";
		}
		//2000자이상이면
		else if(mytextarea.length()>2000)
		{

			return "redirect:/borderList.do";
		}
		else if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/borderList.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
			//borderView에서 borderid로 보냈기에 borderid로 통일
			paramMap.put("borderid", no);	
			paramMap.put("userId", userId);
			paramMap.put("userIp", request.getRemoteAddr());
			paramMap.put("title", title);
			paramMap.put("mytextarea", mytextarea);
		}
		
		borderService.updateBorderEdit(paramMap);
		
		return "redirect:/borderList.do";
	}

	@RequestMapping(value="/borderRemove.do")
	public String borderRemove(HttpServletRequest request,ModelMap model) throws Exception
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		
		String userId= "";
		String no = request.getParameter("no").toString();
		
		//javascript유효성을 앞서서 진행해주시고요.
		if(request.getSession().getAttribute("USER_ID") == null)
		{
			request.getSession().invalidate();
			return "redirect:/borderList.do";
		}
		else
		{
			userId = request.getSession().getAttribute("USER_ID").toString();
			//borderView에서 borderid로 보냈기에 borderid로 통일
			paramMap.put("borderid", no);	
			paramMap.put("userId", userId);
			paramMap.put("userIp", request.getRemoteAddr());

		}
		
		borderService.updateBorderRemove(paramMap);
		
		return "redirect:/borderList.do";
	}
	
	@RequestMapping(value="/proctest.do")
	public String proctest(HttpServletRequest request,ModelMap model) throws Exception{
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", "abc123");
	
		borderService.procdo(paramMap);
		list = (ArrayList<HashMap<String,Object>>)paramMap.get("ref_cursor");
	
		System.out.println("stop");
	
		return "redirect:/borderList.do";
	}
}
