// 文件流类型
export const downLoadFile = res => {
  const blob = new Blob([res.data], {
    type: "application/vnd.ms-excel"
  });
  // 创建标签
  const link = document.createElement("a");
  // 隐藏标签
  link.style.display = "none";
  // 添加文件
  link.href = URL.createObjectURL(blob);
  // 获取文件名（此处看后端接口如何返回，非固定）
  const name = res.headers["content-disposition"].split(";")[1];
  link.download = decodeURI(name);
  // 添加子节点
  document.body.appendChild(link);
  // 模拟点击下载
  link.click();
  // 清除子节点
  document.body.removeChild(link);
};

// 文件地址类型
export const clickDownload = data => {
  // 创建标签
  const link = document.createElement("a");
  // 隐藏标签
  link.style.display = "none";
  // 添加文件
  link.href = data.url;
  // 添加文件名（此方法在本地不会生效，部署到线上可正常使用）
  link.download = data.name;
  // 添加子节点
  document.body.appendChild(link);
  // 模拟点击下载
  link.click();
  // 清除子节点
  document.body.removeChild(link);
};

// 获取文件格式（此处是为了显示不同文件图标，总结为5类，具体可根据需求进行修改）
export const fileType = e => {
  if (!e) {
    return "";
  }
  let list = e.split(".");
  let code = "";
  const format = list[list.length - 1];
  switch (format) {
    case "xls":
      code = "excel";
      break;
    case "xlsx":
      code = "excel";
      break;
    case "doc":
      code = "word";
      break;
    case "docx":
      code = "word";
      break;
    case "pptx":
      code = "ppt";
      break;
    case "png":
      code = "img";
      break;
    case "jpg":
      code = "img";
      break;
    case "jpeg":
      code = "img";
      break;
    case "gif":
      code = "img";
      break;
    case "txt":
      code = "text";
      break;
    case "wps":
      code = "text";
      break;
  }
  return code;
};
