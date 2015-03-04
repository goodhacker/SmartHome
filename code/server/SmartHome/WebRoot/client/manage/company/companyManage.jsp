<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

 

</script>
<div class="pageHeader">
	<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="device/CompanyManage" method="post" name="newsSearch">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					标题：<input type="text" name="newsFilter.title" value="${newsFilter.title}" />
				</td>	
                <td>
					内容：<input type="text" name="newsFilter.content"  value="${newsFilter.content}"/>
				</td>							
				<td>
					发布人：<input type="text" name="newsFilter.author" value="${newsFilter.author}" />
				</td>

				<td class="dateRange">
					发布时间:
					<input type="text"  readonly="readonly" class="date" name="newsFilter.startDate" value="${newsFilter.startDate}"/>
					<span class="limit">-</span>
					<input type="text"  readonly="readonly" class="date" name="newsFilter.endDate" value="${newsFilter.endDate}"/>
				</td>
				<td>
					<s:submit  value="查 询" cssClass="mispButton primary"></s:submit>
				</td>
				<td>
					<s:submit  value="重 置" cssClass="mispButton primary"  onclick="resetForm(this.form) "></s:submit>
				</td>				
			</tr>
		</table>

	</div>
	</s:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="CompanyManage!show.action?operateType=create" target="dialog" mask="true" title="新增单位"><span>新增单位</span></a></li>
			<li><a class="delete" href="CompanyManage!deleteList.action" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除公告</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
 				<th width="25%" align="center">单位名称</th>
				<th width="15%" align="center">使用名称</th>
				<th width="15%" align="center">单位地址</th>
				<th width="10%" align="center">单位类型</th>
				<th width="10%" align="center">操作</th>
				
			</tr>
		</thead>
		<s:form  id="newsForm"  method="POST"  name="newsForm" >
		<tbody>

 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.companyID}">
				<td><input name="selectedIDList" value="${e.companyID}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.companyName}</td>
				<td>${e.applyName}</td>
			 
 				<td>${e.companyAddr}</td>
				<td>${e.companyType}</td>
	            <td style="text-align: center;">
	            
	            <a title="楼层管理" target="navTab" href="device/BuildingManage?selectedID=${e.companyID}" rel="configSensor"
	            class="btnAdd"  style="margin-left:10px;"></a>
             
	            </td> 
			</tr>
		</c:forEach>  	


		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${table.page}" scope="request"/>
			
			<select class="combox" onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach var="e" items="${page.pageSizeList}"> 	
			       <c:if test="${e==page.pageSize}">
			         <option value="${e}" selected>${e}</option>
				  </c:if>
                  <c:if test="${e!=page.pageSize}">
			         <option value="${e}">${e}</option>
				  </c:if>
				</c:forEach>
 
			</select>
			<span>条，共${page.count}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

	</div>
</div>

