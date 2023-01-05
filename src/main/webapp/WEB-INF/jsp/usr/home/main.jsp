<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jsp"%>

<section class="mt-8">
	<div class="container mx-auto">
		<div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias provident repellat cumque fugiat quae
			consequatur magni doloribus illo sequi ipsum ut architecto adipisci nesciunt nostrum eos sit aperiam at quasi.</div>
		<div>안녕하세요</div>

		<span class="modal-exam">팝업 예시</span>
	</div>
</section>

<div class="layer-bg"></div>
<div class="layer">
	<h2>MODAL</h2>
	Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi nemo excepturi ipsam temporibus labore possimus fugiat
	molestiae pariatur itaque ducimus odit corrupti eos incidunt necessitatibus dignissimos perspiciatis sapiente molestias
	animi!
	
	<span class="close">&times;</span>
	<button class="close-btn" type="button">CLOSE</button>
</div>

<%@ include file="../common/foot.jsp"%>