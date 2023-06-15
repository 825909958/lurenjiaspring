1.修改定时任务问题

2.完成权限关联的自动脚本

3.发现不同schemal的多事务问题，导致的脏数据，导致定时任务无法正常跑到working

4.定位并修改liferaft和certjob共用页面卡顿问题



1.liferaft ready for ss appraval出现的bug

2.send to e-sign签名后端校验上传模板问题



1.把表字段名字改了 demand note ->id    dn demand --->dn 



1.大项回滚

3.liferaft中的小项pdf展示在那里？  5-8解决



待结论:1.有保证金的流程？

2.liferaft的approvalBy改下

> 1.price 带入默认值
>
> 2.overSea也要statement,并在statement做相应的计算处理，以及statement中的bug
>
> 3.流程相关
>
> 4.raft cert加文本域字段 5.raft 默认带一条liferaft type数据
>
> 2.保存账单和发送邮件分开  **todo等邮件**
>
> 3.raft流程
>
> 
>
> 代问Natalie问题:1.add错了如何修改
>
> 2.每个add存一条数据  、两个save分别关联什么保存那些内容到那个表
>
> 3.多个账单word中的fee description 如何显示？
>
> 4。状态换顺序，如何发送statement? 如果需要发送邮件，有保证金和无保证金都有问题
>
> 5.statement中的patment date 取那个字段？目前取得创建时间
>
> 6.Ready for statement 改成最终状态的问题
>
> ​    定时任务需要更改有保证金的完成状态判断？
>
> 8.crete statement 是否提示账单还没有结算？

1.listgrid传appNo即可，optype在后端查询申请下的所有账单list，

2.**Total Deposit** fetchdata之后计算list的账单和  **C**

3.D:账

E：



```


currentTask.setOSS_DYNFORM_ID(entity.getOssDynformId());
inboxService.save(currentTask);
```

```
var ossDynformId = createNewApplicationForm.getValue("ossDynformId");
if (ossDynformId) {
    taskDS.fetchData({ossDynformId: ossDynformId, name: "PSSApp_EBSCreated"}, function (rep, data, req) {
        if (data.size() > 0) {
                taskDS.removeData(data[0]);
        }
    });
}
```

1、提供一个接口，service 层接口，OSS_DYNFORM_ID,
2、每个流程都要OSS_DYNFORM_ID,
3、FSQC_DYNFORM表回填信息根据OSS_DYNFORM_ID

>  1.传入ossId 插入Indextask一条数据  
>
>  2.save时改变task名字
>
>  ```
>  //     Task task1 = inboxService.insertOSSData("2023-XXXX", 111111L);
>  ```
>
>  lift approvalBy,sumitBy?raftReadyForApproval   lifeESigning
>
>  1.提示语修改
>
>  2.liferaft流程问题修改,加上权限



>1.oversea 为 Y=》 create statement 隐藏
>
>2.在點擊工作流按鈕“Confirm Actual Amount”時，如果“Actual Amount”中的輸入值與“Revenue”中的值不同，彈出警告信息"
>
>3.状态改变 save触发 
>
>4.approval by 入库js代码优化

1.drawing save source 不能为空？  *****

1.cert ready-for-issue rollback    drawing or survey rollback  
2.根据 approval by  显示 ready for E-sing
3.加||FSQC_ALL
4.withdrawn cancel还原逻辑
5.保证金为0的按钮全现问题

1.cert job  upload问题
2.账单取消 隐藏按钮  
3.大项定时任务完成状态inbox task没修改问题


1.账单取消 隐藏按钮  
2.email 发送失败 如何处理？   问          ???????????????這個先記起來因為bccclc那邊也沒有處理    等待处理方案
         
4.companyName 什么时候该禁止修改       ??????先處理company name其他的等用戶回覆才改吧   等待处理方案

1.五保证金创第二个账单，第二个账单作废但是大项已改，再创不应该改
2.jobitem received spname 显示
4.inbox页面打开  的applicationdate  存在可能空的情况
5.定时任务结算有问题

1.item complete之后inbox应该也更着修改，不然inbox显示的是上个状态的值
2.账单取消 按钮显示
3.recevied
4.index下拉框
5.dn状态取消后应该在创
6.inbox pssitem ready forcheck显示问题

1..demand caneldamand
2.pssitem 显示
3.ready for checking 改状态
4.定时任务