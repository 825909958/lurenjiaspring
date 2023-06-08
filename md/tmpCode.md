# 1

![image-20230511134914940](C:\Users\Hunter Tan\AppData\Roaming\Typora\typora-user-images\image-20230511134914940.png)

![image-20230510174705357](C:\Users\Hunter Tan\AppData\Roaming\Typora\typora-user-images\image-20230510174705357.png)

```


function refreshLifeRaftCertJobPreview() {
    var record = {"jobId": jobId, "appNo": applicationNo};
    var requestArguments=["LiferaftGenerator", record]
    var requestParameters = {};
    DMI.call({appID:"fsqcApp",className:"reportDMI", methodName:"generate", arguments:requestArguments,
        callback:function(rpcResponse, data, rpcRequest){
            console.log(data)
            var xhr = new XMLHttpRequest();
            xhr.open("GET", data, true);
            xhr.responseType = 'blob';
            xhr.onreadystatechange = function () {
                if (xhr.readyState == XMLHttpRequest.DONE) {
                    if (xhr.status == 200) {
                        let blob = new Blob([xhr.response], {type: "octet/stream"});
                        const reader = new FileReader();
                        reader.onloadend = () => {
                            const base64 = reader.result.split(',')[1];
                            // 处理 Base64 编码的字符串
                            console.log(base64)
                            certJobPreviewObj.blob = base64;
                        }
                        reader.readAsDataURL(blob);
                    }
                }
            }
            xhr.send();
            DMI.call({appID:"fsqcApp",className:"reportDMI", methodName:"generate", arguments:requestArguments,
                callback:function(rpcResponse, data2, rpcRequest){
                    let fileURL=data2
                    console.log(fileURL)
                    certJobPreviewPane.setContents(`
                        <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <title>Title</title>
                            </head>
                             <div id="loadPssCertJobPreview" align="center" style="font-size: large"  >
                             Loading...
                            </div>
                            <iframe id="viewFramePssCertJobPreview" src="${fileURL}" width="100%" style="display: none" onload="onPssCertJobPreviewViewLoad()" height="100%" >
                            </iframe>
                        </html>`
                    );
                    certJobPreviewWindow.show();
                },
                requestParams:requestParameters
            });
        },
        requestParams:requestParameters
    });

    console.log("++++++++++++++")
    console.log(fileURL);

}
```

```

if (code === 'LIFERAFT') {
 var record = {"jobId" : jobId, "appNo": applicationNo}
 ReportViewWindow.displayReport(["LiferaftGenerator", record]);
}
```

```


var itemForm = isc.DynamicForm.create({
    ID:"itemForm",
    numCols: 6,
    width:"90%",
    fields: [
        {
            name: "FC_FEE_CODE", title: "Description",
            optionDataSource: "feeCodeDS",
            valueField: "FEE_CODE",
           // displayField:"ENG_DESC",
            pickListCriteria: {
                FORM_CODE: "09"
            },
            optionCriteria:{ACTIVE: 'Y'},
            formatValue: function (value, record, form, item) {
                var item = feeCodes.find({FEE_CODE: value});
                if (item != null) {
                    desc = item.ENG_DESC + (item.CHN_DESC != null ? " " + item.CHN_DESC : "");
                } else {
                    desc = value;
                }
                return desc;
            },
            required: true,
            colSpan: 6,
            width: 600,
            optionFilterContext: {"operationId": "FETCH_FOR_SR", "sortBy": "FEE_CODE"},
            changed: function (form, item, value) {
                form.getItem("CHARGED_UNITS").setValue(null);
                form.getItem("AMOUNT").setValue(null)
                form.getItem("unitPrice").setValue((item && item.getSelectedRecord()) ? item.getSelectedRecord().FEE_PRICE : null);
            },
        },
        {
            name: "unitPrice", title: "Price", required: true, type: "decimal", startRow: true,
            changed: function (form, item, value) {
                if (!isNull(value)) {
                    let chargedUnitsItem = form.getItem("CHARGED_UNITS");
                    if (!isNull(chargedUnitsItem)) {
                        var chargedUnitsItemValue = chargedUnitsItem.getValue();
                        if (!isNull(chargedUnitsItemValue)) {
                            form.getItem("AMOUNT").setValue(value * chargedUnitsItemValue);
                            return;
                        }
                    }
                }
                form.getItem("AMOUNT").setValue(0);
            }
        },
        {
            name: "CHARGED_UNITS", title: "Unit", required: true, type: "integer",
            changed: function (form, item, value) {
                var unitPriceItem = form.getItem("unitPrice");
                if (!isNull(value)) {
                    if (!isNull(unitPriceItem)) {
                        var unitPrice = unitPriceItem.getValue();
                        if (!isNull(unitPrice)) {
                            form.getItem("AMOUNT").setValue(value * unitPrice);
                            return;
                        }
                    }
                }
                form.getItem("AMOUNT").setValue(0);
            }
        },
        {name: "AMOUNT", title: "Amount", type: "decimal", required: true, format: "$#,###.00"},
        {name: "ADHOC_DEMAND_NOTE_TEXT", title: "Text", type: "textArea", rowSpan: 3, colSpan: 6, width: 420}
    ]
});
```

web pom 

- 67line <smartclient_home>D:\devtool</smartclient_home>

- 21 line


  <jetty-env>jetty-local-tht.xml</jetty-env>


