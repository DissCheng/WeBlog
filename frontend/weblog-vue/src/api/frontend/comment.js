import axios from "@/axios.js"

// 获取分类列表
export function getCategoryList(data) {
    return axios.get("/users/category/listAll")
}

// 获取评论
export function getComment(data){
    return axios.post("")
}

// 提交评论
export function addComment(data){
    return axios.post("")
}

// 删除评论
export function deleteComment(id){
    return axios.post("")
}

// 点赞评论
export function likeComment(id){
    return axios.post("")
}