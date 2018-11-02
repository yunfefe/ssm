<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fy" uri="http://java.sun.com/jsp/fenye/fy" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>
    <script>
	function del(mid){
	var k=confirm("确认删除？");
	if (k){
	    location.href="/power/menu/deletemenu?menuid="+mid;
    }
	}

</script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》菜单管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
            <span onclick="todaochu()" style="color: blue;cursor: pointer;">【导出excel】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="javascript:alert('操作成功！');">【批量删除】</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="/power/menu/getnewmenu">【新增菜单】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>
<script type="text/javascript">
    $(function() {
         $("[name=all]").click(function() {
             var singles=$("[name=single]");
            for(var i = 0; i <singles.length ; i++) {
                singles[i].checked=$(this)[0].checked;
            }
        })
    })


    </script>
<script type="text/javascript">
    function todaochu() {
    var singles=$("[name=single]");
    var k =-1;
    for (var i = 0; i <singles.length ; i++) {
    if (singles[i].checked== true) {
    k = -2;
    document.forms[0].submit();
    break;
    }
    }
    if (k==-1){
    alert("请选择需导出的数据")
    }
    }
    </script>





<form action="/power/menu/todaochu2" method="post">
 <div class="cztable">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px" align="center">
                	<th><input type="checkbox" name="all"/></th>
                    <th scope="col">
                        序号
                    </th>
                    
                    <th scope="col">
                        菜单名称
                    </th>
                    <th scope="col">
                        URL
                    </th>
                    <th scope="col">
                        状态
                    </th>
                    <th scope="col">
                        操作
                    </th>
                </tr>
                
               
                <c:forEach items="${pi.list}" var="m" varStatus="sta">
                <tr align="center">
                    <th><input type="checkbox" name="single" value="${m.menuid} "/></th>
                    <td>
                        ${sta.count}
                    </td>
                    <td>
                      ${m.menuname}
                    </td>
                    <td>
                       <a href="">${m.menupath}</a>
                    </td>

                    <td>&nbsp;
                    ${m.menustate==1?"启用":"禁用"}
                    </td>

                    <td>&nbsp;
                        <a href="info.html">详情</a>						   
                        <a href="/power/menu/updatemenu?upmenuid=${m.upmenuid}&menuid=${m.menuid}">修改</a>
						<a href="javascript:void(0)" onclick="del(${m.menuid});return false" class="tablelink"> 删除</a>
                    </td>
                </tr>
                </c:forEach>
                
               
                
                
            </tbody>
        </table>
        
          <div class='MainStyle'>
    <fy:fy url="/power/menu/getmenu" pageInfo="${pi}"></fy:fy>
    </div>

    </div>
    </form>
</body>
</html>