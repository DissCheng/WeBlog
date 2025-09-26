import axios from "@/axios";

// 获取分类列表
export function getCategoryList(data) {
    return axios.get("weblog/users/category/listAll")
}

// 获取分类-文章列表
export function getCategoryArticlePageList(data) {
    return axios.post("weblog/category/article/list", data)
}

