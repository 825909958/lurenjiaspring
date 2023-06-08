```
-- --------------删除角色权限关联表的脏数据
--  delete  from FSQCDB.dbo.FUNC_ENTITLES where ROLE_ID in ('1') and
-- FUNC_ID not in ((select SYSTEM_FUNCS.FUNC_ID from FSQCDB.dbo.SYSTEM_FUNCS))
```

```
async function queryAssAgency() {
   var agencyData = await new Promise(function (resolve, reject) {
      assAgencyDS.fetchData({}, function (response, data, request) {
         resolve(data);
      }, {
         // 配置参数
         operationId: "FETCH_BY_ASSESSMENT_AGENCY_REPORT_PEAK",
         useXmlHttpRequest: true,
         async: true
      });
   });
   return agencyData;
}
```