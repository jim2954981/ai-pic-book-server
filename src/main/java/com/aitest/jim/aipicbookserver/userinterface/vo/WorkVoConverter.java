package com.aitest.jim.aipicbookserver.userinterface.vo;

import com.aitest.jim.aipicbookserver.domain.work.ParagraphDomain;
import com.aitest.jim.aipicbookserver.domain.work.WorkDomain;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liuyi58
 * @since 2023-06-22  23:30
 **/
@Service
public class WorkVoConverter {
	public WorkVO convertFromDomain(WorkDomain workDomain) {
		WorkVO workVO = new WorkVO().setId(workDomain.getId()).setUserId(workDomain.getUserId()).setTitle(workDomain.getTitle())
				.setCreateTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(workDomain.getCreateTime()))).setFirstPic(workDomain.getFirstPic());
		if (!CollectionUtils.isEmpty(workDomain.getParagraphs())) {
			List<ParaGraphVO> paraGraphVOList = workDomain.getParagraphs().stream().map(this::convertFromDomain).collect(Collectors.toList());
			workVO.setParaGraphVOList(paraGraphVOList);
		}
		return workVO;
	}

	private ParaGraphVO convertFromDomain(ParagraphDomain paragraphDomain) {
		return new ParaGraphVO().setWorkId(paragraphDomain.getWorkId()).setId(paragraphDomain.getId()).setContent(paragraphDomain.getContent()).setPic(paragraphDomain.getPic());
	}
}
