package rmkk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmkk.dao.UserMapper;
import rmkk.entity.User;
@Service
public class UserService implements IUserService
{
	@Autowired
	private UserMapper userDao;
	@Override
	public User getUserByUser(User user)
	{
		return userDao.getUserByUser(user);
	}
	@Override
	public boolean addUser(User user)
	{
		if(userDao.insertSelective(user)==1){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public int getUserByName(String username)
	{
		return userDao.getUserByName(username);
	}

}
