<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="APITest" />
<%@ include file="../common/head.jsp"%>

<script type="text/javascript" src="/resource/xml2json.js"></script>

<script>
	const API_KEY = "sAq%2FjoEjRgCx1pEx0jXkpT3nyI60HFLSVjZPlikqVWtA11ILxUlYCcur2%2F%2F2Ff%2Ff4zZZqWPsUYSBkIdb7U%2Bccg%3D%3D";
	
	async function getData() {
		const url = `http://apis.data.go.kr/1790387/covid19HospitalBedStatus/covid19HospitalBedStatusJson?serviceKey=\${API_KEY}`;
		
		const response = await fetch(url);
		const data = await response.json();
		
		console.log(data);
		
		$(".APITest").html(`<div class="text-red-400">\${data.response.result[0].itsv_bed_avlb}</div>`);
	}
	
	getData();
	
	var xml = '<?xml version="1.0" encoding="UTF-8"?>'
        + '<catalog>'
        + '<book id="bk101">'
        + '<author>Gambardella, Matthew</author>'
        + '<title>XML Developer\'s Guide</title>'
        + '<genre>Computer</genre>'
        + '<price>44.95</price>'
        + '<publish_date>2000-10-01</publish_date>'
        + '<description>An in-depth look at creating applications with XML.</description>'
        + '</book>'
        + '</catalog>';
        
	var xml2json = new XMLtoJSON();
	var objson = xml2json.fromStr(xml);	// object 형식			
	var strjson = xml2json.fromStr(xml, 'string');	// string 형식

	console.log(objson)
	console.log(strjson)
	       
	var aJson = objson;
	
	document.write(aJson.catalog.book.title['#text']);
	
</script>

<div class="APITest"></div>

<%@ include file="../common/foot.jsp"%>