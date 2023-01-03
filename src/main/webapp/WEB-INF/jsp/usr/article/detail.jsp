<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/toastUiEditorLib.jsp" %>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
	
	// 조회수 관련 AJAX
	function ArticleDetail__increaseViewCnt() {
		const localStorageKey = 'article__' + params.id + '__alreadyViews';
		
		if(localStorage.getItem(localStorageKey)) {
			return false;
		}
		
		localStorage.setItem(localStorageKey, true);
		
		$.get('doIncreaseViewCntRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data){
			$('.article-detail__view-count').empty().html(data.data1);
		}, 'json');
	}
	
	// 로그인 회원에 따른 리액션 버튼 정보 불러오기
	function ReactionPoint__getReactionPoint() {
		$.get('../reactionPoint/getReactionPoint', {
			id : params.id,
			relTypeCode : 'article',
			ajaxMode : 'Y'
		}, function(data){
			let pointUpBtn = $("#pointUp");
			let pointDownBtn = $("#pointDown");
			
			// 좋아요 눌린 상태
			if(data.data1.pointSum > 0) {
				pointUpBtn.removeClass('btn-outline');
				pointUpBtn.attr("onclick", "location.href='../reactionPoint/delReactionPoint?id=${article.id}&relTypeCode=article&point=1'");
			}
			// 싫어요 눌린 상태
			else if(data.data1.pointSum < 0) {
				pointDownBtn.removeClass('btn-outline');
				pointDownBtn.attr("onclick", "location.href='../reactionPoint/delReactionPoint?id=${article.id}&relTypeCode=article&point=-1'");
			}
			
		}, 'json');	
	}
	
	
	$(function() {
		ReactionPoint__getReactionPoint();
		ArticleDetail__increaseViewCnt();
	});
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<table class="table table-zebra w-full">
				<colgroup>
					<col width="200" />
				</colgroup>

				<tbody>
					<tr>
						<th>번호</th>
						<td><div class="badge badge-lg bg-purple-600 border-transparent font-bold text-white">${article.id}</div></td>
					</tr>
					<tr>
						<th>작성 날짜</th>
						<td>${article.regDate}</td>
					</tr>
					<tr>
						<th>수정 날짜</th>
						<td>${article.updateDate}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.writerName}</td>
					</tr>
					<tr>
						<th>추천</th>
						<td>
							<div>
								<button id="pointUp" class="btn btn-outline btn-success tooltip" data-tip="이 글이 좋다면 클릭!" onclick="location.href='../reactionPoint/doReactionPoint?id=${article.id}&relTypeCode=article&point=1'">
									좋아요 <i class="fa-solid fa-thumbs-up"></i> <span class="badge badge-primary ml-2">${article.pointUp }</span>
								</button>
								<div class="avatar placeholder tooltip" data-tip="총합 : ${article.pointSum }">
									<div class="bg-neutral-focus text-neutral-content rounded-full w-16">
										<span class="text-xl"><span class="text-xl">${article.pointSum }</span></span>
									</div>
								</div>
								<button id="pointDown" class="btn btn-outline btn-error tooltip" data-tip="이 글이 싫다면 클릭.." onclick="location.href='../reactionPoint/doReactionPoint?id=${article.id}&relTypeCode=article&point=-1'">
									싫어요 <i class="fa-solid fa-thumbs-down"></i> <span class="badge badge-secondary ml-2">${article.pointDown * -1 }</span>
								</button>
							</div>
						</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><span class="badge badge-lg article-detail__view-count">${article.viewCnt}</span></td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<div class="toast-ui-viewer text-left">
								<script type="text/x-template">
									${article.getForPrintBody()}
								</script>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btns flex justify-between mt-5">
			<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
			<c:if test="${article.actorCanChangeData}">
				<div>
					<a class="btn btn-secondary" href="modify?id=${article.id}">수정</a>
					<a class="btn btn-secondary" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
				</div>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="./reply.jsp"%>
<%@ include file="../common/foot.jsp"%>