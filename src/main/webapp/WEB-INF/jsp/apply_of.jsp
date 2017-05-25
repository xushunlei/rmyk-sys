<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#rs{
	value:"— —请选择— —",
	color:#777
}
</style>
<script type="text/javascript">
$(function(){
	//ride($("#rs"));
	$('#rs').combobox({ 
		value:"— —请选择— —",
	    readonly:true
	});
	$('#seal').combobox({ 
		value:"— —请选择— —",
	    url:'apply/get_rideseal',
	    valueField:'id',
	    textField:'sealname',
	    editable:false
	});
	$("#up_button").linkbutton({
		iconCls:"icon-upload",
		onClick:function(){
			$("#win").window('open');
		}
	})
	$('#sb').switchbutton({ 
      checked: false, 
      onChange: function(checked){ 
        if(checked){
        	$('#rs').combobox({ 
				readonly:false,
				value:"— —请选择— —",
			    url:'apply/get_rideseal',
			    valueField:'id',
			    textField:'sealname',
			    editable:false
			});
		}else{
			$("#rs").combobox({
				readonly:true
			});
        } 
      } 
    })
	$("#win").window({
		width:300,
		height:180,
		modal:true,
		title:"选择上传",
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false
	})
	$("#win").window('close');
	$('#fb').filebox({    
	    buttonText: '选择文件', 
	    buttonAlign: 'left',
	    accept:"image/png,application/pdf,application/msword"
	})
	$("#do_upload").linkbutton({
		onClick:function(){
			$.ajax({
				url:"apply/upload",
				contentType:false,
				success:function(result){
					$("#filename").Text(result)
				}
			})
		}
	})
})
</script>
<div >
	<form action="apply/useSeal" enctype="multipart/form-data" method="post">
	<table>
		<tr>
		<td><span>文件：</span></td>
		<td><a id="up_button">选择上传</a><span id="filename"></span></td>
		</tr>
		<tr>
		<td><span>印章：</span></td>
		<td><input class="easyui-combobox" name="seal" id="seal"></td>
		</tr>
		<tr><td><span>骑缝：</span></td>
		<td><input id="sb" style="width:45px;height:20px"></td>
		</tr>
		<tr><td></td>
		<td><input class="easyui-combobox" name="rideseal" id="rs"></td>
		</tr>
		<tr>
		<td><span>用印数：</span></td>
		<td><input class="easyui-textbox" name="count"></td>
		</tr>
		<tr>
		<td><button >提交</button></td>
		<td><button type="reset">重置</button></td>
		</tr>
	</table>
	</form>
</div>
<div id="win">
	<input id="fb" style="width:260px;">
	<a id="do_upload">上传</a>
</div>