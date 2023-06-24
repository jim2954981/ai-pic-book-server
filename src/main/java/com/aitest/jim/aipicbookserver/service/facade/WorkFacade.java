package com.aitest.jim.aipicbookserver.service.facade;

import com.aitest.jim.aipicbookserver.domain.work.CharacterEnum;
import com.aitest.jim.aipicbookserver.domain.work.ParagraphDomain;
import com.aitest.jim.aipicbookserver.domain.work.ParagraphPO;
import com.aitest.jim.aipicbookserver.domain.work.WorkDomain;
import com.aitest.jim.aipicbookserver.domain.work.WorkPO;
import com.aitest.jim.aipicbookserver.service.gateway.WorkGateway;
import com.aitest.jim.aipicbookserver.service.mapper.WorkMapper;
import com.aitest.jim.aipicbookserver.userinterface.vo.WorkCreateRequestVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Liuyi58
 * @since 2023-06-22  15:28
 **/
@Service
public class WorkFacade {
	@Resource
	private WorkGateway workGateway;
	@Resource
	private WorkMapper workMapper;

	public WorkDomain createWork(WorkCreateRequestVO requestVO) throws IOException {
		WorkDomain workDomain =
				new WorkDomain().setCharacter(CharacterEnum.findById(requestVO.getCharacterCode())).setChildAge(requestVO.getChildAge()).setTitle(requestVO.getTitle())
						.setReadTime(requestVO.getReadTime()).setStoryAbstract(requestVO.getContentAbstract()).setUserId(requestVO.getUserId());
		List<ParagraphDomain> paragraphDomainList = workGateway.getContents(workDomain);
		if (CollectionUtils.isEmpty(paragraphDomainList)) {
			return null;
		}
		paragraphDomainList.sort(Comparator.comparingInt(ParagraphDomain::getOrder));
		workDomain.setParagraphs(paragraphDomainList);
		workDomain.setFirstPic(paragraphDomainList.get(0).getPic());
		if (workDomain.getUserId() != 0) {
			saveWork(workDomain, paragraphDomainList);
		}
		return workDomain;
	}

	public List<WorkDomain> getWorkList(long userId) {
		List<WorkPO> workPOS = workMapper.getWorkList(userId);
		if (CollectionUtils.isEmpty(workPOS)) {
			return Collections.emptyList();
		}
		return workPOS.stream().map(WorkDomain::fromPO).collect(Collectors.toList());
	}

	public WorkDomain queryWorkById(long workId) {
		WorkPO workPO = workMapper.queryById(workId);
		if (Objects.isNull(workPO)) {
			return null;
		}
		List<ParagraphPO> paragraphPOS = workMapper.queryParaGraphsByWorkId(workId);
		if (CollectionUtils.isEmpty(paragraphPOS)) {
			return null;
		}
		WorkDomain workDomain = WorkDomain.fromPO(workPO);
		workDomain.setParagraphs(paragraphPOS.stream().map(ParagraphDomain::fromPO).collect(Collectors.toList()));
		return workDomain;
	}

	@Transactional
	public void saveWork(WorkDomain workDomain, List<ParagraphDomain> paragraphDomainList) {
		WorkPO workPO = WorkDomain.toPO(workDomain);
		workMapper.insertWork(workPO);
		workDomain.setId(workPO.getId());
		List<ParagraphPO> paragraphPOS = paragraphDomainList.stream().map(ParagraphDomain::toPO).collect(Collectors.toList());
		workMapper.batchInsertParagraphs(paragraphPOS);
	}
}
