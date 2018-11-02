<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
    
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：权限管理-》角色管理-》详情</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="javascript:history.back();">【返回】</a>
                </span>
            </span>
        </div>
</div>
<div class="cztable">
	<form action="/power/role/update" method="post">
<table border="1" width="100%" class="table_a">
    <input type="hidden" name="roleid" value="${role.roleid}">
                <tr  width="120px;">
                    <td width="120px">角色名：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="rolename" value="${role.rolename}" />
					</td>
                </tr>
                <script type="text/javascript">
                    function checksecond(mid) {
                        var cl = "a"+mid
                        var classes =$("[class="+cl+"]");
                        var menu = $("#m"+mid)[0];
                        for (var i = 0; i <classes.length; i++) {
                            classes[i].checked=menu.checked;
                        }

                    }
                    function gettop(mid) {
                        var cl = "a"+mid;
                        var classes =$("[class="+cl+"]");
                        var k = -1;
                        for (var i = 0; i < classes.length; i++) {
                            if (classes[i].checked==true){
                                document.getElementById("m"+mid).checked=true;
                                var k=2;
                                break;
                            }
                        }
                        if (k==-1){
                            document.getElementById("m"+mid).checked=false;
                        }

                    }
                </script>
                <tr  width="120px;">
                    <td>菜单资源<span style="color:red">*</span>：</td>
                    <td>
						<ul>
                            <c:forEach items="${menu}" var="menu1">

                                <li><input type="checkbox" name="menu" value="${menu1.menuid}" onclick="checksecond(${menu1.menuid})" id="m${menu1.menuid}"
                                        <c:forEach items="${role.menus}" var="menu">
                                            <c:if test="${menu.menuid==menu1.menuid}">checked</c:if>
                                        </c:forEach> />${menu1.menuname}
                            	<ul>
                                    <c:forEach var="menu2" items="${menu1.seconds}">
                                        <li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menu" value="${menu2.menuid}" onclick="gettop(${menu1.menuid})" class="a${menu1.menuid}"
                                                  <c:forEach items="${role.menus}" var="menu">
                                                    <c:if test="${menu.menuid==menu2.menuid}">checked</c:if>
                                                  </c:forEach> />${menu2.menuname}</li>
                                    </c:forEach>
                                </ul>
                            </li>
                            </c:forEach>
                        </ul>
					</td>
                </tr>
                
                <tr>
                    <td>启用状态<span style="color:red">*</span>：</td>
                    <td>

                        <input type="radio" name="rolestate"  value="1"  <c:if test="${role.rolestate==1}">checked</c:if> />启用
                        <input type="radio" name="rolestate" value="2" <c:if test="${role.rolestate==2}">checked</c:if> />禁用
                    </td>
                </tr>
                <tr width="120px">
                    <td colspan="2" align="center">
                         <input type="submit" value="修改" />
                    </td>
                </tr>
				
			</table>
	</form>
</div>

            </div>
        </div>
        
    </div>
</body>
</html>
