package com.kij.exam.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kij.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {
	// 회원가입
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			`nickname` = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
	// 아이디를 통해 멤버 가져오기
	@Select("""
			SELECT *
			FROM `member`
			WHERE id = #{id}
			""")
	public Member getMemberById(int id);

	// 로그인 아이디를 통해 멤버 가져오기
	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);
	
	// 이름과 이메일이 같은 사람이 있는가
	@Select("""
			SELECT *
			FROM `member`
			WHERE `name` = #{name}
			AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);
	
	// 마지막 번호 가져오기
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
	
	@Update("""
			<script>
				UPDATE `member`
					<set>
						updateDate = NOW(),
						<if test="nickname != null">
							nickname = #{nickname},
						</if>
						<if test="cellphoneNum != null">
							cellphoneNum = #{cellphoneNum},
						</if>
						<if test="email != null">
							email = #{email}
						</if>
					</set>
					WHERE id = #{loginedMemberId}
			</script>
			""")
	public void doModify(int loginedMemberId, String nickname, String cellphoneNum, String email);
	
	@Update("""
			UPDATE `member`
			SET updateDate = NOW(),
			loginPw = #{loginPw}
			WHERE id = #{loginedMemberId}
			""")
	public void doPasswordModify(int loginedMemberId, String loginPw);

	


}