import axios from "axios";
import { getToken, removeToken, setToken, getRefreshToken, setRefreshToken } from "@/composables/cookie";
import { showMessage } from "./composables/util";
import { useUserStore } from '@/stores/user'
import router from "@/router";
// 创建 Axios 实例
const instance = axios.create({
    baseURL: "/api", // 你的 API 基础 URL
    timeout: 7000, // 请求超时时间
})

// 添加请求拦截器
instance.interceptors.request.use(function(config) {
    // 在发送请求之前做些什么
    const token = getToken()
    const refreshToken = getRefreshToken()
        // 当 token 不为空时
    if (token) {
        // 添加请求头
        config.headers['jwtToken'] = token
    }
    if (refreshToken) {
        config.headers['refreshToken'] = refreshToken
    } else {
        config.headers['refreshToken'] = ''
    }
    return config;
}, function(error) {
    // 对请求错误做些什么
    return Promise.reject(error)
});



// 添加响应拦截器
instance.interceptors.response.use(function(response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response.data
}, function(error) {
    // 捕获 401 状态码

    if (error.response && error.response.status === 401) {
        // 检查响应头中是否包含新的 Token
        const newToken = error.response.headers['jwttoken'];


        if (newToken) {
            // 更新本地存储中的 Token
            setToken(newToken);

            // 重新发起原始请求
            const config = error.config;
            config.headers['jwtToken'] = newToken;

            return instance(config); // 使用新的 Token 重新发起请求
        } else {
            // 如果没有新的 Token，提示用户重新登录
            const useStore = useUserStore();
            useStore.logout();
            router.push('/login');
            showMessage('请先登录', 'warning');
            return Promise.reject(error);
        }
    }

    let errorMsg = error.response.data.message || '请求失败'
        // 弹错误提示
    showMessage(errorMsg, 'error')

    return Promise.reject(error)
})

// 暴露出去
export default instance;