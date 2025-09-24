import { useCookies } from '@vueuse/integrations/useCookies'

const cookie = useCookies()

// ============================== Token 令牌 ==============================

// 存储在 Cookie 中的 Token 的 key
const TOKEN_KEY = 'Authorization'
    // 存储在 Cookie 中的 Refresh Token 的 key
const REFRESH_TOKEN_KEY = 'RefreshToken'

// 获取 Token 值
export function getToken() {
    return cookie.get(TOKEN_KEY)
}

// 设置 Token 到 Cookie 中
export function setToken(token) {
    return cookie.set(TOKEN_KEY, token)
}

// 删除 Token
export function removeToken() {
    return cookie.remove(TOKEN_KEY)
}

// 获取 Refresh Token 值
export function getRefreshToken() {
    return cookie.get(REFRESH_TOKEN_KEY)
}

// 设置 Refresh Token 到 Cookie 中
export function setRefreshToken(refreshToken) {
    return cookie.set(REFRESH_TOKEN_KEY, refreshToken)
}

// 删除 Refresh Token
export function removeRefreshToken() {
    return cookie.remove(REFRESH_TOKEN_KEY)
}

// ============================== 标签页 ==============================

// 存储在 Cookie 中的标签页数据的 key
const TAB_LIST_KEY = 'tabList'

// 获取 TabList
export function getTabList() {
    return cookie.get(TAB_LIST_KEY)
}

// 存储 TabList 到 Cookie 中
export function setTabList(tabList) {
    return cookie.set(TAB_LIST_KEY, tabList)
}