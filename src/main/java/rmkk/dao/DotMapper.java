package rmkk.dao;

import rmkk.entity.Dot;

public interface DotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dot record);

    int insertSelective(Dot record);

    Dot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dot record);

    int updateByPrimaryKey(Dot record);
}