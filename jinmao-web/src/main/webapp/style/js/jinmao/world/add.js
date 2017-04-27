/**
 * @描述 : 添加
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-8 下午8:51:43
 */
function add(){
	$("#sub").attr("disabled", "disabled");
	$.ajax({
		url : "/world/add.action",
		dataType : 'json',
		type : 'post',
		data : $("#form_id").serialize(),
		success : function(result) {
			$("#sub").attr("disabled", false);
			if (result.message == "200") {
				alert("提交成功!");
				window.location.href = "/world/index.action";
			}else{
				alert("提交失败!");
			}
		},
		error : function() {
			alert("错误!");
		}
	});
}