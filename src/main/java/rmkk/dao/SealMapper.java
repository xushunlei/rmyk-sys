package rmkk.dao;

import java.util.List;

import rmkk.entity.Seal;

public interface SealMapper {
	List<Seal> getByState(Integer state);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Seal record);

    int insertSelective(Seal record);

    Seal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seal record);

    int updateByPrimaryKey(Seal record);
}