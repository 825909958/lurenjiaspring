import request from '@/untils/request';

// 登录
export function user(data) {
  return request('/login', 'post', data);
}

export function count(data) {
  return request('/count', 'post', data);
}

