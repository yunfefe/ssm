<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fy" uri="http://java.sun.com/jsp/fenye/fy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	function del(rid){
		var k =confirm("确认删除？");
		if (k){
		    location.href="/power/role/deleterole?rid="+rid;
        }
	}

</script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》角色管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
			<a style="text-decoration: none;" href="/power/role/getmenulist">【新增角色】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>





 <div class="cztable">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px" align="center">
                	<th><input type="checkbox"/></th>
                    <th scope="col">
                        序号
                    </th>
                    
                    <th scope="col">
                        角色名称
                    </th>
                    <th scope="col">
                        状态
                    </th>
                    <th scope="col" width="300px">
                        操作
                    </th>
                </tr>
                
               
                <c:forEach items="${pi.list}" var="r" varStatus="sta">
                <tr align="center">
                    <th><input type="checkbox"/></th>
                    <td>
                        ${sta.count}
                    </td>
                    <td>
                       ${r.rolename}
                    </td>                    
                    <td>&nbsp;
                        ${r.rolestate==1?"启用":"禁用"}
                    </td>
                    
                    <td>&nbsp;
                    	<a href="javascript:alert('操作成功！');"> ${r.rolestate==1?"禁用":"启用"}</a>
                        <a href="/power/role/getall?rid=${r.roleid}">修改</a>
                        <c:if test="${r.roleid!=u1.role.roleid}">
						<a href="javascript:void(0)" onclick="del(${r.roleid});return false" class="tablelink"> 删除</a>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
                
            </tbody>
        </table>

          <div class='MainStyle'>
              <fy:fy url="getrolelist" pageInfo="${pi}"></fy:fy>

            </div>
</body>
</html>