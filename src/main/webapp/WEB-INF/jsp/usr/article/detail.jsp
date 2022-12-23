<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<%@ include file="../common/head.jsp"%>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
	
	function ArticleDetail__increaseViewCnt() {
		const localStorageKey = 'article__' + params.id + '__alreadyViews';
		
		if(localStorage.getItem(localStorageKey)) {
			return;
		}
		
		localStorage.setItem(localStorageKey, true);
		
		$.get('doIncreaseViewCntRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data){
			$('.article-detail__view-count').empty().html(data.data1);
		}, 'json');
		
	}
	
	ArticleDetail__increaseViewCnt();
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
						<td><div class="badge badge-lg">${article.id}</div></td>
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
						<th>좋아요 / 싫어요</th>
						<td>
							<div>
								<button class="btn btn-success">
									👍 좋아요 <span class="badge badge-primary ml-2">${article.pointUp }</span>
								</button>
								<div class="avatar placeholder tooltip" data-tip="총합">
								  <div class="bg-neutral-focus text-neutral-content rounded-full w-16">
								    <span class="text-xl"><span class="text-xl">${article.pointSum }</span></span>
								  </div>
								</div>
								<button class="btn btn-warning">
									👎 싫어요 <span class="badge badge-secondary ml-2">${article.pointDown }</span>
								</button>
							</div>
						</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><span class="badge article-detail__view-count">${article.viewCnt}</span></td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.body}</td>
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
<%@ include file="../common/foot.jsp"%>