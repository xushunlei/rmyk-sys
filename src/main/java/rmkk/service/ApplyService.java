package rmkk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmkk.dao.DocumentMapper;
import rmkk.dao.SealMapper;
import rmkk.dao.TaskMapper;
import rmkk.entity.Document;
import rmkk.entity.Seal;
import rmkk.entity.Task;
import rmkk.entity.User;
@Service
public class ApplyService implements IApplyService
{
	@Autowired
	private DocumentMapper docDao;
	@Autowired
	private TaskMapper taskDao;
	@Autowired
	private SealMapper sealDao;
	@Override
	public void initiateApply(Document doc,HttpSession session)
	{
		User login_user= (User) session.getAttribute("user");
		if(docDao.insertSelective(doc)==1){
			Task task=new Task();
			task.setDocumentid(doc.getDocumentid());//设置申请文件编号
			task.setTasktype(0);//设置任务类型。0文件合同，1单据
			task.setSponsorid(login_user.getUserid());//设置申请人
			taskDao.insertSelective(task);
		}
	}
	@Override
	public List<Seal> getSeal(int sealstate)
	{
		return sealDao.getByState(sealstate);
	}
	@Override
	public boolean createDocument(Document document)
	{
		return docDao.insertSelective(document)==1;
	}

}
