package rmkk.service;

import rmkk.entity.User;
/**
 * 用户操作功能接口
 * @author Administrator
 *
 */
public interface IUserService
{
	User getUserByUser(User user);
	int getUserByName(String username);
	boolean addUser(User user);
}
