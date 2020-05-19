$(function(){
	var tit=$("#container_nav h3");
	var lis=$("#swipe_list li");
	$(tit).each(function(){
		var ind=$(this).index();
		$(this).on("click",function(){
			lis.eq(ind).css({
				"-webkit-transform":"translateX(-"+(ind*100)+"%)",
				"-webkit-transition":"all 0.4s ease-in-out"
			})
			$("#container_nav").css("display","none");
			$("#back_btn").addClass("next_page").show();
		});
	});
	$("#back_btn").on("click",function(){
		$(".container").scrollTop(0);
		$(this).hide()
		if($(this).hasClass("next_page")){
			lis.css({
				"-webkit-transform":"translateX(100%)",
				"-webkit-transition":"all 0.4s ease-in-out"
			})
			setTimeout(function(){
				$("#container_nav").css("display","block");
			},500)
			$(this).removeClass("next_page");
		}else{
			window.location.href="aaa.html";
		}
	});
	
	/*if(id != undefined && id != null && id != "") {
		$.ajax({
			url : "get",
			method : "POST",
			async : false,
			dataType : "json",
			data : {
				"id" : id
			},
			success : function(o) {
				if(o.success == false || o.data == "error" || o.data == "") {
					alert("数据不存在,请联系管理员!!!");
					return ;
				}
				var obj = o.data;
				assignBaseInfoData(obj);
				assignSymptomData(obj);
				assignGeneralData(obj);
				assignLifeStyleData(obj);
				assignOrganData(obj);
				assignExaminationData(obj);
				assignAidData(obj);
				assignChineseMedicineData(obj);
				assignNowHealthProblems(obj);
				assignHospitalTreatmentData(obj);
				assignMainDrugData(obj);
				assignNonEpiVaccinationHistory(obj);
				assignHealthAssessment(obj);
				assignHealthGuidance(obj);
				$(".save_base").click();
			}
		});
	}*/
})

//1、基本信息
function assignBaseInfoData(obj) {
	$("p[name='code']").text(deal(obj.code));
	$("p[name='patient_id']").text(deal(obj.patient_id));
	$("p[name='patientName']").text("姓名："+deal(obj.patientName)+"");
	$("p[name='duty_doctor']").text("责任医生："+deal(obj.duty_doctor)+"");
	$("p[name='check_date']").text("体检日期："+deal(obj.check_date)+"");
}

//2、症状
function assignSymptomData(obj) {
	obj.empty_symptom == 1 ? $("p[name='empty_symptom']").css("display",'block') : $("p[name='empty_symptom']").css("display",'none');
	if(obj.empty_symptom !=1){
		$(".check_box_symptom").css("display","block");
		obj.headache == 1 ? $("input[name='headache']").attr("checked",'true') : "";
		obj.dizzy == 1 ? $("input[name='dizzy']").attr("checked",'true') : "";
		obj.heart_throb == 1 ? $("input[name='heart_throb']").attr("checked",'true') : "";
		obj.heart_thirst == 1 ? $("input[name='heart_thirst']").attr("checked",'true') : "";
		obj.heartache == 1 ? $("input[name='heartache']").attr("checked",'true') : "";
		obj.cough == 1 ? $("input[name='cough']").attr("checked",'true') : "";
		obj.expectoration == 1 ? $("input[name='expectoration']").attr("checked",'true') : "";
		obj.breath_hard == 1 ? $("input[name='breath_hard']").attr("checked",'true') : "";
		obj.drink_more == 1 ? $("input[name='drink_more']").attr("checked",'true') : "";
		obj.urine_more == 1 ? $("input[name='urine_more']").attr("checked",'true') : "";
		obj.weight_drop == 1 ? $("input[name='weight_drop']").attr("checked",'true') : "";
		obj.weak == 1 ? $("input[name='weak']").attr("checked",'true') : "";
		obj.joint_pain == 1 ? $("input[name='joint_pain']").attr("checked",'true') : "";
		obj.see_vision == 1 ? $("input[name='see_vision']").attr("checked",'true') : "";
		obj.hand_foot_pain == 1 ? $("input[name='hand_foot_pain']").attr("checked",'true') : "";
		obj.urine_quick == 1 ? $("input[name='urine_quick']").attr("checked",'true') : "";
		obj.urine_pain == 1 ? $("input[name='urine_pain']").attr("checked",'true') : "";
		obj.constipation == 1 ? $("input[name='constipation']").attr("checked",'true') : "";
		obj.laxness == 1 ? $("input[name='laxness']").attr("checked",'true') : "";
		obj.nausea == 1 ? $("input[name='nausea']").attr("checked",'true') : "";
		obj.giddiness == 1 ? $("input[name='giddiness']").attr("checked",'true') : "";
		obj.tingling == 1 ? $("input[name='tingling']").attr("checked",'true') : "";
		obj.breast_pain == 1 ? $("input[name='breast_pain']").attr("checked",'true') : "";
		$("input[name='symptom_other']").val(deal(obj.symptom_other));
	}
}
//3、一般情况
function assignGeneralData(obj) {
	$("p[name='temperature']").text("体温："+deal(obj.temperature)+"℃");
	$("p[name='heart_rate']").text("脉率："+deal(obj.heart_rate)+"次/分钟");
	$("p[name='breathing_rate']").text("呼吸频率："+deal(obj.breathing_rate)+"次/分钟");
	$("span[name='blood_pressure_left']").text(deal(obj.blood_pressure_left) +"mmHg");
	$("span[name='blood_pressure_right']").text(deal(obj.blood_pressure_right) +"mmHg");
	$("p[name='height']").text("身高："+deal(obj.height)+"cm");
	$("p[name='weight']").text("体重："+deal(obj.weight)+"kg");
	$("p[name='bmi']").text("体质指数（BMI）："+deal(obj.bmi)+"kg/m2");
	$("p[name='kummerbund']").text("腰围："+deal(obj.kummerbund)+"");
	$("p[name='self_health_status']").text("老年人健康状态自我评估："+deal(obj.self_health_status_name)+"");
	$("p[name='self_life_status']").text("老年人生活自理能力自我评估："+deal(obj.self_life_status_name)+"");
	$("p[name='self_konw_ability']").text("老年人认知功能："+deal(obj.self_konw_ability_name)+"");
	$("p[name='emotion_status']").text("老年人情感状态 :"+deal(obj.emotion_status_name)+"");
	$("p[name='simple_brains_score']").text("简易智力状态检查 总分:"+deal(obj.simple_brains_score)+"分");
	$("p[name='epressed_score']").text("老年人抑郁评分检查 总分:"+deal(obj.epressed_score)+"分");
}
//4、生活方式
function assignLifeStyleData(obj) {
	$("p[name='sport']").text("锻炼频率："+deal(obj.sport_name)+"");
	$("p[name='sport_each_time']").text("每次锻炼时间："+deal(obj.sport_each_time)+"分钟");
	$("p[name='sport_continue_time']").text("坚持锻炼时间:" + deal(obj.sport_continue_time)+"年");
	$("p[name='sport_way']").text("坚持锻炼方式："+deal(obj.sport_way)+"");
	obj.eating_habits_mavb == 1 ? $("input[name='eating_habits_mavb']").attr("checked",'true') : "";
	obj.eating_habits_mofb == 1 ? $("input[name='eating_habits_mofb']").attr("checked",'true') : "";
	obj.eating_habits_v == 1 ? $("input[name='eating_habits_v']").attr("checked",'true') : "";
	obj.eating_habits_h == 1 ? $("input[name='eating_habits_h']").attr("checked",'true') : "";
	obj.eating_habits_o == 1 ? $("input[name='eating_habits_o']").attr("checked",'true') : "";
	obj.eating_habits_s == 1 ? $("input[name='eating_habits_s']").attr("checked",'true') : "";
	$("p[name='smoking_status']").text("吸烟情况："+deal(obj.smoking_status_name)+"");
	$("p[name='smoking_day']").text("日吸烟量："+deal(obj.smoking_day)+"支");
	$("p[name='smoking_start_age']").text("开始吸烟年龄："+deal(obj.smoking_start_age)+"岁");
	$("p[name='stop_somking_age']").text("戒烟年龄："+deal(obj.stop_somking_age)+"岁");
	$("p[name='drinking_frequency']").text("饮酒频率："+deal(obj.drinking_frequency)+"");
	$("p[name='drink_day']").text("日饮酒量："+deal(obj.drink_day)+"两");
	$("p[name='is_stop_drinking']").text("是否戒酒："+deal(obj.is_stop_drinking_name)+"");
	$("p[name='stop_drinking_age']").text("戒酒年龄："+deal(obj.stop_drinking_age)+"岁");
	$("p[name='start_drinking_age']").text("开始饮酒年龄："+deal(obj.start_drinking_age)+"岁");
	$("p[name='is_drunk_year']").text("近一年内是否酗酒："+deal(obj.is_drunk_year_name)+"岁");
	obj.drinking_type_spirit == 1 ? $("input[name='drinking_type_spirit']").attr("checked",'true') : "";
	obj.drinking_type_beer == 1 ? $("input[name='drinking_type_beer']").attr("checked",'true') : "";
	obj.drinking_type_red == 1 ? $("input[name='drinking_type_red']").attr("checked",'true') : "";
	obj.drinking_type_yellow == 1 ? $("input[name='drinking_type_yellow']").attr("checked",'true') : "";
	obj.drinking_type_other == 1 ? $("input[name='drinking_type_other']").attr("checked",'true') : "";
	$("input[name='drinking_type_other_text']").val(deal(obj.drinking_type_other_text));
	$("p[name='pro_disease_history']").text("职业病危害因素接触史："+deal(obj.pro_disease_history_name)+"");
	$("p[name='work_type']").text("工种："+deal(obj.work_type)+"");
	$("p[name='work_time']").text("从业时间："+deal(obj.work_time)+"年");
	$("p[name='poision_type']").text("毒物种类"+deal(obj.poision_type_name)+"");
	$("[name='protect_way']").text("防护措施："+deal(obj.protect_way_name)+"");
	$("input[name='protect_way_text']").val(deal(obj.protect_way_text));
}
//5、脏器功能
function assignOrganData(obj) {
	$("p[name='mouth']").text("口唇："+deal(obj.mouth_name)+"");
	$("p[name='pharyngeal']").text("咽部："+deal(obj.pharyngeal_name)+"");
	obj.teech_type == 1 ? $("input[name='teech_type']").attr("checked",'true') : "";
	obj.miss_teech == 1 ? $("input[name='miss_teech']").attr("checked",'true') : "";
	obj.miss_teech_all == 1 ? $("input[name='miss_teech_all']").attr("checked",'true') : "";
	$("input[name='miss_teech_lefttop']").val(deal(obj.caries_lefttop));
	$("input[name='miss_teech_righttop']").val(deal(obj.caries_righttop));
	$("input[name='miss_teech_leftbottom']").val(deal(obj.caries_leftbottom));
	$("input[name='miss_teech_rightbottom']").val(deal(obj.caries_rightbottom));
	obj.caries == 1 ? $("input[name='caries']").attr("checked",'true') : "";
	$("input[name='caries_lefttop']").val(deal(obj.caries_lefttop));
	$("input[name='caries_righttop']").val(deal(obj.caries_righttop));
	$("input[name='caries_leftbottom']").val(deal(obj.caries_leftbottom));
	$("input[name='caries_rightbottom']").val(deal(obj.caries_rightbottom));
	obj.denture == 1 ? $("input[name='denture']").attr("checked",'true') : "";
	obj.denture_all == 1 ? $("input[name='denture_all']").attr("checked",'true') : "";
	$("input[name='denture_lefttop']").val(deal(obj.denture_lefttop));
	$("input[name='denture_righttop']").val(deal(obj.denture_righttop));
	$("input[name='denture_leftbottom']").val(deal(obj.denture_leftbottom));
	$("input[name='denture_rightbottom']").val(deal(obj.denture_rightbottom));

	$("input[name='eye_left']").val(deal(obj.eye_left));
	$("input[name='eye_right']").val(deal(obj.eye_right));
	$("input[name='eye_correct_left']").val(deal(obj.eye_correct_left));
	$("input[name='eye_correct_right']").val(deal(obj.eye_correct_right));
	$("p[name='hearing']").text("听力："+deal(obj.hearing_name)+"");
	$("p[name='sport_ability']").text("运动功能："+deal(obj.sport_ability_name)+"");
}
//6、查体
function assignExaminationData(obj) {
	$("input[name='eye_ground'][value='" + obj.eye_ground + "']").attr("checked",'checked');
	$("input[name='eye_ground_exception']").val(deal(obj.eye_ground_exception));
	$("p[name='skin']").text("皮肤："+deal(obj.skin_name)+"   " + deal(obj.skin_other));
	$("p[name='sclera']").text("巩膜："+deal(obj.sclera_name)+"   " + deal(obj.sclera_other));
	$("p[name='lymph_node']").text("淋巴结："+deal(obj.lymph_node_name)+"  " + deal(obj.lymph_node_other));
	$("input[name='barrel_chest'][value='" + obj.barrel_chest + "']").attr("checked",'checked');
	$("input[name='breath_sounds'][value='" + obj.breath_sounds + "']").attr("checked",'checked');
	$("input[name='breath_sounds_exception']").val(deal(obj.breath_sounds_exception));
	$("input[name='rales'][value='" + obj.rales + "']").attr("checked",'checked');
	$("input[name='rales_other']").val(deal(obj.rales_other));
	$("p[name='heart_rate1']").text("心率："+deal(obj.heart_rate1)+"次/分钟");
	$("p[name='rhythm']").text("心律："+deal(obj.rhythm_name)+"");
	$("select[name='murmur']").val(deal(obj.murmur));
	$("input[name='murmur_other']").val(deal(obj.murmur_other));
	$("select[name='tenderness']").val(deal(obj.tenderness));
	$("input[name='tenderness_other']").val(deal(obj.tenderness_other));
	$("select[name='mass']").val(deal(obj.mass));
	$("input[name='mass_other']").val(deal(obj.mass_other));
	$("select[name='hepatomegaly']").val(deal(obj.hepatomegaly));
	$("input[name='hepatomegaly_other']").val(deal(obj.hepatomegaly_other));
	$("select[name='splenomegaly']").val(deal(obj.splenomegaly));
	$("input[name='splenomegaly_other']").val(deal(obj.splenomegaly_other));
	$("select[name='shifting_dullness']").val(deal(obj.shifting_dullness));
	$("input[name='shifting_dullness_other']").val(deal(obj.shifting_dullness_other));
	$("select[name='lower_extremity_edema']").val(deal(obj.lower_extremity_edema));
	$("select[name='dorsalis_pedis_pulse']").val(deal(obj.dorsalis_pedis_pulse));
	$("select[name='dre']").val(deal(obj.dre));
	$("input[name='dre_other']").val(deal(obj.dre_other));
	$("select[name='breast']").val(deal(obj.breast));
	$("input[name='breast_other']").val(deal(obj.breast_other));
	$("select[name='vulva']").val(deal(obj.vulva));
	$("input[name='vulva_exception']").val(deal(obj.vulva_exception));
	$("select[name='vaginal']").val(deal(obj.vaginal));
	$("input[name='vaginal_exception']").val(deal(obj.vaginal_exception));
	$("select[name='cervix']").val(deal(obj.cervix));
	$("input[name='cervix_exception']").val(deal(obj.cervix_exception));
	$("select[name='palace1']").val(deal(obj.palace1));
	$("input[name='palace1_exception']").val(deal(obj.palace1_exception));
	$("select[name='annex']").val(deal(obj.annex));
	$("input[name='annex_exception']").val(deal(obj.annex_exception));
	$("p[name='examination_other']").text("其他: " + deal(obj.examination_other));
}
//7、辅助检查
function assignAidData(obj){
	$("p[name='hemoglobin']").text("血红蛋白："+deal(obj.hemoglobin)+"g/L");
	$("p[name='leukocyte']").text("白细胞："+deal(obj.leukocyte)+"×109/L");
	$("p[name='blood_platelet']").text("血小板："+deal(obj.blood_platelet)+"×109/L");
	$("p[name='blood_other']").text("其他："+deal(obj.blood_other)+"");
	$("p[name='urinary_protein']").text("尿蛋白："+deal(obj.urinary_protein)+"");
	$("p[name='urine']").text("尿糖："+deal(obj.urine)+"");
	$("p[name='ketone']").text("尿酮体："+deal(obj.ketone)+"");
	$("p[name='urine_occult_blood']").text("尿潜血："+deal(obj.urine_occult_blood)+"");
	$("p[name='urine_other']").text("其他："+deal(obj.urine_other)+"");
	$("p[name='fasting_plasma_glucose']").text("空腹血糖："+deal(obj.fasting_plasma_glucose)+"mmol/L");
	$("select[name='electrocardiogram']").val(deal(obj.electrocardiogram));
	$("input[name='electrocardiogram_exception']").val(deal(obj.electrocardiogram_exception));
	$("p[name='microalbuminuria']").text("尿微量白蛋白: " + deal(obj.microalbuminuria) + "mg/dL");
	$("select[name='fecal_occult_blood']").val(deal(obj.fecal_occult_blood_name));
	$("p[name='glycated_hemoglobin']").text("糖化血红蛋白：" + deal(obj.glycated_hemoglobin)+"%");
	$("input[name='glycated_hemoglobin']").val(deal(obj.glycated_hemoglobin));
	$("select[name='hepatitis_b_surface_antigen']").val(deal(obj.hepatitis_b_surface_antigen_name));
	$("p[name='serum_alanine_aminotransferase']").text("血清谷丙转氨酶：" + deal(obj.serum_alanine_aminotransferase) + "U/L");
	$("p[name='serum_aspartate_aminotransferase']").text("血清谷草转氨酶：" + deal(obj.serum_aspartate_aminotransferase) + "U/L");
	$("p[name='albumin']").text("白蛋白: " + deal(obj.albumin) + "g/L");
	$("p[name='total_bilirubin']").text("总胆红素:" + deal(obj.total_bilirubin) + "μmol/L");
	$("p[name='bilirubin']").text("结合胆红素:" + deal(obj.bilirubin) + "μmol/L");
	$("p[name='serum_creatinine']").text("血清肌酐:" + deal(obj.serum_creatinine) + "μmol/L");
	$("p[name='blood_urea_nitrogen']").text("血尿素氮:"+deal(obj.blood_urea_nitrogen) + "mmol/L");
	$("p[name='serum_potassium_concentration']").text("血钾浓度:"+deal(obj.serum_potassium_concentration)+"mmol/L");
	$("p[name='sodium_concentration']").text("血钠浓度:"+deal(obj.sodium_concentration)+"mmol/L");
	$("p[name='total_cholesterol']").text("总胆固醇:"+deal(obj.total_cholesterol)+"mmol/L");
	$("p[name='triglycerides']").text("甘油三酯:"+deal(obj.triglycerides)+"mmol/L");
	$("p[name='ldl_cholesterol']").text("血清低密度脂蛋白胆固醇:"+deal(obj.ldl_cholesterol)+"mmol/L");
	$("p[name='high_density_lipoprotein_cholesterol']").text("血清高密度脂蛋白胆固醇:"+deal(obj.high_density_lipoprotein_cholesterol)+"mmol/L");
	$("p[name='chest_x_ray']").text("胸部X线片：" + deal(obj.chest_x_ray_name)+" " + deal(obj.chest_x_ray_exception));
	$("p[name='abdominal_b']").text("B超:" + deal(obj.abdominal_b_name)+" " + deal(obj.abdominal_b_exception));
	$("p[name='pap_smears']").text("宫颈图片：" + deal(obj.pap_smears_name)+" " + deal(obj.pap_smears_exception));
	$("p[name='auxiliary_examination_other']").text("其他:" + deal(obj.auxiliary_examination_other));
}

// 8 中医体质辨识数据处理
function assignChineseMedicineData(obj) {
	$("select[name='gentleness']").val(obj.gentleness);
	$("select[name='qi_deficiency']").val(obj.qi_deficiency);
	$("select[name='yang_and_quality']").val(obj.yang_and_quality);
	$("select[name='yin_deficiency']").val(obj.yin_deficiency);
	$("select[name='phlegm']").val(obj.phlegm);
	$("select[name='quality_heat']").val(obj.quality_heat);
	$("select[name='blood_stasis']").val(obj.blood_stasis);
	$("select[name='qi_quality']").val(obj.qi_quality);
	$("select[name='special_intrinsic_quality']").val(obj.special_intrinsic_quality);
}

// 9 住院治疗情况数据处理
function assignHospitalTreatmentData(obj) {
	var history_of_the_hospital = [];
	try {
		history_of_the_hospital = eval("("+obj.history_of_the_hospital +")");
	} catch(e) {}
	try {
		var obj1 = history_of_the_hospital[0];
		$("span[name='h_admission_date1']").text(deal(obj1.h_admission_date));
		$("span[name='h_discharge_date1']").text(deal(obj1.h_discharge_date));
		$("p[name='h_hospitalized_reason1']").text("原因："+deal(obj1.h_hospitalized_reason)+"");
		$("p[name='h_medical_institution1']").text("医疗机构名称："+deal(obj1.h_medical_institution)+"");
		$("p[name='h_medical_number1']").text("病案号："+deal(obj1.h_medical_number)+"");
	}catch(e){}
	try {
		var obj2 = history_of_the_hospital[1];
		$("span[name='h_admission_date2']").text(deal(obj2.h_admission_date));
		$("span[name='h_discharge_date2']").text(deal(obj2.h_discharge_date));
		$("p[name='h_hospitalized_reason2']").text("原因："+deal(obj2.h_hospitalized_reason)+"");
		$("p[name='h_medical_institution2']").text("医疗机构名称："+deal(obj2.h_medical_institution)+"");
		$("p[name='h_medical_number2']").text("病案号："+deal(obj2.h_medical_number)+"");
	}catch(e){}
	var family_history_of_bed = [];
	try {
		family_history_of_bed = eval("(" + obj.family_history_of_bed + ")");
	} catch(e) {}
	try {
		var obj1 = family_history_of_bed[0];
		$("span[name='f_bed_withdrawal_date1']").text(deal(obj1.f_bed_withdrawal_date));
		$("span[name='f_bed_built_date1']").text(deal(obj1.f_bed_built_date));
		$("p[name='f_family_hospitalized_reason1']").text("原因："+deal(obj1.f_family_hospitalized_reason)+"");
		$("p[name='f_medical_institution1']").text("医疗机构名称："+deal(obj1.f_medical_institution)+"");
		$("p[name='f_medical_number1']").text("病案号："+deal(obj1.f_medical_number)+"");
	}catch(e) {}
	try {
		var obj2 = family_history_of_bed[1];
		$("span[name='f_bed_withdrawal_date2']").text(deal(obj2.f_bed_withdrawal_date));
		$("span[name='f_bed_built_date2']").text(deal(obj2.f_bed_built_date));
		$("p[name='f_family_hospitalized_reason2']").text("原因："+deal(obj2.f_family_hospitalized_reason)+"");
		$("p[name='f_medical_institution2']").text("医疗机构名称："+deal(obj2.f_medical_institution)+"");
		$("p[name='f_medical_number2']").text("病案号："+deal(obj2.f_medical_number)+"");
	}catch(e) {}	
}
// 10 主要用药情况数据处理
function assignMainDrugData(obj) {
	var arr = [];
	try {
		arr = eval("(" + obj.the_main_drug_use + ")");
	} catch(e) {}
	try {
		var obj1 = arr[0];
		$("p[name='m_medicine_name1']").text("药物名称:" + deal(obj1.m_medicine_name));
		$("p[name='m_usage1']").text("用法:"+deal(obj1.m_usage));
		$("p[name='m_usage_amount1']").text("用量:"+deal(obj1.m_usage_amount));
		$("p[name='m_medical_time1']").text("用药时间:"+deal(obj1.m_medical_time));
		$("select[name='m_medication_compliance1']").val(deal(obj1.m_medication_compliance));
	} catch(e){}
	try {
		var obj2 = arr[1];
		$("p[name='m_medicine_name2']").text("药物名称:" + deal(obj2.m_medicine_name));
		$("p[name='m_usage2']").text("用法:"+deal(obj2.m_usage));
		$("p[name='m_usage_amount2']").text("用量:"+deal(obj2.m_usage_amount));
		$("p[name='m_medical_time2']").text("用药时间:"+deal(obj2.m_medical_time));
		$("select[name='m_medication_compliance2']").val(deal(obj2.m_medication_compliance));
	} catch(e){}
	try {
		var obj3 = arr[2];
		$("p[name='m_medicine_name3']").text("药物名称:" + deal(obj3.m_medicine_name));
		$("p[name='m_usage3']").text("用法:"+deal(obj3.m_usage));
		$("p[name='m_usage_amount3']").text("用量:"+deal(obj3.m_usage_amount));
		$("p[name='m_medical_time3']").text("用药时间:"+deal(obj3.m_medical_time));
		$("select[name='m_medication_compliance3']").val(deal(obj3.m_medication_compliance));
	} catch(e){}
}
// 11 非免疫规划预防接种史
function assignNonEpiVaccinationHistory(obj) {
	var arr = [];
	try {
		arr = eval("("+obj.non_epi_vaccination_history+")");
	} catch(e) {}
	try {
		var obj1 = arr[0];
		$("p[name='n_name1']").text("名称："+deal(obj1.n_name)+"");
		$("p[name='n_date1']").text("接种日期："+deal(obj1.n_date)+"");
		$("p[name='n_org_name1']").text("接种机构："+deal(obj1.n_org_name)+"");
	} catch(e) {}
	
	try {
		var obj2 = arr[1];
		$("p[name='n_name2']").text("名称："+deal(obj2.n_name)+"");
		$("p[name='n_date2']").text("接种日期："+deal(obj2.n_date)+"");
		$("p[name='n_org_name2']").text("接种机构："+deal(obj2.n_org_name)+"");
	} catch(e) {}
	
	try {
		var obj3 = arr[2];
		$("p[name='n_name3']").text("名称："+deal(obj3.n_name)+"");
		$("p[name='n_date3']").text("接种日期："+deal(obj3.n_date)+"");
		$("p[name='n_org_name3']").text("接种机构："+deal(obj3.n_org_name)+"");
	} catch(e) {}
}

//12 现存主要健康问题数据处理
function assignNowHealthProblems(obj) {
	obj.cerebrovascular_disease == 1 ? $("input[name='cerebrovascular_disease']").attr("checked",'true') : "";
	obj.ischemic_stroke == 1 ? $("input[name='ischemic_stroke']").attr("checked",'true') : "";
	obj.cerebral_hemorrhage == 1 ? $("input[name='cerebral_hemorrhage']").attr("checked",'true') : "";
	obj.subarachnoid_hemorrhage == 1 ? $("input[name='subarachnoid_hemorrhage']").attr("checked",'true') : "";
	obj.transient_ischemic == 1 ? $("input[name='transient_ischemic']").attr("checked",'true') : "";
	obj.cerebrovascular_disease_other == 1 ? $("input[name='cerebrovascular_disease_other']").attr("checked",'true') : "";
	$("input[name='cerebrovascular_disease_other_text']").val(deal(obj.cerebrovascular_disease_other_text));
	obj.kidney_disease == 1 ? $("input[name='kidney_disease']").attr("checked",'true') : "";
	obj.diabetic_nephropathy == 1 ? $("input[name='diabetic_nephropathy']").attr("checked",'true') : "";
	obj.renal_ailure == 1 ? $("input[name='renal_ailure']").attr("checked",'true') : "";
	obj.acute_nephritis == 1 ? $("input[name='acute_nephritis']").attr("checked",'true') : "";
	obj.chronic_nephritis == 1 ? $("input[name='chronic_nephritis']").attr("checked",'true') : "";
	obj.kidney_disease_other == 1 ? $("input[name='kidney_disease_other']").attr("checked",'true') : "";
	$("input[name='kidney_disease_other_text']").val(deal(obj.kidney_disease_other_text));
	obj.heart_disease == 1 ? $("input[name='heart_disease']").attr("checked",'true') : "";
	obj.myocardial_infarction == 1 ? $("input[name='myocardial_infarction']").attr("checked",'true') : "";
	obj.angina == 1 ? $("input[name='angina']").attr("checked",'true') : "";
	obj.coronary_revascularization == 1 ? $("input[name='coronary_revascularization']").attr("checked",'true') : "";
	obj.congestive_heart == 1 ? $("input[name='congestive_heart']").attr("checked",'true') : "";
	obj.chest_pain == 1 ? $("input[name='chest_pain']").attr("checked",'true') : "";
	obj.heart_disease_other == 1 ? $("input[name='heart_disease_other']").attr("checked",'true') : "";
	$("input[name='heart_disease_other_text']").val(deal(obj.heart_disease_other_text));
	obj.vascular_disease == 1 ? $("input[name='vascular_disease']").attr("checked",'true') : "";
	obj.dissecting_aneurysm == 1 ? $("input[name='dissecting_aneurysm']").attr("checked",'true') : "";
	obj.arterial_occlusive == 1 ? $("input[name='arterial_occlusive']").attr("checked",'true') : "";
	obj.vascular_disease_other == 1 ? $("input[name='vascular_disease_other']").attr("checked",'true') : "";
	$("input[name='vascular_disease_other_text']").val(deal(obj.vascular_disease_other_text));
	obj.eye_diseases == 1 ? $("input[name='eye_diseases']").attr("checked",'true') : "";
	obj.or_exudative_retinal_hemorrhage == 1 ? $("input[name='or_exudative_retinal_hemorrhage']").attr("checked",'true') : "";
	obj.papilledema == 1 ? $("input[name='papilledema']").attr("checked",'true') : "";
	obj.cataract == 1 ? $("input[name='cataract']").attr("checked",'true') : "";
	obj.eye_diseases_other == 1 ? $("input[name='eye_diseases_other']").attr("checked",'true') : "";
	$("input[name='eye_diseases_other_text']").val(deal(obj.eye_diseases_other_text));
	$("input[name='nervous_system_diseases'][value='" + obj.nervous_system_diseases + "']").attr("checked",'checked');
	$("input[name='nervous_system_diseases_text']").val(deal(obj.nervous_system_diseases_text));
	$("input[name='other_diseases'][value='" + obj.other_diseases + "']").attr("checked",'checked');
	$("input[name='other_diseases_text']").val(deal(obj.other_diseases_text));
}
// 13 健康评价
function assignHealthAssessment(obj) {
	$("input[name='health_assessment'][value='" + obj.health_assessment + "']").attr("checked",'checked');
	$("input[name='health_assessment_exception1']").val(deal(obj.health_assessment_exception1));
	$("input[name='health_assessment_exception2']").val(deal(obj.health_assessment_exception2));
	$("input[name='health_assessment_exception3']").val(deal(obj.health_assessment_exception3));
	$("input[name='health_assessment_exception4']").val(deal(obj.health_assessment_exception4));
}
// 14 健康指导
function assignHealthGuidance(obj) {
	obj.health_guidance_ipwchm == 1 ? $("input[name='health_guidance_ipwchm']").attr("checked",'true') : "";
	obj.health_guidance_review == 1 ? $("input[name='health_guidance_review']").attr("checked",'true') : "";
	obj.health_guidance_referral == 1 ? $("input[name='health_guidance_referral']").attr("checked",'true') : "";
	obj.control_of_risk_quit_smoking == 1 ? $("input[name='control_of_risk_quit_smoking']").attr("checked",'true') : "";
	obj.control_of_risk_health_drink == 1 ? $("input[name='control_of_risk_health_drink']").attr("checked",'true') : "";
	obj.control_of_risk_diet == 1 ? $("input[name='control_of_risk_diet']").attr("checked",'true') : "";
	obj.control_of_risk_exercise == 1 ? $("input[name='control_of_risk_exercise']").attr("checked",'true') : "";
	obj.control_of_risk_weight_reduction == 1 ? $("input[name='control_of_risk_weight_reduction']").attr("checked",'true') : "";
	$("input[name='control_of_risk_weight_reduction_text']").val(deal(obj.control_of_risk_weight_reduction_text));
	obj.control_of_risk_recommend_vaccination == 1 ? $("input[name='control_of_risk_recommend_vaccination']").attr("checked",'true') : "";
	$("input[name='control_of_risk_recommend_vaccination_text']").val(deal(obj.control_of_risk_recommend_vaccination_text));
	obj.control_of_risk_other == 1 ? $("input[name='control_of_risk_other']").attr("checked",'true') : "";
	$("input[name='control_of_risk_other_text']").val(deal(obj.control_of_risk_other_text));
}

function deal(data) {
	return data == undefined ? "" : data;
}