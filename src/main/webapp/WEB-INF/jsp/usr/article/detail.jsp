<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Detail" />
<%@ include file="../common/head.jsp"%>

<script>
	// 파라미터 값 받을 객체 변수 생성
	const params = {};
	// 객체 id 에 detail 페이지 주소에 있는 id 값을 넣음
	params.id = parseInt('${param.id}');
	
	// 함수명
	function ArticleDetail__increaseViewCnt() {
		// 해당 주소에서 가져온 json을 가져옴
		$.get('doIncreaseViewCntRd', {
			// 파라미터 값 id에 detail에 있는 param.id 를 넣어줌
			id : params.id,
			// ajaxMode 설정
			ajaxMode : 'Y'
		// doIncreaseViewCntRd에서 처리된 객체를 매개변수로 받아옴
		}, function(data){
			// 받아온 데이터를 통해 javascript를 통해 html 수정
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