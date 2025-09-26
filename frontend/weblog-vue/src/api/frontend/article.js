import axios from "@/axios";

// 获取文章列表
export function getArticlePageList(data) {
    return axios.post("weblog/article/list", data)
}
export function getArticleDetail(articleId) {
    return axios.post("weblog/article/detail", { articleId })
}
export function updateReadNum(articleId) {
    return axios.post("weblog/article/updateReadNum", { articleId })
}