// 引入element插件，也可以不用，看具体项目需求
// import { Message } from "element-ui";

import { ElNotification , ElMessageBox , ElMessage } from 'element-plus'
import { getToken } from '@/untils/auth'
import errorCode from '@/untils/errorCode'
import store from '@/store'


// 引入路由，登录失效时跳转回登录页
import router from "../router/index";
// 文件处理方法
// import { file } from "@/assets/js/file.js";

import axios from 'axios'

// const axios = require("axios");
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
// 超时时间
const fetch = axios.create({
  baseURL: "/api",
  timeout: 15 * 1000
});

// fetch.interceptors.request.use(
//   config => {
//     // 设置token
//     const token = ''
//     config.headers.common["Authorization"] = token;
//     return config;
//   },
//   error => {
//     return Promise.reject(error);
//   }
// );
//
// fetch.interceptors.response.use(
//   res => {
//     // 判断返回数据类型，如果是文件，则执行下载；否则判断接口是否请求成功，具体状态与后端协商，这里以res.data.success举例
//     if (
//       res.headers["content-type"] === "application/vnd.ms-excel;charset=UTF-8"
//     ) {
//       // 下载文件
//       file(res);
//     } else if (res.data.success === true) {
//       return res.data;
//     } else {
//       return Promise.reject(res.data);
//     }
//   },
//   error => {
//     const stateCode = error.response.status;
//     // 这里只添加了401状态码，如有需求可自行添加更多状态码
//     if (stateCode === 401) {
//       // 这里记得清除token
//       Message.closeAll();
//       Message.error("登录已失效，请重新登录！");
//       router.push("/login");
//     } else {
//       // 清除多余失败提示框
//       Message.closeAll();
//     }
//     return Promise.reject(error);
//   }
// );
// request拦截器
fetch.interceptors.request.use(config => {
  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false
  if (getToken() && !isToken) {
    config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  // get请求映射params参数
  if (config.method === 'get' && config.params) {
    let url = config.url + '?';
    for (const propName of Object.keys(config.params)) {
      const value = config.params[propName];
      var part = encodeURIComponent(propName) + "=";
      if (value !== null && typeof(value) !== "undefined") {
        if (typeof value === 'object') {
          for (const key of Object.keys(value)) {
            let params = propName + '[' + key + ']';
            var subPart = encodeURIComponent(params) + "=";
            url += subPart + encodeURIComponent(value[key]) + "&";
          }
        } else {
          url += part + encodeURIComponent(value) + "&";
        }
      }
    }
    url = url.slice(0, -1);
    config.params = {};
    config.url = url;
  }
  return config
}, error => {
  console.log(error)
  Promise.reject(error)
})

// 响应拦截器
fetch.interceptors.response.use(res => {
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200;
    // 获取错误信息
    const msg = errorCode[code] || res.data.msg || errorCode['default']
    if (code === 401) {
      ElMessageBox .confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      })
    } else if (code === 500) {
      ElMessage({
        message: msg,
        type: 'error'
      })
      return Promise.reject(new Error(msg))
    } else if (code !== 200) {
      ElNotification .error({
        title: msg
      })
      return Promise.reject('error')
    } else {
      return res.data
    }
  },
  error => {
    console.log('err' + error)
    let { message } = error;
    if (message == "Network Error") {
      message = "后端接口连接异常";
    }
    else if (message.includes("timeout")) {
      message = "系统接口请求超时";
    }
    else if (message.includes("Request failed with status code")) {
      message = "系统接口" + message.substr(message.length - 3) + "异常";
    }
    ElMessage({
      message: message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)
export default fetch;
