<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	const ReplyWrite__submitForm = function(form) {
		form.body.value = form.body.value.trim();
		
		if (form.id == "doWrite") {
			form.relId.value = form.relId.value.trim();
			
			if(form.relId.value != ${article.id} || form.relTypeCode.value != "article") {
				alert("잘못된 접근 방식입니다.");
				location.reload();
				return false;
			}
		}
		
		if(form.body.value.length < 2) {
			alert("2글자 이상 작성해주세요.");
			form.body.focus();
			return false;
		}
		
		form.submit();
	}
	
	let originalIndex = null;
	let originalForm = null;
	
	const ReplyModify__cancel = function(idx) {
		const replyContent = $("#reply_" + idx);
		replyContent.html(originalForm);
		replyContent.addClass("pl-16").removeClass("px-5");
		
		originalIndex = null;
		originalForm = null;
	}
	
	const ReplyModify__getForm = function(replyId, idx) {
		if(originalIndex != null && originalForm != null) {
			ReplyModify__cancel(originalIndex);
		}
		
		$.get('../reply/getReplyContent', {
			id : replyId,
			ajaxMode : 'Y'
		}, function(data){
			const replyContent = $("#reply_" + idx);
			originalIndex = idx;
			originalForm = replyContent.html();
			replyContent.removeClass("pl-16").addClass("px-5");
			
			const modifyForm = `
			<form action="../reply/doModify" method="GET" onsubmit="return ReplyWrite__submitForm(this);">
				<input type="hidden" name="id" value="\${data.data1.id}" />
				<div class="my-5 p-5 w-full border">
					<h2 class="mb-2 text-sm">✍ 댓글 수정 :: \${data.data1.writerName}</h2>
					<textarea class="textarea textarea-info w-full h-20" name="body">\${data.data1.body}</textarea>
					<div class="flex justify-end mt-1">
						<button type="button" class="mr-2 btn btn-outline btn-sm" onclick="ReplyModify__cancel(\${idx})">취소</button>
						<button type="submit" class="btn btn-outline btn-sm">수정</button>
					</div>
				</div>
			</form>
			`;
			
			replyContent.html(modifyForm);
		}, 'json');
	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3 pt-5 border-t border-gray-400">
		<h2>댓글<span class="text-base">(${replies.size()}개)</span></h2>
		
		<c:forEach var="reply" items="${replies}" varStatus="status">
			<div id="reply_${status.index}" class="py-2 pl-16 border-b text-base relative comment-set hover:bg-gray-50">
				<div class="font-semibold"><span class="${reply.actorCanChangeData ? 'text-green-500' : '' }">${reply.writerName }</span></div>
				<div><span>${reply.getForPrintBody() }</span></div>
				<div class="text-sm text-gray-400">${reply.updateDate }</div>
				
				<c:if test="${reply.actorCanChangeData}">
					<div class="text-sm absolute top-3 right-5 transition opacity-0 invisible comment-call">
						<a href='javascript:void(0);' onclick="ReplyModify__getForm(${reply.id}, ${status.index});" class="hover:text-red-300 hover:underline">수정</a>
						<a href='../reply/doDelete?id=${reply.id}' onclick="return confirm('삭제하시겠습니까?')" class="hover:text-red-300 hover:underline">삭제</a>
					</div>
				</c:if>
			</div>
		</c:forEach>

		<c:if test="${rq.getLoginedMemberId() != 0}">
			<form id="doWrite" action="../reply/doWrite" method="GET" onsubmit="return ReplyWrite__submitForm(this);">
				<input type="hidden" name="relTypeCode" value="article" />
				<input type="hidden" name="relId" value="${article.id }" />
				<div class="my-5 p-5 w-full border">
					<h2 class="mb-2 text-sm">✍ 댓글 작성 :: ${rq.loginedMember.nickname }</h2>
					<textarea class="textarea textarea-info w-full h-20" name="body"></textarea>
					<div class="flex justify-end mt-1"><button class="btn btn-outline btn-sm">작성</button></div>
				</div>
			</form>
		</c:if>
	</div>
</section>