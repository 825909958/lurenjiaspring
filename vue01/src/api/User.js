import request from "@/utils/request";

// 登录
export function login(data) {
  return request("/user/login", "post", data);
}

// 退出登录
export function logout(data) {
  return request("/user/logout", "post", data);
}
