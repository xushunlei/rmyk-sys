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
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
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
	
	@ResponseBody
	@RequestMapping("upload")
	public String uploadFile(HttpServletRequest request){
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
	     String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
	     //上传时生成的临时文件保存目录
	     String tempPath = request.getSession().getServletContext().getRealPath("/WEB-INF/temp");
	     System.out.println("======="+savePath+"\n======="+tempPath);
	     File tmpFile = new File(tempPath);
	     if (!tmpFile.exists()) {
	      //创建临时目录
	      tmpFile.mkdir();
	     }
	     
	     //消息提示
	     String message = "";
	     try{
	      //使用Apache文件上传组件处理文件上传步骤：
	      //1、创建一个DiskFileItemFactory工厂
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
	      factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
	      //设置上传时生成的临时文件的保存目录
	      factory.setRepository(tmpFile);
	      //2、创建一个文件上传解析器
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      //监听文件上传进度
	      upload.setProgressListener(new ProgressListener(){
	       public void update(long pBytesRead, long pContentLength, int arg2) {
	        System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
	        /**
	        * 文件大小为：14608,当前已处理：4096
	         文件大小为：14608,当前已处理：7367
	         文件大小为：14608,当前已处理：11419
	         文件大小为：14608,当前已处理：14608
	        */
	       }
	      });
	      //解决上传文件名的中文乱码
	      upload.setHeaderEncoding("UTF-8"); 
	      //3、判断提交上来的数据是否是上传表单的数据
	      if(!ServletFileUpload.isMultipartContent(request)){
	       //按照传统方式获取数据
	       return null;
	      }
	       
	      //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
	      upload.setFileSizeMax(1024*1024);
	      //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
	      upload.setSizeMax(1024*1024*10);
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
	       }else{//如果fileitem中封装的是上传文件
	        //得到上传的文件名称，
	        String filename = item.getName();
	        System.out.println(filename);
	        if(filename==null || filename.trim().equals("")){
	         continue;
	        }
	        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
	        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
	        filename = filename.substring(filename.lastIndexOf("\\")+1);
	        message=filename;
	        //得到上传文件的扩展名
	        String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
	        //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
	        System.out.println("上传的文件的扩展名是："+fileExtName);
	        //获取item中的上传文件的输入流
	        InputStream in = item.getInputStream();
	        //得到文件保存的名称
	        String saveFilename = RandStr.getRandomByModel("DOC")+filename;
	        //得到文件的保存目录
	        String realSavePath = makePath(saveFilename, savePath);
	        //创建一个文件输出流
	        FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFilename);
	       //创建一个缓冲区
	        byte buffer[] = new byte[1024];
	        //判断输入流中的数据是否已经读完的标识       
	        int len = 0;
	        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
	        while((len=in.read(buffer))>0){
	         //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
	         out.write(buffer, 0, len);
	        }
	        //关闭输入流
	        in.close();
	        //关闭输出流
	        out.close();        
	        //删除处理文件上传时生成的临时文件        
	        //item.delete();        
	        message = "文件上传成功！";
	       }
	      }
	     }catch (FileUploadBase.FileSizeLimitExceededException e) {
	      e.printStackTrace();
//	      request.setAttribute("message", "单个文件超出最大值！！！");
//	      request.getRequestDispatcher("/message.jsp").forward(request, response);
	      return "单个文件超出最大值！！！";
	     }catch (FileUploadBase.SizeLimitExceededException e) {
	      e.printStackTrace();
//	      request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
//	      request.getRequestDispatcher("/message.jsp").forward(request, response);
	      return "上传文件的总的大小超出限制的最大值！！！";
	     }catch (Exception e) {
//	      message= "文件上传失败！";
	      e.printStackTrace();
	      return "文件上传失败！";
	     }
//	     request.setAttribute("message",message);
//	     request.getRequestDispatcher("/message.jsp").forward(request, response);
	     return message;
	}
	private String makePath(String filename,String savePath){
		   //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		   int hashcode = filename.hashCode();
		   int dir1 = hashcode&0xf; //0--15
		   int dir2 = (hashcode&0xf0)>>4; //0-15
		   //构造新的保存目录
		   String dir = savePath + "\\" + dir1 + "\\" + dir2; //upload\2\3 upload\3\5
		   //File既可以代表文件也可以代表目录  
		   File file = new File(dir);
		  //如果目录不存在
		   if(!file.exists()){
		    //创建目录
		    file.mkdirs();
		  }
		   return dir;
		  }
}
