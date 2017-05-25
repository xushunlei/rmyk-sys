package rmkk.dao;

import rmkk.entity.User;

public interface UserMapper {
	User getUserByUser(User user);
	int getUserByName(String username);
    int insert(User record);

    int insertSelective(User record);

    int deleteByPrimaryKey(Integer id);
    User selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
}