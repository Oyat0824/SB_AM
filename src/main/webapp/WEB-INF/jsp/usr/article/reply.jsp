<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	const ReplyWrite__submitForm = function(form) {
		form.body.value = form.body.value.trim();
		form.relId.value = form.relId.value.trim();
		
		if(form.body.value.length < 2) {
			alert("2글자 이상 작성해주세요.");
			form.body.focus();
			return false;
		}
		
		if(form.relId.value != ${article.id}) {
			alert("잘못된 접근 방식입니다.");
			location.reload();
			return false;
		}
		
		form.submit();
	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3 pt-5 border-t border-gray-400">
		<h2>댓글<span class="text-base">(${replies.size()} 개)</span></h2>
		
		<c:forEach var="reply" items="${replies}">
			<div class="py-2 pl-16 border-b text-base relative text-md-set">
				<div class="font-semibold"><span class="${reply.memberId == rq.loginedMember.id ? 'text-green-500' : '' }">${reply.writerName }</span></div>
				<div><span>${reply.getForPrintBody() }</span></div>
				<div class="text-sm text-gray-400">${reply.updateDate }</div>
				
				<c:if test="${reply.memberId == rq.loginedMember.id}">
					<div class="text-sm absolute top-3 right-5 transition opacity-0 invisible text-md-call">
						<a href="" class="hover:text-red-300 hover:underline">수정</a>
						<a href="" class="hover:text-red-300 hover:underline">삭제</a>
					</div>
				</c:if>
			</div>
		</c:forEach>
		
		
		<c:if test="${rq.getLoginedMemberId() != 0}">
			<form action="../reply/doWrite" method="GET" onsubmit="return ReplyWrite__submitForm(this);">
				<input type="hidden" name="relTypeCode" value="article" />
				<input type="hidden" name="relId" value="${article.id }" />
				<div class="mt-5 p-5 w-full">
					<h2 class="mb-2">✍ 댓글 쓰기 :: ${rq.loginedMember.nickname }</h2>
					<textarea class="textarea textarea-info w-full h-24" name="body"></textarea>
					<div class="flex justify-end mt-1"><button class="btn btn-outline btn-sm">작성</button></div>
				</div>
			</form>
		</c:if>
	</div>
</section>