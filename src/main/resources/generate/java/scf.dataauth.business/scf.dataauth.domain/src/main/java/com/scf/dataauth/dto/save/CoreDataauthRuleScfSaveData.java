package com.scf.dataauth.dto.save;

import com.scf.component.core.domain.entity.BaseEntity;
import com.scf.component.core.domain.entity.BaseMainSaveData;
import com.scf.dataauth.domain.CoreDataauthRuleScf;
import com.scf.dataauth.domain.CoreDataauthRuleMapperScf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：规则定义表保存时dto
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON           REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
public class CoreDataauthRuleScfSaveData implements BaseMainSaveData<CoreDataauthRuleScf> {
    private static Map<String, Class< ? extends BaseEntity>> detailTypeMap;
    public static final String INFO_KEY = "ruleData";
    public static final String RuleMapper = "ruleMapperList";

    private CoreDataauthRuleScf ruleData; // 规则定义表(主表)
    private List<CoreDataauthRuleMapperScf> ruleMapperList; // 规则数据权限Mapper明细表(明细)

    public CoreDataauthRuleScf getRuleData() {
        return ruleData;
    }
    public void setRuleData(CoreDataauthRuleScf ruleData) {
        this.ruleData = ruleData;
    }

    public List<CoreDataauthRuleMapperScf> getRuleMapperList() {
        return ruleMapperList;
    }
    public void setRuleMapperList(List<CoreDataauthRuleMapperScf> ruleMapperList) {
        this.ruleMapperList = ruleMapperList;
    }

    @Override
    public String getMainKey() {
        return INFO_KEY;
    }

    @Override
    public CoreDataauthRuleScf getMainData() {
        return ruleData;
    }

    @Override
    public void setMainData(CoreDataauthRuleScf mainData) {
        this.ruleData = mainData;
    }

    @Override
    public Map<String, List<? extends BaseEntity>> getDetailDataMap() {
        Map<String, List<? extends BaseEntity>> map = new HashMap<>();
        map.put(RuleMapper, ruleMapperList);
        return map;
    }

    @Override
    public Map<String, Class< ? extends BaseEntity>> getDetailTypeMap() {
        if (detailTypeMap == null) {
            Map<String, Class<? extends BaseEntity>> map = new HashMap<>();
            map.put(RuleMapper, CoreDataauthRuleMapperScf.class);
            detailTypeMap = map;
        }
        return detailTypeMap;
    }
}
