package rmkk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rmkk.dao.TaskMapper;
import rmkk.entity.Document;
import rmkk.entity.Seal;
import rmkk.entity.Task;
import rmkk.entity.User;
import rmkk.service.IApplyService;
import rmkk.util.FileOperator;
import rmkk.util.RandStr;

/**
 * 申请流程
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="apply/")
public class ApplyController
{
	static String DOCUMENT_SAVE_PATH="G:\\Java\\eclipse\\workspace\\ZDGZ\\src\\main\\webapp\\WEB-INF\\uploadfile";
	@Autowired
	private IApplyService applyService;
	@RequestMapping(value="useSeal")
	public String applyUseSeal(HttpServletRequest request,HttpServletResponse response){
		String savePath ="G:\\Java\\eclipse\\workspace\\ZDGZ\\src\\main\\webapp\\WEB-INF\\uploadfile";
		try{
			Document document =new Document();
			 //使用Apache文件上传组件处理文件上传步骤：
			 //1、创建一个DiskFileItemFactory工厂
			 DiskFileItemFactory factory = new DiskFileItemFactory();
			 //2、创建一个文件上传解析器
			 ServletFileUpload upload = new ServletFileUpload(factory);
			 //解决上传文件名的中文乱码
			 upload.setHeaderEncoding("UTF-8"); 
			 //3、判断提交上来的数据是否是上传表单的数据
			 if(!ServletFileUpload.isMultipartContent(request)){
			 //按照传统方式获取数据
			      return "login";
			 }
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
			   //如果fileitem中封装的是普通输入项的数据
			   if(item.isFormField()){
			      String name = item.getFieldName();
			      //解决普通输入项的数据的中文乱码问题
			      String value = item.getString("UTF-8");
			      //value = new String(value.getBytes("iso8859-1"),"UTF-8");
			      System.out.println(name + "=" + value);
			 	}else{
			 		String filename =item.getName();
				 	System.out.println(filename);
				 	if(filename==null || filename.trim().equals("")){
				 			return null;
				 	}
				 	//生成唯一编号
				 	String number=RandStr.getRandomByModel("DOC");
				 	//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
				 	//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
				 	filename = number+filename.substring(filename.lastIndexOf("\\")+1);
				 	if(FileOperator.upload(savePath, filename, item)){
					 	String docUrl=savePath+"\\"+filename;
					 	document.setDocumentid(number);
					 	document.setDocumentname(filename);
					 	document.setDocumenturl(docUrl);
					 	String sealid=request.getParameter("seal");
					 	if(!"— —请选择— —".equals(sealid))
					 		document.setSealid(sealid);
					 	String rideid=request.getParameter("rideseal");
					 	if(!"— —请选择— —".equals(rideid)){
					 		document.setNeedride(1);
					 		document.setRideid(rideid);
					 	}
					 	applyService.createDocument(document);
				 	}
			 	}
			 }
			
		}catch(Exception e){
			
		}
		
//		Document document=new Document();
//		document.setDocumentname(request.getParameter("name"));
		return "login";
	}
	
	@ResponseBody//返回json数据
	@RequestMapping("get_rideseal")
	public List<Map<String, String>> getRideseal(){
		List<Seal> seals=applyService.getSeal(1);
		List<Map<String, String>> result=new ArrayList<Map<String, String>>();
		for(Seal seal:seals){
			Map<String, String> map=new HashMap<String, String>();
			map.put("id", seal.getSealid());
			map.put("sealname", seal.getSealname());
			result.add(map);
		}
		return result;
	}
	
	@RequestMapping("upload")
	public String uploadFile(HttpServletRequest request){
		//获取并解析文件类型和支持最大值  
        String functionId = request.getParameter("functionId");  
        String fileType = request.getParameter("fileType");  
        String maxSize = request.getParameter("maxSize");  
          
        //临时目录名  
        //String tempPath = fileUploadPro.getProperty("tempPath");  
        //真实目录名  
        //String filePath = fileUploadPro.getProperty("filePath");  
        File file=new File(DOCUMENT_SAVE_PATH);
        //判断上传文件的保存目录是否存在
      		if (!file.exists() && !file.isDirectory()) {
      		     //System.out.println(savePath+"目录不存在，需要创建");
      		     //创建目录
      		     file.mkdir();
      		}  
//        FileUtil.createFolder(tempPath);  
//        FileUtil.createFolder(filePath);  
          
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //最大缓存  
        factory.setSizeThreshold(5*1024);  
        //设置临时文件目录  
        //factory.setRepository(new File(tempPath));
        ServletFileUpload upload = new ServletFileUpload(factory);  
        if(maxSize!=null && !"".equals(maxSize.trim())){  
            //文件最大上限  
            upload.setSizeMax(Integer.valueOf(maxSize)*1024*1024);  
        }  
        
        try
		{
        	//获取文件所有列表
        	List<FileItem> list = upload.parseRequest(request);
        	for(FileItem item:list){
        		if(!item.isFormField()){
        			String fileName=item.getName();//获取文件名
        			//检查后缀,文件格式
        			String fileEndName=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        			if(fileType!=null&&!"".equals(fileType.trim())){
        				boolean flag=false;
        				String[] arrType=fileType.split(",");
        				for(String tempType:arrType){
        					if(fileEndName.equals(tempType.toLowerCase())){
        						flag=true;
        						break;
        					}
        				}
        				if(!flag){//格式不正确
        					return null;
        				}
        			}
        			item.write(file);
        		}
        	}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}
}
