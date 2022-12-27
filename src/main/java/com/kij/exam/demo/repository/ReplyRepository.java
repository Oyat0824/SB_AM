package com.kij.exam.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyRepository {
	@Insert("""
			INSERT INTO reply
			SET regDate = NOW(),
				updateDate = NOW(),
				memberId = #{loginedMemberId},
				relTypeCode = #{relTypeCode},
				relId = #{relId},
				`body` = #{body};
			""")
	void writeReply(int loginedMemberId, int relId, String relTypeCode, String body);
}
