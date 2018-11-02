<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../Script/Common.js" type="text/javascript"></script>
    <script src="../Script/Data.js" type="text/javascript"></script>
    <script src="../Script/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.validate.js" type="text/javascript"></script>
  <%--  <script type="text/javascript">
       //验证原密码1.ajax,正则
       var ok1=false,ok2=false,ok3=false;
       $(function () {

           $("[name=upass]").blur(function () {
               var upass=$(this).val();
               $.ajax({
                   url:"/user/updatepassword",
                   data:"upass="+upass,
                   type:"post",
                   datatype:"text",
                   success:function (rs) {
                       if ('原密码正确'==rs) {
                           ok1=true;
                       }else {
                           ok1=false;
                       }
                       $("[name=upass]").next().html("<font color='red'>"+rs+"</font>");
                   }
                   });
           });

       //验证新密码
           $("[name=userPs]").blur(function() {
               var newpass=$(this).val();
               var reg=/^\w{6,}$/;
               if(reg.test(newpass)){
                   $(this).next().html("新密码可用");
                   ok2=true;
               }else {
                   $(this).next().html("新密码不可用");
                   ok2=false;
               }
           });


       //验证重复密码格式，是否与新密码相同
           $("[name=newpass2]").blur(function () {
               var newpass2=$(this).val();
               var newpass=$("[name=userPs]").val();
               var reg=/^\w{6,}$/;
               if(reg.test(newpass2)){
                   if (newpass==newpass2){
                       $("[name=newpass2]").next().html("两次密码一致");
                       ok3=true;
                   } else {
                       $("[name=newpass2]").next().html("两次密码不一致");
                       ok3=false;
                   }
               }else {
                   $(this).next().html("重复密码不可用");
                   ok3=false;
               }
           });

           //提交表单
           $("#button2").click(function () {
               if(ok1&&ok2&&ok3){
                    document.forms[0].submit();
               }else{
                   alert("输入有误")
               }
           });

       });


   </script>--%>

    <script type="text/javascript">
        $(function(){
            $("form").validate({
                rules:{
                    upass:{required:true,
                        remote:"/user/updatepassword"},
                    userPs:{required:true,minlength:6},
                    newpass2:{required:true,equalTo:"#userPs"}
                },
                messages:{
                    upass:{
                        required:"请输入原密码",
                        remote:"与原密码不一致"
                    },
                    userPs:{
                        required:"请输入新密码",
                        minlength:"新密码至少6位"
                    },
                    newpass2:{
                        required:"请输入重复密码",
                        equalTo:"两次密码要一致"
                    }
                }
            })
        })


    </script>
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：个人中心-》密码修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<form action="/user/updatepassword2"   method="post">
    <input type="hidden" name="userId" value="${u1.userId}"/>
<div class="cztable">
    <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right" width="80">原密码：</td>
            <td><input type="password" name="upass" /><span></span></td>
        </tr>
        <tr>
            <td align="right" width="90">新密码：</td>
            <td><input type="password" name="userPs" id="userPs"/><span></span></td>
        </tr>
        <tr>
            <td align="right" width="90">密码确认：</td>
            <td><input type="password" name="newpass2" /><span></span></td>
        </tr>
       
        <tr align="center">
            <td colspan="5" height="40">
                <div align="center">
                    
                    <input type="submit" id="button2" value="确认" />
                </div>
            </td>
        </tr>
    </table>
</div>
</form>
        </div>
        </div>
    </div>
</body>
</html>
