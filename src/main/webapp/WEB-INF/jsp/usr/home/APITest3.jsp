<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="APITest4" />
<%@ include file="../common/head.jsp"%>

<script>
	const submitForm = function(form) {
		let numOfRows = form.numOfRows.value.trim();
		let searchString = form.searchString.value.trim();
		let title = form.title.value.trim();

		if (title.length == 0) {
			title = "";
		}
		
		getData(numOfRows, searchString, title);
		
		return false;
	};
	
	const getData = function(numOfRows, searchString, title) {
		const proxy = "https://cors-anywhere.herokuapp.com/";
		const url = "http://kipo-api.kipi.or.kr/openapi/service/trademarkInfoSearchService/getWordSearch";
		const API_KEY = "sAq%2FjoEjRgCx1pEx0jXkpT3nyI60HFLSVjZPlikqVWtA11ILxUlYCcur2%2F%2F2Ff%2Ff4zZZqWPsUYSBkIdb7U%2Bccg%3D%3D";
		
		let queryParams  = "?" + encodeURIComponent("serviceKey") + "=" + API_KEY;
			queryParams += "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent(numOfRows);
			queryParams += "&" + encodeURIComponent("searchString") + "=" + encodeURIComponent(searchString);
			queryParams += "&" + encodeURIComponent("title") + "=" + encodeURIComponent(title);
			
		$.ajax({
	        url: proxy + url + queryParams,
	        cache: false,
	        dataType: "xml",
	        success: function(data) {
	        	let successYN = $(data).find("successYN").text();
	        	let resultMsg = $(data).find("resultMsg").text();
	        	
	        	if(successYN == "N" ) {
	        		alert(resultMsg);
	        		return false;
	        	}
	        	
	        	$("#product").empty();
	        	
	        	$(data).find("item").each(function() {
	        		const html = `
	        			<tr class="hover">
							<td>\${ $(this).find("indexNo").text() }</td>
							<td>\${ $(this).find("applicantName").text() }</td>
							<td>\${ $(this).find("applicationDate").text() }</td>
							<td>\${ $(this).find("applicationNumber").text() }</td>
						</tr>
	        		`
	        		
	        		$("#product").append(html)
	        	})
	            
	        },
	        error: function(){
	            alert('Error loading XML document');
	        }
	
	    })
	};
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form onsubmit="return submitForm(this);">
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>??????</th>
							<td><select name="numOfRows" class="select select-bordered w-full">
									<option value="10">10?????? ??????</option>
									<option value="20">20?????? ??????</option>
									<option value="50">50?????? ??????</option>
							</select></td>
						</tr>
						<tr>
							<th>??????</th>
							<td><input maxlength="38" class="input input-ghost w-full text-lg border-gray-400" type="text"
								name="searchString" placeholder="??????" /></td>
						</tr>
						<tr>
							<th>??????</th>
							<td><input maxlength="800" class="input input-ghost w-full text-lg border-gray-400" type="text" name="title"
								placeholder="??????" /></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-outline btn-accent w-full">??????</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</section>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<table class="table table-zebra w-full">
			<thead>
				<tr>
					<th class="text-sm">??????</th>
					<th class="text-sm">????????? ??????</th>
					<th class="text-sm">????????????</th>
					<th class="text-sm">????????????</th>
				</tr>
			</thead>

			<tbody id="product">

			</tbody>
		</table>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>