package rmkk.dao;

import rmkk.entity.Document;

public interface DocumentMapper {
	int insertSelective(Document record);
}