import fetch from './fetch'
import qs from 'qs';

function request(url, method, data, blob) {
  switch (method) {
    case 'get':
      return fetch({
        method: 'get',
        url,
        params: data
      })
    case 'post':
      return fetch({
        method: 'post',
        url,
        data,
        responseType: blob || '' // 判断数据类型
      })
    case 'delete':
      return fetch({
        method: 'delete',
        url,
        params: data
      })
    case 'put':
      return fetch({
        method: 'put',
        url,
        data: qs.stringify(data)
      })
    default:
      return fetch
  }
}

export default request;
