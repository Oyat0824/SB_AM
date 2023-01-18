<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member Modify" />
<%@ include file="../common/head.jsp"%>

<script>
	const MemberModify__submit = function(form) {
		let reg_num = /^[0-9]{10,11}$/;
		let reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; 

		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value.length == 0) {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
			
			return false;
		}
		
		const deleteProfileImgFileInput = form["deleteFile__member__0__extra__profileImg__1"];
		
		if (deleteProfileImgFileInput.checked) {
			form["file__member__0__extra__profileImg__1"].value = '';
		}
		
		const maxSizeMb = 10;
		const maxSize = maxSizeMb * 1024 * 1024;
		
		const profileImgFileInput = form["file__member__0__extra__profileImg__1"];
		if (profileImgFileInput.value) {
			if (profileImgFileInput.files[0].size > maxSize) {
				alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요");
				profileImgFileInput.focus();
				return;
			}
		}
		
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value.length == 0) {
			alert("전화번호를 입력해주세요.");
			form.cellphoneNum.focus();
			
			return false;
		}
		if(!reg_num.test(form.cellphoneNum.value)) {
			alert("전화번호 형식이 틀렸습니다.\n" +
				  "ex) 01012345678"
				 );
			return false;
		}
		
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert("이메일을 입력해주세요.");
			form.email.focus();
			
			return false;
		}
		if(!reg_email.test(form.email.value)) {
			alert("이메일 형식이 틀렸습니다.\n" +
				  "ex) abc@abc.com"
				 );
			return false;
		}

	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doModify" method="POST" enctype="multipart/form-data" onsubmit="return MemberModify__submit(this);">
			<input type="hidden" name="memberModifyAuthKey" value="${param.memberModifyAuthKey}" />
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>가입일</th>
							<td>${rq.loginedMember.regDate }</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td>${rq.loginedMember.loginId }</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${rq.loginedMember.name }</td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="text" name="nickname"
								placeholder="닉네임을 입력해주세요." value="${rq.loginedMember.nickname }" /></td>
						</tr>
						<tr>
							<th>프로필 이미지</th>
							<td>
								<img class="w-40 h-40 object-cover" src="${rq.getProfileImgUri(rq.loginedMemberId) }" onerror="${rq.getRemoveProfileImgIfNotExitOnErrorHtmlAttr() }" alt="" />
								<div class="mt-2">
									<label class="cursor-pointer inline-flex">
										<span class="label-text mr-2 mt-1">이미지 삭제</span>
										<div>
											<input class="ckeckbox" type="checkbox" name="deleteFile__member__0__extra__profileImg__1" value="Y" />
										</div>
									</label>
								</div>
								<input accept="image/gif, image/jpeg, image/png" type="file" name="file__member__0__extra__profileImg__1" placeholder="프로필 이미지를 선택해주세요" />
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="tel" name=cellphoneNum
								placeholder="전화번호를 입력해주세요." value="${rq.loginedMember.cellphoneNum }" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="email" name="email"
								placeholder="이메일을 입력해주세요." value="${rq.loginedMember.email }" /></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="btns flex justify-between mt-5">
				<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
				<div>
					<a class="btn btn-outline btn-info" href="passwordModify?memberModifyAuthKey=${param.memberModifyAuthKey }" >비밀번호 변경</a>
					<button class="btn btn-outline btn-success">회원정보 수정</button>
				</div>
			</div>
		</form>

	</div>
</section>
<%@ include file="../common/foot.jsp"%>