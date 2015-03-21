<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	$(function() {
		$.pdialog.resizeDialog({
			style : {
				width : 660,
				height : 360
			}
		}, $.pdialog.getCurrent(), "");
		});//dialog 宽度重新定义
 
    $(function () 
    {
    	//图片预览js
        $("#up1").uploadPreview({ Img: "ImgPr1",ImgType:["jpg","bmp","png"] });        
     });
</script>
<div class="pageContent">
	<s:form method="post" action="device/PlanManage" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data"  >
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="obj.buildingID" value="${obj.buildingID}"/>
			<dl style="width:50%;">
				<dt style="width:20%;">名称：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.name"  size="25" value="${obj.name}" /></dd>		 
			</dl>
			<dl style="width:50%;">
				<dt style="width:20%;">描述：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.desp"  size="25" value="${obj.desp}"/></dd>		 
			</dl>
			 <dl style="width:50%;">
				<dt style="width:20%;">楼层：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.floor"  size="25" value="${obj.desp}"/></dd>		 
			</dl>
 
			<dl style="width:100%;">
				<dt style="width:15%;">平面图：</dt>			
				<dd style="width:65%;">
							<div  >
						<div
							style="margin-bottom:5px; width:120px; height:140px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
							<div>
								<img id="ImgPr1" src="<%=request.getContextPath()%>/${obj.picPath}" height="140" width="120" alt="个人照片">
							</div>

						</div>
						<div>
		 
							<s:file name="upload" id="up1"  ></s:file>
						</div>
						<span class="info">上传图片大小不得超过65KB,只支持bmp与jpg格式</span>
					</div> 
				</dd>		 
			</dl>

				
		</div>		

		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" ><s:submit method="create" value="确  认" cssClass="mispButton primary"></s:submit></li>

				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
