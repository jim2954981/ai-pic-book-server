package com.aitest.jim.aipicbookserver.service.mapper;

import com.aitest.jim.aipicbookserver.domain.work.ParagraphPO;
import com.aitest.jim.aipicbookserver.domain.work.WorkPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Liuyi58
 * @since 2023-06-18  19:38
 **/
@Repository
public interface WorkMapper {
	void insertWork(@Param("work") WorkPO workPO);

	void batchInsertParagraphs(@Param("list")List<ParagraphPO> paragraphPOS);

	List<WorkPO> getWorkList(@Param("userId") long userId);

	WorkPO queryById(@Param("id")long id);

	List<ParagraphPO> queryParaGraphsByWorkId(@Param("workId")long workId);
}

