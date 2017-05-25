package rmkk.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

public class FileOperator
{
	/**
	 * 文件上传
	 * @param savePath 保存路径
	 * @param saveName 保存名
	 * @param item 文件
	 * @return
	 */
	public static boolean upload(String savePath,String saveName,FileItem item){
		//得到上传文件的保存目录，
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
		     //System.out.println(savePath+"目录不存在，需要创建");
		     //创建目录
		     file.mkdir();
		}
		try{
			// 获取item中的上传文件的输入流
			InputStream in = item.getInputStream();
			// 创建一个文件输出流
			FileOutputStream out = new FileOutputStream(savePath + "\\" + saveName);
			// 创建一个缓冲区
			byte buffer[] = new byte[1024];
			// 判断输入流中的数据是否已经读完的标识
			int len = 0;
			// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
			while ((len = in.read(buffer)) > 0)
			{
				// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" +
				// filename)当中
				out.write(buffer, 0, len);
			}
			// 关闭输入流
			in.close();
			// 关闭输出流
			out.close();
			// 删除处理文件上传时生成的临时文件
			item.delete();
		} catch (Exception e){
			return false;
		}
			return true;
	}
}
