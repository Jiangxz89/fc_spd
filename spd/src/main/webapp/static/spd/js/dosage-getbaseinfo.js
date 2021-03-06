//页面初始化implementDiv
$(function(){
    $("#tdodyID").on("click",'tr',function(){
        var input = $(this).find("input[type=radio]");//获取checkbox
        if(input.attr("checked")){
            input.attr("checked",false);
        }else{
            input.attr("checked",true);
        }
    });

    //checkbox冒泡事件
    $("#tdodyID").on("click",'input[type=radio]',function(e) {
        e.stopPropagation();
    })

	//拼接html
	function appendHtml(json){
        var patientDetailInfoHtml = "";
        if(json.operNo){
            $("#operativeNumberHInput").val(json.operNo);
        }
        if(json.projectName){
            $("#operativeName").val(json.projectName);
        }
        if(json.vaa07){
            $("#operativeNumberInput").val(json.vaa07);
        }
        if(json.type){
            if(json.type=='1'){
                $("#zyType").val('是');
                 // document.getElementsByName('chargeFlag')[0].checked=true;
            }else{
                $("#zyType").val('否');
                // document.getElementsByName('chargeFlag')[0].disabled=true;
                // document.getElementsByName('chargeFlag')[0].checked=false;
            }
        }
        if(json.hitaionNo){
            $("#inHospitalNoHInput").val(json.hitaionNo);
        }
        if(json.patientName){
            $("#patientInfo").val(json.patientName);
            patientDetailInfoHtml+="姓名:"+json.patientName+" ";
        }
        if(json.blngName){
            $("#exeDeptName").val(json.blngName);
        }
        if(json.blngNo){
            $("#exeDeptId").val(json.blngNo);
        }
        if(json.blngDptmName){
            $("#subordinateWardIn").val(json.blngDptmName);
        }
        if(json.blngDptmNo){
            $("#subordinateWardIdIn").val(json.blngDptmNo);
        }
        if(json.doctorName){
            $("#sqrtDoctor").val(json.doctorName);
        }
        if(json.outpatCode){
            $("#outpatientNumber").val(json.outpatCode);
        }
        if(json.departName){
            $("#oprDeptName").val(json.departName);
        }
        if(json.sex){
            patientDetailInfoHtml+="性别:"+json.sex+" ";
        }
        if(json.bedCode){
            patientDetailInfoHtml+="床位号:"+json.bedCode+" ";
        }
        if(json.createDate){
            patientDetailInfoHtml+="登记日期:"+json.createDate+" ";
        }
        $("#patientDetailInfo").text(patientDetailInfoHtml);
	}
    //根据住院号或手术编号查询
    $("#inHospitalNoInputN").keydown(function(event) {
        if (event.keyCode != "13") {
            return;
        }
		var inHospital = $("#inHospitalNoInputN").val();
		var operativeNumber = $("#operativeNumberInput").val();
        findInfo(inHospital,operativeNumber);
    })
    //根据住院号或手术编号查询
    $("#operativeNumberInput").keydown(function(event) {
        if (event.keyCode != "13") {
            return;
        }
        var inHospital = $("#inHospitalNoInputN").val();
        var operativeNumber = $("#operativeNumberInput").val();
        findInfo(inHospital,operativeNumber);
    })

	function findInfo(inHospital,operativeNumber){
        $.ajax({
            url:'getBaseInfoByInHospital',
            type:'post',
            data:{'inHospital':inHospital,'operativeNumber':operativeNumber},
            dataType:'json',
            success:function(data){
                if (data && '0' ==data.statusCode ) {
                    var jsonData = data.data;
                    if(jsonData){
                        if(jsonData.length==1){
                            var json =jsonData[0];
                            appendHtml(json);
                        }else{
                            var html="";
                            var jsonStr={};
                            for(var i = "0";i<jsonData.length;i++){
                                var json =jsonData[i];
                                html+="<tr  style='cursor:pointer'>";
                                html+="<td><input type='radio' name='subBox'  data-op-number='"+json.vaa07+"'/></td>";
                                html+="<td>"+json.patientName+"</td>";
                                html+="<td>"+json.hitaionNo+"</td>";
                                html+="<td>"+json.blngDptmName+"</td>";
                                html+="<td>"+json.blngName+"</td>";
                                html+="<td>"+json.departName+"</td>";
                                html+="<td>"+json.bedCode+"</td>";
                                html+="<td>"+json.outpatCode+"</td>";
                                html+="<td>"+json.doctorName+"</td>";
                                html+="<td>"+json.createDate+"</td>";
                                html+="</tr>";
                                jsonStr[json.vaa07] = i;
                            }
                            $("#tdodyID").empty().append(html);
                            layer.open({
                                type:1,
                                title:"选择哪次住院",
                                content:$(".addHighBox"),
                                area:["900px","300px"],
                                shade: [0.8, '#393D49'],
                                btn:["确定","取消"],
                                yes:function(index,layero) {
                                    var operativeNumber =null;
                                    $("input[name=subBox]:checked").each(function() {
                                        operativeNumber = $(this).attr("data-op-number"); //订单号
                                    })
                                    if(operativeNumber){
                                        var index = jsonStr[operativeNumber];
                                        var json =jsonData[index];
                                        appendHtml(json);
                                        layer.closeAll();
                                    }else{
                                        layer.alert('请选择一条记录', {icon:0});
                                    }
                                }
                            })
                        }
                    }else{
                        layer.alert('根据该住院号没有查询到内容', {icon:0});
                    }
                }else{
                    layer.alert(data.msg, {icon:0});
                }
            }
        });
	}

	//如果有his的接口则调用接口，如果没有则显示输入框
	if($("#displayFlag").val()=="1"){
        $("#chargingSelect").show();
        $("#chargingInput").hide();
        $("#chargingInput").find('input').attr("disabled",true);
        $("#chargingSelect").find('input').attr("disabled",false);
        $("#chargingInput").find('textarea').attr("disabled",true);
        $("#chargingSelect").find('textarea').attr("disabled",false);
        $("#implementDiv").show();
       // getBaseInfo('1', '', '');
        //getBaseInfo('2', '', '');
        //getBaseInfo('3', '', '');
		// initHtml("1","")// TODO 测试代码
		// initHtml("2","")// TODO 测试代码
		// initHtml("3","")// TODO 测试代码
    }else{
        $("#chargingSelect").hide();
        $("#chargingSelect").find('input').attr("disabled",true);
        $("#chargingInput").find('input').attr("disabled",false);
        $("#chargingSelect").find('textarea').attr("disabled",true);
        $("#chargingInput").find('textarea').attr("disabled",false);
        $("#chargingInput").show();
        $("#implementDiv").hide();
    }

	/*getBaseInfo('1', '1000', '');
	getBaseInfo('2', '10', '');
	getBaseInfo('3', '90', '');*/
	
	//保存下拉名称
	$(document).on('change','#exeDeptId,#oprDeptId,#surgeonId,#sqrtDoctorId',function(){
		var myvalue = $(this).find("option:selected").text();
		$(this).next().val(myvalue);
	});
	//保存住院号
	$(document).on('change','#patientInfo',function(){
		var myvalue = $(this).find("option:selected");
		$('#inHospitalNo').val(myvalue.data('inhospno'));
		$('#patientDetailInfo').val(myvalue.data('info'));
	});
});

//获取数据-
function getBaseInfo(type, code, remark) {
	if (type)
		$.ajax({
			url:'getBaseInfo',
			type:'post',
			data:{'queryType':type, 'queryCode':code, 'remark':remark},
			dataType:'json',
			success:function(data){
				initHtml(type, data);
			}
		});
}

//初始化
function initHtml(type, data) {
	if (type === '1') {//病人信息
		initPatientHtml(data);
	} else if (type === '2') {//医生信息
		initDoctorHtml(data);
	} else if (type === '3') {//科室信息
		initDeptHtml(data);
	}
}

//科室组装html
function initDeptHtml(data) {
	var str = "{    \"code\": \"0\",    \"data\": [        {          " +
		"  \"cxxx\": \"外一科\",            \"dydm\": \"1001\"   " +
		"     },        {            \"cxxx\": \"外二科\",        " +
		"    \"dydm\": \"1002\"        }    ],    \"msg\": \"\"}";// TODO 测试代码
	data = eval('(' + str + ')');// TODO 测试代码
	var deptHtml = '<option value="">请选择</option>';
	if (data && data.code == '0') {
		var len = data.data.length;
		for (var i = 0; i < len; i++) {
			//执行科室、手术科室
			var obj = data.data[i];
			deptHtml += '<option value="'+obj.dydm+'">'+obj.cxxx+'</option>';
		}
	}
	$('#exeDeptId, #oprDeptId').empty().append(deptHtml);
}

//医生组装html
function initDoctorHtml(data) {
	var str= "{    \"code\": \"0\",    \"data\": [        {      " +
		"      \"cxxx\": \"张医生\",            \"dydm\": \"100101\"   " +
		"     },        {            \"cxxx\": \"李医生\",         " +
		"   \"dydm\": \"100102\"        }    ],    \"msg\": \"\"}";// TODO 测试代码
	data = eval('(' + str + ')');// TODO 测试代码
	var	doctorHtml = '<option value="">请选择</option>';
	if (data && data.code == '0') {
		var len = data.data.length;
		for (var i = 0; i < len; i++) {
			//手术医生、开方医生
			var obj = data.data[i];
			doctorHtml += '<option value="'+obj.dydm+'">'+obj.cxxx+'</option>';
		}
	}
	$('#surgeonId, #sqrtDoctorId').empty().append(doctorHtml);
}

//病人组装html
function initPatientHtml(data) {
	var str="{    \"code\": \"0\",    \"data\": [        {       " +
		"     \"cxxx\": \"姓名：张三；性别：男；年龄：30；住院号：0000001；\t\t\t手机号码：13000000000；\",       " +
		"     \"dydm\": \"0000001\"        },        {       " +
		"     \"cxxx\": \"姓名：李四；性别：男；年龄：30；住院号：0000002；手机号码：13000000001；\",   " +
		"         \"dydm\": \"0000002\"        }    ],    \"msg\": \"\"}";// TODO 测试代码
	data = eval('(' + str + ')');// TODO 测试代码
	var	patientHtml = '<option value="">请选择</option>';
	if (data && data.code == '0') {
		var len = data.data.length;
		for (var i = 0; i < len; i++) {
			//病人信息
			var obj = data.data[i].cxxx;
			var nameIndex = obj.indexOf('姓名'),
				sexsIndex = obj.indexOf('性别'),
				inNoIndex = obj.indexOf('住院号'),
				telpIndex = obj.indexOf('手机号码'),
				inHospitalNo = $.trim(obj.substring(inNoIndex + 4, telpIndex)),//获取住院号
				patientInfo = $.trim(obj.substring(nameIndex + 3, sexsIndex));//获取病人姓名
			inHospitalNo = inHospitalNo.substr(0, inHospitalNo.length - 1);
			patientInfo = patientInfo.substr(0,patientInfo.length-1) + "  "+inHospitalNo;
			patientHtml += '<option value="'+patientInfo+'"'
						+  'data-info="'+obj+'" data-inhospno="'+inHospitalNo+'">'+patientInfo+'</option>';
		}
	}
	$('#patientInfo').empty().append(patientHtml);
}