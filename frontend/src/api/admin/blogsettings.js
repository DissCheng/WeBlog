import axios from "@/axios";

// 获取博客设置详情
export function getBlogSettingsDetail() {
    return axios.get("/users/blog/settings/detail")
}

export function updateBlogSettings(data) {
    return axios.post("/users/blog/settings/update", data)
}