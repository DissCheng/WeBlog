import axios from "@/axios";

// 获取文章分页数据
export function getArticlePageList(data) {
    return axios.post("/users/article/list", data)
}
// 删除文章
export function deleteArticle(id) {
    return axios.delete("/users/article/delete/" + id)
}
// 发布文章
export function publishArticle(data) {
    return axios.post("/users/article/publish", data)
}

//获取文章详情
export function getArticleDetail(id) {
    return axios.get("/users/article/detail/" + id)
}

//更新文章
export function updateArticle(data) {
    return axios.post("/users/article/update", data)
}