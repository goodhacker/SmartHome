<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 660,height:360}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
</script>
<div class="pageContent">
	<s:form method="post" action="info/NewsManage!create.action" class="pageForm required-validate" name="newsForm" onsubmit="return iframeCallback(this,dialogAjaxDone);"  >
		<div class="pageFormContent" layoutH="58">
			<dl style="width:50%;">
				<dt style="width:20%;">公告编号：</dt>			
				<dd style="width:70%;"><input type="text" name="news.newsID"  size="30" readonly="readonly" value="${news.newsID}"/></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:20%;">创建人：</dt>			
				<dd style="width:70%;"><input type="text" name="news.author"  size="30" value="${news.author}"/></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:20%;">标题：</dt>			
				<dd style="width:70%;"><input type="text" name="news.title"  size="30" value="${news.title}"/></dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:10%;">公告内容：</dt>			
				<dd style="width:80%;"><textarea name="news.content" cols="87" rows="5" maxlength="200">${news.content}</textarea></dd>
			</dl>						
		</div>		

		<div class="formBar">
			<ul style="margin-right:200px !important;" >
				<li ><div class="buttonActive"><div class="buttonContent"><button type="submit">提 交</button></div></div></li>
				<li style="padding:0px 30px 0px 10px;"><div class="button"><div class="buttonContent"><button type="button" class="close">取 消</button></div></div></li>			
<!--  	<li style="padding:0px 30px 0px 10px;"><s:submit method="create" value="保 存" cssClass="mispButton primary"></s:submit></li>					
-->
			</ul>
		</div>
	</s:form>
</div>
