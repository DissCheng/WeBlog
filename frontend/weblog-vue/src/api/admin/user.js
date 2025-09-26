import axios from "@/axios";



// 登录接口
export function login(username, password) {
    return axios.post("weblog/users/login", { username, password })
}
// 获取登录用户信息
export function getUserInfo() {
    return axios.post("weblog/users/info")
}
// 修改用户密码
export function updateAdminPassword(data) {
    return axios.post("weblog/users/password/update", data)
}
// 注册接口
export function register(username, password) {
    return axios.post("weblog/users/register", { username, password })
}
// 查询权限接口
export function getPermission() {
    return axios.get("weblog/users/getPermission")
}