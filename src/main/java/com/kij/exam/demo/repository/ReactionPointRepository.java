package com.kij.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kij.exam.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointRepository {
	@Select("""
			SELECT
			IFNULL(SUM(`point`), 0) AS pointSum,
			IFNULL(SUM(IF(`point` > 0, `point`, 0)), 0) AS pointUp,
			IFNULL(SUM(IF(`point` < 0, `point`, 0)), 0) AS pointDown
			FROM reactionPoint
			WHERE relTypeCode = 'article'
			AND memberId = #{loginedMemberId}
			AND relId = #{id}
			""")

	ReactionPoint getReactionPoint(int loginedMemberId, int id);

}
