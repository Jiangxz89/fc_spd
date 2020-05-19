package com.thinkgem.hys.pd.web.task;

import com.thinkgem.hys.pd.dao.PdSupplierDao;
import com.thinkgem.hys.pd.dao.PdVenderDao;
import com.thinkgem.hys.pd.entity.PdSupplier;
import com.thinkgem.hys.pd.entity.PdVender;
import com.thinkgem.hys.pd.service.PdSupplierService;
import com.thinkgem.hys.pd.service.PdVenderService;
import com.thinkgem.hys.utils.PlatDateUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AvailableDateTask {
    private Logger logger = LoggerFactory.getLogger(AvailableDateTask.class);
    @Autowired
    private PdSupplierService pdSupplierService;
    @Autowired
    private PdSupplierDao pdSupplierDao;
    @Autowired
    private PdVenderService pdVenderService;
    @Autowired
    private PdVenderDao pdVenderDao;
/**
 * 获取厂家证件信息及供应商证件信息，判断是否超过有效期；
 * 判断证件有效期是否大于当前日期，如果大于当前日期，直接设置为已过期
 * 获取静态字典表配置有效期天数信息，
 * 如果不大于当前日期，那么加上设置的有效期天数，在判断是否大于当前日期，如果大于，则设置为即将过期
 * 根据有效期天数信息判断信息表中的证件有效期是否是已过期，或者是即将过期，如果是已过期或者是即将过期，则更新基本信息表中的有效期标识字段；
 */
    public void start() {
        logger.info("定时任务进来了AvailableDateTask");
        Date date = DateUtils.getNowDate();//当前日期
         Integer remind = 0;//设定的有效期限   默认 0；
        String day=DictUtils.getDictLabel("1", "avl_dt","");
        if(!StringUtils.isEmpty(day)){
            remind=Integer.valueOf(day);
        }
        //获取生产厂家信息数据
         PdVender vender = new PdVender();
        List<PdVender> venderList = pdVenderService.findList(vender);
        if (venderList != null && venderList.size() > 0) {
            for (PdVender pdVender : venderList) {
                Date afterMonthDate = null;
                String dateStr = null;
                Date licenceDate = null;
                String valType = "0";// 0：未过期  1：即将过期    2：已过期
                dateStr = pdVender.getLicenceDate1();//获取第一组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate2();//获取第二组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate3();//获取第三组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate4();//获取第四组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate5();//获取第五组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate6();//获取第六组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate7();//获取第七组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                   afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate8();//获取第八组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                   afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate9();//获取第九组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                   afterMonthDate = this.getDateToAddDate(date, remind);
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate10();//获取第十组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate11();//获取第十一组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
                dateStr = pdVender.getLicenceDate12();//获取第十二组证件有效期
                if (!StringUtils.isEmpty(dateStr)) {
                    licenceDate=PlatDateUtils.changeStringToDate(dateStr);
                    afterMonthDate = this.getDateToAddDate(date, remind);
                    //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                    valType = this.testDate(date, licenceDate, afterMonthDate);
                    if (!"0".equals(valType)) {
                        pdVender.setAvlType(valType);
                        pdVenderDao.update(pdVender);
                        continue;
                    }
                }
            }
        }
//-----------------------
            //获取供应商信息数据
            PdSupplier supplier = new PdSupplier();
        supplier.setSupplierName("上饶市玖段贸易公司");
            List<PdSupplier> list = pdSupplierService.findList(supplier);
            if (list != null && list.size() > 0) {
                for (PdSupplier pdSupplier : list) {
                    Date afterMonthDate = null;
                    Date validityTerm = null;
                    String valType = "0";// 0：未过期  1：即将过期    2：已过期
                    validityTerm = pdSupplier.getValidityTerm1();//获取第一组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        //如果有效期不等于当前日期，并且当前日期比证件有效期小，则过期了
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm2();//获取第二组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm3();//获取第三组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm4();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm5();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm6();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm7();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm8();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm9();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm10();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm11();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                    validityTerm = pdSupplier.getValidityTerm12();//获取第四组证件有效期
                    if (validityTerm !=null) {
                        afterMonthDate = this.getDateToAddDate(date, remind);
                        valType = this.testDate(date, validityTerm, afterMonthDate);
                        if (!"0".equals(valType)) {
                            pdSupplier.setAvlType(valType);
                            pdSupplierDao.update(pdSupplier);
                            continue;
                        }
                    }
                }
            }
        }

/**
 * 判断证件有效期是否在有效期内
 * @param date
 * @param ValidityTerm
 * @param afterMonthDate
 * @return
 */
        public String testDate(Date date, Date ValidityTerm, Date afterMonthDate) {
            String avlType = "0";
            if ((!PlatDateUtils.sameDate(date, ValidityTerm)) && date.after(ValidityTerm)) {//过期
                avlType = "2";
            } else if((PlatDateUtils.sameDate(date, ValidityTerm)) ||  afterMonthDate.after(ValidityTerm)) {//即将过期
                avlType = "1";
            }
            return avlType;
        }




    /**
     * 获得指定日期之后nDay日
     */
    static public Date getDateToAddDate(Date tsDate, int nDay) {
        if (null == tsDate)
            return null;
        // Comment and Replaced by yangwentao
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(tsDate);
        calendar.add(Calendar.DATE, nDay);
        Date resDate = calendar.getTime();
        return resDate;
    }
    }
