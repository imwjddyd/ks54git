package ksmybatis.admin.common.service;

import java.util.List;

import ksmybatis.admin.common.domain.CommonCode;

public interface CommonService {

	List<CommonCode> getCommonCodeListByGroupCode(String groupCode);
}
		