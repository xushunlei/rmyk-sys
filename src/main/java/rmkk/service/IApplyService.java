package rmkk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import rmkk.entity.Document;
import rmkk.entity.Seal;

/**
 * 申请用章功能接口
 * @author Administrator
 *
 */
public interface IApplyService
{
	/**
	 * 生成申请文件
	 * @param document
	 */
	boolean createDocument(Document document);
	/**
	 * 发起申请
	 * @param doc
	 */
	void initiateApply(Document doc,HttpSession session);
	/**
	 * 获取指定状态的印章
	 * @param sealstate印章状态：0停用，1启用，2销毁
	 * @return
	 */
	List<Seal> getSeal(int sealstate);
}
